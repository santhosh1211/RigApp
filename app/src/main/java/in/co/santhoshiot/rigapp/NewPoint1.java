package in.co.santhoshiot.rigapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class NewPoint1 extends Fragment {
    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, da, rwle, rcsp, rbbt, rbit11;
    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    Button submit;
    private LinearLayout layout;
    private ProgressDialog pDialog;
    boolean registerstatus = false;
    String[] array1, array2;
    int[] a1, a2;
    String s1, s2, s3, s4, s5, s6, s7;
    int v1 = 0, v2 = 0, v3 = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.point_setting, container, false);
        layout = view.findViewById(R.id.PNlayout);
        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        rwle = view.findViewById(R.id.rwle);
        rcsp = view.findViewById(R.id.rcsp);
        rbbt = view.findViewById(R.id.rbbt);
        rbit11 = view.findViewById(R.id.rbh11);
        da = view.findViewById(R.id.damount);
        e1 = view.findViewById(R.id.e1);
        e2 = view.findViewById(R.id.e2);
        e3 = view.findViewById(R.id.e3);
        e4 = view.findViewById(R.id.e4);
        e5 = view.findViewById(R.id.e5);
        e6 = view.findViewById(R.id.e6);
        e7 = view.findViewById(R.id.e7);
        e8 = view.findViewById(R.id.e8);
        e9 = view.findViewById(R.id.e9);
        e10 = view.findViewById(R.id.e10);
        r1 = view.findViewById(R.id.r1);
        r2 = view.findViewById(R.id.r2);
        r3 = view.findViewById(R.id.r3);
        r4 = view.findViewById(R.id.r4);
        r5 = view.findViewById(R.id.r5);
        r6 = view.findViewById(R.id.r6);
        r7 = view.findViewById(R.id.r7);
        r8 = view.findViewById(R.id.r8);
        r9 = view.findViewById(R.id.r9);
        r10 = view.findViewById(R.id.r10);
        t1 = view.findViewById(R.id.t1);
        t2 = view.findViewById(R.id.t2);
        t3 = view.findViewById(R.id.t3);
        t4 = view.findViewById(R.id.t4);
        t5 = view.findViewById(R.id.t5);
        t6 = view.findViewById(R.id.t6);
        t7 = view.findViewById(R.id.t7);
        t8 = view.findViewById(R.id.t8);
        t9 = view.findViewById(R.id.t9);
        t10 = view.findViewById(R.id.t10);


        submit = view.findViewById(R.id.pssummit);
        e1.addTextChangedListener(e1Watcher);
        e2.addTextChangedListener(e2Watcher);
        e3.addTextChangedListener(e3Watcher);
        e4.addTextChangedListener(e4Watcher);
        e5.addTextChangedListener(e5Watcher);
        e6.addTextChangedListener(e6Watcher);
        e7.addTextChangedListener(e7Watcher);
        e8.addTextChangedListener(e8Watcher);
        e9.addTextChangedListener(e9Watcher);
        e10.addTextChangedListener(e10Watcher);

        new RemoteDataTask1().execute();
        //readdata();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteDataTask().execute();
            }
        });
        return view;
    }

    private void readdata() {
        try {
            Connection con1 = null;
            ResultSet rs1 = null;
            Statement statement1 = null;
            String aa = "";
            try {
                con1 = DBconnection.sqlconn();
                if (con1 != null) {
                    statement1 = con1.createStatement();

                    String queryString = "select * from tbl_rigpoint where id='" + ManagerHome.emp_id + "'  ";
                    rs1 = statement1.executeQuery(queryString);
                    if (rs1.next()) {
                        s1 = rs1.getString("distance");
                        s2 = rs1.getString("rate");
                        s3 = rs1.getString("deiselrate");
                        s4 = rs1.getString("weldingrate");
                        s5 = rs1.getString("casingpiperate");
                        s6 = rs1.getString("borebatarate");
                        s7 = rs1.getString("bit_hammer_11");
                        Log.e("array1", s1);
                        Log.e("array2", s2);
                        array1 = s1.split(",");
                        array2 = s2.split(",");
                        Log.e("array1 s1", "" + array1[0]);
                        Log.e("array2 s2", "" + array2[5]);

                        if(!s3.equalsIgnoreCase("")){
                            da.setText(s3);
                        }
                        if(!s4.equalsIgnoreCase("")){
                            rwle.setText(s4);
                        }
                        if(!s5.equalsIgnoreCase("")){
                            rcsp.setText(s5);
                        }
                        if(!s6.equalsIgnoreCase("")){
                            rbbt.setText(s6);
                        }
                        if(!s7.equalsIgnoreCase("")){
                            rbit11.setText(s7);
                        }

                        for (int j = 0; j < array1.length; j++) {
                            a1[j] = Integer.parseInt(array1[j]);
                        }
                        for (int jj = 0; jj < array2.length; jj++) {
                            a2[jj] = Integer.parseInt(array2[jj]);
                        }
                        Log.e("int array1 leath", "" + a1[5]);
                        Log.e("int array2 leath", "" + a2[5]);
                        for (int i = 0; i < array1.length; i++) {
                            if (Integer.parseInt(array1[i]) > Integer.parseInt(e1.getText().toString())) {
                                v1 = (Integer.parseInt(array1[i + 1]) - Integer.parseInt(e1.getText().toString())) * (Integer.parseInt(array2[i]));
                                //   s1= Integer.parseInt(array1[i+1])- Integer.parseInt(array1[i])*(Integer.parseInt(array2[i]));

                            } else {

                                v2 = (Integer.parseInt(array1[i]) - Integer.parseInt(e1.getText().toString())) * (Integer.parseInt(array2[i]));

                                //(Integer.parseInt(array1[i])-Integer.parseInt(e1.getText().toString()))*(Integer.parseInt(array2[i]);
                            }
                        }
                        Log.e("v1", v1 + "");
                        Log.e("v2", v2 + "");
                    }

                    if (!s1.equalsIgnoreCase("")) {
                        array1 = s1.split(",");

                    }
                    if (!s2.equalsIgnoreCase("")) {
                        array2 = s2.split(",");
                    }


                } else {
                    showsnackbar("Connection Null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    statement1.close();
                    con1.close();
                    rs1.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.e("ggggg", e.toString());

        }
    }

    private final TextWatcher e1Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t2.setText(e1.getText().toString());

        }
    };
    private final TextWatcher e2Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t3.setText(e2.getText().toString());
        }
    };
    private final TextWatcher e3Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t4.setText(e3.getText().toString());
        }
    };
    private final TextWatcher e4Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t5.setText(e4.getText().toString());
        }
    };
    private final TextWatcher e5Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t6.setText(e5.getText().toString());
        }
    };
    private final TextWatcher e6Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t7.setText(e6.getText().toString());
        }
    };
    private final TextWatcher e7Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t8.setText(e7.getText().toString());
        }
    };
    private final TextWatcher e8Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t9.setText(e8.getText().toString());
        }
    };
    private final TextWatcher e9Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            t10.setText(e9.getText().toString());
        }
    };
    private final TextWatcher e10Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //t11.setText(e10.getText().toString());
        }
    };

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            addENTRYPOINT();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.cancel();
                if (registerstatus) {
                    showsnackbar("Sucessfully Added");
                } else {
                    showsnackbar("Failed to Added");
                }
            }
        }


    }

    private class RemoteDataTask1 extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            readdata();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.cancel();

            }
        }


    }

    private void addENTRYPOINT() {
        try {
            Connection con = null;
            ResultSet rs = null;
            Statement statement = null;
            String aa = "";
            try {
                con = DBconnection.sqlconn();
                if (con != null) {
                    statement = con.createStatement();

                    PreparedStatement ps = con.prepareStatement("Insert into tbl_rigpoint(id,distance,rate,deiselrate,weldingrate,casingpiperate,borebatarate,bit_hammer_11) Values(?,?,?,?,?,?,?,?)");
                    ps.setString(1, ManagerHome.emp_id);
                    ps.setString(2, "0," + e1.getText().toString() + "," + e2.getText().toString() + "," + e3.getText().toString() + "," + e4.getText().toString() + "," + e5.getText().toString() + "," + e6.getText().toString() + "," + e7.getText().toString() + "," + e8.getText().toString() + "," + e9.getText().toString() + "," + e10.getText().toString());
                    ps.setString(3, "0," + r1.getText().toString() + "," + r2.getText().toString() + "," + r3.getText().toString() + "," + r4.getText().toString() + "," + r5.getText().toString() + "," + r6.getText().toString() + "," + r7.getText().toString() + "," + r8.getText().toString() + "," + r9.getText().toString() + "," + r10.getText().toString());
                    ps.setString(4, da.getText().toString());
                    ps.setString(5, rwle.getText().toString());
                    ps.setString(6, rcsp.getText().toString());
                    ps.setString(7, rbbt.getText().toString());
                    ps.setString(8, rbit11.getText().toString());
                    Log.e("Manager id", ManagerHome.emp_id);
                    int ig = ps.executeUpdate();
                    if (ig > 0) {
                        registerstatus = true;
                    } else {
                        registerstatus = false;
                    }

                } else {
                    showsnackbar("Connection Null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    con.close();
                    rs.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.e("ggggg", e.toString());

        }
    }

    public void showsnackbar(String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }
}
