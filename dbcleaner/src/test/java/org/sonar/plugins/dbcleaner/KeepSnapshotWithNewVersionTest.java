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
package org.sonar.plugins.dbcleaner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.sonar.plugins.dbcleaner.Utils.createSnapshot;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.sonar.api.database.model.Snapshot;
import org.sonar.plugins.dbcleaner.KeepSnapshotWithNewVersion;

public class KeepSnapshotWithNewVersionTest {

  @Test
  public void testFilter() {
    List<Snapshot> snapshots = new LinkedList<Snapshot>();
    snapshots.add(createSnapshot(1, "0.1"));
    snapshots.add(createSnapshot(2, "0.1"));
    snapshots.add(createSnapshot(3, "0.2"));
    snapshots.add(createSnapshot(4, "0.2"));
    snapshots.add(createSnapshot(5, "0.3"));

    assertThat(new KeepSnapshotWithNewVersion().filter(snapshots), is(3));
    assertThat(snapshots.size(), is(2));
    assertThat(snapshots.get(0).getId(), is(2));
    assertThat(snapshots.get(1).getId(), is(4));
  }
}