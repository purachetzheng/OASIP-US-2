package sit.int221.oasipserver.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import sit.int221.oasipserver.dtos.*;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;
import sit.int221.oasipserver.exception.type.ApiNotFoundException;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.utils.ListMapper;
import sit.int221.oasipserver.utils.OverlapValidate;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EventService {
    @Autowired private EventRepository repository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;
    @Autowired public EventcategoryService eventcategoryService;
    @Autowired private OverlapValidate overlapValidate;
    final private FieldError overlapErrorObj = new FieldError("newEventDto",
            "eventStartTime", "overlapped with other events");
    public List<EventDto> getAll() {
        List<Event> eventList = repository.findAllByOrderByEventStartTimeDesc();
        return listMapper.mapList(eventList, EventDto.class, modelMapper);
    }

    public Event getById(Integer id) {
        Event event = repository.findById(id).orElseThrow
                (() -> new ApiNotFoundException("Event id " + id + " Does Not Exist !!!"));
        return event;
    }

    public void delete(Integer id) {
        repository.delete(getById(id));
    }

    public EventDto create( CreateEventDto newEvent, BindingResult result) throws MethodArgumentNotValidException{

        if (result.hasErrors()
                && newEvent.getEventCategoryId() == null
                || newEvent.getEventStartTime() == null)
            throw new MethodArgumentNotValidException(null, result);

        Integer categoryId = newEvent.getEventCategoryId();
        Eventcategory category = eventcategoryService.getById(categoryId);
        newEvent.setEventDuration(category.getEventDuration());
        newEvent.setEventCategoryName(category.getEventCategoryName());

        Event event = modelMapper.map(newEvent, Event.class);
//        List<Event> eventList = repository.findAllByEventCategoryIs(event.getEventCategory());
        ChronoUnit minutes = ChronoUnit.MINUTES;
        Integer duration = event.getEventDuration();
        List<Event> eventList = repository.findAllByEventCategoryIsAndEventStartTimeBetween
                (event.getEventCategory(), event.getEventStartTime().minus(duration, minutes),
                        event.getEventStartTime().plus(duration, minutes));
        if(overlapValidate.overlapCheck(event, eventList))
            result.addError(overlapErrorObj);

        if (result.hasErrors()) throw new MethodArgumentNotValidException(null, result);

        return modelMapper.map(repository.saveAndFlush(event), EventDto.class);
    }

    public EventDetailDto update(UpdateEventDto updateEventDto, Integer id, BindingResult result)
            throws MethodArgumentNotValidException{
        Event event = mapEvent(getById(id),updateEventDto);

        Eventcategory eventcategory = event.getEventCategory();
        ChronoUnit minutes = ChronoUnit.MINUTES;
        Integer duration = event.getEventDuration();
//        List<Event> eventList = repository.
//                findAllByEventCategoryIsAndIdIsNot(eventcategory, id);
        List<Event> eventList = repository.
                findAllByEventCategoryIsAndIdIsNotAndEventStartTimeBetween(eventcategory, id,
                        event.getEventStartTime().minus(duration, minutes),
                        event.getEventStartTime().plus(duration, minutes));

        if(overlapValidate.overlapCheck(event, eventList))
            result.addError(overlapErrorObj);

        if (result.hasErrors()) throw new MethodArgumentNotValidException(null, result);

        return modelMapper.map(repository.saveAndFlush(event),EventDetailDto.class);
    }

    private Event mapEvent(Event existingEvent, UpdateEventDto updateEvent) {
        if(updateEvent.getEventStartTime() != null)
            existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        if(updateEvent.getEventNotes() != null)
            existingEvent.setEventNotes(updateEvent.getEventNotes());
        return existingEvent;
    }

}
