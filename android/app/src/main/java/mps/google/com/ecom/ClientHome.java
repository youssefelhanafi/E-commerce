package mps.google.com.ecom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import mps.google.com.ecom.adapters.GridAdapter;
import mps.google.com.ecom.entities.Catalogue;
import mps.google.com.ecom.entities.Utilisateur;

public class ClientHome extends AppCompatActivity {
    Utilisateur utilisateur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
        //TextView txt = (TextView) findViewById(R.id.text);
        Intent i = getIntent();
         utilisateur = (Utilisateur) i.getSerializableExtra("user");

        //txt.setText("Welcome Mr " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        getCat();
    }

    private void getCat() {
        try {
//            String result = new AsyncGetTask(this).execute("http://10.0.2.2:8080/cats.php").get();
            String result = new AsyncGetTask(this).execute("http://172.20.10.13/cats.php").get();
            JSONArray jArray = new JSONArray(result);
            if (jArray.length() == 0) {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            } else {
                final ArrayList<Catalogue> catalogues=new ArrayList<>();
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    catalogues.add(new Catalogue(json_data.getInt("idCatalogue"), json_data.getString("nom_catalogue"), json_data.getString("date_catalogue"), json_data.getString("img")));
                }
                GridView gridview = (GridView) findViewById(R.id.gridview);

                gridview.setAdapter(new GridAdapter(this, catalogues));
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
//                        Toast.makeText(ClientHome.this, "" + position,
//                                Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(ClientHome.this,produits.class);
//                        Toast.makeText(ClientHome.this,catalogues.get(position).getNomCat(),Toast.LENGTH_LONG).show();
//                        Log.d("testLaunchItem","CAT : "+catalogues.get(position).getNomCat());
                        i.putExtra("cat",catalogues.get(position));
                        i.putExtra("user", utilisateur);
                        startActivity(i);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut() {
        SharedPreferences sharedpreferences = getSharedPreferences("LogInInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
