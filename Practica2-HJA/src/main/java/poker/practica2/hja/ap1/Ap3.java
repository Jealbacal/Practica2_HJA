/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.ap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import poker.practica2.hja.GUI.BoardButton;
import poker.practica2.hja.GUI.PairButton;
import poker.practica2.hja.GUI.Type;
import java.util.Collections;

/**
 *
 * @author jesus
 */
public class Ap3 {
    
    ArrayList<PairButton> rangos;
    Ap1 logic;
    ArrayList<BoardButton> board;
    static ArrayList<output> result;
    
    
    public Ap3(String rango, String board){
        
    
    }
    
//    public void procesaBoard(String board){
//    
//         int lenght =board.length();
//        int i=0;
//        String aux="";
//        
//        while(i < lenght){
//          
//          if(board.charAt(i) != ','){
//              aux=aux+board.charAt(i);
//          }
//          
//          else if (board.charAt(i) == ','){
//              this.board.add(aux);
//              aux="";
//             
//          }
//            
//          i++;
//        }
//         this.board.add(aux);   
//        
//    }
   
    
    public static void calcular(ArrayList<PairButton> rangos, ArrayList<BoardButton> board){
        
        for (PairButton act : rangos){
            
            compara(act,board);
            //procesar salida
        }
        
        
    }
    
                                      
    public static void compara(PairButton rango,ArrayList<BoardButton> board){
        int countFirst = 0, countSecond = 0, straightCount = 1, hs = 0, cs = 0, ds = 0, ss= 0;
        int first, second, maxCount = 0, freq;
        result = new ArrayList<>();
        ArrayList<Integer> escalera = new ArrayList<Integer>();
        Type tipo = rango.getType();
        
        for(BoardButton s : board){
            escalera.add(s.getValor());
        }
        
        switch (tipo){
            case PAIR:
                first=rango.getFirstCard();
                second=rango.getSecondCard();
                for(int i = 0; i < board.size(); ++i){
                    
                //Contamos si se repiten cartas
                    if (first == board.get(i).getValor()) ++countFirst;
                    //Contamos cartas seguidas
//                    if (i > 0 && valores.get(i) == valores.get(i-1)+1) ++straightCount;
//                    //Si hay un gap en la escalera comprobamos si el rango lo suple
//                    if (i > 0 && valores.get(i) == valores.get(i-1)+2 && first == valores.get(i-1)+1) { ++straightCount;first = -1;}
//                    //Contamos cada palo
//                    if(palos.get(i)=='h')++hs; if(palos.get(i)=='c')++cs; if(palos.get(i)=='d')++ds; if(palos.get(i)=='s')++ss;
                    
                    freq = Collections.frequency(escalera, board.get(i).getValor());
                    if(freq > maxCount) maxCount = freq;
                    
                    
                }
            
                //Analizo jugada
//                if (straightCount >= 5 && (hs >=4 || cs >=4 || ds >=4 || ss >=4))result.add(new output(Ranking.STRAIGHTFLUSH,rango.getText(),1));
                if(comprobarEscalera(rango,escalera) && comprobarColor(rango,board))result.add(new output(Ranking.STRAIGHTFLUSH,rango.getText(),1));
                else if(countFirst == 2) result.add(new output(Ranking.FOUROFAKIND,rango.getText(),1));
                else if(maxCount == 2 && countFirst == 1)result.add(new output(Ranking.FULLHOUSE,rango.getText(),3));
                else if(maxCount == 3 && countFirst == 0)result.add(new output(Ranking.FULLHOUSE,rango.getText(),6));
                //combinatoria mal
                else if (comprobarColor(rango,board)) result.add(new output(Ranking.FLUSH,rango.getText(),1));
                //combinatoria mal
                else if (comprobarEscalera(rango,escalera)) result.add(new output(Ranking.STRAIGHT,rango.getText(),4));
                else if (countFirst == 1)result.add(new output(Ranking.THREEOFAKIND,rango.getText(),3));
                else if (countFirst == 0) comparaParejas(rango,valores.get(0),valores.get(1));
            
            break;
            
            case SUITED:
                
                first = cartaParse(rango.charAt(0));
                second = cartaParse(rango.charAt(1));

                 for(int i = 0; i < board.size(); ++i){
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
                
            break;
            
            case OFF_SUIT:    
                first = cartaParse(rango.charAt(0));
                second = cartaParse(rango.charAt(1));

                for(int i = 0; i < board.size(); ++i){
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
            
            break;    
        }
        
    }
    
     public static boolean comprobarEscalera(PairButton rango,ArrayList<Integer> escalera ){
        boolean result=true;
        int i=1,iguales=0;
         ArrayList<Integer> aux= escalera;
         aux.add(rango.getFirstCard());
         
         Collections.sort(aux);
         while(i<aux.size() && result){
            
            if(aux.size()==5){
               if(aux.get(i)-aux.get(i-1) !=1)
                   result=false;
            }
            else if (aux.size()==6){
                
                if(aux.get(i)==aux.get(i-1))
                   iguales++;
                
                if(aux.get(i)-aux.get(i-1) > 1)
                    result=false;
                
                else if (iguales > 1)
                    result=false;
            }
             
            i++;
         }
         
         ArrayList<Integer> aux1= escalera;
         

         ArrayList<Integer> aux2= escalera;

        return result;
     }
     
     public static boolean comprobarColor(PairButton rango,ArrayList<BoardButton> color ){
        boolean result=true;
        int i=1,h=0,s=0,c=0,d=0;
        ArrayList<BoardButton> aux= color;  
         
        while(i<aux.size() && result){

           if(color.get(i).getCardText().equals("h"))
               h++;
           else if(color.get(i).getCardText().equals("s"))
               s++;
           else if(color.get(i).getCardText().equals("c"))
               c++;
           else if(color.get(i).getCardText().equals("d"))
               d++; 
           
           if(color.get(i).getValor()== rango.getFirstCard() || color.get(i).getValor()== rango.getSecondCard())
              result=false;
           
           i++;
        }
        
        if(h<4 && s<4 &&  d<4 && c<4 )
            result=false;
        

        return result;
     }
     
     
     
    
     public static void comparaParejas(String range, int first,int second ){
        
        if(cartaParse(range.charAt(0)) > first)
            result.add(new output(Ranking.OVERPAIR,range,6));
        
        //la combinatoria esta mal exepto la primera distincion
        else if (cartaParse(range.charAt(0)) == first)
            result.add(new output(Ranking.TOPPAIR,range,6));
        
        //preguntar sobre el weak pair 
        else if (cartaParse(range.charAt(0)) < first && cartaParse(range.charAt(0)) > 8)
            result.add(new output(Ranking.PPBTP,range,6));
        
        else if (cartaParse(range.charAt(0)) == second && cartaParse(range.charAt(0)) > 8)
            result.add(new output(Ranking.MIDDLEPAIR,range,6));
        
        else
            result.add(new output(Ranking.WEAKPAIR,range,6));
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
