package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventDetailDto;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.NewEventDto;
import sit.int221.oasipserver.dtos.UpdateEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.exception.type.ApiNotFoundException;
import sit.int221.oasipserver.exception.type.ApiRequestException;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.repo.EventcategoryRepository;
import sit.int221.oasipserver.utils.ListMapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
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

    public EventDetailDto getById(Integer id) {
        Event event = repository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("Event id " + id + " Does Not Exist !!!")
        );
        return modelMapper.map(event, EventDetailDto.class);
    }

    public EventDto create(NewEventDto newEvent) {

        // validateInput(String email) - if email is valid return true.
        if(newEvent.getBookingName() == null
                || validateInput(newEvent.getBookingName())
                || (newEvent.getEventNotes() != null
                        && validateInput(newEvent.getEventNotes()))
        )
            throw new ApiRequestException("the field that is empty/null.");

        if(newEvent.getBookingName().length() > 100
                || (newEvent.getEventNotes() != null && newEvent.getEventNotes().length() >500)
        )   throw new ApiRequestException("the length exceeded the size.");

        // validateDatetimeFuture(Instant date&time) - if time is future return true.
        if(!validateDatetimeFuture(newEvent.getEventStartTime()))
            throw new ApiRequestException("eventStartTime is NOT in the future.");

        // validateEmail(String email) - if email is valid return true.
        if(!validateEmail(newEvent.getBookingEmail()))
            throw new ApiRequestException("bookingEmail is invalid.");

        Event event = modelMapper.map(newEvent, Event.class);

        // validate overlap
        List<Event> eventList = repository.findAllByEventCategoryIs(event.getEventCategory());
        if(isDateTimeOverlap(event, eventList))
            throw new ApiRequestException("the eventStartTime is overlapped.");

        return modelMapper.map(repository.saveAndFlush(event), EventDto.class);
    }

    public void delete(Integer id) {
        repository.findById(id).orElseThrow(() ->
                new ApiNotFoundException("Event id " + id + " Does Not Exist !!!")
        );
        repository.deleteById(id);
    }

    public Event update(UpdateEventDto updateEventDto, Integer id) {
        // validateInput(String email) - if email is valid return true.

        if(updateEventDto.getEventNotes() != null && updateEventDto.getEventNotes().length() >500)
            throw new ApiRequestException("the length exceeded the size.");

        if(validateInput(updateEventDto.getEventNotes()))
            throw new ApiRequestException("the field that is empty.");

        // validateDatetimeFuture(Instant date&time) - if time is future return true.
        if(!validateDatetimeFuture(updateEventDto.getEventStartTime()))
            throw new ApiRequestException("eventStartTime is NOT in the future.");

        Event event = repository.findById(id).map(e -> mapEvent(e, updateEventDto)).orElseThrow(
                        () -> new ApiNotFoundException("Event id " + id + " Does Not Exist !!!")
        );

        // validate overlap
        List<Event> eventList = repository.findAllByEventCategoryIsAndIdIsNot(event.getEventCategory(), event.getId());
        if(isDateTimeOverlap(event, eventList))
            throw new ApiRequestException("the eventStartTime is overlapped.");

        return repository.saveAndFlush(event);
    }

    private Event mapEvent(Event existingEvent, UpdateEventDto updateEvent) {
        existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        existingEvent.setEventNotes(updateEvent.getEventNotes());
        return existingEvent;
    }

    public Boolean validateDatetimeFuture(Instant eventStartTime){
        return eventStartTime.isAfter(Instant.now());
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
        Instant newEventStart = newEvent.getEventStartTime();
        Instant newEventEND = newEvent.getEventStartTime().plus(newEvent.getEventDuration(), ChronoUnit.MINUTES);
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
            }
        });
    }

    public boolean isDateTimeOverlap(Event newEvent, List<Event> eventList){
        ChronoUnit minutes = ChronoUnit.MINUTES;
        Instant startTime = newEvent.getEventStartTime();
        Instant newEventStart = startTime;
        Instant newEventEnd = startTime.plus(newEvent.getEventDuration(), minutes);
        for (Event e: eventList){
            Instant eEventStart = e.getEventStartTime();
            Instant eEventEnd = e.getEventStartTime().plus(e.getEventDuration(), minutes);

            Boolean isStartBetween = (newEventStart.isAfter(eEventStart)
                    || newEventStart.equals(eEventStart))
                    && newEventStart.isBefore(eEventEnd);
            Boolean isEndBetween = newEventEnd.isAfter(eEventStart)
                    && newEventEnd.isBefore(eEventEnd);

            if (isStartBetween || isEndBetween) return true;
        }
        return false;
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
