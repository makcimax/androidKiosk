package net.derohimat.kioskmodesample;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class getSHA256Hash implements IgetHashOfPassword {
    MessageDigest messageDigest;


    public String getHashOfPassword(String password) {
        byte[] passwordBytes = password.getBytes();


        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {

            IgetHashOfPassword hash = new getSimpleHash();
            return hash.getHashOfPassword(password);

        } catch (UnsupportedEncodingException e) {
            IgetHashOfPassword hash = new getSimpleHash();
            return hash.getHashOfPassword(password);
        }


    }

}

