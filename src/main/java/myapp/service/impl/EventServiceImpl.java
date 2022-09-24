package myapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import myapp.model.Event;
import myapp.repository.EventRepository;
import myapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private EventRepository eventRepo;

    @Autowired
    public EventServiceImpl(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public Event getById(Long id) {
        log.info("IN EventRepositoryImpl getById {}", id );
        return eventRepo.findById(id).orElse(null);
    }

    @Override
    public List<Event> getAll() {
        log.info("IN EventServiceImpl getAll {} ");
        return eventRepo.findAll();
    }
}
