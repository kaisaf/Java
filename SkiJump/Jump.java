
import java.util.ArrayList;
import java.util.Random;

public class Jump {
    private int totalScore;
    private int length;
    private ArrayList<Integer> scores;
    Random jump = new Random();

    public Jump() {
        this.scores = new ArrayList<>();
        this.totalScore = 0;
        addLength();
        addScore();
    }

    public void print() {
        System.out.println(this.length + " " + this.scores.toString());
    }

    public void addJump() {
        addScore();
        addLength();
    }

    public void addLength() {
        this.length = jump.nextInt(61) + 60;
    }

    public void addScore() {
        int i = 0;
        while (i < 5) {
            this.scores.add(jump.nextInt(11) + 10);
            i++;
        }
    }

    public String toString() {
        return this.length + " " + this.scores.toString();
    }

    public int getLength() {
        return this.length;
    }

    public String getScores() {
        return this.scores.toString();
    }

    public int countScores() {
        if (this.scores.isEmpty()) {
            return 0;
        } else {
            this.scores.sort(null);
            this.totalScore = this.scores.get(1) + this.scores.get(2) + this.scores.get(3) + this.length;
            return this.totalScore;
        }
    }
}
