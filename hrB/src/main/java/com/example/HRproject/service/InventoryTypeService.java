package com.example.HRproject.service;

import com.example.HRproject.entity.InventoryTypeEntity;
import com.example.HRproject.repository.InventoryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryTypeService {

    @Autowired
    private InventoryTypeRepository inventoryTypeRepository;

    public List<InventoryTypeEntity> getAllInventoryTypes() {
        return inventoryTypeRepository.findAll();
    }

    public Optional<InventoryTypeEntity> getInventoryTypeById(Long id) {
        return inventoryTypeRepository.findById(id);
    }

    public InventoryTypeEntity createInventoryType(InventoryTypeEntity inventoryType) {
        return inventoryTypeRepository.save(inventoryType);
    }

    public Optional<InventoryTypeEntity> updateInventoryType(Long id, InventoryTypeEntity inventoryType) {
        return inventoryTypeRepository.findById(id)
                .map(existingType -> {
                    existingType.setName(inventoryType.getName());
                    return inventoryTypeRepository.save(existingType);
                });
    }

    public boolean deleteInventoryType(Long id) {
        return inventoryTypeRepository.findById(id)
                .map(existingType -> {
                    inventoryTypeRepository.delete(existingType);
                    return true;
                })
                .orElse(false);
    }
}