package in.co.santhoshiot.rigapp;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 24-02-2018.
 */

public class DBconnection {

    DBconnection(){

    }
    public static Connection sqlconn(){
        Connection conn = null;
        try {



            Class.forName("com.mysql.jdbc.Driver").newInstance();
                      conn= DriverManager.getConnection(
                    "jdbc:mysql://103.207.4.72:3306/restdb"+
                              "?verifyServerCertificate=false"+
                                      "&useSSL=true"+
                                      "&requireSSL=true","sandydb1234","sandydb1234");
            Log.e("gggg","connect");
        }catch (Exception e){
            Log.e("gggg",e.toString());
        }

        return conn;
    }


    public List<String> LoadManager(){
        List<String> labels = new ArrayList<String>();
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;
            String aa="";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select Name from tb_manager where AddOwner='"+OwnerHome.emp_id+"' ";
                    Log.e("query","select Name from tb_manager where AddOwner='"+OwnerHome.emp_id+"'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){


                        labels.add(rs.getString("Name"));
                        Log.e("Manger Name",rs.getString("Name"));

                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        return labels;
    }

    public String getID(String emp_id) {
        String aa="";
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;

            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select * from tb_manager where name='"+emp_id+"' ";
                    Log.e("select manager","select * from tb_manager where name='"+emp_id+"'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){
                     aa=rs.getString("Id");
                     Log.e("id",aa);
                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        Log.e("id return",aa);
        return aa;
    }

    public String getSvID(String emp_id) {
        String aa="";
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;

            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select svid from tbl_assignmanager where vid='"+emp_id+"' ";
                    Log.e("select manager","select svid from tbl_assignmanager where vid='"+emp_id+"'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){
                        aa=rs.getString("svid");
                        Log.e("id",aa);
                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        Log.e("id return",aa);
        return aa;
    }




    public List<String> LoadVno(){
        List<String> labels = new ArrayList<String>();
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;
            String aa="";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select vid from tbl_assignmanager where manager='"+ManagerHome.emp_id+"' ";
                    Log.e("query","select vid from tbl_assignmanager where manager='"+ManagerHome.emp_id+"'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){


                        labels.add(rs.getString("vid"));
                        Log.e("Manger Name",rs.getString("vid"));

                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        return labels;
    }
    public List<String> LoadRDri(){
        List<String> labels = new ArrayList<String>();
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;
            String aa="";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select Name from tb_employee where assignby='"+ManagerHome.emp_id+"' and designation='Driver'";
                    Log.e("query","select Name from tb_employee where assignby='"+ManagerHome.emp_id+"' and designation='Driver'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){


                        labels.add(rs.getString("Name"));
                        Log.e("Manger Name",rs.getString("Name"));

                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        return labels;
    }
    public List<String> LoadRSDri(){
        List<String> labels = new ArrayList<String>();
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;
            String aa="";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select Name from tb_employee where assignby='"+ManagerHome.emp_id+"' and designation='Support Driver'";
                    Log.e("query","select Name from tb_employee where assignby='"+ManagerHome.emp_id+"' and designation='Support Driver'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){


                        labels.add(rs.getString("Name"));
                        Log.e("Manger Name",rs.getString("Name"));

                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        return labels;
    }
    public List<String> LoadLabour(){
        List<String> labels = new ArrayList<String>();
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;
            String aa="";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select Name from tb_employee where assignby='"+ManagerHome.emp_id+"' and designation='Labour'";
                    Log.e("query","select Name from tb_employee where assignby='"+ManagerHome.emp_id+"' and designation='Labour'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){


                        labels.add(rs.getString("Name"));
                        Log.e("Manger Name",rs.getString("Name"));

                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        return labels;
    }
    public List<String> LoadCook(){
        List<String> labels = new ArrayList<String>();
        try {
            Connection con=null;
            ResultSet rs=null;
            Statement statement=null;
            String aa="";
            try{
                con=DBconnection.sqlconn();
                if (con!=null) {
                    statement = con.createStatement();
                    String queryString = "select Name from tb_employee where assignby='"+ManagerHome.emp_id+"' and designation='Cook'";
                    Log.e("query","select Name from tb_employee where assignby='"+ManagerHome.emp_id+"' and designation='Cook'");
                    rs = statement.executeQuery(queryString);
                    while(rs.next()){


                        labels.add(rs.getString("Name"));
                        Log.e("Manger Name",rs.getString("Name"));

                    }
                }
                else{
                    Log.e("connection","null");
                }
            }
            catch (Exception e){
                Log.e("ggggg",e.toString());

            }
        }
        catch (Exception e){
            Log.e("ggggg",e.toString());

        }
        return labels;
    }

}
