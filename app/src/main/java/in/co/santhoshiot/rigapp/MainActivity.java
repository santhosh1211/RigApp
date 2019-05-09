package in.co.santhoshiot.rigapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;

public class MainActivity extends AppCompatActivity {
    PieChart pieChart;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean isloggedin;
    public static String emp_id,emp_name,user_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences=getSharedPreferences("rigapp",MODE_PRIVATE);
        editor=preferences.edit();
        isloggedin=preferences.getBoolean("islogged",false);
        emp_id=preferences.getString("emp_id","0");
        emp_name=preferences.getString("emp_name","0");
        user_type=preferences.getString("emp_name","user_type");
        Log.d("user Status","emp name: "+emp_name+" emp id: "+emp_id+" type: "+user_type);
        Log.d("user isloggedin","emp status: "+isloggedin);

    }
}
