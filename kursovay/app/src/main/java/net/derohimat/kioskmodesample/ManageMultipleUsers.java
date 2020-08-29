package net.derohimat.kioskmodesample;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ManageMultipleUsers {


    @RequiresApi(api = Build.VERSION_CODES.P)
    public String createUsers(Context context, String nameOfUser) {
        String idUser = UUID.randomUUID().toString();
        long ephemeralUserId = 0;
        DevicePolicyManager dpm = (DevicePolicyManager)
                context.getSystemService(Context.DEVICE_POLICY_SERVICE);

        ComponentName adminName = dpm.getActiveAdmins().iterator().next();

// If possible, reuse an existing affiliation ID across the
// primary user and (later) the ephemeral user.
        Set<String> identifiers = dpm.getAffiliationIds(adminName);

// Pass an affiliation ID to the ephemeral user in the admin extras.
        PersistableBundle adminExtras = new PersistableBundle();

        adminExtras.putString(idUser, identifiers.iterator().next());
// Include any other config for the new user here ...

// Create the ephemeral user, using this component as the admin.
        try {


            UserHandle ephemeralUser = dpm.createAndManageUser(
                    adminName,
                    nameOfUser,
                    adminName,
                    adminExtras,
                    DevicePolicyManager.MAKE_USER_EPHEMERAL |
                            DevicePolicyManager.SKIP_SETUP_WIZARD);

            UserManager userManager = context.getSystemService(UserManager.class);

            //   UserManager userManager = context().getSystemService(UserManager.class);
             ephemeralUserId = userManager.getSerialNumberForUser(ephemeralUser);


        } catch (UserManager.UserOperationException e) {
            if (e.getUserOperationResult() ==
                    UserManager.USER_OPERATION_ERROR_MAX_USERS) {
                // Find a way to free up users...
            }
        }


        String[] restrictions = {
                UserManager.DISALLOW_FACTORY_RESET,
                UserManager.DISALLOW_SAFE_BOOT,
                UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA,
                UserManager.DISALLOW_ADD_USER};


        for (String restriction: restrictions) dpm.clearUserRestriction(adminName,restriction);
       // for (String restriction: restrictions) dpm.addUserRestriction(adminName, restriction);


        return Long.toString(ephemeralUserId);
    }




    public boolean isUserAdmin(String nameOfUser,int uid, String hashOfPassword)
    {
        //TODO
        //Change this
        // How to store password


if(nameOfUser.equals("admin") &&(nameOfUser.equals("123")))


    return true;
return false;

    }


public void changeUserInBackground(Context context, int uid)
{




    UserManager userManager = context.getSystemService(UserManager.class);

    UserHandle userById = userManager.getUserForSerialNumber(uid);

    DevicePolicyManager dpm = (DevicePolicyManager)
            context.getSystemService(Context.DEVICE_POLICY_SERVICE);



    ComponentName adminName = dpm.getActiveAdmins().iterator().next();

    dpm.startUserInBackground(adminName, userById);

};

    public void changeUser(Context context, int uid)
    {
        UserManager userManager = context.getSystemService(UserManager.class);

        UserHandle userById = userManager.getUserForSerialNumber(uid);
        DevicePolicyManager dpm = (DevicePolicyManager)
                context.getSystemService(Context.DEVICE_POLICY_SERVICE);

        ComponentName adminName = dpm.getActiveAdmins().iterator().next();

        dpm.switchUser(adminName, userById);
    };



public int getMaxCountOfUsers(Context context)
{
  //TODO
    // add termux programm
   // pm get-max-users
return 4;
}


public void changeUserToAdmin(Context context)
{
    DevicePolicyManager dpm = (DevicePolicyManager)
            context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    ComponentName adminName = dpm.getActiveAdmins().iterator().next();

    dpm.startUserInBackground(adminName, null);
}



    public void changeUserToAdminInBackground(Context context)
    {
        DevicePolicyManager dpm = (DevicePolicyManager)
                context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminName = dpm.getActiveAdmins().iterator().next();

        dpm.startUserInBackground(adminName, null);

    }




}
