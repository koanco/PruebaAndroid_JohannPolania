package johannpolania.com.pruebaandroid_johannpolania;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    EditText correo;
    TextView  Fecha;
    TextView  Fecha2;
private Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=(EditText)findViewById(R.id.eNombre);
        correo=(EditText)findViewById(R.id.eCorreo);
        Fecha=(TextView)findViewById(R.id.fecha);
        Fecha2=(TextView)findViewById(R.id.Fecha2);
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void ingresar(View v)
    {
        if(!(nombre.getText().toString().equals("")) && !correo.getText().toString().equals("") &&  !Fecha.getText().toString().equals("Fecha de nacimiento"))
        {
            Toast.makeText(this,"Muy bien",Toast.LENGTH_SHORT).show();
            guardarUsuario();
            Intent i=new Intent(this, MenuActivity.class);
                    startActivity(i);

        }
        else
        {
            Toast.makeText(this,"Faltan datos por ingresar",Toast.LENGTH_SHORT).show();

        }


    }

    private void guardarUsuario( )
    {
        ConexionRegistro cinema=new ConexionRegistro(this,"bdCinema",null,2);
        SQLiteDatabase bd=cinema.getWritableDatabase();
        String nombre=(this.nombre.getText().toString()).toUpperCase();
        String correo=this.correo.getText().toString();
        String fecha=this.Fecha2.getText().toString();


        if(verificarDuplicado(nombre,correo,fecha)!=true) {

            ContentValues registro = new ContentValues(); //Para guardar)

            registro.put("nombre", nombre);
            registro.put("email", correo);
            registro.put("fecha_nacimiento", fecha);
            bd.insert("usuarios", null, registro);
            bd.close();

            Toast.makeText(this, "Bienvenido por primera vez"+nombre, Toast.LENGTH_SHORT).show();

        }
        else
        {

            Toast.makeText(this, "Bienvenido nuevamente: "+nombre, Toast.LENGTH_SHORT).show();

        }


    }

    public boolean verificarDuplicado(String nombre,String correo,String fecha)

    {
        boolean bandera=false;

        ConexionRegistro cinema=new ConexionRegistro(this,"bdCinema",null,2);
        SQLiteDatabase  bd=cinema.getWritableDatabase();


        c=bd.rawQuery("select *  from usuarios  where nombre='"+nombre+"' and email='"+correo+"' and fecha_nacimiento='"+fecha+"' ",null);
        if(c.moveToFirst()==true)
        {
            bandera=true;


        }




        bd.close();

        return bandera;

    }


}
