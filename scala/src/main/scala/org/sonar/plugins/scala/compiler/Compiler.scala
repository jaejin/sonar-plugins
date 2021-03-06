/*
 * Sonar Scala Plugin
 * Copyright (C) 2011 Felix Müller
 * felix.mueller.berlin@googlemail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.scala.compiler

import tools.nsc._
import tools.util.PathResolver._
import java.net.URLClassLoader

/**
 * This is a wrapper for the Scala compiler. It is used to access
 * the compiler in a more convenient way.
 *
 * @author Felix Müller
 * @since 0.1
 */
object Compiler extends Global(new Settings()) {

  private[compiler] val classpathSeparator = System.getProperty("path.separator")

  private val scalaJarUrls: Option[String] = this.getClass.getClassLoader match {
    case urlClassLoader: URLClassLoader => Some(urlClassLoader.getURLs.mkString(classpathSeparator))
    case _ => None
  }

  private val additionalClassPath = classpathSeparator + scalaJarUrls.map(_ + classpathSeparator).getOrElse("") +
      // TODO only add the path to the scala-library.jar, not the whole javaUserClassPath
      Environment.javaUserClassPath
  settings.classpath.value += additionalClassPath
  new Run

  override def forScaladoc = true
}