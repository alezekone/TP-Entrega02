/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.argprograma.grupo06.tp;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author remo
 */
public class ListaParticipantesTest {
    
    public ListaParticipantesTest() {
    }



    /**
     * Test of getNombreDeArchivo method, of class ListaParticipantes.
     */
    @Test
    public void testGetNombreDeArchivo() {
        System.out.println("getNombreDeArchivo");
        ListaParticipantes instance = new ListaParticipantes();
        String expResult = "";
        String result = instance.getNombreDeArchivo();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNombreDeArchivo method, of class ListaParticipantes.
     */
    @Test
    public void testSetNombreDeArchivo() {
        System.out.println("setNombreDeArchivo");
        String nombreDeArchivo = "";
        ListaParticipantes instance = new ListaParticipantes();
        instance.setNombreDeArchivo(nombreDeArchivo);

    }

    /**
     * Test of addParticipante method, of class ListaParticipantes.
     */
    @Test
    public void testAddParticipante() {
        System.out.println("addParticipante");
        Participante p = null;
        ListaParticipantes instance = new ListaParticipantes();
        instance.addParticipante(p);

    }

    /**
     * Test of getParticipante method, of class ListaParticipantes.
     */
    @Test
    public void testGetParticipante() {
        System.out.println("getParticipante");
        int idParticipante = 0;
        ListaParticipantes instance = new ListaParticipantes();
        Participante expResult = null;
        Participante result = instance.getParticipante(idParticipante);
        assertEquals(expResult, result);

    }

    /**

    /**
     * Test of cargarDeArchivo method, of class ListaParticipantes.
     */
    @Test
    public void testCargarDeArchivo() {
        System.out.println("cargarDeArchivo");
        ListaParticipantes instance = new ListaParticipantes();
        instance.cargarDeArchivo();

    }

    /**
     * Test of calcularPuntajes method, of class ListaParticipantes.
     */
    @Test
    public void testCalcularPuntajes() {
        System.out.println("calcularPuntajes");
        ListaParticipantes instance = new ListaParticipantes();
        instance.calcularPuntajes();

    }

    /**
     * Test of cargarDeDB method, of class ListaParticipantes.
     */
    @Test
    public void testCargarDeDB() {
        System.out.println("cargarDeDB");
        ListaParticipantes instance = new ListaParticipantes();
        instance.cargarDeDB();

    }



 
}
