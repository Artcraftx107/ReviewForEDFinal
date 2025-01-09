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

int repeticiones(struct Nodo* head, int num){
    int counter= 0; 
    struct Nodo* actual = head; 
    
    while(actual!=NULL){
        if(actual->value == num){
            counter++; 
        }
        actual = actual->siguiente; 
    }
    
    return counter; 
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
    int result = repeticiones(lista, numToCount); 
    printf("El numero %d aparece %d veces en la lista \n", numToCount, result); 
    
    numToCount=8; 
    result=repeticiones(lista, numToCount); 
    printf("El numero %d aparece %d veces en la lista", numToCount, result);
    
    return 0; 
}