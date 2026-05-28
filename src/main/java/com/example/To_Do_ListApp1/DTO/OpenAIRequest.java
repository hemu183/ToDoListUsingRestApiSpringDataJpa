package com.example.To_Do_ListApp1.DTO;

import lombok.Data;

import java.util.List;
@Data
public class OpenAIRequest {

    private String model;
    private List<Message> messages;

    }

