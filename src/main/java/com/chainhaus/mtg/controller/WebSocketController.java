package com.chainhaus.mtg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by Asad Sarwar on 08/07/2020.
 */
@Controller
public class WebSocketController {
    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/hello")
    public void sendMessage(Principal principal, String message){
        System.out.println(message);
        this.template.convertAndSendToUser("admin","/queue/reply",  message);
    }
}
