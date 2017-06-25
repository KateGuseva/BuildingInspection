package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static android.R.attr.data;

/**Выдача подробной инфо об объекте
 * Created by Job on 08.12.2016.
 */

public class BuildingFragment extends Fragment {
    private static final String ARG_BUILDING_ID = "building_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 1;


    private Building mBuilding;
    private File mPhotoFile;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mReportButton;
    private Button mCallButton;
    private ImageButton mPhotoButton;



    //данный метод получает ,создает пакет аргументовбсоздает экземпляр фрагмента,а затем присоединяет аргументы к фрагменту
    public static BuildingFragment newInstance(UUID building_id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BUILDING_ID, building_id);
        BuildingFragment fragment = new BuildingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID buildingId = (UUID) getArguments().getSerializable(ARG_BUILDING_ID);
        mBuilding = BuildingLab.get(getActivity()).getBuilding(buildingId);
        mPhotoFile = BuildingLab.get(getActivity()).getPhotoFile(mBuilding);



    }

    @Override
    public void onPause() {
        super.onPause();
        BuildingLab.get(getActivity()).updateBuilding(mBuilding);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    //создание и настройка представления фрагмента
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_building, container, false);
        mTitleField = (EditText) v.findViewById(R.id.building_title);
        mTitleField.setText(mBuilding.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBuilding.setTitle((s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDescriptionField = (EditText) v.findViewById(R.id.building_description);
        mDescriptionField.setText(mBuilding.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {//реакция виджета на ввод пользователя
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBuilding.setDescription(s.toString());//метод возвращает строку
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = (Button) v.findViewById(R.id.building_date);
        //coздание строки для отбражения даты!!!!!!!!!!!
        String formattedDate = new SimpleDateFormat("EEEE,MMM d,yyyy.").format(Calendar.getInstance().getTime());

        mDateButton.setText(formattedDate);




        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mBuilding.getDate());
                dialog.setTargetFragment(BuildingFragment.this, REQUEST_DATE);//НАЗНАЧАЕМ CrimeFragment ЦЕЛЕВЫМ ФРАГМЕНТОМ datePickerFragment
                dialog.show(manager, DIALOG_DATE);
            }

        });
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.building_solved);
        mSolvedCheckBox.setChecked(mBuilding.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBuilding.setSolved(isChecked);
            }
        });

        mReportButton = (Button) v.findViewById(R.id.building_report);
        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getBuildingReport());
                i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.building_report_subject));
                startActivity(i);


            }
        });

        mCallButton = (Button) v.findViewById(R.id.building_call);
        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                startActivity(i);
            }
        });

        mPhotoButton = (ImageButton) v.findViewById(R.id.building_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null;
        mPhotoButton.setEnabled(canTakePhoto);

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mBuilding.setDate(date);
            mDateButton.setText(mBuilding.getDate().toString());
        }
    }

    private String getBuildingReport() {
        String report = getString(R.string.building_report, mBuilding.getTitle(), mBuilding.getDescription(), mBuilding.getDate());
        return report;


    }
    @Override

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_building, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_building:
                UUID crimeId = mBuilding.getId();
                BuildingLab.get(getActivity()).deleteCrime(crimeId);

                Toast.makeText(getActivity(), R.string.toast_delete_building, Toast.LENGTH_LONG).show();
                getActivity().finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
