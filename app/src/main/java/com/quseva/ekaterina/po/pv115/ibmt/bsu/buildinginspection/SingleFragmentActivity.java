package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Job on 09.12.2016.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected  abstract Fragment createFragment();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        //передаем фрагмент менеджеру для управления
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);//у менеджера фрагментов
        // запрашивается фрагмент с идентификатором контейнерного представления
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()//создание и закрепление транзакции фрагментов(добавление,удаление,замены фрагментов в списке фрагментов
                    .add(R.id.fragmentContainer, fragment)// Идентификатор сообщает менеджеру
                    //где в представлении активнеости должно находиться представление фрагмента
                    .commit();
        }
    }
}
