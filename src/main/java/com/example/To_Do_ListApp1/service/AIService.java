package com.example.To_Do_ListApp1.service;

import com.example.To_Do_ListApp1.APIResponse.AIResponse;
import com.example.To_Do_ListApp1.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AIService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String apiKey;

    public AIResponse getAIResponse(String title) {

        try {
            String url = "https://api.openai.com/v1/chat/completions";

            String prompt =
                    "For the task: " + title +
                            ", give a short productivity suggestion and priority." +
                            " Format response like this: " +
                            "Suggestion: <text>, Priority: <HIGH/MEDIUM/LOW>";

            Message message =
                    new Message("user", prompt);

            OpenAIRequest request =
                    new OpenAIRequest();

            request.setModel("gpt-4o-mini");

            request.setMessages(List.of(message));

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.setBearerAuth(apiKey);

            HttpEntity<OpenAIRequest> entity =
                    new HttpEntity<>(request, headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            String responseBody = response.getBody();

            // TEMPORARY manual values
            String suggestion =
                    "Practice daily";

            String priority =
                    "HIGH";

            return new AIResponse(
                    suggestion,
                    priority
            );
        } catch (Exception e){

            System.out.println(e.getMessage());

            return new AIResponse(
                    "AI unavailable",
                    "LOW"
            );
        }
    }


}