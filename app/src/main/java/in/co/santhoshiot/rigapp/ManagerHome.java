package in.co.santhoshiot.rigapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean isloggedin;
    public static String emp_id,emp_name,user_type;
    TextView navhead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        preferences = getSharedPreferences("rigapp", MODE_PRIVATE);
        editor = preferences.edit();
        isloggedin = preferences.getBoolean("islogged", false);
        emp_id = preferences.getString("emp_id", "0");
        emp_name = preferences.getString("emp_name", "0");
        user_type = preferences.getString("user_type", "user_type");
        Log.d("user Status", "emp name: " + emp_name + " emp id: " + emp_id + " type: " + user_type);
        Log.d("user isloggedin", "emp status: " + isloggedin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(0xFFFFFFFF);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view11);
        View headerView = navigationView.getHeaderView(0);
        navhead = headerView.findViewById(R.id.nh);
        navhead.setText(emp_name);
        navigationView.setNavigationItemSelectedListener(this);

        //add this line to display menu1 when the activity is loaded
        navigationView.setCheckedItem(R.id.nav_homess);
        Fragment fragment = new HomeFragment();
        displaySelectedFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_send) {
            editor.putString("emp_id", "");
            editor.putBoolean("islogged", false);
            editor.putString("emp_name", "");
            editor.putString("user_type", "");
            editor.commit();
            Intent in=new Intent(getApplicationContext(),Login.class);
            startActivity(in);
            Toast.makeText(ManagerHome.this, "SUCCESSFULLY LOGOUT", Toast.LENGTH_SHORT).show();
            System.exit(0);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedFragment(Fragment fragment) {



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame1, fragment);
        fragmentTransaction.commit();
        ManagerHome.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame1, fragment).commit();
    }

    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_homess) {
            fragment = new ManagerHomeFragment();
            Bundle data = new Bundle();//Use bundle to pass data

            displaySelectedFragment(fragment);
        } else if (id == R.id.addemp) {
            fragment = new AddEmployee();
            displaySelectedFragment(fragment);
        }else if (id == R.id.assemp) {
            fragment = new AssignEmployee();
            displaySelectedFragment(fragment);
        }  else if (id == R.id.areport) {
            fragment = new NewReport();
            displaySelectedFragment(fragment);
        }else if (id == R.id.assign) {
            fragment = new userManagement();
            displaySelectedFragment(fragment);
        }
        else if (id == R.id.psetting) {
            fragment = new PonitSetting();
            displaySelectedFragment(fragment);
        }
        else if (id == R.id.npoint) {
            fragment = new NewPoint();
            displaySelectedFragment(fragment);
        }
         else{
            fragment = new PonitSetting();
            displaySelectedFragment(fragment);
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame1, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}