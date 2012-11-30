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
package com.eclipsesource.tabris;

import org.eclipse.rap.rwt.SingletonUtil;
import org.eclipse.rap.rwt.client.Client;
import org.eclipse.rap.rwt.client.service.ClientService;

import com.eclipsesource.tabris.interaction.AppLauncher;
import com.eclipsesource.tabris.internal.AppLauncherImpl;


/**
 * <p>
 * Special Client implementation for Tabris. Current services are:
 * <ul>
 * <li>{@link AppLauncher}</li>
 * </ul>
 * They can be obtained using the getService( Class ) method.
 * </p>
 * 
 * @since 0.9
 */
public class TabrisClient implements Client {

  @Override
  @SuppressWarnings("unchecked")
  public <T extends ClientService> T getService( Class<T> type ) {
    T result = null;
    if( type == AppLauncher.class ) {
      result = ( T )SingletonUtil.getSessionInstance( AppLauncherImpl.class );
    }
    return result;
  }
}
