package sit.int221.oasipserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipserver.dtos.EventcategoryDto;
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

    public EventcategoryDto getById (Integer id) {
        Eventcategory eventcategory = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id "+ id+
                        "Does Not Exist !!!"
                ));
        return modelMapper.map(eventcategory, EventcategoryDto.class);
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
}
