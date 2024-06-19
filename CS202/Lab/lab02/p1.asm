.data
	str: .ascii "\nWelcome "
	sid: .space 9
	e1: .asciz " to RISC-V World" 

.text
main:
	li a7, 8 # to get a string
	la a0, sid
	li a1, 9 
	ecall
	
	la t1, e1
	lb t2, 0(t1)
	la t3, str
	sb t2, 17(t3)
	
	li a7, 4 # to print a string
	la a0, str
	ecall
	
	li a7, 10 # to exit
	ecall
