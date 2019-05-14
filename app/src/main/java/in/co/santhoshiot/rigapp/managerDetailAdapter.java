package in.co.santhoshiot.rigapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class managerDetailAdapter extends  RecyclerView.Adapter<managerDetailAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<managerDetail> mdModels;

    public managerDetailAdapter(Context ctx, ArrayList<managerDetail> mdModels) {
        inflater = LayoutInflater.from(ctx);
        this.mdModels = mdModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.manager_detail, viewGroup, false);
        managerDetailAdapter.MyViewHolder holder = new managerDetailAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.vno.setText(mdModels.get(i).getVno());
        myViewHolder.svno.setText(mdModels.get(i).getSvno());
        myViewHolder.vnoloc.setText(mdModels.get(i).getVnoloc());
        myViewHolder.svnoloc.setText(mdModels.get(i).getSvnoloc());
        myViewHolder.name.setText(mdModels.get(i).getName());
        myViewHolder.ph.setText(mdModels.get(i).getPhno());
        myViewHolder.eid.setText(mdModels.get(i).getEmailid());
        myViewHolder.address.setText(mdModels.get(i).getAddress());


    }

    @Override
    public int getItemCount() {
        return mdModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView vno, svno,vnoloc,svnoloc,name,ph,eid,address;
        public MyViewHolder(View itemView) {
            super(itemView);
            vno= (TextView) itemView.findViewById(R.id.t_vid);
            svno= (TextView) itemView.findViewById(R.id.t_vsid);
            vnoloc= (TextView) itemView.findViewById(R.id.t_vloc);
            svnoloc= (TextView) itemView.findViewById(R.id.t_vsloc);
            name= (TextView) itemView.findViewById(R.id.t_mname);
            ph= (TextView) itemView.findViewById(R.id.t_phno);
            eid= (TextView) itemView.findViewById(R.id.t_eid);
            address= (TextView) itemView.findViewById(R.id.t_add);


        }
    }
}
