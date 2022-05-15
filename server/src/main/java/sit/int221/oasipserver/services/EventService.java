package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventDetailDto;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.NewEventDto;
import sit.int221.oasipserver.dtos.UpdateEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.repo.EventcategoryRepository;
import sit.int221.oasipserver.utils.ListMapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public EventDetailDto getById(Integer id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + id +
                        "Does Not Exist !!!"
                ));
        return modelMapper.map(event, EventDetailDto.class);
    }

    public EventDto create(NewEventDto newEvent) {
//        if(validateDatetimeFuture(newEvent.getEventStartTime()))
//            throw new ResponseStatusException();
        validateDatetimeFutureThrow(newEvent.getEventStartTime());
        if(!validateEmail(newEvent.getBookingEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bookingEmail is invalid.");
        if(validateInput(newEvent.getBookingName()) || newEvent.getBookingName() == null )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the field that is empty/null.");
        Event event = modelMapper.map(newEvent, Event.class);
//        Boolean isOverlap = validateOverlap(event);
//        if(isOverlap) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the eventStartTime is overlapped.");
        List<Event> eventList = repository.findAllByEventCategoryIs(event.getEventCategory());
        validateOverlap(event, eventList);
        return modelMapper.map(repository.saveAndFlush(event), EventDto.class);
    }

    public void delete(Integer id) {
        repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + " does not exist !!!"));
        repository.deleteById(id);
    }

    public Event update(UpdateEventDto updateEventDto, Integer id) {
//        Event updateEvent = modelMapper.map(updateEventDto, Event.class);
        validateDatetimeFutureThrow(updateEventDto.getEventStartTime());
        Event event = repository.findById(id).map(e -> mapEvent(e, updateEventDto))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + id +
                        "Does Not Exist !!!"
                ));
//        Event event = repository.findById(id).orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "Event id " + id +
//                        "Does Not Exist !!!"
//                ));
//        Event event2 = mapEvent2(new Event(), event, updateEventDto);
        List<Event> eventList = repository.findAllByEventCategoryIsAndIdIsNot(event.getEventCategory(), event.getId());
        validateOverlap(event, eventList);
        return repository.saveAndFlush(event);
    }

    private Event mapEvent(Event existingEvent, UpdateEventDto updateEvent) {
        existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        existingEvent.setEventNotes(updateEvent.getEventNotes());
        return existingEvent;
    }

    private Event mapEvent2(Event existingEvent, Event updateEvent, UpdateEventDto updateEventDto) {
        existingEvent.setId(updateEvent.getId());
        existingEvent.setBookingName(updateEvent.getBookingName());
        existingEvent.setBookingEmail(updateEvent.getBookingEmail());
        existingEvent.setEventStartTime(updateEventDto.getEventStartTime());
        existingEvent.setEventDuration(updateEvent.getEventDuration());
        existingEvent.setEventNotes(updateEventDto.getEventNotes());
        existingEvent.setEventCategory(updateEvent.getEventCategory());

        return existingEvent;
    }

    public Boolean validateDatetimeFuture(Instant eventStartTime){
        return eventStartTime.isAfter(Instant.now());
    }
    public void validateDatetimeFutureThrow(Instant eventStartTime){
        if(!eventStartTime.isAfter(Instant.now()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "eventStartTime is NOT in the future.");
    }

    public void validateDuration() {
//        Eventcategory eventcategory = eventcategoryRepository.findById(newEvent.getEventCategoryId())
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "Event id " + newEvent.getEventCategoryId() + " Does Not Exist !!!"
//                ));
//        newEvent.setEventDuration(eventcategory.getEventDuration());
//        newEvent.setEventCategoryName(eventcategory.getEventCategoryName());
    }
    public void validateOverlap(Event newEvent, List<Event> eventList){
//        List<Event> eventList = repository.findAllByEventCategoryIs(newEvent.getEventCategory());
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

    //https://stackoverflow.com/questions/8204680/java-regex-email
    public boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    public boolean validateInput(String input){
        Pattern pattern = Pattern.compile("^\\s*$",
                Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}
