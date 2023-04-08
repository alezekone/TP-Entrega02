/*
Para entrega 2
 */
package tp;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author aguzman
 */
public class PronosticoDeportivo {
    private ListaEquipos equipos;
    private ListaPartidos partidos;
    private ListaPronosticos pronosticos;

    public PronosticoDeportivo() {
        equipos = new ListaEquipos();
        partidos = new ListaPartidos();
        pronosticos = new ListaPronosticos();
    }

    public void play(){
        // cargar y listar los equipos
        System.out.println("\n" + "*".repeat(10) + " Equipos " + "*".repeat(10) + "\n");
        equipos.setNombreArchivo("equipos.csv");
        equipos.cargarDeArchivo();
        System.out.println("Los equipos cargados son:\n" + equipos.listar());
        
        // Buscar y mostrar el equipo 17...
        System.out.println ("Buscando el equipo");
        int idEquipo = 17;
        Equipo eq = equipos.getEquipo(idEquipo);
        if (eq != null) {
            System.out.println (eq);
        } else {
            System.out.println ("No se encontró el equipo...");
        }
        
        // Cargar y listar partidos.
        System.out.println("\n" + "*".repeat(10) + " Partidos " + "*".repeat(10) + "\n");
        partidos.setNombreArchivo("partidos.csv");
        partidos.cargarDeArchivo(equipos);
        System.out.println("Los partidos cargados son: \n" + partidos.listar());
        
        // Buscar y mostrar el partido 7.
        System.out.println ("Buscando el partido");
        int idPartido = 7;
        Partido p = partidos.getPartido(idPartido);
        if (p != null) {
            System.out.println (p);
        } else {
            System.out.println ("No se encontró el partido...");
        }
        
        // Cargar y listar pronosticos.
        System.out.println("\n" + "*".repeat(10) + " Pronosticos " + "*".repeat(10) + "\n");
        pronosticos.setNombreDeArchivo("pronosticos.csv");
        pronosticos.cargarDeArchivo(7, equipos, partidos);
        System.out.println("Los pronósticos del participante 7 son: \n" + pronosticos.listar());
        
        // Creamos participantes y cargamos un pronostico
        if ((pronosticos.getPronosticos()).isEmpty()){
            System.out.println("Pronosticos es una lista vacia.");
        } else {
            System.out.println("Pronosticos no es una lista vacía.");
        }
        Participante participante = new Participante(7, "Joaquin", pronosticos, 0);
        
        if (equipos==null){
            System.out.println("Equipos es null.");
        } else {
            System.out.println("Equipos no es null.");
        }
        participante.cargarPronosticos(equipos, partidos);
        if (participante.getPronosticos()==null){
            System.out.println("Pronosticos es null.");
        } else {
            System.out.println("Pronosticos no es null.");
        }
        if (((participante.getPronosticos()).getPronosticos()).isEmpty()){
            System.out.println("Pronosticos es una lista vacia.");
        } else {
            System.out.println("Pronosticos no es una lista vacía.");
        }
        // participante.getPronosticos();
        System.out.println(participante);
        
        char pruebita = 'e';
        System.out.println("El caracter es: " + Character.toUpperCase(pruebita));
 
    }    
}
