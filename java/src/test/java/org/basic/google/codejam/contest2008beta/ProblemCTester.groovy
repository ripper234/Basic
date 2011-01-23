package org.basic.google.codejam.contest2008beta

import java.util.regex.Matcher
import org.basic.datastructures.graphs.Edge
import org.basic.datastructures.graphs.Graph
import org.basic.google.codejam.Tester
import org.testng.annotations.Test
import static com.google.common.collect.Maps.newHashMap

class ProblemCTester extends Tester {

  @Test
  void sample() {
    runTest "sample-C.in"
  }

  @Test
  void small() {
    runTest "C-small-practice.in"
  }

  @Test
  void large() {
    runTest "C-large-practice.in"
  }

  protected void test(List<String> lines) {
    int N = Integer.parseInt(lines.get(0))

    int lineNumber = 1;
    for (int i = 1; i <= N; ++i) {
      String header = lines.get(lineNumber++)
      Matcher match = header =~ /(\d+) (.*)/
      int roads = Integer.parseInt(match[0][1]);
      String starting = match[0][2]

      Graph g = new Graph()
      nodeId = 1;
      Map<String, Integer> nodeNames = newHashMap()
      for (int j = 0; j < roads; ++j) {
        String line = lines.get(lineNumber++)
        match = line =~ /(.*) (.*) (\d+)/
        String from = match[0][1]
        String to = match[0][2]
        int fromId = translate(from, nodeNames)
        int toId = translate(to, nodeNames)
        int cost = Integer.parseInt(match[0][3])
        g.addOneWay(new Edge (fromId, toId, cost))
      }

      int startNodeId = nodeNames.get(starting);

      List<Double> probs = RandomRoute.getProbabilities(g, startNodeId);
      String result = "";
      for (double d : probs) {
        result += doubleToString(d) + " ";
      }
      printOutput i, result
    }
  }

  int nodeId;

  int translate(String name, HashMap<String, Integer> hashMap) {
    Integer id = hashMap.get(name);
    if (id != null)
      return id;

    hashMap.put(name, nodeId);
    return nodeId++;
  }

}