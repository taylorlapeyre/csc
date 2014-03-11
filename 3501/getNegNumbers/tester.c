#include <stdio.h>

int main(void) {
  int getNegNumbers(int N, int *X, int *Y);
  int N = 10;
  int X[N];
  int Y[N];
  X[0] = 2;
  X[1] = -4;
  X[2] = 8;
  X[3] = -7;
  X[4] = -8;
  X[5] = 0;
  X[6] = 3;
  X[7] = 1;
  X[8] = -2;
  X[9] = 9;

  int expectedNum = 4;
  int actualNum   = getNegNumbers(N, X, Y);

  printf("Test 1\n");
  printf("======\n");
  printf("Number of negative numbers in X: %d\n", actualNum);
  printf("Number expected: %d\n", expectedNum);
  if (expectedNum == actualNum) {
    printf("Test Passed\n");
  }

  int expectedArray[expectedNum];
  expectedArray[0] = -4;
  expectedArray[1] = -7;
  expectedArray[2] = -8;
  expectedArray[3] = -2;

  int arrayDoesMatch = 1;
  printf("\nTest 2\n");
  printf("======\n");
  for (int i = 0; i < expectedNum; i++) {
    if (expectedArray[i] != Y[i]) {
      printf("Test Failed!\n");
      printf("expectedArray[%d] = %d, Y[%d] = %d", i, expectedArray[i], i, Y[i]);
      arrayDoesMatch = 0;
    }
  }

  if (arrayDoesMatch) {
    printf("Y matches the expected array. Test Passed.\n");
  }
}
