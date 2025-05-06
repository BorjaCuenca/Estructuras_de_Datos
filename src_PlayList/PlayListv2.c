#include "PlayListv2.h"

void PlayList_create(struct PlayList *myPlayList) {
  if (myPlayList == NULL) {
    printf("El puntero a la Playlist es NULL.\n");
    return;
  }
  myPlayList->currentsong = NULL;
  myPlayList->head = NULL;  
  
}

struct Node * createNode(const char* value) {
  struct Node* ptrNode = malloc (sizeof(struct Node));
  if (ptrNode == NULL){
    printf("No hay suficiente espacio en memoria.\n");
    return NULL;
  } 
  if (strlen(value)>MAX_SONG_LENGTH){
    printf("El nombre es demasiado largo.\n");
    free(ptrNode);
    return NULL;
  } 
  strcpy(ptrNode->data, value);
  ptrNode->prev = NULL;
  ptrNode->next = NULL;
  return ptrNode;
}

int PlayList_insertAtFront(struct PlayList *myPlayList, const char* value) {
  if (myPlayList == NULL) {
    printf("La Playlist no está inicializada.\n");
    return -1;
  } 
  
  struct Node* newNode = createNode (value);
  if (newNode == NULL) {
    printf("No se pudo crear el nodo a insertar.\n");
    return -1;
  }
  if (myPlayList->head == NULL){
    myPlayList->head = newNode;
    myPlayList->currentsong = newNode;
    return 0;
  }
  newNode->next = myPlayList->head;
  myPlayList->head->prev = newNode;
  myPlayList->head = newNode;
  return 0;
}

int PlayList_insertInOrder(struct PlayList *myPlayList, const char* value) {
  struct Node* ptrNode = createNode (value);
  if (ptrNode == NULL) {
    return -1;
  }
  if (strcmp(myPlayList->head->data, value)==0){
    printf("No puedes añadir dos veces la misma cancion.\n");
    return -1;
  }
  if (myPlayList == NULL) {
    return PlayList_insertAtFront(myPlayList, value);
  } 

  struct Node* aux = myPlayList->head;

  while (strcmp(aux->data, value)>=0){
    if (strcmp(aux->data, value)==0){
      printf("No puedes añadir dos veces la misma cancion.\n");
      return -1;
    }
    aux = aux->next;
  }
  if (aux->prev == NULL){
    return PlayList_insertAtFront(myPlayList, value);
  }
  aux->prev->next = ptrNode;
  ptrNode->prev = aux->prev;
  ptrNode->next = aux;
  aux->prev = ptrNode;
  
  return 0;
}

int PlayList_insertAtEnd(struct PlayList *myPlayList, const char* value) {
  if (myPlayList == NULL || myPlayList->head == NULL){
    return PlayList_insertAtFront(myPlayList, value);
  }
  struct Node* lastSong = myPlayList->head;
  while (lastSong->next != NULL){
    lastSong = lastSong->next;
  }
  struct Node* newNode = createNode(value);
  if (newNode == NULL){
    printf("No se pudo añadir la canción.\n");
    return -1;
  }
  lastSong->next = newNode;
  newNode->prev = lastSong;
  return 0;
}

int PlayList_insertAfter(struct PlayList *myPlayList, const char *cancion, const char* value) {
  if (myPlayList == NULL) {
    printf("El puntero a la Playlist es NULL.\n");
    return -1;
  }

  struct Node* iter = myPlayList->head;
  bool encontrado = false;
  while (iter != NULL && !encontrado) {
    if (strcmp(iter->data, cancion) == 0){
      encontrado = true;
      struct Node* newNode = createNode(value);
      if (newNode == NULL) {
        printf("No se pudo crear la canción.\n");
        return -1;
      }
      newNode->next = iter->next;
      iter->next->prev = newNode;
      iter->next = newNode;
      newNode->prev = iter;
      return 0;
    }
    iter = iter->next;
  }
  if (!encontrado) {
    printf("No se encontró la canción.\n");
    return -1;
  }
}

int PlayList_insertBefore(struct PlayList *myPlayList, const char *cancion, const char* value) {
  if (myPlayList == NULL) {
    printf("El puntero a la Playlist es NULL.\n");
    return -1;
  }

  struct Node* iter = myPlayList->head;
  bool encontrado = false;
  while (iter != NULL && !encontrado) {
    if (strcmp(iter->data, cancion) == 0){
      encontrado = true;
      struct Node* newNode = createNode(value);
      if (newNode == NULL) {
        printf("No se pudo crear la canción.\n");
        return -1;
      }
      iter->prev->next = newNode;
      newNode->prev = iter->prev;
      iter->prev = newNode;
      newNode->next = iter;
      return 0;
    }
    iter = iter->next;
  }
  if (!encontrado) {
    printf("No se encontró la canción.\n");
    return -1;
  }
}

void PlayList_deleteFromFront(struct PlayList *myPlayList) {
  if (myPlayList == NULL) {
    printf("El puntero a la Playlist es NULL.\n");
    return;
  }
  if (myPlayList->head == NULL) {
    printf("La Playlist ya está vacía.\n");
    return;
  }
  struct Node* first = myPlayList->head;
  myPlayList->head = first->next;
  free(first);
}

int PlayList_deleteSong(struct PlayList *myPlayList, const char *value) {
  if (myPlayList == NULL) {
    printf("El puntero a la Playlist es NULL.\n");
    return 0;
  }

  struct Node* iter = myPlayList->head;
  bool encontrado = false;
  while (iter != NULL && !encontrado) {
    if (strcmp(iter->data, value) == 0){
      encontrado = true;
      if (iter == myPlayList->head){
        PlayList_deleteFromFront(myPlayList);
        return 0;
      }
      iter->prev->next = iter->next;
      iter->next->prev = iter->prev;
      free (iter);
      return 1;
    }
    iter = iter->next;
  }
  if (!encontrado) {
    printf("No se encontró la canción.\n");
    return 0;
  }
}

void PlayList_print (struct PlayList myPlayList) {
  struct Node* iter = myPlayList.head;
  printf("Reproduciendo: %s\n", myPlayList.currentsong);
  printf("En cola: \n");
  while (iter != NULL) {
    printf("%s\n", iter->data);
    iter = iter->next;
  }
}

void PlayList_order(struct PlayList *myPlayList) {
  if (myPlayList == NULL) {
    printf("El puntero a la Playlist es NULL.\n");
    return;
  }
  
  struct Node* iter1 = myPlayList->head;
  struct Node* iter2 = myPlayList->head;
  while (iter1 != NULL){
    while (iter2 != NULL){
      if (strcmp(iter2->data, iter1->data) < 0){
        char temp[MAX_SONG_LENGTH];
        strcpy(temp, iter1->data);
        strcpy(iter1->data, iter2->data);
        strcpy(iter2->data, temp);
      }
      iter2 = iter2->next;
    }
    iter2 = myPlayList->head;
    iter1 = iter1->next;
  }
}

void PlayList_deleteAll (struct PlayList *myPlayList) {
  if (myPlayList == NULL){
    printf("El puntero a la Playlist es NULL.\n");
    return;
  }
  struct Node* iter = myPlayList->head;
  while (iter != NULL) {
    myPlayList->head = iter->next;
    free(iter);
  }
}

char *PlayList_currentSong (struct PlayList myPlayList) {
  if (myPlayList.currentsong == NULL) {
    return "No se está reproduciendo ninguna canción.\n";
  }
  return myPlayList.currentsong->data;
}

void PlayList_forwardSong (struct PlayList *myPlayList) {
  if (myPlayList->currentsong == NULL){
    printf("No se está reproduciendo ninguna canción.\n");
    return;
  }
  myPlayList->currentsong = myPlayList->currentsong->next;
  
}

void PlayList_backwardSong (struct PlayList *myPlayList) {
  if (myPlayList->currentsong == NULL){
    printf("No se está reproduciendo ninguna canción.\n");
    return;
  }
  myPlayList->currentsong = myPlayList->currentsong->prev;
}

