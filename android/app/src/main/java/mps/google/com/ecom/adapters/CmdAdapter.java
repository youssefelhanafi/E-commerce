package mps.google.com.ecom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mps.google.com.ecom.AsyncInscTask;
import mps.google.com.ecom.R;
import mps.google.com.ecom.entities.Commande;
import mps.google.com.ecom.entities.LigneCmd;

/**
 * Created by famille on 5/1/2018.
 */

public class CmdAdapter extends BaseAdapter{
    Context context;
    private List<Commande> cmdList;
    private static LayoutInflater inflater;

    public CmdAdapter(Context context, List<Commande> cmdList) {
        this.context = context;
        this.cmdList = cmdList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cmdList.size();
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
        TextView cmdInf;
        TextView cmdDate;
        ImageButton btn;
//        TextView cmdDate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row_item_cmd, null);
        holder.cmdInf=(TextView) rowView.findViewById(R.id.cmdInfo);
        holder.cmdDate=(TextView)  rowView.findViewById(R.id.cmdDate);
        holder.btn=(ImageButton) rowView.findViewById(R.id.btnliv);
        holder.cmdInf.setText("CMD NUM: "+cmdList.get(position).getIdCommande());
        holder.cmdDate.setText(cmdList.get(position).getDate_commande());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //UPDATE CMD LIVRER!
                String url = "idc="+cmdList.get(position).getIdCommande();
                new AsyncInscTask(context,url).execute("http://172.20.10.13/updatecmd.php");
//                new AsyncInscTask(context,url).execute("http://10.0.2.2:8080/updatecmd.php");
                cmdList.remove(position);
                notifyDataSetChanged();
            }
        });
    rowView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Toast.makeText(context, "You Clicked "+position, Toast.LENGTH_LONG).show();
        }
    });
        return rowView;
    }
}
