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
@RequestMapping("/api/points")
public class PointController {

    @Autowired
    private PointRepository pointRepository;

    // Retrieve all points
    @GetMapping
    public List<PointEntity> getAllPoints() {
        return pointRepository.findAll();
    }

    // Retrieve a specific point by ID
    @GetMapping("/{id}")
    public PointEntity getPointById(@PathVariable Long id) {
        return pointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Point not found with ID: " + id));
    }

    // Create a new point
    @PostMapping
    public PointEntity createPoint(@RequestBody PointEntity point) {
        return pointRepository.save(point);
    }

    // Update an existing point
    @PutMapping("/{id}")
    public PointEntity updatePoint(@PathVariable Long id, @RequestBody PointEntity updatedPoint) {
        PointEntity existingPoint = pointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Point not found with ID: " + id));

        existingPoint.setLatitude(updatedPoint.getLatitude());
        existingPoint.setLongitude(updatedPoint.getLongitude());

        return pointRepository.save(existingPoint);
    }

    // Delete a point by ID
    @DeleteMapping("/{id}")
    public String deletePoint(@PathVariable Long id) {
        pointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Point not found with ID: " + id));

        pointRepository.deleteById(id);
        return "Point deleted successfully with ID: " + id;
    }
}
