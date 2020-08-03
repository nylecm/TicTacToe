public abstract class Player {

    private static int nextId = 0;

    private String name;
    private int id = nextId++;

    public Player(String name) {
        this.name = name;
    }

    public abstract int pickPosition();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Player.nextId = nextId;
    }
}
