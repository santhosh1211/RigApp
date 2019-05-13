package in.co.santhoshiot.rigapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.angmarch.views.NiceSpinner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AssignEmployee extends Fragment {
    AppCompatButton assign;
    TextView vst;
    NiceSpinner vsp,rds,rdss,cs,l1s,l2s,l3s,l4s,l5s;
    private LinearLayout layout;
    private ProgressDialog pDialog;
    boolean registerstatus = false;
    DBconnection dc;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assign_work, container, false);
        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        layout = view.findViewById(R.id.aslayout);
        assign=view.findViewById(R.id.assignemp);
        dc = new DBconnection();
        vsp=view.findViewById(R.id.vsp);
        vst=view.findViewById(R.id.vst);
        rds=view.findViewById(R.id.rds);
        rdss=view.findViewById(R.id.rdss);
        cs=view.findViewById(R.id.cs);
        l1s=view.findViewById(R.id.l1s);
        l2s=view.findViewById(R.id.l2s);
        l3s=view.findViewById(R.id.l3s);
        l4s=view.findViewById(R.id.l4s);
        l5s=view.findViewById(R.id.l5s);
        new loaddataforSpinner().execute();
        loaddataforSpinner1();
        loaddataforSpinner2();
        loaddataforSpinner3();
        loaddataforSpinner4();
        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssignemployeeMethod();
            }
        });
        vsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               setSupportVehichle();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
      
    
        return view;
    }

    private void AssignemployeeMethod() {
        try {
            Connection con = null;
            ResultSet rs = null;
            Statement statement = null;
            String aa = "";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {

                        PreparedStatement ps = con.prepareStatement("Insert into tbl_assignemployee(vno,vsno,rigdriver,supportdriver,cook,Labour1,Labour2,Labour3,Labour4,Labour5) Values(?,?,?,?,?,?,?,?,?,?)");
                        ps.setString(1, vsp.getSelectedItem().toString());
                        ps.setString(2, vst.getText().toString());
                        ps.setString(3, rds.getSelectedItem().toString());
                        ps.setString(4, rdss.getSelectedItem().toString());
                        ps.setString(5, cs.getSelectedItem().toString());
                        ps.setString(6, l1s.getSelectedItem().toString());
                        ps.setString(7, l2s.getSelectedItem().toString());
                        ps.setString(8, l3s.getSelectedItem().toString());
                        ps.setString(9, l4s.getSelectedItem().toString());
                        ps.setString(10, l5s.getSelectedItem().toString());
                        Log.e("Manager id", ManagerHome.emp_id);
                        int ig = ps.executeUpdate();
                        if (ig > 0) {
                          showsnackbar("Sucessfully Assign Employee");
                        } else {
                            showsnackbar("Failed to Assign Employee");
                        }


                }
                else{
                    showsnackbar("Connection Null");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    statement.close();
                    con.close();
                    rs.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }



    }

    private void setSupportVehichle() {

          String kk=dc.getSvID(vsp.getSelectedItem().toString());
        vst.setText(kk);
        Log.e("data ", kk);
//        try {
//            Connection con=null;
//            ResultSet rs1=null;
//            Statement statement1=null;
//            String aa="";
//            try{
//                con=DBconnection.sqlconn();
//                if (con!=null) {
//                    statement1 = con.createStatement();
//                      try{
//                    String queryString = "select svid from tbl_assignmanager where manager='"+vsp.getSelectedItem().toString()+"' ";
//                    rs1 = statement1.executeQuery(queryString);
//
//                        if (rs1.isFirst()) {
//
//                            String aas = rs1.getString("svid");
//                            Log.e("data ", aas);
//
//                        }
//                        else{
//                            Log.e("data ","not comes");
//                        }
//                    }
//                    catch (Exception ex){
//                        ex.printStackTrace();
//                    }
//
//                }
//                else{
//                    Log.e("connection","null");
//                }
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }finally {
//                try {
//                    statement1.close();
//                    con.close();
//                    rs1.close();
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        catch (Exception e){
//            Log.e("ggggg",e.toString());
//
//        }
    }

    private class loaddataforSpinner extends AsyncTask<String, String, Long> {
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @SuppressLint("WrongThread")
        @Override
        protected Long doInBackground(String... strings) {
            List<String> lables = dc.LoadVno();

            Log.e("list", lables.toString());
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.spinner_itemrigt, lables);

            // Drop down layout style - list view with radio button
            dataAdapter
                    .setDropDownViewResource(R.layout.spinner_itemrigt);

            // attaching data adapter to spinner
            vsp.setAdapter(dataAdapter);
            return null;
        }

        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.cancel();


            }
        }
    }
    private void loaddataforSpinner1() {

            List<String> lables = dc.LoadRSDri();

            Log.e("list", lables.toString());
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.spinner_itemrigt, lables);

            // Drop down layout style - list view with radio button
            dataAdapter
                    .setDropDownViewResource(R.layout.spinner_itemrigt);

            // attaching data adapter to spinner
        rdss.setAdapter(dataAdapter);

        }
    private void loaddataforSpinner2() {

        List<String> lables = dc.LoadRDri();

        Log.e("list", lables.toString());
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_itemrigt, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(R.layout.spinner_itemrigt);

        // attaching data adapter to spinner
        rds.setAdapter(dataAdapter);

    }
    private void loaddataforSpinner3() {

        List<String> lables = dc.LoadLabour();

        Log.e("list", lables.toString());
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_itemrigt, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(R.layout.spinner_itemrigt);

        // attaching data adapter to spinner
        l1s.setAdapter(dataAdapter);
        l2s.setAdapter(dataAdapter);
        l3s.setAdapter(dataAdapter);
        l4s.setAdapter(dataAdapter);
        l5s.setAdapter(dataAdapter);

    }
    private void loaddataforSpinner4() {

        List<String> lables = dc.LoadCook();

        Log.e("list", lables.toString());
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_itemrigt, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(R.layout.spinner_itemrigt);

        // attaching data adapter to spinner
        cs.setAdapter(dataAdapter);


    }
    public void showsnackbar(String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }

}
