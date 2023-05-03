package ochoreinas;

import java.util.ArrayList;

public class OchoReinas {
    
    static ArrayList<Tile> tablero = new ArrayList();
    static ArrayList<Tile> reinas = new ArrayList();
    static int incremento = 1;
    static int iteracion = 0;
    static boolean repeat = true;
    
    public static void main(String[] args) {   
        while(repeat){
            buildTablero();
            randoReina();
            drawTablero();
        }
    }
    
    static void buildTablero(){
        tablero.clear();
        reinas.clear();
        incremento = 1;
        
        for(int fila = 0 ; fila < 8 ; fila++){
            for(int columna = 0 ; columna < 8 ; columna++){
                Tile cuadro = new Tile(fila, columna);
                tablero.add(cuadro);
            }
        }
    }
    
    static void randoReina(){
        for(int i = 0 ; i < 8 ; i++){
            if(tablero.size() > 0){
                int rando = (int) Math.round((tablero.size()-1) * Math.random());
                Tile reina = tablero.get(rando);
                reina.setReina(incremento);
                reinas.add(reina);
                tablero.remove(rando);
                incremento++;
                killZone(reina);
            } else {
                System.out.println("whops " + ++iteracion);
                break;
            }
        }
        if(reinas.size() == 8){
            System.out.println("yay " + ++iteracion);
            repeat = false;
        }
    }
    
    static void killZone(Tile reina){
        ArrayList<Tile> cuadros = new ArrayList();
        for(Tile cuadro : tablero){
            if(cuadro.getFila() == reina.getFila() || cuadro.getColumna() == reina.getColumna()){
                cuadros.add(cuadro);
            }
        }
        
        int xPlus = reina.getColumna() + 1;
        int yPlus = reina.getFila() + 1;
        int yMinus = reina.getFila() - 1;
        
        for( ; xPlus < 8 ; xPlus++){
            for(Tile cuadro : tablero){
                if(cuadro.getFila() == yPlus 
                        && cuadro.getColumna() == xPlus
                        || cuadro.getFila() == yMinus 
                        && cuadro.getColumna() == xPlus){
                   cuadros.add(cuadro);
                }
            }
            yPlus++;
            yMinus--;
        }
        
        int xMinus = reina.getColumna() - 1;
        yPlus = reina.getFila() + 1;
        yMinus = reina.getFila() - 1;
        
        for( ; xMinus >= 0 ; xMinus--){
            for(Tile cuadro : tablero){
                if(cuadro.getFila() == yPlus 
                        && cuadro.getColumna() == xMinus
                        || cuadro.getFila() == yMinus 
                        && cuadro.getColumna() == xMinus){
                   cuadros.add(cuadro);
                }
            }
            yPlus++;
            yMinus--;
        }
        
        for(Tile cuadro : cuadros){
            tablero.remove(cuadro);
        }
    }
    
    static void drawTablero(){
        for(int fila = 0 ; fila < 8 ; fila++){
            for(int columna = 0 ; columna < 8 ; columna++){
                System.out.print(isReina(fila, columna));
            }
            System.out.println("");
        }
        
    }
    
    static String isReina(int fila, int columna){
        for(Tile reina: reinas){
            if(reina.getFila() == fila && reina.getColumna() == columna)
                return " " + reina.reina + " |";
        }
        return "   |";
    }
   
}
