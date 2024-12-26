package src.a03a.e2;

import java.util.Random;

public class LogicImpl implements Logic {
    private final int size;
    private Pair<Integer, Integer> botPosition;
    private Pair<Integer, Integer> playerPosition;
    private final Random random = new Random();

    public LogicImpl(final int size) {
        this.size = size;
        this.reset();
    }

    public final void reset() {
        this.playerPosition = new Pair<Integer,Integer>(random.nextInt(0, this.size), random.nextInt(0, this.size));
        do {
            this.botPosition = new Pair<Integer,Integer>(random.nextInt(0, this.size), random.nextInt(0, this.size));
        } while (this.playerPosition.equals(this.botPosition));
    }

    @Override
    public Pair<Integer, Integer> getBotPosition() {
        return this.botPosition;
    }

    @Override
    public Pair<Integer, Integer> getPlayerPosition() {
        return this.playerPosition;
    }

    @Override
    public void movePlayer(final Pair<Integer, Integer> destination) {
        if (canReach(this.playerPosition, destination)) {
            this.playerPosition = destination;
        }
    }

    private boolean canReach(final Pair<Integer, Integer> entityPosition, final Pair<Integer, Integer> destination) {
        return entityPosition.getX() == destination.getX() || entityPosition.getY() == destination.getY();
    }

    @Override
    public final boolean toQuit() {
        return this.playerPosition.equals(this.botPosition);
    }

    @Override
    public final void moveBot() {
        if (canReach(this.botPosition, this.playerPosition)) {
            this.botPosition = this.playerPosition;
        } else {
            var chooseDirection = this.random.nextInt(0, 2);
            if (chooseDirection == 0) { //X
            this.botPosition = new Pair<Integer,Integer>(randomAxisPosition(this.botPosition.getX()), this.botPosition.getY());
            } else { //Y
                this.botPosition = new Pair<Integer,Integer>(this.botPosition.getX(), randomAxisPosition(this.botPosition.getY()));
            }
        }
    }

    private final int randomAxisPosition(final int entityPosition) {
        int newPosition;
        do {
            newPosition = this.random.nextInt(0, this.size);
        } while (newPosition == entityPosition);
        return newPosition;
    }
}
