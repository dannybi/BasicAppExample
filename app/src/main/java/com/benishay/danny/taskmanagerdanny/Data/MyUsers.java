package com.benishay.danny.taskmanagerdanny.Data;

import java.util.HashMap;

/**
 * Created by Dan on 12/07/2017.
 */

public class MyUsers {
    private String name, uKey_email /* primary key */, phone;
    private HashMap<String, Object> groupKeys;
    private HashMap<String, Object> tasksKeys;

    public MyUsers() {
        this.groupKeys = new HashMap<>();
        this.tasksKeys = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuKey_email() {
        return uKey_email.replace('.', '*');
    }

    public void setuKey_email(String uKey_email) {
        this.uKey_email = uKey_email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HashMap<String, Object> getGroupKeys() {
        return groupKeys;
    }

    public void setGroupKeys(HashMap<String, Object> groupKeys) {
        this.groupKeys = groupKeys;
    }

    public HashMap<String, Object> getTasksKeys() {
        return tasksKeys;
    }

    public void setTasksKeys(HashMap<String, Object> tasksKeys) {
        this.tasksKeys = tasksKeys;
    }

    public void addTaskKey (String taskKey)
    {
        this.tasksKeys.put(taskKey, true);
    }

    public void addGroupKey (String groupKey)
    {
        this.groupKeys.put(groupKey, true);
    }

    @Override
    public String toString() {
        return "MyUsers{" +
                "name='" + name + '\'' +
                ", uKey_email='" + uKey_email + '\'' +
                ", phone='" + phone + '\'' +
                ", groupKeys=" + groupKeys +
                ", tasksKeys=" + tasksKeys +
                '}';
    }
}
