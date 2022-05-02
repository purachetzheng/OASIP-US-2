package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.EventcategoryDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.utils.ListMapper;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;
    public List<EventDto> getAll () {
        List<Event> eventList = repository.findAllByOrderByEventStartTimeDesc();
        return listMapper.mapList(eventList, EventDto.class, modelMapper);
    }

    public EventDto getById (Integer id) {
        Event event = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id "+ id+
                        "Does Not Exist !!!"
                ));
        return modelMapper.map(event, EventDto.class);
    }

    public Event create(EventDto newEvent){
        Event event = modelMapper.map(newEvent, Event.class);
        return repository.saveAndFlush(event);
    }

    public void delete(Integer id){
        repository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + " does not exist !!!"));
        repository.deleteById(id);
    }

    public Event update(EventDto updateEventDto, Integer id) {
        Event updateEvent = modelMapper.map(updateEventDto, Event.class);
        Event event = repository.findById(id).map(e -> mapEvent(e, updateEvent))
                .orElseGet(() ->
                {
                    updateEvent.setId(id);
                    return updateEvent;
                });
        return repository.saveAndFlush(event);
    }

    private Event mapEvent(Event existingEvent, Event updateEvent) {
        existingEvent.setBookingName(updateEvent.getBookingName());
        existingEvent.setBookingEmail(updateEvent.getBookingEmail());
        existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        existingEvent.setEventDuration(updateEvent.getEventDuration());
        existingEvent.setEventNotes(updateEvent.getEventNotes());
        existingEvent.setEventCategory(updateEvent.getEventCategory());
        return existingEvent;
    }
}
