package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventDetailDto;
import sit.int221.oasipserver.dtos.EventcategoryDto;
import sit.int221.oasipserver.dtos.UpdateEventDto;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;
import sit.int221.oasipserver.repo.EventcategoryRepository;
import sit.int221.oasipserver.utils.ListMapper;

import java.util.List;

@Service
public class EventcategoryService {
    @Autowired
    private EventcategoryRepository repository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ListMapper listMapper;

    public List<EventcategoryDto> getAll () {
        List<Eventcategory> eventcategoryList = repository.findAll();
        return listMapper.mapList(eventcategoryList, EventcategoryDto.class, modelMapper);
    }

    public Eventcategory getById (Integer id) {
        Eventcategory eventcategory = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id "+ id+
                        "Does Not Exist !!!"
                ));
        return eventcategory;
    }

    public Eventcategory create(EventcategoryDto newEventcategory){
        Eventcategory eventcategory = modelMapper.map(newEventcategory, Eventcategory.class);
        return repository.saveAndFlush(eventcategory);
    }

    public void delete(Integer id){
        repository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + " does not exist !!!"));
        repository.deleteById(id);
    }

    public EventcategoryDto update(EventcategoryDto eventcategoryDto, Integer id) {
        Eventcategory eventcategory = mapEventcategory(getById(id), eventcategoryDto);
        return modelMapper.map(repository.saveAndFlush(eventcategory),EventcategoryDto.class);
    }

    private Eventcategory mapEventcategory(Eventcategory eventcategory, EventcategoryDto eventcategoryDto) {
        if (eventcategoryDto.getEventCategoryName() != null)
            eventcategory.setEventCategoryName(eventcategoryDto.getEventCategoryName());

        if (eventcategoryDto.getEventCategoryDescription() != null)
            eventcategory.setEventCategoryDescription(eventcategoryDto.getEventCategoryDescription());

        if (eventcategoryDto.getEventDuration() != null)
            eventcategory.setEventDuration(eventcategoryDto.getEventDuration());

        return eventcategory;
    }

    public Integer getDurationById(Integer id){
        return getById(id).getEventDuration();
    }
    public String getDurationNameById(Integer id){
        return getById(id).getEventCategoryName();
    }
}
