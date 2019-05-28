package com.adc.om.afterdeathcare;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Developer on 20-03-2018.
 */

public class DeveloperCardViewAdapter extends RecyclerView.Adapter<DeveloperCardViewAdapter.ViewHolder> {
    public List<String> hosp_name, source, dest,contact,body,pincode,accept;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DeveloperCardViewAdapter(Context context, List hosp_name, List source, List dest,List contact,List body,List pincode,List accept) {
        this.context=context;
        this.hosp_name=hosp_name;
        this.source=source;
        this.dest=dest;
        this.contact=contact;
        this.body=body;
        this.pincode=pincode;
        this.accept=accept;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DeveloperCardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.developer_card_view, null);

        // create ViewHolder


        DeveloperCardViewAdapter.ViewHolder viewHolder = new DeveloperCardViewAdapter.ViewHolder(context,itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DeveloperCardViewAdapter.ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.name.setText(hosp_name.get(position));
        viewHolder.clas.setText(source.get(position));
        viewHolder.dest.setText(dest.get(position));
        viewHolder.contact.setText(contact.get(position));
        viewHolder.body.setText(body.get(position));
        viewHolder.pincode.setText(pincode.get(position));
        viewHolder.accept.setText(accept.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return hosp_name.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView outer_card;
        public TextView clas, name,dest,contact,body,pincode,accept;


        private Context context;
        // public Button bt1,bt2;


        public ViewHolder(final Context context, View itemLayoutView) {
            super(itemLayoutView);
            this.context = context;
            outer_card = itemLayoutView.findViewById(R.id.outer_card);
            name =  itemLayoutView.findViewById(R.id.hosp_name);
            clas =  itemLayoutView.findViewById(R.id.sourc);
            dest =  itemLayoutView.findViewById(R.id.dest);
            contact =  itemLayoutView.findViewById(R.id.contacts);
            body =  itemLayoutView.findViewById(R.id.body);
            pincode =  itemLayoutView.findViewById(R.id.pincode);
            accept =  itemLayoutView.findViewById(R.id.accept);

        }
    }
}
