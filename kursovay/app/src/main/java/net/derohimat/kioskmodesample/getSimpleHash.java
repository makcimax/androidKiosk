package net.derohimat.kioskmodesample;

import java.security.NoSuchAlgorithmException;

public class getSimpleHash implements IgetHashOfPassword {
    public getSimpleHash()
    {}


    public String getHashOfPassword(String password)
    {
        return Integer.toString(password.hashCode());
    }


}
