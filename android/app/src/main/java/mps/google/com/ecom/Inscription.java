package mps.google.com.ecom;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Inscription extends AppCompatActivity {

    Button btn;
    EditText nom, prenom, email, motdepass, login, adresse, telephone, dateNaissance;
    private Calendar myCalendar;
    private String myFormat;
    private SimpleDateFormat sdf;
    int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        btn = (Button) findViewById(R.id.inscription);
        nom = (EditText) findViewById(R.id.nomed);
        prenom = (EditText) findViewById(R.id.prenomed);
        email = (EditText) findViewById(R.id.emailed);
        motdepass = (EditText) findViewById(R.id.mdped);
        login = (EditText) findViewById(R.id.logined);
        adresse = (EditText) findViewById(R.id.adred);
        telephone = (EditText) findViewById(R.id.teled);
        dateNaissance = (EditText) findViewById(R.id.datenaissance);


        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        dateNaissance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Inscription.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }


    private void updateLabel() {
        myFormat = "MM/dd/yy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        //birthday=myCalendar.getTime();
        dateNaissance.setText(sdf.format(myCalendar.getTime()));
    }


    public void onRadioButtonClicked(View view) {


        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.client:
                if (checked) {
                    dateNaissance.setVisibility(View.VISIBLE);
                    type = 1;
                }
                break;
            case R.id.liv:
                if (checked) {
                    dateNaissance.setVisibility(View.INVISIBLE);
                    type = 2;
                }
                break;

            case R.id.admin:
                if (checked) {
                    dateNaissance.setVisibility(View.INVISIBLE);
                    type = 3;
                }
                break;
        }


    }


    public void inscrire(View view) {
        String url = "nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&email=" + email.getText().toString() + "&mdp=" + motdepass.getText().toString() + "&login=" + login.getText().toString() + "&adr=" + adresse.getText().toString() + "&tel=" + telephone.getText().toString() + "&type=" + type;
        if (type == 1) {
            url = "nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&email=" + email.getText().toString() + "&mdp=" + motdepass.getText().toString() + "&login=" + login.getText().toString() + "&adr=" + adresse.getText().toString() + "&tel=" + telephone.getText().toString() + "&daten=" + dateNaissance.getText().toString() + "&type=" + type;
        }
//        new AsyncInscTask(this,url).execute("http://10.0.2.2:8080/insert.php");
        new AsyncInscTask(this,url).execute("http://172.20.10.13/insert.php");
    }

}
