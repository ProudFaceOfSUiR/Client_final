package com.company.Login;

//import com.company.PostgreSQL.Check;
import com.company.database.Terminal;
import com.company.exceptions.OperationCanceledException;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class User implements Serializable {
    private String password;
    private String login;
    private boolean newUser;
    private int id;

    public boolean getNew(){
        return this.newUser;
    }



    public void setId(int id) {
        this.id = id;
    }

    public static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-224
            MessageDigest md = MessageDigest.getInstance("SHA-224");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValid(){
        if ((this.password!=null&&this.login!=null&&this.login.length()>1&&this.password.length()>1)){
            return true;
        }else {return false;}
    }

    public void initiate() throws OperationCanceledException {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while(!done) {
            if (Terminal.binaryChoice("sign in")) {
                System.out.println("Enter login");
                this.login = scanner.nextLine();
                System.out.println("Enter password");
                this.password = scanner.nextLine();
                done = true;
                newUser = false;
            } else if(!done && Terminal.binaryChoice("sign up")){
                System.out.println("Enter login");
                this.login = scanner.nextLine();
                System.out.println("Enter password");
                this.password = scanner.nextLine();
                done = true;
                newUser = true;
            }
        }

    }

    public String getPassword() {
        return password;
    }
    public String getLogin(){
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

