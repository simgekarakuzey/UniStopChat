package com.simge.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GirisActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    EditText kullaniciAdiEditText;
    Button kayitOlButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        tanimla();
    }

    public void tanimla() {    //firebasedb'yi tanımlama
        kullaniciAdiEditText = (EditText) findViewById(R.id.kullaniciAdiEditText);
        kayitOlButon = (Button) findViewById(R.id.kayitOlButon);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        kayitOlButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = kullaniciAdiEditText.getText().toString().trim();
                kullaniciAdiEditText.setText("");
                add(userName);
            }
        });
    }

    public void add(final String uName) {

        reference.child("Kullanıcılar").child(uName).child("kullaniciadi").setValue(uName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                try {
                    Toast.makeText(getApplicationContext(), "Başarılı", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GirisActivity.this,MainActivity.class);
                    intent.putExtra("uName",uName);
                    startActivity(intent);
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                     /*  FirebaseDatabase dBasedBase = FirebaseDatabase.getInstance();
                         DatabaseReference dbRef = dBasedBase.getReference("semih");
                         dbRef.setValue(uName);
                         System.out.println(uName); */
                }
            }
        });
    }
}


