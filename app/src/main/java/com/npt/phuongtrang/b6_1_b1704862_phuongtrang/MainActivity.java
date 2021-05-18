package com.npt.phuongtrang.b6_1_b1704862_phuongtrang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    EditText URL;
    TextView txtNetInfor, txtType;
    Button btnDuyetWeb, btnLoaiKetNoi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL = findViewById(R.id.edtURL);
        txtNetInfor = findViewById(R.id.txtNetworkInfor);
        txtType = findViewById(R.id.txtType);
        btnDuyetWeb = findViewById(R.id.btnDuyetWeb);
        btnLoaiKetNoi = findViewById(R.id.btnLoaiKetNoi);

        btnLoaiKetNoi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkInternetConnection();
            }
        });

        btnDuyetWeb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String url = URL.getText().toString();
                Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });
    }

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    private boolean checkInternetConnection()
    {
        connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null)
        {
            // connect to the Internet
            switch (networkInfo.getType())
            {
                case ConnectivityManager.TYPE_WIFI:
                    txtType.setText("WIFE---");
                    txtNetInfor.setText("Network OK");
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    txtType.setText("3G---");
                    txtNetInfor.setText("Network OK");
                    break;
            }
        }
        else if (networkInfo == null)
        {
            txtNetInfor.setText("No default network is currently active");
            return false;
        }

        else if (!networkInfo.isConnected())
        {
            txtNetInfor.setText("Network is not connected");
            return false;
        }

    //    txtNetInfor.setText("Network OK");
        return true;
    }

}