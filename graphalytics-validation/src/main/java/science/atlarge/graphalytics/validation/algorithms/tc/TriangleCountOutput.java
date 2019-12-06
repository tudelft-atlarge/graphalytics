/*
 * Copyright 2015 - 2017 Atlarge Research Team,
 * operating at Technische Universiteit Delft
 * and Vrije Universiteit Amsterdam, the Netherlands.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package science.atlarge.graphalytics.validation.algorithms.tc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TriangleCountOutput {

	private final Map<Long, Long> triangleCounts;

	public TriangleCountOutput(Map<Long, Long> triangleCounts) {
		this.triangleCounts = new HashMap<>(triangleCounts);
	}

	public Set<Long> getVertices() {
		return triangleCounts.keySet();
	}

	public long getTriangleCountForVertex(long vertexId) {
		return triangleCounts.get(vertexId);
	}

}
