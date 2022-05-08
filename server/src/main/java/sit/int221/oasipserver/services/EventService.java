package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.NewEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.repo.EventcategoryRepository;
import sit.int221.oasipserver.utils.ListMapper;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private EventcategoryRepository eventcategoryRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;
    public List<EventDto> getAll () {
        List<Event> eventList = repository.findAllByOrderByEventStartTimeDesc();
        return listMapper.mapList(eventList, EventDto.class, modelMapper);
    }
    public List<Event> getAll2 () {
        return repository.findAll();
    }
    public EventDto getById (Integer id) {
        Event event = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id "+ id+
                        "Does Not Exist !!!"
                ));
        return modelMapper.map(event, EventDto.class);
    }

    public EventDto create(NewEventDto newEvent){
        Eventcategory eventcategory = eventcategoryRepository.findById(newEvent.getEventCategoryId())
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id "+newEvent.getEventCategoryId()+ " Does Not Exist !!!"
                ));
        newEvent.setEventDuration(eventcategory.getEventDuration());
//        newEvent.setEventCategoryName(eventcategory.getEventCategoryName());
        Event e = modelMapper.map(newEvent, Event.class);
        return modelMapper.map(repository.saveAndFlush(e),EventDto.class);
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
