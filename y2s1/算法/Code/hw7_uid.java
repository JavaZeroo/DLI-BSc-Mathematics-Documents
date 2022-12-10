import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class hw7_uid {
    public static void main(String[] args) {
        System.out.println("Use the graph from hw6");
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "c", 8);
        graph.addEdge("A", "D", 2);
        graph.addEdge("A", "E", 9);

        graph.addEdge("C", "B", 3);
        graph.addEdge("B", "C", 3);

        graph.addEdge("C", "D", 5);
        graph.addEdge("D", "C", 5);

        graph.addEdge("D", "E", 5);
        graph.addEdge("E", "D", 2);

        graph.addEdge("F", "B", 8);
        graph.addEdge("B", "F", 8);

        graph.addEdge("F", "C", 6);
        graph.addEdge("C", "F", 6);

        graph.addEdge("C", "G", 3);
        graph.addEdge("G", "C", 3);

        graph.addEdge("C", "H", 1);
        graph.addEdge("H", "C", 1);

        graph.addEdge("H", "D", 4);
        graph.addEdge("D", "H", 4);

        graph.addEdge("E", "H", 1);
        graph.addEdge("H", "E", 1);

        graph.addEdge("E", "I", 10);
        graph.addEdge("I", "E", 10);

        graph.addEdge("H", "I", 10);
        graph.addEdge("I", "H", 10);

        graph.addEdge("G", "H", 5);
        graph.addEdge("H", "G", 5);

        graph.addEdge("F", "G", 2);
        graph.addEdge("G", "F", 2);
        
        graph.addEdge("F", "J", 3);
        graph.addEdge("G", "J", 6);
        graph.addEdge("H", "J", 10);
        graph.addEdge("I", "J", 1);
        
        // graph.addEdge("A", "B", 1);
        // graph.addEdge("A", "D", 2);
        // graph.addEdge("B", "C", 2);
        // graph.addEdge("C", "D", 2);
        // graph.addEdge("C", "E", 8);
        // graph.addEdge("D", "E", 3);
        System.out.println("---Kruskal---");
        for (Edge edge : graph.kruskal()) {
            System.out.println(edge.toString());
        }
        System.out.println("---Prim---");
        for (Edge edge : graph.prim("D")) {
            System.out.println(edge.toString());
        }
    }
}

class Vertex {
    private String label;
    private List<Edge> edgeList;

    public Vertex(String label) {
        this.label = label;
        edgeList = new LinkedList<>();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        Vertex other = (Vertex) o;
        result = label.equals(other.getLabel());
        return result;
    }

    public Iterator<Edge> getEdgeIterator() {
        return edgeList.iterator();
    }

    public void connect(Vertex endVertex, int weight) {
        Iterator<Edge> iterator = getEdgeIterator();
        Edge edge = null;
        Vertex vertex = null;
        while (iterator.hasNext()) {
            edge = iterator.next();
            vertex = edge.getEndVertex();
            if (vertex.equals(endVertex)) {
                edge.setWeight(weight);
            }
        }
        edge = new Edge(this, endVertex, weight);
        edgeList.add(edge);
    }

    public Edge hasNeighbourVertex(Vertex endVertex) {
        Iterator<Edge> iterator = getEdgeIterator();
        Edge edge = null;
        Vertex vertex = null;
        while (iterator.hasNext()) {
            edge = iterator.next();
            vertex = edge.getEndVertex();
            if (vertex.equals(endVertex)) {
                return edge;
            }
        }
        return null;
    }
}

class Edge {
    private Vertex beginVertex;
    private Vertex endVertex;
    private int weight;

    public Edge(Vertex beginVertex, Vertex endVertex, int weight) {
        this.beginVertex = beginVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public Vertex getBeginVertex() {
        return beginVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "The edge is [ " + endVertex.getLabel() + " -> " + beginVertex.getLabel() + " ] which weight is "
                + weight;
    }

}

class Graph {
    private Map<String, Vertex> vertexMap;

    public Graph() {
        vertexMap = new LinkedHashMap<>();
    }

    Iterator<Vertex> getVertexIterator() {
        return vertexMap.values().iterator();
    }

    public void addVertex(String label) {
        Vertex vertex = vertexMap.get(label);
        vertex = new Vertex(label);
        vertexMap.put(label, vertex);
    }

    public Iterator<Edge> getEdgeIterator(String label) {
        Vertex vertex = vertexMap.get(label);
        return vertex.getEdgeIterator();
    }

    public void addEdge(String begin, String end, int weight) {
        Vertex beginVertex = vertexMap.get(begin);
        Vertex endVertex = vertexMap.get(end);
        beginVertex.connect(endVertex, weight);
        // endVertex.connect(beginVertex, weight);
    }

    public List<Edge> kruskal() {
        List<Edge> result = new LinkedList<>();
        HashMap<Vertex, Vertex> map = new HashMap<>();
        Iterator<Vertex> vertexIterator = getVertexIterator();
        ArrayList<Edge> list = new ArrayList<>();
        Vertex vertex;
        Edge edge;
        while (vertexIterator.hasNext()) {
            vertex = vertexIterator.next();
            map.put(vertex, vertex);
            Iterator<Edge> edgeIterator = vertex.getEdgeIterator();
            while (edgeIterator.hasNext()) {
                edge = edgeIterator.next();
                list.add(edge);
            }
        }
        Collections.sort(list, new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) {
                return (int) (edge1.getWeight() - edge2.getWeight());
            }
        });
        for (Edge now : list) {
            Vertex begin = now.getBeginVertex();
            Vertex end = now.getEndVertex();
            Vertex beginRoot = getRootVertex(map, begin);
            Vertex endRoot = getRootVertex(map, end);
            if (beginRoot.equals(endRoot)) {
                continue;
            } else {
                result.add(now);
                map.put(endRoot, beginRoot);
            }
        }
        return result;
    }

    public Vertex getRootVertex(HashMap<Vertex, Vertex> map, Vertex vertex) {
        while (true) {
            Vertex parent = map.get(vertex);
            if (parent.equals(vertex)) {
                return vertex;
            } else {
                vertex = parent;
            }
        }
    }

    public List<Edge> prim(String first) {
        List<Edge> result = new LinkedList<>();
        HashMap<Vertex, Edge> map = new HashMap<>();
        Iterator<Vertex> vertexIterator = getVertexIterator();
        Vertex vertex;
        Edge edge;
        while (vertexIterator.hasNext()) {
            vertex = vertexIterator.next();
            edge = new Edge(vertex, vertex, Integer.MAX_VALUE);
            map.put(vertex, edge);
        }
        vertex = vertexMap.get(first);
        while (!map.isEmpty()) {
            edge = map.get(vertex);
            map.remove(vertex);
            result.add(edge);
            if (vertex.getLabel().equals(first)) {
                result.remove(edge);
            }
            Edge minEdge = new Edge(vertex, vertex, Integer.MAX_VALUE);
            for (Vertex now : map.keySet()) {
                edge = map.get(now);
                Edge newEdge = now.hasNeighbourVertex(vertex);
                if (newEdge != null && newEdge.getWeight() < edge.getWeight()) {
                    edge = newEdge;
                    map.put(now, edge);
                }
                if (edge.getWeight() < minEdge.getWeight()) {
                    minEdge = edge;
                }
            }
            vertex = minEdge.getBeginVertex();
        }
        return result;
    }
}