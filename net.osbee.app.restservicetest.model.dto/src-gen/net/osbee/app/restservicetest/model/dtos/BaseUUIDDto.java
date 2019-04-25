package net.osbee.app.restservicetest.model.dtos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.eclipse.osbp.dsl.common.datatypes.IDto;
import org.eclipse.osbp.runtime.common.annotations.Dirty;
import org.eclipse.osbp.runtime.common.annotations.Dispose;
import org.eclipse.osbp.runtime.common.annotations.Id;
import org.eclipse.osbp.runtime.common.annotations.Version;

@SuppressWarnings("all")
public class BaseUUIDDto implements IDto, Serializable, PropertyChangeListener {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  @Dispose
  private boolean disposed;
  
  @Dirty
  private transient boolean dirty;
  
  @Id
  private String id = java.util.UUID.randomUUID().toString();
  
  @Version
  private int version;
  
  public BaseUUIDDto() {
    installLazyCollections();
  }
  
  /**
   * Installs lazy collection resolving for entity {@link BaseUUID} to the dto {@link BaseUUIDDto}.
   * 
   */
  protected void installLazyCollections() {
    
  }
  
  /**
   * @return true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   * 
   */
  public boolean isDisposed() {
    return this.disposed;
  }
  
  /**
   * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
   */
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }
  
  /**
   * @see PropertyChangeSupport#addPropertyChangeListener(String, PropertyChangeListener)
   */
  public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }
  
  /**
   * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
   */
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
  
  /**
   * @see PropertyChangeSupport#removePropertyChangeListener(String, PropertyChangeListener)
   */
  public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
  
  /**
   * @see PropertyChangeSupport#firePropertyChange(String, Object, Object)
   */
  public void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
  }
  
  /**
   * @return true, if the object is dirty. 
   * 
   */
  public boolean isDirty() {
    return dirty;
  }
  
  /**
   * Sets the dirty state of this object.
   * 
   */
  public void setDirty(final boolean dirty) {
    firePropertyChange("dirty", this.dirty, this.dirty = dirty );
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
    firePropertyChange("disposed", this.disposed, this.disposed = true);
  }
  
  /**
   * Returns the id property or <code>null</code> if not present.
   */
  public String getId() {
    return this.id;
  }
  
  /**
   * Sets the <code>id</code> property to this instance.
   * 
   * @param id - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setId(final String id) {
    firePropertyChange("id", this.id, this.id = id );
    				installLazyCollections();
  }
  
  /**
   * Returns the version property or <code>null</code> if not present.
   */
  public int getVersion() {
    return this.version;
  }
  
  /**
   * Sets the <code>version</code> property to this instance.
   * 
   * @param version - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setVersion(final int version) {
    firePropertyChange("version", this.version, this.version = version );
  }
  
  public boolean equalVersions(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseUUIDDto other = (BaseUUIDDto) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (other.version != this.version)
      return false;
    return true;
  }
  
  public void propertyChange(final java.beans.PropertyChangeEvent event) {
    Object source = event.getSource();
    
    // forward the event from embeddable beans to all listeners. So the parent of the embeddable
    // bean will become notified and its dirty state can be handled properly
    { 
    	// no super class available to forward event
    }
  }
}
