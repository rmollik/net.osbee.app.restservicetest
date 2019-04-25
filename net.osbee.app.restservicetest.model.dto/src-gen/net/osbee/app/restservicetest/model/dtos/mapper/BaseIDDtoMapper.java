package net.osbee.app.restservicetest.model.dtos.mapper;

import net.osbee.app.restservicetest.model.dtos.BaseIDDto;
import net.osbee.app.restservicetest.model.entities.BaseID;
import org.eclipse.osbp.dsl.dto.lib.IMapper;
import org.eclipse.osbp.dsl.dto.lib.IMapperAccess;
import org.eclipse.osbp.dsl.dto.lib.MappingContext;

/**
 * This class maps the dto {@link BaseIDDto} to and from the entity {@link BaseID}.
 * 
 */
@SuppressWarnings("all")
public class BaseIDDtoMapper<DTO extends BaseIDDto, ENTITY extends BaseID> implements IMapper<DTO, ENTITY> {
  private IMapperAccess mapperAccess;
  
  /**
   * Returns the mapper instance that may map between the given dto and entity. Or <code>null</code> if no mapper is available.
   * 
   * @param dtoClass - the class of the dto that should be mapped
   * @param entityClass - the class of the entity that should be mapped
   * @return the mapper instance or <code>null</code>
   */
  protected <D, E> IMapper<D, E> getToDtoMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getToDtoMapper(dtoClass, entityClass);
  }
  
  /**
   * Returns the mapper instance that may map between the given dto and entity. Or <code>null</code> if no mapper is available.
   * 
   * @param dtoClass - the class of the dto that should be mapped
   * @param entityClass - the class of the entity that should be mapped
   * @return the mapper instance or <code>null</code>
   */
  protected <D, E> IMapper<D, E> getToEntityMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getToEntityMapper(dtoClass, entityClass);
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void bindMapperAccess(final IMapperAccess mapperAccess) {
    this.mapperAccess = mapperAccess;
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void unbindMapperAccess(final IMapperAccess mapperAccess) {
    this.mapperAccess = null;
  }
  
  /**
   * Creates a new instance of the entity
   */
  public BaseID createEntity() {
    return new BaseID();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public BaseIDDto createDto() {
    return new BaseIDDto();
  }
  
  /**
   * Maps the entity {@link BaseID} to the dto {@link BaseIDDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final BaseIDDto dto, final BaseID entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    dto.setId(toDto_id(entity, context));
    dto.setVersion(toDto_version(entity, context));
  }
  
  /**
   * Maps the dto {@link BaseIDDto} to the entity {@link BaseID}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final BaseIDDto dto, final BaseID entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    
    entity.setId(toEntity_id(dto, entity, context));
    entity.setVersion(toEntity_version(dto, entity, context));
  }
  
  /**
   * Maps the property id from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_id(final BaseID in, final MappingContext context) {
    return in.getId();
  }
  
  /**
   * Maps the property id from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param parentEntity - The parentEntity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_id(final BaseIDDto in, final BaseID parentEntity, final MappingContext context) {
    return in.getId();
  }
  
  /**
   * Maps the property version from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected int toDto_version(final BaseID in, final MappingContext context) {
    return in.getVersion();
  }
  
  /**
   * Maps the property version from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param parentEntity - The parentEntity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected int toEntity_version(final BaseIDDto in, final BaseID parentEntity, final MappingContext context) {
    return in.getVersion();
  }
  
  public String createDtoHash(final Object in) {
    return org.eclipse.osbp.runtime.common.hash.HashUtil.createObjectWithIdHash(BaseIDDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.eclipse.osbp.runtime.common.hash.HashUtil.createObjectWithIdHash(BaseID.class, in);
  }
}
