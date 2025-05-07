#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "PlayListv2.c"

int main(void) {
    struct PlayList myPlayList;
    char song[MAX_SONG_LENGTH];
    int result=0;

    PlayList_create(&myPlayList);
    printf("****Inserto canciones al principio y al final\n");

    printf("****Inserto cancion2 al principio \n");
    result=PlayList_insertAtFront(&myPlayList, "cancion2");
    if (result==0) {
        printf("Canción insertada\n");
    } else {
        printf("Error al insertar la canción\n");
    }
    PlayList_print(myPlayList);

    printf("Desplazo PlayList para atrás\n");
    PlayList_backwardSong(&myPlayList);
    strcpy(song,PlayList_currentSong(myPlayList));
    printf("Canción actual: %s\n", song);

    printf("****Inserto cancion1 al principio\n");
    result=PlayList_insertAtFront(&myPlayList, "cancion1");
    if (result==0) {
        printf("Canción insertada\n");
    } else {
        printf("Error al insertar la canción\n");
    }
    PlayList_print(myPlayList);

    strcpy(song,PlayList_currentSong(myPlayList));
    printf("Canción actual: %s\n", song);

    printf("Inserto cancion3 al final\n");
    result=PlayList_insertAtEnd(&myPlayList, "cancion3");
    if (result==0) {
        printf("Canción insertada\n");
    } else {
        printf("Error al insertar la canción\n");
    }
    PlayList_print(myPlayList);

    printf("Desplazo PlayList para atrás\n");
    PlayList_backwardSong(&myPlayList);
    strcpy(song,PlayList_currentSong(myPlayList));
    printf("Canción actual: %s\n", song);

    printf("Desplazo PlayList para adelante\n");
    PlayList_forwardSong(&myPlayList);
    strcpy(song,PlayList_currentSong(myPlayList));
    printf("Canción actual: %s\n", song);


    printf("Inserto cancion4 al final\n");
    result=PlayList_insertAtEnd(&myPlayList, "cancion4");
    if (result==0) {
        printf("Canción insertada\n");
    } else {
        printf("Error al insertar la canción\n");
    }
    PlayList_print(myPlayList);

    printf("Inserto una canción despues de cancion2\n");
    result=PlayList_insertAfter(&myPlayList,"cancion2","otracancion2");
    if (result==0) {
        printf("Canción insertada\n");
    } else {
        printf("Error al insertar la canción\n");
    }
    PlayList_print(myPlayList);

    printf("Inserto una canción antes de cancion1\n");
    result=PlayList_insertBefore(&myPlayList,"cancion1","otracancion1"); 
    if (result==0) {
        printf("Canción insertada\n");
    } else {
        printf("Error al insertar la canción\n");
    }
    PlayList_print(myPlayList);


    PlayList_order(&myPlayList);
    PlayList_print(myPlayList);


    printf("Borro la cancion1\n");
    result=PlayList_deleteSong(&myPlayList, "cancion1");
    if (result==1) {
        printf("Canción borrada\n");
    } else {
        printf("Error al borrar la canción\n");
    }
    PlayList_print(myPlayList);

    printf("Borro la cancion no existe\n");
    result=PlayList_deleteSong(&myPlayList, "no existe");
    if (result==1) {
        printf("Canción borrada\n");
    } else {
        printf("Error al borrar la canción\n");
    }
    PlayList_print(myPlayList);

    printf("Elimino el primero\n");
    PlayList_deleteFromFront(&myPlayList);
    PlayList_print(myPlayList);

    printf("Elimino todos\n");
    PlayList_deleteAll (&myPlayList);
    PlayList_print(myPlayList);


    return 0;
}