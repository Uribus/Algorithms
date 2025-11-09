package binarysearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch3 {

    private static List<Integer> readInput(Scanner s) {
        List<Integer> input = new ArrayList<>();
        String line;
        while (s.hasNext()) {
            line = s.nextLine();
            for (String e : line.split(" ")) {
                input.add(Integer.parseInt(e));
            }
        }

        return input;
    }

    private static int binarySearch(List<Integer> arraySearch, int elementToSearch) {
        int leftPointer = 0, rightPointer = arraySearch.size() - 1;
        int slideValue = -1, midValue;
        int positionFound = -1;
        while (slideValue != 0) {
            slideValue = ((rightPointer - leftPointer) + 1) / 2;
            midValue = arraySearch.get(leftPointer + slideValue);

            if (midValue == elementToSearch) {
                positionFound = leftPointer + slideValue;
                slideValue = 0;
            } else if (midValue > elementToSearch) {
                rightPointer -= slideValue;
            } else {
                leftPointer += slideValue;
            }
        }
        return positionFound;
    }

    public static void main(String[] args) {
        List<Integer> exampleList;
        int elementToSearch, positionElement;
        try (
            Scanner sConsole = new Scanner(System.in);
            Scanner sFile = new Scanner(new File("inBS2.txt"));
        ) {
            exampleList = readInput(sFile);
            System.out.println("The array is: " + exampleList);
            System.out.println("Which element do you want to find?");
            elementToSearch = sConsole.nextInt();
            positionElement = binarySearch(exampleList, elementToSearch);

            if (positionElement != -1) {
                System.out.println("Element found in position " + (positionElement + 1));
            } else {
                System.out.println("Element is not in the array");
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
    }
    
}
