package com.example.To_Do_ListApp1.DTO;

import com.example.To_Do_ListApp1.APIResponse.WeatherResponse;
import com.example.To_Do_ListApp1.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class DashboardResponse {

    private List<Task> tasks;

    private WeatherResponse weather;
}
