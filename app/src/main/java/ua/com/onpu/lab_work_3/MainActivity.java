package ua.com.onpu.lab_work_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button LogInButton;
    Button CreateButton;
    ArrayList<String> numberlist = new ArrayList<>();
    String log;
    private EditText login;
    private EditText passwrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogInButton = findViewById(R.id.LogInButton);
        CreateButton = findViewById(R.id.CreateButton);
        LogInButton.setOnClickListener(this);
        CreateButton.setOnClickListener(this);
        login = findViewById(R.id.editText2);
        passwrd = findViewById(R.id.editText);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.LogInButton:

                log = login.getText().toString();

                //Toast.makeText(getApplicationContext(),ecx.d,Toast.LENGTH_LONG).show();
                get_json();

                break;
            case R.id.CreateButton:
                Intent intent1 = new Intent(this, User.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);


                break;

            default:

                break;
        }
    }

    public void get_json()
    {
        String json;
        try
        {
            InputStream is = getAssets().open("users.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("login").equals(login.getText().toString()) &&
                        obj.getString("password").equals(passwrd.getText().toString()))
                {
                    numberlist.add(obj.getString("name"));
                    numberlist.add(obj.getString("gender"));
                    numberlist.add(obj.getString("age"));
                    Intent intent = new Intent(this, ContentActivity.class);
                    intent.putExtra("login", login.getText().toString());
                    intent.putExtra("password", passwrd.getText().toString());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect username or password!!!!!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            //Toast.makeText(getApplicationContext(),numberlist.toString(),Toast.LENGTH_LONG).show();
            numberlist.clear();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
