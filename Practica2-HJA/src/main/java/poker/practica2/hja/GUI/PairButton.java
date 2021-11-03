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
public class PairButton extends JToggleButton implements ActionListener {
    
    private Type _type;
    private int first_card;
    private int second_card;
    private final Color pair_color = Color.white;
    private final Color off_color = new Color(52,152,219);
    private final Color s_color = new Color(26,188,156);
    private final Color sel_color = Color.magenta;
    private final Color sel_color2 = Color.MAGENTA;
    
        
    public PairButton(int i, int j){
       init();
       setFirstCard(i);
       setSecondCard(j);
    }

    private void init(){
         this.setFont(new Font("Arial", Font.BOLD, 17));
         this.setMargin(new Insets(0,0,0,0));
         this.setBorder(null);
         this.setSelected(false);
         
    }
    
    public void setType(Type type){
        _type = type;
    }
    
    public Type getType(){
        return this._type;
    }
    
    public void setFirstCard(int n){
        first_card = n;
    }
    
    public void setSecondCard(int n){
        second_card = n;
    }
    
    public int getFirstCard(){
        return first_card;
    }
    
    public int getSecondCard(){
        return second_card;
    }
    
    //El action listener
    @Override
    public void actionPerformed(ActionEvent e){
        
        // Si no estaba seleccionado
        if(!this.isSelected()){
            switch(this._type){
                case PAIR:
                    this.setBackground(pair_color);
                    break;
                case OFF_SUIT:
                    this.setBackground(off_color);
                    break;
                case SUITED:
                    this.setBackground(s_color);
                    break;
            }
        //Si estaba seleccionado
        }else{
            this.setBackground(sel_color);
        }
    } 

    //Pone el color de las manos que son parejas
    public void setPairColor(){
        this.setBackground(pair_color);
    }
    
    //Pone el color de las manos que son Suited
    public void setSuitedColor(){
        this.setBackground(s_color);
    }
    
    //Pone el color de las manos que son OffSuit
    public void setOffSuitColor(){
        this.setBackground(off_color);
    }
    
    //Pone el color de la carta segun el tipo
    public void setColor(Type type){
        switch(type){
            case PAIR:
                this.setPairColor();
                break;
            case OFF_SUIT:
                this.setOffSuitColor();
                break;
            case SUITED:
                this.setSuitedColor();
                break;
        }
    }
    
    public void setSelectedColor(){
        this.setBackground(sel_color);
    }
    
    public void setSelectedColor2(){
        this.setBackground(sel_color2);
    }
    
        
    
}
