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
public class ListaPronosticosTest {
    
    public ListaPronosticosTest() {
    }


    /**
     * Test of setPronosticos method, of class ListaPronosticos.
     */
    @Test
    public void testSetPronosticos() {
        System.out.println("setPronosticos");
        List<Pronostico> pronosticos = null;
        ListaPronosticos instance = new ListaPronosticos();
        instance.setPronosticos(pronosticos);
       }

    /**
     * Test of getNombreDeArchivo method, of class ListaPronosticos.
     */
    @Test
    public void testGetNombreDeArchivo() {
        System.out.println("getNombreDeArchivo");
        ListaPronosticos instance = new ListaPronosticos();
        String expResult = "";
        String result = instance.getNombreDeArchivo();
        assertEquals(expResult, result);
       }

    /**
     * Test of setNombreDeArchivo method, of class ListaPronosticos.
     */
    @Test
    public void testSetNombreDeArchivo() {
        System.out.println("setNombreDeArchivo");
        String nombreDeArchivo = "";
        ListaPronosticos instance = new ListaPronosticos();
        instance.setNombreDeArchivo(nombreDeArchivo);
       }

    /**
     * Test of listar method, of class ListaPronosticos.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        ListaPronosticos instance = new ListaPronosticos();
        String expResult = "";
        String result = instance.listar();
        assertEquals(expResult, result);
     }

    /**
     * Test of cargarDeArchivo method, of class ListaPronosticos.
     */
    @Test
    public void testCargarDeArchivo() {
        System.out.println("cargarDeArchivo");
        int idParticipante = 0;
        ListaEquipos equipos = null;
        ListaPartidos partidos = null;
        ListaPronosticos instance = new ListaPronosticos();
        instance.cargarDeArchivo(idParticipante, equipos, partidos);
     }
    
}
