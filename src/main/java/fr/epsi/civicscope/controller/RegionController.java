package fr.epsi.civicscope.controller;

import java.util.List;

import fr.epsi.civicscope.model.Region;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Objects;

import fr.epsi.civicscope.model.Region;

import fr.epsi.civicscope.model.Region;
import fr.epsi.civicscope.service.RegionService;

@RestController
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> getRegions() {
        return regionService.findAll();
    }

    @GetMapping("/regions")
    public List<Region> getAllRegion() {
        return this.regionService.findAll();
    }

    @GetMapping("/regions/{code}")
    public ResponseEntity<Region> getRegion(@PathVariable String code) {
        return regionService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Region> addRegion(@RequestBody Region region) {
        return new ResponseEntity<Region>(this.regionService.addRegion(region), HttpStatus.CREATED);
    }

    public ResponseEntity<Region> updateRegion(@RequestBody Region region) {
        if (Objects.nonNull(region)) {
            return new ResponseEntity<Region>(region, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<Region>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Region> deleteRegion(@PathVariable String code) {
        this.regionService.deleteRegion(code);
        return new ResponseEntity<Region>(HttpStatus.NO_CONTENT);
    }
}
