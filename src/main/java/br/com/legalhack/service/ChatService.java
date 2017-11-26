package br.com.legalhack.service;

import br.com.legalhack.model.Chat;
import br.com.legalhack.model.State;
import br.com.legalhack.repository.ChatRepository;
import br.com.legalhack.repository.StateRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.legalhack.Constant.*;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private StateRepository stateRepository;

    public void responder(Chat chat) throws NoSuchAlgorithmException {
        if (chat.getState() != null) stateRepository.save(chat.getState());
        chatRepository.save(chat);
        if (chat.getHash() == null || chat.getHash().equals("")) {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            chat.setHash(String.valueOf(messageDigest.digest(chat.getId().toString().getBytes())));
            State state = new State();
            state.setHash(chat.getHash());
            chat.setState(state);
        }
    }

    public String questao(Chat chat) {
        if (chat.getAnswer().toLowerCase().contains(USR_DIVORCIAR)) {
            return CHAT_NOME;
        }

        if (chat.getAnswer().toLowerCase().contains(USR_CASAR)) {
            return CHAT_PATRIMONIO_COMUM;
        }
        if (chat.getQuestion().contains(CHAT_NOME)) {
            chat.getState().setNome(chat.getAnswer());
            return chat.getAnswer().concat(CHAT_CONSCIENTIZAR).replace("\n", "");
        }
        if (chat.getAnswer().toLowerCase().contains(USR_OK)) {
            return CHAT_FILHOS;
        }
        if (chat.getAnswer().toLowerCase().contains(USR_TENHO)) {
            return CHAT_CEP;
        }
        if (validarCep(chat.getAnswer())) {
            return CHAT_ACEITACAO;
        }


        System.out.println("NAO ACHEI A RESPOSTA");
        return "NAO ENCONTREI SUA REPOSTA";
    }

    private boolean validarCep(String end) {
        //\d{5}-\d{3}
        String validPattern = "\\d{5}-\\d{3}";
        Pattern pattern = Pattern.compile(validPattern);
        Matcher matcher = pattern.matcher(end);
        return matcher.find();
    }
}