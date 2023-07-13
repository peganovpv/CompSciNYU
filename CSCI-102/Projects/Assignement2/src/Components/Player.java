package Components;

public class Player {

    private String name;
    private int score = 0;
    private int position = 0;

    public Player(String name) {
        this.name = name;
    }

    public void reset() {

        this.position = 0;
        this.score = 0;

    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int p) {
        this.position = p;
    }

    public void addScore(int s) {
        this.score += s;
    }

}
