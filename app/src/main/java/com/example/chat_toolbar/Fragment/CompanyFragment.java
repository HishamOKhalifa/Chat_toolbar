package com.example.chat_toolbar.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_toolbar.Adapter.postadapter;
import com.example.chat_toolbar.Model.Post;
import com.example.chat_toolbar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CompanyFragment extends Fragment {

    RecyclerView recview;
    postadapter adapter;
    List<Post> postList;

    FirebaseAuth firebaseAuth;


    public CompanyFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_company, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        recview=view.findViewById(R.id.recview);
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));

        postList = new ArrayList<>();

        getAllPosts();




        return view;
    }

    private void getAllPosts() {

        FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Company");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    Post post = ds.getValue(Post.class);

                    if (!post.getId().equals(fuser.getUid())){
                        postList.add(post);
                    }

                    adapter = new postadapter(getActivity(), postList);

                    recview.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void searchPosts(String query) {

        FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Company");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    Post post = ds.getValue(Post.class);

                    if (!post.getId().equals(fuser.getUid())){

                        if(post.getName().toLowerCase().contains(query.toLowerCase()) ||
                                post.getEmail().toLowerCase().contains(query.toLowerCase()) ||
                                post.getCourse().toLowerCase().contains(query.toLowerCase())){

                            postList.add(post);

                        }


                    }

                    adapter = new postadapter(getActivity(), postList);
                    adapter.notifyDataSetChanged();
                    recview.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        setHasOptionsMenu(true);//Make sure you have this line of code.
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.search,menu);

        super.onCreateOptionsMenu(menu, inflater);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if (!TextUtils.isEmpty(s.trim())){
                    searchPosts(s);
                }
                else {
                    getAllPosts();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!TextUtils.isEmpty(s.trim())){
                    searchPosts(s);
                }
                else {
                    getAllPosts();
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }




}