.text
.globl main
main:
li a0, 0x10010000
li a1, 5000
li a7, 8
ecall

addi a2, a0, 1    # second char addr
li t0, 0x20       # space
lb a2, 0(a2)      # second char
beq a2, t0, L1
loop:           
lb a1, 0(a0)      # now char
beq a1, zero, L4  # if now char is 0x00(the string is end)
beq a1, t0, L3    # if now char is space, goto L3
addi a0, a0, 1
beq zero, zero, loop

L3: 
addi t1, t1, 2
beq t1, a0, L5
add t1, a0, zero  # t1 = a0
addi a0, a0, 1
beq zero, zero, loop

L1:
lb t1, 0(a0)
addi t1, t1, -73    # -'I'
bge t1, zero, L2
addi t1, t1, 26
L2: addi a0, t1, 0
li a7, 1
ecall
li a7, 10
ecall

L4:
li a0, -1
li a7, 1
ecall
li a7, 10
ecall

L5:
addi a0, a0, -1
lb t1, 0(a0)
addi t1, t1, -65    # -'A'
bge t1, zero, L6
addi t1, t1, 26
L6: addi a0, t1, 0
li a7, 1
ecall
li a7, 10
ecall
