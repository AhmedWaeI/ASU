import java.io.File;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)  {



        try {
            int L = args.length;
            error0(L);
            String Filename = args[0];
            File input = new File(args [0]);
            error2(Filename);
            error(input);
            Scanner source = new Scanner(input);
            ArrayList<String> words = new ArrayList<String>();
            while (source.hasNextLine()) {
                words.add(source.nextLine());
            }
            source.close();
            String outputname= Filename.replaceAll("\\.","_mod.");

            File src = new File(outputname);
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
                            int start1=index.get(i);
                            int end1=index.get(i);
                            int start2=index.get(j);
                            int end2=index.get(j);
                            while(!words.get(start1).contains("CONTAINER"))
                            {
                                start1 --;
                                start2--;

                                Collections.swap(words, start1, start2);

                            }
                            while(!words.get(end1).contains("CONTAINER"))
                            {
                                end1 ++;
                                end2 ++;

                                Collections.swap(words, end1, end2);

                            }


                            break;
                        }
                        else {
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
        catch (FileNotFoundException ex)
        {
            System.out.println("There was no files passed");
        }
        catch (NotVaildAutosarFileException ex)
        {
            System.out.println("The passed file has wrong extension it should be ARXML");
        }
        catch (EmptyAutosarFileException ex) {
            System.out.println("The passed file is empty");
        }


    }

    public static void  error0(int x) throws FileNotFoundException
    {
        if(x==0)
            throw new FileNotFoundException();
    }
    public static void error(File x ) throws EmptyAutosarFileException {

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
class EmptyAutosarFileException extends RuntimeException
{
    EmptyAutosarFileException(){
        super("The file is empty");


    }
}
class NotVaildAutosarFileException extends IOException {
    NotVaildAutosarFileException()
    {
        super("invalid extension");
    }
}

