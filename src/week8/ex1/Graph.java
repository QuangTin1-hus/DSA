package week8.ex1;

import javax.management.InvalidAttributeValueException;
import java.util.*;

public class Graph {
    private int vertices;
    private int edges;
    private int[][] graph;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.graph = new int[vertices][vertices];
    }

    public void insertEdge(int start, int end) {
        checkOutOfBoundIndex(start, end);
        graph[start][end] = 1;
        graph[end][start] = 1;
        edges++;
    }

    public void insertVertex() {
        vertices++;
        int i;

        for (i = 0; i < vertices; ++i) {
            graph[i][vertices - 1] = 0;
            graph[vertices - 1][i] = 0;
        }
    }

    public void removeVertex(int vertex){
        checkOutOfBound(vertex);
        int i;
        while (vertex < vertices) {
            for (i = 0; i < vertices; ++i) graph[i][vertex] = graph[i][vertex + 1];
            for (i = 0; i < vertices; ++i) graph[vertex][i] = graph[vertex + 1][i];
            vertex++;
        }
        // decreasing the number of vertices
        vertices--;
    }

    public int numVertices() {
        return vertices;
    }

    public int numEdges() {
        return edges;
    }

    public Map<Integer, Integer> incomingEdges(int vertex) {
        Map<Integer, Integer> imcoimg = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            if (graph[i][vertex] == 1) {
                imcoimg.put(i, vertex);
            }
        }
        return imcoimg;
    }

    public Map<Integer, Integer> outcomingEdges(int vertex) {
        Map<Integer, Integer> outcoming = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            if (graph[vertex][i] == 1) outcoming.put(vertex, i);
        }
        return outcoming;
    }

    public int opposite(int vertex, int[] edges) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[vertex][i] == 1 && i != edges[0]) {
                return i;
            }
        }
        return -1;
    }

    public int[] getEdge(int u, int v) {
        checkOutOfBoundIndex(u, v);
        if (graph[u][v] == graph[v][u] && graph[u][v] == 1) {
            try {
                return new int[]{u, v};
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int[] endVertices(int edge) {
        int[] vertices = new int[2];
        int limit = 0;
        for (int i = 0; i < graph.length && limit < 2; i++) {
            if (graph[edge][i] == 1) {
                vertices[limit++] = i;
            }
        }
        return vertices;
    }

    public Set<Integer> vertices() {
        Set<Integer> list = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == 1) list.add(i);
            }
        }
        return list;
    }

    public void checkOutOfBoundIndex(int i, int j) {
        if (i < 0 || i > graph.length || j < 0 || j > graph.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void checkOutOfBound(int i) {
        if (i < 0 || i > vertices) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        for (int i = 0; i < vertices; i++) {
            sb.append(i+1).append("\t");
        }
        sb.append("\n");
        for (int i = 0; i < vertices; i++) {
            sb.append(i + 1).append("\t");
            for (int j = 0; j < vertices; j++) {
                sb.append(graph[i][j]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
