/*A través de esta clase, se creará la base de datos local, la cual contiene las tablas donde se realizará el registro de los usuarios
y las películas vistas para cada uno de ellos*/

package johannpolania.com.pruebaandroid_johannpolania;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ConexionRegistro extends SQLiteOpenHelper {


    public ConexionRegistro(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    public void onCreate(SQLiteDatabase db) {

        /*En la tabla usuarios se almacenará los usuarios que se registran en la aplicación*/
        db.execSQL("create table usuarios(email text, nombre text, fecha_nacimiento text)");
        /*En la siguiente tabla se almacenan las películas que son vistas por el usuario*/
        db.execSQL("create table peliculasVistas(nombre text, duracion text, genero text, edad text, horario text, url text, email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuarios");
        db.execSQL("drop table if exists peliculasVistas");
        db.execSQL("create table usuarios(email text primary key , nombre text, fecha_nacimiento text)");
        /*En la siguiente tabla se almacenan las películas que son vistas por el usuario*/
        db.execSQL("create table peliculasVistas(nombre text, duracion text, genero text, edad text, horario text, url text, email text)");

    }
}
