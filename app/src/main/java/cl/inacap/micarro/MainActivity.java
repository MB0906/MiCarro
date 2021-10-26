package cl.inacap.micarro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import cl.inacap.micarro.modelo.ListaDeCompras;
import cl.inacap.micarro.modelo.Producto;

public class MainActivity extends AppCompatActivity {

    private ListaDeCompras lista=ListaDeCompras.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void verLista(View view){
        ArrayList<Producto> productos=lista.getListaDeCompras();
        if(productos.size()>0){
            Intent intent=new Intent(this,ListaProductoActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"¡Tu lista de compra esta vacia!", Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"¡Empieza agregando un producto!", Toast.LENGTH_SHORT).show();
        }

    }
    public void agregarProducto(View view){
        Intent intent=new Intent(this,AgregarProductoActivity.class);
        startActivity(intent);
    }

    public void eliminarProducto(View view){
        lista.eliminarProducto();
        Toast.makeText(this,"Todos los productos comprados han sido eliminado exitosamente", Toast.LENGTH_SHORT).show();
    }



}