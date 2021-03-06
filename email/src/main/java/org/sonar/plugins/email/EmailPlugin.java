/*
 * Sonar Email Plugin
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

package org.sonar.plugins.email;

import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;

import java.util.Arrays;
import java.util.List;

/**
 * @author Evgeny Mandrikov
 */
@Properties({
    @Property(
        key = EmailPublisher.ENABLED_PROPERTY,
        name = "Enabled",
        defaultValue = EmailPublisher.ENABLED_DEFAULT_VALUE + "",
        global = true, project = true, module = false),
    @Property(
        key = EmailPublisher.HOST_PROPERTY,
        name = "SMTP host",
        defaultValue = EmailPublisher.SMTP_HOST_DEFAULT_VALUE,
        global = true, project = false, module = false),
    @Property(
        key = EmailPublisher.PORT_PROPERTY,
        name = "SMTP port",
        defaultValue = EmailPublisher.PORT_DEFAULT_VALUE,
        global = true, project = false, module = false),
    @Property(
        key = EmailPublisher.TLS_PROPERTY,
        name = "TLS",
        defaultValue = EmailPublisher.TLS_DEFAULT_VALUE + "",
        global = true, project = false, module = false),
    @Property(
        key = EmailPublisher.USERNAME_PROPERTY,
        name = "SMTP username",
        global = true, project = false, module = false),
    @Property(
        key = EmailPublisher.PASSWORD_PROPERTY,
        name = "SMTP password",
        global = true, project = false, module = false),
    @Property(
        key = EmailPublisher.FROM_PROPERTY,
        name = "From",
        global = true, project = false, module = false),
    @Property(
        key = EmailPublisher.TO_PROPERTY,
        name = "To",
        global = true, project = true, module = false) })
public class EmailPlugin implements Plugin {

  public String getKey() {
    return "email";
  }

  public String getName() {
    return "Email";
  }

  public String getDescription() {
    return "Email";
  }

  public List getExtensions() {
    return Arrays.asList(EmailPublisher.class);
  }

}
