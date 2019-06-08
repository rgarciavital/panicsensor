package com.recollective.panicsensor.shake_sample;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.recollective.panicsensor.R;


import com.recollective.panicsensor.core.ShakeCallback;
import com.recollective.panicsensor.core.ShakeDetector;
import com.recollective.panicsensor.core.ShakeOptions;

/**
 * @author netodevel
 */
public class MainActivity extends AppCompatActivity {

    private ShakeDetector shakeDetector;
    Button btnStopService;
    Button btn_start;
    EditText txt_interval;
    EditText txt_shakecount;
    SeekBar sk_sensi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_start = (Button)findViewById(R.id.btn_start);
        txt_interval = (EditText)findViewById(R.id.txt_interval);
        txt_shakecount = (EditText)findViewById(R.id.txt_shakecount);
        sk_sensi = (SeekBar) findViewById(R.id.sk_sensi);

        btnStopService = (Button) findViewById(R.id.btnStopService);
        btnStopService.setVisibility(View.GONE);
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("destroy", "destroy service shake");
                shakeDetector.stopShakeDetector(getBaseContext());
                btn_start.setVisibility(View.VISIBLE);
                btnStopService.setVisibility(View.GONE);
            }
        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sensi = String.valueOf(sk_sensi.getProgress())+".0f";
                btn_start.setVisibility(View.GONE);
                btnStopService.setVisibility(View.VISIBLE);
                ShakeOptions options = new ShakeOptions()
                        .background(false)
                        .interval(Integer.parseInt(txt_interval.getText().toString()))
                        .shakeCount(Integer.parseInt(txt_shakecount.getText().toString()))
                        .sensibility(Float.parseFloat(sensi));

                shakeDetector = new ShakeDetector(options).start(MainActivity.this, new ShakeCallback() {
                    @Override
                    public void onShake() {
                        Log.d("event", "onShake");
                        Toast.makeText(MainActivity.this,"Se detecto patron",Toast.LENGTH_LONG).show();

                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(MainActivity.this);
                        }
                        builder.setTitle("Bache")
                                .setMessage("Se detecto movimiento de patron")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                });
            }
        });

        //IF YOU WANT JUST IN BACKGROUND
        //this.shakeDetector = new ShakeDetector(options).start(this);
    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        shakeDetector.destroy(getBaseContext());
        super.onDestroy();
    }

}
