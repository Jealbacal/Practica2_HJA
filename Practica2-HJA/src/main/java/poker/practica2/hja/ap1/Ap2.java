/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.ap1;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Ap2 {
    
    ArrayList<String>rankings;
    int percent;
    ArrayList<String>result;
    int _size=13;

    public Ap2(ArrayList<String> rankings, int percent){
        
        this.rankings=rankings;
        this.percent=percent;
        result= new ArrayList<String>();
        
    }
    
    public void bestRanking(){
        
        int best = ((_size*_size)*percent)/100;
        
        String aux="";
        
        for(int i=0;i<best;i++){
            
            aux=this.rankings.get(i);
            result.add(aux);
        }
        
    }
    
    
    
}
