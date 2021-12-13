import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * ／(•ㅅ•)＼
 *
 * @author Kali Hale
 * @since 2021-12-11
 *
 * Very much brute forced, but without sorting the boards somehow or using a hashmap, I don't think there's
 * a better way to do it.
 */
public class day4
{
    static int winningNumber;

    public static Bingo[][] winningBingo(ArrayList<Bingo[][]> boards, ArrayList<Integer> nums)
    {
        for(Integer num : nums)
        {
            for(Bingo[][] board : boards)
            {
                for(Bingo[] row : board)
                {
                    for(Bingo number : row)
                    {
                        if(number.getKey() == num)
                        {
                            number.setValue(true);
                        }
                    }
                }
                if(boardWins(board))
                {
                    winningNumber = num;
                    return board;
                }
            }
        }
        return new Bingo[1][1];
    }

    public static boolean boardWins(Bingo[][] board)
    {
        int c1, c2, c3, c4, c5;
        c1 = c2 = c3 = c4 = c5 = 0;
        for(Bingo[] row : board)
        {
            int rows = 0;
            for(Bingo number : row)
            {
                if(number.getValue())
                {
                    rows++;
                }
            }
            if(rows == 5)
            {
                return true;
            }
            if(row[0].getValue()) c1++;
            if(row[1].getValue()) c2++;
            if(row[2].getValue()) c3++;
            if(row[3].getValue()) c4++;
            if(row[4].getValue()) c5++;
        }
        return c1 == 5 || c2 == 5 || c3 == 5 || c4 == 5 || c5 == 5;
    }

    public static int unmarkedSum(Bingo[][] board)
    {
        int sum = 0;
        for(Bingo[] row : board)
        {
            for(Bingo number : row)
            {
                if(!number.getValue()) sum += number.getKey();
            }
        }
        return sum;
    }

    public static Bingo[][] lastToWin(ArrayList<Bingo[][]> boards, ArrayList<Integer> nums)
    {
        for(Integer num : nums)
        {
            for(int b = 0; b < boards.size(); b++)
            {
                for(Bingo[] row : boards.get(b))
                {
                    for(Bingo number : row)
                    {
                        if(number.getKey() == num)
                        {
                            number.setValue(true);
                        }
                    }
                }
                if(boardWins(boards.get(b)))
                {
                    if(boards.size() == 1)
                    {
                        winningNumber = num;
                        return boards.get(0);
                    }
                    boards.remove(boards.get(b));
                    b--;
                }
            }
        }
        return new Bingo[1][1];
    }

    public static void main(String[] args)
    {
        try
        {
            File file = new File("/home/kali/IdeaProjects/AdventOfCode2021/src/day4.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<Integer> numbers = new ArrayList<>();
            String line = br.readLine();
            String[] split = line.split(",");
            for(String number : split)
            {
                numbers.add(Integer.parseInt(number));
            }
            System.out.println(numbers);
            ArrayList<Bingo[][]> bingos = new ArrayList<>();
            // ／(•ㅅ•)＼ Get boards
            int boardindex = 0;
            br.readLine();
            do
            {
                bingos.add(new Bingo[5][5]);
                // ／(•ㅅ•)＼ Cycle through rows
                for(int i = 0; i < 5; i++)
                {
                    line = br.readLine();
                    line = line.stripLeading();
                    split = line.split("\\s+");
                    // ／(•ㅅ•)＼ Add numbers to arrays
                    for(int j = 0; j < 5; j++)
                    {
                        bingos.get(boardindex)[i][j] = new Bingo(Integer.parseInt(split[j]));
                    }
                }
                boardindex++;
            }while((line = br.readLine()) != null);
            System.out.println(unmarkedSum(winningBingo(bingos, numbers)) * winningNumber);
            System.out.println(unmarkedSum(lastToWin(bingos, numbers)) * winningNumber);
        }catch(Exception e)
        {
            System.out.println("File handling error");
        }
    }
}
