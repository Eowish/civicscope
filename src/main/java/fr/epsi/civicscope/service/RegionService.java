package fr.epsi.civicscope.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import fr.epsi.civicscope.model.Region;
import fr.epsi.civicscope.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return this.regionRepository.findAll();
    }

    public Optional<Region> findByCode(String code) {
        return 
    }
}
