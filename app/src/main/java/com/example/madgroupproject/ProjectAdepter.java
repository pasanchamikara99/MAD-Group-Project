package com.example.madgroupproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {


        Category model = list.get(position);
        Picasso.get().load(model.getUrl()).placeholder(R.drawable.logo).into(holder.categoryImage);
        holder.categoryTitle.setText(model.getTitle());
        holder.categoryID.setText(model.getId());



        holder.categoryUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,UpdateCategoryItem.class);
                intent.putExtra("image" ,model.getUrl());
                intent.putExtra("id" ,model.getId());
                intent.putExtra("title" ,model.getTitle());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });




        holder.categoryDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context,DeleteCategory.class);
                intent.putExtra("image" ,model.getUrl());
                intent.putExtra("id" ,model.getId());
                intent.putExtra("title" ,model.getTitle());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                /*
                builder = new AlertDialog.Builder(context);

                builder.setMessage(R.string.alertDialogMessage).setTitle(R.string.alertDialogTitle);

                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseReference def = FirebaseDatabase.getInstance().getReference().child("Category").child(model.getId());
                                def.removeValue();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(holder.categoryID.getContext(),"cancel",Toast.LENGTH_SHORT).show();
                            }
                        });



                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.show();*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryID,categoryTitle,categoryName;
        ImageView categoryImage;
        Button categoryUpdate,categoryDelete;


        public ViewHolder(@NonNull View itemView){
            super(itemView);

            categoryID = itemView.findViewById(R.id.txtCategoryID);
            categoryTitle = itemView.findViewById(R.id.txtCategoryTitle);
            categoryImage = itemView.findViewById(R.id.categoryImage);

            categoryUpdate = itemView.findViewById(R.id.btncategoryUpdate);
            categoryDelete = itemView.findViewById(R.id.btncategoryDelete);

        }

    }
}
