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
    public Ap1(ArrayList<String> rangos){
        this.rangos = rangos;
        elems = new ArrayList<String>();
        range2Text = new ArrayList<String>();
        texfield="";
        fillRange2Text(rangos);
        
        
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
       int i=0;
       int j=1;
       String aux="";
       boolean solo=false;
       boolean ultimo=false;
       
//       for(String s : r){
//           range2Text.add(s);
//       }
        
         if(j==range2Text.size())
               aux= aux+range2Text.get(i);

        while ( j < range2Text.size()){
            
            //inicio del rango
            String rngI=range2Text.get(i);
            //pivote
            String rngIN=range2Text.get(j-1);
            //final del rango
            String rngF =range2Text.get(j);
            
            //comprobaciones para los pares 
            if(rngI.charAt(0)==rngI.charAt(1)){
                
                //cartas contiguas
                if(rngF.charAt(0)==rngF.charAt(1) && cartaParse(rngIN.charAt(0))- cartaParse(rngF.charAt(0))==1 ){
                    j++;
                    solo=true;
                }
                
                //entra cuando:
                //1-las cartas dejan de ser contiguas
                //2-las cartas dejan de ser pares
                //3-el final del rango llega al final del array
                if((rngF.charAt(0)==rngF.charAt(1) && cartaParse(rngIN.charAt(0))-cartaParse(rngF.charAt(0))>1 && rngI != rngIN) || rngF.charAt(0)!=rngF.charAt(1) && rngI != rngIN || j >= range2Text.size()){
                    
                    //si el rango inicial es el A significa que es el operador +
                    if(rngI.charAt(0)=='A'){
                        
                        //si j llega al final del array (el operador + es lo ultimo que encontro)
                        if(j>= range2Text.size())
                            aux=aux+ rngF+"+";
                        
                        //el operador + esta entremedias
                         else
                             aux=aux+ rngIN+"+,";
                        
                    }
                    
                    //si j llega al final del array (el operador - es lo ultimo que encontro)
                    else if(j>= range2Text.size())
                        aux=aux+ rngI+"-"+rngF;
                    
                    //el operador - esta entremedias
                    else
                        aux=aux+ rngI+"-"+rngIN+",";
                    
                    i=j;
                    j++;
                    
                    if(j==range2Text.size())
                        ultimo=true;
                        
                    solo = true;
                }
                
                //un rango suelto
                if(!solo || ultimo){
                    
                    //si el rango suelto esta al final
                    if(j>= range2Text.size())
                        aux= aux+range2Text.get(i);
                    
                    //si esta entremedias
                    else
                        aux= aux+range2Text.get(i)+",";
                        
                    i++;
                    j++;
                    
                    //si hay dos rangos sueltos al final del array
                    if(j==range2Text.size())
                        aux= aux+range2Text.get(i);
                }    
            }
            
            //comprobaciones del Suited y los Offs
            else {
                
                //cartas contiguas
                if(cartaParse(rngIN.charAt(1))- cartaParse(rngF.charAt(1))==1 && cartaParse(rngIN.charAt(0))== cartaParse(rngF.charAt(0))){
                    j++;
                    solo=true;
                }
                
                //entra cuando:
                //1-las cartas dejan de ser contiguas dentro de la misma fila
                //2-las cartas dejan de ser contguas por ser de distintas filas
                //3-el final del rango llega al final del array
                if( cartaParse(rngIN.charAt(1))-cartaParse(rngF.charAt(1))>1 && rngI != rngIN || j>= range2Text.size() || (cartaParse(rngIN.charAt(0))!= cartaParse(rngF.charAt(0)) && rngI != rngIN)  ){
                    
                     //si el rango inicial la diferencia de los dos caracteres es uno es un operador +
                     if(cartaParse(rngI.charAt(0))- cartaParse(rngI.charAt(1))==1){
                         
                         //si j llega al final del array (el operador + es lo ultimo que encontro)
                         if(j>= range2Text.size())
                            aux=aux+ rngF+"+";
                         
                         //el operador + esta entremedias
                         else
                             aux=aux+ rngIN+"+,";
                     }
                        
                    //si j llega al final del array (el operador - es lo ultimo que encontro) 
                    else if(j>= range2Text.size())
                        aux=aux+ rngI+"-"+rngF;
                    
                    //el operador - esta entremedias
                    else
                        aux=aux+ rngI+"-"+rngIN+",";
                    
                    i=j;
                    j++;
                    
                    if(j==range2Text.size())
                        ultimo=true;
                    
                    solo=true;
                }
                
                //un rango suelto
                if(!solo || ultimo){
                    
                    //si el rango suelto esta al final
                    if(j>= range2Text.size())
                        aux= aux+range2Text.get(i);
                    
                    //si esta entremedias
                    else
                        aux= aux+range2Text.get(i)+",";
                    i++;
                    j++;
                    
                    //si hay dos rangos sueltos al final del array
                    if(j==range2Text.size())
                        aux= aux+range2Text.get(i);
                }    
                
            }
            
            solo=false;
        }
        
        
         
         this.texfield=aux;
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
       Ap1 logic2 = new Ap1(logic.rangos);
       
       for(String s : logic.rangos){
           System.out.println(s);
       }
       
       System.out.println(logic2.texfield);
   }
}
