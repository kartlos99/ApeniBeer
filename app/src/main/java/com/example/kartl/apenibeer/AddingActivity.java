package com.example.kartl.apenibeer;

import android.graphics.Color;
import android.provider.SyncStateContract;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.kartl.apenibeer.utils.Constantebi;

import java.util.ArrayList;
import java.util.List;


public class AddingActivity extends AppCompatActivity {

    TextView textView, textViewAddBeer;
    EditText editText, edit_KasriCount, edit_Litraji, edit_ertFasi;
    Button btn_KasriCount_dec, btn_KasriCount_inc, btn_ertFasi_dec, btn_ertFasi_inc;
    Spinner spinner_KasriType, spinner_BeerType;
    Boolean ludis_shetana = false;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        textView = (TextView) findViewById(R.id.textView2);
        cardView = (CardView) findViewById(R.id.cardView);
        editText = (EditText) findViewById(R.id.username);
        textViewAddBeer = (TextView) findViewById(R.id.text_add_Beer);

        edit_KasriCount = (EditText) findViewById(R.id.edit_KasriCount);
        edit_Litraji = (EditText) findViewById(R.id.edit_Litraji);
        edit_ertFasi = (EditText) findViewById(R.id.edit_ertFasi);

        btn_KasriCount_dec = (Button) findViewById(R.id.btn_KasriCount_dec);
        btn_KasriCount_inc = (Button) findViewById(R.id.btn_KasriCount_inc);
        btn_ertFasi_dec = (Button) findViewById(R.id.btn_ertFasi_dec);
        btn_ertFasi_inc = (Button) findViewById(R.id.btn_ertFasi_inc);

        spinner_BeerType = (Spinner) findViewById(R.id.spinner_BeerType);
        spinner_KasriType = (Spinner) findViewById(R.id.spinner_KasriType);

        List<String> kasriList = new ArrayList<>();
        for (int i = 0; i<Constantebi.kasriTypeList.size(); i++){
            kasriList.add(Constantebi.kasriTypeList.get(i).getDasaxeleba());
        }

        List<String> beerList = new ArrayList<>();
        for (int i = 0; i<Constantebi.beerTypeList.size(); i++){
            beerList.add(Constantebi.beerTypeList.get(i).getDasaxeleba());
        }

        ArrayAdapter kasriSpAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, kasriList);
        spinner_KasriType.setAdapter(kasriSpAdapter);

        ArrayAdapter beerSpAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, beerList);
        spinner_BeerType.setAdapter(beerSpAdapter);

        spinner_KasriType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                litrajiCalculation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_BeerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Double fasi = Constantebi.beerTypeList.get(spinner_BeerType.getSelectedItemPosition()).getFasi();
                edit_ertFasi.setText(String.valueOf(fasi));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_KasriCount_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = edit_KasriCount.getText().toString();
                if (ss.equals("")){
                    ss="0";
                }
                int ii = Integer.valueOf(ss);
                if (ii > 1){
                    ii--;
                }
                edit_KasriCount.setText(String.valueOf(ii));

                litrajiCalculation();
            }
        });

        btn_KasriCount_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = edit_KasriCount.getText().toString();
                if (ss.equals("")){
                    ss="0";
                }
                int ii = Integer.valueOf(ss);
                ii++;
                edit_KasriCount.setText(String.valueOf(ii));

                litrajiCalculation();
            }
        });

        textViewAddBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ludis_shetana) {
                    textViewAddBeer.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop_up, 0);
                }else{
                    textViewAddBeer.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop_down, 0);
                }

                ludis_shetana = !ludis_shetana;
            }
        });

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(textView.getText()+" dfdfdg 9898");

                if (ludis_shetana){
                    editText.setVisibility(View.VISIBLE);
                    cardView.setCardElevation(R.dimen.hiElevation);
                }else{
                    editText.setVisibility(View.GONE);
                    cardView.setCardElevation(R.dimen.lowElevation);
                }
                ludis_shetana = !ludis_shetana;
            }
        });
    }

    private void litrajiCalculation() {
        if (edit_KasriCount.getText().toString().equals("")){
            edit_KasriCount.setText("1");
        }
        int count = Integer.valueOf(edit_KasriCount.getText().toString());
        int kasriSize = Constantebi.kasriTypeList.get(spinner_KasriType.getSelectedItemPosition()).getLitraji();
        edit_Litraji.setText(String.valueOf(kasriSize * count));
    }
}
