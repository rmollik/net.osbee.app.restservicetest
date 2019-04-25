package net.osbee.app.restservicetest.model.dtos.mapper;

import java.util.Date;
import net.osbee.app.restservicetest.model.dtos.PersonDto;
import net.osbee.app.restservicetest.model.dtos.mapper.BaseUUIDDtoMapper;
import net.osbee.app.restservicetest.model.entities.Person;
import org.eclipse.osbp.dsl.dto.lib.MappingContext;

/**
 * This class maps the dto {@link PersonDto} to and from the entity {@link Person}.
 * 
 */
@SuppressWarnings("all")
public class PersonDtoMapper<DTO extends PersonDto, ENTITY extends Person> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Person createEntity() {
    return new Person();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public PersonDto createDto() {
    return new PersonDto();
  }
  
  /**
   * Maps the entity {@link Person} to the dto {@link PersonDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final PersonDto dto, final Person entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setFirstName(toDto_firstName(entity, context));
    dto.setLastName(toDto_lastName(entity, context));
    dto.setBirthdate(toDto_birthdate(entity, context));
  }
  
  /**
   * Maps the dto {@link PersonDto} to the entity {@link Person}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final PersonDto dto, final Person entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    entity.setFirstName(toEntity_firstName(dto, entity, context));
    entity.setLastName(toEntity_lastName(dto, entity, context));
    entity.setBirthdate(toEntity_birthdate(dto, entity, context));
  }
  
  /**
   * Maps the property firstName from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_firstName(final Person in, final MappingContext context) {
    return in.getFirstName();
  }
  
  /**
   * Maps the property firstName from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param parentEntity - The parentEntity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_firstName(final PersonDto in, final Person parentEntity, final MappingContext context) {
    return in.getFirstName();
  }
  
  /**
   * Maps the property lastName from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_lastName(final Person in, final MappingContext context) {
    return in.getLastName();
  }
  
  /**
   * Maps the property lastName from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param parentEntity - The parentEntity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_lastName(final PersonDto in, final Person parentEntity, final MappingContext context) {
    return in.getLastName();
  }
  
  /**
   * Maps the property birthdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Date toDto_birthdate(final Person in, final MappingContext context) {
    return in.getBirthdate();
  }
  
  /**
   * Maps the property birthdate from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param parentEntity - The parentEntity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Date toEntity_birthdate(final PersonDto in, final Person parentEntity, final MappingContext context) {
    return in.getBirthdate();
  }
  
  public String createDtoHash(final Object in) {
    return org.eclipse.osbp.runtime.common.hash.HashUtil.createObjectWithIdHash(PersonDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.eclipse.osbp.runtime.common.hash.HashUtil.createObjectWithIdHash(Person.class, in);
  }
}
