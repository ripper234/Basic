package org.basic.riddles.ihasleetcodes

import org.apache.commons.lang.ArrayUtils
import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals

public class BestSumGameTester {
  @Test
  void sample() {
    def arr = [3, 2, 2, 3, 1, 2];
    int result = new GameRunner(arr).play()
    assertEquals(result, 8)
  }
}

public class GameRunner {
  int player1;
  int player2;
  int turn;

  int a;
  int b;
  int[] arr

  public GameRunner(List<Integer> list) {
    arr = toIntArray(list);
  }

  public int play() {
    a = 0;
    b = arr.length - 1;
    turn = 0;
    player1 = 0;
    player2 = 0;

    BestSumGame game = new BestSumGame(arr)
    while (b >= a) {
      int move = game.getBestMove(a, b);
      if (move == b) {
        addToCurrentPlayer(arr[b]);
        --b;
      }
      else {
        addToCurrentPlayer(arr[a]);
        ++a;
      }
      turn = 1 - turn;
    }
    return player1;
  }

  void addToCurrentPlayer(int x) {
    if (turn == 0)
      player1 += x;
    else
      player2 += x;
  }

  public static int[] toIntArray(List<Integer> list) {
    return ArrayUtils.toPrimitive(list.toArray(new Integer[list.size()]));
  }
}