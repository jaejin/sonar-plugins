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

/**
 * 
 */
package org.sonar.plugins.php.codesniffer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sonar.api.rules.RulePriority;

/**
 * @author akram
 * 
 */
public class PhpCodeSnifferPriorityMapperTest {

  /**
   * Test method for {@link org.sonar.plugins.php.checkstyle.PhpCodeSnifferPriorityMapper#from(java.lang.String)}.
   */
  @Test
  public void testFromWithRegularValues() {
    PhpCodeSnifferPriorityMapper mapper = new PhpCodeSnifferPriorityMapper();
    assertEquals(RulePriority.BLOCKER, mapper.from("error"));
    assertEquals(RulePriority.MAJOR, mapper.from("warning"));
    assertEquals(RulePriority.INFO, mapper.from("info"));
  }

  /**
   * Test method for {@link org.sonar.plugins.php.checkstyle.PhpCodeSnifferPriorityMapper#from(java.lang.String)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFromWithUnknownInput() {
    PhpCodeSnifferPriorityMapper mapper = new PhpCodeSnifferPriorityMapper();
    mapper.from("RANDOM");
  }

  /**
   * Test method for {@link org.sonar.plugins.php.checkstyle.PhpCodeSnifferPriorityMapper#from(java.lang.String)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFromWithNullValue() {
    PhpCodeSnifferPriorityMapper mapper = new PhpCodeSnifferPriorityMapper();
    mapper.from(null);
  }

  /**
   * Test method for {@link org.sonar.plugins.php.checkstyle.PhpCodeSnifferPriorityMapper#to(org.sonar.api.rules.RulePriority)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTo() {
    PhpCodeSnifferPriorityMapper mapper = new PhpCodeSnifferPriorityMapper();
    assertEquals("info", mapper.to(RulePriority.INFO));
    assertEquals("info", mapper.to(RulePriority.MINOR));
    assertEquals("warning", mapper.to(RulePriority.MAJOR));
    assertEquals("error", mapper.to(RulePriority.BLOCKER));
    assertEquals("error", mapper.to(RulePriority.CRITICAL));
    assertEquals(null, mapper.to(null));
  }
}
