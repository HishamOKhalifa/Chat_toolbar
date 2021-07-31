package com.example.chat_toolbar.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chat_toolbar.Model.Scholar;
import com.example.chat_toolbar.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class scholaradapter extends FirebaseRecyclerAdapter<Scholar, scholaradapter.myviewholder>
{
    public scholaradapter(@NonNull FirebaseRecyclerOptions<Scholar> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final Scholar scholar)
    {
        holder.title.setText(scholar.getTitle());
        Glide.with(holder.image.getContext()).load(scholar.getImage()).into(holder.image);


        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Uri uri = Uri.parse(scholar.getLink());
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,uri));


            }


        });
    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.scholarcard,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title;
        Button apply;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            image= itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            apply = itemView.findViewById(R.id.apply);



        }
    }
}

