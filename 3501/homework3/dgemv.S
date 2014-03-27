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

  /*
   * Setup
  */

  # Save our caller saved registers
  sub $FSIZE, %esp
  mov %ebp, (%esp)
  mov %edx, 4(%esp)
  mov %esi, 8(%esp)
  mov %edi, 12(%esp)

  # Load our values
  mov FSIZE+4(%esp), M
  mov FSIZE+8(%esp), N
  fldl FSIZE+12(%esp)     # ST = {alpha}
  mov FSIZE+20(%esp), A
  mov FSIZE+24(%esp), LDA
  mov FSIZE+28(%esp), X
  fldl FSIZE+32(%esp)     # ST = {beta, alpha}
  mov FSIZE+40(%esp), Y

  /*
   * Body
  */

  xor II, II

  fldl (A, II, 8)         #         ST = { A[i], beta, alpha }
  fmul %st(2), %st        # FLOP!   ST = { A[i]*alpha, beta, alpha }

  fldl (X, II, 8)         #         ST = { X[i], A[i]*alpha, beta, alpha }
  fmul %st(1), %st        # FLOP!   ST = { X[i]*A[i]*alpha, A[i]*alpha, beta, alpha }

  fstpl (X, II, 8)        #         ST = { A[i]*alpha, beta, alpha }

  fldl (Y, II, 8)         #         ST = { Y[i], A[i]*alpha, beta, alpha}
  fmul %st(2), %st        # FLOP!   ST = { Y[i]*beta, A[i]*alpha, beta, alpha }

  fadd (X, II, 8)         # FLOP!   ST = { Y[i]*beta + X[i]*A[i]*alpha, A[i]*alpha, beta, alpha }

  fstpl (Y, II, 8)        #         ST = { A[i]*alpha, beta, alpha }
  fstp                    #         ST = { beta, alpha }

DONE:

  /*
   * Tear Down
  */

  fstp %st(0)
  mov (%esp), %ebp
  mov 4(%esp), %edx
  mov 8(%esp), %esi
  mov 12(%esp), %edi
  add $FSIZE, %esp
  ret


