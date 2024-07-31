package com.example.HRproject.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "inventory")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private InventoryTypeEntity type;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "entry_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date entryDate;

    @Column(name = "assignment_type")
    private String assignmentType;

    // Default constructor
    public InventoryEntity() {
    }

    // Parameterized constructor
    public InventoryEntity(InventoryTypeEntity type, String brand, String model, String serialNumber, Date entryDate, String assignmentType) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.entryDate = entryDate;
        this.assignmentType = assignmentType;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InventoryTypeEntity getType() {
        return type;
    }

    public void setType(InventoryTypeEntity type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    // toString method for easy logging and debugging
    @Override
    public String toString() {
        return "InventoryEntity{" +
                "id=" + id +
                ", type=" + (type != null ? type.getName() : "null") +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", entryDate=" + entryDate +
                ", assignmentType='" + assignmentType + '\'' +
                '}';
    }
}