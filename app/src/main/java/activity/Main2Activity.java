package activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.think.myapp.R;

import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv=findViewById(R.id.tv);
        final String[] company = new String[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    company[0] =getInputToString(Main2Activity.this.getAssets().open("company.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;

            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tv.setText(company[0]);
    }

    public String getInputToString(InputStream inputStream) {
        int len=-1;
        StringBuilder stringBuilder=new StringBuilder();
        if (inputStream==null) {
            return null;
        }
        try {
            byte[] bytes=new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
            stringBuilder.append(new String(bytes,0,0));
            }
            return stringBuilder.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
