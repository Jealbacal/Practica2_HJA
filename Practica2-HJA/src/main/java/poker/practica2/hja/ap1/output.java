/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.ap1;

/**
 *
 * @author Pablo
 */


public class output {
    
    //Full house, flush ....
    public Ranking ranking;
    
    //Puede ser AKo        o quizas varias cartas AsKd , AsKc ...
    public String cards;
    
    //numero de jugadas
    public int jugadas;
    
    
    public output (Ranking ranking, String cards, int jugadas){
        this.ranking = ranking;
        this.cards = cards;
        this.jugadas = jugadas;        
    }
    
}
