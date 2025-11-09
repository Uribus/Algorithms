package dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DFS3 {

    private static List<List<Integer>> readInputGraph(Scanner s) {
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> adjacencyList;
        String line;
        String[] lineData;
        while (s.hasNextLine()) {
            adjacencyList = new ArrayList<>();
            line = s.nextLine();
            lineData = line.split("=");
            for (String e : lineData[1].split(",")) {
                adjacencyList.add(Integer.parseInt(e));
            }
            graph.add(adjacencyList);
        }
        return graph;
    }

    private static List<Integer> depthFirstSearch(List<List<Integer>> graph) {
        List<Integer> nodesTraversed = new ArrayList<>();

        if ((graph != null) && !(graph.isEmpty())) {
            Stack<Integer> nodesToVisit = new Stack<>();
            List<Integer> adjacencyListCurrentNode;
            int adjListPointer, adjListSize, currentNodeCheck;
            boolean keepIterate;

            nodesTraversed.add(0);
            nodesToVisit.add(0);

            while (!(nodesToVisit.isEmpty())) {
                adjacencyListCurrentNode = graph.get(nodesToVisit.peek());
                adjListSize = adjacencyListCurrentNode.size();
                adjListPointer = 0;
                keepIterate = true;
                while (keepIterate && (adjListPointer < adjListSize)) {
                    currentNodeCheck = adjacencyListCurrentNode.get(adjListPointer);
                    if (nodesTraversed.contains(currentNodeCheck)) {
                        adjListPointer++;
                    } else {
                        nodesTraversed.add(currentNodeCheck);
                        nodesToVisit.add(currentNodeCheck);
                        keepIterate = !keepIterate;
                    }
                }

                if (adjListPointer == adjListSize) {
                    nodesToVisit.pop();
                }

            }
        }

        return nodesTraversed;
    }
    public static void main(String[] args){
        List<List<Integer>> graph;
        List<Integer> nodePath;
        try (
            Scanner s = new Scanner(new File("dfsA.txt"));
        ) { 
            graph = readInputGraph(s);
            System.out.println(graph);
            nodePath = depthFirstSearch(graph);
            System.out.println(nodePath);
        } 
        catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
        // Scanner to read
        // call method read input file
        // call method dfs
    }
}