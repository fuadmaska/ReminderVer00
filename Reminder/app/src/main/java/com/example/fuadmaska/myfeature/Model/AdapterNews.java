
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

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {

    HomeDataFragment news;
    String name;
    private List<HomeNewsDataModel> newsModel;
    HomeNewsDataModel data;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void SetOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public AdapterNews(List<HomeNewsDataModel> newsModel, Context context) {
        this.newsModel = newsModel;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TVnewsTitle;

        ImageView newsImage;
        View root;


        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            root = view;
            root.setClickable(true);
            root.setOnClickListener(this);
            TVnewsTitle = (TextView) view.findViewById(R.id.tv_newstitle);
            newsImage = view.findViewById(R.id.img_newsimage);

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
    public AdapterNews.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_home_news, parent, false);

        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final AdapterNews.ViewHolder holder, int position) {
        data = newsModel.get(position);
        holder.newsImage.setImageResource(R.drawable.ic_launcher_background);
        holder.TVnewsTitle.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return newsModel.size();
    }
}