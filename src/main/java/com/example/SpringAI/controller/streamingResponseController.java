package com.example.SpringAI.controller;

import com.example.SpringAI.Imp.StreamResponserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/stream")
public class streamingResponseController {


    // Structured Used

    private StreamResponserService streamResponserService;

    public streamingResponseController(
            StreamResponserService chatService
    ) {
        this.streamResponserService = chatService;
    }


    ///  Mappings


   @GetMapping("/stream-chat")
    public ResponseEntity<Flux<String>> streamChat(@RequestParam("q") String query){


       return ResponseEntity.ok(streamResponserService.chatStream(query));

   }

}
