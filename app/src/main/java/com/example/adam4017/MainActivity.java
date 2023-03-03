package com.example.adam4017;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.nle.mylibrary.forUse.mdbus4017.MD4017;
import com.nle.mylibrary.forUse.mdbus4017.MD4017ValConvert;
import com.nle.mylibrary.forUse.mdbus4017.MD4017ValListener;
import com.nle.mylibrary.forUse.mdbus4017.Md4017VIN;
import com.nle.mylibrary.transfer.DataBusFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String tar="";
                    String a="";
                    URL url=new URL("http://api.nlecloud.com/devices/541034/Sensors/m_temp?AccessToken=95051CE513362BB08DEABD9FF64FAA7D6FB55178254B120DDE2803A13E69102ABF8149F52BE7141D820AC3C8BAC276C64B89736FC20F7B9F1A715F3BCDC5D29B55DA8068924F9199FB89A31D50F47A1D2B35F02573C5FEEF253F799897055096B1BD8BCDDB524E36B44F0FC6D425E3E3066DBE00349DA221E6E91FF08FBC11896555976D88547559359E2C65AEFA9A83FF84489C583B28DFABC5E13FBCEF6BBE307929A990AFDBC5A3F2145B9EB6F6DF091B0FF6EC3B76EA80CDD0C0EBC4CE4E28E30CAB751FCE0D67A384051BF392A1FD69D9DC026B90AC3C3A6BC9712FAAC7");
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    InputStream is=conn.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb=new StringBuilder();
                    while ((tar=reader.readLine())!=null){
                        System.out.println(tar);
                        a=tar;
                        System.out.println(a);
                        parseJSON(tar);

                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
    private  void parseJSON(String jsonData){
        try {
            JSONObject jsonObject=new JSONObject(jsonData);
            Log.e("JsonData",jsonData);
            JSONObject jsonObject1=jsonObject.getJSONObject("ResultObj");
            textView.setText(jsonObject1.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
