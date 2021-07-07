package com.example.chat_toolbar.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.chat_toolbar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;




public class ResumeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resume, container,false);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> Snackbar.make(view1, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());


        return view;
    }


}