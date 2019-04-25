/**
 * Copyright (c) 2011, 2019 - Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 		Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 */
package net.osbee.app.restservicetest.model.messages;

import java.util.Locale;
import org.eclipse.osbp.xtext.messagedsl.common.IMessageCategory;
import org.eclipse.osbp.xtext.messagedsl.common.Message;
import org.slf4j.Logger;

/**
 * <b>This class was auto generated! Leave it unmodified to avoid unpredictable results!</b><br>
 * 
 */
@SuppressWarnings("all")
public final class PersonMessage implements IMessageCategory {
  /**
   * the default logger net.osbee.app.restservicetest.model.messages.PersonMessage will be used
   *                 @param name
   */
  public static final Message hello(final String name) {
    return (
        new Message(
                    "Hello, %%name%%!"
                    , "Hello, %%name%%!"
                    , "name", name
    ))
    ;
  }
  
  /**
   * @param logger set the slf4j logger used when logging this message
   *                 @param name
   */
  public static final Message hello(final Logger logger, final String name) {
    return (
        new Message(logger
                    , "Hello, %%name%%!"
                    , "Hello, %%name%%!"
                    , "name", name
    ))
    ;
  }
}
