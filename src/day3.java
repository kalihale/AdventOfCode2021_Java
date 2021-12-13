import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * ／(•ㅅ•)＼
 *
 * @author Kali Hale
 * @since 2021-12-11
 */
public class day3
{
    public static String gammaRate(ArrayList<String> al)
    {
        int i = 0;
        String result = "";
        int ones = 0;
        int zeroes = 0;
        while(i < al.get(0).length())
        {
            for(String str : al)
            {
                if(str.charAt(i) == '1')
                {
                    ones++;
                }
                else
                {
                    zeroes++;
                }
            }
            if(ones > zeroes)
            {
                result = result.concat("1");
            }
            else
            {
                result = result.concat("0");
            }
            ones = 0;
            zeroes = 0;
            i++;
        }
        return result;
    }

    public static String epsilonRate(String gamma)
    {
        String result = "";
        for(int i = 0; i < gamma.length(); i++)
        {
            if(gamma.charAt(i) == '1')
            {
                result = result.concat("0");
            }
            else
            {
                result = result.concat("1");
            }
        }
        return result;
    }

    public static int binaryToArabic(String binary)
    {
        int result = 0;
        for(int i = 0; i < binary.length(); i++)
        {
            if(binary.charAt(binary.length() - 1 - i) == '1')
            {
                result += Math.pow(2, i);
            }
        }
        return result;
    }

    public static int oxygenGeneratorRating(ArrayList<String> input)
    {
        ArrayList<String> al = new ArrayList<>(input);
        int i = 0;
        int ones = 0;
        int zeroes = 0;
        while(i < al.get(0).length() && al.size() > 1)
        {
            for(String str : al)
            {
                if(str.charAt(i) == '0')
                {
                    zeroes++;
                }
                else
                {
                    ones++;
                }
            }
            int finalI = i;
            if(zeroes > ones)
            {
                al.removeIf(str -> str.charAt(finalI) == '1');
            }
            else
            {
                al.removeIf(str -> str.charAt(finalI) == '0');
            }
            zeroes = 0;
            ones = 0;
            i++;
        }
        return binaryToArabic(al.get(0));
    }

    public static int co2ScrubberRating(ArrayList<String> input)
    {
        ArrayList<String> al = new ArrayList<>(input);
        int i = 0;
        int ones = 0;
        int zeroes = 0;
        while(i < al.get(0).length() && al.size() > 1)
        {
            for(String str : al)
            {
                if(str.charAt(i) == '0')
                {
                    zeroes++;
                }
                else
                {
                    ones++;
                }
            }
            int finalI = i;
            if(zeroes <= ones)
            {
                al.removeIf(str -> str.charAt(finalI) == '1');
            }
            else
            {
                al.removeIf(str -> str.charAt(finalI) == '0');
            }
            zeroes = 0;
            ones = 0;
            i++;
        }
        return binaryToArabic(al.get(0));
    }

    public static void main(String[] args)
    {
        try
        {
            File file = new File("/home/kali/IdeaProjects/AdventOfCode2021/src/day3.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> binary = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null)
            {
                binary.add(line);
            }
            String gamma = gammaRate(binary);
            int result = binaryToArabic(gamma) * binaryToArabic(epsilonRate(gamma));
            System.out.println(result);
            int ogr = oxygenGeneratorRating(binary);
            int csr = co2ScrubberRating(binary);
            System.out.println(ogr);
            System.out.println(csr);
            System.out.println(ogr * csr);
        }catch(Exception e)
        {
            System.out.println("File handling error");
        }
    }
}
