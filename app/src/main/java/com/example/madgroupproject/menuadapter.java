package com.example.madgroupproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;


public class menuadapter extends FirebaseRecyclerAdapter<model,menuadapter.menuviewholder>
{
    public menuadapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final menuviewholder holder, final int position, @NonNull final model model)
    {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.id.setText(model.getId());
        holder.description.setText(model.getDescription());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);



        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Add to cart");
                builder.setMessage("Proceed");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Food")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public menuviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new menuviewholder(view);
    }


    class menuviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView cart;
        TextView name,id, price, description;
        public menuviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1m);
            name=(TextView)itemView.findViewById(R.id.nametextm);
            id=(TextView)itemView.findViewById(R.id.idtextm);
            price=(TextView)itemView.findViewById(R.id.pricetextm);
            description=(TextView)itemView.findViewById(R.id.descriptiontextm);



            cart=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
