package com.holidu.interview.assignment.integration.soda2;

import com.holidu.interview.assignment.integration.soda2.dto.Tree;
import com.holidu.interview.assignment.integration.soda2.exception.SodaIntegrationException;
import com.socrata.api.Soda2Consumer;
import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.holidu.interview.assignment.utils.CoordinatesUtils.meterToFeet;

@Component
public class Soda2IntegrationAdapter {
    private static final Logger logger = LoggerFactory.getLogger(Soda2IntegrationAdapter.class);
    private final Soda2Consumer consumer;

    private final String treeDataSetId;

    public Soda2IntegrationAdapter(Soda2Consumer consumer, @Value("${integration.soda2.dataset.id}") String treeDataSetId) {
        this.consumer = consumer;
        this.treeDataSetId = treeDataSetId;
    }

    public List<Tree> getAllWithinCircle(Double x, Double y, Long radius) {
        try {

            double feet = meterToFeet(radius);
            // gain all trees inside square
            List<Tree> candidates = getAllTreesWithinSquare(x - feet, x + feet, y - feet, y + feet);
            // additional check is tree inside circle
            return candidates.stream().filter(tree -> isWithinCircle(tree, x, y, feet)).collect(Collectors.toList());
        } catch (SodaError | InterruptedException e) {
            logger.error("error during the getting trees list", e);
            throw new SodaIntegrationException("error during the getting trees list", e);
        }
    }

    private boolean isWithinCircle(Tree tree, double x, double y, double feet) {
        return Math.pow(tree.getX() - x, 2) + Math.pow(tree.getY() - y, 2) - Math.pow(feet, 2) <= 0;
    }

    private List<Tree> getAllTreesWithinSquare(double minX, double maxX, double minY, double maxY) throws SodaError, InterruptedException {
        SoqlQuery treeQuery = new SoqlQueryBuilder()
                .addSelectPhrase("spc_common")
                .addSelectPhrase("x_sp")
                .addSelectPhrase("y_sp")
                .setLimit(Integer.MAX_VALUE)
                .setWhereClause("x_sp > " + minX + " AND x_sp < " + maxX + " AND y_sp > " + minY + " AND y_sp < " + maxY)
                .build();
        return consumer.query(
                treeDataSetId,
                treeQuery,
                Tree.LIST_TYPE);
    }

}

