package dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstSearch {
    private static List<List<Integer>> readInput(Scanner s) {
        List<List<Integer>> graph = new ArrayList<>();
        String line;
        String[] infoSplit;
        while(s.hasNextLine()){
            List<Integer> newNodeAdjacentList = new ArrayList<>();

            line = s.nextLine();
            infoSplit = line.split("=");
            infoSplit = infoSplit[1].split(",");
            for (String e: infoSplit) {
                newNodeAdjacentList.add(Integer.parseInt(e));
            }
            graph.add(newNodeAdjacentList);
        }

        return graph;
    }


    // Non directed graph
    public static List<Integer> depthFirstSearch(List<List<Integer>> graph) {
        List<Integer> sol = new ArrayList<>();
        if (graph != null && !(graph.isEmpty())) {
            List<Integer> adjacents;
            Stack<Integer> traverseStack = new Stack<>();
            boolean stay;
            int indexAdjacents, adjacentsSize;
            int nextNode;

            traverseStack.push(0);
            sol.add(0);

            while(!traverseStack.isEmpty()) {
                adjacents = graph.get(traverseStack.peek());
                if (!adjacents.isEmpty()) {
                    stay = true;
                    indexAdjacents = 0;
                    adjacentsSize = adjacents.size();
                    while (stay && (indexAdjacents < adjacentsSize)) {
                        nextNode = adjacents.get(indexAdjacents);
                        if (!sol.contains(nextNode)) {
                            sol.add(nextNode);
                            traverseStack.add(nextNode);
                            stay = !stay;
                        } else {
                            indexAdjacents++;
                        }
                    }

                    if (indexAdjacents == adjacentsSize){
                        traverseStack.pop();
                    }
                }
            }            
        }

        return sol;
    }

    public static void main(String[] args) {
        List<List<Integer>> graph;
        List<Integer> traverseDFS;
        try (
            Scanner s = new Scanner(new File("dfsB.txt"));
        ) {
            graph = readInput(s);
            System.out.println(graph);
            traverseDFS = depthFirstSearch(graph);
            System.out.println("Traverse path: " + traverseDFS);

        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
    }
}
