package com.example.muertedecuna;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.muertedecuna.tools.Commons;
import com.google.android.material.tabs.TabLayout;


public class ActivityMain extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tos) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case Commons.IDX_SECTION1:
                    if(getIntent().getExtras() != null) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("ITEM", getIntent().getParcelableExtra("ITEM"));
                        Fragment fragment = new FragmentHome();
                        fragment.setArguments(bundle);
                        return fragment;
                    } else
                        return new FragmentHome();
                case Commons.IDX_SECTION2:
                    return new FragmentMetrics();
                case Commons.IDX_SECTION3:
                    return new FragmentHistoric();
                default:
                    return new FragmentHome();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return Commons.PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case Commons.IDX_SECTION1: return getString(R.string.section1).toUpperCase();
                case Commons.IDX_SECTION2: return getString(R.string.section2).toUpperCase();
                case Commons.IDX_SECTION3: return getString(R.string.section3).toUpperCase();
            }
            return null;
        }
    }
}
