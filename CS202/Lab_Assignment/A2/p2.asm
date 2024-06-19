.text
.globl main
main:
li a7, 7
ecall             	# fa0 = n
li t1, 15
li t2, 20
li t3, 1
fcvt.d.w fa1, t1       # fa1 = 15
fcvt.d.w fa2, t2       # fa2 = 20
fle.d t0, fa0, fa1	# if n <= 15, t0 = 1
beq t0, t3, L1       	# if n <= 15, goto L1 
fle.d t0, fa0, fa2	# if n <= 20, t0 = 1
beq t0, t3, L2       	# if n <= 20, goto L2 
beq zero, zero, L3   	# n > 20, goto L3

L1: 
beq zero, zero, last

L2: 
fsub.d fa0, fa0, fa1	# fa0 -= 15
li t3, 2
fcvt.d.w fa3, t3	# fa3 = 2
fmul.d fa0, fa0, fa3 	# fa0 *= 2
fadd.d fa0, fa0, fa1	# fa0 += 15
beq zero, zero, last

L3: 
fsub.d fa0, fa0, fa2	# fa0 -= 20
li t3, 3
fcvt.d.w fa3, t3	# fa3 = 3
fmul.d fa0, fa0, fa3 	# fa0 *= 3
li t4, 25
fcvt.d.w fa4, t4	# fa4 = 25
fadd.d fa0, fa0, fa4	# fa0 += 25
beq zero, zero, last

last:
li a7, 3
ecall
li a7, 10
ecall
