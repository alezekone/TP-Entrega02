/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// Solo en al momento de instanciar la lista (al hacer el new()) se usa ArrayList !!!

public class ListaEquipos extends ArrayList {
    private List<Equipo> equipos;
    private String nombreArchivo;
    
    // CONSTRUCTORES

    public ListaEquipos(List<Equipo> equipos, String nombreArchivo) {
        super();
        this.equipos = equipos;
        this.nombreArchivo = nombreArchivo;
    }

    public ListaEquipos() {
        super();
        this.equipos = new ArrayList<Equipo>();
        this.nombreArchivo = "";
    }
    
    // GETTERS AND SETTERS

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    // MÉTODOS
    
    public Equipo getEquipo(int idEquipo) {
        Equipo equipo = null;
        if (this.equipos != null){
            for(Equipo eq : this.equipos) {
                if(eq.getIdEquipo() == idEquipo){
                    equipo = eq;
                    break;
                }
            }
        }
        return equipo;
    }
            
    // Metodo para devolver un string con 
    // la lista de todos los equipos.
    public String listar() {
        String lista = "";
        if (this.equipos != null){
            for (Equipo equipo : this.equipos) {
                // System.out.println(equipo);
                lista += (equipo + "\n");
            }
        }
        return lista;
    }
    
    void cargarDeArchivo(){
        String infoDelEquipo = "";
        String vectorEquipo[];
        Scanner sc = null;
        Equipo auxEquipo = null;
        boolean todoOk = true;
        if (this.equipos == null) {
            equipos = new ArrayList<Equipo>();
        } else {
            // La lista de equipos no está vacía, es decir que estoy llamando
            // al metodo cargarDeArchivo() por segunda vez. Lo que hago en 
            // este caso es destruir la lista y crear una nueva. De este
            // modo, evito tener el atributo idEquipo duplicado, lo cual 
            // sería un problema en el futuro.
            while (!equipos.isEmpty()) {
                equipos.remove(0);
            }
        }
        
        try {
            sc = new Scanner(new File(this.nombreArchivo));
            sc.useDelimiter("\\r\\n|\\r|\\n"); // Leerá por renglones.
            int linea = 1;
            while (sc.hasNext() && todoOk){
                infoDelEquipo = sc.next();
                if (linea == 1) {
                    linea++;
                    continue; // Salteo la primer línea (encabezado).
                } else {
                    linea++;
                }
                // System.out.println("Línea leída del archivo: " + infoDelEquipo);
                vectorEquipo = infoDelEquipo.split(",");
                // System.out.println("idEquipo: " + vectorEquipo[0] + ", Equipo: " + vectorEquipo[1] + ", Descripcion: " + vectorEquipo[2] + ".");
                // Creo un nuevo objeto Equipo a partir del dato reción leído.
                // Al mismo tiempo, verifico que vectorEquipo[0] sea realmente un entero,
                // caso contrario, arrojará una excepción y el catch la atrapará.
                // También debo verificar que el split divida en tres partes y que el id sea no negativo.
                if ((Integer.parseInt(vectorEquipo[0]) >= 0) && (vectorEquipo.length == 3)) {
                    auxEquipo = new Equipo(Integer.parseInt(vectorEquipo[0]),vectorEquipo[1], vectorEquipo[2]);
                } else {
                    todoOk = false;
                }
                
                // Agrego el objeto recién creado a al atributo que contiene el ArrayList de Equipos.
                equipos.add(auxEquipo);
                linea++;
            }
        } catch (IOException e1) {
            System.out.println("IOException - Mensaje E1: " + e1.getMessage());
        } catch (NumberFormatException e2){
            System.out.println("NumberFormatException - Mensaje E2: " + e2.getMessage());
        } finally {
            try {
                // Si apareció en el archivo alguna línea fuera de formato,
                // entonces vacío la lista de equipos.
                if (!todoOk) {
                    while (!equipos.isEmpty()) {
                    equipos.remove(0);
                    }
                    equipos = null;
                }
                // Si no se cargó ningún equipo en la lista o la vacié en el 
                // paso anterior, entonces la pongo en null.
                if (equipos.isEmpty()) {
                    equipos = null;
                }
                // Si llegó a abrir el Scanner con éxito, aquí lo cierro.
                if (sc != null){
                    sc.close();
                }
            } catch  (Exception e3) {
                System.out.println("Exception - Mensaje E3: " + e3.getMessage());
            }
        }
        // System.out.println("Fin de archivo.");
    }
    
    void cargarDeDB(){
        Equipo auxEquipo = null;
        
        Connection conn = null;
        
        try {
            //
            // Establecer una conexión
            conn = DriverManager.getConnection("jdbc:sqlite:pronosticos.db");
            // Crear el "statement" para enviar comandos
            Statement stmt = conn.createStatement();
            // Crear el "statement" para enviar comandos
            
            String sql = "SELECT "
            + "idEquipo, Nombre, Descripcion "
            + "FROM equipos " ;
            ResultSet rs = stmt.executeQuery(sql); // Ejecutar la consulta y obtener el ResultSet
            while (rs.next()){
                auxEquipo = new Equipo();
                auxEquipo.setIdEquipo(rs.getInt("idEquipo"));
                auxEquipo.setNombre(rs.getString("Nombre"));
                auxEquipo.setDescripcion(rs.getString("Descripcion"));        
                // Agrego el objeto recién creado a al atributo que contiene el ArrayList de Equipos.
                equipos.add(auxEquipo);
    
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
