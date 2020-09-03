package net.derohimat.kioskmodesample;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegistrationActivity extends BaseActivity {
    private EditText username;
    private EditText password;
    private TextView userIsRegistrated;
    private IgetHashOfPassword hashFunc;
    private DbHelper db;
    private ManageMultipleUsers mngMultiUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = (EditText) findViewById(R.id.edit_user);
        password = (EditText) findViewById(R.id.edit_password);
        userIsRegistrated = (EditText) findViewById(R.id.userIsRegistrated);

        db = new DbHelperSqlLite(this);
        hashFunc = new getSHA256Hash();

        mngMultiUsers = new ManageMultipleUsers();


    }


    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        context.startActivity(intent);
    }


    public void RegistrateUser(View view) {
        db = new DbHelperSqlLite(this);

        String userNameStr = username.getText().toString();
        String passwordStr = password.getText().toString();
        boolean isUserInDb = db.isUserExist(userNameStr);

        if (!isUserInDb) {

            Toast.makeText(getApplicationContext(), "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
            String uid = mngMultiUsers.createUsers(mContext, userNameStr);


            db.addUser(uid, userNameStr, hashFunc.getHashOfPassword(passwordStr));
            EnterActivity.startThisActivity(this);
        } else {
            userIsRegistrated.setVisibility(View.VISIBLE);
            userIsRegistrated.setText("Такой пользователь уже существует");
        }

    }
}









