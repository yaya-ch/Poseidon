package com.nnk.poseidon;

import com.nnk.poseidon.controllers.api.BidListApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PoseidonApplicationTests {

    @Autowired
    private BidListApiController controller;
    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

}
