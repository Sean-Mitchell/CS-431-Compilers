	.text
	.global main
main:
	sub $sp, $sp,-4
	li	$t1,	1
	sw  $t1, dad  
	lw $t2, dad 
	li  $v0, 1
	la  $a0, ($t2)
syscall
	li	$t3	2
	li	$t4	1
	mult  $t4, $t3
	mflo $t5
	move	$t6,	$t5
	sw  $t6, dad  
	lw $t0, dad 
	li  $v0, 1
	la  $a0, ($t0)
syscall
	li	$t2	2
	lw $t3, dad
	mult  $t3, $t2
	lw $t1, dad
	mflo $t4
	mult  $t4, $t1
	mflo $t5
	move	$t6,	$t5
	sw  $t6, dad  
	lw $t0, dad 
	li  $v0, 1
	la  $a0, ($t0)
syscall
	li	$t2	2
	lw $t3, dad
	div  $t3, $t2
	li	$t1	2
	mflo $t4
	div  $t4, $t1
	mflo $t5
	move	$t6,	$t5
	sw  $t6, dad  
	lw $t0, dad 
	li  $v0, 1
	la  $a0, ($t0)
syscall
	jal  COOL 
	b exit
COOL:	sub $sp, $sp,8
	sw $ra, 0($sp) 
	sub $sp, $sp,4
	li	$t2,	9999
	sw  $t2, 8($sp) 
	lw $t3, 8($sp)
	li  $v0, 1
	la  $a0, ($t3)
syscall
	li	$t4	9999
	lw $t5, 8($sp)
	div  $t5, $t4
	mflo $t6
	move	$t0,	$t6
	sw  $t0, 8($sp) 
	lw $t1, 8($sp)
	li  $v0, 1
	la  $a0, ($t1)
syscall
	addiu $sp, $sp,4
	lw $ra, 0($sp) 
	addiu $sp, $sp, 4
	jr $ra
exit: 
	li	$v0, 10
syscall
	.data
dad:	 .word	 0
