package com.example.HRproject.controller;

import com.example.HRproject.entity.InventoryEntity;
import com.example.HRproject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryEntity> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryEntity> getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventoryEntity> createInventory(
            @RequestBody InventoryEntity inventoryEntity,
            @RequestParam Long typeId) {
        InventoryEntity createdInventory = inventoryService.createInventory(inventoryEntity, typeId);
        return ResponseEntity.ok(createdInventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryEntity> updateInventory(
            @PathVariable Long id,
            @RequestBody InventoryEntity inventoryEntity,
            @RequestParam(required = false) Long typeId) {
        return inventoryService.updateInventory(id, inventoryEntity, typeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<InventoryEntity> searchInventory(@RequestParam(required = false) String assignmentType) {
        if (assignmentType == null) {
            return inventoryService.getAllInventory(); // Optionally handle without assignmentType filter
        } else {
            return inventoryService.searchInventory(assignmentType);
        }
    }

    @PatchMapping("/{id}/assignment-type")
    public ResponseEntity<InventoryEntity> updateAssignmentType(@PathVariable Long id, @RequestParam String assignmentType) {
        Optional<InventoryEntity> updatedEntity = inventoryService.updateAssignmentType(id, assignmentType);
        return updatedEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}