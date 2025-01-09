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

void invertir(struct Nodo** head){
    struct Nodo* actual = *head; 
    struct Nodo* anterior = NULL; 
    struct Nodo* siguiente = NULL; 
    
    while(actual!=NULL){
        siguiente=actual->siguiente; 
        actual->siguiente=anterior; 
        anterior=actual; 
        actual=siguiente; 
    }
    
    *head=anterior; 
}

int main(){
    struct Nodo* lista = NULL;

    addAtEnd(&lista, 1);
    addAtEnd(&lista, 2);
    addAtEnd(&lista, 3);
    addAtEnd(&lista, 4); 

    printf("Lista original: ");
    printList(lista);
    printf("\n"); 

    invertir(&lista); 
    
    printf("Lista invertida: "); 
    printList(lista); 
    
    return 0;
}