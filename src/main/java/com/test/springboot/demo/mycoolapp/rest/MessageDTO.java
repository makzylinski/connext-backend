package com.test.springboot.demo.mycoolapp.rest;

import java.time.LocalDateTime;

public class MessageDTO {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private UserDTO sender;
    private UserDTO recipient;


}
