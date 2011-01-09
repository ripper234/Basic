package org.basic.google.codejam.practice;

import java.util.ArrayList;
import java.util.List;

public class ProblemASolver {
    private final int height;
    private final int width;
    public Color[] matrix;

    public ProblemASolver(int white, int black) {
        height = 2 * (white + 1);
        width = 2 * (black + 1);
        matrix = new Color[height * width];
        for (int i = 0; i < matrix.length; ++i)
            matrix[i] = Color.NotCalculatedYet;
    }

    private int getAddr(int white, int black) {
        return white * width + black;
    }

    enum Color {
        NotCalculatedYet, White, Black, Unknown
    }

    public static String staticSolve(int white, int black) {
        Color color = new ProblemASolver(white, black).solve(white, black);
        return color.toString();
    }

    private Color solve(int white, int black) {
        if (white < 0 || black < 0 || white + black == 0)
            throw new RuntimeException();

        if (get(white, black) != Color.NotCalculatedYet)
            return get(white, black);

        if (white <= 0) {
            return Color.Black;
        }
        if (black == 0) {
            return Color.White;
        }

        List<Color> choices = new ArrayList<Color>();
        choices.add(solve(white - 1, black));
        if (black >= 2)
            choices.add(solve(white + 1, black - 2));
        if (black > 0)
            choices.add(solve(white - 1, black));

        final Color result = findResult(choices);
        set(white, black, result);
        return result;
    }

    private Color get(int white, int black) {
        return matrix[getAddr(white, black)];
    }

    private void set(int white, int black, Color result) {
        matrix[getAddr(white, black)] = result;
    }

    private static Color findResult(List<Color> choices) {
        if (choose(choices))
            return choices.get(0);

        return Color.Unknown;
    }

    private static boolean choose(List<Color> choices) {
        Color first = choices.get(0);
        for (int i = 1; i < choices.size(); ++i)
            if (first != choices.get(i))
                return false;

        return true;
    }
}
