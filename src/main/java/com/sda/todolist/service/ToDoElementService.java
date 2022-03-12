package com.sda.todolist.service;

import com.sda.todolist.domain.ToDoElement;
import com.sda.todolist.domain.projection.UserToDoProjection;
import com.sda.todolist.domain.request.ToDoRequest;
import com.sda.todolist.domain.response.ToDoResponse;
import com.sda.todolist.repository.ToDoElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoElementService {

    @Autowired
    private ToDoElementRepository toDoElementRepository;

    public List<ToDoResponse> createNewToDoElement(int userId, ToDoRequest request) {
        ToDoElement element = new ToDoElement(request.getDescription(), false, userId);
        toDoElementRepository.save(element);

        return fromVOs(toDoElementRepository.findAllActiveByUserId(userId));
    }

    public List<ToDoResponse> getAll(int userId) {
        return fromVOs(toDoElementRepository.findAllActiveByUserId(userId));
    }

    public void markAsComplete(int id) {
        Optional<ToDoElement> element = toDoElementRepository.findById(id);

        if (element.isEmpty()) {
            throw new EntityNotFoundException();
        }

        ToDoElement elementToBeUpdated = element.get();
        elementToBeUpdated.setCompleted(true);

        toDoElementRepository.save(elementToBeUpdated);
    }

//    private List<ToDoResponse> fromProjections(List<UserToDoProjection> elements) {
//        List<ToDoResponse> response = new ArrayList<>();
//        for (UserToDoProjection element : elements) {
//            response.add(fromVO(element));
//        }
//
//        return response;
//    }

    private List<ToDoResponse> fromVOs(List<ToDoElement> elements) {
        List<ToDoResponse> response = new ArrayList<>();
        for (ToDoElement element : elements) {
            response.add(fromVO(element));
        }

        return response;
    }

//    private ToDoResponse fromVO(UserToDoProjection element) {
//        return new ToDoResponse(element.getId(), element.getDescription(), element.isCompleted(), element.getUserId());
//    }

    private ToDoResponse fromVO(ToDoElement element) {
        return new ToDoResponse(element.getId(), element.getDescription(), element.isCompleted(), element.getUserId());
    }
}
