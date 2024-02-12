public class ShortLister implements Filter
{
    String word;
    int length;

    public ShortLister(String word) {
        this.word = word;
        this.length = this.word.length();
    }

    @Override
    public boolean accept(Object x)
    {
        if (length < 5)
            return true;
        else
            return false;
    }
}
