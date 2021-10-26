package cl.inacap.micarro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cl.inacap.micarro.modelo.ListaDeCompras;
import cl.inacap.micarro.modelo.Producto;

public class ListaProductoActivity extends ListActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarLista();
    }

    public void cargarLista(){
        lista=getListView();
        List<Producto> productoList=ListaDeCompras.getInstancia().getListaDeCompras();
            ArrayAdapter<Producto> listaAdapter= new ArrayAdapter<Producto>(this, android.R.layout.simple_list_item_1,productoList);
        lista.setAdapter(listaAdapter);
    }
    @Override
    public void onListItemClick(ListView listView, View view, int posicion, long id){
    Intent intent= new Intent(ListaProductoActivity.this, DetallesActivity.class);
    intent.putExtra("idProducto",(int)id);
    startActivityForResult(intent,1);
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