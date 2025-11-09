package binarysearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import colors.Format;
import colors.Style;
import colors.Emphasis;
import colors.Color;

public class BinarySearch {

    private static final Format BACKGROUND_YELLOW = new Format(Style.NORMAL, Emphasis.BACKGROUND_COLOR, Color.YELLOW);
    private static final Format MAGENTA = new Format(Style.NORMAL, Emphasis.NORMAL, Color.MAGENTA);

    private static List<Integer> readInput(Scanner s) {
        List<Integer> listNums = new ArrayList<>();
        String line;
        while(s.hasNextLine()) {
            line = s.nextLine();
            String[] nums = line.split(" ");
            for (String e : nums) {
                listNums.add(Integer.parseInt(e));
            }
        }
        return listNums;
    }

    private static int binarySearch(List<Integer> orderedList, int toFind) {
        int found = -1;
        int min = 0, max = orderedList.size() - 1;
        int newMidPosition = 1;
        int pointerPos;
        int currentElem;
        boolean stay = true;
        if ((orderedList != null) && !(orderedList.isEmpty())) {
            while (stay && newMidPosition != 0) {
                newMidPosition = (((max - min) + 1)  / 2);
                pointerPos = min + newMidPosition;
                currentElem = orderedList.get(pointerPos);
                if (toFind == currentElem) {
                    found = pointerPos;
                    stay = !stay;
                } else if (toFind > currentElem) {
                    min += newMidPosition;
                } else {
                    max -= newMidPosition;
                }
            }
        }
        return found;
    }

    private static String printList(List<Integer> list, int pos) {
        String array = "[";
        for (int i = 0; i < list.size(); i++) {
            if (i != pos) {
                array += Integer.toString(list.get(i)) + ", ";
            } else {
                array += BACKGROUND_YELLOW + Integer.toString(list.get(i)) + Format.RESET_CODE + ", ";
            }
        }

        return array.substring(0, array.lastIndexOf(",")) + "]";
    }

    public static void main(String[] args) {
        List<Integer> orderedList;
        int toFind;
        int position;
        try (
        Scanner sConsole = new Scanner(System.in);
        Scanner sFile = new Scanner(new File("inBinarySearch.txt"));
        ) {
            orderedList = readInput(sFile);
            System.out.println("The array is: " + orderedList);
            System.out.println("Which number do you want to find?");
            toFind = sConsole.nextInt();

            position = binarySearch(orderedList, toFind);
            if (position < 0) {
                System.out.println(MAGENTA + "The element is not in the array" + Format.RESET_CODE);
            } else {
                System.out.println("The element is in position: " + printList(orderedList, position));
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }
    }
}
