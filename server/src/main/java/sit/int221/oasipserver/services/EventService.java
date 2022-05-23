package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import sit.int221.oasipserver.dtos.*;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.exception.type.ApiNotFoundException;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.repo.EventcategoryRepository;
import sit.int221.oasipserver.utils.ListMapper;
import sit.int221.oasipserver.utils.OverlapValidate;

import java.util.List;


@Service
public class EventService {
    @Autowired private EventRepository repository;
    @Autowired private EventcategoryRepository eventcategoryRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;
    @Autowired public EventcategoryService eventcategoryService;
    @Autowired private OverlapValidate overlapValidate;
    public List<EventDto> getAll() {
        List<Event> eventList = repository.findAllByOrderByEventStartTimeDesc();
        return listMapper.mapList(eventList, EventDto.class, modelMapper);
    }

    public Event getById(Integer id) {
        Event event = repository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("Event id " + id + " Does Not Exist !!!")
        );
        return event;
    }


    public EventDto create( CreateEventDto newEvent, BindingResult result) throws MethodArgumentNotValidException{

        if (result.hasErrors() && newEvent.getEventCategoryId() == null || newEvent.getEventStartTime() == null)
            throw new MethodArgumentNotValidException(null, result);

        List<Event> eventList = repository.findAllByEventCategoryId(newEvent.getEventCategoryId());

        Integer eDuration = eventcategoryService.getById(newEvent.getEventCategoryId()).getEventDuration();
        newEvent.setEventDuration(eDuration);
        Event event = modelMapper.map(newEvent, Event.class);

        if(overlapValidate.overlapCheck(event, eventList))
            result.addError(new FieldError(
                    "newEventDto","eventStartTime",
                    "overlapped with other events"));

        if (result.hasErrors())
            throw new MethodArgumentNotValidException(null, result);

        return modelMapper.map(repository.saveAndFlush(event), EventDto.class);
    }


    public void delete(Integer id) {
        repository.findById(id).orElseThrow(() ->
                new ApiNotFoundException("Event id " + id + " Does Not Exist !!!")
        );
        repository.deleteById(id);
    }

    public EventDetailDto update(UpdateEventDto updateEventDto, Integer id, BindingResult result)
            throws MethodArgumentNotValidException{

        Event event = mapEvent(getById(id),updateEventDto);

        List<Event> eventList = repository.findAllByEventCategoryIsAndIdIsNot(event.getEventCategory(), event.getId());
        if(overlapValidate.overlapCheck(event, eventList))
            result.addError(new FieldError(
                    "newEventDto","eventStartTime",
                    "overlapped with other events"));

        if (result.hasErrors())
            throw new MethodArgumentNotValidException(null, result);

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
