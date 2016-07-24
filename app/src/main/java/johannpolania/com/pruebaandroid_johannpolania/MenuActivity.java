package johannpolania.com.pruebaandroid_johannpolania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void verCartelera(View view)
    {
        Intent i=new Intent(this,CarteleraActivity.class);
        startActivity(i);


    }

}
