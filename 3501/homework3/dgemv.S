#define M %ecx
#define N %edx
#define A %ebp
#define LDA %edx
#define X %esi
#define Y %eax
#define II %edi
#define FSIZE 4*4

.text
# void 
.globl _dax2
_dax2:
  sub $FSIZE, %esp
  mov %ebp, (%esp)
  mov %edx, 4(%esp)
  mov %esi, 8(%esp)
  mov %edi, 12(%esp)
  
  mov FSIZE+4(%esp), M
  mov FSIZE+8(%esp), N
  fldl FSIZE+12(%esp) // ST = {alpha}
  mov FSIZE+20(%esp), A
  mov FSIZE+24(%esp), LDA
  mov FSIZE+28(%esp), X
  fldl FSIZE+32(%esp) // ST = {beta, alpha}
  mov FSIZE+40(%esp), Y

  // Body
  xor II, II
  fldl (Y, II, 8)     // ST = {Y[0], beta, alpha}
  fldl (X, II, 8)     // ST = {X[0], Y[0], beta, alpha}
  fldl (A, II, 8)     // ST = {A[0], X[0], Y[0], beta, alpha}
  fmul %st(1), %st    // ST = {A[0]*X[0], X[0], Y[0], beta, alpha}
  fmul %fs(4), %st    // ST = {A[0]*X[0]*alpha, X[0], Y[0], beta, alpha}
  
  
  fmul %
  fmul %st(2), %st
  fstpl (Y, II, 8)



DONE:
  fstp %st(0)
  mov (%esp), %ebp
  mov 4(%esp), %edx
  mov 8(%esp), %esi
  mov 12(%esp), %edi
  add $FSIZE, %esp
  ret


