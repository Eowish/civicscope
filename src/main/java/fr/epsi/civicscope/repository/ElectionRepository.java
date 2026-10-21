package fr.epsi.civicscope.repository;

import fr.epsi.civicscope.model.Election;
import fr.epsi.civicscope.model.Election.TourElection;
import fr.epsi.civicscope.model.Election.TypeElection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ElectionRepository extends JpaRepository<Election, UUID> {

    boolean existsByCode(String code);

    Optional<Election> findByCode(String code);

    List<Election> findByDateBetweenAndTypeElectionAndTourElection(
            LocalDate debut, LocalDate fin, TypeElection typeElection, TourElection tourElection);
}