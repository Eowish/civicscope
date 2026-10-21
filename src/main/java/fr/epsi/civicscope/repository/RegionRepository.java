package fr.epsi.civicscope.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import fr.epsi.civicscope.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {
    Optional<Region> findByCode(String code);
    void deleteByCode(String code);
}
