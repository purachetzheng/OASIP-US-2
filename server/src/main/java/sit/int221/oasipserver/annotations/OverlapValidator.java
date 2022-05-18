package sit.int221.oasipserver.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventDto;
import sit.int221.oasipserver.dtos.NewEventDto;
import sit.int221.oasipserver.dtos.UpdateEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;
import sit.int221.oasipserver.repo.EventRepository;
import sit.int221.oasipserver.repo.EventcategoryRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class OverlapValidator implements ConstraintValidator<OverlapValidation, NewEventDto> {
    @Autowired private EventRepository eventRepo;
    @Autowired private EventcategoryRepository eventcategoryRepo;
    private boolean hasCategoryId;

    @Override
    public void initialize(OverlapValidation constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        hasCategoryId = constraintAnnotation.hasCategoryId();
    }

    @Override
    public boolean isValid(NewEventDto newEvent, ConstraintValidatorContext cxt) {
//        List<Event> eventList;
//
//        Event event;
//        event = eventRepo.findById(newEvent.getId());
//        System.out.println("----");
//        System.out.println(newEvent.getBookingName());
//        System.out.println(newEvent.getId());
//        if(hasCategoryId) {
//            eventList = eventRepo.findAllByEventCategoryId(newEvent.getEventCategoryId());
//        }else {
//
//        }

//        List<Event> eventList = eventRepo.findAllByEventCategoryId(newEvent.getEventCategoryId());
//
//        ChronoUnit minutes = ChronoUnit.MINUTES;
//        Instant startTime = newEvent.getEventStartTime();
//        Instant newEventStart = startTime;
//        Instant newEventEnd = startTime.plus(newEvent.getEventDuration(), minutes);
//        for (Event e: eventList){
//            Instant eEventStart = e.getEventStartTime();
//            Instant eEventEnd = e.getEventStartTime().plus(e.getEventDuration(), minutes);
//
//            Boolean isStartBetween = (newEventStart.isAfter(eEventStart)
//                    || newEventStart.equals(eEventStart))
//                    && newEventStart.isBefore(eEventEnd);
//            Boolean isEndBetween = newEventEnd.isAfter(eEventStart)
//                    && newEventEnd.isBefore(eEventEnd);
//
//            if (isStartBetween || isEndBetween) return false;
//        }
        return true;
    }
}
//https://reflectoring.io/bean-validation-with-spring-boot/