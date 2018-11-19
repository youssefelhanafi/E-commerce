package mps.google.com.ecom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mps.google.com.ecom.adapters.ProduitsAdapter;
import mps.google.com.ecom.entities.Catalogue;
import mps.google.com.ecom.entities.LigneCmd;
import mps.google.com.ecom.entities.Produit;
import mps.google.com.ecom.entities.Utilisateur;

public class produits extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProduitsAdapter adapter;
    private List<Produit> produitsList;
    ArrayList<LigneCmd> eList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits);
        //TextView txt=(TextView) findViewById(R.id.data);
        //txt.setText("CAT "+c.getNomCat());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        produitsList = new ArrayList<>();
        eList=new ArrayList<>();
        adapter = new ProduitsAdapter(this, produitsList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        fetchProducts();
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
            case R.id.cart:
                newCart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean produitNotExists(LigneCmd c){
        for(int i=0;i<eList.size();i++) {
            if (eList.get(i).getProduit().getIdProduit() == c.getProduit().getIdProduit()) {
                eList.get(i).setQte(eList.get(i).getQte()+c.getQte());
                return false;
            }
        }
        return true;
    }

    private void newCart() {
        ArrayList<LigneCmd> mSavedList=new ArrayList<>();

        SharedPreferences sh = getSharedPreferences("panier", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        String jsonf = sh.getString("jsonProduits", "");
        if(jsonf!="") {
            Gson gson = new Gson();
            Type type = new TypeToken<List<LigneCmd>>(){}.getType();
            eList= gson.fromJson(jsonf, type);
            for(int i=0;i<eList.size();i++) mSavedList.add(eList.get(i));
        }
        for(int i=0;i<adapter.prds.size();i++){
            if(produitNotExists(adapter.prds.get(i))) mSavedList.add(adapter.prds.get(i));
        }
            Gson gson = new Gson();
        String jsonCars = gson.toJson(mSavedList);
        Log.d("TAG","jsonProduits = " + jsonCars);
        myEdit.putString("jsonProduits", jsonCars);
        myEdit.commit();
        Utilisateur utilisateur = (Utilisateur) getIntent().getSerializableExtra("user");
        Intent i =new Intent(produits.this,Cart.class);
        i.putExtra("cat",getIntent().getIntExtra("cat",1));
        i.putExtra("user", utilisateur);
        startActivity(i);
    }

    private void logOut() {
        SharedPreferences sharedpreferences = getSharedPreferences("LogInInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void fetchProducts() {
        try {
            Catalogue c = (Catalogue) getIntent().getSerializableExtra("cat");
            String result = new AsyncGetTask(this).execute("http://172.20.10.13/mproduits.php?type="+c.getNomCat()).get();
//            String result = new AsyncGetTask(this).execute("http://10.0.2.2:8080/mproduits.php?type="+c.getNomCat()).get();
            JSONArray jArray = new JSONArray(result);
            if (jArray.length() == 0) {
                Toast.makeText(this, "No Products", Toast.LENGTH_SHORT).show();
            } else {

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    produitsList.add(new Produit(json_data.getInt("idProduit"), json_data.getString("nom_produit"), json_data.getString("type_produit"), Double.parseDouble(json_data.getString("prix")),json_data.getInt("idCatalogue"),json_data.getString("img")));
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}

