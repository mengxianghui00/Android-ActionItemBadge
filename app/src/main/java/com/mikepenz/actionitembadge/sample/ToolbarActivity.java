package com.mikepenz.actionitembadge.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.ui.LibsFragment;
import com.mikepenz.actionitembadge.R;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.iconics.typeface.FontAwesome;


public class ToolbarActivity extends ActionBarActivity {
    private int badgeCount = 10;

    private static final int SAMPLE2_ID = 34535;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        /*
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayUseLogoEnabled(true);
            ab.setTitle("");
            ab.show();
        }
        */

        //init and show about libraries :D
        LibsFragment fragment = new Libs.Builder().withFields(R.string.class.getFields()).withVersionShown(true).withLicenseShown(true).fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        if (badgeCount > 0) {
            ActionItemBadge.update(this, menu.findItem(R.id.item_samplebadge), FontAwesome.Icon.faw_android, ActionItemBadge.BadgeStyle.DARKGREY, badgeCount);
        } else {
            ActionItemBadge.hide(menu.findItem(R.id.item_samplebadge));
        }

        new ActionItemBadge.Add().act(this).menu(menu).title(R.string.sample_2).itemDetails(0, SAMPLE2_ID, 1).showAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS).build(ActionItemBadge.BadgeStyle.BLUE_LARGE, 1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.item_samplebadge) {
            Toast.makeText(this, R.string.sample_3, Toast.LENGTH_SHORT).show();
            badgeCount--;
            invalidateOptionsMenu();
            return true;
        } else if (id == SAMPLE2_ID) {
            Toast.makeText(this, R.string.sample_4, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
