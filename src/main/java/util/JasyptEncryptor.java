package util;

import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Created by Nicolás on 18/02/2017.
 */
public class JasyptEncryptor {

    private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

    public boolean checkPassword(String inputPassword, String encryptedPassword){
        return encryptor.checkPassword(inputPassword, encryptedPassword);
    }

    public String encryptPassword(String password){
        return encryptor.encryptPassword(password);
    }
}
