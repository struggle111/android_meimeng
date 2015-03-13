package com.swater.meimeng.activity.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.meimeng.app.R;
import com.swater.meimeng.activity.reg.RegChoose;

/**
 * Created by shi-02 on 2015/3/13.
 */
public class CustomDialog extends Dialog {

    private Context context;
    public CustomDialog(Context context){
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context, int theme){
        super(context,theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visitor_guide_dialog);

        Button button = (Button)findViewById(R.id.visitor_guide_dialog_cancel_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Button registerButton = (Button)findViewById(R.id.visitor_guide_dialog_register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegChoose.class);
                context.startActivity(intent);
                dismiss();
            }
        });
    }
}


