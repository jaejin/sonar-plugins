/*
 * Sonar PHP Plugin
 * Copyright (C) 2010 Sonar PHP Plugin
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

package org.sonar.plugins.php.phpdepend;

import static org.sonar.api.CoreProperties.PROJECT_EXCLUSIONS_PROPERTY;
import static org.sonar.plugins.php.api.Php.PHP;
import static org.sonar.plugins.php.phpdepend.PhpDependConfiguration.PDEPEND_ARGUMENT_LINE_KEY;
import static org.sonar.plugins.php.phpdepend.PhpDependConfiguration.PDEPEND_BAD_DOCUMENTATION_OPTION;
import static org.sonar.plugins.php.phpdepend.PhpDependConfiguration.PDEPEND_EXCLUDE_OPTION;
import static org.sonar.plugins.php.phpdepend.PhpDependConfiguration.PDEPEND_EXCLUDE_PACKAGE_KEY;
import static org.sonar.plugins.php.phpdepend.PhpDependConfiguration.PDEPEND_IGNORE_KEY;
import static org.sonar.plugins.php.phpdepend.PhpDependConfiguration.PDEPEND_IGNORE_OPTION;
import static org.sonar.plugins.php.phpdepend.PhpDependConfiguration.PDEPEND_WITHOUT_ANNOTATION_OPTION;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.sonar.plugins.php.core.PhpPluginAbstractExecutor;

/**
 * The Class PhpDependExecutor.
 */
public class PhpDependExecutor extends PhpPluginAbstractExecutor {

  /**
   * 
   */
  private static final String PHPDEPEND_DIRECTORY_SEPARATOR = ",";
  /** The configuration. */
  private PhpDependConfiguration configuration;

  /**
   * Instantiates a new php depend executor.
   * 
   * @param configuration
   *          the configuration
   */
  public PhpDependExecutor(PhpDependConfiguration configuration) {
    this.configuration = configuration;
    PHP.setConfiguration(configuration.getProject().getConfiguration());
  }

  @Override
  protected List<String> getCommandLine() {
    List<String> result = new ArrayList<String>();
    result.add(configuration.getOsDependentToolScriptName());
    result.add(configuration.getReportFileCommandOption());
    result.add(configuration.getSuffixesCommandOption());
    if (configuration.isStringPropertySet(PDEPEND_EXCLUDE_PACKAGE_KEY)) {
      result.add(PDEPEND_EXCLUDE_OPTION + configuration.getExcludePackages());
    }

    boolean sonarExclusionsIsSet = configuration.isStringPropertySet(PROJECT_EXCLUSIONS_PROPERTY);
    boolean ignoreKeyIsSet = configuration.isStringPropertySet(PDEPEND_IGNORE_KEY);
    if (ignoreKeyIsSet || sonarExclusionsIsSet) {
      String ignore = configuration.getIgnoreDirs();
      Configuration projectConfiguration = configuration.getProject().getConfiguration();
      String[] sonarExclusions = projectConfiguration.getStringArray(PROJECT_EXCLUSIONS_PROPERTY);
      if (sonarExclusionsIsSet && sonarExclusions != null) {
        ignore += StringUtils.isBlank(ignore) ? "" : ",";
        ignore += StringUtils.join(sonarExclusions, ",");
      }
      result.add(PDEPEND_IGNORE_OPTION + ignore);
    }
    if (configuration.isBadDocumentation()) {
      result.add(PDEPEND_BAD_DOCUMENTATION_OPTION);
    }
    if (configuration.isWithoutAnnotation()) {
      result.add(PDEPEND_WITHOUT_ANNOTATION_OPTION);
    }
    if (configuration.isStringPropertySet(PDEPEND_ARGUMENT_LINE_KEY)) {
      result.add(configuration.getArgumentLine());
    }
    // SONARPLUGINS-547 PhpDependExecutor: wrong dirs params
    result.add(StringUtils.join(configuration.getSourceDirectories(), PHPDEPEND_DIRECTORY_SEPARATOR));
    return result;
  }

  @Override
  protected String getExecutedTool() {
    return PhpDependConfiguration.PDEPEND_COMMAND_LINE;
  }

  /**
   * @return the configuration
   */
  public PhpDependConfiguration getConfiguration() {
    return configuration;
  }

}
