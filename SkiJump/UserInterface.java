
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private HashMap<String, ArrayList<Jump>> statistics;
    

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.statistics = new HashMap<>();
    }

    void start() {
        int round = 1;
        System.out.println("Ski Jump World Tour 2015");
        System.out.println("");
        System.out.println("Enter participant names one by one, leave blank when done and start jumping");
        while (true) {
            System.out.print("  Participant name: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                break;
            } else {
                this.statistics.put(name, null);
            }
        }
        System.out.println("");
        System.out.println("Competition begins!");
        System.out.println("");
        while (true) {

            System.out.println("Write \"jump\" if you want to jump, otherwise leave blank and competion is over");
            String jump = scanner.nextLine();
            if (jump.equals("jump")) {
                System.out.println("");
                System.out.println(round + ". round");
                System.out.println("");
                System.out.println("Jump order: ");
                jumpingOrder();
                System.out.println("");
                System.out.println("Round " + round + " results");

                for (String names : this.statistics.keySet()) {
                    Jump next = new Jump();
                    addJump(names, next);
                    System.out.println("  " + names);
                    System.out.println("    length: " + next.getLength());
                    System.out.println("    judges: " + next.getScores());
                }
                round++;
                System.out.println("");

            } else {
                break;
            }
        }
        System.out.println("");
        System.out.println("Thanks!\n" + 
                "\n" + 
                "Final Results:\n" + 
                "Rank    Name");
        
        printFinalResults(round);

    }

    public void jumpingOrder() {
        int i = 1;
        int a = 0;
        List<TotalScore> list = new ArrayList<>();
        for (String names : this.statistics.keySet()) {
            if (this.statistics.get(names) == null) {
                System.out.println("  " + i + ". " + names + " (0 points)");
                i++;
            } else {
                list.add(new TotalScore(names, totalScore(names)));
            }
        }
        Collections.sort(list);
        while (a < list.size()) {
            System.out.println("  " + (a + 1) + ". " + list.get(a).getName() + " (" + list.get(a).getScores() + " pistettä)");
            a++;
        }
    }

    public void addJump(String name, Jump newJump) {
        ArrayList<Jump> jumps = this.statistics.get(name);
        if (jumps == null) {
            jumps = new ArrayList<>();
        }
        jumps.add(newJump);
        this.statistics.put(name, jumps);
    }
    
    public void printFinalResults(int round) {
        int rank = 1;
        int index = 0;
        List<TotalScore> newTotal = new ArrayList<>();
        newTotal = totalScoreReversed();
        while (index < newTotal.size()) {
            System.out.println(rank + "       " + newTotal.get(index).getName() + " (" + newTotal.get(index).getScores() + " points)");
        
            rank++;
            int a = 1;
            int b = 2;
            for (Jump jumps : this.statistics.get(newTotal.get(index).getName())) {
                if (a == 1) {
                    System.out.print("          lengths of jumps: ");
                    a++;
                    
                }
                if (round > b) {
                    System.out.print(jumps.getLength() + " m, ");
                    b++;
                } else {
                    System.out.print(jumps.getLength() + " m");
                }
            }
            System.out.println("");
            index++;
        }
    }
    
    public int totalScore(String name) {
        int i = 0;
        for (Jump juuump : this.statistics.get(name)) {
            i = i + juuump.countScores();
        }
        return i;
    }
    
    public List totalScoreReversed() {
        List<TotalScore> winnerRank = new ArrayList<>();
        for (String names : this.statistics.keySet()) {
            winnerRank.add(new TotalScore(names, totalScore(names)));
        }
        Collections.sort(winnerRank);
        Collections.reverse(winnerRank);
       
        return winnerRank;
    }
}
