package cl.inacap.micarro.modelo;

import java.util.ArrayList;

public class ListaDeCompras {
    private static ListaDeCompras instancia= new ListaDeCompras();
    private ArrayList<Producto> listaDeCompras;

    private ListaDeCompras(){
        listaDeCompras=new ArrayList<>();
        /*agregarProducto(new Producto("pan",1,"Kilo"));*/
    }

    public static ListaDeCompras getInstancia() {
        return instancia;
    }

    public void agregarProducto(Producto producto){
        listaDeCompras.add(producto);
    }

    public Producto getProducto(int id){
        return listaDeCompras.get(id);
    }

    public void eliminarProducto(){
        for (int i=0 ;i<listaDeCompras.size(); i++){
            if(!listaDeCompras.get(i).isEstado()==Producto.Agregado){
                listaDeCompras.remove(i);
                i--;
            }
        }
    }

    public ArrayList<Producto> getListaDeCompras() {
        return listaDeCompras;
    }
}
