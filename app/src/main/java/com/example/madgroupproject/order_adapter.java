package com.example.madgroupproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class order_adapter extends RecyclerView.Adapter<order_adapter.MyViewHolder> {

    android.content.Context context;

    ArrayList<order_data> list;
    DatabaseReference dbref;

    public order_adapter(android.content.Context context, ArrayList<order_data> list) {
        this.context = context;
        this.list = list;
        dbref=FirebaseDatabase.getInstance().getReference("Order");
    }


    @NonNull
    @Override
    public order_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.show_order,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull order_adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        order_data order= list.get(position);
        holder.name.setText(order.getName());
        holder.address.setText(order.getAddress());
        holder.mobile.setText(order.getPhone());
        holder.card_no.setText(order.getCard());
        holder.DID.setText(order.getDID());
        holder.status.setText(order.getStatus());
        holder.type.setText(order.getType());
        holder.details.setText(order.getOrderDetails());
        holder.price.setText(order.getPrice());
        holder.datetime.setText(order.getDate_time());


        //udate
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_pop_up))
                        .setExpanded(true,1200)
                        .create();

                View v =dialogPlus.getHolderView();
                EditText status=v.findViewById(R.id.svdupdateStatus1);
                EditText did=v.findViewById(R.id.svdupdateDID1);

                Button btnedit=v.findViewById(R.id.svdbtnupdate2);

                status.setText(order.getStatus());
                did.setText(order.getDID());
                dialogPlus.show();

                btnedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("status",status.getText().toString());
                        map.put("did",did.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("Order")
                                .child("order"+"_"+order.getPhone()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Updatated Successfuly", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updatong", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        //delete
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Delete data can not be undone!");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Order").child("order"+"_"+order.getPhone()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView name,address,mobile,card_no,DID,status,type,details,price,datetime;

        Button btnedit,btndelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.svdshowName2);
            address=itemView.findViewById(R.id.svdshowAddress2);
            mobile=itemView.findViewById(R.id.svdshowMobile2);
            card_no=itemView.findViewById(R.id.svdshowcrad2);
            DID=itemView.findViewById(R.id.svdshowdid2);
            status=itemView.findViewById(R.id.svdshowstatus2);
            type=itemView.findViewById(R.id.svdshowtype2);
            details=itemView.findViewById(R.id.svdshowdetails);
            price=itemView.findViewById(R.id.svdshowprice);
            datetime=itemView.findViewById(R.id.svdshowdatetime);

            btnedit=(Button)itemView.findViewById(R.id.svdupdatebtn);
            btndelete=(Button)itemView.findViewById(R.id.svddeleteBtn);
        }
    }

}

