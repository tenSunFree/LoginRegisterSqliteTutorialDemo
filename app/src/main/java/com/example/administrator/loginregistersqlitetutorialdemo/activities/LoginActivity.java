package com.example.administrator.loginregistersqlitetutorialdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.loginregistersqlitetutorialdemo.R;
import com.example.administrator.loginregistersqlitetutorialdemo.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;
    private NestedScrollView nestedScrollView;
    private DatabaseHelper databaseHelper;

    private Button loginButton, registerButton;
    private EditText emailEditText, passwordEditText;
    private String emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getSupportActionBar().hide();                                                               // 隐藏掉整个ActionBar
        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                verifyFromSQLite();
                break;
            case R.id.registerButton:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     * 判斷edittext輸入的內容是否符合標準, 如果符合
     */
    private void verifyFromSQLite() {
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

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

        if (databaseHelper.checkUser(emailString, passwordString)) {
            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
            accountsIntent.putExtra("EMAIL", emailString);
            startActivity(accountsIntent);
        } else {
            Toast.makeText(activity, "輸入的Email與Password 不正確", Toast.LENGTH_SHORT).show();
        }
    }
}
