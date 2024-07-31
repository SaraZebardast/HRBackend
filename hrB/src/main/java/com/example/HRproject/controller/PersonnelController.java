package com.example.HRproject.controller;

import com.example.HRproject.entity.PersonnelEntity;
import com.example.HRproject.repository.PersonnelRepository;
import com.example.HRproject.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    @GetMapping
    public List<PersonnelEntity> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonnelEntity> getPersonnelById(@PathVariable Long id) {
        Optional<PersonnelEntity> personnel = personnelService.getPersonnelById(id);
        return personnel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PersonnelEntity createPersonnel(@RequestBody PersonnelEntity personnelEntity) {
        return personnelService.createPersonnel(personnelEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonnelEntity> updatePersonnel(@PathVariable Long id, @RequestBody PersonnelEntity updatedPersonnel) {
        Optional<PersonnelEntity> updatedEntity = personnelService.updatePersonnel(id, updatedPersonnel);
        return updatedEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        personnelService.deletePersonnel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<PersonnelEntity> searchPersonnel(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String tckn,
            @RequestParam(required = false) String department) {
        return personnelService.searchPersonnel(
                Objects.requireNonNullElse(firstName, ""),
                Objects.requireNonNullElse(lastName, ""),
                Objects.requireNonNullElse(tckn, ""),
                Objects.requireNonNullElse(department, "")
        );
    }

    @GetMapping("/check-tckn/{tckn}")
    public ResponseEntity<Map<String, Boolean>> checkTcknUniqueness(
            @PathVariable long tckn,
            @RequestParam(required = false) Long id) {
        boolean isUnique = personnelService.isTcknUnique(tckn, id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isUnique", isUnique);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/terminate")
    public ResponseEntity<PersonnelEntity> terminatePersonnel(
            @PathVariable Long id,
            @RequestBody TerminationRequest terminationRequest) {
        Optional<PersonnelEntity> updatedEntity = personnelService.terminatePersonnel(id, terminationRequest);
        return updatedEntity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // termination request body
    public static class TerminationRequest {
        private Date terminationDate;
        private String terminationReason;

        // Getters and setters
        public Date getTerminationDate() {
            return terminationDate;
        }

        public void setTerminationDate(Date terminationDate) {
            this.terminationDate = terminationDate;
        }

        public String getTerminationReason() {
            return terminationReason;
        }

        public void setTerminationReason(String terminationReason) {
            this.terminationReason = terminationReason;
        }
    }
}