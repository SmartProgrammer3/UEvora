#include <stdio.h>
#include <string.h>

int main() {
    char string[20];
    printf("string tem %ld bytes\n", sizeof(string));
    strcpy(string, "abcde");
    printf("string conta com %ld caracteres\n", strlen(string));
    strcpy(string, "texto longo");
    printf("string conta com %ld caracteres\n", strlen(string));
    strcpy(string, "s");
    printf("string conta com %ld caracteres\n", strlen(string));
}