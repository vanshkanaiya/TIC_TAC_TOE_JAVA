import java.util.Scanner;
import java.util.Random;

abstract class TicTacToeGame {
    public char[] board = {'0','1','2','3','4','5','6','7','8','9'};
    public abstract void printBoard();
    public abstract int checkWin();
    public void displayMenu() {}
}

class MultiPlayer extends TicTacToeGame {
    private int player;
    private int input;
    private int status;

    MultiPlayer() {
        player = 1;
        status = -1;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int _input) {
        input = _input;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int _status) {
        status = _status;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int _player) {
        player = _player;
    }

    public void printBoard() {
        System.out.print("\n\n");
        System.out.println("==== TIC TAC TOE ====\n");
        System.out.println("    |"+"     |" +"     |   ");
        System.out.println(board[1] + "   | " + board[2] + "   |  "+ board[3] +"  |");
        System.out.println("____|"+"_____|" +"_____|"+"__");
        System.out.println(board[4] + "   | " + board[5] + "   |  "+ board[6] +"  |");
        System.out.println("____|"+"_____|" +"_____|"+"__");
        System.out.println(board[7] + "   | " + board[8] + "   |  "+ board[9] +"  |");
        System.out.println("    |"+"     |" +"     |"+"   ");
    }

    public void printMenu() {
        System.out.println("\n=== WELCOME TO TIC TAC TOE ===");
        System.out.println("1. Single Player (vs Computer)");
        System.out.println("2. Multi Player");
        System.out.println("3. Exit");
        System.out.println("Enter your choice (1-3): ");
    }
    

    public int checkWin() {
        if(board[1]==board[2] && board[2]==board[3]) return 1;
        if(board[1]==board[4] && board[4]==board[7]) return 1;
        if(board[7]==board[8] && board[8]==board[9]) return 1;
        if(board[3]==board[6] && board[6]==board[9]) return 1;
        if(board[1]==board[5] && board[5]==board[9]) return 1;
        if(board[3]==board[5] && board[5]==board[7]) return 1;
        if(board[2]==board[5] && board[5]==board[8]) return 1;
        if(board[4]==board[5] && board[5]==board[6]) return 1;

        int count=0;
        for (int i = 1; i <=9; i++) {
            if(board[i]=='X' || board[i]=='O') count++;
        }
        if(count==9) return 0;
        return -1;
    }
}

class SinglePlayer extends TicTacToeGame {
    private int player;
    private int input;
    private int status;
    private Random random;

    SinglePlayer() {
        player = 1;
        status = -1;
        random = new Random();
    }

    public int getInput() {
        return input;
    }

    public void setInput(int _input) {
        input = _input;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int _status) {
        status = _status;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int _player) {
        player = _player;
    }

    public int getComputerMove() {
        while (true) {
            int move = random.nextInt(9) + 1;
            if (board[move] != 'X' && board[move] != 'O') {
                return move;
            }
        }
    }

    public void printBoard() {
        System.out.print("\n\n");
        System.out.println("==== TIC TAC TOE ====\n");
        System.out.println("    |"+"     |" +"     |   ");
        System.out.println(board[1] + "   | " + board[2] + "   |  "+ board[3] +"  |");
        System.out.println("____|"+"_____|" +"_____|"+"__");
        System.out.println(board[4] + "   | " + board[5] + "   |  "+ board[6] +"  |");
        System.out.println("____|"+"_____|" +"_____|"+"__");
        System.out.println(board[7] + "   | " + board[8] + "   |  "+ board[9] +"  |");
        System.out.println("    |"+"     |" +"     |"+"   ");
    }

    public int checkWin() {
        if(board[1]==board[2] && board[2]==board[3]) return 1;
        if(board[1]==board[4] && board[4]==board[7]) return 1;
        if(board[7]==board[8] && board[8]==board[9]) return 1;
        if(board[3]==board[6] && board[6]==board[9]) return 1;
        if(board[1]==board[5] && board[5]==board[9]) return 1;
        if(board[3]==board[5] && board[5]==board[7]) return 1;
        if(board[2]==board[5] && board[5]==board[8]) return 1;
        if(board[4]==board[5] && board[5]==board[6]) return 1;

        int count=0;
        for (int i = 1; i <=9; i++) {
            if(board[i]=='X' || board[i]=='O') count++;
        }
        if(count==9) return 0;
        return -1;
    }
}

public class driver {
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            MultiPlayer s1 = new MultiPlayer();
            int choice = 0;
            while (true) {
                s1.printMenu();
                if (!scn.hasNextInt()) {
                    System.out.println("Please enter a number 1-3");
                    scn.next();
                    continue;
                }
                choice = scn.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice, choose 1-3");
                    continue;
                }
                break;
            }

            if (choice == 3) {
                s1.setStatus(0);
                return;
            }

            if (choice == 1) {
                SinglePlayer sp = new SinglePlayer();
                while (sp.getStatus() == -1) {
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (Exception e) {
                        // ignore clear-screen failures
                    }

                    sp.printBoard();
                    int ply = sp.getPlayer();
                    sp.setPlayer((ply % 2 == 0) ? 2 : 1);

                    if (sp.getPlayer() == 1) {
                        System.out.println("Player 1 (X) turn. Enter a number (1-9):");
                        if (!scn.hasNextInt()) {
                            System.out.println("Please enter a number 1-9");
                            scn.next();
                            continue;
                        }
                        int inp = scn.nextInt();
                        sp.setInput(inp);

                        if (sp.getInput() < 1 || sp.getInput() > 9) {
                            System.out.println("Invalid Move");
                            continue;
                        }
                        if (sp.board[sp.getInput()] == 'X' || sp.board[sp.getInput()] == 'O') {
                            System.out.println("Cell already taken");
                            continue;
                        }

                        sp.board[sp.getInput()] = 'X';
                        int result = sp.checkWin();
                        if (result == 1) {
                            sp.printBoard();
                            System.out.println("Player 1 Wins!!!!");
                            break;
                        } else if (result == 0) {
                            sp.printBoard();
                            System.out.println("Game Draws");
                            break;
                        }
                        sp.printBoard();
                        sp.setPlayer(ply + 1);
                    } else {
                        int move = sp.getComputerMove();
                        sp.board[move] = 'O';
                        System.out.println("Computer chose: " + move);
                        sp.printBoard();
                        int result = sp.checkWin();
                        if (result == 1) {
                            System.out.println("Computer Wins!!!!");
                            break;
                        } else if (result == 0) {
                            System.out.println("Game Draws");
                            break;
                        }
                        sp.setPlayer(sp.getPlayer() + 1);
                        try { Thread.sleep(700); } catch (InterruptedException e) { /* ignore */ }
                    }
                }
                s1.setStatus(1);
                return;
            }

            
            while (s1.getStatus() == -1) {
                try {
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //s1.printMenu();
                s1.printBoard();
                int ply = s1.getPlayer();
                s1.setPlayer(((ply%2==0) ? 2 : 1));
                char mark = (s1.getPlayer() == 1) ? 'X' : 'O';
                System.out.println("Player "+ s1.getPlayer()+" turns");

                if (!scn.hasNextInt()) {
                    System.out.println("Please enter a number 1-9");
                    scn.next(); // consume invalid token
                    continue;
                }

                int inp = scn.nextInt();
                s1.setInput(inp);

                if (s1.getInput() < 1 || s1.getInput() > 9) {
                    System.out.println("Invalid Move");
                    continue;
                }

                if (s1.board[s1.getInput()] == 'X' || s1.board[s1.getInput()] == 'O') {
                    System.out.println("Cell already taken");
                    continue;
                }

                s1.board[s1.getInput()] = mark;
                s1.printBoard();
                int result = s1.checkWin();

                if (result == 1) {
                    System.out.println("Player " + s1.getPlayer() + " Wins!!!!");
                    break;
                } else if (result == 0) {
                    System.out.println("Game Draws");
                    break;
                }

                ply++;
                s1.setPlayer(ply);
            }
        }
    }
}