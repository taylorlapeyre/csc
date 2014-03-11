##  my registers:    %eax, %edx, %ecx
##  other registers: %esi, %ebp, %edi

#define N %edi
#define X %esi
#define Y %ebp

#define NN %eax
#define II %ecx
#define V %edx
#define FSIZE 12

# %eax                  4       8      12
# int getNegNumbers(int N, int *X, int *Y)
.text
.globl _getNegNumbers
_getNegNumbers:
  sub $FSIZE, %esp
  mov %edi, (%esp)
  mov %esi, 4(%esp)
  mov %ebp, 8(%esp)

  mov FSIZE+4(%esp),  N
  mov FSIZE+8(%esp),  X
  mov FSIZE+12(%esp), Y

  ## int numNegNumbers = 0;
  ## for (int i = 0; i < N; i++) {
  ##   int num = X[i];
  ##   if (num < 0) {
  ##     Y[numNegNumbers] = num;
  ##     numNegNumbers++;
  ##   }
  ## }

  xor NN, NN
  xor II, II
  LOOP1:
    mov (X, II, 4), V
    cmp $0, V
    jge SKIP
    mov V, (Y, NN, 4)
    inc NN
    SKIP:
      inc II
      cmp N, II
  jne LOOP1

  mov (%esp),  %edi
  mov 4(%esp), %esi
  mov 8(%esp), %ebp
  add $FSIZE, %esp
  ret
