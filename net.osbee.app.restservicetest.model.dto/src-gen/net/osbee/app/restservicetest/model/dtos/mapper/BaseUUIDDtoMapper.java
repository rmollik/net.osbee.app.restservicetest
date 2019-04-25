package net.osbee.app.restservicetest.model.dtos.mapper;

import net.osbee.app.restservicetest.model.dtos.BaseUUIDDto;
import net.osbee.app.restservicetest.model.entities.BaseUUID;
import org.eclipse.osbp.dsl.dto.lib.IMapper;
import org.eclipse.osbp.dsl.dto.lib.IMapperAccess;
import org.eclipse.osbp.dsl.dto.lib.MappingContext;

/**
 * This class maps the dto {@link BaseUUIDDto} to and from the entity {@link BaseUUID}.
 * 
 */
@SuppressWarnings("all")
public class BaseUUIDDtoMapper<DTO extends BaseUUIDDto, ENTITY extends BaseUUID> implements IMapper<DTO, ENTITY> {
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
  public BaseUUID createEntity() {
    return new BaseUUID();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public BaseUUIDDto createDto() {
    return new BaseUUIDDto();
  }
  
  /**
   * Maps the entity {@link BaseUUID} to the dto {@link BaseUUIDDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final BaseUUIDDto dto, final BaseUUID entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    dto.setId(toDto_id(entity, context));
    dto.setVersion(toDto_version(entity, context));
  }
  
  /**
   * Maps the dto {@link BaseUUIDDto} to the entity {@link BaseUUID}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final BaseUUIDDto dto, final BaseUUID entity, final MappingContext context) {
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
  protected String toDto_id(final BaseUUID in, final MappingContext context) {
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
  protected String toEntity_id(final BaseUUIDDto in, final BaseUUID parentEntity, final MappingContext context) {
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
  protected int toDto_version(final BaseUUID in, final MappingContext context) {
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
  protected int toEntity_version(final BaseUUIDDto in, final BaseUUID parentEntity, final MappingContext context) {
    return in.getVersion();
  }
  
  public String createDtoHash(final Object in) {
    return org.eclipse.osbp.runtime.common.hash.HashUtil.createObjectWithIdHash(BaseUUIDDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.eclipse.osbp.runtime.common.hash.HashUtil.createObjectWithIdHash(BaseUUID.class, in);
  }
}
