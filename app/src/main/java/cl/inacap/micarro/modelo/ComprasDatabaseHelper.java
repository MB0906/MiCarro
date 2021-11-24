package cl.inacap.micarro.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ComprasDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="productos.db";
    private static final int DB_VERSION=1;

    public ComprasDatabaseHelper(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTxt="CREATE TABLE PRODUCTOS("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT, "+
                "cantidad INTEGER,"+
                "unidad TEXT,"+
                "estado INTEGER);";
        sqLiteDatabase.execSQL(sqlTxt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void ingresarProducto(Producto producto){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valores= new ContentValues();
        valores.put("nombre",producto.getNombre());
        valores.put("cantidad",producto.getCantidad());
        valores.put("unidad",producto.getUnidadmedida());
        if (producto.isEstado()==Producto.Agregado){
            valores.put("estado",1);
        }else{
            valores.put("estado",0);
        }
        db.insert("PRODUCTOS",null,valores);
    }

    public List<Producto> listaProductos(){
        SQLiteDatabase db=getReadableDatabase();
        List<Producto> productos= new ArrayList<>();
        Cursor cursor=db.query("PRODUCTOS",
                new String[]{"nombre","cantidad","unidad","estado"},
                null,null,null,null,null);
        cursor.moveToFirst();

        int estadoInt;
        boolean estado=false;

        do {
            estadoInt= cursor.getInt(3);
            if (estadoInt==1) estado=true;
            else estado=false;

            productos.add(new Producto(cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    estado));
        }while (cursor.moveToNext());

        cursor.close();
        db.close();
        return productos;
    }

    public Producto getProducto(String nombre){
        Producto p;
        SQLiteDatabase db=getReadableDatabase();
        String sqlTxt="SELECT nombre,cantidad,unidad,estado FROM PRODUCTOS WHERE nombre=?";
        String[] argumentos= new String[] {nombre};
        try{
            Cursor cursor= db.rawQuery(sqlTxt,argumentos);
            cursor.moveToFirst();
            boolean estado=false;

            if (cursor.getInt(3)==1) estado=true;

            p=new Producto(cursor.getString(0),cursor.getInt(1),cursor.getString(2),estado);
        }catch (SQLException ex)
        {
            p=null;
        }
        return p;
    }

    public String cambiarEstado(Producto p){
        int estadoInt;
        if(p.isEstado()==Producto.Agregado){
            estadoInt=1;
        }else{
            estadoInt=0;
        }
        String sqlTxt="UPDATE PRODUCTOS SET estado=? WHERE nombre=?";
        Object[] argumentos= new Object[] {estadoInt, p.getNombre()};
        try{
            getWritableDatabase().execSQL(sqlTxt,argumentos);
            return "Se cambio el estado correctamente";
        }catch (SQLException ex){
            return "No se puede cambiar el estado";
        }
    }

    public String eliminarComprados(){
        String sqlTxt="DELETE FROM PRODUCTOS WHERE estado=0";
        try{
            getWritableDatabase().execSQL(sqlTxt);
            return "Se eliminaron los productos comprados";
        }catch (SQLException ex){
            return "No se puede elimar los productos";
        }
    }

}
