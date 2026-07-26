package com.leaning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OssTest {

    @Test
    public void contextLoads() {
        String ak = System.getenv("OSS_ACCESS_KEY_ID");
        String sk = System.getenv("OSS_ACCESS_KEY_SECRET");
        System.out.println("OSS_ACCESS_KEY_ID：" + ak);
        System.out.println("OSS_ACCESS_KEY_SECRET：" + sk);
    }
}