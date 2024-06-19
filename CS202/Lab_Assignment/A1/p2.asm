.text
.globl main
main:
li t0, 0x10010000
li t1, 1
sw t1, 0(t0)
li t1, 2
sw t1, 4(t0)
li t1, 3
sw t1, 8(t0)
li t1, 5
sw t1, 12(t0)
li t1, 8
sw t1, 16(t0)
li t1, 13
sw t1, 20(t0)
li t1, 21
sw t1, 24(t0)
li t1, 34
sw t1, 28(t0)
li t1, 55
sw t1, 32(t0)
li t1, 89
sw t1, 36(t0)
li t1, 144
sw t1, 40(t0)
li t1, 233
sw t1, 44(t0)
li t1, 377
sw t1, 48(t0)
li t1, 610
sw t1, 52(t0)
li t1, 987
sw t1, 56(t0)
li t1, 1597
sw t1, 60(t0)
li t1, 2584
sw t1, 64(t0)
li t1, 4181
sw t1, 68(t0)
li t1, 6765
sw t1, 72(t0)
li t1, 10946
sw t1, 76(t0)
li t1, 17711
sw t1, 80(t0)
li t1, 28657
sw t1, 84(t0)
li t1, 46368
sw t1, 88(t0)
li t1, 75025
sw t1, 92(t0)
li t1, 121393
sw t1, 96(t0)
li t1, 196418
sw t1, 100(t0)
li t1, 317811
sw t1, 104(t0)
li t1, 514229
sw t1, 108(t0)
li t1, 832040
sw t1, 112(t0)
li t1, 1346269
sw t1, 116(t0)

li a7, 5
ecall
add t1, a0, zero # left
ecall
add t2, a0, zero # right

add a0, zero, zero # counter
add a1, zero, zero # index
li a2, 30
li a3, 4

loop:
bge a1, a2, end
mul t3, a3, a1 # index*4
add t4, t0, t3 # addr
addi a1, a1, 1
lw t5, 0(t4)
blt t5, t1, next
bgt t5, t2, next
addi a0, a0, 1
next: beq zero, zero, loop

end:
li a7, 1
ecall
li a7, 10
ecall

