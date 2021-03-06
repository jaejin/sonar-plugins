/*
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */

package org.codehaus.sonar.plugins.testability;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.RubyRailsWidget;

public class TestabilityDashboardWidget extends AbstractRubyTemplate implements RubyRailsWidget {

  private static final String WIDGET_ID = "testabilityGlobalDashboardWidget";
  private static final String WIDGET_TITLE = "Testability";

  public String getId() {
    return WIDGET_ID;
  }

  public String getTitle() {
    return WIDGET_TITLE;
  }

  @Override
  protected String getTemplatePath() {
    return "/org/codehaus/sonar/plugins/testability/testability_global_metrics.erb";
  }

}
