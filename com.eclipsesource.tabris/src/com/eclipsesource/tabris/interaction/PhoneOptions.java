/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.tabris.interaction;

import static com.eclipsesource.tabris.internal.Clauses.when;
import static com.eclipsesource.tabris.internal.Clauses.whenNull;
import static com.eclipsesource.tabris.internal.Constants.PROPERTY_NUMBER;


/**
 * <p>
 * Concrete launch option to do a phone call.
 * </p>
 * @since 0.9
 */
public class PhoneOptions extends LaunchOptions {

  public PhoneOptions( String phoneNumber ) {
    super( App.PHONE );
    whenNull( phoneNumber ).throwIllegalArgument( "PhoneNumber must not be null" );
    when( phoneNumber.isEmpty() ).throwIllegalArgument( "Phone Number must not be empty" );
    add( PROPERTY_NUMBER, phoneNumber );
  }
}
