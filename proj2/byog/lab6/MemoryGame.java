package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};
    public enum Status {
        FLASHING, WINNING, TYPING, LOSING
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        char[] randomChars = new char[n];
        for (int i = 0; i < n; i++) {
            randomChars[i] = CHARACTERS[rand.nextInt(25)];
        }
        return new String(randomChars);
    }

    public void drawFrame(String s, Status status) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear(Color.BLACK);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text((double) width / 2, (double) height / 2, s);

        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.setFont(new Font("Monaco", Font.PLAIN, 20));
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.line(0, height - 2, width, height - 2);
        StdDraw.textLeft(0, height - 1, "Round: " + round);
        switch (status) {
            case FLASHING:
                StdDraw.text((double) width / 2, height - 1, "Watch!");
                break;
            case WINNING:
                StdDraw.text((double) width / 2, height - 1, "Congratulations!");
                break;
            case TYPING:
                StdDraw.text((double) width / 2, height - 1, "Type the answer!");
                break;
            case LOSING:
                break;
        }

        StdDraw.textRight(width, height - 1, ENCOURAGEMENT[round % 7]);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(letters.substring(i, i + 1), Status.FLASHING);
            StdDraw.pause(1000);
            drawFrame("", Status.FLASHING);
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        char[] input = new char[n];
        for (int i = 0; i < input.length; i++) {
            while (!StdDraw.hasNextKeyTyped()) {
                StdDraw.pause(100);
            }
            input[i] = StdDraw.nextKeyTyped();
            drawFrame(new String(input), Status.TYPING);
        }
        return new String(input);
    }

    public void startGame() {
        round = 1;
        while (true) {
            drawFrame("Round: " + round, Status.FLASHING);
            String guess = generateRandomString(round);
            flashSequence(guess);
            String user = solicitNCharsInput(round);
            if (!guess.equals(user)) {
                drawFrame("Game Over!" + " You made it to round: " + round, Status.LOSING);
                break;
            }
            StdDraw.pause(1000);
            round++;
        }
    }

}
