package net.derohimat.kioskmodesample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class UserProfiles extends BaseActivity {
    private EditText mEditTextName;


    //private final List<Item> items = new ArrayList<>();
 DbHelper dbHelper;
 CursorAdapter cursorAdapter;
 Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        setContentView(R.layout.users_profiles);

        dbHelper = new DbHelperSqlLite(mContext);


   Cursor allUsers =  dbHelper.getAllUsers();
     /*   allUsers.moveToFirst();
      String  user =  allUsers.getString(allUsers
                .getColumnIndex("name"));*/

        cursorAdapter = new CursorAdapter(mContext,allUsers);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(cursorAdapter);

    }

    public void add(View view) {
        RegistrationActivity.startThisActivity(mContext);
    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, UserProfiles.class);
        context.startActivity(intent);
    }


    public void editUserByAdmin (View view)
    {
        EditUserByAdminActivity.startThisActivity(mContext);
    }




}
