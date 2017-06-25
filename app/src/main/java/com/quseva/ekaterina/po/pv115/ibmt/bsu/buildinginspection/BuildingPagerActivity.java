package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import static com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.BuildingLab.get;
import java.util.List;
import java.util.UUID;

/**
 * Created by Job on 10.12.2016.
 */

public class BuildingPagerActivity extends AppCompatActivity {
    private static  final String EXTRA_BUILDING_ID="com.guseva.ekatetrina.po.pv115.ibmt.bsu.buildinginspection.building_id";

    private ViewPager mViewPager;
    private List<Building> mBuildings;
    public static Intent newIntent(Context packageContext, UUID buildingId){
        Intent intent=new Intent(packageContext, BuildingPagerActivity.class);
        intent.putExtra(EXTRA_BUILDING_ID, buildingId);
        return intent;
    }

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_pager);
        UUID buildingId=(UUID) getIntent().getSerializableExtra(EXTRA_BUILDING_ID);

        mViewPager=(ViewPager) findViewById(R.id.activity_building_pager_view_pager);
        mBuildings=BuildingLab.get(this).getBuilding();
        FragmentManager fragmentManager=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            //FragmentStatePagerAdapter спец класс управляющий взаимодействием  с ViewPager
            @Override
            public Fragment getItem(int position) {//для позиции в массиве событий возвращается объект BuildingsFragment
                //настроенный для вывода инфо о событии в заданной позиции
                Building building=mBuildings.get(position);
                return BuildingFragment.newInstance(building.getId());



            }
            @Override
            public int getCount() {//текущее кол-во элементов в списке
                return mBuildings.size();
            }


        });
        //для того,чтобы ViewPager отображал выбранный элемент списка
        for (int i=0;i<mBuildings.size();i++){
            if(mBuildings.get(i).getId().equals(buildingId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }



    }

}
