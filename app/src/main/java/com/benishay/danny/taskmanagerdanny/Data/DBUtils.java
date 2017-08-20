package com.benishay.danny.taskmanagerdanny.Data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Dan on 16/08/2017.
 */

public class DBUtils {
    public static FirebaseDatabase dbase = FirebaseDatabase.getInstance();
    public static DatabaseReference myUsersRef = dbase.getReference(MyUsers.class.getSimpleName());
    public static DatabaseReference myTasksRef = dbase.getReference(MyTasks.class.getSimpleName());
    public static DatabaseReference myGroupsRef = dbase.getReference(MyGroup.class.getSimpleName());
    public static FirebaseAuth auth = FirebaseAuth.getInstance();
}
