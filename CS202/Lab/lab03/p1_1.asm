.include "macro_print_str.asm"
.data
	arrayx: .space 10 
	str: .asciz "\nThe arrayx is: "
.text
main:
	print_string("Please input 10 integers: \n")
	add t0, zero, zero
	addi t1, zero, 10
	la t2, arrayx

loop_r:
	li a7, 5
	ecall
	sw a0, (t2)
	addi t0, t0, 1
	addi t2, t2, 4
	bne t0, t1, loop_r
	la a0, str
	li a7, 4
	ecall
	addi t0, zero, 0
	la t2, arrayx

	print_string("\nThe arrayx is: ")

loop_w:
	lw a0, (t2)
	li a7, 1
	ecall
	print_string(" ")
	addi t2, t2, 4
	addi t0, t0, 1
	bne t0, t1, loop_w
	
	end