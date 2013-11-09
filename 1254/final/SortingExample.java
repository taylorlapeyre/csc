// These are the sorting algoriths on the test, plus the Binary Search algorithm

public class SortingExample {

  public static int[] selectionSort(int[] array, int start, int end) {
    if (start >= end) return array; // Base Case

    // Make minIndex the smallest value
    int minIndex, start;
    minIndex = start;
    for (int i = start + 1; i < end; i++)
      if (array[i] < array[minIndex]) minIndex = i;

    // Swap the value of startIndex with the value of minIndex
    int temp = array[start];
    array[start] = array[minIndex];
    array[minIndex] = temp;

    // Do the same thing for what's left of the array
    selectionSort(array, start + 1, end);
  }

  public static int[] insertionSort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int value = array[i];
      int j = i - 1;
      // Is the next value out of order?
      while (j >= 0 && array[j] > value) {
        // If so, swap it with the previous element
        array[j + 1] = array[j];
        j = j - 1;
      }
      // Otherwise, don't do anything
      array[j + 1] = value;
    }
    return array;
  }

  public static int[] mergeSort(int[] array, int start, int end) {
    if (array.length <= 1) return array; // Base Case

    // split the array into two equal arrays
    int mid = (start + end) / 2;
    int[] left = new int[mid];
    int[] right = new int[end - mid];
    for (int i = 0; i < mid; i++)
      array[i] = left[i];
    for (int i = mid + 1; i < end; i++)
      array[i] = right[i];

    // Split recursively until we get to the base case
    right =  mergeSort(right);
    left  =  mergeSort(left);

    //merge the two sorted halves
    return merge(left, right);
  }

  public static int binarySearch(int[] array, int start, int end, int target) {
    if (end < start) return -1; // Base Case

    // Split the array into two halves
    int mid = (start + end) / 2;

    // If the midpoint's value is > target, then search the lower half
    if (array[mid] > target)
      binarySearch(array, start, mid - 1, target);
    // Otherwise, search the upper half
    else if (array[mid] < target)
      binarySearch(array, mid + 1, end, target);
    // If the midpoint's value isn't > or < the target, it must be the target
    else
      return mid;
  }
}