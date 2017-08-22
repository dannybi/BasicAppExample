package com.benishay.danny.taskmanagerdanny.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Dan on 12/07/2017.
 */

public class MyGroup implements Serializable{
    private String gKey, mngrUkey, name;
    private HashMap<String, Object> userKeys=new HashMap<>();
    private HashMap<String, Object> taskKeys=new HashMap<>();

    public MyGroup() {
    }

    public String getgKey() {
        return gKey;
    }

    public void setgKey(String gKey) {
        this.gKey = gKey;
    }

    public String getMngrUkey() {
        return mngrUkey;
    }

    public void setMngrUkey(String mngrUkey) {
        this.mngrUkey = mngrUkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Object> getUserKeys() {
        return userKeys;
    }

    public void setUserKeys(HashMap<String, Object> userKeys) {
        this.userKeys = userKeys;
    }

    public HashMap<String, Object> getTaskKeys() {
        return taskKeys;
    }

    public void setTaskKeys(HashMap<String, Object> taskKeys) {
        this.taskKeys = taskKeys;
    }

    @Override
    public String toString() {
        return "MyGroup{" +
                "gKey='" + gKey + '\'' +
                ", mngrUkey='" + mngrUkey + '\'' +
                ", name='" + name + '\'' +
                ", userKeys=" + userKeys +
                ", taskKeys=" + taskKeys +
                '}';
    }

    public void addTaskKey(String taskKey)
    {
        this.taskKeys.put(taskKey, true);
    }

    public void addUserKey(String userKey)
    {
        this.userKeys.put(userKey, true);
    }
}
