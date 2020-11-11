package com.holidu.interview.assignment.adapter.soda2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Soda2IntegrationAdapterTest {

    @Autowired
    Soda2IntegrationAdapter soda2IntegrationAdapter;

    @Test
    void getAllWithinCircle() {
        assertTrue(
                soda2IntegrationAdapter.getAllWithinCircle(1027632.259, 254897.07, 1L)
                        .stream()
                        .anyMatch(tree -> tree.getName().equals("pin oak"))
        );
        assertTrue(
                soda2IntegrationAdapter.getAllWithinCircle(1027612.88, 254635.6588, 1L)
                        .stream()
                        .anyMatch(tree -> tree.getName().equals("honeylocust"))
        );
    }
}