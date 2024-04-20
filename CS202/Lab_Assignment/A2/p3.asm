.text
.globl main
main:
# fa1 = a, fa2 = b, fa3 = c, fa4 = d
# ft1 = x1, ft2 = x2, ft0 = x1 + x2, ft3 = x3, ft4 = f(x1), ft5 = f(x2), ft6 = abs(f(x3)), ft7 = f(x3), ft8 = f(x1)*f(x3)
# fs0 = result of f(x), fs0-3 used to calculate f(x)
# fs4 = 1, fs5 = 0.5, fs6 = 1e-6, fs7 = 2, fs8 = 1e6, fs9 = 0
li a7, 7
ecall
fmv.d fa1, fa0 			# fa1 = a

li a7, 7
ecall
fmv.d fa2, fa0 			# fa2 = b

li a7, 7
ecall
fmv.d fa3, fa0 			# fa3 = c

li a7, 7
ecall
fmv.d fa4, fa0 			# fa4 = d

li a7, 7
ecall
fmv.d ft1, fa0 			# ft1 = x1

li a7, 7
ecall
fmv.d ft2, fa0 			# ft2 = x2

# calculate f(x1) (x1 is in ft1)
fmv.d fs3, ft1			
fmul.d fs3, fs3, ft1
fmul.d fs3, fs3, ft1
fmul.d fs3, fs3, fa1		# fs3 = ax^3
fmv.d fs2, ft1			
fmul.d fs2, fs2, ft1
fmul.d fs2, fs2, fa2		# fs2 = bx^2
fmv.d fs1, ft1	
fmul.d fs1, fs1, fa3		# fs1 = cx
fmv.d fs0, fs9
fadd.d fs0, fs0, fa4
fadd.d fs0, fs0, fs1
fadd.d fs0, fs0, fs2
fadd.d fs0, fs0, fs3		# fs0 = f(x1)

fmv.d ft4, fs0			# ft4 = f(x1)

# calculate f(x2) (x2 is in ft2)
fmv.d fs3, ft2			
fmul.d fs3, fs3, ft2
fmul.d fs3, fs3, ft2
fmul.d fs3, fs3, fa1		# fs3 = ax^3
fmv.d fs2, ft2			
fmul.d fs2, fs2, ft2
fmul.d fs2, fs2, fa2		# fs2 = bx^2
fmv.d fs1, ft2	
fmul.d fs1, fs1, fa3		# fs1 = cx
fmv.d fs0, fs9
fadd.d fs0, fs0, fa4
fadd.d fs0, fs0, fs1
fadd.d fs0, fs0, fs2
fadd.d fs0, fs0, fs3		# fs0 = f(x2)

fmv.d ft5, fs0			# ft5 = f(x2)

li a2, 1
fcvt.d.w fs4, a2 		# fs4 = 1
li a2, 2
fcvt.d.w fs7, a2		# fs7 = 2
li a2, 1000000
fcvt.d.w fs8, a2 		# fs8 = 1e6
fdiv.d fs5, fs4, fs7		# fs5 = 0.5
fdiv.d fs6, fs4, fs8		# fs6 = 1e-6

Loop:
fadd.d ft0, ft1, ft2		# ft0 = x1 + x2
fmul.d ft3, ft0, fs5		# ft3 = x3

# calculate f(x1) (x1 is in ft1)
fmv.d fs3, ft1			
fmul.d fs3, fs3, ft1
fmul.d fs3, fs3, ft1
fmul.d fs3, fs3, fa1		# fs3 = ax^3
fmv.d fs2, ft1			
fmul.d fs2, fs2, ft1
fmul.d fs2, fs2, fa2		# fs2 = bx^2
fmv.d fs1, ft1	
fmul.d fs1, fs1, fa3		# fs1 = cx
fmv.d fs0, fs9
fadd.d fs0, fs0, fa4
fadd.d fs0, fs0, fs1
fadd.d fs0, fs0, fs2
fadd.d fs0, fs0, fs3		# fs0 = f(x1)

fmv.d ft4, fs0			# ft4 = f(x1)
# calculate f(x3) (x3 is in ft3)
fmv.d fs3, ft3			
fmul.d fs3, fs3, ft3
fmul.d fs3, fs3, ft3
fmul.d fs3, fs3, fa1		# fs3 = ax^3
fmv.d fs2, ft3			
fmul.d fs2, fs2, ft3
fmul.d fs2, fs2, fa2		# fs2 = bx^2
fmv.d fs1, ft3
fmul.d fs1, fs1, fa3		# fs1 = cx
fmv.d fs0, fs9
fadd.d fs0, fs0, fa4
fadd.d fs0, fs0, fs1
fadd.d fs0, fs0, fs2
fadd.d fs0, fs0, fs3		# fs0 = f(x3)
fmv.d ft7, fs0			# ft7 = f(x3)
fabs.d ft6, fs0			# ft6 = abs(f(x3))

flt.d t1, ft6, fs6		# if abs(f(x3)) < 1e-6, t1 = 1
li t2, 1
beq t1, t2, out		# if abs(f(x3)) < 1e-6, goto out
fmul.d ft8, ft7, ft4		# ft8 = f(x1)*f(x3)
flt.d t1, ft8, fs9		# if f(x1)*f(x3) < 0, t1 = 1

################
#fmv.d fa0, ft4
#li a7, 3
#ecall
###############
################
#fmv.d fa0, ft7
#li a7, 3
#ecall
###############

beq t1, t2, case1
fmv.d ft1, ft3
beq zero, zero, Loop
case1: fmv.d ft2, ft3
beq zero, zero, Loop

out:
fmv.d fa0, ft3
li a7, 3
ecall
li a7, 10
ecall

