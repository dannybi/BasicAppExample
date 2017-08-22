package com.benishay.danny.taskmanagerdanny.GroupFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.benishay.danny.taskmanagerdanny.Data.DBUtils;
import com.benishay.danny.taskmanagerdanny.Data.MyGroup;
import com.benishay.danny.taskmanagerdanny.Data.MyUsers;
import com.benishay.danny.taskmanagerdanny.Data.UsersAdapter;
import com.benishay.danny.taskmanagerdanny.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupMembersFragment extends Fragment {
    private ListView lstvGroupMembers;
    private UsersAdapter usersAdapter;
    private MyGroup myGroup;

    public GroupMembersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_members, container, false);
        lstvGroupMembers = (ListView) view.findViewById(R.id.lstvGroupMembers);
        usersAdapter = new UsersAdapter(getActivity(), R.layout.itm_user, UsersAdapter.GROUP_MEMBERS);
        lstvGroupMembers.setAdapter(usersAdapter);
        Intent i = getActivity().getIntent();
        if (i != null) {
            if (i.getExtras() != null) {
                myGroup = (MyGroup) i.getExtras().get("gr");
            }
        }

        initListView();
        return view;
    }

    private void initListView() {
        if (myGroup != null) {
            usersAdapter.clear();
            for (String userKey : myGroup.getUserKeys().keySet()){
                DBUtils.myUsersRef.child(userKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        MyUsers myUser = dataSnapshot.getValue(MyUsers.class);
                        usersAdapter.add(myUser);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

}
