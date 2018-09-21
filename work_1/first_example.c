//#include <stdio.h>

//Fazendo chamadas diretas a System Calls
#include <unistd.h>
#include <sys/syscall.h>

int main(void) {
	int a=1;
	
	//write(1, "Hello World\n", 14);
	syscall(SYS_write, 1, "Hello, World\n",14)

	
	//printf("Hello, first_example\n");
	
	/*
	while(a) {
		//scanf("Enter: %d" , &a);
		printf("E ai\t");
	}*/

	return 0;
}
