/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.ap1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jesus
 */
public class Ap3 {
    
    ArrayList<String> rangos;
    Ap1 logic;
    ArrayList<String> board;
    static ArrayList<output> result;
    
    
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
   
    
    public static void calcular(ArrayList<String> rangos, ArrayList<String> board){
        
        ArrayList<Character> palos = new ArrayList<>();
        for(String aux : board){ palos.add(aux.charAt(1)); }
        
        ArrayList<Integer> valores = new ArrayList<>();
        for(String aux : board){valores.add(cartaParse(aux.charAt(0))); }
        
        
        for (String act : rangos){
            
            compara(act, palos, valores);
            //procesar salida
        }
        
        
    }
    
                                      
    public static void compara(String rango, ArrayList<Character> palos, ArrayList<Integer> valores){
        int countFirst = 0, countSecond = 0, straightGap = -1, gapNeed = -1, straightCount = 1, hs = 0, cs = 0, ds = 0, ss= 0;
        result = new ArrayList<>();

        if(rango.charAt(0) == rango.charAt(1)){ //El rango es pareja JJ 22 AA
            
            //Analizo board
            for(int i = 0; i < palos.size(); ++i){
                //Contamos si se repiten cartas
                if (cartaParse(rango.charAt(0)) == valores.get(i)) ++countFirst;
                //Contamos cartas seguidas
                if (i > 0 && valores.get(i) == valores.get(i-1)+1) ++straightCount;
                //Si hay un gap en la escalera guardamos la posision del gap y el valor necesario
                if (i > 0 && valores.get(i) == valores.get(i-1)+2) {straightGap = i; gapNeed = valores.get(i)-1; ++straightCount;}
                //Contamos cada palo
                if(palos.get(i)=='h')++hs; if(palos.get(i)=='c')++cs; if(palos.get(i)=='d')++ds; if(palos.get(i)=='s')++ss;
            }
            
            //Analizo jugada
            
            if(countFirst == 2) result.add(new output(Ranking.FOUROFAKIND,rango,1));
            else if (countFirst == 1)result.add(new output(Ranking.THREEOFAKIND,rango,2));
            
            //else if (hs==3 ||cs==3 ||ds==3 ||ss==3)result.add(new output(Ranking.FLUSH,rango,2));
        }
        else if (rango.charAt(2) == 's'){ // El rango es suited Aks QJs
            
             for(int i = 0; i < palos.size(); ++i){
                //Contamos si se repiten cartas
                if (cartaParse(rango.charAt(0)) == valores.get(i)) ++countFirst;
                if (cartaParse(rango.charAt(1)) == valores.get(i)) ++countSecond;
                //Contamos cartas seguidas
                if (i > 0 && valores.get(i) == valores.get(i-1)+1) ++straightCount;
                //Si hay un gap en la escalera guardamos la posision del gap y el valor necesario
                if (i > 0 && valores.get(i) == valores.get(i-1)+2) {straightGap = i; gapNeed = valores.get(i)-1; ++straightCount;}
                //Contamos cada palo
                if(palos.get(i)=='h')++hs; if(palos.get(i)=='c')++cs; if(palos.get(i)=='d')++ds; if(palos.get(i)=='s')++ss;
            }
            
        }
        else{ // El rango es offsuited Ako QJo
            
            for(int i = 0; i < palos.size(); ++i){
                //Contamos si se repiten cartas
                if (cartaParse(rango.charAt(0)) == valores.get(i)) ++countFirst;
                if (cartaParse(rango.charAt(1)) == valores.get(i)) ++countSecond;
                //Contamos cartas seguidas
                if (i > 0 && valores.get(i) == valores.get(i-1)+1) ++straightCount;
                //Si hay un gap en la escalera guardamos la posision del gap y el valor necesario
                if (i > 0 && valores.get(i) == valores.get(i-1)+2) {straightGap = i; gapNeed = valores.get(i)-1; ++straightCount;}
                //Contamos cada palo
                if(palos.get(i)=='h')++hs; if(palos.get(i)=='c')++cs; if(palos.get(i)=='d')++ds; if(palos.get(i)=='s')++ss;
            }
            
        }
        
        
    }
    
    public static int cartaParse(char carta){
        
        int valor;
        
         switch(carta) {
            case'A':
                valor = 14;
                break;
            case'K':
                valor = 13;
                break;
            case'Q':
                valor = 12;
                break;
            case'J':
                valor = 11;
                break;
            case'T':
                valor = 10;
                break;
            default:
                valor = Character.getNumericValue(carta);
        }
         
         return valor;
    }
    
    
       public static void main(String args[]){
       
       //Parece que no procesa bien el ultimo elemento
       //Los intervalos no salen
       
//       String test1 = "AKs-A2s, TT+,T2s"; // No funciona
//       String test2 = "Ah,Js,Ad"; // Falta que saque el T2s+, el TT+ lo saca bien
//       String test3 = "AA"; // No funciona
//       String test4 = "AA,TT"; // Saca solo AA
//       String test5 = "J3s+"; 
//       
//       Ap3 logic = new Ap3(test1,test2);
//      
//       
//       for(String s : logic.rangos){
//           System.out.println(s);
//       }
//       
//       System.out.println("---------------------------------------------------------------");
//         
//       for(String s : logic.board){
//           System.out.println(s);
//       }

        
        ArrayList<String> testBoard = new ArrayList<>(Arrays.asList("Ah", "Qh", "Jc"));
        ArrayList<String> testRange = new ArrayList<>(Arrays.asList("AA","KK","22","AKs","Q9s","65s"));
        
        calcular(testRange,testBoard);
        
    }   
       
   }
