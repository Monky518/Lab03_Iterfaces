public class BigRectLister implements Filter
{
    int width;
    int length;
    int perimeter;

    public BigRectLister(int width, int length)
    {
        this.width = width;
        this.length = length;
        perimeter = (width + length) * 2;
    }

    @Override
    public boolean accept(Object x)
    {
        if (perimeter > 10)
            return true;
        else
            return false;
    }
}