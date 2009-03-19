/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2009 SonarSource SA
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
package org.codehaus.javancss.metrics;

import org.codehaus.javancss.Resource;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class PackageCounter extends ASTVisitor {

	public void beginTree(DetailAST ast) {
		Resource packageRes;
		if (ast.getType() != TokenTypes.PACKAGE_DEF) {
			packageRes = new Resource("[default]", Resource.Type.PACKAGE);
		} else {
			String packageName = FullIdent.createFullIdent(ast.getLastChild().getPreviousSibling()).getText();
			packageRes = new Resource(packageName, Resource.Type.PACKAGE);
		}
		if (!packageRes.equals(resourceTree.peek())) {
			if (resourceTree.peek().getType().equals(Resource.Type.PACKAGE)) {
				resourceTree.pop();
			}
			resourceTree.addChild(packageRes);
		}
	}
}
