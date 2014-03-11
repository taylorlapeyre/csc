##  my registers:    %eax, %edx, %ecx
##  other registers: %esi, %ebp, %edi

#define II    %edx
#define NL    %eax
#define CH    %cl
#define STR   %esi
#define FSIZE 4

.text
.globl _numLowerCase

# %eax                   4
# int numLowerCase(char *str)
_numLowerCase:
  sub $FSIZE, %esp
  movl %esi, (%esp)

  movl FSIZE+4(%esp), STR

  ##  int i  = 0;
  ##  int nl = 0;
  ##  while (S[i]) {
  ##    if (S[i] > 97 && S[i] < 122) {
  ##      nl++;
  ##    }
  ##    i++;
  ##  }

  xor II, II
  xor NL, NL
  LOOP1:
    movb (STR, II), CH

    cmp $0, CH
    je DONE

    cmp $97, CH
    jl SKIP

    cmp $122, CH
    jg SKIP

    inc NL
    SKIP:
      inc II
  jmp LOOP1

  DONE:
    movl (%esp), %esi
    add $FSIZE, %esp
    ret
