package graphic;

/**
 * Clase Biblioteca que representa un conjunto de albumes
 */
import java.util.*;
public class Biblioteca {
    private List<Album> albumes; //conjunto de álbumes
    
    /**
     * Constructor que define el tipo de contenedor
     */
    public Biblioteca() {
        this.albumes = new ArrayList<>();
    }
    
    /**
     * Añade un álbum a la biblioteca. Si ya existe uno 
     * con el mismo nombre lo sustituye por el nuevo
     * 
     * @param álbum a añadir
     */
    public void añadeÁlbum(Album a) {
        int indice = indiceExistente(a);
        if (indice > -1) {
            albumes.remove(indice);
            albumes.add(indice, a);
        } else {
            albumes.add(a);
        }
    }
    
    /**
     * Devuelve un álbum a partir de su nombre. Si no existe devuelve null.
     * 
     * @param nombre del álbum a buscar
     * @return el álbum si lo encuentra, sino, null
     */
    public Album dameÁlbum(String nombre) {
        for (int i = 0; i < albumes.size(); i++) {
            if (albumes.get(i).dameNombre() == nombre) {
                return albumes.get(i);
            }
        }
        return null;
    }
    
    /**
     * Elimina un álbum a partir de su nombre. Si no existe no hace nada.
     * 
     * @param el nombre del álbum a quitar
     */
    public void eliminaÁlbum(String nombre) {
        Album a = dameÁlbum(nombre);
        int indice = indiceExistente(a);
        if (indice > -1) {
            albumes.remove(indice);
        }
    }
    
    /**
     * Devuelve el conjunto de las canciones que se repiten 
     * (que tienen el mismo nombre) más de una vez en toda la biblioteca.
     * 
     * @return las canciones repetidas más de una vez
     */
    public Set<Cancion> dameCancionesRepetidas() {
        Set<Cancion> unicos = new HashSet<Cancion>();
        Set<Cancion> duplicados = new HashSet<Cancion>();
        for (int i = 0; i < albumes.size(); i++) {
            Album a = albumes.get(i);
            for (int j = 0; j < a.númeroDeCanciones(); j++) {
                Cancion c = a.dameCanción((j));
                if (unicos.contains(c)) {
                    duplicados.add(c);
                } else {
                    unicos.add(c);
                }
            }
        }
        return duplicados;
    }
    
    /**
     * Devuelve una lista con los intérpretes ordenados 
     * desde el que más canciones interpreta hasta el que menos 
     * (en caso de que interpreten el mismo número de canciones siguen el orden alfabético)
     * 
     * @return devuelve una lista con los intérpretes ordenados
     */
    public List<String> dameIntérpretes() {
        Map<String, Integer> contador = new HashMap<>();
        List<String> resultado = new ArrayList<>();

        for (int i = 0; i < albumes.size(); i++) {
            Album a = albumes.get(i);
            for (int j = 0; j < a.númeroDeCanciones(); j++) {
                Cancion c = a.dameCanción((j));

                String interprete = c.dameIntérprete();

                if (contador.containsKey(interprete)) {
                    int valor = contador.get(interprete);
                    contador.put(interprete, valor + 1);
                } else {
                    contador.put(interprete, 1);
                }
            }
        }
        List<String> interpretes = new ArrayList<String>(contador.keySet());
        if (interpretes.size() == 0) {
            return resultado;
        }

        while (interpretes.size() > 0) {
            int maxActual = 0;
            List<String> maxInterpretes = new ArrayList<>();

            for (int i = 0; i < interpretes.size(); i++) {
                String interprete = interpretes.get(i);
                if (contador.get(interprete) >= maxActual) {
                    maxActual = contador.get(interprete);
                }
            }

            for (int i = 0; i < interpretes.size(); i++) {
                String interprete = interpretes.get(i);
                if (contador.get(interprete) == maxActual) {
                    maxInterpretes.add(interprete);
                }
            }

            Collections.sort(maxInterpretes);
            resultado.addAll(maxInterpretes);

            for (int i = 0; i < maxInterpretes.size(); i++) {
                interpretes.remove(maxInterpretes.get(i));
            }
        }

        return resultado;
    }
    
    /**
     * Devuelve una String con la concatenación de todos 
     * los álbumes de la biblioteca seguidos por una línea en blanco
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < albumes.size(); i++) {
            Album a = albumes.get(i);
            s = s + a.toString() + "\n";
        }
        return s;
    }
    
    /**
     * Método auxiliar que nos permite saber si un álbum ya existe
     */
    private int indiceExistente(Album a) {
        for (int i = 0; i < albumes.size(); i++) {
            if (albumes.get(i).dameNombre().equals(a.dameNombre())) {
                return i;
            }
        }
        return -1;
    }
}
