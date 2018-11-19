package mps.google.com.ecom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import mps.google.com.ecom.entities.LigneCmd;
import mps.google.com.ecom.entities.Produit;
import mps.google.com.ecom.entities.Utilisateur;

/**
 * Created by famille on 4/30/2018.
 */

class CustomAdapter extends BaseAdapter implements NumberPicker.OnValueChangeListener {
    Context context;
    private List<LigneCmd> produitsList;
    private static LayoutInflater inflater;
    int prdPosition;

    public CustomAdapter(Context ctx, List<LigneCmd> produitsList) {
    context=ctx;
    this.produitsList=produitsList;
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return produitsList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder
    {
        ImageView img;
        TextView tv;
        TextView tvDesc;
        ImageButton edit;
        ImageButton remove;

//        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.productid);
        holder.img=(ImageView)  rowView.findViewById(R.id.imgprd);
        holder.tvDesc=(TextView) rowView.findViewById(R.id.prddesc);
        holder.edit=(ImageButton) rowView.findViewById(R.id.btned);
        holder.remove=(ImageButton) rowView.findViewById(R.id.btnrm);
//        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(produitsList.get(position).getProduit().getNom_produit());
        Picasso.with(context).load(produitsList.get(position).getProduit().getImg()).resize(250, 250).into(holder.img);
        holder.tvDesc.setText("Qte: "+produitsList.get(position).getQte());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,"Edit",Toast.LENGTH_LONG).show();
                prdPosition=position;
                    pickQte();
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,"Edit",Toast.LENGTH_LONG).show();

//                AlertDialog.Builder alert= new AlertDialog.Builder(context);
//                alert.setTitle("Alert!!");
//                alert.setMessage("Etes vous sure de supprimer ce produit?");
//                alert.setPositiveButton("Oui",new OnClick)

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Alert!!");
                alert.setMessage("Etes vous sure de supprimer ce produit?");
                alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
//                        Log.d("TAG","REMOVEITEM POSITION: "+position+" PRODUCT: "+produitsList.get(position).getProduit().getNom_produit());
                        produitsList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
                alert.show();


            }
        });

//        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+position, Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

        private void pickQte() {
        final Dialog d = new Dialog(context);
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

                //np.getValue()
                produitsList.get(prdPosition).setQte(np.getValue());
                notifyDataSetChanged();
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

    @Override
    public void onValueChange (NumberPicker picker,int oldVal, int newVal){
        Log.i("value is", "" + newVal);
    }
}
