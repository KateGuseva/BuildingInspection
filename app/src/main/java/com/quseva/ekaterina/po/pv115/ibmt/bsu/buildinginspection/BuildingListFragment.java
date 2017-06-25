package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.BuildingLab.get;

/**
 * Created by Job on 09.12.2016.
 */
public class BuildingListFragment extends Fragment
{
    private RecyclerView mBuildingRecyclerView;
    private BuildingAdapter mAdapter;
    private boolean mSubtitleVisible;
    private static final String SAVED_SUBTITLE_VISIBLE="subtitle";
    //получение обратных вызовов командного меню
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_building_list, container, false);
        mBuildingRecyclerView = (RecyclerView) view.findViewById(R.id.building_recycler_view);
        mBuildingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//назначается объект LayoutManager,который необходим лоя работы виджета
        if (savedInstanceState!=null){
            mSubtitleVisible=savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);

        }

        updateUI();
        return view;
    }
    //метод для перезагрузки элементов списка
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
    @Override
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_building_list, menu);
        MenuItem subtitleItem=menu.findItem(R.id.menu_item_1);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        }else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    //СОЗДАДИМ МЕТОД,КОТОРЫЙ БУДЕТ ЗАДАВАТЬ ПОДЗАГОЛОВОК ПАНЕЛИ ИНСТРУМЕНТОВ
    private void updateSubtitle(){
        BuildingLab buildingLab=BuildingLab.get(getActivity());
        int buildingCount=buildingLab.getBuilding().size();

        String subtitle=getString(R.string.subtitle_format, buildingCount);
        if(!mSubtitleVisible){
            subtitle=null;
        }
        AppCompatActivity activity=(AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);

    }

    //при выборе комманды меню добавить создается новый объект
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_new_building:
                Building building =new Building();
                BuildingLab.get(getActivity()).addBuilding(building);
                Intent intent=BuildingPagerActivity.newIntent(getActivity(), building.getId());
                startActivity(intent);
                return  true;
            case R.id.menu_item_2:
                Intent in=new Intent(getActivity(), BuildingPrice.class);
                startActivity(in);
                return true;
            case R.id.menu_item_3:
                Intent intent1=new Intent(getActivity(), DocumentsActivity.class);
                startActivity(intent1);
                return true;
            case R.id.menu_item_1:
                mSubtitleVisible=!mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
               updateSubtitle();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }

    }




    private void updateUI() {
        BuildingLab buildingLab = BuildingLab.get(getActivity());
        List<Building> buildings = buildingLab.getBuilding();
        if (mAdapter == null) {
            mAdapter = new BuildingAdapter(buildings);
            mBuildingRecyclerView.setAdapter(mAdapter);

        } else {
            mAdapter.setBuildings(buildings);
            mAdapter.notifyDataSetChanged();//обновление списка после добавления или редактирования  нового элемента
        }
        updateSubtitle();
    }


    private class BuildingHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private Building mBuilding;
        public TextView mTitleTextView;
        private TextView mDescriptionTextView;

        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public BuildingHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
           mTitleTextView=(TextView) itemView.findViewById(R.id.list_item_building_title_text_view);
            mDescriptionTextView=(TextView) itemView.findViewById(R.id.list_item_building_description_text_view);
            mDateTextView=(TextView) itemView.findViewById(R.id.list_item_building_date_text_view);
            mSolvedCheckBox=(CheckBox) itemView.findViewById(R.id.list_item_building_solved_check_box);

        }

        public void bindBuilding(Building building){
            mBuilding=building;
            mTitleTextView.setText(mBuilding.getTitle());
            mDescriptionTextView.setText(mBuilding.getDescription());
            mDescriptionTextView.setVisibility(View.INVISIBLE);
            mDateTextView.setText(mBuilding.getDate().toString());

            mSolvedCheckBox.setChecked(mBuilding.isSolved());
        }


        public void onClick(View v) {
            Intent intent=BuildingPagerActivity.newIntent(getActivity(),mBuilding.getId());
            startActivity(intent);

        }
    }

    private class BuildingAdapter extends RecyclerView.Adapter<BuildingHolder> {
        private List<Building> mBuildings;

        public BuildingAdapter(List<Building> buildings) {
            mBuildings = buildings;
        }

        //создаем объект View
        @Override
        public BuildingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_building, parent, false);
            return new BuildingHolder(view);
        }

        //связываем представление View объекта с объектом модели
        @Override
        public void onBindViewHolder(BuildingHolder holder, int position) {
            Building building = mBuildings.get(position);
            holder.bindBuilding(building);
        }

        @Override
        public int getItemCount() {
          return   mBuildings.size();

        }
        public void setBuildings(List<Building> buildings){
            mBuildings=buildings;
        }

    }

}



