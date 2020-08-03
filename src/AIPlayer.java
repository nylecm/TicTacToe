public class AIPlayer extends Player {

    private AIDifficulty difficulty;


    public AIPlayer(String name, AIDifficulty difficulty) {
        super(name);
        this.difficulty = difficulty;
    }

    @Override
    public String pickPosition() {
        return ((Long) Math.round((Math.random() * 9))).toString(); //TODO write position picker algorithm.
    }
}
