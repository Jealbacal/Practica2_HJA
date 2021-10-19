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
    
    //Aqui se devuelve el rango carta a carta. La entrada para la logica inversa.
    //Misma forma que rangos
    private ArrayList<String> range2Text;
    
    //Si podeis guardar aqui el String que escribir en el bloque de texto seria maravilloso.
    private String textFromRange;


    public Ap1(String textfield){

        this.texfield=textfield;
        rangos =  new  ArrayList<String>();
        elems = new ArrayList<String>();
        range2Text = new ArrayList<String>();
        parse();
        procesa();

    }
    
    //Constructor sin argumentos para procesar la tabla. No el texto.
    public Ap1(){
        rangos =  new  ArrayList<String>();
        elems = new ArrayList<String>();
        range2Text = new ArrayList<String>();
        
        //Aqui los metodos que procesan range2Text
    }

    public void parse(){

        int lenght =this.texfield.length();
        int i=0;
        String aux="";
        
        while(i < lenght){
          
          if(texfield.charAt(i) != ','){
              aux=aux+texfield.charAt(i);
          }
          
          else if (texfield.charAt(i) == ','){
              this.elems.add(aux);
              aux="";
             
          }
            
          i++;
        }
         this.elems.add(aux);   
    }

    public void procesa(){
        String mayor=null;
        String menor=null;
       for(String x : this.elems){
           if(x.contains("s") || x.contains("o")){
               
               if(x.contains("+")){
                   mayor=x.substring(0, x.length()-1);
                   masOperator(mayor,false);
                   
               }
               
               else if(x.contains("-")){
                   
                   menosOperator(x,false);
               }
               
               else
                  this.rangos.add(x);
           }
           else{
                if(x.contains("+")){
                   mayor=x.substring(0, x.length()-1);
                   masOperator(mayor,true);
               }               
               else if(x.contains("-")){ //JJ-AA
                   menosOperator(x,true);
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
                
                aux= aux.replace(aux.charAt(1),valorToChar(i));
                this.rangos.add(aux);
                ++i;
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
   
   //AKs-A2s  JJ-QQ
   public void menosOperator(String rango, boolean pair){
       
       int dif, ini, fin;
       String aux;
       if (!pair){
           ini = cartaParse(rango.charAt(1));
           fin = cartaParse(rango.charAt(5));
           aux = rango.substring(0,3);
           for(int i = Math.min(ini, fin); i <= Math.max(ini, fin); i++){
                aux= aux.replace(aux.charAt(1),valorToChar(i));
                this.rangos.add(aux);
           }
       }
       else{
           ini = cartaParse(rango.charAt(0));
           fin = cartaParse(rango.charAt(3));
           for(int i = Math.min(ini, fin); i <= Math.max(ini, fin); i++){
               String s =String.valueOf(valorToChar(i));
               aux= s+s;
               this.rangos.add(aux);
           }
       }

      
   }
   
   //Copia la entrada que lleva la GUI en el array range2Text para procesarlo
   public void fillRange2Text(ArrayList<String> r){
       //No se si esta asignacion funciona, si no funciona se hace el bucle y listo.
       range2Text = r;
       
//       for(String s : r){
//           range2Text.add(s);
//       }
   }
   
   public String getTextFromRange(){
       return textFromRange;
   }
   
   //Testeo del parse
   public static void main(String args[]){
       
       //Parece que no procesa bien el ultimo elemento
       //Los intervalos no salen
       
       String test1 = "AKs-A2s, TT+, T2s"; // No funciona
       String test2 = "TT+,T2s+"; // Falta que saque el T2s+, el TT+ lo saca bien
       String test3 = "AA"; // No funciona
       String test4 = "AA,TT"; // Saca solo AA
       String test5 = "J3s+"; 
       
       Ap1 logic = new Ap1(test1);
       
       for(String s : logic.rangos){
           System.out.println(s);
       }
   }
}
