/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package tp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nancy
 */
public class PartidoTest {
    
    public PartidoTest() {
    }

    /**
     * Test of getIdPartido method, of class Partido.
     */
    @Test
    public void testGetIdPartido() {
        System.out.println("getIdPartido");
        Partido instance = new Partido();
        int expResult = 0;
        int result = instance.getIdPartido();
        assertEquals(expResult, result);
        
       
    }

    /**
     * Test of setIdPartido method, of class Partido.
     */
    @Test
    public void testSetIdPartido() {
        System.out.println("setIdPartido");
        int idPartido = 0;
        Partido instance = new Partido();
        instance.setIdPartido(idPartido);
       
    }

    /**
     * Test of getEquipo1 method, of class Partido.
     */
    @Test
    public void testGetEquipo1() {
        System.out.println("getEquipo1");
        Partido instance = new Partido();
        Equipo expResult = null;
        Equipo result = instance.getEquipo1();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setEquipo1 method, of class Partido.
     */
    @Test
    public void testSetEquipo1() {
        System.out.println("setEquipo1");
        Equipo equipo1 = null;
        Partido instance = new Partido();
        instance.setEquipo1(equipo1);
        
    }

    /**
     * Test of getEquipo2 method, of class Partido.
     */
    @Test
    public void testGetEquipo2() {
        System.out.println("getEquipo2");
        Partido instance = new Partido();
        Equipo expResult = null;
        Equipo result = instance.getEquipo2();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setEquipo2 method, of class Partido.
     */
    @Test
    public void testSetEquipo2() {
        System.out.println("setEquipo2");
        Equipo equipo2 = null;
        Partido instance = new Partido();
        instance.setEquipo2(equipo2);
       
    }

    /**
     * Test of getGolesEquipo1 method, of class Partido.
     */
    @Test
    public void testGetGolesEquipo1() {
        System.out.println("getGolesEquipo1");
        Partido instance = new Partido();
        int expResult = 0;
        int result = instance.getGolesEquipo1();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setGolesEquipo1 method, of class Partido.
     */
    @Test
    public void testSetGolesEquipo1() {
        System.out.println("setGolesEquipo1");
        int golesEquipo1 = 0;
        Partido instance = new Partido();
        instance.setGolesEquipo1(golesEquipo1);
       
    }

    /**
     * Test of getGolesEquipo2 method, of class Partido.
     */
    @Test
    public void testGetGolesEquipo2() {
        System.out.println("getGolesEquipo2");
        Partido instance = new Partido();
        int expResult = 0;
        int result = instance.getGolesEquipo2();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setGolesEquipo2 method, of class Partido.
     */
    @Test
    public void testSetGolesEquipo2() {
        System.out.println("setGolesEquipo2");
        int golesEquipo2 = 0;
        Partido instance = new Partido();
        instance.setGolesEquipo2(golesEquipo2);
        
    }

    /**
     * Test of toString method, of class Partido.
     
    @Test
    public void testToString() {
        System.out.println("toString");
        Partido instance = new Partido();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    */
    /**
     * Test of resultado method, of class Partido.
     */
    @Test
    public void testResultado() {
        System.out.println("resultado");
        Equipo equipo = null;
        Partido instance = new Partido();
        char expResult = 'E';
        char result = instance.resultado(equipo);
        assertEquals(expResult, result);
        
    }
    
}
