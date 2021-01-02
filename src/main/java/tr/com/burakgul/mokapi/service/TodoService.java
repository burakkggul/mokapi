package tr.com.burakgul.mokapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tr.com.burakgul.mokapi.repository.TodoRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    Logger logger = LoggerFactory.getLogger(TodoService.class);

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
}
