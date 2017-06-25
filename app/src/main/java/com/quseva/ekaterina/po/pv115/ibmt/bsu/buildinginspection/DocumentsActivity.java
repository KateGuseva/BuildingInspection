package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DocumentsActivity extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {



            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_documents);



            ListView listView = (ListView)findViewById(R.id.listView);
            // определяем массив типа String
            final String[] docNames = getResources().getStringArray(R.array.doc_names);

    // используем адаптер данных
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, docNames);

            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                        long id) {
                    switch (position){
                        case 0:
                            Intent intent  = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://zdom-proekt.by/SNB10302.pdf"));
                            startActivity(intent);
                        case 1:
                            Intent intent1  = new Intent();
                            intent1.setAction(Intent.ACTION_VIEW);
                            intent1.setData(Uri.parse("http://docs.cntd.ru/document/gost-5781-82"));
                            startActivity(intent1);
                        case 2:
                            Intent intent2  = new Intent();
                            intent2.setAction(Intent.ACTION_VIEW);
                            intent2.setData(Uri.parse("http://www.gosthelp.ru/text/GOST824097SHvellerystalny.html"));
                            startActivity(intent2);
                        case 3:
                            Intent intent3  = new Intent();
                            intent3.setAction(Intent.ACTION_VIEW);
                            intent3.setData(Uri.parse("http://www.gosthelp.ru/text/GOST850993Ugolkistalnyego.html"));
                            startActivity(intent3);
                        case 4:
                            Intent intent4  = new Intent();
                            intent4.setAction(Intent.ACTION_VIEW);
                            intent4.setData(Uri.parse("http://www.ohranatruda.ru/ot_biblio/normativ/data_normativ/1/1800/index.php"));
                            startActivity(intent4);
                    }
                }
            });


        }
}

