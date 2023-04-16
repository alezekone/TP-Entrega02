package com.argprograma.grupo06.tp;

import java.util.*;

public class Participante implements Comparable<Participante> {
    
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
            if(pronosticos.getPronosticos()==null) {
                cantPronosticos = 0;
            } else { // Toqúé por acá...
                cantPronosticos = (pronosticos.getPronosticos()).size();                
            }
        }
        if (cantPronosticos == 0) {
            aux = "[]";
            return "Participante = " + idParticipante +  ",       Nombre =" + nombre;
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
        return "Participante = " + idParticipante +  ",  Nombre =" + nombre + ", pronosticos=" + aux + ", puntaje=" + puntaje + "}";
    }
    
    void cargarPronosticos(ListaEquipos equipos, ListaPartidos partidos) {
        ListaPronosticos lp = new ListaPronosticos();
        /*
        if (equipos==null){
            System.out.println("Equipos es null.");
        } else {
            System.out.println("Equipos no es null.");
        }
        */
        lp.setNombreDeArchivo("pronosticos.csv");
        lp.cargarDeArchivo(this.idParticipante, equipos, partidos);
        this.setPronosticos(lp);

    }
    @Override
    public int compareTo(Participante p) {
        // devuelve -1, si es menor, 0 si es igual, 1 si es mayor
        int miPuntaje = this.getPuntaje();
        int otroPuntaje = p.getPuntaje();
        if (miPuntaje == otroPuntaje)
            return 0;
        else if (miPuntaje > otroPuntaje)
            return 1;
        else 
            return -1;
    }

}
