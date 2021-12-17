import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ／(•ㅅ•)＼
 *
 * @author Kali Hale
 * @since 2021-12-16
 * Used https://github.com/xblak/adventOfCode2021/blob/main/day05.py for some help to get unstuck - thanks!
 */
public class day05
{
    public static HashMap<String, Integer> intersections1(ArrayList<String> lines)
    {
        HashMap<String, Integer> intersections = new HashMap<>();
        for(String line : lines)
        {
            String[] endpoints = line.split(" -> ");
            String[] x1y1 = endpoints[0].split(",");
            String[] x2y2 = endpoints[1].split(",");
            if(Integer.parseInt(x2y2[0]) < Integer.parseInt(x1y1[0]))
            {
                String[] xtemp = x1y1;
                x1y1 = x2y2;
                x2y2 = xtemp;
            }
            if(x1y1[0].equals(x2y2[0]))
            {
                for(int i = Integer.parseInt(x1y1[1]); i <= Integer.parseInt(x2y2[1]); i++)
                {
                    String key = x1y1[0] + "," + i;
                    if(!intersections.containsKey(key))
                    {
                        intersections.put(key, 1);
                    }
                    else
                    {
                        intersections.put(key, intersections.get(key) + 1);
                    }
                }
            }
            else if(x1y1[1].equals(x2y2[1]))
            {
                for(int i = Integer.parseInt(x1y1[0]); i <= Integer.parseInt(x2y2[0]); i++)
                {
                    String key = i + "," + x1y1[1];
                    if(!intersections.containsKey(key))
                    {
                        intersections.put(key, 1);
                    }
                    else
                    {
                        intersections.put(key, intersections.get(key) + 1);
                    }
                }
            }
        }
        return intersections;
    }
    public static HashMap<String, Integer> intersections2(ArrayList<String> lines)
    {
        HashMap<String, Integer> intersections = new HashMap<>();
        for(String line : lines)
        {
            String[] endpoints = line.split(" -> ");
            String[] x1y1 = endpoints[0].split(",");
            String[] x2y2 = endpoints[1].split(",");
            if(Integer.parseInt(x2y2[0]) < Integer.parseInt(x1y1[0]))
            {
                String[] xtemp = x1y1;
                x1y1 = x2y2;
                x2y2 = xtemp;
            }
            // ／(•ㅅ•)＼ If line is vertical
            if(x1y1[0].equals(x2y2[0]))
            {
                if(Integer.parseInt(x1y1[1]) < Integer.parseInt(x2y2[1]))
                {
                    for(int i = Integer.parseInt(x1y1[1]); i <= Integer.parseInt(x2y2[1]); i++)
                    {
                        String key = x1y1[0] + "," + i;
                        if(!intersections.containsKey(key))
                        {
                            intersections.put(key, 1);
                        }
                        else
                        {
                            intersections.put(key, intersections.get(key) + 1);
                        }
                    }
                }
                else if(Integer.parseInt(x1y1[1]) > Integer.parseInt(x2y2[1]))
                {
                    for(int i = Integer.parseInt(x1y1[1]); i >= Integer.parseInt(x2y2[1]); i--)
                    {
                        String key = x1y1[0] + "," + i;
                        if(!intersections.containsKey(key))
                        {
                            intersections.put(key, 1);
                        }
                        else
                        {
                            intersections.put(key, intersections.get(key) + 1);
                        }
                    }
                }
            }
            // ／(•ㅅ•)＼ If line is horizontal
            else if(x1y1[1].equals(x2y2[1]))
            {
                for(int i = Integer.parseInt(x1y1[0]); i <= Integer.parseInt(x2y2[0]); i++)
                {
                    String key = i + "," + x1y1[1];
                    if(!intersections.containsKey(key))
                    {
                        intersections.put(key, 1);
                    }
                    else
                    {
                        intersections.put(key, intersections.get(key) + 1);
                    }
                }
            }
            // ／(•ㅅ•)＼ If line is diagonal and goes from lower left to upper right
            else if(Integer.parseInt(x1y1[1]) < Integer.parseInt(x2y2[1]))
            {
                for(int i = 0; i <= Integer.parseInt(x2y2[0]) - Integer.parseInt(x1y1[0]); i++)
                {
                    int x = Integer.parseInt(x1y1[0]) + i;
                    int y = Integer.parseInt(x1y1[1]) + i;
                    String key = x + "," + y;
                    if(!intersections.containsKey(key))
                    {
                        intersections.put(key, 1);
                    }
                    else
                    {
                        intersections.put(key, intersections.get(key) + 1);
                    }
                }
            }
            // ／(•ㅅ•)＼ If line is diagonal and goes from upper left to lower right
            else if(Integer.parseInt(x1y1[1]) > Integer.parseInt(x2y2[1]))
            {
                for(int i = 0; i <= Integer.parseInt(x2y2[0]) - Integer.parseInt(x1y1[0]); i++)
                {
                    int x = Integer.parseInt(x1y1[0]) + i;
                    int y = Integer.parseInt(x1y1[1]) - i;
                    String key = x + "," + y;
                    if(!intersections.containsKey(key))
                    {
                        intersections.put(key, 1);
                    }
                    else
                    {
                        intersections.put(key, intersections.get(key) + 1);
                    }
                }
            }
        }
        return intersections;
    }
    public static int mostIntersect(HashMap<String, Integer> intersections)
    {
        int tally = 0;
        for(Integer num : intersections.values())
        {
            if(num >= 2)
            {
                tally++;
            }
        }
        return tally;
    }
    public static void main(String[] args)
    {
        try
        {
            File file = new File("/home/kali/IdeaProjects/AdventOfCode2021/src/day05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null)
            {
                lines.add(line);
            }
            System.out.println(mostIntersect(intersections1(lines)));
            System.out.println(mostIntersect(intersections2(lines)));
        }catch(Exception e)
        {
            System.out.println("File handling error");
        }
    }
}
