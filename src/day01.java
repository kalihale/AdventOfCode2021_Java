import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * ／(•ㅅ•)＼
 *
 * @author Kali Hale
 * @since 2021-12-01
 */
public class day01
{
    public static int numberBiggerThan(ArrayList<Integer> measurements)
    {
        int bigger = 0;
        for(int i = 1; i < measurements.size(); i++)
        {
            if(measurements.get(i - 1) < measurements.get(i))
            {
                bigger++;
            }
        }
        return bigger;
    }

    public static int numberBiggerThanSummation(ArrayList<Integer> measurements)
    {
        int bigger = 0;
        if(measurements.size() == 3)
        {
            return measurements.get(0) + measurements.get(1) + measurements.get(2);
        }

        int prevsum = measurements.get(0) + measurements.get(1) + measurements.get(2);
        int thissum;

        for(int i = 2; i < measurements.size() - 1; i++)
        {
            thissum = measurements.get(i - 1) + measurements.get(i) + measurements.get(i + 1);
            if(prevsum < thissum)
            {
                bigger++;
            }
            prevsum = thissum;
        }
        return bigger;
    }

    public static void main(String[] args)
    {
        try
        {
            File file = new File("/home/kali/IdeaProjects/AdventOfCode2021/src/day01.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<Integer> measurements = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null)
            {
                measurements.add(Integer.parseInt(line));
            }
            System.out.println(numberBiggerThan(measurements));
            System.out.println(numberBiggerThanSummation(measurements));
        }catch(Exception e)
        {
            System.out.println("File handling error");
        }
    }
}
