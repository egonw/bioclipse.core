/* ***************************************************************************
 * Copyright (c) 2008-2009  Egon Willighagen <egonw@users.sf.net>
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *****************************************************************************/
package net.bioclipse.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that allows indication of a comma-separated list of
 * JUnit4 test methods that tests the annotated manager method. It
 * requires the manager class to be annotated
 * with {@link TestClasses}.
 * 
 * @author egonw
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TestMethods {
    /**
     * Returns the comma-separated list of test methods.
     */
    String value();
}
