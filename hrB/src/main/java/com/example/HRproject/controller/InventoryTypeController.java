package com.example.HRproject.controller;

import com.example.HRproject.entity.InventoryTypeEntity;
import com.example.HRproject.service.InventoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-types")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryTypeController {

    @Autowired
    private InventoryTypeService inventoryTypeService;

    // Gets all inventory types
    @GetMapping
    public ResponseEntity<List<InventoryTypeEntity>> getAllInventoryTypes() {
        List<InventoryTypeEntity> types = inventoryTypeService.getAllInventoryTypes();
        return ResponseEntity.ok(types);
    }

    // Gets a single inventory type by ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryTypeEntity> getInventoryTypeById(@PathVariable Long id) {
        return inventoryTypeService.getInventoryTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Adds a new inventory type
    @PostMapping
    public ResponseEntity<InventoryTypeEntity> addInventoryType(@RequestBody InventoryTypeEntity inventoryType) {
        InventoryTypeEntity newType = inventoryTypeService.createInventoryType(inventoryType);
        return ResponseEntity.status(HttpStatus.CREATED).body(newType);
    }

    // Updates an existing inventory type
    @PutMapping("/{id}")
    public ResponseEntity<InventoryTypeEntity> updateInventoryType(@PathVariable Long id, @RequestBody InventoryTypeEntity inventoryType) {
        return inventoryTypeService.updateInventoryType(id, inventoryType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletes an inventory type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryType(@PathVariable Long id) {
        boolean deleted = inventoryTypeService.deleteInventoryType(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}