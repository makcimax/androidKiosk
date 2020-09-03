package net.derohimat.kioskmodesample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditUserActivity extends BaseActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_activity);
        mContext = this;
    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }


    public void changeUser(View view) {


    }
}
