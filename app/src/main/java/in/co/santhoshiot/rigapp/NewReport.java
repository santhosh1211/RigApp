package in.co.santhoshiot.rigapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class NewReport extends Fragment {
    AppCompatButton rsubmit;
    static TextView stime,etime,sdate,edate;
    EditText pno,place,advance,aname,aphn,saname,samno,tdepth,diesel,srmp,crmp,trmp,drill,drillname,mname,hbalance,bitbalance,dieselbalance,total,totaladvance,totalbalance,remarks;
    boolean registerstatus = false;
    private LinearLayout layout;
    private ProgressDialog pDialog;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_report, container, false);
        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        layout=view.findViewById(R.id.nrlayout);
        rsubmit=view.findViewById(R.id.rsummit);
        stime=view.findViewById(R.id.stime);
        etime=view.findViewById(R.id.etime);
        sdate=view.findViewById(R.id.sdate);
        edate=view.findViewById(R.id.edate);
        pno=view.findViewById(R.id.pno);
        place=view.findViewById(R.id.place);
        advance=view.findViewById(R.id.advance);
        aname=view.findViewById(R.id.aname);
        aphn=view.findViewById(R.id.aphn);
        saname=view.findViewById(R.id.saname);
        samno=view.findViewById(R.id.samno);
        tdepth=view.findViewById(R.id.tdepth);
        diesel=view.findViewById(R.id.diesel);
        srmp=view.findViewById(R.id.srmp);
        crmp=view.findViewById(R.id.crmp);
        trmp=view.findViewById(R.id.trmp);
        drill=view.findViewById(R.id.drill);
        drillname=view.findViewById(R.id.drillname);
        mname=view.findViewById(R.id.mname);
        hbalance=view.findViewById(R.id.hbalance);
        bitbalance=view.findViewById(R.id.bitbalance);
        dieselbalance=view.findViewById(R.id.dieselbalance);
        total=view.findViewById(R.id.total);
        totaladvance=view.findViewById(R.id.totaladvance);
        totalbalance=view.findViewById(R.id.totalbalance);
        remarks=view.findViewById(R.id.remarks);


        rsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewReportManager().execute();
            }
        });


        sdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "From Date");

            }
        });

      edate.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment1();
                newFragment.show(getFragmentManager(), "To Date");

            }
        });
        stime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectTimeFragment();
                newFragment.show(getFragmentManager(), "From Date");

            }
        });

        etime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectTimeFragment1();
                newFragment.show(getFragmentManager(), "To Date");

            }
        });
        return view;
    }

    @SuppressLint("ValidFragment")
    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {

            sdate.setText(day+"/"+month+"/"+year);
        }

    }
    @SuppressLint("ValidFragment")
    public static class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(),this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minut) {
            stime.setText( String.valueOf(hourOfDay)+ ":" + String.valueOf(minut));

        }
    }
    @SuppressLint("ValidFragment")
    public static class SelectTimeFragment1 extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(),this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minut) {
            etime.setText( String.valueOf(hourOfDay)+ ":" + String.valueOf(minut));

        }
    }
    @SuppressLint("ValidFragment")
    public static class SelectDateFragment1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            int setdate=day;

            edate.setText(day+"/"+month+"/"+year);
        }

    }

    private class NewReportManager extends AsyncTask<String, String, Long> {

        protected void onPreExecute () {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Long doInBackground (String...params){
           reportAdd();
            return null;
        }

        protected void onPostExecute (Long result){
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.cancel();
                if (registerstatus) {
                    showsnackbar("Sucessfully Report Submit");


                } else {
                    showsnackbar("Report Not Submit");
                }
            }
        }
    }

    private void reportAdd() {
        try {
            Connection con = null;
            String aa = "";
            ResultSet rs=null;
            Statement statement=null;
            try {
                con = DBconnection.sqlconn();

                PreparedStatement ps = con.prepareStatement("Insert into tbl_managerreport(sdate,edate,pointno,place,advance,agentname,aggentmno,subaggent,subaggentmno,stime,ctime,totaldeepth,deciel," +
                        "srpm,crpm,trpm,drilavg,drillername,managername,hummerbalance,bitbalanc,decielbalance,total,totaladvance,totalbalance,remarks,mid) Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, sdate.getText().toString());
                ps.setString(2, edate.getText().toString());
                ps.setString(3, pno.getText().toString());
                ps.setString(4, place.getText().toString());
                ps.setString(5, advance.getText().toString());
                ps.setString(6, aname.getText().toString());
                ps.setString(7, aphn.getText().toString());
                ps.setString(8, saname.getText().toString());
                ps.setString(9, samno.getText().toString());
                ps.setString(10, stime.getText().toString());
                ps.setString(11, etime.getText().toString());
                ps.setString(12, tdepth.getText().toString());
                ps.setString(13, diesel.getText().toString());
                ps.setString(14, srmp.getText().toString());
                ps.setString(15, crmp.getText().toString());
                ps.setString(16, trmp.getText().toString());
                ps.setString(17, drill.getText().toString());
                ps.setString(18, drillname.getText().toString());
                ps.setString(19, mname.getText().toString());
                ps.setString(20, hbalance.getText().toString());
                ps.setString(21, bitbalance.getText().toString());
                ps.setString(22, dieselbalance.getText().toString());
                ps.setString(23, total.getText().toString());
                ps.setString(24, totaladvance.getText().toString());
                ps.setString(25, totalbalance.getText().toString());
                ps.setString(26, remarks.getText().toString());
                ps.setString(27, ManagerHome.emp_id);
                Log.e("query", ps.toString());
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

    public void showsnackbar(String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }
}
