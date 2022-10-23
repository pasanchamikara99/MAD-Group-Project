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


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DeliveryPersonMainAdapter extends FirebaseRecyclerAdapter<DeliveryPersonModel,DeliveryPersonMainAdapter.myViewHolder> {

    public DeliveryPersonMainAdapter(@NonNull FirebaseRecyclerOptions<DeliveryPersonModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull DeliveryPersonModel model){

        holder.name.setText(model.getName());
        holder.contactNo.setText(model.getContactNo());
        holder.licenseNo.setText(model.getLicenseNo());
        holder.email.setText(model.getEmail());

        holder.btnPersonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_delivery_person))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();
                EditText name = view.findViewById(R.id.txtDpName);
                EditText contactNo = view.findViewById(R.id.txtDpPhone);
                EditText licenseNo = view.findViewById(R.id.txtDpLisence);
                EditText email = view.findViewById(R.id.txtDpEmail);

                Button btnUpdate1 = view.findViewById(R.id.btnPersonUpdate);

                name.setText(model.getName());
                contactNo.setText(model.getContactNo());
                licenseNo.setText(model.getLicenseNo());
                email.setText(model.getEmail());

                dialogPlus.show();

                btnUpdate1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("contactNo",contactNo.getText().toString());
                        map.put("licenseNo",licenseNo.getText().toString());
                        map.put("email",email.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("DeliveryPersons")
                                .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(),"Data Updated Successfully",Toast.LENGTH_LONG).show();


                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(),"Error while updating",Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                });

            }
        });

        holder.btnPersonDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("DeliveryPersons")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(),"Cancelled",Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deliverypersons_list,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView name,contactNo,licenseNo,email;

        Button btnPersonEdit , btnPersonDlt;




        public myViewHolder(@NonNull View itemView){
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.textViewDname);
            contactNo = (TextView) itemView.findViewById(R.id.textViewDphone);
            licenseNo = (TextView) itemView.findViewById(R.id.textViewDlicense);
            email = (TextView) itemView.findViewById(R.id.textViewDemail);

            btnPersonEdit=(Button) itemView.findViewById(R.id.btnPersonedit);
            btnPersonDlt =(Button) itemView.findViewById(R.id.btnPersonDlt);
        }
    }
}
