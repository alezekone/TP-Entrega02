package tp;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class Participante {
    
    private int idParticipante;
    private String nombre;
    private ListaPronosticos pronosticos;
    private int puntaje;

    // Constructors...
    public Participante(int idParticipante, String nombre, ListaPronosticos pronosticos, int puntaje) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.pronosticos = pronosticos;
        this.puntaje = puntaje;
    }

    public Participante() {
        this.idParticipante = 0;
        this.nombre = "";
        this.pronosticos = null;
        this.puntaje = 0;
    }

    // Setters and Getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaPronosticos getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(ListaPronosticos pronosticos) {
        this.pronosticos = pronosticos;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    
 /*   
    public void addPronostico(Pronostico pronostico) {
        if (this.pronosticos == null) {
            this.pronosticos = new ListaPronosticos();
            this.pronosticos.add(pronostico);
        } else {
            this.pronosticos.add(pronostico);
        }
    }
*/
    // toString()
    @Override
    public String toString() {
        String aux;
        int cantPronosticos;
        if (pronosticos==null) {
            cantPronosticos = 0;
        } else {
            cantPronosticos = (pronosticos.getPronosticos()).size();
        }
        if (cantPronosticos == 0) {
            aux = "[]";
        } else {
            aux = "[";
            for (int i = 0; i < cantPronosticos; i++) {
                aux = aux + pronosticos.getPronosticos().get(i); // El .toString() entra solo.
                if (i < (cantPronosticos - 1)) {
                    aux = aux + ", ";
                }
            }
            aux = aux + "]";
        }
        return "Participante{" + "idParticipante=" + idParticipante +  ", nombre=" + nombre + ", pronosticos=" + aux + ", puntaje=" + puntaje + "}";
    }
    
    void cargarPronosticos(ListaEquipos equipos, ListaPartidos partidos) {
        ListaPronosticos lp = new ListaPronosticos();
        if (equipos==null){
            System.out.println("Equipos es null.");
        } else {
            System.out.println("Equipos no es null.");
        }
        lp.setNombreDeArchivo("pronosticos.csv");
        System.out.println(lp.getNombreDeArchivo());
        lp.cargarDeArchivo(this.idParticipante, equipos, partidos);
        // this.setPronosticos(lp);
        // nancysilva840@gmail.com
        // guillesuarez68@gmail.com
    }

}
