package sit.int221.oasipserver.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public List<EventDto> getAllEvent(){
        return eventService.getAll();
    }
    @GetMapping("{id}")
    public EventDto getEventById(@PathVariable Integer id){
        return eventService.getById(id);
    }
    @PostMapping("")
    public Event createEvent(@RequestBody EventDto newEvnet){
        return eventService.create(newEvnet);
    }
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventService.delete(id);
    }
    @PutMapping("{id}")
    public Event updateEvent(@RequestBody EventDto updateEventDto, @PathVariable Integer id){
        return eventService.update(updateEventDto,id);
    }
}
