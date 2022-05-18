package sit.int221.oasipserver.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sit.int221.oasipserver.dtos.EventDetailDto;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.NewEventDto;
import sit.int221.oasipserver.dtos.UpdateEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.exception.ApiException;
import sit.int221.oasipserver.exception.ErrorDetail;
import sit.int221.oasipserver.services.EventService;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired public EventService eventService;
    @Autowired private ModelMapper modelMapper;

    @GetMapping("")
    public List<EventDto> getAllEvent(){
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public EventDetailDto getEventById(@PathVariable Integer id){
        return modelMapper.map(eventService.getById(id), EventDetailDto.class);
    }

    @PostMapping("")
    public EventDto createEvent(@Valid @RequestBody NewEventDto newEvent){
        return eventService.create(newEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventService.delete(id);
    }


    @PutMapping("/{id}")
    public Event updateEvent( @RequestBody UpdateEventDto updateEventDto, @PathVariable Integer id){
        return eventService.update(updateEventDto,id);
    }
}
