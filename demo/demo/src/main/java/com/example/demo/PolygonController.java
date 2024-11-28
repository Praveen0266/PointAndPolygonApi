package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polygons")
public class PolygonController {

    @Autowired
    private PolygonRepository polygonRepository;

    // Retrieve all polygons
    @GetMapping
    public List<PolygonEntity> getAllPolygons() {
        return polygonRepository.findAll();
    }

    // Retrieve a specific polygon by ID
    @GetMapping("/{id}")
    public PolygonEntity getPolygonById(@PathVariable Long id) {
        return polygonRepository.findById(id).orElseThrow(() -> new RuntimeException("Polygon not found"));
    }

    // Create a new polygon
    @PostMapping
    public PolygonEntity createPolygon(@RequestBody PolygonEntity polygon) {
        return polygonRepository.save(polygon);
    }

    // Update an existing polygon
    @PutMapping("/{id}")
    public PolygonEntity updatePolygon(@PathVariable Long id, @RequestBody PolygonEntity updatedPolygon) {
        PolygonEntity polygon = polygonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Polygon is found with ID: " + id));

        polygon.setCoordinates(updatedPolygon.getCoordinates());
        return polygonRepository.save(polygon);
    }

    // Delete a polygon
    @DeleteMapping("/{id}")
    public void deletePolygon(@PathVariable Long id) {
        polygonRepository.deleteById(id);
    }
}
