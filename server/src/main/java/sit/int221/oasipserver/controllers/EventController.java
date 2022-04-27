package sit.int221.oasipserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    public EventService eventService;

    @GetMapping("")
    public List<EventDto> getAllEmployee(){
        return eventService.getAll();
    }
    @GetMapping("{id}")
    public EventDto getEventById(@PathVariable Integer id){
        return eventService.getById(id);
    }

}
