#include <stdio.h>
#include <conio.h>
#include <stdlib.h>;

struct noh {
       
        int valor;
        struct noh * nohEsq;
        struct noh * nohDir;    
       
        void lerPosOrdem(){
			if(nohEsq != NULL){
      			 nohEsq -> lerPosOrdem();
      		}
			if(nohDir != NULL){
      			 nohDir -> lerPosOrdem();
      		}
      		printf("%d - ", valor);
      	}    
       
        void lerPreOrdem(){
      		printf("%d - ", valor);
			if(nohEsq != NULL){
      			 nohEsq -> lerPreOrdem();
      		}
			if(nohDir != NULL){
      			 nohDir -> lerPreOrdem();
      		}
      	}    
       
        void lerInOrdem(){
			if(nohEsq != NULL){
      			 nohEsq -> lerInOrdem();
      		}
      		printf("%d - ", valor);
			if(nohDir != NULL){
      			 nohDir -> lerInOrdem();
      		}
      	}
      	
      	noh* construirNoh(int val){
             struct noh * nohAux = (struct noh *)malloc(sizeof(struct noh));
             nohAux -> valor = val;
             nohAux -> nohEsq = NULL; 
             nohAux -> nohDir = NULL;
             return nohAux;
        }
      	
        void inserirNohFilho(int val){                
                  if(val < valor){
                           if(nohEsq == NULL){
                               nohEsq = construirNoh(val);
                           }
                           else{
                               nohEsq -> inserirNohFilho(val);
                           }  
                  } 
                  else{
                           if(nohDir == NULL){  
                               nohDir = construirNoh(val);
                           }
                           else{
                               nohDir -> inserirNohFilho(val);
                           }
                  } 
        }  
};

struct noh * raiz;

void criarArvore(int val){
     if(raiz == NULL){
          raiz = (struct noh *)malloc(sizeof(struct noh)); 
          raiz -> valor = val;
          raiz -> nohEsq = NULL;
          raiz -> nohDir = NULL;
     }
     raiz->inserirNohFilho(val);     
}

main(){              
       int i = 0;
       int v[12] = {10, 15, 5, 7, 9, 25, 30, 19, 1, 4, 8, 22};
       
       while(i < sizeof(v)/sizeof(int)){
            criarArvore(v[i]);
            i++;
       }
       
       printf("\n Pos-ordem: ");
       raiz->lerPosOrdem();
       printf("\n In-ordem: ");
       raiz->lerInOrdem();
       printf("\n Pre-ordem: ");
       raiz->lerPreOrdem();
       
       getch();
}

       
