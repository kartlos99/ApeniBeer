package com.example.kartl.apenibeer;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kartl.apenibeer.adapters.MainExpAdapter;
import com.example.kartl.apenibeer.models.BeerType;
import com.example.kartl.apenibeer.models.Jgufi;
import com.example.kartl.apenibeer.models.KasriType;
import com.example.kartl.apenibeer.models.Obieqti;
import com.example.kartl.apenibeer.utils.Constantebi;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import android.widget.SearchView;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchManager searchManager;
    private android.widget.SearchView searchView;
    private MenuItem searchItem;
    List<Obieqti> obieqteis_sia;

    ExpandableListView main_listExp;
    ProgressDialog progressDialog;

    MainExpAdapter expAdapter;
    FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        main_listExp = (ExpandableListView) findViewById(R.id.mainexpListView);

        get_obieqts();
        if (Constantebi.kasriTypeList.size() < 1){
            get_BaseUnits();
        }


        fabButton = (FloatingActionButton) findViewById(R.id.fab_main);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addPageIntent = new Intent();
                addPageIntent.setClass(getApplicationContext(), AddingActivity.class);
                startActivity(addPageIntent);
            }
        });



    }

    public void expandAll(){
        for (int i =0; i < expAdapter.getGroupCount(); i++){
            main_listExp.expandGroup(i,true);
        }
    }

    private void get_obieqts() {

        progressDialog = ProgressDialog.show(this,"იტვირთება!", "loading!");
        obieqteis_sia = new ArrayList<Obieqti>();
        String url = Constantebi.URL_GET_OBIEQTS;


        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest requestObieqtebi = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // aq modis obieqtebis chamonatvali

                ArrayList<Jgufi> mainObjList = new ArrayList<Jgufi>();

                if(response.length() > 0){
                    obieqteis_sia.clear();
                    for (int i=0; i<response.length(); i++){
                        try {
                            Obieqti axaliObieqti = new Obieqti(response.getJSONObject(i).getString("dasaxeleba"), response.getJSONObject(i).getString("jgufi"));
                            axaliObieqti.setAdress(response.getJSONObject(i).getString("adress"));
                            axaliObieqti.setTel(response.getJSONObject(i).getString("tel"));
                            axaliObieqti.setComment(response.getJSONObject(i).getString("comment"));

                            obieqteis_sia.add(axaliObieqti);

                        }catch (JSONException excep){
                            excep.printStackTrace();
                        }
                    }


                    String jgufiName = obieqteis_sia.get(0).getJgufi();
                    ArrayList<Obieqti> childList = new ArrayList<Obieqti>();

                    for (int i = 0; i< obieqteis_sia.size(); i++){

                        if(jgufiName.equals(obieqteis_sia.get(i).getJgufi())){
                            childList.add(obieqteis_sia.get(i));
                        }else{
                            mainObjList.add(new Jgufi(jgufiName, childList));
                            jgufiName = obieqteis_sia.get(i).getJgufi();
                            childList = new ArrayList<Obieqti>();
                            childList.add(obieqteis_sia.get(i));
                        }
                    }
                    mainObjList.add(new Jgufi(jgufiName, childList));
                }

                expAdapter = new MainExpAdapter(mainObjList ,getApplicationContext());
                main_listExp.setAdapter(expAdapter);
                progressDialog.dismiss();
//                expAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

        queue.add(requestObieqtebi);

    }

    private void get_BaseUnits(){
        RequestQueue queue = Volley.newRequestQueue(this);

        // kasris tipebis sia mogvaqvs
        JsonArrayRequest requestKasriList = new JsonArrayRequest(Constantebi.URL_GET_KASRILIST, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length()>0){

                    for (int i=0; i<response.length(); i++){
                        try {
                            KasriType newkasriType = new KasriType(response.getJSONObject(i).getString("dasaxeleba"),response.getJSONObject(i).getInt("litraji"));
                            Constantebi.kasriTypeList.add(newkasriType);
                        }catch (JSONException excep){
                            excep.printStackTrace();
                        }
                    }
                }else{
                    Toast.makeText(MainActivity.this, "კასრების მონაცემებია შესაყვანი!", Toast.LENGTH_LONG).show();
                    Constantebi.kasriTypeList.add(new KasriType("uzomo",0));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // ludis saxeobebis sia mogvaqvs
        JsonArrayRequest requestLudiList = new JsonArrayRequest(Constantebi.URL_GET_LUDILIST, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length()>0){

                    for (int i=0; i<response.length(); i++){
                        try {
                            BeerType newBeerType= new BeerType(response.getJSONObject(i).getString("dasaxeleba"),response.getJSONObject(i).getDouble("fasi"));
                            Constantebi.beerTypeList.add(newBeerType);
                        }catch (JSONException excep){
                            excep.printStackTrace();
                        }
                    }

                }else{
                    Toast.makeText(MainActivity.this, "ლუდის სახეოების მონაცემებია შესაყვანი!", Toast.LENGTH_LONG).show();
                    Constantebi.beerTypeList.add(new BeerType("N.A.",0));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(requestKasriList);
        queue.add(requestLudiList);
    }

    @Override
    public boolean onClose() {
        expAdapter.filterData("");
//        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        expAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        expAdapter.filterData(newText);
        expandAll();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();

        return super.onCreateOptionsMenu(menu);
    }
}
