package com.benishay.danny.taskmanagerdanny.MainFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.benishay.danny.taskmanagerdanny.Data.DBUtils;
import com.benishay.danny.taskmanagerdanny.Data.GroupAdapter;
import com.benishay.danny.taskmanagerdanny.Data.MyGroup;
import com.benishay.danny.taskmanagerdanny.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupsFragment extends Fragment implements Titleable{
    private ListView lstVGroups;
    private GroupAdapter groupAdapter;

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
        groupAdapter = new GroupAdapter(getActivity(), R.layout.itm_groups);
        initListView();
        return view;
    }

    private void initListView() {
        DBUtils.myGroupsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                groupAdapter.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    MyGroup myGroup = ds.getValue(MyGroup.class);
                    groupAdapter.add(myGroup);
                }
                lstVGroups.setAdapter(groupAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String getTitle(){
        return "My Groups";
    }
}
