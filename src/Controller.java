/**
 * Created by Subhodh Kotekal on 8/5/2016.
 */

import java.util.*;

public class Controller
{
    private static String[][] board;
    private static int N;
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);

        System.out.print("Input Board Size: ");
        N = cin.nextInt();
        board = new String[N][N];

        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                board[i][j] = "-";
            }
        }

        System.out.print("\nX or O? (X = first): ");
        String user = cin.next();
        String bot = "";
        if(user.equals("X"))
        {
            bot = "O";
        }
        else
        {
            bot = "X";
        }

        AI artoo = new AI(N);

        if(bot.equals("X"))
        {
            int[] bot_move = artoo.move();
            artoo.update(bot_move[0], bot_move[1], false);
            update(bot_move, "X");
        }

        System.out.println(print_board());

        while(!isDone())
        {
            System.out.println("Input move as row col with 0 0 being upper left: ");

            String r = cin.next();
            String c = cin.next();

            int[] user_move = new int[2];
            user_move[0] = Integer.parseInt(r);
            user_move[1] = Integer.parseInt(c);

            update(user_move, user);
            System.out.println(print_board());

            artoo.update(user_move[0], user_move[1], true);
            int[] bot_move = artoo.move();

            artoo.update(bot_move[0], bot_move[1], false);
            update(bot_move, bot);

            System.out.println("\n\nArtoo moves: ");
            System.out.println(print_board());
        }
    }

    public static boolean isDone()
    {
        // Check if totally empty
        boolean empty = true;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(!board[i][j].equals("-")) // Not empty
                {
                    empty = false;
                }
            }
        }
        if(empty)
        {
            return false;
        }

        // Row
        for(int i = 0; i < N; i++)
        {
            boolean done = true;
            String piece = board[i][0];
            for(int j = 1; j < board[i].length; j++)
            {
                if(!piece.equals(board[i][j]))
                {
                    done = false;
                    break;
                }
            }
            if(done && !piece.equals("-"))
                return true;
        }

        // Col
        for(int i = 0; i < N; i++)
        {
            boolean done = true;
            String piece = board[0][i];
            for(int j = 1; j < board[i].length; j++)
            {
                if(!piece.equals(board[j][i]))
                {
                    done = false;
                    break;
                }
            }
            if(done && !piece.equals("-"))
                return true;
        }

        // Major
        boolean done = true;
        String piece = board[0][0];
        for(int i = 1; i < N; i++)
        {
            if(!piece.equals(board[i][i]))
            {
                done = false;
                break;
            }
        }
        if(done && !piece.equals("-"))
            return true;

        // Minor
        done = true;
        piece = board[0][N-1];

        for(int i = 1; i < N; i++)
        {
            if(!piece.equals(board[i][N-i-1]))
            {
                done = false;
                break;
            }
        }
        if(done && !piece.equals("-"))
            return true;

        // Check if board is filled
        done = true;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(board[i][j].equals("-")) {
                    done = false;
                    break;
                }
            }
        }
        if(done && !piece.equals("-"))
            return true;
        return false;
    }
    public static void update(int[] move, String piece)
    {
        board[move[0]][move[1]] = piece;
    }
    public static String print_board()
    {
        String to_print = "";
        for(int i = 0 ; i < N; i++)
        {
            to_print += "\n" + board[i][0];
            for(int j = 1; j < N; j++)
            {
                to_print += "\t" + board[i][j];
            }
        }

        return to_print;
    }
}
