public class AIPlayer extends Player {

    private AIDifficulty difficulty;


    public AIPlayer(String name, AIDifficulty difficulty) {
        super(name);
        this.difficulty = difficulty;
    }

    @Override
    public int pickPosition() {
        return -1; //TODO write position picker algorithm.
    }
}
