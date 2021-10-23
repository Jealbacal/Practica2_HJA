/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package poker.practica2.hja.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 *
 * @author jjcar
 */
public class RangeGrid extends javax.swing.JPanel {
    
    private final static int _size = 14;
    private ArrayList<PairButton> button_list;
    private ArrayList<PairButton> sel_button_list;
    private PairButton[][] button_matrix;
   
    

    /**
     * Creates new form RangeGrid
     */
    public RangeGrid() {
        button_list = new ArrayList<>();
        sel_button_list = new ArrayList<>();
        button_matrix = new PairButton[_size-1][_size-1];
        
        initComponents();
        createButtons();
        fillMatrix(button_list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout(13, 13));
    }// </editor-fold>//GEN-END:initComponents

    private void fillMatrix(ArrayList<PairButton> b){
        
        int col_count = 0;
        int row_count = 0;
        int index = col_count;
        
        while( row_count < _size-1){
            if(col_count == _size-1){
                col_count = 0;
                row_count++;
            }
            if ( row_count < _size -1){
                  button_matrix[row_count][col_count] = b.get(index);
            }
          
            col_count++;
            index++;
        }
       
    }

    //Crea la matriz de botones que van a representar el rango.
    // Y los mete en un ArrayList para gestionarlos.
    private void createButtons(){
        
        //Recorre la tabla
        for (int i = _size; i >= 2; i-- ){
            for(int j = _size; j >= 2; j--){
                
                //Creacion del boton y atributos
                PairButton pair = new PairButton();
                pair.addActionListener(pair);
                
                //Botones de las Parejas
                if( i == j ){

                    pair.setType(Type.PAIR);
                    
                    pair.setText(rangeButtonText(i) + 
                            rangeButtonText(j));
                    pair.setPairColor();
                    
                }
                // Botones de las cartas Suited
                else if ( i > j ){
                    
                     pair.setType(Type.SUITED);
                    
                    pair.setText(rangeButtonText(i) + 
                            rangeButtonText(j) +
                            "s");
                    pair.setSuitedColor();
                }
                // Botones de las cartas Offsuit
                else if( i < j ){
                    
                     pair.setType(Type.OFF_SUIT);
                    
                    pair.setText(rangeButtonText(j) + 
                            rangeButtonText(i) +
                            "o");
                    pair.setOffSuitColor();
                    //pair.setForeground(Color.WHITE);
                }
                
                //Añade a la gui
                add(pair);
                // Añade al array
                button_list.add(pair);
                
            }
        }
    }
    
    // Metodo para escribir el texto de las cartas en el boton dado un interador
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
    
    // Devuelve el texto del boton
    public String getPairText(int i){
        return button_list.get(i).getText();
    }
    
    //Limpia la tabla de botones seleccionados
    public void clear(){
        for (PairButton b : button_list){
            if(b.isSelected()){
                b.setSelected(false);
                b.setColor(b.getType());
                sel_button_list.clear();
            }
        }
    }
    
    //Metodo que busca el boton dado un texto y lo selecciona
    public void searchButton(String text){
        
        int i = 0;
        int n = button_list.size();
        boolean found = false;
        
        while(i < n && !found){
            if(button_list.get(i).getText().equals(text)){
                found = true;
                selectButton(button_list.get(i));
            }else{
                i++;
            }
        }
        
    }
    
    //Selecciona un boton
    private void selectButton(PairButton b){
        b.setSelected(true);
        b.setSelectedColor();
        sel_button_list.add(b);
    }
    
    //Se asegura de que todos los botones seleccionados estan en la lista
    //de botones seleccionados
    public void checkSelected(){
        sel_button_list.clear();
        for(PairButton b : button_list){
            if(b.isSelected()){
                sel_button_list.add(b);
            }else{
                sel_button_list.remove(b);
            }
        }
    }
    
    //Crea una lista (semi-ordenada) con los strings (nombres) de los botones seleccionados
    /*Orden:
        1.- Las parejas (bien)
        2.- Las suited (bien)
        3.- Las offsuit
            - v1 Creo que las offsuit no quedan ordenadas para comprobar los intervalos bien.
            Por ejemplo, AQo-A5o no estarian consecutivas si hay alguna otro offsuit seleccionado.
            - v2 Collections.sort(off_list); puede que lo ordene
            - v2 Collections.sort(off_list); lo ordena de manera decreciente :(
            - v3 Collections.sort(off_list, Collections.reverseOrder()); ya lo hace bien!!!
            - v3 No funciona todavia, el orden alfabetico lo jode.
            - v4 Ya funciona por reestructuracion de la estrucutra de botones. Lista -> Matriz
    */
    public ArrayList<String> getSelButtonList(){
        
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> pair_list = new ArrayList<>();
        ArrayList<String> suit_list = new ArrayList<>();
        ArrayList<String> off_list = new ArrayList<>();
        
        for( PairButton b : sel_button_list){
            if (null != b.getType()){
                
                switch (b.getType()) {
                case PAIR:
                    pair_list.add(b.getText());
                    break;
                case SUITED:
                    suit_list.add(b.getText());
                    break;
                case OFF_SUIT:
                    //off_list.add(b.getText());
                    break;
                default:
                    break;
                }
            }
        }
        
        
        
        
        int col = 0;
        
        while (col < _size-1){
            for (int i = 0; i < _size-1; i++){
                if(button_matrix[i][col].getType().equals(Type.OFF_SUIT)){
                    if(button_matrix[i][col].isSelected()){
                        off_list.add(button_matrix[i][col].getText());
                    }
                    
                }
            }
            col++;
        }
        
        list.addAll(pair_list);
        list.addAll(suit_list);
        list.addAll(off_list);
        
        return list;
    }
    
    /**Recorre la lista de texto pasada y seleccciona el boton que corresponde con el texto.
     * 
     * @param percent_range La lista de texto de botones a seleccionar
     */
    public void selectSliderRange(ArrayList<String> percent_range){
        
       for (String s : percent_range){
           searchButton(s);
       }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
