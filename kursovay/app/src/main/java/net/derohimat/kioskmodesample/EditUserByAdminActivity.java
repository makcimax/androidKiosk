package net.derohimat.kioskmodesample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class EditUserByAdminActivity extends BaseActivity {
Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_activity_by_admin);
        mContext = this;
    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, EditUserByAdminActivity.class);
        context.startActivity(intent);
    }


}


