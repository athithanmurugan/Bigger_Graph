package graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int numVertices;
    private int numEdges;
    private ArrayList<Integer>[] adjList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.numEdges = 0;
        adjList = new ArrayList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
    }


    public void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u);
        numEdges++;
    }

    public void removeEdge(int u, int v) {
        adjList[u].remove((Integer)v);
        adjList[v].remove((Integer)u);
        numEdges--;
    }

    public boolean hasEdge(int u, int v) {
        return adjList[u].contains(v);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public int getDegree(int v) {
        return adjList[v].size();
    }

    public int getMinDegree() {
        int minDegree = Integer.MAX_VALUE;
        for (int v = 0; v < numVertices; v++) {
            int degree = getDegree(v);
            if (degree < minDegree) {
                minDegree = degree;
            }
        }
        return minDegree;
    }

    public int getMaxDegree() {
        int maxDegree = Integer.MIN_VALUE;
        for (int v = 0; v < numVertices; v++) {
            int degree = getDegree(v);
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }

    public double getAvgDegree() {
        return (double) numEdges / numVertices;
    }

    public int getShortestPath(int source, int dest) {
        int[] dist = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numVertices; i++) {
            dist[i] = -1;
            visited[i] = false;
        }
        dist[source] = 0;
        visited[source] = true;
        queue.offer(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adjList[u]) {
                if (!visited[v]) {
                    dist[v] = dist[u] + 1;
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
        return dist[dest];
    }
}