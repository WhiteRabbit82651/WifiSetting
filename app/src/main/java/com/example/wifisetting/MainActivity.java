package com.example.wifisetting;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG_NAME = "WifiSetting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.d(TAG_NAME, "ResultCode is " + result.getResultCode());
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // 公式にあるのに使えないgetIntent()意味わかrな
                            // https://developer.android.com/training/basics/intents/result?hl=ja
                            Intent intent = result.getData();
                            // Handle the Intent
                        }
                        // 戻り値が何であれ、ここに帰ってきたら接続中のWifiを更新する処理を行う
                    }
                });

        // ボタンにパネル呼び出しを割付
        findViewById(R.id.button).setOnClickListener(v -> {
            mGetContent.launch(new Intent(Settings.Panel.ACTION_WIFI));
        });

    }
}