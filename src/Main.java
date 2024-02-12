import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<ShortLister> words = new ArrayList<>();
        ArrayList<BigRectLister> rectangles = new ArrayList<>();
        int counter;

        // Word Data
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> test = new ArrayList<>();

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                while(reader.ready())
                {
                    rec = reader.readLine();
                    test.add(rec);
                }
                reader.close();

                String[] totalWords;
                for(String l : test)
                {
                    totalWords = l.split(" ");
                    for (String cut : totalWords)
                    {
                        words.add(new ShortLister(cut));
                    }
                }
            }
            else
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("\n  Total Words  \n===============");
        counter = 0;
        for (ShortLister word : words)
        {
            counter++;
            System.out.println("Word " + counter + ": " + word.word + " (" + word.length + ")");
        }

        System.out.println("\n  Short Words  \n===============");
        counter = 0;
        for (ShortLister word : words)
        {
            counter++;
            if (word.accept(word))
            {
                System.out.println("Word " + counter + ": " + word.word + " (" + word.length + ")");
            }
        }

        // Rectangle Data
        for (int i = 0; i < 10; i++)
        {
            rectangles.add(new BigRectLister((int)((Math.random() * 4) + 1), (int)((Math.random() * 5) + 1)));
        }

        System.out.println("\n  Total Rectangles  \n====================");
        counter = 0;
        for (BigRectLister rect : rectangles)
        {
            counter++;
            System.out.println("Rectangle " + counter + ": {" + rect.width + ", " + rect.length + "} " + rect.perimeter);
        }

        System.out.println("\n  Big Rectangles  \n==================");
        counter = 0;
        for (BigRectLister rect : rectangles)
        {
            counter++;
            if (rect.accept(rect))
            {
                System.out.println("Rectangle " + counter + ": {" + rect.width + ", " + rect.length + "} " + rect.perimeter);
            }
        }
        System.out.println("\nData complete!");
    }
}