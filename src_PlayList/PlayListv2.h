
// PLAYLIST.h

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#ifndef PLAYLISTV2_H
#define PLAYLISTV2_H

/**
 * @brief Estructura para un nodo de la lista
 * 
 */
 
#define MAX_SONG_LENGTH 30


struct Node {
    char data[MAX_SONG_LENGTH];
    struct Node* prev;
    struct Node* next;
};

struct PlayList {
    struct Node *currentsong;
    struct Node *head;
};




/**
 * @brief Initializes an empty playlist
 * @param myPlayList Pointer to the PlayList structure to be initialized
 */
void PlayList_create(struct PlayList *myPlayList);

/**
 * @brief Creates a new node with the given value
 * @param value The string to be stored in the node
 * @return Pointer to the newly created node, or NULL if creation fails
 */
struct Node * createNode(const char* value);

/**
 * @brief Inserts a new node at the front of the playlist
 * @param myPlayList Pointer to the PlayList structure
 * @param value The string to be inserted
 * @return 0 if successful, -1 if failed
 */
int PlayList_insertAtFront(struct PlayList *myPlayList, const char* value);

/**
 * @brief Inserts a new node in the playlist in alphabetical order
 * @param myPlayList Pointer to the PlayList structure
 * @param valor The string to be inserted
 * @return 0 if successful, -1 if failed
 */
int PlayList_insertInOrder(struct PlayList *myPlayList, const char* value);

/**
 * @brief Inserts a new node at the end of the playlist
 * @param myPlayList Pointer to the PlayList structure
 * @param value The string to be inserted
 * @return 0 if successful, -1 if failed
 */
int PlayList_insertAtEnd(struct PlayList *myPlayList, const char* value);

/**
 * @brief Inserts a new node after a specified node in the playlist
 * @param myPlayList Pointer to the PlayList structure
 * @param cancion The string to search for in the playlist
 * @param value The string to be inserted
 * @return 0 if successful, -1 if failed
 */

int PlayList_insertAfter(struct PlayList *myPlayList, const char *cancion, const char* value);


/**
 * @brief Inserts a new node before a specified node in the playlist
 * @param myPlayList Pointer to the PlayList structure
 * @param cancion The string to search for in the playlist
 * @param value The string to be inserted
 * @return 0 if successful, -1 if failed
 */

int PlayList_insertBefore(struct PlayList *myPlayList, const char *cancion, const char* value);

/**
 * @brief Deletes the first node from the playlist
 * @param myPlayList Pointer to the PlayList structure
 */
void PlayList_deleteFromFront(struct PlayList *myPlayList);

/**
 * @brief Deletes a node with the given value from the playlist
 * @param myPlayList Pointer to the PlayList structure
 * @param value The string to be deleted
 * @return 1 if the node was found and deleted, 0 otherwise
 */
int PlayList_deleteSong(struct PlayList *myPlayList, const char *value);



/**
 * @brief Prints the contents of the playlist
 * @param myPlayList The PlayList structure to be printed
 */
void PlayList_print (  struct PlayList myPlayList);



/**
 * @brief Sorts the playlist in alphabetical order using bubble sort
 * @param myPlayList Pointer to the PlayList structure
 */
void PlayList_order(struct PlayList *myPlayList);

/**
 * @brief Deletes all nodes from the playlist
 * @param myPlayList Pointer to the PlayList structure
 */

void PlayList_deleteAll (struct PlayList *myPlayList);


/**
  * @brief Return current song
  * 
  * @param myPlayList PlayList structure
  * @return * char* with de current song
  */
char *PlayList_currentSong (struct PlayList myPlayList);

/**
 * @brief Shift current song to the next. No action if last
 * 
 * @param myPlayList Pointer to the PlayList structure
 */

void PlayList_forwardSong (struct PlayList *myPlayList);

/**
 * @brief shift current song to the previous. No action if first
 * 
 * @param myPlayList Pointer to the PlayList structure
 */
void PlayList_backwardSong (struct PlayList *myPlayList);

#endif // PLAYLISTV2_H
