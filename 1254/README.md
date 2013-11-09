## Selection Sort:

  * if the start index >= the end index, then return the array
  * find the lowest value, place it at the beginning by swapping the two.
  * find the 2nd lowest value, place it after that one.
  * continue until sorted

## Insertion Sort:
  * compare the first two terms, and if they are out of place then switch them
  * compare the next term to the one before it until a value is lower than it
  * continue until sorted

## Merge Sort:
  * if the length of the array <= 1, return the array
  * split the array into two equal arrays
  * split each side until the base case is reached
  * merge the two sorted arrays

## Binary Search:

  *Only good for sorted arrays*

  * if the start index is >= the high index, then the target isn't in the array
  * get a middle value of the array
  * if the middle value is greater than the target, search the lower half
  * if the middle value is less than the target, search the upper half
  * if neither, then return the middle value's index

## Three parts of Recursive Algorithms:

  1. Base case
  2. Recursive calls to make the problem into smaller parts
  3. Combining the solutions to form a single solution

## Goals of This Class:

  1. Learning to program effectively using OOP methods
  2. Learning the java language as a tool for efficient programming
  3. Learning problem-solving methods and how to think logically

## To Determine if Three Points are Collinear:

  *Collinear if...*

  `(x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1)`