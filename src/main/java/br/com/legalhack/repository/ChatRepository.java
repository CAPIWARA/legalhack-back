package br.com.legalhack.repository;

import br.com.legalhack.model.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {

}
