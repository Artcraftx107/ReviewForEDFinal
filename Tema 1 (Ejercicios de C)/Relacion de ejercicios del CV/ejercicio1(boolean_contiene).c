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

int main(){
    struct Nodo* lista = NULL; 
    
    addAtEnd(&lista, 1); 
    addAtEnd(&lista, 5); 
    addAtEnd(&lista, 6); 
    
    printList(lista); 
    
    int numToSearch = 5; 
    if(contiene(lista, numToSearch)){
        printf("La lista contiene el numero %d \n", numToSearch);
    }else{
        printf("La lista no contiene el numero %d \n", numToSearch);
    }
    
    return 0; 
}