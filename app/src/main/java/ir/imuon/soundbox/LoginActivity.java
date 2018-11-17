package ir.imuon.soundbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String LOG_IN_KEY = "log_in_key";
    public static final String LOG_IN_CHECK = "log_in_check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login();

        Button btn_signUp = findViewById(R.id.btn_signUp);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login() {

        // Find Objects
        EditText edt_email = findViewById(R.id.edt_email);
        EditText edt_password = findViewById(R.id.edt_password);

        Button btn_login = findViewById(R.id.btn_login);

        // Get values
        String userEmail = edt_email.getText().toString();
        String userPassword = edt_password.getText().toString();

        btn_login.setOnClickListener(view -> {
            SharedPreferences.Editor editor =
                    getSharedPreferences(LOG_IN_CHECK, MODE_PRIVATE).edit();
            editor.putBoolean(LOG_IN_KEY, true);
//        editor.putString(USER_EMAIL_KEY, userEmail);
            editor.apply();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }
}
