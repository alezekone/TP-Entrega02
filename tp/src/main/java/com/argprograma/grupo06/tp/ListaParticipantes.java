/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argprograma.grupo06.tp;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.asciitable.AT_Cell;


public class ListaParticipantes extends ArrayList {
    public List<Participante> participantes;
    private String nombreDeArchivo;
    
    // CONSTRUCTORS

    public ListaParticipantes() {
        super();
        this.participantes = new ArrayList<Participante>();
        this.nombreDeArchivo = "";
    }

    public ListaParticipantes(List<Participante> participantes, String nombreDeArchivo) {
        super();
        this.participantes = participantes;
        this.nombreDeArchivo = nombreDeArchivo;
    }
    
    // SETTERS AND GETTERS

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public String getNombreDeArchivo() {
        return nombreDeArchivo;
    }

    public void setNombreDeArchivo(String nombreDeArchivo) {
        this.nombreDeArchivo = nombreDeArchivo;
    }
    
    // OTROS METODOS
    public void addParticipante(Participante p) {
        if (participantes == null) {
            participantes = new ArrayList<Participante>();
        }
        this.participantes.add(p);
    }
    
    /**
     * Devuelve un Participante (o null) buscandolo por idParticipante
     * @param idParticipante Identificador del equipo deseado
     * @return Objeto Participante (o null si no se encuentra)
     */
    public Participante getParticipante (int idParticipante) {
        Participante encontrado = null;
        for (Participante p : this.getParticipantes()) {
            if (p.getIdParticipante() == idParticipante) {
                encontrado = p;
                // Lo encontré => abandono el ciclo for.
                break;
            }
        }
        // Devuelvo el objeto encontrado o null.
        return encontrado;
    }
    
    @Override
    public String toString() {
        return "ListaParticipantes{" + "participantes=" + participantes + '}';
    }

    public String listar() {
        String lista = "";
        for (Participante participante: participantes) {
            lista += (participante + "\n" + "\n");
        }  
        return lista;
    }
    
        // cargar desde el archivo
    public void cargarDeArchivo() {
        // para las lineas del archivo csv
        String datosParticipante;
        // para los datos individuales de cada linea
        String vectorParticipante[];
        // para el objeto en memoria
        Participante participante;
        int fila = 0;
       
        try { 
            Scanner sc = new Scanner(new File(this.getNombreDeArchivo()));
            sc.useDelimiter("\\r\\n|\\r|\\n"); 
                
            while (sc.hasNext()) {
                datosParticipante = sc.next();
                // System.out.println(datosParticipante);
                fila ++;
                // La primer fila del archivo es un encabezado.
                if (fila == 1)
                    continue;              
                vectorParticipante = datosParticipante.split(",");   
                int idParticipante = Integer.parseInt(vectorParticipante[0]);
                String nombre = vectorParticipante[1];
                participante = new Participante(idParticipante, nombre, null, 0);
                this.addParticipante(participante);
            }
            //closes the scanner
        } catch (IOException ex) {
                System.out.println("Mensaje: " + ex.getMessage());
        }       
    }
    
    public void calcularPuntajes() {
        int puntaje = 0;
        int cantPronosticos = 0;
        int cantAciertos = 0;
        for (Participante pte : this.getParticipantes()) {
            puntaje = 0;
            cantPronosticos = 0;
            cantAciertos = 0;
            ListaPronosticos pronosticos = new ListaPronosticos();
            pronosticos = pte.getPronosticos();
            if (pronosticos != null && pronosticos.getPronosticos()!=null) {
                for (Pronostico pco: pronosticos.getPronosticos()){
                    puntaje += pco.puntos();
                    if(pco.puntos()>0) cantAciertos++;
                    cantPronosticos++;
                }               
            } else {
                // El participante no realizó ningún pronóstico,
                // entonces su puntaje es 0 -el valor que ya
                // le había asignado el constructor. Entonces,
                // este "else" no hace falta, pero lo pongo por
                // claridad.
                puntaje = 0;  
            }
            pte.setPuntaje(puntaje);
            System.out.println("El participante " + pte.getNombre() + " -con " + cantAciertos + " aciertos en " + cantPronosticos + " pronosticos- tiene " + puntaje + " puntos.");
        }
    }
     void cargarDeDB(){
        Participante auxParticipante = null;
        
        Connection conn = null;
        
        try {
            //
            // Establecer una conexión
            conn = DriverManager.getConnection("jdbc:sqlite:pronosticos.db");
            // Crear el "statement" para enviar comandos
            Statement stmt = conn.createStatement();
            // Crear el "statement" para enviar comandos
            
            String sql = "SELECT "
            + "*"
            + "FROM participantes " ;
            ResultSet rs = stmt.executeQuery(sql); // Ejecutar la consulta y obtener el ResultSet
            while (rs.next()){
                auxParticipante = new Participante();
                auxParticipante.setIdParticipante(rs.getInt("idParticipante"));
                auxParticipante.setNombre(rs.getString("Nombre"));
                // Agrego el objeto recién creado a al atributo que contiene el ArrayList de Equipos.
                participantes.add(auxParticipante);
    
            }
        } catch (SQLException e1) {
            System.out.println("Class: " + this.getClass().getSimpleName() + " - SQLException - Mensaje E1: " + e1.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // conn close failed.
                System.out.println(e.getMessage());
            }
        }
       
    }
      
    
    public List<Participante> getOrdenadosPorPuntaje() {
        // genera una copia de la lista para no modificar el contenido original
        List<Participante> ordenados = new ArrayList <Participante>();
        ordenados.addAll( participantes);
        
        //Obtener la lista ordenada de maor a menor
        Collections.sort(ordenados,Collections.reverseOrder());
        return ordenados;
    }
    
    public String listaOrdenadaPorPuntaje(){
        List<Participante> ordenados = this.getOrdenadosPorPuntaje();
        AsciiTable at = new AsciiTable();
	/*at.addRule();
	at.addRow("row 1 col 1", "row 1 col 2");
	at.addRule();
	at.addRow("row 2 col 1", "row 2 col 2");
	at.addRule();

        */	
        String Lista = "";
        String ganador = "";
        int puesto =1;
        for(Participante participante: ordenados){
            //System.out.println("\n" + "El participante " + participante.getNombre() + " -con " + participante.getPuntaje() + " aciertos. " );
            Lista += "\n" + "En el " + puesto + "° puesto el participante " + participante.getNombre() + " con " + participante.getPuntaje() + " aciertos. " ;
            at.addRow("    "+participante.getNombre(),"   "+participante.getPuntaje());
            at.addRule();
            //at.setTextAlignment(TextAlignment.Center);
            
            if ( puesto == 1){
            ganador = "\n" + "\n" + "El participante ganador/a es " + participante.getNombre() + " con " + participante.getPuntaje() + " aciertos !!!";    
            }
                    puesto = puesto + 1 ;
                  
        }
        at.setTextAlignment(TextAlignment.CENTER);
	System.out.println(at.render(50));
        at.getContext().setWidth(50);
	System.out.println(at.render());

        Lista = Lista + ganador ;
        return Lista;
    }
    
    
   
}
