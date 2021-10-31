/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.GUI;

import java.util.ArrayList;

/**
 *
 * @author jjcar
 */
public class CombGrid extends javax.swing.JPanel {
    
    private final int n_rows = 4;
    private final int n_cols = 14;
    private ArrayList<BoardButton> button_list;
    private ArrayList<BoardButton> sel_button_list;
    
    public CombGrid(){
        button_list = new ArrayList<>();
        sel_button_list = new ArrayList<>();
        createButtons();
    }
    
    private void createButtons(){
        
        for (int i = 0; i < n_rows; i++){
            for(int j = n_cols; j >= 2; j--){
                
                BoardButton b_button = new BoardButton();
                b_button.addActionListener(b_button);
                
                buttonConfig(i,j,b_button);
                
                button_list.add(b_button);
                
                this.add(b_button);
            }
        }
        
//        for( int i = n_rows; i >= 2; i--){
//            for(int j = 0; j < n_cols; j++){
//                
//                BoardButton b_button = new BoardButton();
//                b_button.addActionListener(b_button);
//                
//                buttonConfig(i,j,b_button);
//                
//                button_list.add(b_button);
//                
//                this.add(b_button);
//                
//            }
//        }

          
    }
    
    private String rangeButtonText(int i){
        
        String text = "";
        
        switch(i){
            case 14:
                text += "A";
                break;
            case 13:
                text += "K";
                break;
            case 12:
                text += "Q";
                break;
            case 11:
                text += "J";
                break;
            case 10:
                text += "T";
                break;
            default:
                text += Integer.toString(i);
                break;
                
        }
        
        return text;
    }
    
    private void buttonConfig(int i, int j, BoardButton b_button){
        
        String card = rangeButtonText(j);
        
        switch(i){
            case 0:
                b_button.setText(card + "♥");
                b_button.setSuit(Suit.HEARTS);
                break;
            case 1:
                b_button.setText(card + "♣");
                b_button.setSuit(Suit.CLUBS);
                break;
            case 2:
                 b_button.setText(card + "♦");
                 b_button.setSuit(Suit.DIAMONDS);
                break;
            case 3:
                 b_button.setText(card + "♠");
                 b_button.setSuit(Suit.SPADES);
                break;
        }
    }
    
    /**
     * Metodo que checkea las cartas seleccionadas
     */
    public void checkSelected(){
        
        sel_button_list.clear();
        
        for(BoardButton b : button_list){
            if(b.isSelected()){
                sel_button_list.add(b);
            }
        }
        
        BoardButton.setSelCount(sel_button_list.size());
    }
    
    /**
     * Reocoge el texto de las cartas seleccionadas (en el board)
     * @return: Una lista de strings con el texto de las cartas seleccionadas
     */
    public ArrayList<String> getSelectedCards(){
        
        ArrayList<String> sel_cards = new ArrayList<>();
        
        for(BoardButton b : sel_button_list){
            sel_cards.add(b.getText());
        }
        
        return sel_cards;
    }
    
    /**
     * Limpia las cartas seleccionadas de la tabla y la lista.
     */
    public void clear(){
        checkSelected();
        for(BoardButton b :sel_button_list){
            b.setSelected(false);
            b.setSuitColor(b.getSuit());
            BoardButton.setSelCount(0);
        }
        sel_button_list.clear();
    }
}
