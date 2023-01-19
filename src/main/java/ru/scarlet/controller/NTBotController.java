package ru.scarlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.service.NTBotService;

import java.io.IOException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class NTBotController {

    private final NTBotService service;

    @PostMapping("/")
    public ResponseEntity<?> sendMessage(@RequestBody NTBotService.ToDto toDto) throws IOException {
        return service.send(toDto);
    }
}
