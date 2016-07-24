package johannpolania.com.pruebaandroid_johannpolania;

/**
 * Created by koanco on 24/07/2016.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by koanco on 6/03/2016.
 */

public class DatePickerFragment extends android.support.v4.app.DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int ano;
    private int mes;
    private int dia;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar c= Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,month,day);

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        System.out.println("hola");
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        TextView fecha=(TextView)(getActivity().findViewById(R.id.fecha));
        TextView fecha2=(TextView)(getActivity().findViewById(R.id.Fecha2));
        this.ano=year;
        this.mes=month+1;
        this.dia=day;
        fecha.setText("Fecha de nacimiento"+String.valueOf(ano+"/"+mes+"/"+dia));
        fecha2.setText(String.valueOf(ano+"/"+mes+"/"+dia));

    }



}
