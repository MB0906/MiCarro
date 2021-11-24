package cl.inacap.micarro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cl.inacap.micarro.modelo.ComprasDatabaseHelper;
import cl.inacap.micarro.modelo.ListaDeCompras;
import cl.inacap.micarro.modelo.Producto;

public class ListaProductoActivity extends ListActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarLista();
    }

    public void cargarLista() {
        lista = getListView();
        ComprasDatabaseHelper helper = new ComprasDatabaseHelper(this);
        List<Producto> productoList = helper.listaProductos();
        ArrayAdapter<Producto> listaAdapter = new ArrayAdapter<Producto>(this, android.R.layout.simple_list_item_1, productoList);
        lista.setAdapter(listaAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Obtener el item seleccionado
                Object ob=lista.getItemAtPosition(i);
                //Leer lo que esta escrito en el item
                String linea=ob.toString();
                //Obtener nombre del item
                String[] separar=linea.split(":");
                //Llamar a DetallesActivity
                Intent intent = new Intent(ListaProductoActivity.this, DetallesActivity.class);
                intent.putExtra("nombreProducto", separar[0]);
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                cargarLista();
            }
        }
    }
}