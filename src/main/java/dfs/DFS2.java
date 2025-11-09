package dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DFS2 {
    public static List<List<Integer>> readInput(Scanner s) {
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> adjacencyList;
        String line;
        String[] stripLine;
        while (s.hasNextLine()) {
            line = s.nextLine();
            stripLine = line.split("=");
            adjacencyList = new ArrayList<>();
            for (String e : stripLine[1].split(",")) {
                adjacencyList.add(Integer.parseInt(e));
            }
            graph.add(adjacencyList);
        }

        return graph;
    }

    public static List<Node> readInputToGraph(Scanner s) {
        List<Node> graph = new ArrayList<>();
        Node aNode;
        Node adjacentNode;
        String line;
        String[] stripLine;
        int idx;
        while (s.hasNextLine()) {
            line = s.nextLine();
            stripLine = line.split("=");
            aNode = new Node(Integer.parseInt(stripLine[0]));

            if(!(graph.contains(aNode))) {
                graph.add(aNode);
            }
            idx = graph.indexOf(aNode);
            for (String e : stripLine[1].split(",")) {
                adjacentNode = new Node(Integer.parseInt(e));

                if(!(graph.contains(adjacentNode))) {
                    graph.add(adjacentNode);
                } else {
                    adjacentNode = graph.get(graph.indexOf(adjacentNode));
                }
                
                graph.get(idx).addAdjacentNode(adjacentNode);
            }
        }

        return graph;
    }

    public static List<Integer> depthFirstSearch(List<List<Integer>> graph) {
        List<Integer> traversed = new ArrayList<>();
        if (graph != null && !graph.isEmpty()) {
            Stack<Integer> toVisit = new Stack<>();
            List<Integer> adjacencyListCurrentNode = new ArrayList<>();
            int nodeToCheck, indexAdjacencyList, sizeAdjacencyList;
            boolean checkNextElement;

            traversed.add(0);
            toVisit.push(0);
            while (!(toVisit.isEmpty())) { //elems on the stack toVisit
                adjacencyListCurrentNode = graph.get(toVisit.peek());
                checkNextElement = true;
                sizeAdjacencyList = adjacencyListCurrentNode.size();
                indexAdjacencyList = 0;

                while (checkNextElement && (indexAdjacencyList < sizeAdjacencyList)) { // traverse adjacency list looking for next node not in solution
                    nodeToCheck = adjacencyListCurrentNode.get(indexAdjacencyList);
                    if (traversed.contains(nodeToCheck)) {
                        indexAdjacencyList++;
                    } else {
                        traversed.add(nodeToCheck);
                        toVisit.add(nodeToCheck);

                        checkNextElement = !checkNextElement;
                    }
                }

                if (indexAdjacencyList == sizeAdjacencyList) { // node fully visited
                    toVisit.pop();
                }
            }
        }

        return traversed;
    }

    public static List<Node> depthFirstSearchNodes(List<Node> graph) {
        List<Node> traversed = new ArrayList<>();
        if (graph != null && !graph.isEmpty()) {
            Stack<Node> toVisit = new Stack<>();
            List<Node> adjacencyListCurrentNode = new ArrayList<>();
            Node nodeToCheck; 
            int indexAdjacencyList, sizeAdjacencyList;
            boolean checkNextElement;

            traversed.add(graph.get(0));
            toVisit.push(graph.get(0));
            while (!(toVisit.isEmpty())) { //elems on the stack toVisit
                adjacencyListCurrentNode = graph.get(graph.indexOf(toVisit.peek())).getAdjacencyList();
                checkNextElement = true;
                sizeAdjacencyList = adjacencyListCurrentNode.size();
                indexAdjacencyList = 0;

                while (checkNextElement && (indexAdjacencyList < sizeAdjacencyList)) { // traverse adjacency list looking for next node not in solution
                    nodeToCheck = adjacencyListCurrentNode.get(indexAdjacencyList);
                    if (traversed.contains(nodeToCheck)) {
                        indexAdjacencyList++;
                    } else {
                        traversed.add(nodeToCheck);
                        toVisit.add(nodeToCheck);

                        checkNextElement = !checkNextElement;
                    }
                }

                if (indexAdjacencyList == sizeAdjacencyList) { // node fully visited
                    toVisit.pop();
                }
            }
        }

        return traversed;
    }

    public static void main(String[] args) {
        //List<List<Integer>> graph;
        List<Node> graphNodes;
        //List<Integer> solution;
        List<Node> solutionNodes;
        String toPrint = "";
        try (
            Scanner s = new Scanner(new File("dfsA.txt"));
        ) {
            //graph = readInput(s);
            graphNodes = readInputToGraph(s);
            //solution = depthFirstSearch(graph);

            for (Node e : graphNodes) {
                System.out.print(e.getId() + ": ");
                for (Node adj : e.getAdjacencyList()) {
                    System.out.print(adj.getId() + ", ");
                }
                System.out.println();
            }

            solutionNodes = depthFirstSearchNodes(graphNodes);

            //System.out.println(solution);

            toPrint += "[";
            for (Node e : solutionNodes) {
                toPrint += Integer.toString(e.getId()) + ", ";
            }
            toPrint =  toPrint.substring(0, toPrint.lastIndexOf(",")) + "]";
            System.out.println(toPrint);
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
    }
}

class Node implements Comparable<Node>{
    private static int VALUE_EQUALS = 0;
    private static int VALUE_LOWER = -1;
    private static int VALUE_GREATER = 1;

    private int id;
    private List<Node> adjacencyList;

    public Node(int id) {
        this.id = id;
        adjacencyList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Node> getAdjacencyList() {
        return adjacencyList;
    }

    public void addAdjacentNode(Node node) {
        if (node.getId() != id) {
            if (adjacencyList.isEmpty()) {
                adjacencyList.add(node);
            } else if (!(adjacencyList.contains(node))) {
                adjacencyList.add(node);
            }
        }
    }

    @Override
    public int compareTo(Node o) {
        if (o == null || !(o instanceof Node)) {
            return VALUE_EQUALS;
        }
        Node otherNode = (Node)o;

        if (id == otherNode.getId()) {
            return VALUE_EQUALS;
        }

        else if (id > otherNode.getId()) {
            return VALUE_GREATER;
        }

        else {
            return VALUE_LOWER;
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
        if (getClass() != o.getClass()) {
            return false;
        }

        Node otherNode = (Node)o;
        return id == otherNode.getId();
    }


}
