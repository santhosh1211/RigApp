package in.co.santhoshiot.rigapp;

import android.view.View;

/**
 * Created by Lucky on 2/18/2016.
 */
public interface RecyclerViewClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}