package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

/**
 * Created by Job on 09.12.2016.
 */

public class BuildingListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BuildingListFragment();
    }


}