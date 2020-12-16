package by.matusevich;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static by.matusevich.ReadFromFileToStringArrayList.readFromFileToArray;

public class Task1 {
    private static final String INPUT = "/home/sergey/Documents/input";
    private static final int YEAR = 2020;

    public static void main(String[] args) {

        ArrayList<Integer> integers = new ArrayList<>();

        List<String> strings = readFromFileToArray(INPUT);

        for (String s : strings
        ) {
            integers.add(Integer.valueOf(s));
        }
        Set<Integer> integerSet = new TreeSet<>(integers);
        //find numbers via arrayList
        long l = System.currentTimeMillis();

        for (int i = 0; i < integers.size(); i++) {
            for (int j = i + 1; j < integers.size(); j++) {
                if (integers.get(i) + integers.get(j) == YEAR) {
                    System.out.println(integers.get(i) + " " + integers.get(j));
                    System.out.println(integers.get(i) * integers.get(j));
                    break;
                }
            }
        }
        long l2 = System.currentTimeMillis();
        System.out.println("speed = " + (l2 - l));

        //find numbers via treeSet
        long lset = System.currentTimeMillis();

        for (Integer i : integerSet
        ) {
            int numberToFind = YEAR - i;
            if (integerSet.contains(numberToFind)) {
                System.out.println(i + " " + numberToFind);
                System.out.println(i * numberToFind);
                break;
            }

        }
        long l2set = System.currentTimeMillis();
        System.out.println("speed of set = " + (l2set - lset));

        //find 3 numbers via treeSet
        long lset3 = System.currentTimeMillis();

        for (Integer i : integerSet
        ) {
            int sumOfLastTwoNumbers = YEAR - i;
            for (Integer j : integerSet
            ) {
                int number = sumOfLastTwoNumbers - j;
                if (integerSet.contains(number)) {
                    System.out.println(i);
                    System.out.println(j);
                    System.out.println(number);

                    int mult = i * j * number;
                    System.out.println(mult);
                }
            }
        }
        long lset4 = System.currentTimeMillis();
        System.out.println("speed = " + (lset4 - lset3));
    }
}