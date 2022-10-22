package com.example.madgroupproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProjectAdepter extends RecyclerView.Adapter<ProjectAdepter.ViewHolder>{
    ArrayList<Category> list;
    Context context;

    public ProjectAdepter(ArrayList<Category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category model = list.get(position);
        Picasso.get().load(model.getUrl()).placeholder(R.drawable.logo).into(holder.categoryImage);
        holder.categoryTitle.setText(model.getTitle());
        holder.categoryID.setText(model.getId());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryHeadLine,categoryID,categoryTitle;
        ImageView categoryImage;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            categoryID = itemView.findViewById(R.id.txtCategoryID);
            categoryTitle = itemView.findViewById(R.id.txtCategoryTitle);
            categoryImage = itemView.findViewById(R.id.categoryImage);

        }

    }
}
