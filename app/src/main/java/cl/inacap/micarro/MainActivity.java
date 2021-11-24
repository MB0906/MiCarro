package cl.inacap.micarro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import cl.inacap.micarro.modelo.ComprasDatabaseHelper;
import cl.inacap.micarro.modelo.ListaDeCompras;
import cl.inacap.micarro.modelo.Producto;

public class MainActivity extends AppCompatActivity {

    //private ListaDeCompras lista=ListaDeCompras.getInstancia();
    private ComprasDatabaseHelper helper= new ComprasDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void verLista(View view){
        try {
            ArrayList<Producto> productos = (ArrayList<Producto>) helper.listaProductos();
            Intent intent=new Intent(this,ListaProductoActivity.class);
            startActivity(intent);
        }catch(Exception ex){
            Toast.makeText(this,"¡Tu lista de compra esta vacia!", Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"¡Empieza agregando un producto!", Toast.LENGTH_SHORT).show();
        }
    }
    public void agregarProducto(View view){
        Intent intent=new Intent(this,AgregarProductoActivity.class);
        startActivity(intent);
    }

    public void eliminarProducto(View view){
        String msg=helper.eliminarComprados();
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }



}