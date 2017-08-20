package com.benishay.danny.taskmanagerdanny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.benishay.danny.taskmanagerdanny.Data.DBUtils;
import com.benishay.danny.taskmanagerdanny.Data.MyGroup;
import com.benishay.danny.taskmanagerdanny.Data.MyTasks;
import com.benishay.danny.taskmanagerdanny.MainFragments.MyGroupsFragment;
import com.benishay.danny.taskmanagerdanny.MainFragments.MyTasksFragment;
import com.benishay.danny.taskmanagerdanny.MainFragments.Titleable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private MyPagerAdapter mMyPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new Fragment[2];
        fragments[0] = new MyTasksFragment();
        fragments[1] = new MyGroupsFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mMyPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "BEFORE...", Toast.LENGTH_LONG).show();
                MyGroup myGroup = new MyGroup();
                myGroup.setMngrUkey(DBUtils.auth.getCurrentUser().getEmail());
                myGroup.setName("g"+System.currentTimeMillis());
                myGroup.addUserKey(myGroup.getMngrUkey().replace('.','*'));

                myGroup.setgKey(DBUtils.myGroupsRef.push().getKey());


                DBUtils.myGroupsRef.child(myGroup.getgKey()).setValue(myGroup);
                //Toast.makeText(MainActivity.this, "AFTER...", Toast.LENGTH_LONG).show();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                Intent intent = new Intent(getBaseContext(), AddGroupActivity.class);
//                startActivity(intent);

//                final MyTasks myTasks = new MyTasks();
//                myTasks.setCreateAt(System.currentTimeMillis());
//                myTasks.setText("todo "+System.currentTimeMillis());
//                myTasks.setCompleted(false);
//                myTasks.setAddress("Haifa");
//                myTasks.setgKey("group 1");
//                myTasks.setuKey(DBUtils.auth.getCurrentUser().getEmail());
//                myTasks.settKey(DBUtils.myTasksRef.push().getKey());
//                DBUtils.myTasksRef.child(myTasks.gettKey()).setValue(myTasks).
//                        addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(MainActivity.this, myTasks.getText()+" added",
//                                            Toast.LENGTH_LONG).show();
//                                }
//                                else {
//                                    Toast.makeText(MainActivity.this, myTasks.getText()+ " failed",
//                                            Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if(id == R.id.mnItmSignout) {
            DBUtils.auth.signOut();
            finish(); // to close this activity
            //another option is to return to the Login activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        //// TODO: 16/08/2017 change Tab' title
        @Override
        public CharSequence getPageTitle(int position) {
            return ((Titleable)fragments[position]).getTitle();
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
//            return null;
        }
    }
}
