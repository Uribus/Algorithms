package bfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BreadthFirstSearch {

    private static List<List<Integer>> readInput(Scanner s) {
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> adjList;
        String line;
        String[] stripLine;

        while (s.hasNextLine()) {
            line = s.nextLine();
            adjList = new ArrayList<>();
            stripLine = line.split("=");
            if (stripLine.length > 1) {
                for (String e : stripLine[1].split(",")) {
                    adjList.add(Integer.parseInt(e));
                }
            }

            graph.add(adjList);
        }

        return graph;
    }

    private static List<Integer> breadthFristSearch(List<List<Integer>> graph) {
        List<Integer> traverseList = new ArrayList<>();

        if (graph != null && !(graph.isEmpty())) {
            List<Integer> level = graph.get(0);
            traverseList.add(0);
            List<Integer> deeperLevel;
            while (!level.isEmpty()) {
                deeperLevel = new ArrayList<>();
                for (int node : level) {
                    if (!(traverseList.contains(node))) {
                        traverseList.add(node);
                        deeperLevel.addAll(graph.get(node));
                    }
                }
                level = deeperLevel;
            }
        }

        return traverseList;
    }
    public static void main(String[] args) {
        List<List<Integer>> graph;
        List<Integer> solution;

        try (
            Scanner s = new Scanner(new File("graphC.txt"));
        ) {
            graph = readInput(s);
            solution = breadthFristSearch(graph);

            System.out.println(solution);

        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
    }
}
