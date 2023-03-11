#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

void forkTest() {
puts("TESTE FORK:\n");
pid_t pid;
pid = fork(); //cria processo
if(pid > 0) {
printf(" Processo Pai PID: %d \n", pid);
}
else {
printf(" Processo Filho PID: %d \n", pid);
}
puts("FIM\n");
}
void main()
{
forkTest();
}