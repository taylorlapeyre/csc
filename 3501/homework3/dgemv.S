## Taylor Lapeyre

#define M %ecx
#define N %edx
#define A %ebp
#define LDA %edx
#define X %esi
#define Y %eax
#define II %edi
#define FSIZE 4*4

#                      4            8               12                  20
# void dgemv(const int M, const int N, const double alpha, const double *A,
#                      24                28               32           40
#            const int lda, const double *X, const double beta, double *Y)
.text
.global dgemv
dgemv:

  ##
  ## Setup
  ##

  # Completely clear our floating point stack, just in case
  finit

  # Save the values of our caller saved registers
  sub $FSIZE, %esp
  mov %ebp, (%esp)
  mov %edx, 4(%esp)
  mov %esi, 8(%esp)
  mov %edi, 12(%esp)

  # Load our values into them
  mov  FSIZE+4(%esp), M
  mov  FSIZE+8(%esp), N
  # skipping alpha, which we will load into the stack
  mov  FSIZE+20(%esp), A
  mov  FSIZE+24(%esp), LDA
  mov  FSIZE+28(%esp), X
  # skipping beta, which we will load into the stack
  mov  FSIZE+40(%esp), Y

  fldl FSIZE+12(%esp)
  fldl FSIZE+32(%esp)
  # Stack: {beta, alpha}

  ##
  ## Body
  ##

  xor II, II

  # Multiply A[i] by alpha
  fldl (A, II, 8)
  fmul %st(2), %st # FLOP!
  # Stack: {A[i]*alpha, beta, alpha}

  # Multiply that answer by X[i]
  fldl (X, II, 8)
  fmul %st(1), %st # FLOP!
  # Stack: {X[i]*A[i]*alpha, A[i]*alpha, beta, alpha}

  # Multiply Y[i] by beta
  fldl (Y, II, 8)
  fmul %st(3), %st # FLOP!
  # Stack: {Y[i]*beta, X[i]*A[i]*alpha, A[i]*alpha, beta, alpha}

  # Add that product to the result we got before that
  fadd %st(1) # FLOP!
  # Stack: {Y[i]*beta + X[i]*A[i]*alpha, X[i]*A[i]*alpha, A[i]*alpha, beta, alpha}

  # Store the final answer back into Y[i]
  fstpl (Y, II, 8)
  # Stack: {A[i]*alpha, beta, alpha}

  # Get rid of the junk values still stored on the stack
  fstp %st
  # Stack: {beta, alpha}

DONE:

  ##
  ## Tear Down
  ##

  # Clear the stack
  fstp %st(0)

  # Restore the values of the caller-saved registers we used
  mov (%esp), %ebp
  mov 4(%esp), %edx
  mov 8(%esp), %esi
  mov 12(%esp), %edi
  add $FSIZE, %esp
  ret
