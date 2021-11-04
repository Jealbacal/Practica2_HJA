/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package poker.practica2.hja.GUI;

/**
 *
 * @author jjcar
 */
public enum Type {
    PAIR(6), OFF_SUIT(12), SUITED(4);
    
    private int combs;

    private Type(int combs){
        this.combs =combs;
    }
    
    public int getCombs(){
        return combs;
    }
    
    public void removeCombs(int n){
        this.combs -= n;
    }
    
}
