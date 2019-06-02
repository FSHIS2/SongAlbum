package graphic;

/**
 * Práctica 4 - Biblioteca de álbumes de canciones (equipo)
 * @author Grupo 47-1
 * @version 8-12-2018
 * 
 * Clase Album que representa un conjunto de canciones
 */

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String nombre; //nombre del Álbum
    private List<Cancion> canciones; //conjunto de canciones - Álbum
    
    /**
     * Constructor que inicializa un álbum al que se le pasa una String con el identificador.
     * 
     * @param nombre    nombre del Álbum
     */
    public Album(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }
    
    /**
     * Devuelve el identificador(nombre) del álbum.
     * 
     * @return nombre   nombre del Álbum
     */
    public String dameNombre() {
        return nombre;
    }
    
    /**
     * Devuelve el número de canciones que contiene el Álbum
     * 
     * @return tamaño del contenedor    número de canciones del Álbum
     */
    public int númeroDeCanciones() {
        return canciones.size();
    }
    
    /**
     * Devuelve la canción que se encuentra en la posición pasada
     * por parámetro. Si la posición no es válida devuelve null.
     * 
     * @param posicion  posición de la canción a obtener
     * @return canción en la posición deseada
     */
    public Cancion dameCanción(int posicion) {
        if (posicion >= canciones.size() || posicion < 0) {
            return null;
        }
        return canciones.get(posicion);
    }
    
    /**
     * Añade la canción pasada por parámetro al Álbum
     * 
     * @param canción a añadir
     */
    public void añadeCanción(Cancion c) {
        canciones.add(c);
    }
    
    /**
     * Quita la canción que se encuentre en la posición pasada por paramétro
     * Si la posición no es válida no hace nada.
     * 
     * @param posición en la que se encuentra la canción a quitar
     */
    public void quitaCanción(int posicion) {
        if (canciones.size() == 0 || posicion > canciones.size()){
            return;
        }
        if (posicion <= canciones.size() - 1 || posicion >= 0) {
            canciones.remove(posicion);
        }
    }
    
    /**
     * Devuelve la suma de las duraciones de las canciones
     * contenidas en el Álbum
     * 
     * @return total    suma de las duraciones de las canciones
     */
    public int duración() {
        int total = 0;
        for (int i = 0; i < canciones.size(); i++) {
            total += canciones.get(i).dameDuración();
        }
        return total;
    }
    
    /**
     * Devuelve una String con un listado numerado empezando en uno 
     * (después del número va un cierra paréntesis y un espacio en blanco) 
     * de todas las canciones del álbum seguidas por saltos de línea ("\n") 
     * y precedidas por el nombre del álbum al que corresponden seguido de 
     * dos puntos y un salto de línea (":\n")
     */
    @Override
    public String toString() {
        String s = dameNombre() + ":\n";
        for (int i = 0; i < canciones.size(); i++) {
            Cancion c = canciones.get(i);
            s = s + (i + 1) + ") " + c.toString() + "\n";
        }
        return s;
    }
}
