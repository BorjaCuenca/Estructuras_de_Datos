/*
 * GestionMemoria.h
 *
 *  Created on: 
 *      Author: 
 */

//#ifndef GESTIONMEMORIA_H_
//#define GESTIONMEMORIA_H_

unsigned const MAX = 99;

typedef struct T_Nodo* T_manejador;

	struct T_Nodo {
			unsigned inicio;
			unsigned fin;
			T_manejador sig;
		};

// Crea la estructura utilizada para gestionar la memoria disponible.
	void Crear(T_manejador* Manejador);

// Destruye la estructura utilizada.
	void Destruir(T_manejador* manejador);

/* Devuelve en �dir� la direcci�n de memoria donde comienza el
 * trozo de memoria continua de tama�o �tam� solicitada.
 * Si la operaci�n se pudo llevar a cabo, es decir, existe dicho
 * trozo, devolvera TRUE en �ok�; FALSE en otro caso.
 */
	void Obtener(T_manejador* manejador,unsigned tam, unsigned* dir, unsigned* ok);

/* Devuelve el trozo de memoria continua de tama�o �tam� y que
 * comienza en �dir�.
 * Se puede suponer que se trata de un trozo obtenido previamente.
 */
	void Devolver(T_manejador* manejador,unsigned tam,unsigned dir);

	/* Muestra el estado actual de la memoria */
	void Mostrar (T_manejador manejador);


//#endif /* GESTIONMEMORIA_H_ */
