/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.ap1;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Ap3 {
    
    ArrayList<String> rangos;
    Ap1 logic;
    ArrayList<String> board;

    
    public Ap3(String rango, String board){
        
        logic = new Ap1(rango);
        this.board= new ArrayList<String>();
        procesaBoard(board);
        rangos=logic.rangos;
        
        
    }
    
    public void procesaBoard(String board){
    
         int lenght =board.length();
        int i=0;
        String aux="";
        
        while(i < lenght){
          
          if(board.charAt(i) != ','){
              aux=aux+board.charAt(i);
          }
          
          else if (board.charAt(i) == ','){
              this.board.add(aux);
              aux="";
             
          }
            
          i++;
        }
         this.board.add(aux);   
        
    }
   
    
    public void calcular(){
        
        
        
    }
    
    
       public static void main(String args[]){
       
       //Parece que no procesa bien el ultimo elemento
       //Los intervalos no salen
       
       String test1 = "AKs-A2s, TT+,T2s"; // No funciona
       String test2 = "Ah,Js,Ad"; // Falta que saque el T2s+, el TT+ lo saca bien
       String test3 = "AA"; // No funciona
       String test4 = "AA,TT"; // Saca solo AA
       String test5 = "J3s+"; 
       
       Ap3 logic = new Ap3(test1,test2);
      
       
       for(String s : logic.rangos){
           System.out.println(s);
       }
       
       System.out.println("---------------------------------------------------------------");
         
       for(String s : logic.board){
           System.out.println(s);
       }
    }   
       
   }
