package com.nickmillward.eventinviteconcept.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nickmillward.eventinviteconcept.R;

/**
 * //////// WORK IN PROGRESS ////////
 */

public class InviteConfirmActivity extends AppCompatActivity {

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_confirm);

        confirmButton = (Button) findViewById(R.id.btn_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InviteConfirmActivity.this, EventDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

}
