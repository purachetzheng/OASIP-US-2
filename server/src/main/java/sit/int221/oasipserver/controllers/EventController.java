package sit.int221.oasipserver.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.NewEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.repo.EventcategoryRepository;
import sit.int221.oasipserver.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    public EventService eventService;
    @Autowired private ModelMapper modelMapper;
    @Autowired private EventRepository eventRepository;
    @Autowired private EventcategoryRepository eventcategoryRepository;
    @GetMapping("")
    public List<EventDto> getAllEvent(){
        return eventService.getAll();
    }
    @GetMapping("/test")
    public List<Event> getAllEvent2(){
        return eventService.getAll2();
    }
    @GetMapping("{id}")
    public EventDto getEventById(@PathVariable Integer id){
        return eventService.getById(id);
    }

    @PostMapping("")
    public EventDto createEvent(@RequestBody NewEventDto newEvent){
        return eventService.create(newEvent);
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
