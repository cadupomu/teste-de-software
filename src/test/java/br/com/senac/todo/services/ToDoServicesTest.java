package br.com.senac.todo.services;

import br.com.senac.todo.model.ToDo;
import br.com.senac.todo.repositories.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoServicesTest {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDoServicesTest(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @AfterEach
    void tearDown() {
        toDoRepository.deleteAll();
    }

    @Test
    void findAll() {
        var toDo = new ToDo("Dormir 8 horas por dia", true);
        toDoRepository.save(toDo);

        var toDoService = new ToDoServices(toDoRepository);
        List<ToDo> toDoList = toDoService.findAll();
        var lastToDo = toDoList.get(toDoList.size() - 1);

        assertEquals(toDo.getDescription(), lastToDo.getDescription());
        assertEquals(toDo.isCompleted(), lastToDo.isCompleted());
        assertEquals(toDo.getId(), lastToDo.getId());
    }

    @Test
    void SaveAToDo() {
        var toDoServices = new ToDoServices(toDoRepository);
        var todo = new ToDo("FixDI on TodoServiceTest", true);

        toDoServices.save(todo);

        assertEquals(3,toDoRepository.count());
    }
}