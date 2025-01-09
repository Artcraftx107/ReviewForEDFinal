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

void deleteAllRepeats(struct Nodo** head){
    struct Nodo* actual = *head; 
    struct Nodo* nextNodo; 
    
    while(actual!=NULL){
        int numReps = repeticiones(*head, actual->value); 
        
        nextNodo = actual->siguiente; 
        
        if(numReps>1){
            deleteElem(head, actual->value); 
        }
        
        actual = nextNodo; 
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
    addAtEnd(&lista, 4); 
    
    printf("Lista original: "); 
    printList(lista); 
    printf("\n"); 
    
    deleteAllRepeats(&lista); 
    
    printf("Lista sin repetidos: "); 
    printList(lista); 
    
    return 0; 
}