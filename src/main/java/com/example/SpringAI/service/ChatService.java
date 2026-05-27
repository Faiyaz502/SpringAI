package com.example.SpringAI.service;

import com.example.SpringAI.Entity.customResponse;

import java.util.List;

public interface ChatService {

    String chat(String query);

    customResponse chatCustomResponse(String query);

    List<customResponse> chatCustomResponseList(String query);


    String chatClientModelSpecific(String query);
}
