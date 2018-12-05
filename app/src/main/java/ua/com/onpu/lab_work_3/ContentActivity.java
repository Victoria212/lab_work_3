package ua.com.onpu.lab_work_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ContentActivity extends AppCompatActivity {
    TextView name;
    TextView age;
    TextView gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        name = findViewById(R.id.textView4);
        age = findViewById(R.id.textView5);
        gender = findViewById(R.id.textView6);
        json_reader();
        // getIntent().getStringExtra("login");
        // getIntent().getStringExtra("password");

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    void json_reader()
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
                if(obj.getString("login").equals(getIntent().getStringExtra("login")) &&
                        obj.getString("password").equals(getIntent().getStringExtra("password")))
                {

                    name.setText(obj.getString("name") );
                    age.setText(obj.getString("age") );
                    gender.setText(obj.getString("gender") );
                }
            }


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
