package com.sanque.marc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference database;
    EditText nameS;
    EditText ageS;
    EditText genderS;
    TextView tName;
    TextView tAge;
    TextView tGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance().getReference("Record");

        nameS = findViewById(R.id.name1);
        ageS = findViewById(R.id.age1);
        genderS = findViewById(R.id.gender1);

        tName = findViewById(R.id.display1);
        tAge = findViewById(R.id.display3);
        tGender = findViewById(R.id.display2);

    }
    protected void Toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void search(View v) {
        final String name = nameS.getText().toString().trim().toLowerCase();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss : dataSnapshot.getChildren()) {
                    Record r = ss.getValue(Record.class);
                    String person_name = r.getName().toLowerCase();

                    if(!person_name.equals(name))
                        continue;

                    else {
                        tName.setText(r.getName());
                        tAge.setText(r.getAge());
                        tGender.setText(r.getGender());
                        Toast("Record found");
                        return;
                    }
                }

                tName.setText("");
                tAge.setText("");
                tGender.setText("");
                Toast("Record not found");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };

        database.addValueEventListener(listener);
    }

    public void save(View v) {
        String name;
        String age;
        String gender;
        String key;

        name = nameS.getText().toString().trim();
        age = ageS.getText().toString().trim();
        gender = genderS.getText().toString().trim();
        key = database.push().getKey();

        database.child(key).setValue(new Record(name, age, gender));

        Toast("The record has been saved.");
    }

}
