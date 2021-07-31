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

import com.example.chat_toolbar.Adapter.scholaradapter;
import com.example.chat_toolbar.Model.Scholar;
import com.example.chat_toolbar.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class ScholarFragment extends Fragment {



    RecyclerView recview1; //recview;
    scholaradapter adapter1;
  //  postadapter adapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_scholar, container, false);

        recview1=view.findViewById(R.id.recview1);
        recview1.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<Scholar> options =
                new FirebaseRecyclerOptions.Builder<Scholar>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Scholar"), Scholar.class)
                        .build();

        adapter1=new scholaradapter(options);
        recview1.setAdapter(adapter1);

     //   recview=view.findViewById(R.id.recview);


       /* FirebaseRecyclerOptions<Post> options1 =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Company"), Post.class)
                        .build();*/

   //     adapter=new postadapter(options1);
//        recview.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter1.startListening();
      //  adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter1.stopListening();
      //  adapter.stopListening();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Make sure you have this line of code.
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
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
        FirebaseRecyclerOptions<Scholar> options =
                new FirebaseRecyclerOptions.Builder<Scholar>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Scholar").orderByChild("title").startAt(s).endAt(s+"\uf8ff"), Scholar.class)
                        .build();
        adapter1=new scholaradapter(options);
        adapter1.startListening();
        recview1.setAdapter(adapter1);

    }
}