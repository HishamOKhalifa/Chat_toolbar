package com.example.chat_toolbar.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_toolbar.Adapter.postadapter;
import com.example.chat_toolbar.Model.Post;
import com.example.chat_toolbar.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class CompanyFragment extends Fragment {

    RecyclerView recview;
    postadapter adapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_company, container, false);

        recview=view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Company"), Post.class)
                        .build();

        adapter=new postadapter(options);
        recview.setAdapter(adapter);






        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Make sure you have this line of code. and to show menu option in fragment
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here

        inflater.inflate(R.menu.search,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return true;
            }
        });



    }



    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Company").orderByChild("course").startAt(s).endAt(s+"\uf8ff"), Post.class)
                        .build();
        adapter=new postadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }
}