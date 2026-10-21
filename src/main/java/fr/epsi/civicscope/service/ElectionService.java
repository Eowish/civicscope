package fr.epsi.civicscope.service;

import fr.epsi.civicscope.exception.ElectionConflitException;
import fr.epsi.civicscope.exception.ElectionInvalideException;
import fr.epsi.civicscope.model.Election;
import fr.epsi.civicscope.repository.ElectionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;

    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    public List<Election> findAll(Integer limit) {
        Sort tri = Sort.by(Sort.Direction.DESC, "date");

        if (limit == null) {
            return electionRepository.findAll(tri);
        }
        if (limit <= 0) {
            throw new ElectionInvalideException("Une limite doit être strictement positive");
        }
        return electionRepository.findAll(PageRequest.of(0, limit, tri)).getContent();
    }

    public Optional<Election> findById(UUID id) {
        return electionRepository.findById(id);
    }

    public Election create(Election election) {
        valider(election);
        if (electionRepository.existsByCode(election.getCode())) {
            throw new ElectionConflitException("Le code " + election.getCode() + " est déjà utilisé");
        }
        verifierUnicite(election, null);
        election.setId(null);
        return electionRepository.save(election);
    }

    public Optional<Election> update(UUID id, Election updated) {
        valider(updated);
        return electionRepository.findById(id).map(existing -> {
            boolean codePrisParUneAutre = electionRepository.findByCode(updated.getCode())
                    .filter(autre -> !autre.getId().equals(id))
                    .isPresent();
            if (codePrisParUneAutre) {
                throw new ElectionConflitException("Le code " + updated.getCode() + " est déjà utilisé");
            }
            verifierUnicite(updated, id);
            existing.setCode(updated.getCode());
            existing.setDate(updated.getDate());
            existing.setTypeElection(updated.getTypeElection());
            existing.setTourElection(updated.getTourElection());
            existing.setCodePays(updated.getCodePays());
            return electionRepository.save(existing);
        });
    }

    public boolean delete(UUID id) {
        if (!electionRepository.existsById(id)) {
            return false;
        }
        electionRepository.deleteById(id);
        return true;
    }

    private void valider(Election election) {
        if (election.getCode() == null || election.getCode().isBlank()
                || election.getDate() == null
                || election.getTypeElection() == null) {
            throw new ElectionInvalideException("code, date et typeElection sont obligatoires");
        }
    }

    private void verifierUnicite(Election election, UUID idExclu) {
        int annee = election.getDate().getYear();
        LocalDate debut = LocalDate.of(annee, 1, 1);
        LocalDate fin = LocalDate.of(annee, 12, 31);

        boolean conflit = electionRepository
                .findByDateBetweenAndTypeElectionAndTourElection(
                        debut, fin, election.getTypeElection(), election.getTourElection())
                .stream()
                .anyMatch(autre -> !autre.getId().equals(idExclu));

        if (conflit) {
            throw new ElectionConflitException(
                    "Une élection de ce type existe déjà pour ce tour en " + annee);
        }
    }
}