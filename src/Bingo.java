/**
 * ／(•ㅅ•)＼
 *
 * @author Kali Hale
 * @since 2021-12-11
 */
public class Bingo
{
    int key;
    boolean value;

    Bingo(int key)
    {
        this.key = key;
        this.value = false;
    }

    public int getKey()
    {
        return key;
    }

    public void setKey(int key)
    {
        this.key = key;
    }

    public void setValue(boolean value)
    {
        this.value = value;
    }

    public boolean getValue()
    {
        return value;
    }
}
