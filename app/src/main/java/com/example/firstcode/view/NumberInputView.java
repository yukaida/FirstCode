package com.example.firstcode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.firstcode.R;

public class NumberInputView extends LinearLayout {
    int currentNumber = 0;
    private Button minusBt;
    private Button addBt;
    private EditText numEt;

    public int getNumber() {
        return currentNumber;
    }

    public void setNumber(int value) {
        this.currentNumber = currentNumber;
    }


    public NumberInputView(Context context) {
        this(context, null);

    }

    public NumberInputView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public NumberInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.num_bt_layout, this, false);
        addView(view);
        minusBt = view.findViewById(R.id.num_bt_minus);
        addBt = view.findViewById(R.id.num_bt_add);
        numEt = view.findViewById(R.id.num_et_number);

    }

    public void setUpEvent(){
        minusBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
