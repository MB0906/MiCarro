package cl.inacap.micarro.modelo;

public class Producto {
    private String nombre;
    private int cantidad;
    private String unidadmedida;
    private boolean estado;
    public static final boolean Agregado=true;

    public Producto(String nombre, int cantidad, String unidadmedida, boolean estado) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadmedida = unidadmedida;
        this.estado = estado;
    }

    public Producto(String nombre, int cantidad, String unidadmedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadmedida = unidadmedida;
        this.estado=Agregado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String comprado;
        if(estado==Agregado){
            comprado="Agregado al carro";
        }else{
            comprado="Comprado";
        }
        return nombre+": "+comprado;
    }
}
