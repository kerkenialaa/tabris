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
package com.eclipsesource.tabris.ui;



/**
 * <p>
 * The {@link PageOperator} is used to handle pages. This includes the navigation or the data transmission between
 * single pages. To get an instance use {@link UI#getPageOperator()}.
 * </p>
 * <p>
 * <strong>Note:</strong> This interfaces is still considered <strong>provisional</strong>. It is
 * expected to evolve over the next releases, which may lead to slight changes. Consider using {@link AbstractPage}
 * and it's methods.
 * </p>
 *
 * @see UI
 * @see Page
 *
 * @noimplement This interface is not intended to be implemented by clients.
 * @since 1.0
 */
public interface PageOperator {

  /**
   * <p>
   * Opens a specific page on the client device.
   * </p>
   *
   * @param pageId the Id of the page to open. Must not be empty or <code>null</code>.
   *
   * @throws IllegalStateException when no page exist for the given id.
   *
   * @since 1.0
   */
  void openPage( String pageId ) throws IllegalStateException;

  /**
   * <p>
   * Opens a specific page on the client device. The provided data will be the page data for
   * the new page. See {@link PageOperator#getCurrentPageData()}.
   * </p>
   *
   * @param pageId the Id of the page to show. Must not be empty or <code>null</code>.
   * @param data the page data for the new page. Must not be <code>null</code>.
   *
   * @throws IllegalStateException when no page exist for the given id.
   *
   * @since 1.0
   */
  void openPage( String pageId, PageData data ) throws IllegalStateException;

  /**
   * <p>
   * Shows the last page and destroys the current active page.
   * </p>
   *
   * @throws IllegalStateException when it's called on a top level page.
   *
   * @since 1.0
   */
  void closeCurrentPage() throws IllegalStateException;

  /**
   * <p>
   * Returns the current visible {@link Page} object.
   * </p>
   *
   * @since 1.0
   */
  Page getCurrentPage();

  /**
   * <p>
   * Every page has it's own {@link PageData}. You can influence this data by calling the
   * {@link PageOperator#openPage(String, PageData)} method with a new {@link PageData}. This method
   * returns the {@link PageData} for the current active page.
   * </p>
   *
   * @since 1.0
   */
  PageData getCurrentPageData();

  /**
   * <p>
   * Returns the current visible {@link Page} id.
   * </p>
   *
   * @since 1.2
   */
  String getCurrentPageId();

  /**
   * <p>
   * Sets the title for the defined page.
   * </p>
   *
   * @since 1.0
   */
  void setCurrentPageTitle( String title );

}
