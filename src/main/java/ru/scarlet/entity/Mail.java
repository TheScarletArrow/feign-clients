package ru.scarlet.entity;

import lombok.Data;

import java.util.List;

@Data
public class Mail {
    private String to;
    private String subject;
    private String body;
    private String from;

    private List<String> attachments;
}
