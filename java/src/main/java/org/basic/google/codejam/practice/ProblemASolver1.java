package org.basic.google.codejam.practice;

import java.util.ArrayList;
import java.util.List;

public class ProblemASolver1 {
    private final int height;
    private final int width;
    public Color[] matrix;

    public ProblemASolver1(int white, int black) {
        height = white + black + 1;
        width = black + 1;
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
        Color color = new ProblemASolver1(white, black).solve(white, black);
        return color.toString().toUpperCase();
    }

    private Color solve(int white, int black) {
        if (white < 0 || black < 0 || white + black == 0)
            throw new RuntimeException();

        if (get(white, black) != Color.NotCalculatedYet)
            return get(white, black);

        if (white == 0 && black == 1) {
            return Color.Black;
        }
        if (black == 0 && white == 1) {
            return Color.White;
        }

        List<Color> choices = new ArrayList<Color>();
        if (white > 0)
            choices.add(solve(white - 1, black));
        if (black >= 2)
            choices.add(solve(white + 1, black - 2));

        final Color result = findResult(choices);
        set(white, black, result);
        return result;
    }

    private Color get(int white, int black) {
        try {
            return matrix[getAddr(white, black)];
        } catch (Exception e) {
            throw new RuntimeException("Problem accessing (" + white + ", " + black + ")");
        }
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

