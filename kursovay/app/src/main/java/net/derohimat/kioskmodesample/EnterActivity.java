package net.derohimat.kioskmodesample;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterActivity extends BaseActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private TextView loginLocked;
    private TextView attempts;
    private TextView numberOfAttempts;
    int numberOfRemainingLoginAttempts = 3;
    IgetHashOfPassword hashFunc;
    private DbHelper dbHelper;

    private ManageMultipleUsers mngMultiUsers;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableKioskMode(true);
        username = (EditText) findViewById(R.id.edit_user);
        password = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_login);
        loginLocked = (TextView) findViewById(R.id.login_locked);
        attempts = (TextView) findViewById(R.id.attempts);
        numberOfAttempts = (TextView) findViewById(R.id.number_of_attempts);
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));
        hashFunc = new getSHA256Hash();

        dbHelper = new DbHelperSqlLite(this);
        mngMultiUsers = new ManageMultipleUsers();

        //dbHelper.deleteAllUsers();
       // setUpAdmin();
        registerReceiver(new LocksreenReceiver(), new IntentFilter("android.intent.action.USER_PRESENT"));
    }

    public void Registration(View view){
        RegistrationActivity.startThisActivity(mContext);
    }




    public void Login(View view){
        // Если введенные логин и пароль будут словом "admin",
        // показываем Toast сообщение об успешном входе:


     String usernameStr =   username.getText().toString();
     String passwordStr =   username.getText().toString();

        Boolean isRightData =       dbHelper.isPasswordTrue(username.getText().toString(), hashFunc.getHashOfPassword(password.getText().toString()));

        if (isRightData) {
            Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();

            changeUser(usernameStr);
            // Выполняем переход на другой экран:
            UserProfiles.startThisActivity(mContext);
        }

        // В другом случае выдаем сообщение с ошибкой:
        else {
            Toast.makeText(getApplicationContext(), "Неправильные данные!",Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;

            // Делаем видимыми текстовые поля, указывающие на количество оставшихся попыток:
            attempts.setVisibility(View.VISIBLE);
            numberOfAttempts.setVisibility(View.VISIBLE);
            numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

            // Когда выполнено 3 безуспешных попытки залогиниться,
            // делаем видимым текстовое поле с надписью, что все пропало и выставляем
            // кнопке настройку невозможности нажатия setEnabled(false):
            if (numberOfRemainingLoginAttempts == 0) {
                login.setEnabled(false);
                loginLocked.setVisibility(View.VISIBLE);
                loginLocked.setBackgroundColor(Color.RED);
                loginLocked.setText("Вход заблокирован!!!");
            }
        }
    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

    private void changeUser(String nameOfUser)
    {
        if(nameOfUser.equals("admin"))
            mngMultiUsers.changeUserToAdminInBackground(mContext);

        else {
            int uid = dbHelper.getUidByExistName(nameOfUser);
           // mngMultiUsers.changeUserInBackground(mContext, uid);
       //     mngMultiUsers.changeUser(mContext, uid);
        }

    }




}
