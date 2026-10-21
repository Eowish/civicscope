package fr.epsi.civicscope.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import fr.epsi.civicscope.model.Region;
import fr.epsi.civicscope.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;@Service

public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return this.regionRepository.findAll();
    }

    public Optional<Region> findByCode(String code) {
        return this.regionRepository.findByCode(code);
    }

    public Region addRegion(Region region) {
        return this.regionRepository.save(region);
    }

    public Region updateRegion(Region region) {
        Optional<Region> optionalRegion = this.regionRepository.findByCode(region.getCode());
        if (optionalRegion.isPresent()) {
            region.setId(optionalRegion.get().getId());
            return this.regionRepository.save(region);
        }
        else {
            return null;
        }
    }

    @Transactional
    public void deleteRegion(String code) {
        this.regionRepository.deleteByCode(code);
    }
}
