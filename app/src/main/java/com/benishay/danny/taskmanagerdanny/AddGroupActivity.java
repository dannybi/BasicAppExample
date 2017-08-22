package com.benishay.danny.taskmanagerdanny;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.benishay.danny.taskmanagerdanny.Data.DBUtils;
import com.benishay.danny.taskmanagerdanny.Data.MyUsers;
import com.benishay.danny.taskmanagerdanny.Data.UsersAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//the class will be a listener
public class AddGroupActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText etGroupName, etEmail, etUserName;
    private ImageButton ibtnSearch, ibtnAddMember, ibtnSaveGroup;
    private ListView     lstMemberSearchResult, lstvSelectedMembers;

    private UsersAdapter userAdapterSerchResult, userAdapterSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        etGroupName = (EditText) findViewById(R.id.etGroupName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUserName = (EditText) findViewById(R.id.etName);
        ibtnSearch = (ImageButton) findViewById(R.id.ibtnSearch);
        ibtnAddMember = (ImageButton) findViewById(R.id.ibtnAddMember);
        ibtnSaveGroup = (ImageButton) findViewById(R.id.ibtnSaveGroup);
        lstMemberSearchResult = (ListView) findViewById(R.id.lstvMemberSearchResults);
        lstvSelectedMembers = (ListView) findViewById(R.id.lstvSelectedMembers);

        userAdapterSerchResult = new UsersAdapter(this, R.layout.itm_user, UsersAdapter.SEARCH_RESULT_MEMBERS);
        userAdapterSelected = new UsersAdapter(this, R.layout.itm_user, UsersAdapter.SELECTED_MEMBERS);

        lstMemberSearchResult.setAdapter(userAdapterSerchResult);
        lstvSelectedMembers.setAdapter(userAdapterSelected);

        eventHandler();
    }

    private void eventHandler() {
        ibtnSaveGroup.setOnClickListener(this);
        ibtnAddMember.setOnClickListener(this);
        ibtnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ibtnSearch) {
            search();
        }
        if (v == ibtnAddMember) {
            addSelectedUsers();
        }
        if (v == ibtnSaveGroup) {

        }
    }

    //prepare array list from type MyUser
    private void addSelectedUsers() {
        ArrayList<MyUsers> selectedUers = userAdapterSerchResult.getSelectedUsers();
        userAdapterSelected.addAll(selectedUers);

        //clear result displayed on screen from previous search
        userAdapterSerchResult.clearSelectedUsers();
//        for (MyUsers user: selectedUers) {
//            userAdapterSelected.add(user);
//
//        }
    }

    private void search() {
        String stEmail = etEmail.getText().toString();
        String stName = etUserName.getText().toString();
        userAdapterSerchResult.clear();

        final ProgressDialog progDialog = ProgressDialog.show(this,"Wait", "Searching");

        if (stEmail.length() > 0) {
            progDialog.show();
            //// TODO: 21/08/2017 because we have ptr to the desired value - we won't do the query
            DBUtils.myUsersRef.child(stEmail.replace('.','*'))
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    progDialog.dismiss();
                    if (dataSnapshot.exists()) {
                        MyUsers myUser = dataSnapshot.getValue(MyUsers.class);
                        userAdapterSerchResult.add(myUser);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (stName.length() > 0) {
            //the below will supply ALL the users under myUserRef

            //Query query = DBUtils.myUsersRef.orderByChild("name").equalTo(stName);
            //query.addListenerForSingleValueEvent(...);
            if (progDialog.isShowing() == false)
                progDialog.show();
            DBUtils.myUsersRef.orderByChild("name").equalTo(stName).
                    addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (progDialog.isShowing())
                        progDialog.dismiss();
                    for (DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        MyUsers myUsers = ds.getValue(MyUsers.class);
                        userAdapterSerchResult.add(myUsers);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
