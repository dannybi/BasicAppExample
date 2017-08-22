package com.benishay.danny.taskmanagerdanny.Data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.benishay.danny.taskmanagerdanny.R;

import java.util.ArrayList;

import static com.google.android.gms.tasks.Tasks.call;

/**
 * Created by Dan on 21/08/2017.
 */

public class UsersAdapter extends ArrayAdapter<MyUsers> {
    private int target;
    private ArrayList<MyUsers> selectedUsers;

    public final static int GROUP_MEMBERS = 1;
    public final static int SEARCH_RESULT_MEMBERS = 2;
    public final static int SELECTED_MEMBERS = 3;
    public final static int SPINNER_MEMBERS = 4;

    public ArrayList<MyUsers> getSelectedUsers() {
        return selectedUsers;
    }

    public UsersAdapter(@NonNull Context context, @LayoutRes int resource, int target) {
        super(context, resource);
        this.target = target;
        selectedUsers = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itm_user, parent, false);
        }
        final MyUsers myUsers = getItem(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.itmtvUserName);
        TextView tvMail = (TextView) convertView.findViewById(R.id.itmtvUserEmail);
        ImageButton ibtnCall = (ImageButton) convertView.findViewById(R.id.itmBtnUserCall);
        ImageButton ibtnDelet = (ImageButton) convertView.findViewById(R.id.itmBtnUserDel);
        CheckBox cbSelect = (CheckBox) convertView.findViewById(R.id.itmCBSelect);

        tvName.setText(myUsers.getName());
        tvMail.setText(myUsers.getuKey_email());
        switch (this.target) {
            case GROUP_MEMBERS:
                ibtnDelet.setVisibility(View.GONE);
                cbSelect.setVisibility(View.GONE);
                ibtnCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call(myUsers.getPhone());
                    }
                });
                break;
            case SEARCH_RESULT_MEMBERS:
                ibtnCall.setVisibility(View.GONE);
                ibtnDelet.setVisibility(View.GONE);
                cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        select(myUsers, isChecked);
                    }
                });
                break;
            case SELECTED_MEMBERS:
                ibtnCall.setVisibility(View.GONE);
                cbSelect.setVisibility(View.GONE);
                ibtnDelet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteMember(myUsers);
                    }
                });
                break;
            case SPINNER_MEMBERS:
                ibtnCall.setVisibility(View.GONE);
                ibtnDelet.setVisibility(View.GONE);
                cbSelect.setVisibility(View.GONE);
                break;
        }

        return convertView;
    }

    //// TODO: 21/08/2017 delete user from the selected memebers
    private void deleteMember(MyUsers myUser) {
        remove(myUser);
    }

    //// TODO: 21/08/2017 selection of user. will update the selectUsers
    private void select(MyUsers user, boolean isChecked) {
        if (isChecked) {
            selectedUsers.add(user);
        }
        else {
            selectedUsers.remove(user);
        }
    }

    //// TODO: 21/08/2017 make CALL by intent
    private void call(String phone) {

    }

    public void clearSelectedUsers() {
        selectedUsers.clear();
    }
}
