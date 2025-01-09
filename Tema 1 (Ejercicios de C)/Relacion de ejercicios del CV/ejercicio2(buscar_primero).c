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

int countElements(struct Nodo* head){
    int counter = 0; 
    struct Nodo* actual = head; 
    while(actual!=NULL){
        counter++; 
        actual=actual->siguiente; 
    }
    return counter;
}

int buscarPrimero(struct Nodo* head, int num){
    struct Nodo* actual = head; 
    int pos = 0; 
    bool found = false; 
    
    while(actual!=NULL && !found){
        if(actual->value==num){
            found=true; 
        }else{
            actual=actual->siguiente;
            pos++;
        }
    }
    
    if(!found){
        pos=countElements(head); 
    }
    
    return pos; //Si no esta el numero en la lista, devolvera el total de elementos
}

int main(){
    struct Nodo* lista = NULL; 
    
    addAtEnd(&lista, 1); 
    addAtEnd(&lista, 5); 
    addAtEnd(&lista, 5); 
    
    printList(lista); 
    
    int numToSearch = 5; 
    int position = buscarPrimero(lista, numToSearch); 
    printf("La primera aparicion del numero %d  es en la posicion: %d\n", numToSearch, position); 
    
    numToSearch = 69; 
    position = buscarPrimero(lista, numToSearch); 
    printf("El numero %d no esta en la lista, devolvemos el tamaño de la lista: %d\n", numToSearch, position); 
    
    return 0; 
}