package com.benishay.danny.taskmanagerdanny.Data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.benishay.danny.taskmanagerdanny.R;

/**
 * Created by Dan on 20/08/2017.
 */

public class GroupAdapter extends ArrayAdapter<MyGroup> {

    public GroupAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itm_groups, parent, false);
        }
        TextView tvGroupName = (TextView) convertView.findViewById(R.id.itmTvGrpName);
        TextView tvOwnerName = (TextView) convertView.findViewById(R.id.itmTvGroOwner);

        MyGroup myGroup = getItem(position);

        tvGroupName.setText((myGroup.getName()));

        //todo get real name for the group manager
        tvOwnerName.setText(myGroup.getMngrUkey());

        return convertView;
    }

}
