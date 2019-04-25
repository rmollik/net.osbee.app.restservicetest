package net.osbee.app.restservicetest.model.dtos;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import net.osbee.app.restservicetest.model.dtos.BaseUUIDDto;
import org.eclipse.osbp.dsl.common.datatypes.IDto;
import org.eclipse.osbp.runtime.common.annotations.Dispose;
import org.eclipse.osbp.runtime.common.annotations.Properties;
import org.eclipse.osbp.runtime.common.annotations.Property;
import org.eclipse.osbp.runtime.common.validation.ErrorSeverity;
import org.eclipse.osbp.runtime.common.validation.InfoSeverity;

@SuppressWarnings("all")
public class PersonDto extends BaseUUIDDto implements IDto, Serializable, PropertyChangeListener {
  private String firstName;
  
  private String lastName;
  
  @Properties(properties = @Property(key = "Date", value = "Day"))
  @Valid
  @NotNull(payload = InfoSeverity.class)
  @Past(payload = ErrorSeverity.class)
  private Date birthdate;
  
  public PersonDto() {
    installLazyCollections();
  }
  
  /**
   * Installs lazy collection resolving for entity {@link Person} to the dto {@link PersonDto}.
   * 
   */
  protected void installLazyCollections() {
    super.installLazyCollections();
  }
  
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
   * Each call will result in runtime exceptions.<br/>
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
   * Returns the firstName property or <code>null</code> if not present.
   */
  public String getFirstName() {
    return this.firstName;
  }
  
  /**
   * Sets the <code>firstName</code> property to this instance.
   * 
   * @param firstName - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setFirstName(final String firstName) {
    firePropertyChange("firstName", this.firstName, this.firstName = firstName );
  }
  
  /**
   * Returns the lastName property or <code>null</code> if not present.
   */
  public String getLastName() {
    return this.lastName;
  }
  
  /**
   * Sets the <code>lastName</code> property to this instance.
   * 
   * @param lastName - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setLastName(final String lastName) {
    firePropertyChange("lastName", this.lastName, this.lastName = lastName );
  }
  
  /**
   * Returns the birthdate property or <code>null</code> if not present.
   */
  public Date getBirthdate() {
    return this.birthdate;
  }
  
  /**
   * Sets the <code>birthdate</code> property to this instance.
   * 
   * @param birthdate - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setBirthdate(final Date birthdate) {
    firePropertyChange("birthdate", this.birthdate, this.birthdate = birthdate );
  }
  
  public void propertyChange(final java.beans.PropertyChangeEvent event) {
    Object source = event.getSource();
    
    // forward the event from embeddable beans to all listeners. So the parent of the embeddable
    // bean will become notified and its dirty state can be handled properly
    { 
    	super.propertyChange(event);
    }
  }
}
