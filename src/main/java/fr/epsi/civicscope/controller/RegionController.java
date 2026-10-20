package fr.epsi.civicscope.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.civicscope.model.Region;
import fr.epsi.civicscope.service.RegionService;

@RestController
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/regions")
    public List<Region> getRegions() {
        return regionService.findAll();
    }

    @GetMapping("/regions/{code}")
    public ResponseEntity<Region> getRegion(@PathVariable String code) {
        return regionService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
