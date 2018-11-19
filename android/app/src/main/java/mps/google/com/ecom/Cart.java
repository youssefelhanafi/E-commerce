package mps.google.com.ecom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mps.google.com.ecom.entities.LigneCmd;
import mps.google.com.ecom.entities.Produit;
import mps.google.com.ecom.entities.Utilisateur;

public class Cart extends AppCompatActivity {
    ListView listView;
    private List<LigneCmd> produitsList;
    EditText adrs;
    boolean saved=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView=(ListView)findViewById(R.id.list);
        produitsList=new ArrayList<>();
        getProducts();
        listView.setAdapter(new CustomAdapter(this, produitsList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cartmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                logOut();
                return true;
            case R.id.done:
                popUp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newCmd() {

        Utilisateur utilisateur = (Utilisateur) getIntent().getSerializableExtra("user");
        int idClt = getIdClient(utilisateur.getIdUser());
        String ur="adrliv="+adrs.getText().toString()+"&idc="+idClt;
//        new AsyncInscTask(this, ur).execute("http://10.0.2.2:8080/cmd.php");
        new AsyncInscTask(this, ur).execute("http://172.20.10.13/cmd.php");
        for(int i=0;i<produitsList.size();i++){
//            Log.d("TAG","ADRLIVID : "+adrs.getText().toString());
        String url = "idProduit="+produitsList.get(i).getProduit().getIdProduit() + "&qte=" + produitsList.get(i).getQte() ;
//        new AsyncInscTask(this, url).execute("http://10.0.2.2:8080/newcmd.php");
        new AsyncInscTask(this, url).execute("http://172.20.10.13/newcmd.php");
        }
        saved=true;
        Intent i = new Intent(Cart.this,produits.class);
        i.putExtra("cat",getIntent().getIntExtra("cat",1));
        i.putExtra("user", utilisateur);
        startActivity(i);
    }

    private void popUp() {
        //Preparing views
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_layout, null);
//layout_root should be the name of the "top-level" layout node in the dialog_layout.xml file.
        adrs = (EditText) layout.findViewById(R.id.adrliv);

        //Building dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setPositiveButton("Commander", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //save info where you want it
                newCmd();
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int getIdClient(int idUser) {
        int r=-1;
        try {
            String result = new AsyncGetTask(this).execute("http://172.20.10.13/getCltById.php?idUser=" + idUser).get();
//            String result = new AsyncGetTask(this).execute("http://10.0.2.2:8080/getCltById.php?idUser=" + idUser).get();
            JSONArray jArray = new JSONArray(result);
            JSONObject json_data = jArray.getJSONObject(0);
            r= json_data.getInt("idClient");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    public void getProducts() {
        SharedPreferences sh = getSharedPreferences("panier", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        String jsonf = sh.getString("jsonProduits", "");
        if(jsonf!=""){
            Gson gson = new Gson();
            Type type = new TypeToken<List<LigneCmd>>(){}.getType();
            produitsList= gson.fromJson(jsonf, type);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG","LEAVINGACT");
        SharedPreferences sh = getSharedPreferences("panier", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        if(saved){
            myEdit.putString("jsonProduits", "");
            myEdit.commit();
        }else {
            Gson gson = new Gson();
            String jsonCars = gson.toJson(produitsList);
            Log.d("TAG","jsonProduits = " + jsonCars);
            myEdit.putString("jsonProduits", jsonCars);
            myEdit.commit();
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
