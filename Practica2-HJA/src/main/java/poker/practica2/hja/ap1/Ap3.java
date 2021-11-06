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
    public static ArrayList<output> result = new ArrayList<>();
    public static ArrayList<output> result2 = new ArrayList<>();

    ;
    
    
    public Ap3(ArrayList<PairButton> rangos, ArrayList<BoardButton> board) {

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
    public static void calcular(ArrayList<PairButton> rangos, ArrayList<BoardButton> board) {

        for (PairButton act : rangos) {

            compara(act, board);
            //procesar salida
            System.out.println(result.get(result.size()-1));
            for(output o : result){
                
            }
        }

    }

    public static void compara(PairButton rango, ArrayList<BoardButton> board) {
        int countFirst = 0, countSecond = 0, straightCount = 1, hs = 0, cs = 0, ds = 0, ss = 0;
        int first, second, maxCount = 0, freq;
        char palo;
        boolean escaleras, iguales;
        ArrayList<Integer> escalera = new ArrayList<Integer>();
        Type tipo = rango.getType();

        for (BoardButton s : board) {
            escalera.add(s.getValor());
        }

        first = rango.getFirstCard();
        second = rango.getSecondCard();

        char firstString = rango.getText().charAt(0);
        char secondString = rango.getText().charAt(1);

        switch (tipo) {

            case PAIR:

                escaleras = comprobarEscalera(rango, escalera);
                palo = comprobarColor(rango, board, false);
                for (int i = 0; i < board.size(); ++i) {

                    //Contamos si se repiten cartas
                    if (first == board.get(i).getValor()) {
                        ++countFirst;
                    }
                    //Contamos cartas seguidas
//                    if (i > 0 && valores.get(i) == valores.get(i-1)+1) ++straightCount;
//                    //Si hay un gap en la escalera comprobamos si el rango lo suple
//                    if (i > 0 && valores.get(i) == valores.get(i-1)+2 && first == valores.get(i-1)+1) { ++straightCount;first = -1;}
//                    //Contamos cada palo
//                    if(palos.get(i)=='h')++hs; if(palos.get(i)=='c')++cs; if(palos.get(i)=='d')++ds; if(palos.get(i)=='s')++ss;

                    freq = Collections.frequency(escalera, board.get(i).getValor());
                    if (freq > maxCount) {
                        maxCount = freq;
                    }

                }

                //Analizo jugada
//                if (straightCount >= 5 && (hs >=4 || cs >=4 || ds >=4 || ss >=4))result.add(new output(Ranking.STRAIGHTFLUSH,rango.getText(),1));
                if (escaleras) {
                    switch (palo) {

                        case 'h': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + secondString + "h", 1));
                            result.add(new output(Ranking.STRAIGHT, firstString + secondString + "c, " + firstString + secondString + "d, " + firstString + secondString + "s", 3));
                        }

                        break;

                        case 'c': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + secondString + "c", 1));
                            result.add(new output(Ranking.STRAIGHT, firstString + secondString + "h, " + firstString + secondString + "d, " + firstString + secondString + "s", 3));
                        }
                        break;
                        case 'd': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + secondString + "d", 1));
                            result.add(new output(Ranking.STRAIGHT, firstString + secondString + "c, " + firstString + secondString + "h, " + firstString + secondString + "s", 3));
                        }
                        break;

                        case 's': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + secondString + "s", 1));
                            result.add(new output(Ranking.STRAIGHT, firstString + secondString + "c, " + firstString + secondString + "d, " + firstString + secondString + "h", 3));
                        }
                        break;

                        case 'x': {
                            result.add(new output(Ranking.STRAIGHT, rango.getText(), 1));
                        }

                    }

                } else if (countFirst == 2) {
                    result.add(new output(Ranking.FOUROFAKIND, rango.getText(), 1));
                } else if (maxCount == 2 && countFirst == 1) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 3));
                } else if (maxCount == 3 && countFirst == 0) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 6));
                } else if (palo != 'x') {
                    result.add(new output(Ranking.FLUSH, rango.getText(), 3));
                } else if (countFirst == 1) {
                    result.add(new output(Ranking.THREEOFAKIND, rango.getText(), 3));
                } else if (maxCount == 2) {
                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 6));
                } //parametro boolean 
                else {
                    comparaParejas(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1));
                }

                break;

            case SUITED:

                escaleras = comprobarEscaleraSO(rango, escalera);
                palo = comprobarColor(rango, board, true);
                boolean h1 = false,
                 h2 = false,
                 s1 = false,
                 s2 = false,
                 c1 = false,
                 c2 = false,
                 d1 = false,
                 d2 = false;
                String carta = "";

                for (int i = 0; i < board.size(); ++i) {
                    //Contamos si se repiten cartas
                    if (first == board.get(i).getValor()) {

                        ++countFirst;
                        if (board.get(i).getCardText().charAt(1)=='h') {
                            h1 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='c') {
                            c1 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='s') {
                            s1 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='d') {
                            d1 = true;
                        }
                    }

                    if (second == board.get(i).getValor()) {
                        ++countSecond;
                        if (board.get(i).getCardText().charAt(1)=='h') {
                            h2 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='c') {
                            c2 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='s') {
                            s2 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='d') {
                            d2 = true;
                        }
                    }
                    //Contamos cartas seguidas

                    freq = Collections.frequency(escalera, board.get(i).getValor());
                    if (freq > maxCount && !(board.get(i).getValor()==first || board.get(i).getValor()==second )) {
                        maxCount = freq;
                    }
                }

                //Analizo jugada
                if (escaleras) {
                    switch (palo) {

                        case 'h': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "h" + secondString + "h", 1));
                            result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                        }

                        break;

                        case 'c': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "c" + secondString + "c", 1));
                            result.add(new output(Ranking.STRAIGHT, firstString + "h" + secondString + "h, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                        }
                        break;
                        case 'd': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "d" + secondString + "d", 1));
                            result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "c, " + firstString + "h" + secondString + "h, " + firstString + "s" + secondString + "s", 3));
                        }
                        break;

                        case 's': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "s" + secondString + "s", 1));
                            result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "h" + secondString + "h", 3));
                        }
                        break;

                        case 'x': {
                            result.add(new output(Ranking.STRAIGHT, rango.getText(), 4));
                        }

                    }

                } else if (countFirst == 3 || countSecond == 3) {
                    result.add(new output(Ranking.FOUROFAKIND, rango.getText(), 1));
                } else if (countFirst == 2 && countSecond >= 1) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 4));
                } else if (countFirst >= 1 && countSecond == 2) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 4));
                } else if (maxCount >= 2 && countFirst == 2 && countSecond == 0) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 2));
                } else if (maxCount >= 2 && countFirst == 0 && countSecond == 2) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 2));
                } else if (maxCount >= 3 && countFirst == 1 && countSecond == 0) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 3));
                } else if (maxCount >= 3 && countFirst == 0 && countSecond == 1) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 3));
                } else if(palo !='x'){
                   switch(palo){
                       case 'h':
                           result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "h" , 1));
                           result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s" , 3));
                        break;
                        
                        case 'c': {
                            result.add(new output(Ranking.FLUSH, firstString + "c" + secondString + "c", 1));
                            result.add(new output(Ranking.NOMADEHAND, firstString + "h" + secondString + "h, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                        }
                        break;
                        case 'd': {
                            result.add(new output(Ranking.FLUSH, firstString + "d" + secondString + "d", 1));
                            result.add(new output(Ranking.NOMADEHAND,firstString + "c" + secondString + "c, " + firstString + "h" + secondString + "h, " + firstString + "s" + secondString + "s", 3));
                        }
                        break;

                        case 's': {
                            result.add(new output(Ranking.FLUSH, firstString + "s" + secondString + "s", 1));
                            result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "h" + secondString + "h", 3));
                        }
                        break;

                        
                   }
                   
                } 
                 else if (countFirst == 2 || countSecond == 2) {
                    result.add(new output(Ranking.THREEOFAKIND, rango.getText(), 2));
                } else if (countFirst == 1 && countSecond == 1) {

                    if (!(h1 && h2)) {
                        carta += firstString + "h" + secondString + "h ";
                        
                    }if (!(c1 && c2)) {
                        carta += firstString + "c" + secondString + "c ";
                    }if (!(s1 && s2)) {
                        carta += firstString + "s" + secondString + "s ";
                    }if (!(d1 && d2)) {
                        carta += firstString + "d" + secondString + "d ";
                    }
                    result.add(new output(Ranking.TWOPAIR, carta, 3));
//                } else if (countFirst == 1 && countSecond == 0 && maxCount == 2) {
//                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 3));
//                } else if (countFirst == 0 && countSecond == 1 && maxCount == 2) {
//                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 3));
                } else if (countFirst == 1 ) {
                    comparaParejasSO(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1),true,true);
                } else if (countSecond == 1 ) {
                    comparaParejasSO(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1),true,false);
                }
                else {
                    result.add(new output(Ranking.NOMADEHAND, rango.getText(), 4));
                }

                break;

            case OFF_SUIT:
                escaleras = comprobarEscaleraSO(rango, escalera);
                palo = comprobarColor(rango, board, false);
                h1 = false;
                h2 = false;
                s1 = false;
                s2 = false;
                c1 = false;
                c2 = false;
                d1 = false;
                d2 = false;
                carta = "";

                for (int i = 0; i < board.size(); ++i) {
                    if (first == board.get(i).getValor()) {

                        ++countFirst;
                        if (board.get(i).getCardText().charAt(1)=='h') {
                            h1 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='c') {
                            c1 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='s') {
                            s1 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='d') {
                            d1 = true;
                        }
                    }

                    if (second == board.get(i).getValor()) {
                        ++countSecond;
                        if (board.get(i).getCardText().charAt(1)=='h') {
                            h2 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='c') {
                            c2 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='s') {
                            s2 = true;
                        } else if (board.get(i).getCardText().charAt(1)=='d') {
                            d2 = true;
                        }
                    }
                    
                    freq = Collections.frequency(escalera, board.get(i).getValor());
                    if (freq > maxCount && !(board.get(i).getValor()==first || board.get(i).getValor()==second )) {
                        maxCount = freq;
                    }
                }
                //Analizo jugada
                if (escaleras) {
                    switch (palo) {

                        case 'h': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s", 3));
                            result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                    +firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h",9));
                        }
                        break;
                        case 'c': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "c" + secondString + "c, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s", 3));
                            result.add(new output(Ranking.STRAIGHT, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                    +firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h",9));
                        }
                        break;
                        case 'd': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "s", 3));
                            result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                    +firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h",9));
                        }
                        break;

                        case 's': {
                           result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h", 3));
                            result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                    +firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "s"
                                    +firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s",9));
                        }
                        break;

                        case 'x': {
                            result.add(new output(Ranking.STRAIGHT, rango.getText(), 4));
                        }

                    }

                } else if (countFirst == 3) {
                    result.add(new output(Ranking.FOUROFAKIND, rango.getText(), 3-countSecond));
                } else if (countSecond == 3) {
                    result.add(new output(Ranking.FOUROFAKIND, rango.getText(), 3-countFirst));
                
                } else if (countFirst == 2 && countSecond >= 1) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 4));
                } else if (countFirst >= 1 && countSecond == 2) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 4));
                } else if (maxCount >= 2 && countFirst == 2 && countSecond == 0) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 6));
                } else if (maxCount >= 2 && countFirst == 0 && countSecond == 2) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 6));
                } else if (maxCount >= 3 && countFirst == 1 && countSecond == 0) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 9));
                } else if (maxCount >= 3 && countFirst == 0 && countSecond == 1) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 9));
                } else if(palo !='x' && board.size() > 3){
                    if(countFirst > 0 ||countSecond > 0)
                   switch(palo){
                       case 'h':
                           result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s" , 3));
                           result.add(new output(Ranking.NOMADEHAND,firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                    +firstString + "d" + secondString +"c"+ firstString + "d" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                        break;
                        
                        case 'c': {
                            result.add(new output(Ranking.FLUSH, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s" , 3));
                            result.add(new output(Ranking.NOMADEHAND,firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                    +firstString + "d" + secondString +"h"+ firstString + "d" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                        }
                        break;
                        case 'd': {
                            result.add(new output(Ranking.FLUSH, firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "s" , 3));
                            result.add(new output(Ranking.NOMADEHAND,firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                    +firstString + "h" + secondString +"d"+ firstString + "h" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                        }
                        break;

                        case 's': {
                             result.add(new output(Ranking.FLUSH, firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h" , 3));
                           result.add(new output(Ranking.NOMADEHAND,firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                    +firstString + "d" + secondString +"c"+ firstString + "d" + secondString + "s"
                                    +firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, ", 6));
                        }
                        break;
                   }
                    else
                       switch(palo){
                       
                      case 'h':
                           result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s" , 6));
                           result.add(new output(Ranking.NOMADEHAND,firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                    +firstString + "d" + secondString +"c"+ firstString + "d" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                        break;
                        
                        case 'c': {
                            result.add(new output(Ranking.FLUSH, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s" , 6));
                            result.add(new output(Ranking.NOMADEHAND,firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                    +firstString + "d" + secondString +"h"+ firstString + "d" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                        }
                        break;
                        case 'd': {
                            result.add(new output(Ranking.FLUSH, firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "s" , 6));
                            result.add(new output(Ranking.NOMADEHAND,firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                    +firstString + "h" + secondString +"d"+ firstString + "h" + secondString + "s"
                                    +firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                        }
                        break;

                        case 's': {
                             result.add(new output(Ranking.FLUSH, firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h" , 6));
                           result.add(new output(Ranking.NOMADEHAND,firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                    +firstString + "d" + secondString +"c"+ firstString + "d" + secondString + "s"
                                    +firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, ", 6));
                        }
                        break;
                        
                   }
                   
                } 
                 else if (countFirst == 2 || countSecond == 2) {
                    result.add(new output(Ranking.THREEOFAKIND, rango.getText(), 6));
                } else if (countFirst == 1 && countSecond == 1) {

                    if (!(h1 && h2)) {
                        carta += firstString + "h" + secondString + "h ";
                        
                    }if (!(c1 && c2)) {
                        carta += firstString + "c" + secondString + "c ";
                    }if (!(s1 && s2)) {
                        carta += firstString + "s" + secondString + "s ";
                    }if (!(d1 && d2)) {
                        carta += firstString + "d" + secondString + "d ";
                    }
                    result.add(new output(Ranking.TWOPAIR, carta, 6));
//                } else if (countFirst == 1 && countSecond == 0 && maxCount == 2) {
//                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 3));
//                } else if (countFirst == 0 && countSecond == 1 && maxCount == 2) {
//                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 3));
                } else if (countFirst == 1 ) {
                    comparaParejasSO(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1),true,true);
                } else if (countSecond == 1 ) {
                    comparaParejasSO(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1),true,false);
                }
                else {
                    result.add(new output(Ranking.NOMADEHAND, rango.getText(), 4));
                }

                break;

        }
        

    }

    public static boolean comprobarEscalera(PairButton rango, ArrayList<Integer> escalera) {
        boolean result = true;
        int i = 1, iguales = 0;
        ArrayList<Integer> aux = new ArrayList<Integer>();
        aux = (ArrayList<Integer>) escalera.clone();
        //if(rango.getFirstCard() == 14) aux.add(1);
        aux.add(rango.getFirstCard());

        Collections.sort(aux);
        
        
        while (i < aux.size() && result) {

            if (aux.size() == 5) {
                if (aux.get(i) - aux.get(i - 1) != 1) {
                    result = false;
                }
            } else if (aux.size() == 6) {

                if (aux.get(i) == aux.get(i - 1)) {
                    iguales++;
                }

                if (aux.get(i) - aux.get(i - 1) > 1) {
                    result = false;
                } else if (iguales > 1) {
                    result = false;
                }
            } else {
                result = false;
            }

            i++;
        }

        return result;
    }

    //comprobar esto
    public static boolean comprobarEscaleraSO(PairButton rango, ArrayList<Integer> escalera) {

        int i = 1, cont = 1;
        ArrayList<Integer> aux = new ArrayList<Integer>();
        aux = (ArrayList<Integer>) escalera.clone();

        if(rango.getFirstCard() == 14) aux.add(1);
        aux.add(rango.getFirstCard());
        aux.add(rango.getSecondCard());
        Collections.sort(aux);

        while (i < aux.size()) {

            if (aux.get(i) - aux.get(i - 1) == 1) {
                cont++;
            }
            else if (aux.get(i) - aux.get(i - 1) == 0)
                ;
            else if(cont < 5){
                cont = 1;
            }
            i++;
        }

        if (cont >= 5) {
            return true;
        } else {
            return false;
        }

    }

    public static char comprobarColor(PairButton rango, ArrayList<BoardButton> color, boolean suited) {
        boolean result = true;
        char aux1 = 'x';
        int i = 0, h = 0, s = 0, c = 0, d = 0;
        ArrayList<BoardButton> aux = color;

        while (i < aux.size()) {

            if (color.get(i).getCardText().charAt(1) == ('h')) {
                h++;
            } else if (color.get(i).getCardText().charAt(1) == ('s')) {
                s++;
            } else if (color.get(i).getCardText().charAt(1) == ('c')) {
                c++;
            } else if (color.get(i).getCardText().charAt(1) == ('d')) {
                d++;
            }

            if (color.get(i).getValor() == rango.getFirstCard() || color.get(i).getValor() == rango.getSecondCard()) {
                return aux1;
            }

            i++;
        }

        if (!suited) {
            if (h < 4 && s < 4 && d < 4 && c < 4) {
                return aux1;
            } 
        }
        else if (h < 3 && s < 3 && d < 3 && c < 3) {
                return aux1;
        }

        if (h > s || h > d || h > c) {
            aux1 = 'h';
        } else if (s > h || s > d || s > c) {
            aux1 = 's';
        } else if (d > h || d > s || d > c) {
            aux1 = 'd';
        } else {
            aux1 = 'c';
        }

        return aux1;
    }

    public static void comparaParejas(PairButton range, int first, int second, int last) {
        int firstCard = range.getFirstCard();
        int secondCard = range.getSecondCard();

        if (firstCard > first) {
            result.add(new output(Ranking.OVERPAIR, range.getText(), 6));
        } //la combinatoria esta mal exepto la primera distincion
        else if (firstCard == first) {
            result.add(new output(Ranking.TOPPAIR, range.getText(), 6));
        } //preguntar sobre el weak pair 
        else if (firstCard < first && firstCard > last) {
            result.add(new output(Ranking.PPBTP, range.getText(), 6));
        } else if (firstCard == second && firstCard > last) {
            result.add(new output(Ranking.MIDDLEPAIR, range.getText(), 6));
        } else {
            result.add(new output(Ranking.WEAKPAIR, range.getText(), 6));
        }

    }

     public static void comparaParejasSO(PairButton range, int first, int second, int last,boolean suited,boolean carta) {
        int firstCard;
        
        
        if(carta)
             firstCard = range.getFirstCard();
        else
            firstCard = range.getSecondCard();
        
        if(suited){
            if (firstCard > first) {
                result.add(new output(Ranking.OVERPAIR, range.getText(), 3));
            } //la combinatoria esta mal exepto la primera distincion
            else if (firstCard == first) {
                result.add(new output(Ranking.TOPPAIR, range.getText(), 3));
            } //preguntar sobre el weak pair 
             else if (firstCard == second && firstCard > last) {
                result.add(new output(Ranking.MIDDLEPAIR, range.getText(), 3));
            } else {
                result.add(new output(Ranking.WEAKPAIR, range.getText(), 3));
            } 
        }
        
        else{
            if (firstCard > first) {
                result.add(new output(Ranking.OVERPAIR, range.getText(), 9));
            } //la combinatoria esta mal exepto la primera distincion
            else if (firstCard == first) {
                result.add(new output(Ranking.TOPPAIR, range.getText(), 9));
            } //preguntar sobre el weak pair 
             else if (firstCard == second && firstCard > last) {
                result.add(new output(Ranking.MIDDLEPAIR, range.getText(), 9));
            } else {
                result.add(new output(Ranking.WEAKPAIR, range.getText(), 9));
            } 
        }

    }
    
    
    
    
    public static int cartaParse(char carta) {

        int valor;

        switch (carta) {
            case 'A':
                valor = 14;
                break;
            case 'K':
                valor = 13;
                break;
            case 'Q':
                valor = 12;
                break;
            case 'J':
                valor = 11;
                break;
            case 'T':
                valor = 10;
                break;
            default:
                valor = Character.getNumericValue(carta);
        }

        return valor;
    }

    public static void main(String args[]) {

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
        ArrayList<String> testRange = new ArrayList<>(Arrays.asList("AA", "KK", "22", "AKs", "Q9s", "65s"));

        

    }

}
