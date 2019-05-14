package in.co.santhoshiot.rigapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ownerHomeFragment extends Fragment   {
    String emp_id, emp_name;
    private RecyclerView recyclerView;
    private ArrayList<ownerHomeModel> ownerHomeModelArrayList;
    private LinearLayout linearLayout;
    AppCompatButton savedetail;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_home, container, false);
        recyclerView = view.findViewById(R.id.rv_bookmark);
        savedetail = view.findViewById(R.id.savedetail);
        linearLayout= view.findViewById(R.id.idForSaving);
        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        emp_id = OwnerHome.emp_id;
        emp_name = OwnerHome.emp_name;
        ownerHomeModelArrayList = populateList();
        ownerHomeAdapter ownerHomeAdapter = new ownerHomeAdapter(getActivity(), ownerHomeModelArrayList);
        recyclerView.setAdapter(ownerHomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), ownerHomeModelArrayList.get(position).getName() + " is clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), ownerHomeModelArrayList.get(position).getVno() + " is pressed!", Toast.LENGTH_SHORT).show();

            }
        }));
        savedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = saveBitMap(getActivity(), linearLayout);
            }
        });

        return view;
    }
    private File saveBitMap(Context context, View drawView){
        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Handcare");

        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if(!isDirectoryCreated)
                Log.i("ATG", "Can't create directory to save the image");
            return null;
        }
        String filename = pictureFileDir.getPath() +File.separator+ System.currentTimeMillis()+".jpg";
        File pictureFile = new File(filename);
        Bitmap bitmap =getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        scanGallery( context,pictureFile.getAbsolutePath());

        return pictureFile;
    }
    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
    // used for scanning gallery
    private void scanGallery(Context cntx, String path) {
        try {
            MediaScannerConnection.scanFile(cntx, new String[] { path },null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override

                public void onScanCompleted(String path, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<ownerHomeModel> populateList() {
        ArrayList<ownerHomeModel> list = new ArrayList<>();
        try {
            Connection con = null;
            ResultSet rs = null;
            Statement statement = null;
            String aa = "";

            try{
                con=DBconnection.sqlconn();
                statement = con.createStatement();
                String queryString = "SELECT asm.vid,asm.svid,tb_manager.name from tbl_assignmanager asm  JOIN tb_manager on asm.manager=tb_manager.Id WHERE asm.ownerid='" + OwnerHome.emp_id + "'  ";
                rs = statement.executeQuery(queryString);
                while(rs.next()){
                    ownerHomeModel om=new ownerHomeModel();
                    om.setVno(rs.getString("vid"));
                    om.setSvno(rs.getString("svid"));
                    om.setName(rs.getString("name"));
                    rs.getString("vid");
                    list.add(om);

                }
                //savedetail.setVisibility(View.VISIBLE);

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

        return list;
    }
}