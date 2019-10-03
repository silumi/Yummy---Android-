package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummy.Common.Common;
import com.example.yummy.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Login extends AppCompatActivity {
MaterialEditText edtPhone, edtPassword;
Button BtnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPhone =(MaterialEditText)findViewById(R.id.edtPhone);
        edtPassword=(MaterialEditText)findViewById(R.id.edtPassword);


        BtnLogin=(Button)findViewById(R.id.BtnLogin);

        //init firebase
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user= database.getReference("User");

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog=new ProgressDialog(Login.this);
                mDialog.setMessage("Please Wait...!");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // check user not exist in data base

                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            // get user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                               Intent HomeIntent = new Intent(Login.this, Home.class);
                               Common.currentUser= user;
                               startActivity(HomeIntent);
                               finish();

                            } else {
                                Toast.makeText(Login.this, "Wrong password!!!", Toast.LENGTH_SHORT).show();
                            }}
                             else{
                                mDialog.dismiss();
                                Toast.makeText(Login.this, "User Not exist in database", Toast.LENGTH_SHORT).show();
                            }
                        }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
