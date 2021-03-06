/*
 * Sonar Standalone Runner
 * Copyright (C) 2011 SonarSource
 * dev@sonar.codehaus.org
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
package org.sonar.runner;

import org.sonar.batch.bootstrapper.BootstrapClassLoader;
import org.sonar.batch.bootstrapper.BootstrapException;
import org.sonar.batch.bootstrapper.Bootstrapper;
import org.sonar.batch.bootstrapper.BootstrapperIOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

/**
 * @since 1.1
 */
public final class Runner {
  public static final String DEBUG_MODE = "runner.debug";

  private File projectDir;
  private File workDir;
  private Properties properties;

  private Runner(Properties props) {
    this.properties = props;
    initDirs();
  }

  public static Runner create(Properties props) {
    return new Runner(props);
  }

  public void execute() {
    Bootstrapper bootstrapper = new Bootstrapper("SonarRunner/" + getRunnerVersion(), getServerURl(), getWorkDir());
    checkSonarVersion(bootstrapper);
    delegateExecution(createClassLoader(bootstrapper));
  }

  public String getServerURl() {
    return properties.getProperty("sonar.host.url", "http://localhost:9000");
  }

  private void initDirs() {
    String path = properties.getProperty("project.home", ".");
    projectDir = new File(path);
    if (!projectDir.isDirectory() || !projectDir.exists()) {
      throw new IllegalArgumentException("Project home must be an existing directory: " + path);
    }
    workDir = new File(projectDir, ".sonar");
  }

  public File getProjectDir() {
    return projectDir;
  }

  /**
   * @return work directory, default is ".sonar" in project directory
   */
  public File getWorkDir() {
    return workDir;
  }

  /**
   * @return global properties, project properties and command-line properties
   */
  public Properties getProperties() {
    return properties;
  }

  public boolean isDebug() {
    return Boolean.parseBoolean(properties.getProperty(DEBUG_MODE, "false"));
  }

  public String getRunnerVersion() {
    InputStream in = null;
    try {
      in = Runner.class.getResourceAsStream("/org/sonar/runner/version.txt");
      Properties props = new Properties();
      props.load(in);
      return props.getProperty("version");
    } catch (IOException e) {
      throw new BootstrapException("Could not load the version information for Sonar Standalone Runner", e);
    } finally {
      BootstrapperIOUtils.closeQuietly(in);
    }
  }

  private void checkSonarVersion(Bootstrapper bootstrapper) {
    String serverVersion = bootstrapper.getServerVersion();
    if (isVersionPriorTo2Dot6(serverVersion)) {
      throw new BootstrapException("Sonar " + serverVersion
          + " does not support Standalone Runner. Please upgrade Sonar to version 2.6 or more.");
    }
  }

  private BootstrapClassLoader createClassLoader(Bootstrapper bootstrapper) {
    URL url = Main.class.getProtectionDomain().getCodeSource().getLocation();
    return bootstrapper.createClassLoader(
        new URL[]{url}, // Add JAR with Sonar Runner - it's a Jar which contains this class
        getClass().getClassLoader());
  }

  static boolean isVersionPriorTo2Dot6(String version) {
    return isVersion(version, "1")
        || isVersion(version, "2.0")
        || isVersion(version, "2.1")
        || isVersion(version, "2.2")
        || isVersion(version, "2.3")
        || isVersion(version, "2.4")
        || isVersion(version, "2.5");
  }

  static boolean isVersion(String version, String prefix) {
    return version.startsWith(prefix + ".") || version.equals(prefix);
  }

  /**
   * Loads {@link Launcher} from specified {@link org.sonar.batch.bootstrapper.BootstrapClassLoader} and passes control to it.
   *
   * @see Launcher#execute()
   */
  private void delegateExecution(BootstrapClassLoader sonarClassLoader) {
    ClassLoader oldContextClassLoader = Thread.currentThread().getContextClassLoader();
    try {
      Thread.currentThread().setContextClassLoader(sonarClassLoader);
      Class<?> launcherClass = sonarClassLoader.findClass("org.sonar.runner.Launcher");
      Constructor<?> constructor = launcherClass.getConstructor(Runner.class);
      Object launcher = constructor.newInstance(this);
      Method method = launcherClass.getMethod("execute");
      method.invoke(launcher);
    } catch (InvocationTargetException e) {
      // Unwrap original exception
      throw new BootstrapException(e.getTargetException());
    } catch (Exception e) {
      // Catch all other exceptions, which relates to reflection
      throw new BootstrapException(e);
    } finally {
      Thread.currentThread().setContextClassLoader(oldContextClassLoader);
    }
  }
}
