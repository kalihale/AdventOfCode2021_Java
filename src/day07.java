import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * ／(•ㅅ•)＼
 *
 * @author Kali Hale
 * @since 2021-12-16
 */
public class day07
{
    public static int leastFuel1(ArrayList<Integer> positions)
    {
        HashMap<Integer, Integer> fuel = new HashMap<>();
        for(int target = positions.get(0); target < positions.get(positions.size() - 1); target++)
        {
            for(int pos : positions)
            {
                if(!fuel.containsKey(target))
                {
                    // ／(•ㅅ•)＼ pos - target = number of spaces to move
                    fuel.put(target, Math.abs(pos - target));
                }
                else
                {
                    fuel.put(target, fuel.get(target) + Math.abs(pos - target));
                }
            }
        }
        Integer smallest = findSmallest(fuel);
        System.out.println("Target position: " + smallest);
        return fuel.get(smallest);
    }
    public static int leastFuel2(ArrayList<Integer> positions)
    {
        HashMap<Integer, Integer> fuel = new HashMap<>();
        for(int target = positions.get(0); target < positions.get(positions.size() - 1); target++)
        {
            for(int pos : positions)
            {
                // ／(•ㅅ•)＼ sum of numbers 1 to n inclusive
                int n = Math.abs(pos - target);
                int sum = n * (n + 1) / 2;
                if(!fuel.containsKey(target))
                {
                    fuel.put(target, sum);
                }
                else
                {
                    fuel.put(target, fuel.get(target) + sum);
                }
            }
        }
        Integer smallest = findSmallest(fuel);
        System.out.println("Target position: " + smallest);
        return fuel.get(smallest);
    }
    private static Integer findSmallest(HashMap<Integer, Integer> fuel)
    {
        Integer smallest = null;
        for(int pos : fuel.keySet())
        {
            if(smallest == null)
            {
                smallest = pos;
            }
            else
            {
                if(fuel.get(pos) < fuel.get(smallest))
                {
                    smallest = pos;
                }
            }
        }
        return smallest;
    }
    public static void main(String[] args)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        try
        {
            File file = new File("/home/kali/IdeaProjects/AdventOfCode2021/src/day07.txt");
            Scanner sc = new Scanner(new FileReader(file));
            sc.useDelimiter(",");
            while (sc.hasNext())
            {
                positions.add(Integer.parseInt(sc.next()));
            }
            sc.close();
        }catch(Exception e)
        {
            System.out.println("File handling error");
        }
        Collections.sort(positions);
        System.out.println(leastFuel1(positions));
        System.out.println(leastFuel2(positions));
    }
}
