package com.example.myapplication;

import static com.example.myapplication.DBmain_casee.Casee1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

 class MyAdapter_casee extends RecyclerView.Adapter<MyAdapter_casee.ModelViewHolder> {
    Context context;
    ArrayList<case_DataHolder> modelArrayList=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    //generate constructor

    public MyAdapter_casee(Context context, int singledata, ArrayList<case_DataHolder> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }


    @Override
    public MyAdapter_casee.ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.singledata_casee,null);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter_casee.ModelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final case_DataHolder model = modelArrayList.get(position);
        holder.t1.setText(model.getCase_status());
        holder.t2.setText(model.getClinte_name());
        holder.t3.setText(model.getCase_number());
        holder.t4.setText(model.getCase_name());
        holder.t5.setText(model.getCase_date());
        holder.t6.setText(model.getRemarks());


        //click on button go to main activity
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("case_status",model.getCase_status());
                bundle.putString("clinte_name",model.getClinte_name());
                bundle.putString("case_number",model.getCase_number());
                bundle.putString("case_name",model.getCase_name());
                bundle.putString("case_date",model.getCase_date());
                bundle.putString("remarks_casee",model.getRemarks());
                Intent intent=new Intent(context,casee.class);
                intent.putExtra("userdata",bundle);
                context.startActivity(intent);
            }
        });
        //delete row
        holder.delete.setOnClickListener(new View.OnClickListener() {
            DBmain_casee dBmain= new DBmain_casee(context);
            @Override
            public void onClick(View v) {
                sqLiteDatabase=dBmain.getReadableDatabase();
                long delele=sqLiteDatabase.delete(Casee1,"case_name="+model.getCase_number(),null);
                if (delele!=-1){
                    Toast.makeText(context, "deleted data successfully", Toast.LENGTH_SHORT).show();
                    modelArrayList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5,t6;
        Button edit,delete;
        public ModelViewHolder( View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.casee_status_casee);
            t2=(TextView)itemView.findViewById(R.id.clientname_casee);
            t3=(TextView)itemView.findViewById(R.id.casee_number_casee);
           t4=(TextView)itemView.findViewById(R.id.casename_casee);
            t5=(TextView)itemView.findViewById(R.id.casedate_casee);
            t6=(TextView)itemView.findViewById(R.id.remarks_casee);
            edit=(Button)itemView.findViewById(R.id.txt_btn_edit);
            delete=(Button)itemView.findViewById(R.id.txt_btn_delete);
        }
    }
}