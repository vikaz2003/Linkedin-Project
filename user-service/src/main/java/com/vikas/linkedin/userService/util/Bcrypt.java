package com.vikas.linkedin.userService.util;


import org.mindrot.jbcrypt.BCrypt;

import static org.mindrot.jbcrypt.BCrypt.checkpw;

public class Bcrypt {

    public static String hash(String s){
        return BCrypt.hashpw(s,BCrypt.gensalt());
    }

    public static boolean match(String passwordText,String passwordHashed){
        return checkpw(passwordText,passwordHashed);
    }
}
