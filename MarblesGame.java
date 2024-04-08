import java.util.Scanner;

public class MarblesGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String player1 = "";
        String player2 = "";
        boolean firstPlay = true;
        boolean exit = false;

        System.out.println("Welcome to Marbles!\n" +
                "In this game, there are n marbles in a bag.\n" +
                "On each turn, the current player may pick up 1 to k marbles.\n" +
                "The player who picks up the last marble loses the game.");

        while (!exit)
        {
            System.out.print("\nOptions: (P)LAY, (H)ELP, (E)XIT. Enter your choice: ");
            input = scanner.nextLine().toLowerCase();

            switch (input)
            {
                // PLAY
                case "p":
                case "play":

                    if (firstPlay){
                        // PLAYERS USERNAME
                        System.out.println("\n###### PLAYERS ######");
                        player1 = getPlayerName(scanner, 1);
                        player2 = getPlayerName(scanner, 2);
                        System.out.println("#####################\n");
                        firstPlay = false;
                    }

                    playGame(scanner, player1, player2);
                    break;

                // HELP
                case "h":
                case "help":
                    System.out.println("\tIn this game, there are n marbles in a bag.\n" +
                            "\tOn each turn, the current player may pick up 1 to k marbles.\n" +
                            "\tThe player who picks up the last marble loses the game.");
                    break;

                // EXIT
                case "e":
                case "exit":
                    System.out.println("\n###############################");
                    System.out.println("# Thanks for playing Marbles! #");
                    System.out.println("###############################");
                    exit = true;
                    break;

                // INVALID OPTION
                default:
                    System.out.println("Option not recognized.");
            }
        }
    }

    private static String getPlayerName(Scanner scanner, int playerNumber) {

        System.out.print("Enter player " + playerNumber + "'s name: ");
        String playerName = "";

        // VALIDATE PLAYER'S NAME INPUT
        do {
            playerName = scanner.nextLine().trim();
            if (playerName.isEmpty()) {
                System.out.println("Player 1's name cannot be empty. Please enter a valid name.");
            }
        } while (playerName.isEmpty());

        return playerName;
    }

    private static void playGame(Scanner scanner, String player1, String player2) {

        // MARBLES IN BAG
        int marbles = 2;
        boolean marblesValid = false;
        System.out.println("\n###### GAME SETTINGS ######");

        // VALIDATE MARBLES INPUT
        while (!marblesValid){

            System.out.print("Enter the total number of marbles in the bag: ");
            String marblesInput = scanner.nextLine();

            try {
                marbles = Integer.parseInt(marblesInput);

                if (marbles < 1) {
                    System.out.println("Invalid input. The total number of marbles in the bag must be greater than 0.");
                } else {
                    marblesValid = true;
                }

            } catch (NumberFormatException e) {
                System.out.println(marblesInput + " is not a valid number of marbles. Try again.");
            }
        }

        // MAX PICK
        boolean maxPickValid = false;
        int maxPick = 1;

        // VALIDATE MAXPICK INPUT
        while (!maxPickValid){

            System.out.print("Enter the max number of marbles a player can pick, each turn: ");
            String maxPickInput = scanner.nextLine();

            try {
                maxPick = Integer.parseInt(maxPickInput);

                if (maxPick > marbles) {
                    System.out.println("Invalid input. The max number of marbles a player can pick, each turn, must be less than or equal to the total number of marbles in the bag.");
                } else if (maxPick < 1) {
                    System.out.println("Invalid input. The max number of marbles a player can pick, each turn, must be greater than 0.");
                } else if (maxPick == marbles ){
                    System.out.println("Invalid input. The max number of marbles a player can pick, each turn, must be less than the total number of marbles in the bag.");
                } else {
                    maxPickValid = true;
                }

            } catch (NumberFormatException e) {
                System.out.println(maxPickInput + " is not a valid number of marbles. Try again.");
            }


        }
        System.out.println("###########################\n");
        System.out.print("There are " + marbles + " marble(s) in the bag.\n\n");

        // START GAME
        String currentPlayer = player1;

        while (marbles > 0)
        {
            int marblesPicked = 1;
            boolean marblesPickedValid = false;

            do {
                System.out.print(currentPlayer + ", how many marbles do you want to pick? ");
                String marblesPickedInput = scanner.nextLine();

                try {
                    marblesPicked = Integer.parseInt(marblesPickedInput);

                    if (marblesPicked < 1 || marblesPicked > maxPick) {
                        System.out.println("\t" + marblesPickedInput + " is not a valid number of marbles. Please pick a number between 1 and " + maxPick);
                        System.out.println("\tThere are " + marbles + " marble(s) left in the bag.");
                    } else if (marblesPicked > marbles) {
                        System.out.println("\t" + marblesPickedInput + " is not a valid number of marbles. There is only " + marbles + " marble(s) left in the bag.");
                        System.out.println("\tThere are " + marbles + " marble(s) left in the bag.");
                    } else {
                        marblesPickedValid = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("\t" + marblesPickedInput + " is not a valid number of marbles. Please pick a number between 1 and " + maxPick);
                    System.out.println("\tThere are " + marbles + " marble(s) left in the bag.");
                }

            } while (!marblesPickedValid);

            marbles -= marblesPicked;
            System.out.println("There are " + marbles + " marble(s) left in the bag.");

            if (marbles == 0)
            {
                System.out.println("\n###### GAME OVER ######");
                System.out.println("# " + currentPlayer + " picked the last marble and lost the game!");
                System.out.println("# Congratulations " + ((currentPlayer.equals(player1)) ? player2 : player1) + " wins!");
                System.out.println("#######################");
            }

            else
            {
                currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
                System.out.println("-----------------------");
            }
        }
    }
}

/*
PLAYER NAME SYSTEM V1

// VALIDATE PLAYER 1 NAME INPUT
System.out.print("Enter player 1's name: ");
do {
    player1 = scanner.nextLine().trim();

    if (player1.isEmpty()) {
        System.out.println("Player 1's name cannot be empty. Please enter a valid name.");
    }
} while (player1.isEmpty());

// VALIDATE PLAYER 2 NAME INPUT
System.out.print("Enter player 2's name: ");
do {
    player2 = scanner.nextLine().trim();
    if (player2.isEmpty()) {
        System.out.println("Player 2's name cannot be empty. Please enter a valid name.");
    }
} while (player2.isEmpty());
*/