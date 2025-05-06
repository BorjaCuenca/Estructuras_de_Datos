/*
 * GestionMemoria.c
 *
 *  Created on: 
 *      Author:
 */
#include <stdio.h>
#include <stdlib.h>
#include "GestionMemoria.h"



/* Crea la estructura utilizada para gestionar la memoria disponible. */
void Crear(T_manejador *Manejador) {
  *Manejador = malloc (sizeof(struct T_Nodo));
  if (*Manejador == NULL) {
    printf ("Crear: No hay suficiente memoria.");
    return;
  }
  (*Manejador)->inicio = 0;
  (*Manejador)->fin = MAX;
}

/* Destruye la estructura utilizada. */
void Destruir(T_manejador* manejador) {
  if (manejador == NULL) {
    printf ("Destruir: El puntero a la lista es NULL.");
    return;
  }
  T_manejador aux = *manejador;
  while (*manejador != NULL) {
    aux = *manejador;
    *manejador = (*manejador)->sig;
    free (aux);
  }
}

/* Devuelve en �dir� la direcci�n de memoria donde comienza el
 * trozo de memoria continua de tama�o �tam� solicitada.
 * Si la operaci�n se pudo llevar a cabo, es decir, existe dicho
 * trozo, devolvera TRUE en �ok�; FALSE en otro caso.
 */
void Obtener(T_manejador* manejador,unsigned tam, unsigned* dir, unsigned* ok) {

  *ok = 0;

  if (manejador == NULL) {
    printf ("Obtener: El puntero a la lista es NULL.");
    return;
  }

  if (*manejador == NULL) {
    printf("Obtener: La lista está vacia.");
    return;
  }

  //Primero vemos si cabe en el primer nodo. En ese caso lo modificamos
  if ((*manejador)->fin - (*manejador)->inicio + 1 > tam){
    *dir = (*manejador)->inicio;
    (*manejador)->inicio += tam;
    *ok = 1;
  }
  //Si cabe, pero no deja espacio libre, eliminamos el primer nodo
  else if ((*manejador)->fin - (*manejador)->inicio + 1 == tam){
    *dir = (*manejador)->inicio;
    T_manejador aux = *manejador;
    *manejador = (*manejador)->sig;
    free(aux);
    *ok = 1;
  }
  //Si no cabe en el primero iteramos la lista hasta que quepa
  else { 
    T_manejador iter = (*manejador)->sig;
    T_manejador prev = *manejador;

    while (iter != NULL) {
      //Si cabe en el nodo, lo modificamos
      if (iter->fin - iter->inicio + 1 > tam){
        *dir = iter->inicio;
        *ok = 1;
        iter->inicio += tam;
        return;
      }
      //Si cabe, pero no deja espacio libre, eliminamos el nodo
      else if (iter->fin - iter->inicio + 1 == tam){
        *dir = iter->inicio;
        *ok = 1;
        prev->sig = iter->sig;
        free(iter);
        return;
      }
    }
  }
  
}

/* Devuelve el trozo de memoria continua de tama�o �tam� y que
 * comienza en �dir�.
 * Se puede suponer que se trata de un trozo obtenido previamente.
 */
void Devolver(T_manejador* manejador,unsigned tam,unsigned dir) {
  if (manejador == NULL) {
    printf ("Devolver: El puntero a la lista es NULL.");
    return;
  }

  if (*manejador == NULL) {
    printf("Devolver: La lista está vacia.");
    return;
  }

  //Primero vemos si va al principio. 
  //Si va al principio y hay espacio en medio, añadimos un nuevo nodo
  if ((*manejador)->inicio > dir + tam){

    //Creamos un nodo con los datos necesarios
    T_manejador newNode = malloc (sizeof (struct T_Nodo));
    if (newNode == NULL) {
      printf ("Devolver: No hay suficiente memoria.");
      return;
    }

    newNode->inicio = dir;
    newNode->fin = dir + tam -1;
    newNode->sig = NULL;
    //Fin de la creacion del nodo

    newNode->sig = *manejador;
    *manejador = newNode;
  }
  //Si va al principio pero NO hay espacio en medio, modificamos el primer nodo
  else if ((*manejador)->inicio == dir + tam){
    (*manejador)->inicio = dir;
  }
  //Si NO va al principio, iteramos hasta que encontremos el lugar
  else {
    T_manejador prev = *manejador;
    T_manejador iter = prev->sig;

    while (iter != NULL) {
      //Si va aqui y hay espacio en medio, añadimos un nuevo nodo
      if (iter->inicio > dir + tam){

        //Creamos un nodo con los datos necesarios
        T_manejador newNode = malloc (sizeof (struct T_Nodo));
        if (newNode == NULL) {
          printf ("Devolver: No hay suficiente memoria.");
          return;
        }

        newNode->inicio = dir;
        newNode->fin = dir + tam -1;
        newNode->sig = NULL;
        //Fin de la creacion del nodo

        prev->sig = newNode;
        newNode->sig = iter;
        return;
      }
      //Si va aqui pero NO hay espacio en medio, modificamos el nodo
      else if (iter->inicio == dir + tam){
        iter->inicio = dir;
        return;
      }
      prev = iter;
      iter = iter->sig;
    }
    //Si el programa llega aqui, es que iter es NULL, es decir, va al final de la lista
    //Si NO hay espacio en medio, modificamos el ultimo nodo
    if (prev->fin + 1 == dir){
      prev->fin = dir + tam - 1;
    }
    //Si hay espacio en medio, añadimos un nuevo nodo
    else {

      //Creamos un nodo con los datos necesarios
      T_manejador newNode = malloc (sizeof (struct T_Nodo));
      if (newNode == NULL) {
        printf ("Devolver: No hay suficiente memoria.");
        return;
      }

      newNode->inicio = dir;
      newNode->fin = dir + tam -1;
      newNode->sig = NULL;
      //Fin de la creacion del nodo

      prev->sig = newNode;
    }
    
  }
  
}

/* Muestra el estado actual de la memoria */
void Mostrar (T_manejador manejador) {
  T_manejador iter = manejador;
  int contador = 1;
  while (iter != NULL){
    printf("Nodo %d:\n" ,contador);
    printf("  Inicio: %d\n", iter->inicio);
    printf("  Fin: %d\n", iter->fin);
    iter = iter->sig;
    contador++;
  }
}
