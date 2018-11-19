package mps.google.com.ecom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import mps.google.com.ecom.entities.Utilisateur;

public class MainActivity extends AppCompatActivity {

    EditText login, mdp;
    Utilisateur user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authTest();
        login = (EditText) findViewById(R.id.mlogin);
        mdp = (EditText) findViewById(R.id.mmdp);
    }

    private void authTest() {
        SharedPreferences sh = getSharedPreferences("LogInInfo", MODE_PRIVATE);
        // // nom prenom  email  mdp login  adresse  telephone
        int idUser = sh.getInt("id", -1);
        String nom = sh.getString("nom", "");
        String prenom = sh.getString("prenom", "");
        String email = sh.getString("email", "");
        String mdp = sh.getString("mdp", "");
        String logIn = sh.getString("login", "");
        String adresse = sh.getString("adresse", "");
        String telephone = sh.getString("telephone", "");
        if (!(logIn.equals("")) || !(mdp.equals(""))) {
            //Toast.makeText(this, "you Already logged In", Toast.LENGTH_LONG).show();
            startHomeClient(new Utilisateur(idUser, nom, prenom, email, mdp, logIn, adresse, telephone));
        }

    }

    private void startHomeClient(Utilisateur utilisateur) {
        Intent i1 = new Intent(this, ClientHome.class);
        i1.putExtra("user", utilisateur);
        startActivity(i1);
    }


    public void launchApp(View view) {
        Intent i = new Intent(this, Inscription.class);
        startActivity(i);
    }

    public void authentification(View view) {
        try {
            String result = new AsyncGetTask(this).execute("http://172.20.10.13/login.php?login=" + login.getText().toString() + "&mdp=" + mdp.getText().toString()).get();
//            String result = new AsyncGetTask(this).execute("http://10.0.2.2:8080/login.php?login=" + login.getText().toString() + "&mdp=" + mdp.getText().toString()).get();
            JSONArray jArray = new JSONArray(result);
            if (jArray.length() == 0) {
                Toast.makeText(this, "Log In Or Password Incorrect", Toast.LENGTH_LONG).show();
            } else {
                JSONObject json_data = jArray.getJSONObject(0);
                user = new Utilisateur(json_data.getInt("idUser"), json_data.getString("nom"), json_data.getString("prenom"), json_data.getString("email"), json_data.getString("motdepass"), json_data.getString("login"), json_data.getString("adresse"), json_data.getString("telephone"));
//                Toast.makeText(this, "Welcome Mr " + user.getNom() + " " + user.getPrenom(), Toast.LENGTH_LONG).show();
                saveLogIn(user);
//                Log.d("TAG","NBX idu "+user.getIdUser());
                livreur(user.getIdUser());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void livreur(int idu) {
//        Toast.makeText(MainActivity.this,"LIVREUR",Toast.LENGTH_LONG).show();
        try {
            String result = new AsyncGetTask(this).execute("http://172.20.10.13/livreur.php?idc=" + idu).get();
//            String result = new AsyncGetTask(this).execute("http://10.0.2.2:8080/livreur.php?idc=" + idu).get();
//            int nb = Integer.parseInt(result);
//            Log.d("TAG", "NBX v = " + result);
//           Log.d("TAG", "NBX v = " + result);
//            Log.d("TAG", "NBX v = " + nb);
            if (result.substring(0,1).equals("1")) {
//                Toast.makeText(MainActivity.this,"New LIVREUR "+result,Toast.LENGTH_LONG).show();
//            Log.d("TAG","NBX =>> 1");
//                Log.d("TAG", "NBX v = " + nb);
                Intent in = new Intent(MainActivity.this, LivreurInterface.class);
                in.putExtra("user", user);
                startActivity(in);
            } else {
//                Log.d("TAG", "NBX v = " + nb);
//            Log.d("TAG","NBX =>> 0");
//                Toast.makeText(MainActivity.this,"New CLIENT "+result,Toast.LENGTH_LONG).show();
                startHomeClient(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveLogIn(Utilisateur user) {
        SharedPreferences sh = getSharedPreferences("LogInInfo", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        myEdit.putInt("id", user.getIdUser());
        myEdit.putString("nom", user.getNom());
        myEdit.putString("prenom", user.getPrenom());
        myEdit.putString("email", user.getEmail());
        myEdit.putString("mdp", user.getMotdepass());
        myEdit.putString("login", user.getLogin());
        myEdit.putString("adresse", user.getAdresse());
        myEdit.putString("telephone", user.getTelephone());
        myEdit.commit();
    }
}
