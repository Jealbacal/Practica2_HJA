/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;

/**
 *
 * @author jjcar
 */
public class PairButton extends JToggleButton implements ActionListener {
    
    private Type _type;
    private final Color pair_color = Color.green;
    private final Color off_color = Color.red;
    private final Color s_color = Color.cyan;
    private final Color sel_color = Color.yellow;
        
    public PairButton(){
       init();
    }

    private void init(){
         this.setFont(new Font("Arial", Font.BOLD, 12));
         this.setSelected(false);
         
    }
    
    public void setType(Type type){
        _type = type;
    }
    
    public Type getType(){
        return this._type;
    }
    
    //El action listener
    @Override
    public void actionPerformed(ActionEvent e){
        
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
        }else{
            this.setBackground(sel_color);
        }
    } 

    public void setPairColor(){
        this.setBackground(pair_color);
    }
    
    public void setSuitedColor(){
        this.setBackground(s_color);
    }
    
    public void setOffSuitColor(){
        this.setBackground(off_color);
    }
    
    
    
        
    
}
