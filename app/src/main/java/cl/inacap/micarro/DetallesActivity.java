package cl.inacap.micarro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import cl.inacap.micarro.R;
import cl.inacap.micarro.modelo.ListaDeCompras;
import cl.inacap.micarro.modelo.Producto;

public class DetallesActivity extends AppCompatActivity {

    public Producto producto;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        intent=getIntent();
        int id=(Integer) intent.getExtras().get("idProducto");
        producto= ListaDeCompras.getInstancia().getProducto(id);

        TextView txtNombre=(TextView) findViewById(R.id.txtNombre);
        txtNombre.setText("Nombre del producto: "+producto.getNombre());

        TextView txtUnidadmedida=(TextView) findViewById(R.id.txtUnidadmedida);
        txtUnidadmedida.setText("Cantidad del producto: "+producto.getCantidad()+" "+producto.getUnidadmedida());

        TextView txtEstado=(TextView) findViewById(R.id.txtEstado);
        Button cambiar=(Button) findViewById(R.id.estado);
        if(producto.isEstado()==Producto.Agregado){
            txtEstado.setText("Estado del producto: Agregado al carro \uD83D\uDED2");
            cambiar.setText("Marcar como Comprado \uD83E\uDDFE");
        }else{
            txtEstado.setText("Estado del producto: Comprado \uD83E\uDDFE");
            cambiar.setText("Marcar como Agregado \uD83D\uDED2");
        }
    }
    public void cambiarEstado(View view){
    producto.setEstado(!producto.isEstado());
    setResult(RESULT_OK, intent);
    finish();
    }
}