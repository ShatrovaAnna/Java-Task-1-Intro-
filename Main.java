package com.company;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Wrong args!");
        }
        WordsAnalyzer analyzer = new WordsAnalyzer();
        analyzer.startWork(args[0], args[1]);
    }
}
