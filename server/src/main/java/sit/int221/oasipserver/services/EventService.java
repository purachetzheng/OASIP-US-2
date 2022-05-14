package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.NewEventDto;
import sit.int221.oasipserver.dtos.UpdateEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.repo.EventcategoryRepository;
import sit.int221.oasipserver.utils.ListMapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private EventcategoryRepository eventcategoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    public List<EventDto> getAll() {
        List<Event> eventList = repository.findAllByOrderByEventStartTimeDesc();
        return listMapper.mapList(eventList, EventDto.class, modelMapper);
    }

    public List<Event> getAll2() {
        return repository.findAll();
    }

    public EventDto getById(Integer id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + id +
                        "Does Not Exist !!!"
                ));
        return modelMapper.map(event, EventDto.class);
    }

    public EventDto create(NewEventDto newEvent) {
        Event e = modelMapper.map(newEvent, Event.class);
//        Boolean isOverlap = checkOverlap(e);
        checkOverlap(e);
//        if(isOverlap) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the eventStartTime is overlapped.");
        return modelMapper.map(repository.saveAndFlush(e), EventDto.class);
    }

    public void delete(Integer id) {
        repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + " does not exist !!!"));
        repository.deleteById(id);
    }

    public Event update(UpdateEventDto updateEventDto, Integer id) {
//        Event updateEvent = modelMapper.map(updateEventDto, Event.class);
        Event event = repository.findById(id).map(e -> mapEvent(e, updateEventDto))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + id +
                        "Does Not Exist !!!"
                ));
        return repository.saveAndFlush(event);
    }

    private Event mapEvent(Event existingEvent, UpdateEventDto updateEvent) {
        existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        existingEvent.setEventNotes(updateEvent.getEventNotes());
        return existingEvent;
    }

    public void checkDuration() {
//        Eventcategory eventcategory = eventcategoryRepository.findById(newEvent.getEventCategoryId())
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "Event id " + newEvent.getEventCategoryId() + " Does Not Exist !!!"
//                ));
//        newEvent.setEventDuration(eventcategory.getEventDuration());
//        newEvent.setEventCategoryName(eventcategory.getEventCategoryName());
    }
    public void checkOverlap(Event newEvent){
        List<Event> eventList = repository.findAllByEventCategoryIs(newEvent.getEventCategory());
        Instant newEventStart = newEvent.getEventStartTime();
        Instant newEventEND = newEvent.getEventStartTime().plus(newEvent.getEventDuration(), ChronoUnit.MINUTES);
//        AtomicReference<Boolean> isOverLap = new AtomicReference<>(false);
        eventList.forEach(e -> {
            Instant eachEventStart = e.getEventStartTime();
            Instant eachEventEnd = e.getEventStartTime().plus(e.getEventDuration(), ChronoUnit.MINUTES);

            Boolean isStartBetween = (newEventStart.isAfter(eachEventStart)
                    || newEventStart.equals(eachEventStart))
                    && newEventStart.isBefore(eachEventEnd);
            Boolean isEndBetween = newEventEND.isAfter(eachEventStart)
                    && newEventEND.isBefore(eachEventEnd);

            if (isStartBetween || isEndBetween){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the eventStartTime is overlapped.");
//                isOverLap.set(true);
            }
        });
//        return isOverLap.get();
    }

    //    public Event update(EventDto updateEventDto, Integer id) {
//        Event updateEvent = modelMapper.map(updateEventDto, Event.class);
//        Event event = repository.findById(id).map(e -> mapEvent(e, updateEvent))
//                .orElseGet(() ->
//                {
//                    updateEvent.setId(id);
//                    return updateEvent;
//                });
//        return repository.saveAndFlush(event);
//    }
//    private Event mapEvent(Event existingEvent, Event updateEvent) {
//        existingEvent.setBookingName(updateEvent.getBookingName());
//        existingEvent.setBookingEmail(updateEvent.getBookingEmail());
//        existingEvent.setEventStartTime(updateEvent.getEventStartTime());
//        existingEvent.setEventDuration(updateEvent.getEventDuration());
//        existingEvent.setEventNotes(updateEvent.getEventNotes());
//        existingEvent.setEventCategory(updateEvent.getEventCategory());
//        return existingEvent;
//    }

}
