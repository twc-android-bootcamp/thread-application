package com.example.threadapplication.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.threadapplication.R;

public class LiveDataActivity extends AppCompatActivity {

    private Button startBtn;
    private Button stopBtn;
    private TextView content;

    private LiveDataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        startBtn = (Button) findViewById(R.id.start);
        stopBtn = (Button) findViewById(R.id.stop);
        content = (TextView) findViewById(R.id.content);

        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel.class);

        subscribeLiveData();
        setOnclickListener();
    }

    private void subscribeLiveData() {
        viewModel.getIsStarted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isStarted) {
                startBtn.setEnabled(!isStarted);
                stopBtn.setEnabled(isStarted);
            }
        });

        viewModel.getTimeTick().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                content.setText("" + aLong);
            }
        });
    }

    private void setOnclickListener() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setIsStarted(true);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setIsStarted(false);
            }
        });
    }
}