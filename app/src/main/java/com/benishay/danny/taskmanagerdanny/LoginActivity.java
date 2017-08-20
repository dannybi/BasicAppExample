package com.benishay.danny.taskmanagerdanny;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.benishay.danny.taskmanagerdanny.Data.MyGroup;
import com.benishay.danny.taskmanagerdanny.Data.MyTasks;
import com.benishay.danny.taskmanagerdanny.Data.MyUsers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText etemail, etpass;
    private Button btnsignup, btnsignin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        //will check if the user is already logged in go to MainAct. Otherwise - need to login
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        etemail = (EditText) findViewById(R.id.etEmail);
        etpass = (EditText) findViewById(R.id.etPassword);
        btnsignin = (Button) findViewById(R.id.btnSignin);
        btnsignup = (Button) findViewById(R.id.btnSignup);

        eventHandler();
/*
        //connect to the firebase dbase
        //request instance/reference to the dbase
        //Whenever we want to connect to the dbase/storage - we need such OBJECT of this type
        //then we get ACCESS or INSTANCE to the dbase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //get access (specific !! URL) to SPECIFIC POINT in the dbase
        //if exist - return a value. If doesn't exist - then it will create it
        DatabaseReference refMyGroups = database.getReference("MyGroups");
        DatabaseReference refMyTasks = database.getReference("MyTasks");
        DatabaseReference refMyUsers = database.getReference("MyUsers");

        MyUsers u = new MyUsers();
        u.setName("s3");
        u.setuKey_email("dannyb@gmail.com");
        u.setPhone("0545");
        u.addTaskKey("t1");
        u.addTaskKey("t2");
        u.addTaskKey("t3");
        u.addGroupKey("g2");

        //"child" means CREATE a child
        //setValue means EMBED the u value into the database
        refMyUsers.child(u.getuKey_email().replace('.','*')).setValue(u);
        //******************************************************************

        MyGroup myGroup = new MyGroup();
        myGroup.setgKey("Grp_Key");
        myGroup.setName("my first group");
        myGroup.addUserKey("U1");
        myGroup.addTaskKey("T11");
        refMyGroups.child(myGroup.getgKey()).setValue(myGroup);

        //example on how to create UNIQUE ID of a group
        myGroup.setName("G2");
        myGroup.setMngrUkey(u.getuKey_email());
        myGroup.setgKey(refMyGroups.push().getKey());
        refMyGroups.child(myGroup.getgKey()).setValue(myGroup);
        //******************************************************************

        MyTasks myTasks = new MyTasks();
        myTasks.settKey(refMyTasks.push().getKey());
        myTasks.setText("Clean my Toyota");
        myTasks.setgKey(myGroup.getgKey());
        myTasks.setCompleted(false);
        myTasks.setuKey("dannyb@gmail.com");
        myTasks.setCreateAt(System.currentTimeMillis());
        refMyTasks.child(myTasks.gettKey()).setValue(myTasks);
        */
    }

    private void eventHandler() {
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler();
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void dataHandler() {
        //// TODO: 14/08/2017 sign in by firebase
        String stEmail = etemail.getText().toString();
        String stPassw = etpass.getText().toString();
        boolean isOK = true;
        if (stEmail.indexOf('@') < 1) {
            isOK = false;
            etemail.setError("Wrong email address");
        }

        if (isOK) {
            signIn(stEmail, stPassw);
        }
    }

    private void signIn(String stEmail, String stPassw) {
        final ProgressDialog progDialog = ProgressDialog.show(this, "WAIT...", "Login undergo....",
                /* type of waiting using clockwise */true,
                /* the dialog can be dismissed  during the process ?*/ true);
        progDialog.show();

        auth.signInWithEmailAndPassword(stEmail, stPassw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progDialog.dismiss();
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "wrong email or address", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //Example was.... DatabaseReference myRef = database.getReference("my_tasks");
    //Example was.... myRef.setValue("my 1st task details");
}
