package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String filePath = "/Users/athi_macbookair/Desktop/Al tp4/Wiki-Vote.txt";
		Graph wikiVoteGraph = loadGraphFromFile(filePath);
		Graph facebookGraph = loadGraphFromFile("/Users/athi_macbookair/Desktop/Al tp4/facebook_combined.txt");

		System.out.println("Metrics for wiki-vote graph:");
		System.out.println("Number of vertices: " + wikiVoteGraph.getNumVertices());
		System.out.println("Number of edges: " + wikiVoteGraph.getNumEdges());
		System.out.println("Minimum degree: " + wikiVoteGraph.getMinDegree());
		System.out.println("Maximum degree: " + wikiVoteGraph.getMaxDegree());
		System.out.println("Average degree: " + wikiVoteGraph.getAvgDegree());

		System.out.println();

		System.out.println("Metrics for facebook graph:");
		System.out.println("Number of vertices: " + facebookGraph.getNumVertices());
		System.out.println("Number of edges: " + facebookGraph.getNumEdges());
		System.out.println("Minimum degree: " + facebookGraph.getMinDegree());
		System.out.println("Maximum degree: " + facebookGraph.getMaxDegree());
		System.out.println("Average degree: " + facebookGraph.getAvgDegree());
	}

	private static Graph loadGraphFromFile(String filePath) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filePath));
		int numVertices = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.startsWith("#")) {
				continue;
			}
			String[] parts = line.split("\\s+");
			int u = Integer.parseInt(parts[0]);
			int v = Integer.parseInt(parts[1]);
			numVertices = Math.max(numVertices, Math.max(u, v));
		}
		scanner.close();

		Graph graph = new Graph(numVertices + 1);

		scanner = new Scanner(new File(filePath));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.startsWith("#")) {
				continue;
			}
			String[] parts = line.split("\\s+");
			int u = Integer.parseInt(parts[0]);
			int v = Integer.parseInt(parts[1]);
			graph.addEdge(u, v);
		}
		scanner.close();

		return graph;
	}
}
