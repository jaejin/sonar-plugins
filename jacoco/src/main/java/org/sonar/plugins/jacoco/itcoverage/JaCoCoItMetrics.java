/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2010 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
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

package org.sonar.plugins.jacoco.itcoverage;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;
import org.sonar.api.measures.SumChildValuesFormula;

import java.util.Arrays;
import java.util.List;

/**
 * Should be in {@link org.sonar.api.measures.CoreMetrics}
 * 
 * @author Evgeny Mandrikov
 */
public class JaCoCoItMetrics implements Metrics {

  public static final String DOMAIN_IT_TESTS = "IT Tests";

  public static final String IT_COVERAGE_KEY = "it_coverage";
  public static final Metric IT_COVERAGE = new Metric(IT_COVERAGE_KEY, "Coverage", "Coverage by unit tests", Metric.ValueType.PERCENT,
      Metric.DIRECTION_BETTER, true, DOMAIN_IT_TESTS).setWorstValue(0.0).setBestValue(100.0);

  public static final String IT_LINES_TO_COVER_KEY = "it_lines_to_cover";
  public static final Metric IT_LINES_TO_COVER = new Metric(IT_LINES_TO_COVER_KEY, "Lines to cover", "Lines to cover", Metric.ValueType.INT,
      Metric.DIRECTION_BETTER, false, DOMAIN_IT_TESTS).setFormula(new SumChildValuesFormula(false)).setHidden(true);

  public static final String IT_UNCOVERED_LINES_KEY = "it_uncovered_lines";
  public static final Metric IT_UNCOVERED_LINES = new Metric(IT_UNCOVERED_LINES_KEY, "Uncovered lines", "Uncovered lines", Metric.ValueType.INT,
      Metric.DIRECTION_WORST, false, DOMAIN_IT_TESTS).setFormula(new SumChildValuesFormula(false));

  public static final String IT_LINE_COVERAGE_KEY = "it_line_coverage";
  public static final Metric IT_LINE_COVERAGE = new Metric(IT_LINE_COVERAGE_KEY, "Line coverage", "Line coverage", Metric.ValueType.PERCENT,
      Metric.DIRECTION_BETTER, true, DOMAIN_IT_TESTS);

  public static final String IT_COVERAGE_LINE_HITS_DATA_KEY = "it_coverage_line_hits_data";
  public static final Metric IT_COVERAGE_LINE_HITS_DATA = new Metric(IT_COVERAGE_LINE_HITS_DATA_KEY, "Coverage hits data", "Code coverage line hits data", Metric.ValueType.DATA,
      Metric.DIRECTION_NONE, false, DOMAIN_IT_TESTS);

  public static final String IT_CONDITIONS_TO_COVER_KEY = "it_conditions_to_cover";
  public static final Metric IT_CONDITIONS_TO_COVER = new Metric(IT_CONDITIONS_TO_COVER_KEY, "Conditions to cover", "Conditions to cover",
      Metric.ValueType.INT, Metric.DIRECTION_BETTER, false, DOMAIN_IT_TESTS).setFormula(new SumChildValuesFormula(false)).setHidden(true);

  public static final String IT_UNCOVERED_CONDITIONS_KEY = "it_uncovered_conditions";
  public static final Metric IT_UNCOVERED_CONDITIONS = new Metric(IT_UNCOVERED_CONDITIONS_KEY, "Uncovered conditions", "Uncovered conditions",
      Metric.ValueType.INT, Metric.DIRECTION_WORST, false, DOMAIN_IT_TESTS).setFormula(new SumChildValuesFormula(false));

  public static final String IT_BRANCH_COVERAGE_KEY = "it_branch_coverage";
  public static final Metric IT_BRANCH_COVERAGE = new Metric(IT_BRANCH_COVERAGE_KEY, "Branch coverage", "Branch coverage",
      Metric.ValueType.PERCENT, Metric.DIRECTION_BETTER, true, DOMAIN_IT_TESTS).setWorstValue(0.0).setBestValue(100.0);

  public static final String IT_BRANCH_COVERAGE_HITS_DATA_KEY = "it_branch_coverage_hits_data";
  public static final Metric IT_BRANCH_COVERAGE_HITS_DATA = new Metric(IT_BRANCH_COVERAGE_HITS_DATA_KEY, "Branch coverage hits",
      "Branch coverage hits", Metric.ValueType.DATA, Metric.DIRECTION_NONE, false, DOMAIN_IT_TESTS);

  public List<Metric> getMetrics() {
    return Arrays.asList(IT_COVERAGE, IT_LINES_TO_COVER, IT_UNCOVERED_LINES, IT_LINE_COVERAGE, IT_COVERAGE_LINE_HITS_DATA, IT_CONDITIONS_TO_COVER, IT_UNCOVERED_CONDITIONS, IT_BRANCH_COVERAGE, IT_BRANCH_COVERAGE_HITS_DATA);
  }

}