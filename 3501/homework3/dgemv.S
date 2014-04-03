## Taylor Lapeyre

#define A %ebp
#define LDA %edx
#define X %esi
#define Y %edx
#define II %edi
#define JJ %ecx
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
  # Skipping the constant N, which we will refer to directly
  # Skipping the constant M, which we will refer to directly
  # Skipping alpha, which we will refer to directly
  mov  FSIZE+20(%esp), A
  mov  FSIZE+24(%esp), LDA
  mov  FSIZE+28(%esp), X
  # Skipping beta, which we will refer to directly
  mov  FSIZE+40(%esp), Y

  ##
  ## THE BUSINESS
  ##

  xor II, II

OUTERLOOP:

INNERLOOP:

  xor JJ, JJ

  # Multiply A[j] by X[j]
  fldl    (A, JJ, 8)
  fmull   (X, JJ, 8)
  # Stack: {A[j]*X[j]}

  # Add previous product to that answer
  faddp
  # Stack: {[j]+X[j]+Product}

  # J++
  inc JJ

  # If J is less than N, run the loop again
  cmp JJ, FSIZE+8(%esp)
  jne INNERLOOP

  ##
  ## End Inner Loop Logic
  ##

  # Otherwise, we're back in the outer loop again

  # Multiply the product by alpha
  fmull FSIZE+12(%esp)
  # Stack: {alpha*Product}

  # Multiply Y[i] by beta
  fldl (Y,II,8)
  fmull FSIZE+32(%esp)
  # Stack: {Y[i]*beta, alpha*Product}

  # Add the two together and then pop the stack
  faddp
  # Stack: {}

  # Put the answer into Y[i]
  fstpl (Y,II,8)
  # Stack: {}

  # I++
  inc II

  # Move to the next row
  add LDA, A

  # Run the whole thing again until I is equal to M
  cmp II, FSIZE+4(%esp)
  jne OUTERLOOP

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
