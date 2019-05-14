package in.co.santhoshiot.rigapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ownerHomeAdapter extends RecyclerView.Adapter<ownerHomeAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<ownerHomeModel> ownerHomeModels;

    public ownerHomeAdapter(Context ctx, ArrayList<ownerHomeModel> ownerHomeModels) {
        inflater = LayoutInflater.from(ctx);
        this.ownerHomeModels = ownerHomeModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.layout_reclyer, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tvno.setText(ownerHomeModels.get(i).getVno());
        myViewHolder.tvvno.setText(ownerHomeModels.get(i).getSvno());
        myViewHolder.name.setText(ownerHomeModels.get(i).getName());
        
    }

    @Override
    public int getItemCount() {
        return ownerHomeModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvvno, tvno,name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvno = (TextView) itemView.findViewById(R.id.tv_id);
            tvvno = (TextView) itemView.findViewById(R.id.tsv_id);
            name = (TextView) itemView.findViewById(R.id.t_name);
        }
    }
}
