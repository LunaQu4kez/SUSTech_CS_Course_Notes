.text
main:
li a7, 5
ecall
jal ra, func
li a7, 1
ecall
li a7, 10
ecall

func:
addi sp, sp, -16
sw a2, 12(sp)
sw a1, 8(sp)
sw ra, 4(sp)
sw a0, 0(sp)

addi t0, a0, -1
bge zero, t0, retu
addi a0, a0, -1
jal ra, func
mv a1, a0
lw a0, 0(sp)
addi a0, a0, -2
jal ra, func
mv a2, a0
add t3, a1, a2
lw a0, 0(sp)
lw ra, 4(sp)
lw a1, 8(sp)
lw a2, 12(sp)
addi sp, sp, 16
mv a0, t3
jr ra

retu:
li a0, 1
addi sp, sp, 16
jr ra

 
