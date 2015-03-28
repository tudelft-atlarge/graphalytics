package nl.tudelft.graphalytics.validation.io;

import nl.tudelft.graphalytics.validation.GraphStructure;
import nl.tudelft.graphalytics.validation.GraphValues;
import nl.tudelft.graphalytics.validation.io.GraphValueParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility class for parsing graphs for the purpose of testing algorithm implementations.
 *
 * @author Tim Hegeman
 */
public class GraphParser {

	public static GraphStructure parseGraphStructureFromVertexBasedDataset(InputStream dataset, boolean directed)
			throws IOException {
		try (BufferedReader datasetReader = new BufferedReader(new InputStreamReader(dataset))) {
			Map<Long, Set<Long>> edges = new HashMap<>();
			for (String line = datasetReader.readLine(); line != null; line = datasetReader.readLine()) {
				line = line.trim();
				if (line.isEmpty() || line.startsWith("#")) {
					continue;
				}

				String tokens[] = line.split(" ");
				long sourceVertex = Long.parseLong(tokens[0]);
				edges.put(sourceVertex, new HashSet<Long>());

				for (int i = 1; i < tokens.length; i++) {
					long destinationVertex = Long.parseLong(tokens[i]);
					if (!edges.containsKey(destinationVertex)) {
						edges.put(destinationVertex, new HashSet<Long>());
					}

					edges.get(sourceVertex).add(destinationVertex);
					if (!directed) {
						edges.get(destinationVertex).add(sourceVertex);
					}
				}
			}
			return new GraphStructure(edges);
		}
	}

	public static <ValueType> GraphValues<ValueType> parseGraphValuesFromDataset(
			InputStream dataset, GraphValueParser<ValueType> valueParser) throws IOException {
		try (BufferedReader datasetReader = new BufferedReader(new InputStreamReader(dataset))) {
			Map<Long, ValueType> values = new HashMap<>();
			for (String line = datasetReader.readLine(); line != null; line = datasetReader.readLine()) {
				line = line.trim();
				if (line.isEmpty() || line.startsWith("#")) {
					continue;
				}

				String tokens[] = line.split(" ", 2);
				long vertexId = Long.parseLong(tokens[0]);
				ValueType vertexValue = valueParser.parseValue(tokens[1]);
				values.put(vertexId, vertexValue);
			}
			return new GraphValues<>(values);
		}
	}

}