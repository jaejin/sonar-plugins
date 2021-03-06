/*
 * Sonar Sonargraph Plugin
 * Copyright (C) 2009, 2010, 2011 hello2morrow GmbH
 * mailto: info AT hello2morrow DOT com
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

package com.hello2morrow.sonarplugin;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public final class SonargraphPluginBase
{
  public static final String COST_PER_INDEX_POINT = "sonargraph.index_point_cost";
  public static final double COST_PER_INDEX_POINT_DEFAULT = 11;
  public static final String CURRENCY = "sonargraph.currency";
  public static final String CURRENCY_DEFAULT = "USD";

  public final static String PLUGIN_KEY = "Sonargraph";

  public final static String ARCH_RULE_KEY = "sonargraph.architecture";
  public final static String THRESHOLD_RULE_KEY = "sonargraph.threshold";
  public final static String DUPLICATE_RULE_KEY = "sonargraph.duplicate";
  public final static String CYCLE_GROUP_RULE_KEY = "sonargraph.cyclegroup";
  public final static String WORKSPACE_RULE_KEY = "sonargraph.workspace";
  public final static String TASK_RULE_KEY = "sonargraph.open_task";

  public static final DecimalFormat FLOAT_FORMAT = new DecimalFormat("###,##0.00");
  public static final DecimalFormat INTEGER_FORMAT = new DecimalFormat("###,##0");

  static
  {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator(',');
    symbols.setDecimalSeparator('.');
    FLOAT_FORMAT.setDecimalFormatSymbols(symbols);
    FLOAT_FORMAT.setParseIntegerOnly(false);
    INTEGER_FORMAT.setDecimalFormatSymbols(symbols);
    INTEGER_FORMAT.setParseIntegerOnly(true);
  }

  private SonargraphPluginBase()
  {
    // Don't instantiate
  }
}
