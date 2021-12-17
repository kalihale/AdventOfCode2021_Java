import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * ／(•ㅅ•)＼
 *
 * @author Kali Hale
 * @since 2021-12-16
 */
public class day06
{
    private static HashMap<Integer, Integer> fishies;

    // ／(•ㅅ•)＼ Get a stack overflow over 220 days... need a new solution
    public static long lanternfishPopulation(List<Integer> list, int days)
    {
        for(long i = 0; i < days; i++)
        {
            System.out.println("day" + i);
            int ls = list.size();
            for(int j = 0; j < ls; j++)
            {
                if(list.get(j) == 0)
                {
                    list.add(8);
                    list.set(j, 6);
                }
                else
                {
                    list.set(j, list.get(j) - 1);
                }
            }
        }
        return list.size();
    }
    // ／(•ㅅ•)＼ Hashmaps work. And Longs.
    public static long fishPop(List<Long> list, int days)
    {
        // ／(•ㅅ•)＼ Hashmap with the timer and the number of fish with that timer
        HashMap<Long, Long> fishies = new HashMap<>();
        // ／(•ㅅ•)＼ Initialize hashmap
        for(Long fishTimer : list)
        {
            if(!fishies.containsKey(fishTimer))
            {
                fishies.put(fishTimer, (long)1);
            }
            else
            {
                fishies.put(fishTimer, fishies.get(fishTimer) + 1);
            }
        }
        for(int i = 0; i < days; i++)
        {
            System.out.println("day " + i);
            HashMap<Long, Long> newFish = new HashMap<>();
            for(Long key : fishies.keySet())
            {
                if(key == 0)
                {
                    newFish.put((long)8, fishies.get(key));
                    if(fishies.containsKey((long)7))
                    {
                        newFish.put((long)6, fishies.get(key) + fishies.get((long)7));
                    }
                    else
                    {
                        newFish.put((long)6, fishies.get(key));
                    }
                }
                else if(key == (long)7)
                {
                    if(newFish.containsKey((long)6) && newFish.containsKey((long)8))
                    {
                        continue;
                    }
                    else if(!newFish.containsKey((long)6))
                    {
                        newFish.put((long)6, fishies.get((long)7));
                    }
                }
                else
                {
                    newFish.put((long)(key - 1), fishies.get(key));
                }
            }
            fishies = newFish;
        }
        long sum = 0;
        for(long vals : fishies.values())
        {
            sum += vals;
        }
        return sum;
    }
    public static void main(String[] args)
    {
        ArrayList<Long> lanternfish = new ArrayList<>();
        try
        {
            File file = new File("/home/kali/IdeaProjects/AdventOfCode2021/src/day06.txt");
            Scanner sc = new Scanner(new FileReader(file));
            sc.useDelimiter(",");
            while (sc.hasNext())
            {
                lanternfish.add(Long.parseLong(sc.next()));
            }
            sc.close();
        }catch(Exception e)
        {
            System.out.println("File handling error");
        }
        System.out.println(fishPop(lanternfish, 256));
    }
}
