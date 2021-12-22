import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * ／(•ㅅ•)＼
 *
 * @author Kali Hale
 * @since 2021-12-16
 */
public class day08
{
    public static int oneFourSevenEight(ArrayList<String[]> outputs)
    {
        int num = 0;
        for(String[] i : outputs)
        {
            for(String str : i)
            {
                if(str.length() == 2 || str.length() == 3 || str.length() == 4 || str.length() == 7)
                {
                    num++;
                }
            }
        }
        return num;
    }
    public static int sumVals(int[] values)
    {
        int result = 0;
        for(int value : values)
        {
            result += value;
        }
        return result;
    }
    public static int[] decode(ArrayList<String[]> inputs, ArrayList<String[]> outputs)
    {
        HashMap<Integer, String> numbers = new HashMap<>();
        HashMap<Character, Character> segments = new HashMap<>();
        int[] outputValues = new int[outputs.size()];
        // ／(•ㅅ•)＼ Do for each line
        for(int i = 0; i < inputs.size(); i++)
        {
            // ／(•ㅅ•)＼ For each number string in the line
            for(String current : inputs.get(i))
            {
                if(current.length() == 2)
                {
                    numbers.put(1, sortString(current));
                }
                else if(current.length() == 7)
                {
                    numbers.put(8, sortString(current));
                }
                else if(current.length() == 4)
                {
                    numbers.put(4, sortString(current));
                }
                else if(current.length() == 3)
                {
                    numbers.put(7, sortString(current));
                }
            }
            for(String current : inputs.get(i))
            {
                if(current.length() == 5)
                {
                    if(charsInCommon(numbers.get(4), current) == 3)
                    {
                        if(charsInCommon(numbers.get(1), current) == 2)
                        {
                            numbers.put(3, sortString(current));
                        }
                        else
                        {
                            numbers.put(5, sortString(current));
                        }
                    }
                    else
                    {
                        numbers.put(2, sortString(current));
                    }
                }
                else if(current.length() == 6)
                {
                    if(charsInCommon(numbers.get(1), current) == 2)
                    {
                        if(charsInCommon(numbers.get(4), current) == 4)
                        {
                            numbers.put(9, sortString(current));
                        }
                        else
                        {
                            numbers.put(0, sortString(current));
                        }
                    }
                    else
                    {
                        numbers.put(6, sortString(current));
                    }
                }
            }
            int outputnum = 0;
            for(String digit : outputs.get(i))
            {
                outputnum *= 10;
                for(int j = 0; j < 10; j++)
                {
                    if(sortString(digit).equals(numbers.get(j)))
                    {
                        outputnum += j;
                        break;
                    }
                }
            }
            outputValues[i] = outputnum;
        }
        return outputValues;
    }
    public static int charsInCommon(String one, String two)
    {
        if(one.length() < two.length())
        {
            String temp = one;
            one = two;
            two = temp;
        }
        int total = 0;
        for(int i = 0; i < one.length(); i++)
        {
            if(two.contains(one.charAt(i) + ""))
            {
                total++;
            }
        }
        return total;
    }
    public static String sortString(String str)
    {
        char[] characters = str.toCharArray();
        quicksortString(characters, 0, characters.length - 1);
        StringBuilder newStr = new StringBuilder();
        for(int i = 0; i < characters.length; i++)
        {
            newStr.append(characters[i]);
        }
        return newStr.toString();
    }
    public static void quicksortString(char[] chars, int first, int last)
    {
        if(first < last)
        {
            int pivot = pivot(first, chars, first, last);
            quicksortString(chars, first, pivot - 1);
            quicksortString(chars, pivot + 1, last);
        }
    }
    public static int pivot(int pivot, char[] arr, int left, int right)
    {
        char temp = arr[right];
        arr[right] = arr[pivot];
        arr[pivot] = temp;
        int i = left;
        for(int j = i; j < right; j++)
        {
            if(arr[j] < arr[right])
            {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;
        return i;
    }
    public static void main(String[] args)
    {
        ArrayList<String[]> inputs = new ArrayList<>();
        ArrayList<String[]> outputs = new ArrayList<>();
        try
        {
            File file = new File("/home/kali/IdeaProjects/AdventOfCode2021/src/day08.txt");
            Scanner sc = new Scanner(new FileReader(file));
            sc.useDelimiter("\\s+");
            while (sc.hasNext())
            {
                String[] inp = new String[10];
                String[] out = new String[4];
                for(int i = 0; i < 10; i++)
                {
                    inp[i] = sc.next();
                }
                sc.next();
                for(int i = 0; i < 4; i++)
                {
                    out[i] = sc.next();
                }
                inputs.add(inp);
                outputs.add(out);
            }
            sc.close();
        }catch(Exception e)
        {
            System.out.println("File handling error");
        }
        int[] outs = decode(inputs, outputs);
        System.out.println(Arrays.toString(outs));
        System.out.println(sumVals(outs));
    }
}
