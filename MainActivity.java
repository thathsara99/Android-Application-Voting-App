package com.example.votingapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtid, txtname;
    Button btnv;
    DatabaseReference reff;
    RadioButton malkini,suboda,malinga,rashane;
    TextView vote1,vote2,vote3,vote4,tot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid = (EditText) findViewById(R.id.txtid);
        txtname =(EditText) findViewById(R.id.txtnam);
        btnv = (Button) findViewById(R.id.btnv);
        malkini =(RadioButton) findViewById(R.id.malkini);
        suboda =(RadioButton) findViewById(R.id.suboda);
        malinga =(RadioButton) findViewById(R.id.malinga);
        rashane =(RadioButton) findViewById(R.id.rashane);

        vote1 = (TextView) findViewById(R.id.v1);
        vote2 = (TextView) findViewById(R.id.v2);
        vote3 = (TextView) findViewById(R.id.v3);
        vote4 = (TextView) findViewById(R.id.v4);
        tot = (TextView) findViewById(R.id.v5);


        reff = FirebaseDatabase.getInstance().getReference().child("vote");

        btnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    insertvote();

                   txtid.setText(null);
                   txtname.setText(null);
                   //malinga.setChecked(false);
                  // malkini.setChecked(false);
                   // suboda.setChecked(false);
                 //rashane.setChecked(false);




            }
        });


        malkini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malinga.setChecked(false);
                suboda.setChecked(false);
                rashane.setChecked(false);

            }
        });

        malinga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                malkini.setChecked(false);
                suboda.setChecked(false);
                rashane.setChecked(false);

            }
        });

        suboda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                malkini.setChecked(false);
                malinga.setChecked(false);
                rashane.setChecked(false);

            }
        });

        rashane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                malkini.setChecked(false);
                suboda.setChecked(false);
                malinga.setChecked(false);

            }
        });



        String v1=malkini.getText().toString();
        String v2=suboda.getText().toString();
        String v3=malinga.getText().toString();
        String v4=rashane.getText().toString();


        reff.orderByChild("v")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // get total available
                        int tot1 = (int) dataSnapshot.getChildrenCount();
                        tot.setText(String.valueOf(tot1));


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        reff.orderByChild("v").equalTo(v1)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // get total available
                        int V1mal = (int) dataSnapshot.getChildrenCount();
                        vote1.setText(String.valueOf(V1mal));


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        reff.orderByChild("v").equalTo(v2)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // get total available
                        int V2su = (int) dataSnapshot.getChildrenCount();
                       vote2.setText(String.valueOf(V2su));


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        reff.orderByChild("v").equalTo(v3)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // get total available
                        int V3ma = (int) dataSnapshot.getChildrenCount();
                        vote3.setText(String.valueOf(V3ma));


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        reff.orderByChild("v").equalTo(v4)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // get total available
                        int V4ra = (int) dataSnapshot.getChildrenCount();

                       vote4.setText(String.valueOf(V4ra));

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });







    }

    private void insertvote() {

        if(txtid.getText().toString().equals("") || txtname.getText().toString().equals("") )
        {
            Toast.makeText(MainActivity.this, "Please fill all details", Toast.LENGTH_LONG).show();
        }
        else {


            String id = txtid.getText().toString();
            String name = txtname.getText().toString();
            String v1 = malkini.getText().toString();
            String v2 = suboda.getText().toString();
            String v3 = malinga.getText().toString();
            String v4 = rashane.getText().toString();


            reff.orderByChild("id").equalTo(id)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Toast.makeText(MainActivity.this, "You have already voted once", Toast.LENGTH_LONG).show();
                            } else {

                                if (malkini.isChecked()) {

                                    vote Vote = new vote(id, name, v1);
                                    reff.push().setValue(Vote);

                                    Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();

                                }
                                if (suboda.isChecked()) {

                                    vote Vote = new vote(id, name, v2);
                                    reff.push().setValue(Vote);

                                    Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();

                                }
                                if (malinga.isChecked()) {

                                    vote Vote = new vote(id, name, v3);
                                    reff.push().setValue(Vote);

                                    Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();

                                }
                                if (rashane.isChecked()) {

                                    vote Vote = new vote(id, name, v4);
                                    reff.push().setValue(Vote);

                                    Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();

                                }
                            }

                            reff.orderByChild("v")
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // get total available
                                            int tot1 = (int) dataSnapshot.getChildrenCount();
                                            tot.setText(String.valueOf(tot1));


                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                            reff.orderByChild("v").equalTo(v1)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // get total available
                                            int V1mal = (int) dataSnapshot.getChildrenCount();
                                            vote1.setText(String.valueOf(V1mal));


                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                            reff.orderByChild("v").equalTo(v2)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // get total available
                                            int V2su = (int) dataSnapshot.getChildrenCount();
                                            vote2.setText(String.valueOf(V2su));


                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                            reff.orderByChild("v").equalTo(v3)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // get total available
                                            int V3ma = (int) dataSnapshot.getChildrenCount();
                                            vote3.setText(String.valueOf(V3ma));


                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                            reff.orderByChild("v").equalTo(v4)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // get total available
                                            int V4ra = (int) dataSnapshot.getChildrenCount();

                                            vote4.setText(String.valueOf(V4ra));

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

        }


    }
}