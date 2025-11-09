package binarysearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch5 {

    private static List<Integer> readInputList(Scanner sFile) {
        List<Integer> inputList = new ArrayList<>();
        String line;
        String[] lineData;

        while (sFile.hasNextLine()) {
            line = sFile.nextLine();
            lineData = line.split(" ");
            for (String e : lineData) {
                inputList.add(Integer.parseInt(e));
            }
        }

        return inputList;
    }

    private static int binarySearch(List<Integer> graph, int toFind) {
        int solution = -1;

        if (graph != null && !(graph.isEmpty())) {
            int leftPointer = 0, righPointer = graph.size() - 1;
            int midPoint, midValue;

            do {
                midPoint = ((righPointer - leftPointer) + 1) / 2;
                midValue = graph.get(leftPointer + midPoint);

                if (midValue == toFind) {
                    solution = leftPointer + midPoint;
                    midPoint = 0;
                } else if (midValue < toFind) {
                    leftPointer += midPoint;
                } else {
                    righPointer -= midPoint;
                }

            } while (midPoint != 0);
        }

        return solution;
    }
    public static void main(String[] args) {
        List<Integer> elemList;
        int elemToFind, sol;
        try (
            Scanner sFile = new Scanner(new File("inBS2.txt"));
            Scanner sConsole = new Scanner(System.in);
        ) {
            elemList = readInputList(sFile);
            System.out.println("The list is: " + elemList);
            System.out.println("Input a number to find");
            elemToFind = sConsole.nextInt();
            sol = binarySearch(elemList, elemToFind);

            if (sol < 0) {
                System.out.println("Element is not in the list");
            } else {
                System.out.println("The element " + elemToFind + " was found in position " + (sol + 1));
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
    }
}
