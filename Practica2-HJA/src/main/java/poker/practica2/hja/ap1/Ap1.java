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
public class Ap1 {

    public ArrayList<String> rangos; //A2O+ -> A3o...Ako  JJ+ ->
    public String texfield;
    public ArrayList<String> elems;


    public Ap1(String textfield){

        this.texfield=textfield;
        ArrayList<String>rangos =  new  ArrayList<String>();
        ArrayList<String> elems = new ArrayList<String>();

    }

    public void parse(){

        int lenght =this.texfield.length();
        int i=0;
        
        while(i < lenght){
          String aux=null;
          
          if(texfield.charAt(i) != ','){
              aux=aux+texfield.charAt(i);
          }
          
          else if (texfield.charAt(i) == ','){
              this.elems.add(aux);
              aux=null;
             
          }
            
          i++;
        }
            
    }

    public void procesa(){
        String mayor=null;
        String menor=null;
       for(String x : this.elems){
           
           if(x.contains("s") || x.contains("o")){
               
               if(x.contains("+")){
                   mayor=x.substring(0, x.length()-1);
                   
               }
               
               else if(x.contains("-")){
                   
                   mayor=x.substring(0, 3);
                   menor=x.substring(3, x.length()-1);
               }
               
               else
                  this.rangos.add(x);
           }
           
           else{
               
                if(x.contains("+")){
                   
               }
               
               else if(x.contains("-")){
                   
               }
               
               else
                  this.rangos.add(x);
           }
               
           
       }
    }

    public int cartaParse(char carta){
        
        int valor;
        
         switch(carta) {
            case'A':
                valor = 14;
                break;
            case'K':
                valor = 13;
                break;
            case'Q':
                valor = 12;
                break;
            case'J':
                valor = 11;
                break;
            case'T':
                valor = 10;
                break;
            default:
                valor = Character.getNumericValue(carta);
        }
         
         return valor;
    }
    
    
    public char valorToChar(int valor){
        
        char representacion;
        
        switch(valor){
            
            case 14:
                representacion='A';
                break;
            
            case 13:
                representacion='K';
                break;
                
            case 12:
                representacion='Q';
                break;
            
            case 11:
                representacion='J';
                break;
            
            case 10:
                representacion='T';
                break;
            
            default:
                representacion=(char)(valor+'0');
        }
        return representacion;
    }
   
   public void masOperator(String rango,boolean pair){
       
       int i= cartaParse(rango.charAt(1));
       int max=cartaParse(rango.charAt(0));
       String aux=rango;
       if(!pair){
            while(i<max){
                
                aux= aux.replace(rango.charAt(1),valorToChar(i));
                this.rangos.add(aux);
                

                i++;
            }
       }
       
       else{
          
           while(i<=14){
                
                String s =String.valueOf(valorToChar(i));
                aux= s+s;
                this.rangos.add(aux);               
                i++;
            }
           
       }
   }    
}
