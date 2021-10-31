/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;

/**
 *
 * @author jjcar
 */
public class BoardButton extends JToggleButton implements ActionListener {
   
    //Numero minimo de botones seleccionados
    private static final int min_sel_count = 3;
    //Numero de maximo de botones seleccionados
    private static final int max_sel_count = 5;
    //Numero botones hay seleccionados
    private static int sel_count;
    //El palo de la carta
    private Suit suit;
    
    //--------------COLORES-----------------------------------------
    
    //Colores de los corazones (Rojo)
    //No seleccionado
    private final Color h_color_NS = new Color(255,204,204);
    //Seleccionado
    private final Color h_color_S = Color.red;
    
    //Colores de los treboles (Verde)
    //No Seleccionado
    private final Color c_color_NS = new Color(204,255,204);
    //Seleccionado
    private final Color c_color_S = Color.green;
    
    //Colores de los diamantes (Cyan)
    //No seleccionado
    private final Color d_color_NS = new Color(204,255,255);
    //Seleccionado
    private final Color d_color_S = Color.cyan;
    
    //Colores de las picas (Gris)
    //No seleccionado
    private final Color s_color_NS = new Color(224,224,224);
    //Seleccionado
    private final Color s_color_S = new Color(128,128,128);
    
    //-----------------------------------------------------------------------
    
    private Font custom_font = new Font("Arial", Font.BOLD, 22);
    
    /**
     * Constructor
     */
    public BoardButton(){
        init();
    }
    
    /**
     * Metodo que inicializa parametros de los botones
     */
    private void init(){
         this.setSelected(false);
         this.setFont(custom_font);
         this.setMargin(new Insets(0,0,0,0));
         this.setBorder(null);
         sel_count = 0;
         
    }
    
    /**
     * Asigna a la carta un palo especifico
     * @param s Un enumerado con el palo de las cartas
     */
    public void setSuit(Suit s){
        suit = s;
        setSuitColor(s);
    }
    
    /**
     * Getter del palo
     * @return Un enumerado con el palo de las cartas
     */
    public Suit getSuit(){
        return this.suit;
    }
    
    /**
     * Asigna un color dependiendo de:
     *  - El palo de la carta
     *  - Su estado
     * @param s Un enumerado con el palo de las cartas
     */
    public void setSuitColor(Suit s){
        switch(s){
            case HEARTS:
                if(!this.isSelected()){ this.setBackground(h_color_NS); }
                else { this.setBackground(h_color_S); }
                break;
            case CLUBS:
                 if(!this.isSelected()){ this.setBackground(c_color_NS); }
                else { this.setBackground(c_color_S); }
                break;
            case DIAMONDS:
                 if(!this.isSelected()){ this.setBackground(d_color_NS); }
                else { this.setBackground(d_color_S); }
                break;
            case SPADES:
                 if(!this.isSelected()){ this.setBackground(s_color_NS); }
                else { this.setBackground(s_color_S); }
                break;
        }
    }
    
    /**
     * El actionlistener de los botones de la tabla de combinaciones
     * Lo que pasa cuando aprietas un boton
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Si el boton no esta seleccionado (Es puto al reves no tiene sentido nada)
        if(this.isSelected()){
             //Si hay menos de 5 cartas en el board y se esta seleccionando el boton.
            if(sel_count < max_sel_count){
                //Se selecciona
                setSuitColor(this.suit);
                sel_count++;
            //Si hay cinco cartas en el board y se intenta seleccionar otra carta mas.
            }else {
                //No se selecciona
                this.setSelected(false);
            }
        }
        //Si el boton esta seleccionado (siempre se puede deseleccionar)
        else{
            //Se deselecciona
            setSuitColor(this.suit);
            sel_count--;
        } 
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Devuelve el numero de cartas seleccionadas
     * @return Un entero entre 0 y 5;
     */
    public static int getSelCount(){
        return sel_count;
    }
    
    public static void setSelCount(int i){
        if( i > 0 || i < 5){
            sel_count = i;
        }
    }
}
