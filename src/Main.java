import java.io.File;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String Filename = "New Text Document.ARXML";
        File input = new File("D:\\New Text Document.ARXML");

        try {
            error(input);
            error2(Filename);
        } catch (EmptyAutosarFileException | NotVaildAutosarFileException ex) {
            System.out.println("invalid");
        }

        Scanner source = new Scanner(input);
        ArrayList<String> words = new ArrayList<String>();
        while (source.hasNextLine()) {
            words.add(source.nextLine());
        }
        source.close();

        File src = new File("D:\\output.ARXML");
        PrintWriter output = new PrintWriter(src);

        ArrayList<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < words.size(); i++) {
            String x = words.get(i);
            if (x.trim().startsWith("<SHORT-NAME>")) {
                index.add(i);
            }
        }

        for (int i = 0; i < index.size(); i++) {
            for (int j = i + 1; j < index.size(); j++) {
                String s1 = words.get(index.get(i));
                String s2 = words.get(index.get(j));
                int minLength = Math.min(s1.length(), s2.length());

                for (int k = 0; k < minLength; k++) {
                    char c1 = s1.charAt(k);
                    char c2 = s2.charAt(k);

                    if (c1 == c2) {
                        continue;
                    } else if (c1 > c2) {
                        Collections.swap(words, index.get(i), index.get(j));
                        break;
                    } else {
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < words.size(); i++) {
            output.println(words.get(i));
        }

        output.close();
    }

    public static void error(File x) throws EmptyAutosarFileException, NotVaildAutosarFileException, FileNotFoundException {

        if (x.length() == 0) {
            throw new EmptyAutosarFileException();
        }
    }

    public static void error2(String x) throws NotVaildAutosarFileException {
        if (!x.endsWith("ARXML")) {
            throw new NotVaildAutosarFileException();
        }
    }
}
