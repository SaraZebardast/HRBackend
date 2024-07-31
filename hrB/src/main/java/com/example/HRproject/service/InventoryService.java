package com.example.HRproject.service;

import com.example.HRproject.entity.InventoryEntity;
import com.example.HRproject.entity.InventoryTypeEntity;
import com.example.HRproject.repository.InventoryRepository;
import com.example.HRproject.repository.InventoryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryTypeRepository inventoryTypeRepository;

    public List<InventoryEntity> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<InventoryEntity> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public InventoryEntity createInventory(InventoryEntity inventoryEntity, Long typeId) {
        InventoryTypeEntity type = inventoryTypeRepository.findById(typeId)
                .orElseThrow(() -> new EntityNotFoundException("Inventory Type not found"));
        inventoryEntity.setType(type);
        return inventoryRepository.save(inventoryEntity);
    }

    public Optional<InventoryEntity> updateInventory(Long id, InventoryEntity updatedInventory, Long typeId) {
        return inventoryRepository.findById(id)
                .map(existingInventory -> {
                    existingInventory.setBrand(updatedInventory.getBrand());
                    existingInventory.setModel(updatedInventory.getModel());
                    existingInventory.setSerialNumber(updatedInventory.getSerialNumber());
                    existingInventory.setEntryDate(updatedInventory.getEntryDate());
                    existingInventory.setAssignmentType(updatedInventory.getAssignmentType());

                    if (typeId != null) {
                        InventoryTypeEntity type = inventoryTypeRepository.findById(typeId)
                                .orElseThrow(() -> new EntityNotFoundException("Inventory Type not found"));
                        existingInventory.setType(type);
                    }

                    return inventoryRepository.save(existingInventory);
                });
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public List<InventoryEntity> searchInventory(String assignmentType) {
        return inventoryRepository.findByAssignmentType(assignmentType);
    }

    public Optional<InventoryEntity> updateAssignmentType(Long id, String assignmentType) {
        return inventoryRepository.findById(id).map(inventory -> {
            inventory.setAssignmentType(assignmentType);
            return inventoryRepository.save(inventory);
        });
    }
}