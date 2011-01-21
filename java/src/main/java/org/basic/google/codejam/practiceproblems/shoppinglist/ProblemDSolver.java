package org.basic.google.codejam.practiceproblems.shoppinglist;

import org.basic.datastructures.geometry.Point;
import org.basic.datastructures.tuple.Tuple;
import org.basic.datastructures.tuple.TupleType;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class ProblemDSolver {
    private final ItemsRepo repo;
    private final List<Store> stores;
    private final double gasPrice;
    private final Map<Tuple, Double> costs = new HashMap<Tuple, Double>();
    private final static Point home = new Point(0, 0);

    public TupleType tupleType = TupleType.DefaultFactory.create(Set.class, Store.class);

    public ProblemDSolver(ItemsRepo repo, List<Store> stores, double gasPrice) {

        this.repo = repo;
        this.stores = stores;
        this.gasPrice = gasPrice;
    }

    public static double solve(ItemsRepo repo, List<Store> stores, double gasPrice) {
        return new ProblemDSolver(repo, stores, gasPrice).solveIt();
    }

    private double solveIt() {
        return solveIt(repo.getItemIds(), null);
    }

    private Double solveIt(Set<Integer> targetItems, Store currentLocation) {
        Tuple tuple = tupleType.createTuple(targetItems, currentLocation);
        final Double price = costs.get(tuple);
        if (costs.containsKey(tuple))
            return price;

        if (targetItems.isEmpty()) {
            if (currentLocation == null)
                return 0D;

            return gasPrice * distance(currentLocation, null);
        }

        // go over all stores worth visiting
        Double min = null;
        for (Store nextStore : stores) {
            if (nextStore == currentLocation)
                continue;
            Double cost = solveUsingNextStore(nextStore, targetItems, currentLocation);
            if (cost == null)
                continue;

            if (min == null || min > cost)
                min = cost;
        }
        if (currentLocation != null) {
            Double goHomeNow = solveUsingNextStore(null, targetItems, currentLocation);
            if (min == null || (goHomeNow != null && goHomeNow < min))
                min = goHomeNow;
        }

        costs.put(tuple, min);
        return min;
    }

    private Double solveUsingNextStore(Store nextStore, Set<Integer> targetItems, Store currentStore) {
        // System.out.println("Solving using store " + nextStore);
        if (currentStore == null) {
            // nothing to buy here
            final Double solution = solveIt(targetItems, nextStore);
            if (solution == null)
                return null;

            return gasPrice * nextStore.getLocation().distance(home) + solution;
        }

        // let's assume we have to buy at least one item at the current store, otherwise there's no point going there.
        Set<Integer> potentialItems = new HashSet<Integer>(targetItems);
        potentialItems.retainAll(currentStore.getItems());

        if (nextStore != null) {
            // not point in buying perishables
            potentialItems = filterPerishables(potentialItems);
        }

        if (potentialItems.isEmpty())
        {
            // technically, it might still be possible to walk through this store on the way to another one.
            // without buying anything. But, there's no use in doing that, so let's pretend we can't.
            return null;
        }

        Double min = null;
        Map<Integer, Boolean> purchase = initPurchaseSet(potentialItems);
        boolean gotPurchase = true;
        for (; gotPurchase; gotPurchase = generateNextPurchaseSet(purchase)) {
            double priceOfCurrentPurchase = subTotal(purchase, currentStore);
            Set<Integer> targetItemsAfterPurchase = new HashSet<Integer>(targetItems);
            targetItemsAfterPurchase.removeAll(getSet(purchase));
            Double nextCost = solveIt(targetItemsAfterPurchase, nextStore);
            if (nextCost == null)
                continue;
            if (min == null || min > nextCost + priceOfCurrentPurchase)
                min = nextCost + priceOfCurrentPurchase;
        }
        if (min == null)
            return null;

        return min + gasPrice * distance(currentStore, nextStore);
    }

    private Map<Integer, Boolean> initPurchaseSet(Set<Integer> potentialItems) {
        Map<Integer, Boolean> purchase = new HashMap<Integer, Boolean>();
        for (int potential : potentialItems) {
            purchase.put(potential, true);
        }
        return purchase;
    }

    private Collection<Integer> getSet(Map<Integer, Boolean> purchase) {
        List<Integer> result = newArrayList();
        for (Map.Entry<Integer, Boolean> entry : purchase.entrySet()) {
            if (entry.getValue())
                result.add(entry.getKey());
        }
        return result;
    }

    private double subTotal(Map<Integer, Boolean> purchase, Store currentStore) {
        double result = 0;
        for (Map.Entry<Integer, Boolean> entry : purchase.entrySet()) {
            if (!entry.getValue())
                continue;

            result += currentStore.getCost(entry.getKey());
        }
        return result;
    }

    private boolean generateNextPurchaseSet(Map<Integer, Boolean> purchase) {
        for (Map.Entry<Integer, Boolean> entry : purchase.entrySet()) {
            if (entry.getValue()) {
                entry.setValue(false);
                break;
            }
            entry.setValue(true);
        }

        for (Map.Entry<Integer, Boolean> entry : purchase.entrySet()) {
            if (entry.getValue())
                return true;
        }
        return false;
    }


    private Set<Integer> filterPerishables(Set<Integer> potentialItems) {
        Set<Integer> result = newHashSet();
        for (int item : potentialItems) {
            if (!repo.isPerishable(item))
                result.add(item);
        }
        return result;
    }

    private static double distance(Store source, Store target) {
        if (target == null)
            return source.getLocation().distance(home);
        if (source == null)
            return target.getLocation().distance(home);
        return source.getLocation().distance(target.getLocation());
    }

}

