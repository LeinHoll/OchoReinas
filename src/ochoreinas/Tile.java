package ochoreinas;

public class Tile {
    int fila = 0;
    int columna = 0;
    int reina = 0;

    public Tile() {
    }
    
    public Tile(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getReina() {
        return reina;
    }

    public void setReina(int reina) {
        this.reina = reina;
    }
    
    
}