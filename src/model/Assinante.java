/*
 * Classe contendo os atributos e métodos de um Assinante
 */
package model;

/**
 *
 * @author allan
 */
public class Assinante extends Node{
    
    public int conectadoNaCentral;      // Códgio da Central a qual esta ligado    
    private int numero;

    public Assinante(int id, int conectadoNaCentral, int numero) {
        super(Boolean.FALSE);
        this.conectadoNaCentral = conectadoNaCentral;
        this.numero = numero;
    }
    
    private boolean discaNumero(int numeroAssinanteB){
        //levanta telefone do gancho e disca numero
        //notifica central
        //central retorna se é possivel realizar a chamada
        //iniciaChamadaTelefonica()
        //ou
        //não
        return Boolean.TRUE;
    }
    
    private void iniciaChamadaTelefonica(){
        
    }
    
    private void desligaTelefone(){
        //coloca no gancho
        //muda o status
    }

 
}
