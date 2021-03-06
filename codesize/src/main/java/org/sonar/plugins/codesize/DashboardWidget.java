/*
 * Sonar Codesize Plugin
 * Copyright (C) 2010 Matthijs Galesloot
 * dev@sonar.codehaus.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sonar.plugins.codesize;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.NavigationSection;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;

/**
 * Widget to show lines of code for all languages on the sonar dashboard.
 *
 * @author Matthijs Galesloot
 * @since 1.0
 */
@NavigationSection(NavigationSection.RESOURCE)
@UserRole(UserRole.USER)
public final class DashboardWidget extends AbstractRubyTemplate implements RubyRailsWidget {

  public String getId() {
    return "codesize";
  }

  @Override
  protected String getTemplatePath() {
    return "/dashboard_widget.erb";
    // use a system filepath for prototyping:
    // return "C:/workspaces/sonar-plugins/codesize/src/main/resources/dashboard_widget.erb";
  }

  public String getTitle() {
    // not used for the moment by widgets.
    return "CodeSize";
  }
}