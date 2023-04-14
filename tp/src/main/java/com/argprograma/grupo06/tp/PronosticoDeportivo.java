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
    private ListaParticipantes participantes;

    public PronosticoDeportivo() {
        equipos = new ListaEquipos();
        partidos = new ListaPartidos();
        pronosticos = new ListaPronosticos();
        participantes = new ListaParticipantes();
    }

    public void play(){
        // cargar y listar los equipos
        System.out.println("\n" + "*".repeat(10) + " Equipos " + "*".repeat(10) + "\n");
        equipos.setNombreArchivo("equipos.csv");
        //equipos.cargarDeArchivo();
        equipos.cargarDeDB();
        System.out.println(equipos.listar());
        
        // Buscar y mostrar el equipo 17...
        System.out.println("\n" + "*".repeat(10) + " Buscando el equipo con id 17... " + "*".repeat(10) + "\n");
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
        System.out.println(partidos.listar());
        
        // Buscar y mostrar el partido 7.
        System.out.println("\n" + "*".repeat(10) + " Buscando el partido con id 7... " + "*".repeat(10) + "\n");
        int idPartido = 7;
        Partido p = partidos.getPartido(idPartido);
        if (p != null) {
            System.out.println (p);
        } else {
            System.out.println ("No se encontró el partido...");
        }
        
        // Cargar y listar pronosticos.
        System.out.println("\n" + "*".repeat(10) + " Pronosticos del participante con id 7... " + "*".repeat(10) + "\n");
        pronosticos.setNombreDeArchivo("pronosticos.csv");
        pronosticos.cargarDeArchivo(7, equipos, partidos);
        System.out.println("Los pronósticos del participante 7 son: \n" + pronosticos.listar());
        
        // PRUEBA: Creamos participantes y cargamos un pronostico
        /*
        if ((pronosticos.getPronosticos()).isEmpty()){
            System.out.println("Pronosticos es una lista vacia.");
        } else {
            System.out.println("Pronosticos no es una lista vacía.");
        }
        
        Participante participante = new Participante(7, "Joaquin", pronosticos, 0);
        */
        /*
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
        */
        
        participantes.setNombreDeArchivo("participantes.csv");
        participantes.cargarDeArchivo();
        System.out.println("\n" + "*".repeat(10) + " Participantes " + "*".repeat(10) + "\n");
        System.out.println(participantes.listar());
        
        // IMPORTANTE: Una vez cargados los participantes, para cada uno de ellos
        // se deben cargar sus pronósticos !!!
        // Y luego, calcular sus puntajes !!!
        
        for(Participante pte : participantes.getParticipantes()) {
            pte.cargarPronosticos(equipos, partidos);
        }
        
        System.out.println("\n" + "*".repeat(10) + " Participantes y sus puntajes " + "*".repeat(10) + "\n");
        participantes.calcularPuntajes();
        
        System.out.println("\n" + "*".repeat(10) + " Participantes y sus datos completos " + "*".repeat(10) + "\n");
        System.out.println("Los participantes son:\n" + participantes.listar());
    }    
}
