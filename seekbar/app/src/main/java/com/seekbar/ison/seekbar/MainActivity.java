package com.seekbar.ison.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView1, textView2;
    private SeekBar seekBar1, seekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)this.findViewById(R.id.textview1);
        textView2 = (TextView)this.findViewById(R.id.textview2);
        seekBar1 = (SeekBar)this.findViewById(R.id.seekbar1);
        seekBar2 = (SeekBar)this.findViewById(R.id.seekbar2);

        runSeekBar();
    }

    public void runSeekBar(){
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView1.setText("seekbar1 的当前进度是:"+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView1.setText("seekbar1 开始");
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView1.setText("seekbar1 结束");
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView2.setText("seekbar2 的当前进度是:"+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView2.setText("seekbar2 开始");
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView2.setText("seekbar2 结束");
            }
        });

    }

}
