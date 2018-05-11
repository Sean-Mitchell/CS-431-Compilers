	.text
	.globl main
jal main
NEAT:	sub $sp, $sp,8
	sw $ra, 0($sp) 
	sub $sp, $sp,4
	li	$t1,	1
	sw  $t1, 8($sp) 
	lw $t2, 8($sp) 
	move	$t3,	$t2
	la  $v0, ($t3) 
	addiu $sp, $sp,4
	lw $ra, 0($sp) 
	addiu $sp, $sp, 4
	jr $ra
main:
	sub $sp, $sp,-4
	lw $t4, 0($sp) 
	move	$t5,	$t4
	sw  $t5, dad  
	lw $t6, dad 
	li  $v0, 1
	la  $a0, ($t6)
syscall
	jal	NEAT 
	la $t1, ($v0) 
	move	$t3,	$t1
	sw  $t3, dad  
	lw $t2, dad 
	li  $v0, 1
	la  $a0, ($t2)
syscall
	b exit
exit: 
	li	$v0, 10
syscall
	.data
dad:	 .word	 0
