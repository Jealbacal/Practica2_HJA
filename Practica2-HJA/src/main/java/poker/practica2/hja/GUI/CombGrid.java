/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author jjcar
 */
public class CombGrid extends javax.swing.JPanel {
    
    //Numero de columnas
    private final int n_rows = 4;
    //Numero de filas
    private final int n_cols = 14;
    
    //Lista de los botones
    private ArrayList<BoardButton> button_list;
    //Lista de los botones seleccionados
    private ArrayList<BoardButton> sel_button_list;
    
    
    
    //Contructor basico
    public CombGrid(){
        button_list = new ArrayList<>();
        sel_button_list = new ArrayList<>();
        createButtons();
    }
    
    /**
     * Inicializa la tabla de botones
     */
    private void createButtons(){
        
        for (int i = 0; i < n_rows; i++){
            for(int j = n_cols; j >= 2; j--){
                
                BoardButton b_button = new BoardButton(j);
                b_button.addActionListener(b_button);
                
                buttonConfig(i,j,b_button);
                
                button_list.add(b_button);
                
                this.add(b_button);
            }
        }
        
        /**
         * Trocito de codigo que pone la tabla al reves
         * Lo dejo por si hay que cambiar la orientacion en algun momento
         * 
         * Tambien habria que alternar el numero de columnas y filas en el layout
         * Tambien modificar el metedo buttonConfig alternando la i por la j
         * Tambien alternar los atributos n_rows y n_cols 
         */
               
        /**
        for( int i = n_rows; i >= 2; i--){
            for(int j = 0; j < n_cols; j++){
                
                BoardButton b_button = new BoardButton();
                b_button.addActionListener(b_button);
                
                buttonConfig(i,j,b_button);
                
                button_list.add(b_button);
                
                this.add(b_button);
                
            }
        }*/
    
    }
    
    /**
     * Recibe un entero y parsea para mostrar el texto de la carta
     * @param i: El entero a parsear
     * @return: Un string con el valor de la carta
     */
    private String ButtonText(int i){
        
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
    
    /**
     * Configura atributos individuales del boton
     * @param i: Su posicion en la fila
     * @param j: Su posicion en la columna
     * @param b_button: El Boton
     */
    private void buttonConfig(int i, int j, BoardButton b_button){
        
        String card = ButtonText(j);
        
        switch(i){
            case 0:
                b_button.setText(card + "♥");
                b_button.setCardText(card + "h");
                b_button.setSuit(Suit.HEARTS);
                
                break;
            case 1:
                b_button.setText(card + "♣");
                b_button.setCardText(card + "c");
                b_button.setSuit(Suit.CLUBS);
                break;
            case 2:
                 b_button.setText(card + "♦");
                 b_button.setCardText(card + "d");
                 b_button.setSuit(Suit.DIAMONDS);
                break;
            case 3:
                 b_button.setText(card + "♠");
                 b_button.setCardText(card + "s");
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
    public ArrayList<String> getSelectedCardsText(){
        
        ArrayList<String> sel_cards = new ArrayList<>();
        
        for(BoardButton b : sel_button_list){
            sel_cards.add(b.getCardText());
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
        }
        sel_button_list.clear();
        BoardButton.setSelCount(0);
    }
    
    /**
     * Getter de la lista de botones
     * @return: La lista de botones del grid
     */
    public ArrayList<BoardButton> getButtonList(){
        return button_list;
    }
    
    /**
     * Getter de la lista de botones seleccionados
     * @return Una lista de botones que estan seleccionados
     */
    public ArrayList<BoardButton> getSelectedCards(){
        return sel_button_list;
    }
    
   
    
}
