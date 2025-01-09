#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct Nodo {
    int value; 
    struct Nodo* siguiente; 
}; 

struct Nodo* createNodo(int value){
    struct Nodo* newNodo = (struct Nodo*)malloc(sizeof(struct Nodo)); 
    newNodo->value = value; 
    newNodo->siguiente = NULL; 
    return newNodo; 
}


void addAtEnd(struct Nodo** head, int value){
    struct Nodo* newNodo= createNodo(value); 
    if(*head == NULL){
        *head=newNodo; 
    }else{
        struct Nodo* actual = *head; 
        while(actual->siguiente != NULL){
            actual=actual->siguiente;
        }
        actual->siguiente=newNodo;
    }
}

void printList(struct Nodo* head){
    struct Nodo* actual = head; 
    while(actual != NULL){
        printf("%d -> ", actual->value); 
        actual=actual->siguiente; 
    }
    printf("NULL\n"); 
}

int countElements(struct Nodo* head){
    int counter = 0; 
    struct Nodo* actual = head; 
    while(actual!=NULL){
        counter++; 
        actual=actual->siguiente; 
    }
    return counter;
}

void deleteElem(struct Nodo** head, int num){
    struct Nodo* actual = *head; 
    struct Nodo* anterior = NULL; 
    
    while(actual!=NULL){
        if(actual->value==num){
            if(anterior==NULL){
                *head = actual->siguiente; 
                free(actual); 
                actual = *head;
            }else{
                anterior->siguiente = actual->siguiente; 
                free(actual); 
                actual=anterior->siguiente; 
            }
        }else{
            anterior=actual; 
            actual=actual->siguiente; 
        }
    }
}

int main(){
    struct Nodo* lista = NULL;

    addAtEnd(&lista, 2);
    addAtEnd(&lista, 2);
    addAtEnd(&lista, 6);
    addAtEnd(&lista, 2); 

    printf("Lista original: ");
    printList(lista);
    printf("\n"); 

    int numeroEliminar = 2;
    deleteElem(&lista, numeroEliminar);
    printf("Lista despues de eliminar el ultimo %d: ", numeroEliminar);
    printList(lista);
    printf("\n"); 

    numeroEliminar = 7;
    deleteElem(&lista, numeroEliminar);
    printf("Lista despues de intentar eliminar el %d (no estaba en la lista): ", numeroEliminar);
    printList(lista);
    printf("\n"); 
    
    return 0;
}