package in.co.santhoshiot.rigapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import org.angmarch.views.NiceSpinner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssignManager extends Fragment {
    NiceSpinner mgr;
    AppCompatButton assign;
    TextInputEditText vid, svid;
    DBconnection dc;
    private LinearLayout layout;
    private ProgressDialog pDialog;
    boolean registerstatus = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assign_manager, container, false);
        layout = view.findViewById(R.id.reg_layout);
        dc = new DBconnection();
        mgr = view.findViewById(R.id.managerspinner);
        assign = view.findViewById(R.id.addmanager);
        vid = view.findViewById(R.id.vno);
        svid = view.findViewById(R.id.svno);
        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
      //.  loadSpinnerData();
        new loaddataforSpinner().execute();

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignManager();
            }
        });

        return view;
    }

    private void assignManager() {
        String mid = dc.getID(mgr.getSelectedItem().toString());
        new RemoteDataTask().execute(mid);


    }

    private void loadSpinnerData() {
        try {


            List<String> lables = dc.LoadManager();
            ;
            Log.e("list", lables.toString());
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, lables);

            // Drop down layout style - list view with radio button
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            mgr.setAdapter(dataAdapter);

        } catch (Exception e) {
            Log.e("ggggg", e.toString());

        }
    }



    private class RemoteDataTask extends AsyncTask<String, String, Long> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Long doInBackground(String... params) {
            String s = params[0];
            assignuser(s);
            return null;
        }

        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.cancel();
                if (registerstatus) {
                    showsnackbar("Sucessfully Assign");
                    svid.setText("");
                    vid.setText("");


                } else {
                    showsnackbar("Not Assign");
                }
            }
        }

        private void assignuser(String s) {
            try {
                Connection con = null;
                String aa = "";
                ResultSet rs=null;
                Statement statement=null;
                try {
                    con = DBconnection.sqlconn();

                    PreparedStatement ps = con.prepareStatement("Insert into tbl_assignmanager(vid,svid,manager,ownerid) Values(?,?,?,?)");
                    ps.setString(1, vid.getText().toString());
                    ps.setString(2, svid.getText().toString());
                    ps.setString(3, s);
                    ps.setString(4, OwnerHome.emp_id);

                    Log.e("owner id", OwnerHome.emp_id);
                    int ig = ps.executeUpdate();
                    if (ig > 0) {
                        registerstatus = true;
                    } else {
                        registerstatus = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("error", e.toString());
                } finally {
                    try {

                        con.close();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                Log.e("ggggg", e.toString());

            }
        }
    }

    private class loaddataforSpinner extends AsyncTask<String, String, Long>{
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
            List<String> lables = dc.LoadManager();

            Log.e("list", lables.toString());
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, lables);

            // Drop down layout style - list view with radio button
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            mgr.setAdapter(dataAdapter);
            return null;
        }
        @Override
        protected void onPostExecute(Long result) {
            if (pDialog.isShowing()) {
                pDialog.cancel();
            }
        }
    }
    public void showsnackbar(String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }

}

