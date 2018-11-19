package mps.google.com.ecom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mps.google.com.ecom.R;
import mps.google.com.ecom.entities.Catalogue;

/**
 * Created by famille on 3/11/2018.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Catalogue> catalogues;

    public GridAdapter(Context mContext, ArrayList<Catalogue> catalogues) {
        this.mContext = mContext;
        this.catalogues = catalogues;
    }

    @Override
    public int getCount() {
        return catalogues.size();
    }

    @Override
    public Object getItem(int i) {
        return catalogues.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Catalogue c = catalogues.get(i);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.categorie_layout, null);


            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
        }

        final ImageView imageView = (ImageView) view.findViewById(R.id.categorie_image);
        final TextView nameTextView = (TextView) view.findViewById(R.id.categorie_name);

        // 4
        Picasso.with(mContext).load(catalogues.get(i).getImgCat()).resize(500, 500).into(imageView);
        nameTextView.setText(catalogues.get(i).getNomCat());




//
//        else {
//            imageView = (ImageView) view;
//        }
//        Picasso.with(mContext)
//                .load(catalogues.get(i).getImgCat())
//                .resize(500, 500).into(imageView);
        return view;
    }
}
