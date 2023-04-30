#include <stdio.h>

int main() {
    int x, y;

    printf("Escolhe um número:\n");
    scanf("%d", &x);
    printf("Escolhe o segundo número:\n");
    scanf("%d", &y);

    if(x>y) {
        printf("1");
        return 0;
    }
    else if(x==y) {
        printf("0");
        return 0;
    }
    else if(x<y) {
        printf("-1");
        return 0;
    }


}