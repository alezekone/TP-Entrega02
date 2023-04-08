/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package tp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author remo
 */
public class EquipoTest {
    
    public EquipoTest() {
    }

    /**
     * Test of getIdEquipo method, of class Equipo.
     */
    @Test
    public void testGetIdEquipo() {
        System.out.println("getIdEquipo");
        Equipo instance = new Equipo();
        int expResult = 0;
        int result = instance.getIdEquipo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        }

    /**
     * Test of setIdEquipo method, of class Equipo.
     */
    @Test
    public void testSetIdEquipo() {
        System.out.println("setIdEquipo");
        int idEquipo = 0;
        Equipo instance = new Equipo();
        instance.setIdEquipo(idEquipo);
        // TODO review the generated test code and remove the default call to fail.
        }

    /**
     * Test of getNombre method, of class Equipo.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Equipo instance = new Equipo();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
             
    }

    /**
     * Test of setNombre method, of class Equipo.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Equipo instance = new Equipo();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        }

    /**
     * Test of getDescripcion method, of class Equipo.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Equipo instance = new Equipo();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        }

    /**
     * Test of setDescripcion method, of class Equipo.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String descripcion = "";
        Equipo instance = new Equipo();
        instance.setDescripcion(descripcion);
        // TODO review the generated test code and remove the default call to fail.
        }

    /**
     * Test of toString method, of class Equipo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Equipo instance = new Equipo();
        String expResult = "Equipo{idEquipo=0, nombre=, descripcion=}";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //<Equipo{idEquipo=[[0, nombre=, descripcion=]]}>
        //<Equipo{idEquipo=[0, nombre=, descripcion=]}>      
    }
    
}
