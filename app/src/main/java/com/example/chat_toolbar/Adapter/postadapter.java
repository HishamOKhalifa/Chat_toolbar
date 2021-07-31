package com.example.chat_toolbar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chat_toolbar.Model.Post;
import com.example.chat_toolbar.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;


public class postadapter extends FirebaseRecyclerAdapter<Post, postadapter.myviewholder>
{
    public postadapter(@NonNull FirebaseRecyclerOptions<Post> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final Post post)
    {
        holder.name.setText(post.getName());
        holder.course.setText(post.getCourse());
        holder.email.setText(post.getEmail());
        Glide.with(holder.img.getContext()).load(post.getPurl()).into(holder.img);




    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.poscard,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,course,email;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.img1);
            name=itemView.findViewById(R.id.nametext);
            course=itemView.findViewById(R.id.coursetext);
            email=itemView.findViewById(R.id.emailtext);



        }
    }
}
