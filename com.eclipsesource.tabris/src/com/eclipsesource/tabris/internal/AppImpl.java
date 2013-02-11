/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html Contributors:
 * EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.tabris.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.internal.remote.ConnectionImpl;
import org.eclipse.rap.rwt.remote.AbstractOperationHandler;
import org.eclipse.rap.rwt.remote.RemoteObject;

import com.eclipsesource.tabris.event.App;
import com.eclipsesource.tabris.event.AppEvent;
import com.eclipsesource.tabris.event.AppListener;
import com.eclipsesource.tabris.event.EventType;

@SuppressWarnings("restriction")
public class AppImpl implements App {

  private static final String TYPE = "tabris.App";
  private final RemoteObject remoteObject;
  private final Map<EventType, List<AppListener>> eventListeners;


  public AppImpl() {
    remoteObject = ( ( ConnectionImpl )RWT.getUISession().getConnection() ).createServiceObject( TYPE );
    remoteObject.setHandler( new AppOperationHandler() );
    eventListeners = new HashMap<EventType, List<AppListener>>();
  }

  @Override
  public void addListener( EventType type, AppListener listener ) {
    List<AppListener> listeners = eventListeners.get( type );
    if( listeners == null ) {
      listeners = new ArrayList<AppListener>();
      eventListeners.put( type, listeners );
      remoteObject.listen( type.getName(), true );
    }
    listeners.add( listener );
  }

  @Override
  public void removeListener( EventType type, AppListener listener ) {
    List<AppListener> listeners = eventListeners.get( type );
    listeners.remove( listener );
    if( listeners.isEmpty() ) {
      eventListeners.remove( type );
      remoteObject.listen( type.getName(), false );
    }
  }

  private class AppOperationHandler extends AbstractOperationHandler {

    @Override
    public void handleNotify( String event, Map<String, Object> properties ) {
      AppEvent appEvent = new AppEvent( EventType.fromName( event ), properties );
      notifyListeners( appEvent );
    }

    private void notifyListeners( AppEvent appEvent ) {
      List<AppListener> listeners = eventListeners.get( appEvent.getType() );
      if( listeners != null ) {
        for( AppListener appListener : listeners ) {
          appListener.handleEvent( appEvent );
        }
      }
    }

  }

  RemoteObject getRemoteObject() {
    return remoteObject;
  }
}
