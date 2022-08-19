package sit.int221.oasipserver.utils;

import org.modelmapper.ModelMapper;
import sit.int221.oasipserver.entities.Event;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class OverlapValidate {
    private static final OverlapValidate overlapValidate = new OverlapValidate();
    private OverlapValidate() { }
    public boolean overlapCheck(Event newEvent, List<Event> eventList) {
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
    public static OverlapValidate getInstance() {
        return overlapValidate;
    }
}
