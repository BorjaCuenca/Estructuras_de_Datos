#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>

typedef struct Node *ptrNode; // Alias para un puntero a la estructura
typedef struct Node
{
    int data;
    ptrNode next;
} Node;// Alias para la estructura

/**
 * @brief Itera y muestra por pantalla la lista enlazada
 * @param head Puntero al primer nodo de la lista enlazada
 */
void iterar(ptrNode head)
{
    if (head == NULL) {
      printf("iterar: No hay elementos en la lista.\n");
      //exit(-1);
    }
    else {
      ptrNode aux = head;

      while (aux != NULL) {
        printf("Elemento: %d\n", aux->data);
        aux = aux->next;
      }
    }
}

/**
 * @brief Inserta un nodo en la lista enlazada
 * @param ptrhead  Puntero al primer nodo de la lista enlazada
 * @param data    Dato a insertar en el nodo
 * @return true si se insertó correctamente, false en caso contrario
 */
bool insertar(ptrNode *ptrhead, int data)
{
    if (ptrhead == NULL){
      printf("insertar: El puntero a la lista es NULL");
      return false;
    }

    ptrNode newNode = malloc (sizeof (struct Node));

    if (newNode == NULL) {
      printf ("insertar: No hay espacio suficiente.");
      return false;
    }
    else {
      newNode->next = NULL;
      newNode->data = data;

      if (*ptrhead == NULL) {
        *ptrhead = newNode;
      }
      else {
        ptrNode aux = *ptrhead;

        while (aux->next != NULL) {
          aux = aux->next;
        }

        aux->next = newNode;
      }
    }
    
    return true;
}

/**
 * @brief Elimina un nodo de la lista enlazada
 * @param ptrhead Puntero al primer nodo de la lista enlazada
 * @param data  Dato a eliminar del nodo
 * @return true si se eliminó correctamente, false en caso contrario
 */
bool eliminar(ptrNode *ptrhead, int data)
{
    bool encontrado = false;

    if (ptrhead == NULL) {
      printf("eliminar: El puntero a la lista es NULL");
      return encontrado;
    }

    while (*ptrhead != NULL && (*ptrhead)->data == data) {
      encontrado = true; 
      ptrNode aux = *ptrhead;
      *ptrhead = (*ptrhead)->next;
      free(aux);
    }

    if (*ptrhead == NULL) {
        return encontrado;
    }

    ptrNode prev = *ptrhead;
    ptrNode aux = prev->next;

    while (aux != NULL) {
      if (aux->data == data) {
        encontrado = true;
        prev->next = aux->next;
        free(aux);
        aux = prev->next;
      }
      else{  
        prev = aux;
        aux = aux->next;
      }
    }

    return encontrado;
}

/**
 * @brief Destruye la lista enlazada
 * @param ptrhead Puntero al primer nodo de la lista enlazada. Al finalizar la función, ptrhead apuntará a NULL
 */
void destruir(ptrNode *ptrhead)
{
    if (ptrhead == NULL) {
      printf("destruir: El puntero a la lista es NULL");
      return;
    }
    ptrNode aux;

    while (*ptrhead != NULL) {
      aux = *ptrhead;
      *ptrhead = (*ptrhead)->next;
      free(aux);
    }
}

int main(void)
{
    ptrNode head = NULL;

    // Primero probamos a usar las funciones con una lista NULL
    iterar(head);
    assert(eliminar(&head, 1) == false);

    // Insertar un nodo 1 en cabeza
    assert(insertar(&head, 1) == true);

    // Insertar otro nodo 1, la lista acepta duplicados
    assert(insertar(&head, 1) == true);

    // Insertar otro nodo 5 en la cola
    assert(insertar(&head, 5) == true);

    // Insertar otro nodo 4 en la cola
    assert(insertar(&head, 4) == true);

    // Mostramos la lista
    iterar(head);

    // Eliminar un nodo en cabeza, debe dar true
    assert(eliminar(&head, 1) == true);

    // Eliminar un nodo en cola, debe dar true
    assert(eliminar(&head, 5) == true);

    // Eliminar un nodo que no existe, debe dar false
    assert(eliminar(&head, 99) == false);

    // Mostramos la lista
    iterar(head);

    // Destruir la lista enlazada
    destruir(&head);

    assert(head == NULL);

    return 0;
}