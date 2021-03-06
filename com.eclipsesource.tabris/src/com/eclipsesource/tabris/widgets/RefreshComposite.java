/*******************************************************************************
 * Copyright (c) 2014 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.tabris.widgets;

import static com.eclipsesource.tabris.internal.Clauses.whenNull;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.rwt.internal.lifecycle.WidgetLifeCycleAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import com.eclipsesource.tabris.internal.RefreshCompositeLCA;
import com.eclipsesource.tabris.internal.RefreshCompositeLCA.RefreshAdapter;
import com.eclipsesource.tabris.widgets.enhancement.RefreshHandler;


/**
 * <p>
 * A {@link RefreshComposite} works like an ordinary {@link Composite} with the exception that it adds native refresh
 * behavior. This means the client uses native facilities to allow the user to refresh some visible area e.g. by
 * pulling down the area.
 * </p>
 * <p>
 * Refreshing is an asynchronous operation and is mostly done in the background. For this reason it's your
 * responsibility to call a refresh done by calling the method {@link RefreshComposite#done()}. This will instruct the
 * client to hide the refresh UI.
 * </p>
 * <p>
 * <b>Please Note:</b> Because {@link Tree}s are a special component on mobile devices it's recommended to use a
 * {@link RefreshHandler} on a {@link Tree} instead of embedding it into a {@link RefreshComposite}.
 * </p>
 *
 * @see RefreshHandler
 *
 * @since 1.4
 */
@SuppressWarnings("restriction")
public class RefreshComposite extends Composite {

  private final List<RefreshListener> listeners;
  private final RefreshAdapter resetAdapter;
  private String message;

  public RefreshComposite( Composite parent, int style ) {
    super( parent, style );
    this.listeners = new ArrayList<RefreshListener>();
    this.resetAdapter = new RefreshAdapter();
  }

  /**
   * <p>
   * Sets the message the client should show during a refresh.
   * </p>
   *
   * @param message the message to show. Must not be <code>null</code>.
   */
  public void setMessage( String message ) {
    whenNull( message ).throwIllegalArgument( "Message must not be null" );
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  /**
   * <p>
   * Adds a {@link RefreshListener} that will be notified when the user has triggered a refresh. After the refresh
   * is done you need to call {@link RefreshComposite#done()} to hide the refresh UI on the client.
   * </p>
   *
   * @param listener the listener to add. Must not be <code>null</code>.
   */
  public void addRefreshListener( RefreshListener listener ) {
    whenNull( listener ).throwIllegalArgument( "RefreshListener must not be null" );
    listeners.add( listener );
  }

  /**
   * <p>
   * Removes a {@link RefreshListener}.
   * </p>
   *
   * @param listener the listener to remove. Must not be <code>null</code>.
   */
  public void removeRefreshListener( RefreshListener listener ) {
    whenNull( listener ).throwIllegalArgument( "RefreshListener must not be null" );
    listeners.remove( listener );
  }

  /**
   * <p>
   * Returns the added {@link RefreshListener}s
   * </p>
   */
  public List<RefreshListener> getRefreshListeners() {
    return new ArrayList<RefreshListener>( listeners );
  }

  /**
   * <p>
   * Instructs a client to hide it's refresh UI. Usually this method will be called within a
   * {@link RefreshListener#refresh()} method after the refreshing is done.
   * </p>
   */
  public void done() {
    resetAdapter.setDone( true );
  }

  @Override
  @SuppressWarnings({ "unchecked", "deprecation" })
  public <T> T getAdapter( Class<T> adapter ) {
    T result;
    if( adapter == WidgetLifeCycleAdapter.class || adapter == org.eclipse.rap.rwt.lifecycle.WidgetLifeCycleAdapter.class ) {
      result = ( T )new RefreshCompositeLCA();
    } else if( adapter == RefreshAdapter.class ) {
      result = ( T )resetAdapter;
    } else {
      result = super.getAdapter( adapter );
    }
    return result;
  }

}
