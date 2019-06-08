package com.recollective.panicsensor;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recollective.panicsensor.models.LOGIN;
import com.recollective.panicsensor.models.REPORT;

import java.util.ArrayList;

public class Login extends Activity {

    EditText txt_user;
    EditText txt_pass;
    Button btn_login;
    ListView lst_login;
    ArrayList<LOGIN> lst_users = new ArrayList<>();
    ArrayList<REPORT> lst_report = new ArrayList<>();
    //DB
    DatabaseReference databaseLogin;
    DatabaseReference databaseLoginChild;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vlogin);
        txt_user = (EditText)findViewById(R.id.txt_user);
        txt_pass = (EditText)findViewById(R.id.txt_pass);
        btn_login = (Button) findViewById(R.id.btn_login);
        lst_login = (ListView)findViewById(R.id.lst_login);

        databaseLogin = FirebaseDatabase.getInstance().getReference("login");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseLogin.push().getKey();
                LOGIN login = new LOGIN(id,txt_user.getText().toString(),txt_pass.getText().toString());
                databaseLogin.child(id).setValue(login);

            }
        });

        lst_login.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                databaseLoginChild = FirebaseDatabase.getInstance().getReference("loginsub").child(lst_users.get(i).getId_auto());

                String id_tracking = databaseLoginChild.push().getKey();

                REPORT repo = new REPORT(id_tracking,"1.223","32.4546","Olivino 124",1,"phoyo.png","Tina");

                databaseLoginChild.child(lst_users.get(i).getId_auto()).setValue(repo);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseLogin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lst_users.clear();
                for (DataSnapshot loginsnap : dataSnapshot.getChildren()){
                    LOGIN logread = loginsnap.getValue(LOGIN.class);
                    lst_users.add(logread);
                }

               /* ArrayAdapter<LOGIN> adapter = new ArrayAdapter(Login.this,android.R.layout.simple_list_item_1,lst_users);

                lst_login.setAdapter(adapter);*/
                databaseLoginChild = FirebaseDatabase.getInstance().getReference("loginsub").child(lst_users.get(3).getId_auto());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final Handler h = new Handler();
        new Thread(new Runnable() {
            public void run() {
                // DO NOT ATTEMPT TO DIRECTLY UPDATE THE UI HERE, IT WON'T WORK!
                // YOU MUST POST THE WORK TO THE UI THREAD'S HANDLER
                h.postDelayed(new Runnable() {
                    public void run() {
                        databaseLoginChild.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                lst_report.clear();
                                for (DataSnapshot logindetsnap : dataSnapshot.getChildren()){
                                    REPORT logread = logindetsnap.getValue(REPORT.class);
                                    lst_report.add(logread);
                                }

                                ArrayAdapter<REPORT> adapter = new ArrayAdapter(Login.this,android.R.layout.simple_list_item_1,lst_report);

                                lst_login.setAdapter(adapter);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }, 1000);
            }
        }).start();

    }

}
