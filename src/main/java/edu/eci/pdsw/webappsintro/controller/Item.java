/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.webappsintro.controller;

import edu.eci.pdsw.stubs.servicesfacadestub.Producto;

/**
 *
 * @author andres
 */
public class Item {
    static Item item;
    int cantidad;
    String nombre;
    double precio;
    public Item(String nombre,double precio,int cantidad){
        this.cantidad=cantidad;
        this.precio=cantidad*precio;
        this.nombre=nombre;
    }
    public int getCantidad(){
        return cantidad;
    }
    public void setCantidad(int ncant){
        cantidad=ncant;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nname){
        nombre=nname;
    }
    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double nprecio){
        precio=nprecio;
    }
    
}
