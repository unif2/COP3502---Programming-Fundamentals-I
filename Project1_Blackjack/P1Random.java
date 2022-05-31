public class P1Random
{
    private long next;

    public P1Random()
    {
        next = 0;
    }

    private short nextShort()
    {
        return nextShort(Short.MAX_VALUE);
    }

    private short nextShort(short limit)
    {
        next = next * 1103515245 + 12345;
        return (short) Math.abs(((next / 65536) % limit));
    }

    private int nextInt()
    {
        return nextInt(Integer.MAX_VALUE);
    }

    public int nextInt(int limit)
    {
        return ((((int) nextShort()) << 16) | ((int) nextShort())) % limit;
    }

    private double nextDouble()
    {
        return (double) nextInt() / (double) Integer.MAX_VALUE;
    }
}
