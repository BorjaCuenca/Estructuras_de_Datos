#include <stdio.h>

int main () {
  int a[5] ={1,2,3,4,5};
  int *ptr = a;
  int i=0;

  while (i<5){
    printf("%d", *(ptr+i));
    i++;
  }

  return 0;
}
