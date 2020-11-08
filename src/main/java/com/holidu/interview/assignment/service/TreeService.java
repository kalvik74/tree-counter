package com.holidu.interview.assignment.service;

import java.util.Map;

public interface TreeService {
    Map<String, Integer> getGroupedTrees(Double x, Double y, Long radius);
}
