package cl.inacap.micarro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cl.inacap.micarro.modelo.ListaDeCompras;
import cl.inacap.micarro.modelo.Producto;

public class AgregarProductoActivity extends AppCompatActivity {
    private ListaDeCompras listaDeCompras=ListaDeCompras.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
    }

    public void ingresarProducto(View view){
        String nombre=((EditText)findViewById(R.id.ingresarNombre)).getText().toString();
        String cantidadStr=((EditText)findViewById(R.id.ingresarCantidad)).getText().toString();
        String unidad=((Spinner)findViewById(R.id.ingresarUnidad)).getSelectedItem().toString();
        String unidadNueva=((EditText)findViewById(R.id.otraUnidad)).getText().toString();
        int cantidad=0;
        CheckBox acepto=(CheckBox) findViewById(R.id.aceptoUnidad);

        if(nombre.equals("")){
            Toast.makeText(this,"Debe ingresar el nombre del nuevo producto",Toast.LENGTH_SHORT).show();
        }else{
            try {
                cantidad = (Integer.parseInt(cantidadStr));
            }catch(NumberFormatException ex){
                Toast.makeText(this,"Debe ingresar una cantidad.",Toast.LENGTH_SHORT).show();
            }
            if(cantidad>0){
                if(unidad.equals("Otro")){
                    if(unidadNueva.equals("")){
                        Toast.makeText(this,"Debe ingresar una nueva unidad de medida.",Toast.LENGTH_SHORT).show();
                    }else{
                        if(acepto.isChecked()){
                            unidad=unidadNueva;
                            Producto producto=new Producto(nombre,cantidad,unidad);
                            listaDeCompras.agregarProducto(producto);
                            finish();
                            Toast.makeText(this,"Tu producto se agrego exitosamente✔",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this,"Debe aceptar la nueva unidad de medida.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Producto producto=new Producto(nombre,cantidad,unidad);
                    listaDeCompras.agregarProducto(producto);
                    finish();
                    Toast.makeText(this,"Tu producto se agrego exitosamente✔",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Debe ingresar una cantidad mayor que cero.",Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void onAceptoUnidad(View view){
        boolean acepta=((CheckBox)view).isChecked();
            if(!acepta){
                Toast.makeText(this,"Debe aceptar la nueva unidad de medida.", Toast.LENGTH_SHORT).show();
            }
    }
}