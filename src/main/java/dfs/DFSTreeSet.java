package dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class DFSTreeSet {
    
    private static TreeSet<Node> readInput(Scanner s) {
        TreeSet<Node> graph = new TreeSet<>();

        Node aNode, adjacentNode;
        String line;
        String[] lineData;

        while(s.hasNextLine()) {
            line = s.nextLine();
            lineData = line.split("=");

            aNode = new Node(Integer.parseInt(lineData[0]));

            if(!graph.contains(aNode)) {
                graph.add(aNode);
            } else {
                aNode = retrieveNode(graph, aNode);
            }

            for (String e : lineData[1].split(",")) {
                adjacentNode = new Node(Integer.parseInt(e));
                if(!graph.contains(adjacentNode)) {
                    graph.add(adjacentNode);
                } else {
                    adjacentNode = retrieveNode(graph, adjacentNode);
                }

                aNode.addAdjacentNode(adjacentNode);
            }
        }

        return graph;
    }

    private static Node retrieveNode(TreeSet<Node> graph, Node targetNode) {
        Node ceilNode = graph.ceiling(targetNode);
        Node flNode = graph.floor(targetNode);

        return ceilNode == flNode ? ceilNode : null;
    }

    private static List<Node> depthFirstSearch(TreeSet<Node> graph) {
        List<Node> result = new ArrayList<>();

        if (graph != null && !(graph.isEmpty())) {
            Stack<Node> nodesToVisit = new Stack<>();
            List<Node> adjacencyList;
            int indexAdjList, ajdListSize;
            boolean stay;

            Node currentNode = graph.first();
            Node candidateNode;

            result.add(currentNode);
            nodesToVisit.add(currentNode);

            while(!(nodesToVisit.isEmpty())) {
                currentNode = nodesToVisit.peek();
                adjacencyList = currentNode.getAdjacencyList();
                ajdListSize = adjacencyList.size();
                indexAdjList = 0;
                stay = true;
                while (stay && (indexAdjList < ajdListSize)) {
                    candidateNode = adjacencyList.get(indexAdjList);
                    if (result.contains(candidateNode)) {
                        indexAdjList++;
                    } else {
                        result.add(candidateNode);
                        nodesToVisit.add(candidateNode);
                        stay = !stay;
                    }
                }

                if (indexAdjList == ajdListSize) {
                    nodesToVisit.pop();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeSet<Node> graph;
        List<Node> solution;
        try (
            Scanner s = new Scanner(new File("dfsB.txt"));
        ) {
            graph = readInput(s);
            solution = depthFirstSearch(graph);

            String toPrint = "[";
            for (Node e : solution) {
                toPrint += e.getId() + ", ";
            }
            toPrint = toPrint.substring(0, toPrint.lastIndexOf(",")) + "]";
            System.out.println(toPrint);
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
    }
}

class Node implements Comparable<Node> {
    private static int EQUALS = 0;
    private static int LOWER = -1;
    private static int GREATER = 1;

    private int id;
    private List<Node> adjacentList;

    public Node(int id) {
        this.id = id;
        adjacentList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Node> getAdjacencyList() {
        return adjacentList;
    }

    public void addAdjacentNode(Node node) {
        if (!(adjacentList.contains(node))) {
            adjacentList.add(node);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Node obj) {
        if (obj == null || !(obj instanceof Node)) {
            return EQUALS;
        }
        Node otherNode = (Node)obj;
        
        if (otherNode.getId() == id) {
            return EQUALS;
        }   
        
        else if (otherNode.getId() < id) {
            return LOWER;
        }

        else {
            return GREATER;
        }
        
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (this == o) {
            return true;
        }

        if (o.getClass() != getClass()) {
            return false;
        }

        Node otherNode = (Node)o;
        // return Objects.equals(id, otherNode.getId()); PROB NOT WORKING WITH PRIMITIVE DATA TYPES, THOUGH CONSIDER STRINGS
        return otherNode.getId() == id;
    }
}
