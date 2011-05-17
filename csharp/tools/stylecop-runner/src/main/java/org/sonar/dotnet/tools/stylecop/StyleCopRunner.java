/*
 * .NET tools :: StyleCop Runner
 * Copyright (C) 2011 Jose Chillan, Alexandre Victoor and SonarSource
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

package org.sonar.dotnet.tools.stylecop;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.utils.command.Command;
import org.sonar.api.utils.command.CommandExecutor;
import org.sonar.dotnet.tools.commons.utils.ZipUtils;
import org.sonar.plugins.csharp.api.visualstudio.VisualStudioSolution;

/**
 * Class that runs the StyleCop program.
 */
public class StyleCopRunner { // NOSONAR : can't mock it otherwise

  private static final Logger LOG = LoggerFactory.getLogger(StyleCopRunner.class);

  private static final int MINUTES_TO_MILLISECONDS = 60000;
  private static final String EMBEDDED_VERSION = "4.4.0.14";

  private File styleCopFolder;
  private File dotnetSdkFolder;

  /**
   * Constructs a {@link StyleCopRunner}.
   * 
   * @param configuration
   *          StyleCop configuration elements
   * @param fileSystem
   *          the file system of the project
   */
  private StyleCopRunner() {
  }

  /**
   * Creates a new {@link StyleCopRunner} object for the given executable file. If the executable file does not exist, then the embedded one
   * will be used.
   * 
   * @param styleCopPath
   *          the full path of the StyleCop install directory. For instance: "C:/Program Files/Microsoft StyleCop 4.4.0.14". May be null: in
   *          this case, the embedded StyleCop executable will be used.
   * @param tempFolder
   *          the temporary folder where the embedded StyleCop executable will be copied if the styleCopPath does not point to a valid
   *          executable
   */
  public static StyleCopRunner create(String styleCopPath, String dotnetSdkPath, String tempFolder) throws StyleCopException {
    StyleCopRunner runner = new StyleCopRunner();
    File dotnetSdkDir = new File(dotnetSdkPath);
    runner.dotnetSdkFolder = dotnetSdkDir;
    File styleCopDir = new File(styleCopPath);
    runner.styleCopFolder = styleCopDir;

    if ( !styleCopDir.exists() || !styleCopDir.isDirectory()) {
      LOG.info("StyleCop install folder not found: '{}'. The embedded version ({}) will be used instead.", styleCopDir.getAbsolutePath(),
          EMBEDDED_VERSION);
      try {
        URL executableURL = StyleCopRunner.class.getResource("/StyleCop-" + EMBEDDED_VERSION);
        runner.styleCopFolder = ZipUtils.extractArchiveFolderIntoDirectory(StringUtils.substringBefore(executableURL.getFile(), "!")
            .substring(5), "StyleCop-" + EMBEDDED_VERSION, tempFolder);
      } catch (IOException e) {
        throw new StyleCopException("Could not extract the embedded StyleCop executable: " + e.getMessage(), e);
      }
    }

    return runner;
  }

  /**
   * Creates a pre-configured {@link StyleCopCommandBuilder} that needs to be completed before running the {@link #execute(Command, int)}
   * method.
   * 
   * @return the command to complete
   */
  public StyleCopCommandBuilder createCommandBuilder(VisualStudioSolution solution) {
    StyleCopCommandBuilder builder = StyleCopCommandBuilder.createBuilder(solution);
    builder.setDotnetSdkDirectory(dotnetSdkFolder);
    builder.setStyleCopFolder(styleCopFolder);
    return builder;
  }

  /**
   * Executes the given StyleCop command.
   * 
   * @param command
   *          the command
   * @param timeoutMinutes
   *          the timeout for the command
   * @throws StyleCopException
   *           if StyleCop fails to execute
   */
  public void execute(Command command, int timeoutMinutes) throws StyleCopException {
    LOG.debug("Executing StyleCop program...");
    int exitCode = CommandExecutor.create().execute(command, timeoutMinutes * MINUTES_TO_MILLISECONDS);
    if (exitCode != 0) {
      throw new StyleCopException("StyleCop execution failed with return code '" + exitCode
          + "'. Check StyleCopCop documentation for more information.");
    }
  }

}