package com.example.HRproject.controller;

import com.example.HRproject.entity.AssignmentEntity;
import com.example.HRproject.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/personnel/{personnelId}")
    public ResponseEntity<List<AssignmentEntity>> getAssignmentsByPersonnel(@PathVariable Long personnelId) {
        List<AssignmentEntity> assignments = assignmentService.getAssignmentsByPersonnel(personnelId);
        return ResponseEntity.ok(assignments);
    }

    @PostMapping
    public ResponseEntity<AssignmentEntity> createAssignment(
            @RequestParam Long personnelId,
            @RequestParam Long inventoryId,
            @RequestParam Long assignedById,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date assignmentDate) {
        AssignmentEntity newAssignment = assignmentService.createAssignment(personnelId, inventoryId, assignedById, assignmentDate);
        if (newAssignment != null) {
            return ResponseEntity.ok(newAssignment);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{assignmentId}/return")
    public ResponseEntity<AssignmentEntity> returnAssignment(
            @PathVariable Long assignmentId,
            @RequestParam Long returnedToId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate) {
        AssignmentEntity updatedAssignment = assignmentService.returnAssignment(assignmentId, returnedToId, returnDate);
        if (updatedAssignment != null) {
            return ResponseEntity.ok(updatedAssignment);
        }
        return ResponseEntity.badRequest().build();
    }
}