/*******************************************************************************
 * Copyright (c) 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.tabris.internal.ui;

import static com.eclipsesource.tabris.internal.Preconditions.checkArgumentNotNull;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.eclipsesource.tabris.ui.ActionConfiguration;
import com.eclipsesource.tabris.ui.Page;
import com.eclipsesource.tabris.ui.PageStyle;


public class PageDescriptor implements Serializable {

  private final String id;
  private final boolean isTopLevelPage;
  private final Class<? extends Page> pageType;
  private final List<ActionDescriptor> actions;
  private final PageStyle[] style;
  private final String title;
  private final byte[] image;

  public PageDescriptor( String id,
                             Class<? extends Page> pageType,
                             String title,
                             InputStream image,
                             boolean isTopLevelPage,
                             PageStyle... style )
  {
    this.actions = new ArrayList<ActionDescriptor>();
    this.id = id;
    this.title = title;
    this.pageType = pageType;
    this.isTopLevelPage = isTopLevelPage;
    this.image = ImageUtil.getBytes( image );
    this.style = style;
  }

  public void addAction( ActionConfiguration configuration ) {
    checkArgumentNotNull( configuration, "Action Configuration" );
    ActionDescriptor descriptor = configuration.getAdapter( ActionDescriptor.class );
    actions.add( descriptor );
  }

  public String getId() {
    return id;
  }

  public boolean isTopLevel() {
    return isTopLevelPage;
  }

  public Class<? extends Page> getPageType() {
    return pageType;
  }

  public String getTitle() {
    return title;
  }

  public List<ActionDescriptor> getActions() {
    return actions;
  }

  public PageStyle[] getPageStyle() {
    return style;
  }

  public byte[] getImage() {
    return image;
  }

  // Generated by eclipse. Sufficient for the moment.

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ( ( actions == null )
                                                   ? 0
                                                   : actions.hashCode() );
    result = prime * result + ( ( id == null )
                                              ? 0
                                              : id.hashCode() );
    result = prime * result + ( ( image == null )
                                                 ? 0
                                                 : image.hashCode() );
    result = prime * result + ( isTopLevelPage
                                              ? 1231
                                              : 1237 );
    result = prime * result + ( ( pageType == null )
                                                    ? 0
                                                    : pageType.hashCode() );
    result = prime * result + Arrays.hashCode( style );
    result = prime * result + ( ( title == null )
                                                 ? 0
                                                 : title.hashCode() );
    return result;
  }

  @Override
  public boolean equals( Object obj ) {
    if( this == obj )
      return true;
    if( obj == null )
      return false;
    if( getClass() != obj.getClass() )
      return false;
    PageDescriptor other = ( PageDescriptor )obj;
    if( actions == null ) {
      if( other.actions != null )
        return false;
    } else if( !actions.equals( other.actions ) )
      return false;
    if( id == null ) {
      if( other.id != null )
        return false;
    } else if( !id.equals( other.id ) )
      return false;
    if( image == null ) {
      if( other.image != null )
        return false;
    } else if( !image.equals( other.image ) )
      return false;
    if( isTopLevelPage != other.isTopLevelPage )
      return false;
    if( pageType == null ) {
      if( other.pageType != null )
        return false;
    } else if( !pageType.equals( other.pageType ) )
      return false;
    if( !Arrays.equals( style, other.style ) )
      return false;
    if( title == null ) {
      if( other.title != null )
        return false;
    } else if( !title.equals( other.title ) )
      return false;
    return true;
  }

}
