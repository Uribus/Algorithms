package binarysearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch2 {

    private static List<Integer> readInput(Scanner s){
        List<Integer> input = new ArrayList<>();
        while (s.hasNext()) {
            input.add(s.nextInt());
        }

        return input;
    }

    private static int binarySearch(List<Integer> list, int toFind) {
        int positionReturn = -1;
        int smallPointer = 0, bigPointer = list.size() - 1;
        int midPoint;
        int inPos;
        boolean stay = true;
        if (list != null && !(list.isEmpty())) {
            while(stay) {
                midPoint = ((bigPointer - smallPointer) + 1) / 2;
                inPos = list.get(smallPointer + midPoint);
                if (toFind == inPos) {
                    positionReturn = smallPointer + midPoint;
                    stay = false;
                } else if (toFind > inPos) {
                    smallPointer += midPoint;
                } else if (toFind < inPos) {
                    bigPointer -= midPoint;
                }

                if (midPoint == 0) { //smallPointer == bigPointer, found or not array is traversed
                    stay = false;
                }
            }
        }

        return positionReturn;
    }
    public static void main(String[] args) {
        // list = readInput(scanner)
        List<Integer> numbersList;
        int toFind;
        int resultPosition;
        try (
            Scanner sConsole = new Scanner(System.in);
            Scanner sFile = new Scanner(new File("inBinarySearch.txt"));
        ) {
            numbersList = readInput(sFile);
            System.out.println("The array is: " + numbersList);
            System.out.println("Which number do you want to find?");
            toFind = sConsole.nextInt();

            resultPosition = binarySearch(numbersList, toFind);

            if (resultPosition != -1) {
                System.out.println("The element is in position: " + (resultPosition + 1));
            } else {
                System.out.println("The element is not in the list");
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found" + e);
        }

        // result = binarySearch(list)

    }
}
