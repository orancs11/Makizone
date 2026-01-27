package com.Oran.Makizone.Utilities;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Hasher {

    public static String hashed_password(String password)  {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        byte[] hash;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try{
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("There is no such an algorithm to hash");
            throw new RuntimeException(e);
        }
        catch (InvalidKeySpecException e) {
            System.out.println("Key Spec is broken!");
            throw new RuntimeException(e);
        }
        return Arrays.toString(hash);
    }
}
