public class AIPlayer extends Player {

    private final AIDifficulty difficulty;


    public AIPlayer(String name, AIDifficulty difficulty) {
        super(name);
        this.difficulty = difficulty;
    }

    @Override
    public String pickPosition() {
        switch (difficulty) {
            case EASY:
                // Random 80% of the time
                break;
            case MEDIUM:
                break; // " 60 "
            case HARD:
                break; // " 40 "
        }


        return ((Long) Math.round((Math.random() * 9))).toString(); //TODO write position picker algorithm.
    }
}
