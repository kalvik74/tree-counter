package com.holidu.interview.assignment.rest;

import com.holidu.interview.assignment.service.TreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.*;

@RestController
@Validated
public class TreeResource {
    private static final Logger logger = LoggerFactory.getLogger(TreeResource.class);
    private final TreeService treeService;

    public TreeResource(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/trees")
    Map<String, Integer> trees(@RequestParam Double x, @RequestParam Double y, @RequestParam @Min(1) Long radius) {
        long start = System.currentTimeMillis();
        Map<String, Integer> trees = treeService.getGroupedTrees(x, y, radius);
        logger.info("PROCESS TIME: " + (System.currentTimeMillis() - start) + " ms");
        return trees;
    }
}
