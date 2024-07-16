package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 128;
    public static final int HEIGHT = 72;
    public static final Random rand = new Random();

    public static int pathDensity = 100;
    public static int pathIdx = 0;
    public static int roomDensity = 95;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        ter.initialize(WIDTH, HEIGHT);
        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 80));
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 2 / 3, "CS61B: THE GAME");

        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 30));
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 7 / 15, "New Game (N)");
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 6 / 15, "Load Game (L)");
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 5 / 15, "Quit (Q)");

        StdDraw.enableDoubleBuffering();
        StdDraw.show();

        StdDraw.setFont();
        StdDraw.setPenColor();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (c == 'Q' || c == 'q') {
                    System.exit(0);
                } else if (c == 'N' || c == 'n') {
                    displaySeed("");
                    long seed;
                    while (true) {
                        try {
                            seed = inputSeed();
                            break;
                        } catch (NumberFormatException e) {
                            displaySeedError();
                        }
                    }
                    rand.setSeed(seed);
                    TETile[][] map = generateWorld();
                    ter.initialize(WIDTH, HEIGHT);
                    ter.renderFrame(map);
                    return;
                } else if (c == 'L' || c == 'l') {
                }
            }
            StdDraw.pause(100);
        }
    }

    private TETile[][] generateWorld() {
        // Create a map and paint it black
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                finalWorldFrame[i][j] = Tileset.NOTHING;
            }
        }

        // Make some hallways & rooms
        makeHallway(finalWorldFrame, WIDTH / 2, HEIGHT / 2, null);

        // Clear up walls
        clearUpWalls(finalWorldFrame);

        return finalWorldFrame;
    }

    private long inputSeed() {
        char[] inputChars = new char[19];
        int count = -1;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                count++;
                char key = StdDraw.nextKeyTyped();
                if (key >= '0' && key <= '9' && count != 19) {
                    inputChars[count] = key;
                    displaySeed(new String(inputChars));
                } else if (key == 'S') {
                    break;
                } else {
                    count--;
                    continue;
                }
            }
            StdDraw.pause(50);
        }
        System.out.println("Seed: " + new String(inputChars).substring(0, count));
        return Long.parseLong(new String(inputChars).substring(0, count));
    }

    private void displaySeed(String inputString) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 40));
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 8 / 15, "Enter a seed for the game:");
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 7 / 15, inputString);
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 6 / 15, "(Press 'S' to start)");
        StdDraw.show();
    }

    private void displaySeedError() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 40));
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 8 / 15, "Enter a seed for the game:");
        StdDraw.setPenColor(Color.RED);
        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 6 / 15, "Invalid seed.");
        StdDraw.show();
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww"). The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        if (input.charAt(0) == 'n' || input.charAt(0) == 'N') {
            int firstNonNum = input.length();
            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                    firstNonNum = i;
                    break;
                }
            }
            rand.setSeed(Long.parseLong(input.substring(1, firstNonNum)));
        }
        return generateWorld();
    }

    private TETile getRandomWall() {
        return switch (rand.nextInt(3)) {
            case 0 -> Tileset.WALL_1;
            case 1 -> Tileset.WALL_2;
            case 2 -> Tileset.WALL_3;
            default -> throw new IllegalStateException("Unexpected value: " + rand.nextInt(3));
        };
    }

    public void makeRooms(TETile[][] map, int x, int y) {
        if (map[x][y] == Tileset.FLOOR) {
            int height = rand.nextInt(3, 5);
            int width = rand.nextInt(3, 5);
            for (int m = x - width; m <= x + width; m++) {
                for (int n = y - height; n <= y + height; n++) {
                    if (m == x - width || m == x + width || n == y - height || n == y + height) {
                        map[m][n] = getRandomWall();
                    } else {
                        map[m][n] = Tileset.FLOOR;
                    }
                }
            }
        }
    }

    public boolean isEnclosed(TETile[][] map, int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (map[i][j].equals(Tileset.NOTHING)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void clearUpWalls(TETile[][] map) {
        for (int i = 1; i <= map.length - 2; i++) {
            for (int j = 1; j <= map[0].length - 2; j++) {
                if (map[i][j] == Tileset.WALL_1 || map[i][j] == Tileset.WALL_2 || map[i][j] == Tileset.WALL_3) {
                    if (isEnclosed(map, i, j)) {
                        map[i][j] = Tileset.FLOOR;
                    }
                }
            }
        }
    }

    public void makeHallway(TETile[][] map, int init_x, int init_y, Direction priorDirection) {
        // Build walls around itself
        for (int i = init_x - 1; i <= init_x + 1; i++) {
            for (int j = init_y - 1; j <= init_y + 1; j++) {
                if (!map[i][j].equals(Tileset.FLOOR)) {
                    map[i][j] = getRandomWall();
                }
            }
        }

        // Get a distance
        int distance = rand.nextInt(7, 26);

        // Update pathIdx
        if (pathIdx == pathDensity) {
            map[init_x][init_y] = Tileset.SAND;
            pathIdx = 0;
            return;
        }
        pathIdx++;

        // Get a direction
        Direction direction = Direction.values()[rand.nextInt(0, 4)];
        if (priorDirection != null) {
            int judge = priorDirection.ordinal() + direction.ordinal();
            if (judge == 1 || judge == 5 || priorDirection.equals(direction)) {
                direction = Direction.values()[(direction.ordinal() + 2) % 4];
            }
        }

        // Build hallways and walls
        int i;
        switch (direction) {
            case UP:
                for (i = 0; i < distance; i++) {
                    int y = init_y + i;
                    if (y == map[0].length - 4) {
                        break;
                    }
                    if (rand.nextInt(101 - roomDensity) == 0) {
                        makeRooms(map, init_x, y);
                    }
                    moveVertical(map, init_x, y);
                }
                makeHallway(map, init_x, init_y + i - 1, direction);
                break;
            case DOWN:
                for (i = 0; i < distance; i++) {
                    int y = init_y - i;
                    if (y == 3) {
                        break;
                    }
                    if (rand.nextInt(101 - roomDensity) == 0) {
                        makeRooms(map, init_x, y);
                    }
                    moveVertical(map, init_x, y);
                }
                makeHallway(map, init_x, init_y - i + 1, direction);
                break;
            case LEFT:
                for (i = 0; i < distance; i++) {
                    int x = init_x - i;
                    if (x == 3) {
                        break;
                    }
                    if (rand.nextInt(101 - roomDensity) == 0) {
                        makeRooms(map, x, init_y);
                    }
                    moveHorizontal(map, x, init_y);
                }
                makeHallway(map, init_x - i + 1, init_y, direction);
                break;
            case RIGHT:
                for (i = 0; i < distance; i++) {
                    int x = init_x + i;
                    if (x == map.length - 4) {
                        break;
                    }
                    if (rand.nextInt(101 - roomDensity) == 0) {
                        makeRooms(map, x, init_y);
                    }
                    moveHorizontal(map, x, init_y);
                }
                makeHallway(map, init_x + i - 1, init_y, direction);
                break;
        }
    }

    private void moveHorizontal(TETile[][] map, int x, int y) {
        map[x][y] = Tileset.FLOOR;
        int up_y = y + 1;
        if (!map[x][up_y].equals(Tileset.FLOOR)) {
            map[x][up_y] = getRandomWall();
        }
        int down_y = y - 1;
        if (!map[x][down_y].equals(Tileset.FLOOR)) {
            map[x][down_y] = getRandomWall();
        }
    }

    private void moveVertical(TETile[][] map, int x, int y) {
        map[x][y] = Tileset.FLOOR;
        int right_x = x + 1;
        if (!map[right_x][y].equals(Tileset.FLOOR)) {
            map[right_x][y] = getRandomWall();
        }
        int left_x = x - 1;
        if (!map[left_x][y].equals(Tileset.FLOOR)) {
            map[left_x][y] = getRandomWall();
        }
    }
}
