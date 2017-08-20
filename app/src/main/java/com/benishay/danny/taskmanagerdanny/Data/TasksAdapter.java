package com.benishay.danny.taskmanagerdanny.Data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.benishay.danny.taskmanagerdanny.R;

import java.util.Date;

/**
 * Created by Dan on 17/08/2017.
 */

public class TasksAdapter extends ArrayAdapter<MyTasks> {
    public TasksAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    //write getV and complete to get this function. used to OVERRIDE exisitng func
    //ctrl + Q when pressing on a function - gives info about it
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itm_task, parent, false);
            //create JAVA'VIEW from the itm_task' XML data
        }
        MyTasks task = getItem(position);

        TextView tvText = (TextView) convertView.findViewById(R.id.itmTvText);
        CheckBox cbIscompleted = (CheckBox) convertView.findViewById(R.id.itmCBIsCompleted);
        TextView tvCreatedAt = (TextView) convertView.findViewById(R.id.itmtvCreatedAt);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.itmTvAddress);
        TextView tvUser = (TextView) convertView.findViewById(R.id.itmTvUser);
        Button btnGroup = (Button) convertView.findViewById(R.id.itmBtnGroup);

        tvText.setText(task.getText());
        cbIscompleted.setChecked(task.isCompleted());
        tvAddress.setText(task.getAddress());
        //// TODO: 17/08/2017  need to convert they key to real name
        tvUser.setText(task.getuKey());
        //// TODO: 17/08/2017  need to convert they key to real name
        btnGroup.setText(task.getgKey());

        Date date=new Date(task.getCreateAt());
        tvCreatedAt.setText(date.toString());

        return convertView;
    }
}
