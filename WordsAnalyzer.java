package com.company;
import java.io.*;
import java.util.*;

public class WordsAnalyzer {
    public void startWork(String in, String out){
        if(readFile(in) == 1)
            writeFile(out);
    }

    private List<Map.Entry<String, Integer>> listOfWord = new LinkedList<>();
    private int count = 0;

    private int readFile(String infile){
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(infile));
            StringBuilder stringBuilder = new StringBuilder();
            HashMap<String, Integer> wordAndCount = new HashMap<>();
            int symbol = reader.read();
            while(symbol != -1){
                if (Character.isLetterOrDigit(symbol)) {
                    stringBuilder.append((char) symbol);
                }
                else if (stringBuilder.length() != 0){
                    String string = stringBuilder.toString();
                    if (wordAndCount.containsKey(string)){
                        wordAndCount.put(string, wordAndCount.get(string) + 1);
                    }
                    else {
                        wordAndCount.put(string, 1);
                    }
                    count++;
                    stringBuilder.setLength(0);
                }
                symbol = reader.read();
            }
            listOfWord.addAll(wordAndCount.entrySet());
            Collections.sort(listOfWord, (a, b) -> b.getValue().compareTo(a.getValue()));
        }
        catch (IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
        return 1;
    }

    private void writeFile(String outfile){
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outfile));
            for (HashMap.Entry<String, Integer> i : listOfWord) {
                writer.write(i.getKey() + "; " + i.getValue() + "; " + (double) (10000 * i.getValue() / count) / 100 + "%\n");
            }
        }
        catch (IOException e)
        {
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (null != writer)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
