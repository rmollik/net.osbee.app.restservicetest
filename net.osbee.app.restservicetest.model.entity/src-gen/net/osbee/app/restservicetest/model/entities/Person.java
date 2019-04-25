package net.osbee.app.restservicetest.model.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import net.osbee.app.restservicetest.model.entities.BaseUUID;
import org.eclipse.osbp.dsl.common.datatypes.IEntity;
import org.eclipse.osbp.runtime.common.annotations.Dispose;
import org.eclipse.osbp.runtime.common.annotations.Properties;
import org.eclipse.osbp.runtime.common.annotations.Property;
import org.eclipse.osbp.runtime.common.validation.ErrorSeverity;
import org.eclipse.osbp.runtime.common.validation.InfoSeverity;

/**
 * a natural person
 */
@Entity
@Table(name = "PERSON")
@SuppressWarnings("all")
public class Person extends BaseUUID implements IEntity {
  @Column(name = "FIRST_NAME")
  private String firstName;
  
  @Column(name = "LAST_NAME")
  private String lastName;
  
  @Column(name = "BIRTHDATE")
  @Temporal(value = TemporalType.DATE)
  @Properties(properties = @Property(key = "Date", value = "Day"))
  @Valid
  @NotNull(payload = InfoSeverity.class)
  @Past(payload = ErrorSeverity.class)
  private Date birthdate;
  
  /**
   * Checks whether the object is disposed.
   * @throws RuntimeException if the object is disposed.
   */
  private void checkDisposed() {
    if (isDisposed()) {
      throw new RuntimeException("Object already disposed: " + this);
    }
  }
  
  /**
   * Calling dispose will destroy that instance. The internal state will be 
   * set to 'disposed' and methods of that object must not be used anymore. 
   * Each call will result in runtime exceptions.<br>
   * If this object keeps composition containments, these will be disposed too. 
   * So the whole composition containment tree will be disposed on calling this method.
   */
  @Dispose
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    super.dispose();
  }
  
  /**
   * @return Returns the firstName property or <code>null</code> if not present.
   */
  public String getFirstName() {
    checkDisposed();
    return this.firstName;
  }
  
  /**
   * Sets the firstName property to this instance.
   */
  public void setFirstName(final String firstName) {
    checkDisposed();
    this.firstName = firstName;
  }
  
  /**
   * @return Returns the lastName property or <code>null</code> if not present.
   */
  public String getLastName() {
    checkDisposed();
    return this.lastName;
  }
  
  /**
   * Sets the lastName property to this instance.
   */
  public void setLastName(final String lastName) {
    checkDisposed();
    this.lastName = lastName;
  }
  
  /**
   * @return Returns the birthdate property or <code>null</code> if not present.
   */
  public Date getBirthdate() {
    checkDisposed();
    return this.birthdate;
  }
  
  /**
   * Sets the birthdate property to this instance.
   */
  public void setBirthdate(final Date birthdate) {
    checkDisposed();
    this.birthdate = birthdate;
  }
  
  /**
   * Iterates all cross references and removes them from the parent to avoid ConstraintViolationException
   */
  @PreRemove
  protected void preRemove() {
    
  }
}
