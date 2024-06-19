.data
m1:.word -21 # multiplicand
m2:.word -21 # multiplier
str:.ascii "\n"

.text
lw t0, m1
lw t1, m2
add t2, zero, zero
loop:
li s1, 1
and s2, s1, t1 #to determine the lowest bit of t1
beq s2, zero, jumpAdd
add t2, t0, t2
jumpAdd:
slli t0, t0, 1
srli t1, t1, 1
addi a0, a0,1
li a1, 32 
blt a0, a1, loop
mv a0, t2

li a7, 1
ecall
add a2, a0, zero
li a7, 4
la a0, str
ecall
add a0, a2, zero
li a7, 35
ecall
