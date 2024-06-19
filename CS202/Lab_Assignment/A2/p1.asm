.text
.globl main
main:
li a7, 5
ecall
mv t0, a0   # t0 = n

li a2, 0x10010000   # base addr of first vector
L1: bge t1, t0, o1  # i >= n, out loop
li a7, 6
ecall               # read a float
fsw fa0, 0(a2)
addi a2, a2, 4
addi t1, t1, 1
beq zero, zero, L1

o1: li t1, 0
li a2, 0x10010000
fcvt.s.d fa1, fa1
L2: bge t1, t0, o2  # i >= n, out loop
li a7, 6
ecall               # read a float
flw fa3, 0(a2)
fmul.s fa2, fa0, fa3
fadd.s fa1, fa1, fa2
addi a2, a2, 4
addi t1, t1, 1
beq zero, zero, L2

o2: fmv.s fa0, fa1
li a7, 2
ecall
li a7, 10
ecall

