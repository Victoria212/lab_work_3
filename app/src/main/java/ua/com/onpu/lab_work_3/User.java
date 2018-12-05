package ua.com.onpu.lab_work_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity implements View.OnClickListener,Serializable {
    private ArrayAdapter<User> adapter;
    private List<User> users;
    public String login;
    public String password;
    public String name;
    public String mail;
    public String gender;
    public int age;
    public String[] Gender = {"Male", "Female"};
    String item;
    Spinner spinner;

    Button create;
    int i;

   /* User(String login, String password, String mail, String name, int age, int g) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.name = name;
        this.age = age;
        this.gender = Gender[g];
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createuser_activity);
        users  = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);

        create = findViewById(R.id.button);
        create.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                item = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                User user = new User();
                user.gender = "female";
                user.mail = "sdfsdf";
                user.password = "in";
                user.login = "log";
                user.age = 13;
                user.name = "sefsdf1111";

                users.add(user);
                adapter.notifyDataSetChanged();
                boolean result = JSONHelper.exportToJSON(this, users);
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
                break;

            default:

                break;
        }
    }

    /*User()
    {
        Toast.makeText(getApplicationContext(),"constructor",Toast.LENGTH_LONG).show();
    }*/



}
