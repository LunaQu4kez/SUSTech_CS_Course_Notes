.text
.globl main
main:
li a0, 0x10010000
li a1, 5000
li a7, 8
ecall
li a7, 4
ecall
li a7, 10
ecall