package com.ibm.firstaidhelper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileScrollingActivity extends AppCompatActivity {

    private EditText nameUserProfileET;

    private EditText usrMedCondET;
    private EditText usrMedNotesET;
    private EditText usrAllergiesET;
    private EditText usrMedicationsET;
    private EditText usrBloodTypeET;
    private EditText usrEmailET;
    private EditText usrPhoneET;
    private Button usrModifyB;
    private Button usrCancelB;
    private Button usrSaveB;
    private Drawable originalDrawable;
    private int originalInputType;
    private int stateOfUser = GlobalSettings.getStateOfUser();
    private int certVerified = GlobalSettings.getCertVerified();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CircleImageView profilePhoto = findViewById(R.id.profile_image);
        TextView nameUserProfileTV = findViewById(R.id.nameTextView);
        nameUserProfileET = findViewById(R.id.nameUserEditText);
        Button camera1_button = findViewById(R.id.camera1_button);

        usrMedCondET = findViewById(R.id.user_profile_med_condition_editText);
        usrMedNotesET =  findViewById(R.id.user_profile_med_notes_editText);
        usrAllergiesET = findViewById(R.id.user_profile_allergies_editText);
        usrMedicationsET = findViewById(R.id.user_profile_medications_editText);
        usrBloodTypeET = findViewById(R.id.user_profile_blood_type_editText);
        CheckBox usrVerifiedCB = findViewById(R.id.verified_checkBox);
        Button usrCertPhB = findViewById(R.id.add_cert_photo_button);
        usrEmailET = findViewById(R.id.email_usr_profile_editText);
        usrPhoneET = findViewById(R.id.user_profile_phone_editText);
        usrModifyB = findViewById(R.id.modify_personal_data_button);
        usrCancelB = findViewById(R.id.cancel_button);
        usrSaveB = findViewById(R.id.save_button);
        originalDrawable = usrMedCondET.getBackground();
        originalInputType = usrMedCondET.getInputType();

        //CODE
        newUserOrOldUser();
        validateCertification(usrVerifiedCB);
        usrModifyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonsFocusable(true);
                usrModifyB.setVisibility(View.GONE);
                usrCancelB.setVisibility(View.VISIBLE);
                usrSaveB.setVisibility(View.VISIBLE);
            }
        });

        usrCancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 0 scenario
                if (stateOfUser == 0) {
                    startActivity(new Intent(UserProfileScrollingActivity.this, SignUp.class));
                } else {
                    setButtonsFocusable(false); // 1 scenario
                }

            }
        });

        usrSaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //0 scenario
                if (stateOfUser == 0) {
                    GlobalSettings.setStateOfUser(1);
                    finish();
                    startActivity(new Intent(UserProfileScrollingActivity.this, MainActivity.class));
                    setButtonsFocusable(false);
                } else {
                    setButtonsFocusable(false); // 1 scenario
                }
            }
        });
    }

    private void newUserOrOldUser() {
        if (stateOfUser == 0) {
            setButtonsFocusable(true);
            usrModifyB.setVisibility(View.GONE);
        } else if (stateOfUser == 1) {
            setButtonsFocusable(false);
        }
    }

    private void defaultButtons() {

        usrModifyB.setVisibility(View.VISIBLE);
        usrCancelB.setVisibility(View.INVISIBLE);
        usrSaveB.setVisibility(View.INVISIBLE);
    }

    private void setButtonsFocusable(boolean focus) {
        if (focus) {
            enableEditText(nameUserProfileET);
            enableEditText(usrMedCondET);
            enableEditText(usrMedNotesET);
            enableEditText(usrAllergiesET);
            enableEditText(usrMedicationsET);
            enableEditText(usrBloodTypeET);
            enableEditText(usrEmailET);
            usrEmailET.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            enableEditText(usrPhoneET);
            usrPhoneET.setInputType(InputType.TYPE_CLASS_PHONE);
        } else {
            defaultButtons();
            disableEditText(nameUserProfileET);
            disableEditText(usrMedCondET);
            disableEditText(usrMedNotesET);
            disableEditText(usrAllergiesET);
            disableEditText(usrMedicationsET);
            disableEditText(usrBloodTypeET);
            disableEditText(usrEmailET);
            disableEditText(usrPhoneET);
        }
    }

    private void disableEditText(EditText editText) {
        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setInputType(InputType.TYPE_NULL);
    }

    private void enableEditText(EditText editText){
        editText.setBackground(originalDrawable);
        editText.setInputType(originalInputType);
    }

    private void validateCertification (final CheckBox checkBox) {
        if (certVerified == 0) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        GlobalSettings.setCertVerified(1);
                        checkBox.setEnabled(false);
                    }
                }
            });
        }else {
            checkBox.setChecked(true);
            checkBox.setEnabled(false);
        }
    }

}
