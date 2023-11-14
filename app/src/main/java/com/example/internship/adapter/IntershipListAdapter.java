package com.example.internship.adapter;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

import com.example.internship.HomeActivity;
import com.example.internship.R;
import com.example.internship.applyActivity;
import com.example.internship.database.AppDataBase;
import com.example.internship.entity.Intership;
import com.example.internship.updateIntershipActivity;

import java.util.List;


public class IntershipListAdapter extends RecyclerView.Adapter<IntershipListAdapter.IntershipViewHolder> {
    private Context context;
    private List<Intership> inters;

    public IntershipListAdapter(List<Intership> entity1List) {
        this.inters = entity1List;
    }
    SharedPreferences myPref;
    public static final String SharedPreFile = "MyFile";

    @NonNull
    @Override
    public IntershipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context  = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.intership_item, parent, false);
        return new IntershipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntershipViewHolder holder, int position) {
        Intership inter = inters.get(position);

        holder.textViewTitle.setText(inter.getTitle());
        holder.textViewDescription.setText(inter.getDescription());

        int ituId= inter.getId();

        holder.textViewTitle.setOnClickListener(i->
        {
            Intent intent = new Intent(i.getContext(), updateIntershipActivity.class);
            intent.putExtra("ituId", ituId);
            intent.putExtra("attr1", this.inters.get(position).getTitle());
            intent.putExtra("attr2", this.inters.get(position).getDescription());
            i.getContext().startActivity(intent);
        });

        myPref = context.getSharedPreferences(SharedPreFile,MODE_PRIVATE);

        String value = myPref.getString("role",null);
        if (value == "Admin") {
            holder.apply.setVisibility(View.INVISIBLE);

        } else  {
            holder.delete.setVisibility(View.INVISIBLE);

        }

        holder.apply.setOnClickListener(i->{
            Intent intent = new Intent(i.getContext(), applyActivity.class);
            intent.putExtra("interId", ituId);
            intent.putExtra("title", this.inters.get(position).getTitle() );


            i.getContext().startActivity(intent);

        });

            holder.delete.setOnClickListener(i->{


                Intent intent = new Intent(i.getContext(), HomeActivity.class);
                intent.putExtra("email",myPref.getString("EMAIL",""));
                intent.putExtra("name", myPref.getString("NAME", ""));



                AppDataBase.getAppDatabase(context).intershipDao().delete(inter);
            Toast.makeText(i.getContext(), "deleted", Toast.LENGTH_LONG).show();
            i.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        if (inters != null) {
            return inters.size();
        }
        return 0;
    }

    public void setJobs(List<Intership> inters) {
        this.inters = inters;
        notifyDataSetChanged();
    }

    static class IntershipViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;

        Button delete, apply;

        public IntershipViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            delete = itemView.findViewById(R.id.delete);
            apply = itemView.findViewById(R.id.apply);
        }
    }
}