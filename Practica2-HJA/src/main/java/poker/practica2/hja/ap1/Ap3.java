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
    public static int total=0;
    public static int ppbtp=0;
    public static boolean straightFlush =true;
    ArrayList<BoardButton> board;
    public static ArrayList<output> result = new ArrayList<>();

    public Ap3(ArrayList<PairButton> rangos, ArrayList<BoardButton> board) {

    }

    public static int calcular(ArrayList<PairButton> rangos, ArrayList<BoardButton> board) {
        result = new ArrayList<>();
        
        for (PairButton act : rangos) {

            compara(act, board);
            //procesar salida
            //System.out.println(result.get(result.size() - 1));

        }
        result = cargaLista(result);
        return total;
    }

    public static void compara(PairButton rango, ArrayList<BoardButton> board) {
        int countFirst = 0, countSecond = 0, straightCount = 1, hs = 0, cs = 0, ds = 0, ss = 0;
        int first, second, maxCount = 0, freq;
        char palo;
        boolean escaleras;
        straightFlush = true;
        
        ArrayList<Integer> escalera = new ArrayList<Integer>();
        ArrayList<Character> colorPalo = new ArrayList<Character>();
        Type tipo = rango.getType();

        for (BoardButton s : board) {
            escalera.add(s.getValor());
            colorPalo.add(s.getCardText().charAt(1));
        }
        
        Collections.sort(escalera,Collections.reverseOrder());
        first = rango.getFirstCard();
        second = rango.getSecondCard();

        String firstString = String.valueOf ( rango.getText().charAt(0));
        String secondString =String.valueOf ( rango.getText().charAt(1));

        switch (tipo) {

            case PAIR:

                
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
                escaleras = comprobarEscalera(rango, escalera,countFirst);
                palo = comprobarColor(rango, board, 0,countFirst,countSecond);
                //Analizo jugada
//                if (straightCount >= 5 && (hs >=4 || cs >=4 || ds >=4 || ss >=4))result.add(new output(Ranking.STRAIGHTFLUSH,rango.getText(),1));
                if (escaleras) {
                    switch (palo) {

                        case 'h': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + secondString + "h", 3));
                            result.add(new output(Ranking.STRAIGHT, firstString + secondString + "c, " + firstString + secondString + "d, " + firstString + secondString + "s", 3));
                        }

                        break;

                        case 'c': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + secondString + "c", 3));
                            result.add(new output(Ranking.STRAIGHT, firstString + secondString + "h, " + firstString + secondString + "d, " + firstString + secondString + "s", 3));
                        }
                        break;
                        case 'd': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + secondString + "d", 3));
                            result.add(new output(Ranking.STRAIGHT, firstString + secondString + "c, " + firstString + secondString + "h, " + firstString + secondString + "s", 3));
                        }
                        break;

                        case 's': {
                            result.add(new output(Ranking.STRAIGHTFLUSH, firstString + secondString + "s", 3));
                            result.add(new output(Ranking.STRAIGHT, firstString + secondString + "c, " + firstString + secondString + "d, " + firstString + secondString + "h", 3));
                        }
                        break;

                        case 'x': {
                            if(countFirst==1)
                                result.add(new output(Ranking.STRAIGHT, rango.getText(), 3));
                            else
                                result.add(new output(Ranking.STRAIGHT, rango.getText(), 6));
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
                } 
                //parametro boolean 
                else {
                    comparaParejas(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1));
                }
                
                if(palo=='x')
                    drawColor(rango,board,0,countFirst,countSecond);
                
                if(!escaleras)
                    draw(rango,escalera,countFirst);

                break;

            case SUITED:

                escaleras = comprobarEscaleraSO(rango, escalera,colorPalo);
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
                        if (board.get(i).getCardText().charAt(1) == 'h') {
                            h1 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 'c') {
                            c1 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 's') {
                            s1 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 'd') {
                            d1 = true;
                        }
                    }

                    if (second == board.get(i).getValor()) {
                        ++countSecond;
                        if (board.get(i).getCardText().charAt(1) == 'h') {
                            h2 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 'c') {
                            c2 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 's') {
                            s2 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 'd') {
                            d2 = true;
                        }
                    }
                    //Contamos cartas seguidas

                    freq = Collections.frequency(escalera, board.get(i).getValor());
                    if (freq > maxCount && !(board.get(i).getValor() == first || board.get(i).getValor() == second)) {
                        maxCount = freq;
                    }
                }
                palo = comprobarColor(rango, board, 1,countFirst,countSecond);

                //Analizo jugada
                if (escaleras) {
                    switch (palo) {

                        case 'h': {
                            if (straightFlush) {

                                result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "h" + secondString + "h", 1));
                                result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                            }else{
                                result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "h", 1));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                            }
                        }

                        break;

                        case 'c': {
                            if (straightFlush) {

                                result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "c" + secondString + "c", 1));
                                result.add(new output(Ranking.STRAIGHT, firstString + "h" + secondString + "h, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                            }else{
                                result.add(new output(Ranking.FLUSH, firstString + "c" + secondString + "c", 1));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "h" + secondString + "h, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                            }
                        }
                        break;
                        case 'd': {
                            if (straightFlush) {

                                result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "d" + secondString + "d", 1));
                                result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "c, " + firstString + "h" + secondString + "h, " + firstString + "s" + secondString + "s", 3));
                            }else{
                                 result.add(new output(Ranking.FLUSH, firstString + "d" + secondString + "d", 1));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "c, " + firstString + "h" + secondString + "h, " + firstString + "s" + secondString + "s", 3));
                            }
                        }
                        break;

                        case 's': {
                            if (straightFlush) {

                                result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "s" + secondString + "s", 1));
                                result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "h" + secondString + "h", 3));
                            }else{
                                result.add(new output(Ranking.FLUSH, firstString + "s" + secondString + "s", 1));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "h" + secondString + "h", 3));
                            }
                        }
                        break;

                        case 'x': {
                            result.add(new output(Ranking.STRAIGHT, rango.getText(), 4));
                        }

                    }

                } else if (countFirst == 3 || countSecond == 3) {
                    result.add(new output(Ranking.FOUROFAKIND, rango.getText(), 1));
                } else if (countFirst == 2 && countSecond >= 1) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 2));
                } else if (countFirst >= 1 && countSecond == 2) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 2));
                } else if (maxCount >= 2 && countFirst == 2 && countSecond == 0) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 2));
                } else if (maxCount >= 2 && countFirst == 0 && countSecond == 2) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 2));
                } else if (maxCount >= 3 && countFirst == 1 && countSecond == 0) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 3));
                } else if (maxCount >= 3 && countFirst == 0 && countSecond == 1) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 3));
                }else if(palo!='x')   {
                    
                    switch(palo){
                         case 'h': {
                            
                         
                                result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "h", 1));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                            }
                        

                        break;

                        case 'c': {
                            
                                result.add(new output(Ranking.FLUSH, firstString + "c" + secondString + "c", 1));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "h" + secondString + "h, " + firstString + "d" + secondString + "d, " + firstString + "s" + secondString + "s", 3));
                            
                        }
                        break;
                        case 'd': {
                            
                                 result.add(new output(Ranking.FLUSH, firstString + "d" + secondString + "d", 1));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "c, " + firstString + "h" + secondString + "h, " + firstString + "s" + secondString + "s", 3));
                            
                        }
                        break;

                        case 's': {
                            
                                result.add(new output(Ranking.FLUSH, firstString + "s" + secondString + "s", 1));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "c, " + firstString + "d" + secondString + "d, " + firstString + "h" + secondString + "h", 3));
                            
                        }
                        break;

               
                    }
                    
                } else if (countFirst == 2 || countSecond == 2) {
                    result.add(new output(Ranking.THREEOFAKIND, rango.getText(), 2));
                } else if (countFirst == 1 && countSecond == 1) {

                    if (!(h1 && h2)) {
                        carta += firstString + "h" + secondString + "h ";

                    }
                    if (!(c1 && c2)) {
                        carta += firstString + "c" + secondString + "c ";
                    }
                    if (!(s1 && s2)) {
                        carta += firstString + "s" + secondString + "s ";
                    }
                    if (!(d1 && d2)) {
                        carta += firstString + "d" + secondString + "d ";
                    }
                    result.add(new output(Ranking.TWOPAIR, carta, 3));
//                } else if (countFirst == 1 && countSecond == 0 && maxCount == 2) {
//                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 3));
//                } else if (countFirst == 0 && countSecond == 1 && maxCount == 2) {
//                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 3));
                } else if (countFirst == 1) {
                    comparaParejasSO(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1), true, first);
                } else if (countSecond == 1) {
                    comparaParejasSO(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1), true, second);
                } else {
                    result.add(new output(Ranking.NOMADEHAND, rango.getText(), 4));
                }
                
                if(palo=='x')
                    drawColor(rango,board,1,countFirst,countSecond);
                
                break;

            case OFF_SUIT:
                escaleras = comprobarEscaleraSO(rango, escalera,colorPalo);
                int aux=first;
                first=second;
                second=aux;
                h1 = false;
                h2 = false;
                s1 = false;
                s2 = false;
                c1 = false;
                c2 = false;
                d1 = false;
                d2 = false;
                carta = "";
                int res = 0;
                if(countFirst > 0 || countSecond > 0)res = 3;
                for (int i = 0; i < board.size(); ++i) {
                    if (first == board.get(i).getValor()) {

                        ++countFirst;
                        if (board.get(i).getCardText().charAt(1) == 'h') {
                            h1 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 'c') {
                            c1 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 's') {
                            s1 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 'd') {
                            d1 = true;
                        }
                    }

                    if (second == board.get(i).getValor()) {
                        ++countSecond;
                        if (board.get(i).getCardText().charAt(1) == 'h') {
                            h2 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 'c') {
                            c2 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 's') {
                            s2 = true;
                        } else if (board.get(i).getCardText().charAt(1) == 'd') {
                            d2 = true;
                        }
                    }

                    freq = Collections.frequency(escalera, board.get(i).getValor());
                    if (freq > maxCount && !(board.get(i).getValor() == first || board.get(i).getValor() == second)) {
                        maxCount = freq;
                    }
                }
                palo = comprobarColor(rango, board, 2,countFirst,countSecond);
                //Analizo jugada
                if (escaleras) {
                    switch (palo) {

                        case 'h': {
                            if (straightFlush) {
                                result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s", 3));
                                result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                        + firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "s"
                                        + firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h", 9));
                            }else{
                                result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s", 6-res));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                        + firstString + "d" + secondString + "c" + firstString + "d" + secondString + "s"
                                        + firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                            }
                        }
                        break;
                        case 'c': {
                            if (straightFlush) {

                                result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "c" + secondString + "c, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s", 3));
                                result.add(new output(Ranking.STRAIGHT, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                        + firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "s"
                                        + firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h", 9));
                            }else{
                                result.add(new output(Ranking.FLUSH, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s", 6-res));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                        + firstString + "d" + secondString + "h" + firstString + "d" + secondString + "s"
                                        + firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                            }
                        }
                        break;

                        case 'd': {

                            if (straightFlush) {

                                result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "s", 3));
                                result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                        + firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                        + firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h", 9));
                            }else{
                                result.add(new output(Ranking.FLUSH, firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "s", 6-res));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s"
                                        + firstString + "h" + secondString + "d" + firstString + "h" + secondString + "s"
                                        + firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, ", 6));
                            }
                        }
                        break;

                        case 's': {
                            if (straightFlush) {

                                result.add(new output(Ranking.STRAIGHTFLUSH, firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h", 3));
                                result.add(new output(Ranking.STRAIGHT, firstString + "c" + secondString + "h, " + firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                        + firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "s"
                                        + firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s", 9));
                            }else{
                                result.add(new output(Ranking.FLUSH, firstString + "s" + secondString + "c, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "h", 6-res));
                                result.add(new output(Ranking.NOMADEHAND, firstString + "c" + secondString + "d, " + firstString + "c" + secondString + "s"
                                        + firstString + "d" + secondString + "c" + firstString + "d" + secondString + "s"
                                        + firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, ", 6));
                            }
                        }
                        break;

                        case 'x': {
                            result.add(new output(Ranking.STRAIGHT, rango.getText(), 12));
                        }

                    }

                } else if (countFirst == 3) {
                    result.add(new output(Ranking.FOUROFAKIND, rango.getText(), 3 - countSecond));
                } else if (countSecond == 3) {
                    result.add(new output(Ranking.FOUROFAKIND, rango.getText(), 3 - countFirst));

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
                }else if (maxCount >= 3 && countFirst == 1 && countSecond == 1) {
                    result.add(new output(Ranking.FULLHOUSE, rango.getText(), 6));    
                }else if(palo!='x')   {
                    
                    switch(palo){
                         case 'h': {
                                
                                if(countFirst==1){
                                    result.add(new output(Ranking.FLUSH, firstString + "c" + secondString + "h, " + firstString + "d" + secondString + "h, " + firstString + "s" + secondString + "h" , 3));
                                    
                                }else if (countSecond==1){
                                    result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s" , 3));
                                    
                                }else{
                                    result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "c, " + firstString + "h" + secondString + "d, " + firstString + "h" + secondString + "s, "+firstString + "c" + secondString + "h, " + firstString + "d" + secondString + "h, " + firstString + "s" + secondString + "h" , 6));
                                }
                                    
                            }
                        

                        break;

                        case 'c': {
                            if(countFirst==1){
                                    result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "c, " + firstString + "d" + secondString + "c, " + firstString + "s" + secondString + "c" , 3));
                                    
                                }else if (countSecond==1){
                                    result.add(new output(Ranking.FLUSH, firstString + "c" + secondString + "h, " + firstString + "d" + secondString + "c, " + firstString + "s" + secondString + "c" , 3));
                                    
                                }else{
                                    result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "c, " + firstString + "d" + secondString + "c, " + firstString + "s" + secondString + "c, " + firstString + "c" + secondString + "h, " + firstString + "d" + secondString + "c, " + firstString + "s" + secondString + "c" , 6));
                                }
                            
                        }
                        break;
                        case 'd': {
                            if(countFirst==1){
                                    result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "d, " + firstString + "c" + secondString + "d, " + firstString + "s" + secondString + "d" , 3));
                                    
                                }else if (countSecond==1){
                                    result.add(new output(Ranking.FLUSH, firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "s" , 3));
                                    
                                }else{
                                    result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "d, " + firstString + "c" + secondString + "d, " + firstString + "s" + secondString + "d, " + firstString + "d" + secondString + "h, " + firstString + "d" + secondString + "c, " + firstString + "d" + secondString + "s", 6));
                                }
                            
                        }
                        break;

                        case 's': {
                            if(countFirst==1){
                                    result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "s, " + firstString + "d" + secondString + "s, " + firstString + "c" + secondString + "s" , 3));
                                    
                                }else if (countSecond==1){
                                    result.add(new output(Ranking.FLUSH, firstString + "s" + secondString + "h, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "c" , 3));
                                    
                                }else{
                                    result.add(new output(Ranking.FLUSH, firstString + "h" + secondString + "s, " + firstString + "d" + secondString + "s, " + firstString + "c" + secondString + "s, "+firstString + "s" + secondString + "h, " + firstString + "s" + secondString + "d, " + firstString + "s" + secondString + "c"  , 6));
                                }
                            
                        }
                        break;

               
                    }
                } else if (countFirst == 2 || countSecond == 2) {
                    result.add(new output(Ranking.THREEOFAKIND, rango.getText(), 6));
                } else if (countFirst == 1 && countSecond == 1) {

                    if (!(h1 && h2)) {
                        carta += firstString + "h" + secondString + "h ";

                    }
                    if (!(c1 && c2)) {
                        carta += firstString + "c" + secondString + "c ";
                    }
                    if (!(s1 && s2)) {
                        carta += firstString + "s" + secondString + "s ";
                    }
                    if (!(d1 && d2)) {
                        carta += firstString + "d" + secondString + "d ";
                    }
                    result.add(new output(Ranking.TWOPAIR, carta, 6));
//                } else if (countFirst == 1 && countSecond == 0 && maxCount == 2) {
//                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 3));
//                } else if (countFirst == 0 && countSecond == 1 && maxCount == 2) {
//                    result.add(new output(Ranking.TWOPAIR, rango.getText(), 3));
                } else if (countFirst == 1) {
                    comparaParejasSO(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1), false, first);
                } else if (countSecond == 1) {
                    comparaParejasSO(rango, escalera.get(0), escalera.get(1), escalera.get(escalera.size() - 1), false, second);
                } else {
                    result.add(new output(Ranking.NOMADEHAND, rango.getText(), 12));
                }
                
                if(palo=='x')
                    drawColor(rango,board,2,countFirst,countSecond);
                
                break;

        }

    }

    public static boolean comprobarEscalera(PairButton rango, ArrayList<Integer> escalera, int countFirst) {
        boolean result = true;
        int i = 1, iguales = 0,cont=0;
        ArrayList<Integer> aux = new ArrayList<Integer>();
        aux = (ArrayList<Integer>) escalera.clone();
        if(rango.getFirstCard() == 14) aux.add(1);
        if(escalera.contains(14)) aux.add(1);
        aux.add(rango.getFirstCard());

        Collections.sort(aux);

        while (i < aux.size() ) {

            if (aux.size() == 5) {
                if (aux.get(i) - aux.get(i - 1) != 1) {
                    result = false;
                }
            } else if (aux.size() == 6 || (aux.size() == 7 && countFirst==1)) {
                
                if(aux.get(i)==aux.get(i - 1)+1)
                    cont++;
                
                if (aux.get(i) == aux.get(i - 1)) {
                    iguales++;
                }
                
                if ((aux.get(i) - aux.get(i - 1) > 1 ) ) {
                    result = false;
                }
                else if (iguales > 1) {
                    result = false;
                }
                
                
            } else {
                result = false;
                if(aux.get(i)==aux.get(i - 1)+1)
                    cont++;
            }
            
             
            i++;
        }
        if((aux.get(0)==1 && aux.get(aux.size()-2)==5 && cont==4)|| (aux.get(0)==1 && aux.get(aux.size()-3)==5 && cont==4) )
            result=true;
        
        return result;
    }

    //comprobar esto
    public static boolean comprobarEscaleraSO(PairButton rango, ArrayList<Integer> escalera, ArrayList<Character> color){
        int i = 1, cont = 1;
        ArrayList<Integer> asc = new ArrayList<Integer>();
        ArrayList<Character> res = new ArrayList<Character>();
        ArrayList<Integer> aux = new ArrayList<Integer>();
        aux = (ArrayList<Integer>) escalera.clone();

        if (rango.getFirstCard() == 14) {
            aux.add(1);
        }
        if(escalera.contains(14)) aux.add(1);
        aux.add(rango.getFirstCard());
        aux.add(rango.getSecondCard());
        Collections.sort(aux);
        
        
        
        while (i < aux.size()) {

            if (aux.get(i) - aux.get(i - 1) == 1) {
                cont++;
                asc.add(aux.get(i-1));
            } else if  (aux.get(i) - aux.get(i - 1) == 0);
            else if (cont < 5) {
                cont = 1; 
            }
           
            i++;
        }
        if(aux.get(aux.size()-1) - aux.get(aux.size()-2) == 1)asc.add(aux.get(aux.size()-1));
        
        int k = 0;
        for(int j = 0; j < color.size();++j){
            if(asc.contains(escalera.get(j))){
                res.add(color.get(j));
            }
        }
        for(int cnt = 1; cnt <res.size(); ++cnt){
            if(res.get(cnt) != res.get(cnt-1))straightFlush = false;
        }
        if (cont >= 5) {
            return true;
        } else {
            return false;
        }
    }

    public static char comprobarColor(PairButton rango, ArrayList<BoardButton> color, int type,int countFirst,int countSecond) {
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
            
            i++;
        }
        
        if(type==0 || type==1){
                if (countFirst >=1 || countSecond >=1) {
                    return aux1;
                }
            }
        else if(type==2){
                if (countFirst >=1 && countSecond >=1) {
                    return aux1;
                }
            }

        if (type==0 || type==2) {
            if (h < 4 && s < 4 && d < 4 && c < 4) {
                return aux1;
            }
        } else if (h < 3 && s < 3 && d < 3 && c < 3) {
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
    
    public static void drawColor(PairButton rango, ArrayList<BoardButton> color, int type,int countFirst,int countSecond) {
        boolean repeticion=true;
        char aux1 = 'x';
        int i = 0, h = 0, s = 0, c = 0, d = 0;
        int combs;
        ArrayList<BoardButton> aux = color;

        while (i < aux.size() && aux.size()<5 ) {

            if (color.get(i).getCardText().charAt(1) == ('h')) {
                h++;
            } else if (color.get(i).getCardText().charAt(1) == ('s')) {
                s++;
            } else if (color.get(i).getCardText().charAt(1) == ('c')) {
                c++;
            } else if (color.get(i).getCardText().charAt(1) == ('d')) {
                d++;
            }
           

            i++;
        }
         if(type==0 || type==1){
                if (countFirst >=1 || countSecond >=1) {
                    repeticion=false;
                }
            }
            else if(type==2){
                if (countFirst >=1 && countSecond >=1) {
                    repeticion=false;
                }
            }
         
        if(repeticion){
            if (type==0 || type==2) {
                if (h == 3 || s == 3 || d == 3 || c == 3) {

                    if(rango.getFirstCard()==rango.getSecondCard()){
                        result.add(new output(Ranking.DRAWFLUSH, rango.getText(), 3));
                    }


                    else{
                        if(countFirst==1 || countSecond == 1)
                            result.add(new output(Ranking.DRAWFLUSH, rango.getText(),3));
                        
                        else
                            result.add(new output(Ranking.DRAWFLUSH, rango.getText(),6));
                    }
                }
            } else if (h == 2 || s == 2 || d == 2 || c == 2) {

                result.add(new output(Ranking.DRAWFLUSH, rango.getText(), 1));
            }
        }
        
    }

    public static void comparaParejas(PairButton range, int first, int second, int last) {
        int firstCard = range.getFirstCard();
        

        if (firstCard > first) {
            result.add(new output(Ranking.OVERPAIR, range.getText(), 6));
        } //la combinatoria esta mal exepto la primera distincion
        else if (firstCard == first) {
            result.add(new output(Ranking.TOPPAIR, range.getText(), 6));
        } //preguntar sobre el weak pair 
        else if (firstCard < first && firstCard > last && ppbtp==0) {
            result.add(new output(Ranking.PPBTP, range.getText(), 6));
            ppbtp++;
        } else if (firstCard == second && firstCard > last) {
            result.add(new output(Ranking.MIDDLEPAIR, range.getText(), 6));
        } else {
            result.add(new output(Ranking.WEAKPAIR, range.getText(), 6));
        }

    }

    public static void comparaParejasSO(PairButton range, int first, int second, int last, boolean suited, int firstCard) {

       

        if (suited) {
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
        } else {
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
    
    public static void  draw(PairButton rango, ArrayList<Integer> escalera, int countFirst){
        boolean resultBool = true;
        int i = 1,fallo=0,auxFallo=0,combs;
        String draw="";
        ArrayList<Integer> aux = new ArrayList<Integer>();
        aux = (ArrayList<Integer>) escalera.clone();
        //if(rango.getFirstCard() == 14) aux.add(1);
        aux.add(rango.getFirstCard());

        Collections.sort(aux);

        while (i < aux.size() && resultBool && escalera.size()<5) {
            if(aux.size()==4 || aux.size()==5){
                
                if((aux.size()==4 && countFirst >=1) || (aux.size()==5 && countFirst > 1)){
                    resultBool=false;
                }
                
                if (aux.get(i) - aux.get(i - 1) != 1) {
                    fallo++;
                    auxFallo=i;
                    if(aux.get(i) - aux.get(i - 1) ==2 && fallo==1 && aux.size()==4){
                        resultBool=true;
                    }
                    
                    else if((aux.get(i) - aux.get(i - 1) ==2 && fallo==2 && aux.size()==5) || (aux.get(i) - aux.get(i - 1) ==2 && fallo==1 && aux.size()==5) ){
                        resultBool=true;
                    }
                    
                }
                
                
                if(aux.get(i) - aux.get(i - 1) != 1 && countFirst==1 && aux.size()==5){
                    fallo--;
                }
                
                if(fallo>2)
                   resultBool=false;
            }
           
            else {
                resultBool= false;
            } 

            i++;
        }
        
        if(resultBool){
            if(aux.size()==4){
                if(fallo==0){
                    draw="Open-Ended";
                    result.add(new output(Ranking.DRAWOPENENDED,rango.getText(),48));
                    combs=48;
                }
                else{
                    draw="Gutshot";
                    result.add(new output(Ranking.DRAWGUSTSHOT,rango.getText(),24));
                    combs=24;
                }
                        
            }
            else if (aux.size()==5){
                
                if(fallo==0 && countFirst==1){
                    draw="Open-Ended";
                    result.add(new output(Ranking.DRAWOPENENDED,rango.getText(),24));
                    combs=24;
                }
                else if (countFirst==1 && fallo > 1){
                    draw="Gutshot";
                    result.add(new output(Ranking.DRAWGUSTSHOT,rango.getText(),12));
                    combs=12;
                }
                else if (countFirst==0 && fallo > 1){
                    draw="Gutshot";
                    result.add(new output(Ranking.DRAWGUSTSHOT,rango.getText(),24));
                    combs=24;
                }
            }      
        }
        

    }
    
    public static void  drawSO(PairButton rango, ArrayList<Integer> escalera, int countFirst,int countSecond){
        boolean result = true,result2=true,result3=true;
        int i = 1,j=1,k=1,fallo=0,fallo2=0,fallo3=0,combs,combs2,combs3,falloPos=0,gap=0;
        String draw="",draw2="",draw3="";
        ArrayList<Integer> aux = new ArrayList<Integer>();
        ArrayList<Integer> aux2 = new ArrayList<Integer>();
        ArrayList<Integer> aux3= new ArrayList<Integer>();
        aux = (ArrayList<Integer>) escalera.clone();
        aux2 = (ArrayList<Integer>) escalera.clone();
        aux3= (ArrayList<Integer>) escalera.clone();
        //if(rango.getFirstCard() == 14) aux.add(1);
        aux.add(rango.getFirstCard());
        aux2.add(rango.getSecondCard());
        aux3.add(rango.getFirstCard(),rango.getSecondCard());


        Collections.sort(aux);
        Collections.sort(aux2);
        Collections.sort(aux3);

        while (i < aux.size() && result && escalera.size()<5) {
            if(aux.size()==4 || aux.size()==5){
                
                if((aux.size()==4 && countFirst >=1) || (aux.size()==5 && countFirst > 1)){
                    result=false;
                }
                
                if (aux.get(i) - aux.get(i - 1) != 1) {
                    fallo++;
                    
                    if(aux.get(i) - aux.get(i - 1) ==2 && fallo==1 && aux.size()==4){
                        result=true;
                    }
                    
                    else if((aux.get(i) - aux.get(i - 1) ==2 && fallo==2 && aux.size()==5) || (aux.get(i) - aux.get(i - 1) ==2 && fallo==1 && aux.size()==5) ){
                        result=true;
                    }
                    
                }
                
                
                if(aux.get(i) - aux.get(i - 1) != 1 && countFirst==1 && aux.size()==5){
                    fallo--;
                }
                
                if(fallo>2)
                   result=false;
            }
           
            else {
                result= false;
            } 

            i++;
        }
        
        while (j < aux2.size() && result2) {
            if(aux2.size()==4 || aux2.size()==5){
                
                if((aux2.size()==4 && countSecond >=1) || (aux2.size()==5 && countSecond > 1)){
                    result2=false;
                }
                
                if (aux2.get(j) - aux2.get(j - 1) != 1) {
                    fallo2++;
                    
                    if(aux2.get(j) - aux2.get(j - 1) ==2 && fallo2==1 && aux2.size()==4){
                        result2=true;
                    }
                    
                    else if((aux2.get(j) - aux2.get(j - 1) ==2 && fallo2==2 && aux2.size()==5) || (aux2.get(j) - aux2.get(j - 1) ==2 && fallo2==1 && aux2.size()==5) ){
                        result2=true;
                    }
                    
                }
                
                
                if(aux2.get(j) - aux2.get(j - 1) != 1 && countSecond==1 && aux2.size()==5){
                    fallo2--;
                }
                
                if(fallo2>2)
                   result2=false;
            }
           
            else {
                result2= false;
            } 

            j++;
        }
        
        while (k < aux3.size() && result3) {
            if(aux3.size()==5){
                 if (aux3.get(k) - aux3.get(k - 1) != 1) {
                    fallo3++;
                    falloPos=k;
                    //1gap 1fallo / 2gap 2fallos / 1gap 2fallos
                    if(aux3.get(k) - aux3.get(k - 1) ==2 && fallo3<=2){
                        gap++;
                    }
                    if(countFirst==1 && countSecond==1){
                        result3=false;
                    }
                    if((countFirst==1 || countSecond==1)&&( fallo3>2 || (fallo3==2 && gap==0)))
                        result3=false;
                    
                    
                 }
            }
            //fallo==0 && contFirst==1 && contSecond==1 (Open-end) / fallo==1 && contFirst==1 && contSecond==1 (gutshot)
            else if(aux3.size()==6){
                if (aux3.get(k) - aux3.get(k - 1) != 1) {
                    fallo3++;
                    falloPos=k;
                    //1gap 1fallo / 2gap 2fallos / 1gap 2fallos
                    if(aux3.get(k) - aux3.get(k - 1) ==2 && fallo3<=2){
                        gap++;
                    }
                    if(countFirst==1 && countSecond==1 && fallo3>2){
                        result3=false;
                    }
                    //fallo3>=2 && gap>=2
                    if((countFirst==1 || countSecond==1)&&( fallo3>2 || (fallo3==3 && gap==0)))
                        result3=false;
                    
                    if(fallo3>3)
                        result3=false;
            }
            k++;
        }
        
        if(result){
            if(aux.size()==4){
                if(fallo==0){
                    draw="Open-Ended";
                    combs=48;
                }
                else{
                    draw="Gutshot";
                    combs=24;
                }
                        
            }
            else if (aux.size()==5){
                
                if(fallo==0 && countFirst==1){
                    draw="Open-Ended";
                    combs=24;
                }
                else if (countFirst==1 && fallo > 1 ){
                    draw="Gutshot";
                    combs=12;
                }
                else if (countFirst==0 && fallo > 1){
                    draw="Gutshot";
                    combs=24;
                }
            }      
        }
        

        }
    }
    
    public static void  drawSOBOOl(PairButton rango, ArrayList<Integer> escalera, int countFirst,int countSecond){
        
        //ArrayList<boolean> aux = new ArrayList<boolean>();
        
//        for(int i=0;i<14;i++){
//            aux.add(false);
//        }
       
    }
    public static ArrayList<output> cargaLista(ArrayList<output> in) {
        ArrayList<output> out = new ArrayList<output>();

        out.add(new output(Ranking.STRAIGHTFLUSH, "", 0));
        out.add(new output(Ranking.FOUROFAKIND, "", 0));
        out.add(new output(Ranking.FULLHOUSE, "", 0));
        out.add(new output(Ranking.FLUSH, "", 0));
        out.add(new output(Ranking.STRAIGHT, "", 0));
        out.add(new output(Ranking.THREEOFAKIND, "", 0));
        out.add(new output(Ranking.TWOPAIR, "", 0));
        out.add(new output(Ranking.OVERPAIR, "", 0));
        out.add(new output(Ranking.TOPPAIR, "", 0));
        out.add(new output(Ranking.PPBTP, "", 0));
        out.add(new output(Ranking.MIDDLEPAIR, "", 0));
        out.add(new output(Ranking.WEAKPAIR, "", 0));
        out.add(new output(Ranking.NOMADEHAND, "", 0));
        out.add(new output(Ranking.DRAWFLUSH, "", 0));
        out.add(new output(Ranking.DRAWOPENENDED, "", 0));
        out.add(new output(Ranking.DRAWGUSTSHOT, "", 0));



        for (output act : in) {
            for (output res : out) {
                if (act.ranking.equals(res.ranking)) {
                    res.cards += act.cards +"("+ act.jugadas+ "), ";
                    res.jugadas += act.jugadas;
                }
                if(!act.ranking.equals(Ranking.DRAWFLUSH)&& !act.ranking.equals(Ranking.DRAWGUSTSHOT)&& !act.ranking.equals(Ranking.DRAWOPENENDED))
                    total+= res.jugadas;
            }
        }

        return out;
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


}
