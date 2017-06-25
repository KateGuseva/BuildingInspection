package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class BuildingPrice extends AppCompatActivity {
    TextView tv_rez;
    EditText et_height;
    EditText et_hzdan;
    EditText et_ob;
    EditText et_etag;
    Button btn_count;
    Spinner spn;
    Spinner spn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_price);

        tv_rez=(TextView) findViewById(R.id.building_title_price);
        et_height=(EditText) findViewById(R.id.height_et);
        et_hzdan=(EditText) findViewById(R.id.height_zd);
        et_ob=(EditText) findViewById(R.id.height_ob);
        et_etag=(EditText) findViewById(R.id.height_etag);
        btn_count=(Button) findViewById(R.id.btn_count);

        spn = (Spinner) findViewById(R.id.spinner1);
        spn2 = (Spinner) findViewById(R.id.spinner2);

        View.OnClickListener oclBtnPrice = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               double num1=0;
                double num2=0;
                double num3=0;
                double num4=0;

                num1 = Double.parseDouble(et_height.getText().toString());
                num2 = Double.parseDouble( et_hzdan.getText().toString());
                num3 = Double.parseDouble( et_ob.getText().toString());
                num4 = Double.parseDouble( et_etag.getText().toString());

                // Проверяем поля на пустоту
                if (TextUtils.isEmpty(et_height.getText().toString())
                        || TextUtils.isEmpty(et_hzdan.getText().toString())
                        || TextUtils.isEmpty(et_ob.getText().toString())
                        || TextUtils.isEmpty(et_etag.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Введите все значения ",
                            Toast.LENGTH_LONG).show();
                }

                if (spn.getSelectedItemPosition()==1&spn2.getSelectedItemPosition()==1){
                    double result = 0.2*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }

                else if (spn.getSelectedItemPosition()==2 &spn2.getSelectedItemPosition()==1){
                    double result = 0.28*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==3 &spn2.getSelectedItemPosition()==1){
                    double result = 0.38*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==4&spn2.getSelectedItemPosition()==1){
                    double result = 0.3*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==5&spn2.getSelectedItemPosition()==1){
                    double result = 0.15*num1 * num2 * num3/num4;

                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==1&spn2.getSelectedItemPosition()==2){
                    double result = 0.5*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }

                else if (spn.getSelectedItemPosition()==2 &spn2.getSelectedItemPosition()==2){
                    double result = 0.58*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==3 &spn2.getSelectedItemPosition()==2){
                    double result = 0.78*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==4&spn2.getSelectedItemPosition()==2){
                    double result = 0.6*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+ " "+String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==5&spn2.getSelectedItemPosition()==2){
                    double result = 0.4*num1 * num2 * num3/num4;

                    tv_rez.setText("примерная стоимость"+" " +String.format( Locale.US, "%.2f", result)+" " + "руб");
                }

                else if (spn.getSelectedItemPosition()==1&spn2.getSelectedItemPosition()==3){
                    double result = 0.8*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }

                else if (spn.getSelectedItemPosition()==2 &spn2.getSelectedItemPosition()==3){
                    double result = 0.9*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==3 &spn2.getSelectedItemPosition()==3){
                    double result = 0.95*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+ " "+String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==4&spn2.getSelectedItemPosition()==3){
                    double result = 0.92*num1 * num2 * num3/num4;
                    tv_rez.setText("примерная стоимость"+ " "+String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
                else if (spn.getSelectedItemPosition()==5&spn2.getSelectedItemPosition()==3){
                    double result = 0.75*num1 * num2 * num3/num4;

                    tv_rez.setText("примерная стоимость"+" "+ String.format( Locale.US, "%.2f", result)+" " + "руб");
                }
            }
        };

        // присвоим обработчик кнопке OK (btnOk)
        btn_count.setOnClickListener(oclBtnPrice);






    }


}
