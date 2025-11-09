package binarysearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch4 {

    private static List<Integer> readInputFile(Scanner sFile) {
        List<Integer> inputList = new ArrayList<>();
        while (sFile.hasNextInt()) {
            inputList.add(sFile.nextInt());
        }

        return inputList;
    }

    private static int binarySearch(List<Integer> elements, int toFind) {
        int position = -1;
        if ((elements != null) && !(elements.isEmpty())) {
            int leftPointer = 0, rightPointer = elements.size() - 1;
            int midPoint, midValue;
            do {
                midPoint = ((rightPointer - leftPointer) + 1) / 2;
                midValue = elements.get(leftPointer + midPoint);

                if (midValue == toFind) {
                    position = leftPointer + midPoint;
                    midPoint = 0;
                }
                if (midValue > toFind) {
                    rightPointer -= midPoint;
                }
                if (midValue < toFind) {
                    leftPointer += midPoint;
                }
            } while (midPoint != 0); 
        }

        return position;
    }
    public static void main(String[] args) {
        List<Integer> listToSearch;
        int elemToFind, positionElem;
        try (
            Scanner sFile = new Scanner(new File("inBS2.txt"));
            Scanner sConsole = new Scanner(System.in);
        ) {
            listToSearch = readInputFile(sFile);
            System.out.println("The array is: " + listToSearch);
            System.out.println("Input a number to search for, the position in the array will be returned");
            elemToFind = sConsole.nextInt();

            positionElem = binarySearch(listToSearch, elemToFind);

            if (positionElem < 0) {
                System.out.println("Element is not in the list.");
            } 
            if (positionElem >= 0) {
                System.out.println("Element was found in position " + (positionElem + 1));
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
        // scaner read file
        // scaner input user
        // method read list
        // method search for element with array and user input
    }    
}
