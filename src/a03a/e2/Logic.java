package src.a03a.e2;

public interface Logic {

    void reset();

    Pair<Integer, Integer> getBotPosition();

    Pair<Integer, Integer> getPlayerPosition();

    void movePlayer(Pair<Integer, Integer> destination);

    boolean toQuit();

    void moveBot();
} 