#include <stdio.h>

int main(void) {
  int numLowerCase(char *str);
  void test1(int actualNum);

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

  int result = numLowerCase(S);
  test1(result);

}

void test1(actualNum) {
  int expectedNum = 8;
  printf("Number of lowercase characters in S: %d\n", actualNum);
  printf("Expected number: %d\n", expectedNum);
  if (expectedNum == actualNum) {
    printf("Test passed.\n");
  }
}
