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

void deleteRepeated(struct Nodo** head, int numero){
    struct Nodo* actual = *head; 
    struct Nodo* anterior = NULL; 
    int repeticiones = 0; 
    
    while(actual!=NULL){
        if(actual->value == numero && repeticiones>0){
            if(anterior==NULL){
                *head = actual->siguiente; 
                free(actual); 
                actual=*head; 
            }else{
                anterior->siguiente = actual->siguiente; 
                free(actual); 
                actual = anterior->siguiente; 
            }
        }else{
            if(actual->value==numero){
                repeticiones++; 
            }
            
            anterior=actual; 
            actual=actual->siguiente; 
        }
    }
}

int main(){
    struct Nodo* lista = NULL; 
    
    addAtEnd(&lista, 1); 
    addAtEnd(&lista, 3); 
    addAtEnd(&lista, 6);
    addAtEnd(&lista, 3);
    addAtEnd(&lista, 4);
    addAtEnd(&lista, 3);
    addAtEnd(&lista, 3);
    addAtEnd(&lista, 7); 
    
    printf("Lista original: "); 
    printList(lista); 
    printf("\n"); 
    
    int numToCount = 3; 
    deleteRepeated(&lista, numToCount); 
    printf("\nLista despues de eliminar repeticiones del %d (excepto la primera):\n", numToCount);
    printList(lista); 
    
    return 0; 
}