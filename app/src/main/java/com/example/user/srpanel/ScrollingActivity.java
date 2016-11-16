package com.example.user.srpanel;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import io.github.kexanie.library.MathView;

public class ScrollingActivity extends AppCompatActivity {

    AppBarLayout appBarLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    RelativeLayout toolbarWrapper;
    MathView formulaOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        formulaOne = (MathView) findViewById(R.id.formula_one);

        formulaOne.config(
                "MathJax.Hub.Config({\n"+
                        "  CommonHTML: { linebreaks: { automatic: true } },\n"+
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n"+
                        "         SVG: { linebreaks: { automatic: true } }\n"+
                        "});");

        formulaOne.setText("$${-b \\pm \\sqrt{b^2-4ac} \\over 2a} + \\sum_{n=1}^{\\infty} \\frac{1+n}{\\sqrt{n^6}}-\n" +
                "                    \\sin{x^3+\\tan{\\cos{\\frac{1}{x-12}}}}$$");

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        toolbarWrapper = (RelativeLayout) findViewById(R.id.toolbarWrapper);

        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) appBarLayout.getLayoutParams();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int yPosition = (int)event.getY();
                Log.d("CURRENT_POS_Y", Float.toString(toolbarWrapper.getY()));

//                toolbar.setY(toolbar.getLocationOnScreen(int location[])+yPosition);

                if((int)toolbarWrapper.getY() + yPosition > 500){
                    params.height = (int)toolbarWrapper.getY() + yPosition;
                    appBarLayout.setLayoutParams(params);
                }

//                toolbarWrapper.setY(toolbarWrapper.getY() + yPosition);

                return false;
            }
        });

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(Color.parseColor("#cccccc"), Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
