/**
 * Created by Subhodh Kotekal on 8/5/2016.
 */

public class AI
{
    private int N;

    private int[] row;
    private int[] col;
    private int major;
    private int minor;

    private int[][] board;

    public AI(int n)
    {
        this.N = n;
        this.row = new int[n];
        this.col = new int[n];
        this.major = 0;
        this.minor = 0;

        this.board = new int[n][n];
    }

    public void update(int r, int c, boolean user)
    {
        int parity = -1;
        if(!user)
            parity = 1;

        board[r][c] = parity;

        if(row[r] != Integer.MAX_VALUE && row[r]*parity >= 0) // Not blocking the row or col with opposing piece
            row[r] += parity;
        else
            row[r] = Integer.MAX_VALUE;

        if(col[c] != Integer.MAX_VALUE && col[c]*parity >= 0)
            col[c] += parity;
        else
            col[c] = Integer.MAX_VALUE;


        if(major != Integer.MAX_VALUE && r == c && major*parity >= 0) // Major diagonal
            major += parity;
        else if( r == c && major*parity < 0)
            major = Integer.MAX_VALUE;

        if(minor != Integer.MAX_VALUE && +c == N && minor*parity >= 0) //"Minor" diagonal
            minor += parity;
        else if(r+c == N-1 && minor*parity < 0)
            minor = Integer.MAX_VALUE;
    }

    public int[] move()
    {
        // Priority 1: WIN!!
        //("PRIORITY 1");
        for(int i = 0; i < N; i++)
        {
            if(row[i] == N-1) // Suck it loser
            {
                for(int j = 0; j < N; j++)
                {
                    if(board[i][j] == 0) // winner winner chicken dinner
                    {
                        return new int[] {i, j};
                    }
                }
            }
            if(col[i] == N-1)
            {
                for(int j = 0; j < N; j++)
                {
                    if(board[j][i] == 0)
                    {
                        return new int[] {j, i};
                    }
                }
            }
        }

        if(major == N-1)
        {
            for(int i = 0; i < N; i++)
            {
                if(board[i][i] == 0)
                {
                    return new int[] {i, i};
                }
            }
        }

        if(minor == N-1)
        {
            for(int i = 0; i < N; i++)
            {
                if(board[i][N-i-1] == 0)
                {
                    return new int[] {i, N-i-1};
                }
            }
        }

        // Priority 2: Block opponent win
        //("PRIOIRTY 2");
        for(int i = 0; i < N; i++)
        {
            if(row[i] == -(N-1))
            {
                for(int j = 0; j < N; j++)
                {
                    if(board[i][j] == 0)
                    {
                        return new int[] {i, j};
                    }
                }
            }
            if(col[i] == -(N-1))
            {
                for(int j = 0; j < N; j++)
                {
                    if(board[j][i] == 0)
                    {
                        return new int[] {j, i};
                    }
                }
            }
        }
        if(major == -(N-1))
        {
            for(int i = 0; i < N; i++)
            {
                if(board[i][i] == 0)
                {
                    return new int[] {i, i};
                }
            }
        }

        if(minor == -(N-1))
        {
            for(int i = 0; i < N; i++)
            {
                if(board[i][N-i-1] == 0)
                {
                    return new int[] {i, N-i-1};
                }
            }
        }

        //Priority 3: Setup force win
        //("PRIORITY 3");
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(row[i] == N-2 && col[j] == N-2 && board[i][j] == 0)
                {
                    return new int[] {i, j};
                }
            }
            if(major == N-2 && row[i] == N-2 && board[i][i] == 0)
            {
                return new int[]{i, i};
            }
            if(minor == N-2 && row[i] == N-2 && board[i][N-i-1] == 0)
            {
                return new int[] {i, N-i-1};
            }
        }

        //Priority 4: Block force win
        //("PRIORITY 4");
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(row[i] == -(N-2) && col[j] == -(N-2) && board[i][j] == 0)
                {
                    return new int[] {i, j};
                }
            }
            if(major == -(N-2) && row[i] == -(N-2) && board[i][i] == 0)
            {
                return new int[]{i, i};
            }
            if(minor == -(N-2) && row[i] == -(N-2) && board[i][N-i-1] == 0)
            {
                return new int[] {i, N-i-1};
            }
        }

        //Priority 5: Maximize total number of dimensions incremented
        //("PRIORITY 5");
        int max = 0;
        int[] ret = new int[2];
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                int num = 0;
                if(board[i][j] == 0)
                {
                    if(row[i] >= 0)
                        num++;
                    if(col[j] >= 0)
                        num++;
                    if(i == j && major >= 0)
                        num++;
                    if(i+j == N-1 && minor >= 0)
                        num++;
                }
                if(num > max)
                {
                    max = num;
                    ret[0] = i;
                    ret[1] = j;
                }
            }
        }

        return ret;
    }
}
