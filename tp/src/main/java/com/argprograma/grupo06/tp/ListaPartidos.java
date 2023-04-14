/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mye
 */
public class ListaPartidos extends ArrayList {
     private List<Partido> partidos;
     private String nombreArchivo;
    
    // CONSTRUCTORES

    public ListaPartidos(List<Partido> partidos, String nombreArchivo) {
        super();
        this.partidos = partidos;
        this.nombreArchivo = nombreArchivo;
    }

    public ListaPartidos() {
        super();
        this.partidos = new ArrayList<>();
        this.nombreArchivo = "";
    }
    
    // GETTERS AND SETTERS
    
    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    // MÉTODOS
    // Arma un String con el listado de partidos levantados de archivo.
    public String listar() {
        String lista = "";
        if (this.partidos != null){
            Iterator<Partido> it = this.partidos.iterator();
            while (it.hasNext()){
                Partido partido = it.next();
                lista += (partido + "\n");
            }
        }
        return lista;
    }
    
        public Partido getPartido(int idPartido) {
        Partido partido = null;
        if (this.partidos != null){
            for(Partido p : this.partidos) {
                if(p.getIdPartido() == idPartido){
                    partido = p;
                    break;
                }
            }
        }
        return partido;
    }
    
    void cargarDeArchivo(ListaEquipos equipos){
        String infoDelPartido = "";
        String vectorPartido[];
        Scanner sc = null;
        Partido auxPartido = null;
        boolean todoOk = true;
        if (this.partidos == null) {
            partidos = new ArrayList<Partido>();
        } else {
            // La lista de equipos no está vacía, es decir que estoy llamando
            // al metodo cargarDeArchivo() por segunda vez. Lo que hago en 
            // este caso es destruir la lista y crear una nueva. De este
            // modo, evito tener el atributo idEquipo duplicado, lo cual 
            // sería un problema en el futuro.
            while (!partidos.isEmpty()) {
                partidos.remove(0);
            }
        }
        
        try {
            sc = new Scanner(new File(this.nombreArchivo));
            sc.useDelimiter("\\r\\n|\\r|\\n"); // Leerá por renglones.
            int linea = 1;
            while (sc.hasNext() && todoOk){
                infoDelPartido = sc.next();
                if (linea == 1) {
                    linea++;
                    continue; // Salteo la primer línea (encabezado).
                } else {
                    linea++;
                }
                // System.out.println("Línea leída del archivo: " + infoDelPartido);
                vectorPartido = infoDelPartido.split(",");
                // System.out.print("idPartido: " + vectorPartido[0]);
                // System.out.print(", idEquipo1: " + vectorPartido[1]);
                // System.out.print(", idEquipo2: " + vectorPartido[2]);
                // System.out.print(", golesEquipo1: " + vectorPartido[3]);
                // System.out.println(", golesEquipo2: " + vectorPartido[4] + ".");

                // Creo un nuevo objeto Equipo a partir del dato reción leído.
                // Al mismo tiempo, verifico que vectorPartido[0..4] sean realmente enteros,
                // caso contrario, arrojará una excepción y el catch la atrapará.
                // También debo verificar que el split divida en tres partes y que el id sea no negativo.
                if (vectorPartido.length == 5) {
                    int auxIdPartido = Integer.parseInt(vectorPartido[0]);
                    int auxIdEquipo1 = Integer.parseInt(vectorPartido[1]);
                    int auxIdEquipo2 = Integer.parseInt(vectorPartido[2]);
                    int auxGolesEquipo1 = Integer.parseInt(vectorPartido[3]);
                    int auxGolesEquipo2 = Integer.parseInt(vectorPartido[4]); 
                    if ((auxIdPartido > 0)&&(auxIdEquipo1 > 0)&&(auxIdEquipo2 > 0)&&(auxGolesEquipo1 >= 0)&&(auxGolesEquipo2 >= 0)){
                        Equipo auxEquipo1 = equipos.getEquipo(auxIdEquipo1);
                        Equipo auxEquipo2 = equipos.getEquipo(auxIdEquipo2);
                        if (auxEquipo1!=null && auxEquipo2!=null) {
                            auxPartido = new Partido(auxIdPartido, auxEquipo1, auxEquipo2, auxGolesEquipo1, auxGolesEquipo2);
                        } else {
                            todoOk = false;
                        }
                    }
                } else {
                    todoOk = false;
                }
                
                // Agrego el objeto recién creado a al atributo que contiene el ArrayList de Equipos.
                if (todoOk) partidos.add(auxPartido);
                linea++;
            }
        } catch (IOException | NumberFormatException e1){
            System.out.println("Exception - Mensaje: " + e1.getMessage());
        } finally {
            try {
                // Si apareció en el archivo alguna línea fuera de formato,
                // entonces vacío la lista de equipos.
                if (!todoOk) {
                    while (!partidos.isEmpty()) {
                    partidos.remove(0);
                    }
                    partidos = null;
                }
                // Si no se cargó ningún equipo en la lista o la vacié en el 
                // paso anterior, entonces la pongo en null.
                if (partidos.isEmpty()) {
                    partidos = null;
                }
                // Si llegó a abrir el Scanner con éxito, aquí lo cierro.
                if (sc != null){
                    sc.close();
                }
            } catch  (Exception e3) {
                System.out.println("Exception Mensaje: " + e3.getMessage());
            }
        }
        // System.out.println("Fin de archivo.");
    }
    void cargarDeDB(ListaEquipos equipos){
        Partido auxPartido = null;
        boolean todoOk = true;
        if (this.partidos == null) {
            partidos = new ArrayList<Partido>();
        } else {
              while (!partidos.isEmpty()) {
                partidos.remove(0);
            }
        }
        
        Connection conn = null;
        
        try {
            //
            // Establecer una conexión
            conn = DriverManager.getConnection("jdbc:sqlite:pronosticos.db");
            // Crear el "statement" para enviar comandos
            Statement stmt = conn.createStatement();
            // Crear el "statement" para enviar comandos
            
            String sql = "SELECT "
            + "idPartido, idEquipo1, idEquipo2, golesEquipo1, golesEquipo2 "
            + "FROM partidos " ;
            ResultSet rs = stmt.executeQuery(sql); // Ejecutar la consulta y obtener el ResultSet
            while (rs.next()){
                int auxIdPartido = (rs.getInt("idPartido"));
                int auxIdEquipo1 = (rs.getInt("idEquipo1"));
                int auxIdEquipo2 = (rs.getInt("idEquipo2"));
                int auxGolesEquipo1 = (rs.getInt("golesEquipo1"));
                int auxGolesEquipo2 = (rs.getInt("golesEquipo2"));
                Equipo auxEquipo1 = equipos.getEquipo(auxIdEquipo1);
                Equipo auxEquipo2 = equipos.getEquipo(auxIdEquipo2);
                auxPartido = new Partido(auxIdPartido, auxEquipo1, auxEquipo2, auxGolesEquipo1, auxGolesEquipo2);       
                // Agrego el objeto recién creado a al atributo que contiene el ArrayList de Equipos.
                partidos.add(auxPartido);
    
            }
        } catch (SQLException e1) {
            System.out.println("SQLException - Mensaje E1: " + e1.getMessage());
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
        // System.out.println("Fin de archivo.");
    }  
}
