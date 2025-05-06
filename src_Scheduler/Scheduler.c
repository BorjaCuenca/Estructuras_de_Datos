//
// Luis Llopis, Data Structures, UMA.
//

#include "Scheduler.h"

#include <assert.h>
#include <stdlib.h>
#include <stdio.h>

struct Node* Scheduler_new() {
  return NULL;  // Un planificador vacío está representado por un puntero NULL
}

size_t Scheduler_size(const struct Node* p_last) {
  unsigned contador = 1;
  if (p_last == NULL){
    return 0;
  }
  struct Node* iter = p_last->p_next;
  while (iter!=p_last){
    contador++;
    iter = iter->p_next;
  }
  return contador;
}

void Scheduler_clear(struct Node** p_p_last) {
  if (p_p_last == NULL){
    printf ("Scheduler_clear: The pointer is NULL.\n");
    return;
  }
  if (*p_p_last != NULL) {
    struct Node* iter = (*p_p_last)->p_next;
    struct Node* prev;
    while (iter != *p_p_last){
      prev = iter;
      iter = iter->p_next;
      free(prev);
    }
    free(iter);
    *p_p_last = NULL;
  }
}

struct Task* Scheduler_first(const struct Node* p_last) {
  if (p_last == NULL){
    printf ("Scheduler_first: The scheduler is empty.\n");
    return NULL;
  }
  struct Node* first = p_last->p_next;
  return Task_copyOf(&(first->task));
}

void Scheduler_enqueue(struct Node** p_p_last, const struct Task* p_task) {
  if (p_p_last == NULL) {
    printf("Scheduler_enqueue: The pointer is NULL.\n");
    return;
  }

  if (p_task == NULL) {
    printf("Scheduler_enqueue: The task is NULL.\n");
    return;
  }

  struct Node* newNode = malloc(sizeof(struct Node));
  if (newNode == NULL) {
    printf("Scheduler_enqueue: Couldn't allocate memory for new node.\n");
    return;
  }

  newNode->task = *Task_copyOf(p_task);

  if (*p_p_last == NULL) { 
    newNode->p_next = newNode;
    *p_p_last = newNode;
  } 
  else {
    struct Node* first = (*p_p_last)->p_next;
    newNode->p_next = first;
    (*p_p_last)->p_next = newNode;
    *p_p_last = newNode;
  }
}

void Scheduler_dequeue(struct Node** p_p_last) {
  struct Node* first = (*p_p_last)->p_next;
  (*p_p_last)->p_next = first->p_next;
  free(first);
}

void Scheduler_print(const struct Node* p_last) {
  if (p_last == NULL) {
    printf("Scheduler_print: Scheduler is empty.\n");
    return;
  }
    
  struct Node* iter = p_last->p_next;
  do {
    Task_print(&(iter->task));
    printf("\n");
    iter = iter->p_next;
  } while (iter != p_last->p_next);
}