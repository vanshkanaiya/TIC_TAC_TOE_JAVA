import java.util.Scanner;

interface TicTacToeGame {
    public char[] board = {'0','1','2','3','4','5','6','7','8','9'};
    public void printBoard();
    public int checkWin();
}

class SinglePlayer implements TicTacToeGame {
    private int player;
    private int input;
    private int status;

    SinglePlayer() {
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

    public int checkWin() {
        if(board[1]==board[2] && board[2]==board[3]){
        return 1;
        }
        if(board[1]==board[4] && board[4]==board[7]){
            return 1;
        }
        if(board[7]==board[8] && board[8]==board[9]){
            return 1;
        }
        if(board[3]==board[6] && board[6]==board[9]){
            return 1;
        }
        if(board[1]==board[5] && board[5]==board[9]){
            return 1;
        }
        if(board[3]==board[5] && board[5]==board[7]){
            return 1;
        }
        if(board[2]==board[5] && board[5]==board[8]){
            return 1;
        }
        if(board[4]==board[5] && board[5]==board[6]){
            return 1;
        }

        int count=0;
        for (int i = 1; i <=9; i++)
        {
            if(board[i]=='X' || board[i]=='O'){
                count++;
            }
        }
        
        if(count==9){
            return 0;
        }
        return -1;
    }
};

public class driver {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        SinglePlayer s1 = new SinglePlayer();
        while (s1.getStatus() == -1) {
            try {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            s1.printBoard();
            int ply = s1.getPlayer();
            s1.setPlayer(((ply%2==0) ? 2 : 1));
            char mark = (s1.getPlayer() == 1) ? 'X' : 'O';
            System.out.println("Player "+ s1.getPlayer()+" turns");
            int inp = scn.nextInt();
            s1.setInput(inp);

            if (s1.getInput() < 1 || s1.getInput() > 9) {
                System.out.println("Invalid Move");
            }

            s1.board[s1.getInput()] = mark;
            s1.printBoard();
            int result = s1.checkWin();

            if (result == 1) {
                System.out.println("Player " + s1.getPlayer() + " Wins!!!!");
                break;
            }
            else if (result == 0) {
                System.out.println("Game Draws");
                break;
            }

            ply++;
            s1.setPlayer(ply);
        }

    }
}