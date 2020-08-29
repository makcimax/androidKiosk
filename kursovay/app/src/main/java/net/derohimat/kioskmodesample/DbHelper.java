package net.derohimat.kioskmodesample;

import android.database.Cursor;

public interface DbHelper {

   public void addUser(String uid, String name, String  hashOfPassword);
    public boolean isPasswordTrue(String name, String hashOfPassword);
    public void updateUserData(String name, String  hashOfPassword);
    public boolean isUserExist(String name);
    public void deleteAllUsers();
    public int getUidByExistName(String name);
    public Cursor getAllUsers();

}
