package net.osbee.app.restservicetest.model.dtos.service;

import net.osbee.app.restservicetest.model.dtos.PersonDto;
import net.osbee.app.restservicetest.model.entities.Person;
import org.eclipse.osbp.dsl.dto.lib.services.impl.AbstractDTOService;

@SuppressWarnings("all")
public class PersonDtoService extends AbstractDTOService<PersonDto, Person> {
  public PersonDtoService() {
    // set the default persistence ID
    setPersistenceId("businessdata");
  }
  
  public Class<PersonDto> getDtoClass() {
    return PersonDto.class;
  }
  
  public Class<Person> getEntityClass() {
    return Person.class;
  }
  
  public Object getId(final PersonDto dto) {
    return dto.getId();
  }
}
