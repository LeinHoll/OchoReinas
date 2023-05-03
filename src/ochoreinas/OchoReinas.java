package ochoreinas;

import java.util.ArrayList;

public class OchoReinas {
    
    //static Tile[][] tablero = new Tile[8][8];
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
    
/*    static void buildTablero(){
        for(int fil = 0 ; fil < 8 ; fil++){
            for(int col = 0 ; col < 8 ; col++){
                tablero[fil][col] = new Tile();
            }
        }
    }
    
    static void drawTablero(){
        for(int fil = 0 ; fil < 8 ; fil++){
            for(int col = 0 ; col < 8 ; col++){
                if(tablero[fil][col].reina)
                    System.out.print("| A ");
                else{                    
                    if(!tablero[fil][col].green)
                        System.out.print("| D ");
                    else
                        System.out.print("|   ");
                }
            }
            System.out.println("");
        }
    }
    
    static void randoReina(){
        while(true){
            int rFil = (int) Math.round(7 * Math.random());
            int rCol = (int) Math.round(7 * Math.random());
            
            if(tablero[rFil][rCol].green){
                tablero[rFil][rCol].green = false;
                tablero[rFil][rCol].reina = true;
                
                killZone(rFil, rCol);
                break;
            }
        }
    }
        
    static void killZone(int fil, int col){
        //kill column
        for(int x = 0 ; x < 8 ; x++){
            tablero[x][col].green = false;
        }
        //kill line
        for(int y = 0 ; y < 8 ; y++){
            tablero[fil][y].green = false;
        }
        //kill bottom diagonals
        int yMinus = col;
        int yPlus = col;
        for(int x = fil + 1 ; x < 8 ; x++){
            yMinus--;
            yPlus++;
            if(yMinus >= 0 ){
                tablero[x][yMinus].green = false;
            }
            if(yPlus < 8 ){
                tablero[x][yPlus].green = false;
            }
        }
        //kill upper diagonals
        yMinus = col;
        yPlus = col;
        for(int x = fil - 1 ; x >= 0 ; x--){
            yMinus--;
            yPlus++;
            if(yMinus >= 0 ){
                tablero[x][yMinus].green = false;
            }
            if(yPlus < 8 ){
                tablero[x][yPlus].green = false;
            }
        }
    }*/
}
