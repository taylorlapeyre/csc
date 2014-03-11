#include <stdio.h>

int main(void) {
  int numLowerCase(char *str);
  char S[80];
  S[0] = 'H';
  S[1] = 'e';
  S[2] = 'l';
  S[3] = 'l';
  S[4] = 'o';
  S[5] = 'W';
  S[6] = 'o';
  S[7] = 'r';
  S[8] = 'l';
  S[9] = 'd';
  S[10] = 0;

  int expectedNum = 8;
  int actualNum   = numLowerCase(S);
  printf("Number of lowercase characters in S: %d\n", actualNum);
  printf("Expected number: %d\n", expectedNum);
  if (expectedNum == actualNum) {
    printf("Test passed.\n");
  }
}
