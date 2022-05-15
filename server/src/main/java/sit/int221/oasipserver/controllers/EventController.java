package sit.int221.oasipserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasipserver.dtos.EventDetailDto;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.NewEventDto;
import sit.int221.oasipserver.dtos.UpdateEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.exception.type.ApiNotFoundException;
import sit.int221.oasipserver.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired public EventService eventService;

    @GetMapping("")
    public List<EventDto> getAllEvent(){
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public EventDetailDto getEventById(@PathVariable Integer id){
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

    @PutMapping("/{id}")
    public Event updateEvent(@RequestBody UpdateEventDto updateEventDto, @PathVariable Integer id){
        return eventService.update(updateEventDto,id);
    }

}
