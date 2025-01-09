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
void deleteFirstElem(struct Nodo** head, int num){
    struct Nodo* actual = *head; 
    struct Nodo* anterior = NULL; 
    
    while(actual!=NULL){
        if(actual->value==num){
            if(anterior == NULL){
                *head = actual->siguiente;
            }else{
                anterior->siguiente = actual->siguiente; 
            }
            free(actual); 
            return; 
        }
        anterior=actual; 
        actual=actual->siguiente; 
    }
    
    //si num no se encuentra, no hacemos nada
}

int main(){
    struct Nodo* lista = NULL;

    addAtEnd(&lista, 2);
    addAtEnd(&lista, 30);
    addAtEnd(&lista, 5);

    printf("Lista original: ");
    printList(lista);
    printf("\n"); 

    int numeroEliminar = 2;
    deleteFirstElem(&lista, numeroEliminar);
    printf("Lista despues de eliminar el primer %d: ", numeroEliminar);
    printList(lista);
    printf("\n"); 

    numeroEliminar = 6;
    deleteFirstElem(&lista, numeroEliminar);
    printf("Lista despues de intentar eliminar el %d (no estaba en la lista): ", numeroEliminar);
    printList(lista);
    printf("\n"); 
    
    numeroEliminar = 5; 
    deleteFirstElem(&lista, numeroEliminar); 
    printf("Lista despues de intentar eliminar el %d: ", numeroEliminar); 
    printList(lista);
    printf("\n"); 
    
    return 0;
}