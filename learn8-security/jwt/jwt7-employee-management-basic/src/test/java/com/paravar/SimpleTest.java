package com.paravar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SimpleTest {

   /* final PasswordEncoder passwordEncoder;

    SimpleTest(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }*/

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void test(){
      var s =  this.passwordEncoder.encode("password");
        System.out.println(s);
    }

}
