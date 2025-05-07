#include <stdio.h>

int main (){
  int v1=33;
  int *ptr;
  ptr= &v1;

  printf("Valor del puntero: %d %s", *ptr, "\n");
  printf("Direccion de memoria: %p", ptr);

  return 0; 
}