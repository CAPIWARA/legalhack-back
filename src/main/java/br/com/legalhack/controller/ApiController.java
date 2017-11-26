package br.com.legalhack.controller;

import br.com.legalhack.model.Chat;
import br.com.legalhack.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@RestController
public class ApiController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/")
    public LocalDate main() {
        return LocalDate.now();
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public Chat chat(@RequestBody Chat chat) throws NoSuchAlgorithmException {
        System.out.println(chat.toString());
        chatService.responder(chat);
        System.out.println(chat.toString());
        chat.setQuestion(chatService.questao(chat));
        return chat;
    }
}
