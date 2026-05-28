package com.example.To_Do_ListApp1.APIResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIResponse {

  private String suggestion;
  private String priority;

    }

