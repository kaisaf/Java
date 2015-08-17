


public class TotalScore implements Comparable<TotalScore> {
    private String name;
    private int scores;
    
    public TotalScore(String name, int scores) {
        this.name = name;
        this.scores = scores;
    }
    
    public int getScores() {
        return this.scores;
    }
    
    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(TotalScore comparable) {
        return this.scores - comparable.getScores();
    }
}
