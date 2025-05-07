//
// Luis Llopis, Data Structures, UMA.
//

#include "Task.h"

#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

struct Task* Task_new(unsigned int id, const char* name, int quantum) {
  if (strlen(name) > MAX_NAME_LEN) {
    printf("Task_new: The name is too long.\n");
    return NULL;
  }
  
  struct Task* ptrTask = malloc (sizeof (struct Task));
  if (ptrTask == NULL) {
    printf("Task_new: There is no suficient memory.\n");
    return ptrTask;
  }
  
  ptrTask->id = id;
  strcpy(ptrTask->name, name);
  ptrTask->quantum = quantum;

  return ptrTask;
}

void Task_free(struct Task** p_p_task) {
  if (p_p_task == NULL) {
    printf("Task_free: The pointer is NULL.\n");
    return;
  }

  if (*p_p_task == NULL) {
    printf("Task_free: The task is already NULL.\n");
  }
  free (*p_p_task);
  *p_p_task = NULL;
}

struct Task* Task_copyOf(const struct Task* p_task) {
  if (p_task == NULL) {
    printf("Task_copyOf: The task pointer is NULL.\n");
    return NULL;
  }

  return Task_new(p_task->id, p_task->name, p_task->quantum);
}

void Task_print(const struct Task* p_task) {
  if (p_task != NULL){
    printf ("ID: %d\n", p_task->id);
    printf ("Name: %s\n", p_task->name);
    printf ("Quantum: %d\n", p_task->quantum);
  }
}