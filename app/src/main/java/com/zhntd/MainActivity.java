package com.zhntd;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.david.myapplication.R;

import fragment.BlankFragment;
import fragment.ImageListFragment;

public class MainActivity extends Activity implements BlankFragment.OnFragmentInteractionListener,ImageListFragment.OnFragmentInteractionListener {
    private static final int icon_toolBar = R.mipmap.file;

    SearchView searchview;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("");
        searchview = (SearchView) findViewById(R.id.searchView);
        textView = (TextView) findViewById(R.id.textView);


        Drawable drawable = new ColorDrawable(Color.YELLOW);
        getActionBar().setBackgroundDrawable(drawable);
        getActionBar().setIcon(icon_toolBar);
        Fragment fragment = BlankFragment.newInstance(R.layout.fragment_blank,null);
        updateFragment(fragment);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_about:

                AlertDialog dialog = new AlertDialog.Builder(this).setTitle("About me")
                        .setMessage("I'm man!")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        }).show();
                break;
            case R.id.action_search:
                Fragment fragment_black = ImageListFragment.newInstance(R.layout.fragment_image_list,null);
                updateFragment(fragment_black);
                item.setVisible(false);
                textView.setVisibility(View.GONE);
                searchview.setVisibility(View.VISIBLE);
                getActionBar().setDisplayHomeAsUpEnabled(true);
                getActionBar().setIcon(null);
                break;
            case android.R.id.home:
                Fragment fragment_blank = BlankFragment.newInstance(R.layout.fragment_blank,null);
                updateFragment(fragment_blank);
                searchview.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                getActionBar().setDisplayHomeAsUpEnabled(false);
                getActionBar().setIcon(icon_toolBar);
                invalidateOptionsMenu();


            default:
                break;


        }
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void updateFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.blankfragment_container, fragment);
        transaction.commit();

    }
}
