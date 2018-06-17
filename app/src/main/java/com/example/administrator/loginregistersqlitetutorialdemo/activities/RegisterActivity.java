package com.example.administrator.loginregistersqlitetutorialdemo.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.loginregistersqlitetutorialdemo.R;
import com.example.administrator.loginregistersqlitetutorialdemo.model.User;
import com.example.administrator.loginregistersqlitetutorialdemo.sql.DatabaseHelper;

/**
 * Created by lalit on 8/27/2016.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private DatabaseHelper databaseHelper;
    private User user;

    private Button determineButton, cancelButton;
    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private String nameString, emailString, passwordString, confirmPasswordString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        getSupportActionBar().hide();                                                               // 隐藏掉整个ActionBar

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        determineButton = (Button) findViewById(R.id.determineButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        // appCompatButtonRegister.setOnClickListener(this);
        // appCompatTextViewLoginLink.setOnClickListener(this);

        determineButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.determineButton:
                postDataToSQLite();
                break;

            case R.id.cancelButton:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     * 判斷所有輸入的內容 是否符合要求, 如果都符合的話, 就將資料寫入資料庫
     */
    private void postDataToSQLite() {

        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        confirmPasswordString = confirmPasswordEditText.getText().toString().trim();

        /** 判斷所有輸入的內容, 是否符合規定要求 */
        if (nameString.isEmpty()) {
            Toast.makeText(activity, "Name 不能為空值", Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailString.isEmpty()) {
            Toast.makeText(activity, "Eamil 不能為空值", Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailString.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            Toast.makeText(activity, "Email 格式不正確", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passwordString.isEmpty()) {
            Toast.makeText(activity, "Password 不能為空值", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirmPasswordString.isEmpty()) {
            Toast.makeText(activity, "Confirm Password 不能為空值", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!passwordString.contentEquals(confirmPasswordString)) {
            Toast.makeText(activity, "Confirm Password 不正確", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!databaseHelper.checkUser(emailString)) {
            user.setName(nameString);
            user.setEmail(emailString);
            user.setPassword(passwordString);
            databaseHelper.addUser(user);
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(determineButton.getWindowToken(), 0);
            Toast.makeText(activity, "註冊成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
