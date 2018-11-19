package mps.google.com.ecom;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mps.google.com.ecom.adapters.CmdAdapter;
import mps.google.com.ecom.entities.Catalogue;
import mps.google.com.ecom.entities.Commande;
import mps.google.com.ecom.entities.LigneCmd;
import mps.google.com.ecom.entities.Produit;

public class LivreurInterface extends AppCompatActivity {
    ListView listView;
    private List<Commande> cmdList;
//    NotificationCompat.Builder mNotifyBuilder;
//    NotificationManager mNotifyManager;
//    static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livreur_interface);
        listView = (ListView) findViewById(R.id.listCmd);
//        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        cmdList = new ArrayList<>();
        getCmd();
        listView.setAdapter(new CmdAdapter(this, cmdList));

    }

    private void getCmd() {
        try {
//            String result = new AsyncGetTask(this).execute("http://10.0.2.2:8080/mcmd.php").get();
            String result = new AsyncGetTask(this).execute("http://172.20.10.13/mcmd.php").get();
            JSONArray jArray = new JSONArray(result);
            if (jArray.length() == 0) {
                Toast.makeText(this, "No Products", Toast.LENGTH_SHORT).show();
            } else {
//                mNotifyBuilder = new NotificationCompat.Builder(this).setContentTitle("Nouveaux Produit").setContentText("Vous avez des produits a livrer").setSmallIcon(R.drawable.ic_feedback_black);
//                Notification myNotification = mNotifyBuilder.build();
//                mNotifyManager.notify(NOTIFICATION_ID, myNotification);
                notification();
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    cmdList.add(new Commande(json_data.getInt("idCommande"), json_data.getString("adresse_livraison"), json_data.getString("date_commande"), json_data.getInt("idLivreur"), json_data.getInt("idClient"), json_data.getInt("Etat")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notification() {
        NotificationManagerCompat myManager=NotificationManagerCompat.from(this);
        NotificationCompat.Builder myNotify=new NotificationCompat.Builder(this);
        myNotify.setContentTitle("Nouveau Commandes");
        myNotify.setContentText("Vous avez des produits a livrer");
        myNotify.setSmallIcon(R.drawable.ic_feedback_black);
        Intent i1 =new Intent(this,MainActivity.class);
        PendingIntent pd=PendingIntent.getActivity(this,1,i1,0);
        myNotify.setContentIntent(pd);
        myNotify.setAutoCancel(true);
        myManager.notify(1,myNotify.build());
//        finish();
 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_liv, menu);
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
