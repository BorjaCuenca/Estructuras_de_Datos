#include <stdio.h>

int main () {
  int numero, suma=0;
  int elementos[100];

  do {
    printf("Introduce el numero de elementos a sumar (max. 100): ");
    scanf("%d", &numero);
  } while (numero<1 || numero>100);

  printf("Introduce los elementos separados por un espacio: ");
  for (int i=0; i<numero; i++){
    scanf("%d", &elementos[i]);
  }
  for (int i=0; i<numero; i++){
    suma+= elementos[i];
  }

  printf("La suma de todos es: %d", suma);
  return 0;
  
}