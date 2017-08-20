package com.benishay.danny.taskmanagerdanny.MainFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.benishay.danny.taskmanagerdanny.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupsFragment extends Fragment implements Titleable{
    private ListView lstVGroups;

    public MyGroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_groups, container, false);
        lstVGroups = (ListView) view.findViewById(R.id.lstGroups);
        //// TODO: 16/08/2017 connect the list view to the data source by adapter
        return view;
    }
    public String getTitle(){
        return "My Groups";
    }
}
