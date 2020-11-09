package com.holidu.interview.assignment.service.impl;

import com.holidu.interview.assignment.integration.soda2.Soda2IntegrationAdapter;
import com.holidu.interview.assignment.integration.soda2.dto.Tree;
import com.holidu.interview.assignment.service.TreeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TreeServiceImpl implements TreeService {
    Soda2IntegrationAdapter soda2IntegrationAdapter;

    public TreeServiceImpl(Soda2IntegrationAdapter soda2IntegrationAdapter) {
        this.soda2IntegrationAdapter = soda2IntegrationAdapter;
    }

    @Override
    public Map<String, Integer> getGroupedTrees(Double x, Double y, Long radius) {
        return soda2IntegrationAdapter.getAllWithinCircle(x, y, radius).stream()
                .filter(tree -> tree.getName() != null)
                .collect(Collectors.groupingBy(Tree::getName))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }
}
