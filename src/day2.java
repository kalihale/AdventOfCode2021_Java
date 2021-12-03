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
public class day2
{
    public static int position(ArrayList<String> commands) throws Exception
    {
        int horizontal = 0;
        int depth = 0;
        for (String command : commands)
        {
            String[] instruct = command.split(" ");

            switch (instruct[0])
            {
                case "forward":
                    horizontal += Integer.parseInt(instruct[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(instruct[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(instruct[1]);
                    break;
                default:
                    throw new Exception("Parse error");
            }
        }
        return horizontal * depth;
    }
    public static int part2(ArrayList<String> commands) throws Exception
    {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (String command : commands)
        {
            String[] instruct = command.split(" ");

            switch (instruct[0])
            {
                case "forward":
                    horizontal += Integer.parseInt(instruct[1]);
                    depth += aim * Integer.parseInt(instruct[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(instruct[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(instruct[1]);
                    break;
                default:
                    throw new Exception("Parse error");
            }
        }
        return horizontal * depth;
    }
    public static void main(String[] args)
    {
        try
        {
            File file = new File("/home/kali/IdeaProjects/AdventOfCode2021/src/day2.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> commands = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null)
            {
                commands.add(line);
            }
            System.out.println(position(commands));
            System.out.println(part2(commands));
        }catch(Exception e)
        {
            System.out.println("File handling error");
        }
    }
}
