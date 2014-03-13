##  my registers:    %eax, %edx, %ecx
##  other registers: %esi, %ebp, %edi

#define iret  %eax
#define II    %ecx
#define ptr   %edx
#define ech   %ebx
#define ch    %bl
#define FSIZE 4

.text
.globl _istr

# %eax         4       8        12        16
# int istr(int i1, int i2, int *i3, char *str)
_istr:
  sub $FSIZE, %esp
  movl %ebx, (%esp)        # save caller's value in ebx

  ## iret = i1 + i2;

  movl FSIZE+4(%esp), iret
  addl FSIZE+8(%esp), iret

  ## *i3 = 5 * (*i3);

  movl FSIZE+12, ptr
  movl (ptr), II
  lea (II, II, 4), II
  movl II, (ptr)

  ## for (int i = 0; i < 26; i++) {
  ##   str[i] = i + 97;
  ## }
  ## istr[26] = 0;

  movl FSIZE+16(%esp), ptr
  xor II, II
  ALPLOOP:
    lea 97(II), ech
    movb ch, (ptr, II, 1)
    inc II
    cmp $26, II
  jne ALPLOOP
  movb $0, 26(ptr)
DONE:
  movl (%esp), %ebx        # restore caller's value to ebx
  add $FSIZE, %esp
