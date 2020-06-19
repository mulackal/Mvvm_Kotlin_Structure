/*
package com.vip.mvvm_setup.ui.dialoge;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




public class LogoutDialogue extends Dialog {

    //  LogoutDataBinding logoutDataBinding;

    private Activity home;
    MainActivity mainActivity;
    TextView sendbtn;
    ImageView close_btn;
    private SharedPreferenceUtils preferenceUtils;

    public LogoutDialogue(MainActivity mainActivity, Activity home) {
        super(home);
        this.home = home;
        this.mainActivity = mainActivity;
        //  mView = (PreviousOrderContract.View) home;
        preferenceUtils = new SharedPreferenceUtils(home);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
      */
/*  logoutDataBinding = DataBindingUtil.setContentView(home, R.layout.logout_popup);
        logoutDataBinding.setLogoutviewModel(ViewModelProviders.of(mainActivity).get(LogoutViewModel.class));
      *//*

        setContentView(R.layout.logout_popup);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        OnInit();


    }

    private void OnInit() {

        sendbtn = findViewById(R.id.logout_btn);
        close_btn = findViewById(R.id.close_btn);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(home, "Logged out", Toast.LENGTH_SHORT).show();
                dismiss();

                preferenceUtils.setValue(SharedValues.IS_LOGGED_IN, false);

                try {
                    mainActivity.finish();
                }catch (Exception e){e.printStackTrace();}

                Intent intent = new Intent(home, LoginActivity.class);
                home.startActivity(intent);

            }
        });
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


}*/
