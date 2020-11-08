package com.holidu.interview.assignment.service.impl;

import com.holidu.interview.assignment.integration.soda2.Soda2IntegrationAdapter;
import com.holidu.interview.assignment.integration.soda2.dto.Tree;
import com.holidu.interview.assignment.service.TreeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.holidu.interview.assignment.utils.CoordinatUtils.meterToFoot;

@Service
public class TreeServiceImpl implements TreeService {
    Soda2IntegrationAdapter soda2IntegrationAdapter;

    public TreeServiceImpl(Soda2IntegrationAdapter soda2IntegrationAdapter) {
        this.soda2IntegrationAdapter = soda2IntegrationAdapter;
    }

    @Override
    public Map<String, Integer> getGroupedTrees(Double x, Double y, Long radius) {

        List<Tree> trees = soda2IntegrationAdapter.getAllWithinCircle(x, y, radius);
        return trees.stream()
                .collect(Collectors.groupingBy(tree -> Optional.ofNullable(tree.getName())))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(key -> key.getKey().orElse("null"), e -> e.getValue().size()));
    }
}
