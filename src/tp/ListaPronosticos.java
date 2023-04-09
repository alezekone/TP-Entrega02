/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ACLARACIÓN IMPORTANTE:
// El archivo "pronosticos.csv" contiene todos los pronósticos,
// realizados por todos los participantes.
// El tema es que cada participante tiene su lista de pronóstics
// según modelo UML. Entonces, no existirá un objeto de clase 
// "ListaPronosticos" que contenga todos los pronósticos (es decir,
// con todo el contenido de del archivo pronosticos.csv).
// Lo que tendremos es a cada participante con su propia lista de
// pronósticos. Es decir que el archivo pronosticos.csv se leerá
// tantas veces como participantes haya, y cada participante solo
// tomará de él los pronósticos correspondientes a su propio id
// (es decir, participante.idParticipante == archivo.idParticipante).

public class ListaPronosticos extends ArrayList {

    private List<Pronostico> pronosticos;
    private String nombreDeArchivo;
    
    // CONSTRUCTORES

    public ListaPronosticos() {
        super();
        pronosticos = null;
        nombreDeArchivo = "";
    }

    public ListaPronosticos(String nombreDeArchivo) {
        super();
        pronosticos = null;
        this.nombreDeArchivo = nombreDeArchivo;
    }
    
    
    
    // SETTERS & GETTERS

    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(List<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }

    public String getNombreDeArchivo() {
        return nombreDeArchivo;
    }

    public void setNombreDeArchivo(String nombreDeArchivo) {
        this.nombreDeArchivo = nombreDeArchivo;
    }
    
    // Métodos del negocio
    
    public String listar() {
        String lista = "";
        if (this.pronosticos != null) {
            for (Pronostico p : pronosticos){
                lista += (p + "\n");
            }
        }
        return lista;
    }
    
    public void cargarDeArchivo(int idParticipante, ListaEquipos equipos, ListaPartidos partidos) {
        String infoDelPronostico = "";  // Contendrá cada línea leída del pronóstico (una por vez)
        String vectorPronostico[];      // Contendrá la línea anterior, separada en campos.
        String mensajeDeError = "";
        String charValidos = "gGeEpP";
        Scanner sc = null;
        Pronostico auxPronostico = null;
        boolean todoOk = true;
        int auxIdPronostico;
        int auxIdParticipante;
        int auxIdPartido;
        int auxIdEquipo;  // No es el id del Equipo, es su número de orden en el Partido (1 ó 2).
        char auxResultado;
        
        if (this.pronosticos == null) {
            pronosticos = new ArrayList<Pronostico>();
        } else {
            // La lista de equipos no está vacía, es decir que estoy llamando
            // al metodo cargarDeArchivo() por segunda vez. Lo que hago en 
            // este caso es destruir la lista y crear una nueva. De este
            // modo, evito tener el atributo idEquipo duplicado, lo cual 
            // sería un problema en el futuro.
            while (!pronosticos.isEmpty()) {
                pronosticos.remove(0);
            }
        }
        
        try {
            sc = new Scanner(new File(this.nombreDeArchivo));
            sc.useDelimiter("\\r\\n|\\r|\\n"); // Leerá por renglones.
            int linea = 1;
            while (sc.hasNext() && todoOk){
                infoDelPronostico = sc.next();
                if (linea == 1) {
                    linea++;
                    continue; // Salteo la primer línea (encabezado).
                } else {
                    linea++;
                }
                // System.out.println("Línea leída del archivo: " + infoDelPronostico);
                vectorPronostico = infoDelPronostico.split(",");

                // Verifico que la cantidad de campos de la línea sea 5.
                // En caso afirmativo, cargo los valores de los campos.
                if (vectorPronostico.length != 5) {
                    todoOk = false;
                    mensajeDeError = "Hay al menos una linea en el archivo con cant. de campos distinta de 5.\n";
                    mensajeDeError += "(Encabezado excluído)\n";
                } else {
                    try {
                        auxIdPronostico = Integer.parseInt(vectorPronostico[0]);
                        auxIdParticipante = Integer.parseInt(vectorPronostico[1]);
                        auxIdPartido = Integer.parseInt(vectorPronostico[2]);
                        auxIdEquipo = Integer.parseInt(vectorPronostico[3]);
                        auxResultado = (vectorPronostico[4]).charAt(1);
                        // Si hasta ahora vamos bien, verifico que los valores leídos sean válidos.
                        if((auxIdPronostico <= 0)||(auxIdParticipante <= 0)||(auxIdPartido <= 0)||(auxIdEquipo <= 0)||(auxIdEquipo > 2)||(charValidos.indexOf(auxResultado)< 0)){
                            todoOk = false;
                            // System.out.println("El caracter leído es " + Character.toString(auxResultado));
                            mensajeDeError = "Existe al menos un id negativo o un resultado invalido";
                        }
                        // Si hasta acá vamos bien, veo si el idParticipante de la 
                        // linea de archivo en análisis coincide con el id del
                        // Participante cuya lista de pronósticos estamos intentando
                        // levantar.
                        // En caso negativo, ignoro la línea.
                        // En caso afirmativo, analizo si existen un Partido y un 
                        // Equipo con los id suministrados.
                        if (todoOk) {
                                if(auxIdParticipante!=idParticipante){
                                    continue; // Salgo del while, esta línea no me interesa.
                                } else {
                                    Partido auxPartido = partidos.getPartido(auxIdPartido);
                                    Equipo auxEquipo = null;
                                    if (auxIdEquipo == 1) {
                                        auxEquipo = auxPartido.getEquipo1();
                                    } else {   // auxIdEquipo == 2
                                        auxEquipo = auxPartido.getEquipo2();
                                    }
                                    
                                    // Equipo auxEquipo = equipos.getEquipo(auxIdEquipo);
                                    
                                    // Verifico existencia de Partido y Equipo con esos ids.
                                    if (auxPartido!=null && auxEquipo!=null) { 
                                        auxPronostico = new Pronostico(auxIdPronostico, auxEquipo, auxPartido, Character.toUpperCase(auxResultado));
                                    } else {
                                        mensajeDeError = "No se puede armar la lista de pronosticos para este participante.\n";
                                        mensajeDeError += "Uno de los Equipos y/o Partidos referidos no existe.\n";
                                        todoOk = false;
                                    }
                                }
                        }                        
                    } catch (NumberFormatException e1){
                        todoOk = false;
                        mensajeDeError = "Alguno de los campos de id es no entero.";
                        // System.out.println("Exception - Mensaje: " + e1.getMessage());
                    }
                }

                // Si llegué hasta acá, la línea recién leída es de mi interés
                // y el objeto Pronostico fue correctamente creado. Entonces, 
                // lo agrego al atributo que contiene el ArrayList de Pronosticos.
                if (todoOk) {
                    pronosticos.add(auxPronostico);
                    linea++;
                } else {
                    System.out.println("Se produjo un error: ");
                    System.out.println(mensajeDeError);
                }
            }  // END DEL WHILE
        } catch (IOException e1){
            System.out.println("Exception - Mensaje: " + e1.getMessage());
        } finally {
            try {
                // Si apareció en el archivo alguna línea fuera de formato,
                // entonces vacío la lista de equipos.
                if (!todoOk) {
                    while (!pronosticos.isEmpty()) {
                    pronosticos.remove(0);
                    }
                    pronosticos = null;
                }
                // Si no se cargó ningún equipo en la lista o la vacié en el 
                // paso anterior, entonces la pongo en null.
                if (pronosticos.isEmpty()) {
                    pronosticos = null;
                }
                // Si llegó a abrir el Scanner con éxito, aquí lo cierro.
                if (sc != null){
                    sc.close();
                }
            } catch  (Exception e3) {
                System.out.println("Exception Mensaje: " + e3.getMessage());
            }
        }        
    }


    


    
}
