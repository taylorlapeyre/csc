/**
 * @file UseSet.java
 * @author Taylor Lapeyre
 * @date 2012/4/15
 * Description: Uses the Set class to do operations on user-specified sets
 * Course: CS1350.s2
 * PAWS ID: tlapey1
 * Project #: 5
 * Instructor: Duncan
 */


/* TODO: Replace words for Unicode; implement xProduct? */


import java.util.*;

public class UseSet {
	public static void main(String[] args) {
		Scanner keyb = new Scanner(System.in);

		System.out.print("Enter the elements of the first set > ");
		String numbers1 = keyb.nextLine();

                System.out.print("Enter the elements of the second set > ");
                String numbers2 = keyb.nextLine();

                System.out.print("Enter the elements of the third set > ");
                String numbers3 = keyb.nextLine();

                ArrayList<String> a1 = new ArrayList<String>(Arrays.asList(numbers1.split(" ")));
                ArrayList<String> a2 = new ArrayList<String>(Arrays.asList(numbers2.split(" ")));
                ArrayList<String> a3 = new ArrayList<String>(Arrays.asList(numbers3.split(" ")));
                Set s1 = new Set(a1);
                Set s2 = new Set(a2);
                Set s3 = new Set(a3);


                System.out.print(s1.toString() + " subset " + s2.toString() + " -> ");
                System.out.print(s1.subset(s2));
                System.out.println();

                System.out.print(s2.toString() + " proper subset " + s3.toString() + " -> ");
                System.out.print(s2.properSubset(s3));
                System.out.println();

                System.out.print(s1.toString() + " intersection (" + s2.toString() + " union " + s3.toString() + ") -> ");
                System.out.print(s1.intersect(s2.union(s3)).toString());
                System.out.println();

                System.out.print("(" + s1.toString() + " - " + s2.toString() + ") intersection (" + s1.toString() + " symDiff " + s2.toString() + ") -> ");
                System.out.print(s1.diff(s2).intersect(s1.symDiff(s2)).toString());
                System.out.println();


                System.out.print("(" + s1.toString() + " - " + s2.toString() + ") union (" + s2.toString() + " - " + s1.toString() + ") -> ");
                System.out.print(s1.diff(s2).union(s2.diff(s1)).toString());
                System.out.println();

                System.out.print("(" + s1.toString() + " symDiff " + s2.toString() + ") union (" + s2.toString() + " symDiff " + s1.toString() + ") -> ");
                System.out.print(s1.symDiff(s2).union(s2.symDiff(s1)).toString());
                System.out.println();

                Set s4 = s1.diff(s2).union(s2.diff(s1));
                Set s5 = s1.symDiff(s2).union(s2.symDiff(s1));

                System.out.print(s1.diff(s2).union(s2.diff(s1)).toString() + " = " + s1.symDiff(s2).union(s2.symDiff(s1)).toString() + " -> ");
                System.out.print(s1.diff(s2).union(s2.diff(s1)).toString().equals(s1.symDiff(s2).union(s2.symDiff(s1)).toString()));
                System.out.println();

	}
}