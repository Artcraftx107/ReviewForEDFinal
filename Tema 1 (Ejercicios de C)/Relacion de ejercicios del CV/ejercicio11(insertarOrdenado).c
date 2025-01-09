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

bool contiene(struct Nodo* head, int num){
    bool aux = false; 
    struct Nodo* actual = head; 
    while(actual!=NULL && !aux){
        if(actual->value == num){
            aux = true; 
        }
        actual=actual->siguiente; 
    }
    return aux;
}

void printList(struct Nodo* head){
    struct Nodo* actual = head; 
    while(actual != NULL){
        printf("%d -> ", actual->value); 
        actual=actual->siguiente; 
    }
    printf("NULL\n"); 
}

void insertInOrder(struct Nodo** head, int num){
    struct Nodo* newNodo = createNodo(num); 
    
    if(*head==NULL || (*head)->value > num){
        newNodo->siguiente = *head; 
        *head = newNodo; 
    }else{
        struct Nodo* actual = *head; 
        while(actual->siguiente !=NULL && actual->siguiente->value < num){
            actual=actual->siguiente; 
        }
        
        newNodo->siguiente = actual->siguiente; 
        actual->siguiente=newNodo; 
        
    }
}

int main(){
    struct Nodo* lista = NULL; 
    
    insertInOrder(&lista, 5); 
    insertInOrder(&lista, 3); 
    insertInOrder(&lista, 2); 
    insertInOrder(&lista, 1); 
    
    printf("Lista original: "); 
    printList(lista); 
    printf("\n"); 
    
    int numToAdd = 4; 
    insertInOrder(&lista, numToAdd); 
    
    printf("Lista tras añadir %d: ", numToAdd); 
    printList(lista); 
    
    return 0; 
}