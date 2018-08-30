package com.example.fuadmaska.myfeature.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fuadmaska.myfeature.R;
import com.example.fuadmaska.myfeature.Fragment.HomeDataFragment;

import java.util.List;

public class AdapterInsurance extends RecyclerView.Adapter<AdapterInsurance.ViewHolder> {

    HomeDataFragment insurance;
    String name;
    private List<HomeDataModel> insuranceModels;
    HomeDataModel data;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void SetOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public AdapterInsurance(List<HomeDataModel> insuranceModels, Context context) {
        this.insuranceModels = insuranceModels;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TVinsurance;
        TextView TVname;
        ImageView Image;
        View root;


        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            root = view;
            root.setClickable(true);
            root.setOnClickListener(this);
            TVinsurance = (TextView) view.findViewById(R.id.tv_insurance);
            TVname = (TextView) view.findViewById(R.id.tv_name);
            Image = view.findViewById(R.id.img_category);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION);
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }


    @Override
    public AdapterInsurance.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_home_data, parent, false);

        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final AdapterInsurance.ViewHolder holder, int position) {
        data = insuranceModels.get(position);


        String checkImageTitle=data.getTitle();
        if(checkImageTitle.equals("type1")){
            holder.Image.setImageResource(R.drawable.ic_account_balance_wallet_black_24dp);
        }else if(checkImageTitle.equals("type2")){
            holder.Image.setImageResource(R.drawable.ic_launcher_background);
        }else if(checkImageTitle.equals("type 3")){
            holder.Image.setImageResource(R.drawable.ic_arrow_back_black_24dp);
        }else if(checkImageTitle.equals("type4")){
            holder.Image.setImageResource(R.drawable.ic_account_balance_wallet_black_24dp);
        }


        //holder.Image.setImageResource(R.drawable.ic_account_balance_wallet_black_24dp);

        holder.TVname.setText(data.getTitle());
        holder.TVinsurance.setText(data.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return insuranceModels.size();
    }
}