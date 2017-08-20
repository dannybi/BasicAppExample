package com.benishay.danny.taskmanagerdanny;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.benishay.danny.taskmanagerdanny.Data.DBUtils;
import com.benishay.danny.taskmanagerdanny.Data.MyUsers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText etmail, etpassw, etrepassw, etname, etphone;
    private Button btnregister;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etmail = (EditText) findViewById(R.id.etEmail);
        etpassw = (EditText) findViewById(R.id.etPAssword);
        etrepassw =  (EditText) findViewById(R.id.etRenterPassw);
        etname = (EditText) findViewById(R.id.etName);
        etphone = (EditText) findViewById(R.id.etPhone);
        btnregister = (Button) findViewById(R.id.btnRegister);
        auth = FirebaseAuth.getInstance();

        eventHandler();
    }

    private void eventHandler() {
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler();
            }
        });
    }

    private void dataHandler() {
        final String stEmail = etmail.getText().toString();
        String stPassw = etpassw.getText().toString();
        String stRepas = etrepassw.getText().toString();
        final String stName = etname.getText().toString();
        final String stPhone = etphone.getText().toString();

        boolean isOK = true;
        if (stEmail.length() < 6 || stEmail.indexOf('@') < 2) {
            etmail.setError("Wrong email");
            isOK = false;
        }
        //// TODO: 14/08/2017 complete all possible error checks
        if (isOK) {
            final ProgressDialog progDialog = ProgressDialog.show(this, "WAIT...", "signing up....", true, true);
            progDialog.show();

            //DBUtils.auth...
            auth.createUserWithEmailAndPassword(stEmail, stPassw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progDialog.dismiss();
                    if (task.isSuccessful()){
                        //// TODO: 14/08/2017 add new user to  my new users dbase
                        MyUsers myusers = new MyUsers();
                        myusers.setName(stName);
                        myusers.setuKey_email(stEmail);
                        myusers.setPhone(stPhone);

                        DBUtils.myUsersRef.child(myusers.getuKey_email()).setValue(myusers).addOnCompleteListener(
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                          if (task.isSuccessful()) {
                                              finish();
                                          }
                                          else {
                                              Toast.makeText(SignUpActivity.this,
                                                      task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                          }
                                    }
                                }
                        );


 //was...                       finish(); // close the CURRENT activity
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        task.getException().printStackTrace();
                    }
                }
            });
        }
    }
}
