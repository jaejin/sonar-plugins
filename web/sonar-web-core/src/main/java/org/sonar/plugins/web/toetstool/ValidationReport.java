/*
 * Copyright (C) 2010 Matthijs Galesloot
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

package org.sonar.plugins.web.toetstool;

import java.io.File;

/**
 * @author Matthijs Galesloot
 * @since 0.2
 */
public final class ValidationReport {

  public static File reportFile(File file) {
    return new File(file.getParentFile().getPath() + "/" + file.getName() + "-report.xml");
  }

  private ValidationReport() {
    // utility class, cannot instantiate
  }
}
