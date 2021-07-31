package com.example.chat_toolbar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chat_toolbar.Model.Post;
import com.example.chat_toolbar.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class postadapter extends RecyclerView.Adapter<postadapter.myviewholder>
{

    Context context;
    List<Post> postList;

    public postadapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    /*public postadapter(@NonNull FirebaseRecyclerOptions<Post> options)
    {
        super(options);
    }*/

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder,  int position)
    {
        /*holder.name.setText(post.getName());
        holder.course.setText(post.getCourse());
        holder.email.setText(post.getEmail());
        Glide.with(holder.img.getContext()).load(post.getPurl()).into(holder.img);*/

        String name = postList.get(position).getName();
        String course = postList.get(position).getCourse();
        String email = postList.get(position).getEmail();
        String image = postList.get(position).getPurl();


        holder.name.setText(name);
        holder.course.setText(course);
        holder.email.setText(email);

       Glide.with(holder.img.getContext()).load(image).into(holder.img);

       holder.img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(context, ""+name, Toast.LENGTH_SHORT).show();
           }
       });




    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.poscard,parent, false);
        return new myviewholder(view);
    }



    @Override
    public int getItemCount() {
        return postList.size();
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
