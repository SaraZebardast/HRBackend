package com.example.HRproject.service;

import com.example.HRproject.entity.PersonnelEntity;
import com.example.HRproject.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.HRproject.controller.PersonnelController.TerminationRequest;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class PersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;

    public List<PersonnelEntity> getAllPersonnel() {
        return personnelRepository.findAll();
    }

    public Optional<PersonnelEntity> getPersonnelById(Long id) {
        return personnelRepository.findById(id);
    }

    public PersonnelEntity createPersonnel(PersonnelEntity personnelEntity) {
        if (personnelRepository.existsByTckn(personnelEntity.getTckn())) {
            throw new RuntimeException("TCKN must be unique");
        }
        personnelEntity.setHireDate(new Date()); // Set hire date to today
        personnelEntity.setTerminationDate(null);
        personnelEntity.setTerminationReason(null);
        return personnelRepository.save(personnelEntity);
    }

    public Optional<PersonnelEntity> updatePersonnel(Long id, PersonnelEntity updatedPersonnel) {
        return personnelRepository.findById(id).map(personnel -> {
            personnel.setFirstName(updatedPersonnel.getFirstName());
            personnel.setLastName(updatedPersonnel.getLastName());
            personnel.setGender(updatedPersonnel.getGender());
            personnel.setBirthDate(updatedPersonnel.getBirthDate());
            personnel.setMaritalStatus(updatedPersonnel.getMaritalStatus());
            personnel.setEducationStatus(updatedPersonnel.getEducationStatus());
            personnel.setDepartment(updatedPersonnel.getDepartment());
            personnel.setPosition(updatedPersonnel.getPosition());
            personnel.setActive(updatedPersonnel.isActive());
            personnel.setTckn(updatedPersonnel.getTckn());
            personnel.setHireDate(updatedPersonnel.getHireDate());
            personnel.setTerminationDate(updatedPersonnel.getTerminationDate());
            personnel.setTerminationReason(updatedPersonnel.getTerminationReason());
            return personnelRepository.save(personnel);
        });
    }

    public void deletePersonnel(Long id) {
        personnelRepository.deleteById(id);
    }

    public List<PersonnelEntity> searchPersonnel(String firstName, String lastName, String tckn, String department) {
        return personnelRepository.findByMultipleFields(firstName, lastName, tckn, department);
    }

    public boolean isTcknUnique(long tckn) {
        return !personnelRepository.existsByTckn(tckn);
    }

    public boolean isTcknUnique(long tckn, Long id) {
        if (id == null) {
            return isTcknUnique(tckn);
        } else {
            return !personnelRepository.existsByTcknAndIdNot(tckn, id);
        }
    }

    public Optional<PersonnelEntity> terminatePersonnel(Long id, TerminationRequest terminationRequest) {
        return personnelRepository.findById(id).map(personnel -> {
            personnel.setTerminationDate(terminationRequest.getTerminationDate());
            personnel.setTerminationReason(terminationRequest.getTerminationReason());
            personnel.setActive(false);
            return personnelRepository.save(personnel);
        });
    }
}