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

import org.junit.Test;
import science.atlarge.graphalytics.validation.GraphStructure;
import science.atlarge.graphalytics.validation.GraphValues;
import science.atlarge.graphalytics.validation.io.DoubleParser;
import science.atlarge.graphalytics.validation.io.GraphParser;
import science.atlarge.graphalytics.validation.io.LongParser;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public abstract class TriangleCountValidationTest {

	public abstract TriangleCountOutput executeDirectedTriangleCount(
			GraphStructure graph) throws Exception;

	public abstract TriangleCountOutput executeUndirectedTriangleCount(
			GraphStructure graph) throws Exception;

	@Test
	public final void testDirectedTriangleCountOnValidationGraph() throws Exception {
		final String inputPath = "/validation-graphs/tc/dir-input";
		final String graphOutputPath = "/validation-graphs/tc/dir-output";

		GraphStructure inputGraph = GraphParser.parseGraphStructureFromVertexBasedDataset(
				getClass().getResourceAsStream(inputPath), true);

		TriangleCountOutput executionResult = executeDirectedTriangleCount(inputGraph);

		validateTriangleCount(executionResult, graphOutputPath);
	}

	@Test
	public final void testUndirectedTriangleCountOnValidationGraph() throws Exception {
		final String inputPath = "/validation-graphs/tc/undir-input";
		final String graphOutputPath = "/validation-graphs/tc/undir-output";

		GraphStructure inputGraph = GraphParser.parseGraphStructureFromVertexBasedDataset(
				getClass().getResourceAsStream(inputPath), false);

		TriangleCountOutput executionResult = executeUndirectedTriangleCount(inputGraph);

		validateTriangleCount(executionResult, graphOutputPath);
	}

	@Test
	public final void testDirectedTriangleCountOnExampleGraph() throws Exception {
		final String inputPath = "/validation-graphs/example/example-directed-input";
		final String outputPath = "/validation-graphs/example/example-directed-TC";

		GraphStructure inputGraph = GraphParser.parseGraphStructureFromVertexBasedDataset(
				getClass().getResourceAsStream(inputPath), true);

		TriangleCountOutput executionResult = executeDirectedTriangleCount(inputGraph);

		validateTriangleCount(executionResult, outputPath);
	}

	@Test
	public final void testUndirectedTriangleCountOnExampleGraph() throws Exception {
		final String inputPath = "/validation-graphs/example/example-undirected-input";
		final String outputPath = "/validation-graphs/example/example-undirected-TC";

		GraphStructure inputGraph = GraphParser.parseGraphStructureFromVertexBasedDataset(
				getClass().getResourceAsStream(inputPath), true);

		TriangleCountOutput executionResult = executeUndirectedTriangleCount(inputGraph);

		validateTriangleCount(executionResult, outputPath);
	}

	private void validateTriangleCount(TriangleCountOutput executionResult,
								String graphOutputPath) throws IOException {
		GraphValues<Long> outputGraph = GraphParser.parseGraphValuesFromDataset(
				getClass().getResourceAsStream(graphOutputPath), new LongParser());

		assertThat("result graph has the correct number of vertices",
				executionResult.getVertices(), hasSize(outputGraph.getVertices().size()));
		assertThat("result graph has the expected vertex ids",
				executionResult.getVertices(), containsInAnyOrder(outputGraph.getVertices().toArray()));
		for (long vertexId : outputGraph.getVertices()) {
			assertThat("vertex " + vertexId + " has correct value",
					executionResult.getTriangleCountForVertex(vertexId),
					is(equalTo(outputGraph.getVertexValue(vertexId))));
		}
	}

}
