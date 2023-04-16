/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.argprograma.grupo06.tp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author remo
 */
public class ParticipanteTest {
    
    public ParticipanteTest() {
    }

    /**
     * Test of getNombre method, of class Participante.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Participante instance = new Participante();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setNombre method, of class Participante.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Participante instance = new Participante();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getPronosticos method, of class Participante.
     */
    @Test
    public void testGetPronosticos() {
        System.out.println("getPronosticos");
        Participante instance = new Participante();
        ListaPronosticos expResult = null;
        ListaPronosticos result = instance.getPronosticos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setPronosticos method, of class Participante.
     */
    @Test
    public void testSetPronosticos() {
        System.out.println("setPronosticos");
        ListaPronosticos pronosticos = null;
        Participante instance = new Participante();
        instance.setPronosticos(pronosticos);
        // TODO review the generated test code and remove the default call to fail.
     
    }

    /**
     * Test of getIdParticipante method, of class Participante.
     */
    @Test
    public void testGetIdParticipante() {
        System.out.println("getIdParticipante");
        Participante instance = new Participante();
        int expResult = 0;
        int result = instance.getIdParticipante();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setIdParticipante method, of class Participante.
     */
    @Test
    public void testSetIdParticipante() {
        System.out.println("setIdParticipante");
        int idParticipante = 0;
        Participante instance = new Participante();
        instance.setIdParticipante(idParticipante);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getPuntaje method, of class Participante.
     */
    
    /**
     * Test of setPuntaje method, of class Participante.
     */
    @Test
    public void testSetPuntaje() {
        System.out.println("setPuntaje");
        int puntaje = 0;
        Participante instance = new Participante();
        instance.setPuntaje(puntaje);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of toString method, of class Participante.
     */
   

    /**
     * Test of cargarPronosticos method, of class Participante.
     */
    @Test
    public void testCargarPronosticos() {
        System.out.println("cargarPronosticos");
        ListaEquipos equipos = null;
        ListaPartidos partidos = null;
        Participante instance = new Participante();
        instance.cargarPronosticos(equipos, partidos);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
   
    
}
