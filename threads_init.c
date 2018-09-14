#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NUM_THREADS 30

//Fazendo chamadas diretas a System Calls
//#include <unistd.h>
//#include <sys/syscall.h>

void *print_hello(void *thread_id) {
	long tid;
	tid = (long) thread_id;

	printf("Ola mundo! Eu sou a thread #%ld!\n", tid);
	pthread_exit(NULL);

}


int main(int argc, char *argv[]) {
	
    pthread_t threads[NUM_THREADS];
	int rc;
	long t;

	for(t=0; t<NUM_THREADS; t++) {

		printf("Estou no main: Criando a thread %Id\n", t);
		rc = pthread_create(&threads[t], NULL, print_hello, (void *)t);

		sleep(5);

		if(rc) {
			printf("Erro. O código retornado de pthread_create() eh %d\n", rc);
			exit(-1);
		}

	}

	pthread_exit(NULL);

}
