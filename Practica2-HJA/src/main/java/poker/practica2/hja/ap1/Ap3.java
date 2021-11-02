/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.ap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
        int countFirst = 0, countSecond = 0, straightCount = 1, hs = 0, cs = 0, ds = 0, ss= 0;
        int first, second, maxCount = 0, freq;
        result = new ArrayList<>();

        if(rango.charAt(0) == rango.charAt(1)){ //El rango es pareja JJ 22 AA
            first = cartaParse(rango.charAt(0));
            //Analizo board
            for(int i = 0; i < palos.size(); ++i){
                //Contamos si se repiten cartas
                if (first == valores.get(i)) ++countFirst;
                //Contamos cartas seguidas
                if (i > 0 && valores.get(i) == valores.get(i-1)+1) ++straightCount;
                //Si hay un gap en la escalera comprobamos si el rango lo suple
                if (i > 0 && valores.get(i) == valores.get(i-1)+2 && first == valores.get(i-1)+1) { ++straightCount;first = -1;}
                //Contamos cada palo
                if(palos.get(i)=='h')++hs; if(palos.get(i)=='c')++cs; if(palos.get(i)=='d')++ds; if(palos.get(i)=='s')++ss;
                freq = Collections.frequency(valores, valores.get(i));
                if(freq > maxCount) maxCount = freq;
            }
            
            //Analizo jugada
            if (straightCount >= 5 && (hs >=4 || cs >=4 || ds >=4 || ss >=4))result.add(new output(Ranking.STRAIGHTFLUSH,rango,1));
            else if(countFirst == 2) result.add(new output(Ranking.FOUROFAKIND,rango,1));
            else if(maxCount == 2 && countFirst == 1)result.add(new output(Ranking.FULLHOUSE,rango,3));
            else if(maxCount == 3 && countFirst == 0)result.add(new output(Ranking.FULLHOUSE,rango,6));
            else if (hs >=4 || cs >=4 || ds >=4 || ss >=4) result.add(new output(Ranking.FLUSH,rango,3));
            else if (straightCount >= 5) result.add(new output(Ranking.STRAIGHT,rango,4));
            else if (countFirst == 1)result.add(new output(Ranking.THREEOFAKIND,rango,3));
            else result.add(new output(Ranking.PAIR,rango,6));
        }
        else if (rango.charAt(2) == 's'){ // El rango es suited Aks QJs
            first = cartaParse(rango.charAt(0));
            second = cartaParse(rango.charAt(1));
            
             for(int i = 0; i < palos.size(); ++i){
                //Contamos si se repiten cartas
                if (cartaParse(rango.charAt(0)) == valores.get(i)) ++countFirst;
                if (cartaParse(rango.charAt(1)) == valores.get(i)) ++countSecond;
                //Contamos cartas seguidas
                if (i > 0 && valores.get(i) == valores.get(i-1)+1) ++straightCount;
                //Si hay un gap en la escalera comprobamos si el rango lo suple
                if (i > 0 && valores.get(i) == valores.get(i-1)+2){
                    if (first == valores.get(i-1)+1){
                        ++straightCount;first = -1;
                    }
                    if (second == valores.get(i-1)+1){
                        ++straightCount;second = -1;
                    }
                }      
                //Contamos cada palo
                if(palos.get(i)=='h')++hs; if(palos.get(i)=='c')++cs; if(palos.get(i)=='d')++ds; if(palos.get(i)=='s')++ss;
                freq = Collections.frequency(valores, valores.get(i));
                if(freq > maxCount) maxCount = freq;
            }
             //Analizo jugada
            if (straightCount >= 5){
                if(hs >=3) {result.add(new output(Ranking.STRAIGHTFLUSH,rango.charAt(0)+"h"+rango.charAt(1)+"h",1));
                            result.add(new output(Ranking.STRAIGHT,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
                if(cs >=3) {result.add(new output(Ranking.STRAIGHTFLUSH,rango.charAt(0)+"c"+rango.charAt(1)+"c",1));
                            result.add(new output(Ranking.STRAIGHT,rango.charAt(0)+"h"+rango.charAt(1)+"h, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
                if(ds >=3) {result.add(new output(Ranking.STRAIGHTFLUSH,rango.charAt(0)+"d"+rango.charAt(1)+"d",1));
                            result.add(new output(Ranking.STRAIGHT,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"h"+rango.charAt(1)+"h, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
                if(ss >=3) {result.add(new output(Ranking.STRAIGHTFLUSH,rango.charAt(0)+"s"+rango.charAt(1)+"s",1));
                            result.add(new output(Ranking.STRAIGHT,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"h"+rango.charAt(1)+"h",3));}
            }
            else if(countFirst == 3 || countSecond == 3) result.add(new output(Ranking.FOUROFAKIND,rango,1));
            else if(maxCount == 1 && countFirst == 2 && countSecond == 1)result.add(new output(Ranking.FULLHOUSE,rango,6));
            else if(maxCount == 1 && countFirst == 1 && countSecond == 2)result.add(new output(Ranking.FULLHOUSE,rango,6));
            else if(maxCount == 2 && countFirst == 2 && countSecond == 0)result.add(new output(Ranking.FULLHOUSE,rango,8));
            else if(maxCount == 2 && countFirst == 0 && countSecond == 2)result.add(new output(Ranking.FULLHOUSE,rango,8));
            else if(maxCount == 3 && countFirst == 1 && countSecond == 0)result.add(new output(Ranking.FULLHOUSE,rango,12));
            else if(maxCount == 3 && countFirst == 0 && countSecond == 1)result.add(new output(Ranking.FULLHOUSE,rango,12));
            else if(hs >=3) {result.add(new output(Ranking.FLUSH,rango.charAt(0)+"h"+rango.charAt(1)+"h",1));
                            result.add(new output(Ranking.HIGHCARD,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
            else if(cs >=3) {result.add(new output(Ranking.FLUSH,rango.charAt(0)+"c"+rango.charAt(1)+"c",1));
                            result.add(new output(Ranking.HIGHCARD,rango.charAt(0)+"h"+rango.charAt(1)+"h, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
            else if(ds >=3) {result.add(new output(Ranking.FLUSH,rango.charAt(0)+"d"+rango.charAt(1)+"d",1));
                            result.add(new output(Ranking.HIGHCARD,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"h"+rango.charAt(1)+"h, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
            else if(ss >=3) {result.add(new output(Ranking.FLUSH,rango.charAt(0)+"s"+rango.charAt(1)+"s",1));
                            result.add(new output(Ranking.HIGHCARD,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"h"+rango.charAt(1)+"h",3));}
            else if (straightCount >= 5) result.add(new output(Ranking.STRAIGHT,rango,4));
            else if (countFirst == 2 || countSecond == 2)result.add(new output(Ranking.THREEOFAKIND,rango,4));
            else if (countFirst == 1 || countSecond == 1)result.add(new output(Ranking.PAIR,rango,4));
            else result.add(new output(Ranking.HIGHCARD,rango,4));
            
        }
        else{ // El rango es offsuited Ako QJo
            first = cartaParse(rango.charAt(0));
            second = cartaParse(rango.charAt(1));
            
            for(int i = 0; i < palos.size(); ++i){
                ///Contamos si se repiten cartas
                if (cartaParse(rango.charAt(0)) == valores.get(i)) ++countFirst;
                if (cartaParse(rango.charAt(1)) == valores.get(i)) ++countSecond;
                //Contamos cartas seguidas
                if (i > 0 && valores.get(i) == valores.get(i-1)+1) ++straightCount;
                //Si hay un gap en la escalera comprobamos si el rango lo suple
                if (i > 0 && valores.get(i) == valores.get(i-1)+2){
                    if (first == valores.get(i-1)+1){
                        ++straightCount;first = -1;
                    }
                    if (second == valores.get(i-1)+1){
                        ++straightCount;second = -1;
                    }
                }      
                //Contamos cada palo
                if(palos.get(i)=='h')++hs; if(palos.get(i)=='c')++cs; if(palos.get(i)=='d')++ds; if(palos.get(i)=='s')++ss;
                freq = Collections.frequency(valores, valores.get(i));
                if(freq > maxCount) maxCount = freq;
            }
            /* Por terminar
            if (straightCount >= 5){
                if(hs >=3) {result.add(new output(Ranking.STRAIGHTFLUSH,rango.charAt(0)+"h"+rango.charAt(1)+"h",1));
                            result.add(new output(Ranking.STRAIGHT,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
                if(cs >=3) {result.add(new output(Ranking.STRAIGHTFLUSH,rango.charAt(0)+"c"+rango.charAt(1)+"c",1));
                            result.add(new output(Ranking.STRAIGHT,rango.charAt(0)+"h"+rango.charAt(1)+"h, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
                if(ds >=3) {result.add(new output(Ranking.STRAIGHTFLUSH,rango.charAt(0)+"d"+rango.charAt(1)+"d",1));
                            result.add(new output(Ranking.STRAIGHT,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"h"+rango.charAt(1)+"h, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
                if(ss >=3) {result.add(new output(Ranking.STRAIGHTFLUSH,rango.charAt(0)+"s"+rango.charAt(1)+"s",1));
                            result.add(new output(Ranking.STRAIGHT,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"h"+rango.charAt(1)+"h",3));}
            }
            else if(countFirst == 3 || countSecond == 3) result.add(new output(Ranking.FOUROFAKIND,rango,1));
            else if(maxCount == 1 && countFirst == 2 && countSecond == 1)result.add(new output(Ranking.FULLHOUSE,rango,6));
            else if(maxCount == 1 && countFirst == 1 && countSecond == 2)result.add(new output(Ranking.FULLHOUSE,rango,6));
            else if(maxCount == 2 && countFirst == 2 && countSecond == 0)result.add(new output(Ranking.FULLHOUSE,rango,8));
            else if(maxCount == 2 && countFirst == 0 && countSecond == 2)result.add(new output(Ranking.FULLHOUSE,rango,8));
            else if(maxCount == 3 && countFirst == 1 && countSecond == 0)result.add(new output(Ranking.FULLHOUSE,rango,12));
            else if(maxCount == 3 && countFirst == 0 && countSecond == 1)result.add(new output(Ranking.FULLHOUSE,rango,12));
            else if(hs >=3) {result.add(new output(Ranking.FLUSH,rango.charAt(0)+"h"+rango.charAt(1)+"h",1));
                            result.add(new output(Ranking.HIGHCARD,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
            else if(cs >=3) {result.add(new output(Ranking.FLUSH,rango.charAt(0)+"c"+rango.charAt(1)+"c",1));
                            result.add(new output(Ranking.HIGHCARD,rango.charAt(0)+"h"+rango.charAt(1)+"h, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
            else if(ds >=3) {result.add(new output(Ranking.FLUSH,rango.charAt(0)+"d"+rango.charAt(1)+"d",1));
                            result.add(new output(Ranking.HIGHCARD,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"h"+rango.charAt(1)+"h, "+rango.charAt(0)+"s"+rango.charAt(1)+"s",3));}
            else if(ss >=3) {result.add(new output(Ranking.FLUSH,rango.charAt(0)+"s"+rango.charAt(1)+"s",1));
                            result.add(new output(Ranking.HIGHCARD,rango.charAt(0)+"c"+rango.charAt(1)+"c, "+rango.charAt(0)+"d"+rango.charAt(1)+"d, "+rango.charAt(0)+"h"+rango.charAt(1)+"h",3));}
            else if (straightCount >= 5) result.add(new output(Ranking.STRAIGHT,rango,4));
            else if (countFirst == 2 || countSecond == 2)result.add(new output(Ranking.THREEOFAKIND,rango,4));
            else if (countFirst == 1 || countSecond == 1)result.add(new output(Ranking.PAIR,rango,4));
            else result.add(new output(Ranking.HIGHCARD,rango,4));
            */
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
