package mps.google.com.ecom.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mps.google.com.ecom.AsyncGetTask;
import mps.google.com.ecom.AsyncInscTask;
import mps.google.com.ecom.R;
import mps.google.com.ecom.entities.LigneCmd;
import mps.google.com.ecom.entities.Produit;
import mps.google.com.ecom.entities.Utilisateur;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by famille on 3/12/2018.
 */

public class ProduitsAdapter extends RecyclerView.Adapter<ProduitsAdapter.MyViewHolder> implements NumberPicker.OnValueChangeListener {
    private Context mContext;
    private List<Produit> produitsList;
    public List<LigneCmd> prds;
    static Dialog d;
    Produit p;
    int idp;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public ProduitsAdapter(Context mContext, List<Produit> produitsList) {
        this.mContext = mContext;
        this.produitsList = produitsList;
        prds=new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Produit produit = produitsList.get(position);
        holder.title.setText(produit.getNom_produit());
        holder.count.setText(produit.getPrix() + " MAD");

        // loading album cover using Glide library
        Glide.with(mContext).load(produit.getImg()).into(holder.thumbnail);


        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p = produitsList.get(position);
                idp=position;
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
//                    SharedPreferences sh = mContext.getSharedPreferences("panier", MODE_PRIVATE);
//                    SharedPreferences.Editor myEdit = sh.edit();
//                    Gson gson = new Gson();
//
//                    productsIdC.add(new Integer(idp));
                    pickPrice();
//                    Toast.makeText(mContext, "POSITION: "+idp+"produit id: " + p.getIdProduit(), Toast.LENGTH_SHORT).show();
                    Log.i("IDPX","POSITION: "+idp+"produit id: " + p.getIdProduit());
//                    String url="idProduit="+p.getIdProduit()+"&qte="+1+"&idc="+2;
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    new AsyncInscTask(mContext,url).execute("http://10.0.2.2:8080/insertcmd.php");
                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
                default:
            }
            return false;
        }
    }

    private void pickPrice() {
        final Dialog d = new Dialog(mContext);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
//        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tv.setText(String.valueOf(np.getValue()));
//                Toast.makeText(mContext, "PickNumber " + np.getValue(), Toast.LENGTH_SHORT).show();
                Intent intent = ((Activity) mContext).getIntent();
                Utilisateur utilisateur = (Utilisateur) intent.getSerializableExtra("user");
                int idClt = getIdClient(utilisateur.getIdUser());
                Log.i("IDC", "IDC= " + utilisateur.getIdUser()+" IDCLT: "+idClt+" PRODUIT ID: "+p.getIdProduit()+" QTE : "+np.getValue());

//                String url = "idProduit=" + p.getIdProduit() + "&qte=" + np.getValue() + "&idc="+idClt;
                prds.add(new LigneCmd(p,np.getValue()));
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();

//                new AsyncInscTask(mContext, url).execute("http://10.0.2.2:8080/insertcmd.php");
                d.dismiss();
            }
        });
//        b2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                d.dismiss();
//            }
//        });
        d.show();
    }

    private int getIdClient(int idUser) {
        int r=-1;
        try {
//            String result = new AsyncGetTask(mContext).execute("http://10.0.2.2:8080/getCltById.php?idUser=" + idUser).get();
            String result = new AsyncGetTask(mContext).execute("http://172.20.10.13/getCltById.php?idUser=" + idUser).get();
            JSONArray jArray = new JSONArray(result);
            JSONObject json_data = jArray.getJSONObject(0);
            r= json_data.getInt("idClient");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

        @Override
        public void onValueChange (NumberPicker picker,int oldVal, int newVal){
            Log.i("value is", "" + newVal);
        }

        @Override
        public int getItemCount () {
            return produitsList.size();
        }
    }
