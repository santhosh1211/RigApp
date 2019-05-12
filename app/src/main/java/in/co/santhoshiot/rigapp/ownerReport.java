package in.co.santhoshiot.rigapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ownerReport extends Fragment {
    String emp_id,emp_name;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_chat, container, false);
        emp_id=OwnerHome.emp_id;
        emp_name=OwnerHome.emp_name;
        return view;
    }
}
