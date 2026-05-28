package com.example.To_Do_ListApp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Task {
    @Id
    private int id;
    private String title;
    private String description;
    private String status;
    private String aiSuggestion;
    private String priority;
}
