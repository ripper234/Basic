package org.basic.google.codejam.practiceproblems.shoppinglist

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.regex.Matcher
import org.basic.google.codejam.Tester
import org.testng.annotations.Test
import static com.google.common.collect.Lists.newArrayList

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Jan 15, 2011
 * Time: 8:36:59 PM
 * To change this template use File | Settings | File Templates.
 */
class ProblemDTester extends Tester {
  @Test
  public void sample() {
    runTest "sample.in"
  }

  @Test
  public void small() {
    runTest "D-small-practice.in"
  }

  @Test
  public void large() {
    runTest "D-large-practice.in"
  }

  protected void test(List<String> lines) {
    int N = Integer.parseInt(lines.get(0));
    int line = 1;
    for (int i = 1; i <= N; ++i) {
      def (numItems, numStores, pricesOfGas) = readThreeNumbers(lines.get(line++));
      String itemsLine = lines.get(line++);
      ItemsRepo repo = parseItems(itemsLine);
      List<Store> stores = newArrayList();
      for (int s = 1; s <= numStores; ++s) {
        String storeLine = lines.get(line++);
        Store store = parseStore(repo, storeLine);
        stores.add(store);
      }
      double cost = ProblemDSolver.solve(repo, stores, (double)pricesOfGas);
      DecimalFormat decim = new DecimalFormat("0.0000000");
      final String costStr = decim.format(cost);
      printOutput i, costStr
    }
  }

  Store parseStore(ItemsRepo repo, String storeLine) {
    try {
      Matcher match = storeLine =~ /(-?\d+) (-?\d+) (.*)/
      int x = Integer.parseInt(match[0][1])
      int y = Integer.parseInt(match[0][2])
      Store store = new Store(x, y)
      for (String productStr: match[0][3].split(" ")) {
        String[] nameAndPrice = productStr.split(":");
        String name = nameAndPrice[0];
        int price = Integer.parseInt(nameAndPrice[1]);
        store.addItem repo.getId(name), price
      }
      return store
    } catch (Exception e) {
      throw new RuntimeException("Exception parsing store line '${storeLine}'", e)
    }

  }

  ItemsRepo parseItems(String line) {
    ItemsRepo repo = new ItemsRepo()
    String[] matches = line.split(/ /);
    for (String itemMatch: matches) {
      def parsed = itemMatch =~ /([a-z]*)(!?)/
      repo.addItem(new Item(parsed[0][1], parsed[0][2].length() > 0))
    }
    return repo;
  }
}
