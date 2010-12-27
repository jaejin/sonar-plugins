/*
 * Sonar Webscanner Plugin
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

package org.sonar.plugins.webscanner.markup;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.Extension;
import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.plugins.webscanner.language.ProjectConfiguration;

/**
 * @author Matthijs Galesloot
 */
@Properties({
    @Property(key = ProjectConfiguration.FILE_EXTENSIONS, name = "File extensions",
        description = "List of file extensions that will be scanned.", defaultValue = "html", global = true, project = true),
    @Property(key = W3CMarkupSensor.VALIDATION_URL, name = "W3CMarkup API", description = "W3CMarkup Validation API",
        defaultValue = "http://validator.w3.org/check", global = true, project = true) })
public final class W3CMarkupPlugin implements Plugin {

  private static final String KEY = "sonar-w3cmarkup-plugin";

  public static String getKEY() {
    return KEY;
  }

  public String getDescription() {
    return getName() + " validates HTML markup with help of the online W3C Markup Validator";
  }

  public List<Class<? extends Extension>> getExtensions() {
    List<Class<? extends Extension>> list = new ArrayList<Class<? extends Extension>>();



    return list;
  }

  public String getKey() {
    return KEY;
  }

  public String getName() {
    return "W3C Markup";
  }

  @Override
  public String toString() {
    return getKey();
  }
}
