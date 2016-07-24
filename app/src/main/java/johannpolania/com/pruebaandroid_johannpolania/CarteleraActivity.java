package johannpolania.com.pruebaandroid_johannpolania;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CarteleraActivity extends AppCompatActivity {
    private TextView tCartelera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);
        tCartelera = (TextView) findViewById(R.id.tCartelera);
        new MyAsyncTask().execute();
        cargarDatos();
        //descargarFichero();

    }

    /*A través de esta clase se descarga el archivo donde esta la información de las pelóculas*/
    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {

                URL url = new URL("https://dl.dropboxusercontent.com/u/39626335/PruebaEleinco/pruebaEleinco_Peliculas.txt");

                //establecemos la conexión con el destino
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                //establecemos el método jet para nuestra conexión
                //el método setdooutput es necesario para este tipo de conexiones
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);

                urlConnection.connect();


                File SDCardRoot = Environment.getExternalStorageDirectory();

//Creamos un nuevo archivo llamado cartelera el cual almacenará la información de las peliculas contenidas en la url
                File file = new File(getFilesDir(), "cartelera.txt");


                FileOutputStream fileOutput = new FileOutputStream(file);


                InputStream inputStream = urlConnection.getInputStream();

                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;


                byte[] buffer = new byte[1024];
                int bufferLength = 0;


                while ((bufferLength = inputStream.read(buffer)) > 0) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;


                }

                fileOutput.close();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
//A través de este método mostramos en la actividad lo que contiene el archivo cartelera.txt
    public void cargarDatos() {


        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), "cartelera.txt");
        try {
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader archivo = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            tCartelera.setText(todo);

        } catch (IOException e) {
        }

        }

    }
