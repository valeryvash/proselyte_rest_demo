package myapp.service;

import myapp.model.Event;

import java.util.List;

public interface EventService {

    Event getById(Long id);

    List<Event> getAll();

}
