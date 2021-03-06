/*
 * Sonar W3C Markup Validation Plugin
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
package org.sonar.plugins.web.markup;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.Extension;
import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.plugins.web.markup.constants.MarkupValidatorConstants;
import org.sonar.plugins.web.markup.resourcetab.GwtPageTab;
import org.sonar.plugins.web.markup.rules.DefaultMarkupProfile;
import org.sonar.plugins.web.markup.rules.MarkupRuleRepository;

/**
 * W3CMarkupValidation plugin.
 *
 * @author Matthijs Galesloot
 * @since 1.0
 */
@Properties({
    @Property(key = MarkupValidatorConstants.VALIDATION_URL, name = "W3C Validation URL",
        description = "URL of the W3C Markup Validation API", defaultValue = "http://validator.w3.org/check", global = true, project = true),
    @Property(key = MarkupValidatorConstants.PAUSE_BETWEEN_VALIDATIONS, name = "Pause between validations",
        description = "Pause between validation requests, in milliseconds", defaultValue = "1000", global = true, project = true),
    @Property(key = MarkupValidatorConstants.PROXY_HOST, name = "Proxy host", global = true, project = true),
    @Property(key = MarkupValidatorConstants.PROXY_PORT, name = "Proxy port", global = true, project = true) })
public final class W3CMarkupValidationPlugin implements Plugin {

  public String getDescription() {
    return null;
  }

  public List<Class<? extends Extension>> getExtensions() {
    List<Class<? extends Extension>> list = new ArrayList<Class<? extends Extension>>();

    // W3C markup rules
    list.add(MarkupRuleRepository.class);
    list.add(DefaultMarkupProfile.class);
    list.add(W3CMarkupSensor.class);

    list.add(PageMetrics.class);
    list.add(GwtPageTab.class);

    return list;
  }

  public String getKey() {
    return null;
  }

  public String getName() {
    return null;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
