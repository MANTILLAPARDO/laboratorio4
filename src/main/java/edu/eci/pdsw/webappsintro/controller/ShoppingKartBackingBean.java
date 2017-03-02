/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.webappsintro.controller;

import edu.eci.pdsw.stubs.servicesfacadestub.CurrencyServices;
import edu.eci.pdsw.stubs.servicesfacadestub.Producto;
import edu.eci.pdsw.stubs.servicesfacadestub.ProductsServices;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import edu.eci.pdsw.webappsintro.controller.Item;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author hcadavid
 */
 @ManagedBean(name="estadocarrito")
 @SessionScoped
public class ShoppingKartBackingBean {
    String moneda="DOLARES";
    List<Item> items=new ArrayList<Item>();
    List<Item> itemTemp=new ArrayList<Item>();
    List<Producto> productos;
    HashMap<Producto,Integer> cantidades=new HashMap<Producto,Integer>();
    double compra=0;
    String select="Seleccionar";
    boolean us=true;
    public String getMoneda(){
        return moneda;
    }
    public void setMoneda(String moneda){
        this.moneda=moneda;
    }
    public HashMap<Producto,Integer> getCantidades(){
        return cantidades;
    }
    
    public void setCantidades(HashMap<Producto,Integer> cantid){
        cantidades=cantid;
    }
    public String getSelect(){
        return select;
    }
    
    public void setSelect(String sel){
        select=sel;
    }
    public double getCompra(){
        return compra;
    }
    public List<Item>  getItems(){
        return items;
    }
    public void setItems(String nombre,double precio,int cantidad){
        compra=precio*cantidad+compra;
        items.add(new Item(nombre,precio,cantidad));
    }
    public List<Producto> getProductos(){
        productos=ProductsServices.getInstance().getProductos();
        for(Producto i:productos)cantidades.put(i,1);
        return productos;
    }
    public void setProductos(Producto p){
        ProductsServices.getInstance().registrarProducto(p);
    }
    
    public double getTasaCambioDolar(){
        return CurrencyServices.getInstance().getUSDExchangeRateInCOP();
    }
 
    
    //public List<Boolean>  getSeleccion(){
    //    return seleccion;
    //}
    //public void setSeleccion(Producto p){
        
    //}
    public void seleccionar(Producto producto,int cantidad){
            boolean resp=false;
            int index=-1;
            for(int i=0;i<itemTemp.size()&&!resp;i++){
                if(producto.getNombre()==itemTemp.get(i).getNombre()){
                    index=i;
                    resp=true;
                }
            }
            if(resp){
                itemTemp.remove(index);
                select="Seleccionar";
            }
            else{
                if(producto!=null&&cantidad!=0){
                Item nitem=new Item(producto.getNombre(),producto.getPrecioEnUSD(),cantidad);
                itemTemp.add(nitem);
                select="Remover";
                }else{
                    select="Seleccionar";
                }
                
            }
    }
    public void cantSelect(Producto producto,int cant){
        
    }
    public void actualiceCarrito(){
        us=false;
        actualicePrecio();
        for(Item i:itemTemp){
            items.add(i);
        }
        itemTemp.clear();
        compra=0;
       for (Item i :items) { 
         compra=i.precio+compra; 
       }
       select="Seleccionar";
    }
    public void actualicePrecio(){
        if(us){
            compra=compra*getTasaCambioDolar();
            us=false;
            moneda="PESOS";
        }
        else {
            compra=compra/getTasaCambioDolar();
            us=true;
            moneda="DOLARES";
        }
    }
    
}
