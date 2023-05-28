package com.example.artapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.equals("admin") && password.equals("admin123")) {
                    // Login successful, start the main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Finish the login activity
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Database operations example
        addData("admin", "admin123");
        updateData("admin", "newPassword");
        deleteData("admin");
        Cursor dataCursor = viewData();
        // Process the retrieved data from the cursor
        clearData();
    }

    private void addData(String username, String password) {
        boolean isDataAdded = databaseHelper.addData(username, password);
        if (isDataAdded) {
            Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData(String username, String newPassword) {
        boolean isDataUpdated = databaseHelper.updateData(username, newPassword);
        if (isDataUpdated) {
            Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData(String username) {
        boolean isDataDeleted = databaseHelper.deleteData(username);
        if (isDataDeleted) {
            Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }

    private Cursor viewData() {
        Cursor dataCursor = databaseHelper.viewData();
        // Process the retrieved data from the cursor
        return dataCursor;
    }

    private void clearData() {
        boolean isDataCleared = databaseHelper.clearData();
        if (isDataCleared) {
            Toast.makeText(this, "Data cleared successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to clear data", Toast.LENGTH_SHORT).show();
        }
    }
}
