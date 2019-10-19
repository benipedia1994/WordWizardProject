package edu.gatech.seclass.words6300.data;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import edu.gatech.seclass.words6300.GameStat;
import edu.gatech.seclass.words6300.Letter;
import edu.gatech.seclass.words6300.Game;
import edu.gatech.seclass.words6300.Word;
import edu.gatech.seclass.words6300.LetterStat;
import edu.gatech.seclass.words6300.WordStat;

public class Statistics {

    private final String LETTER_FILE = "letter_stats.txt";
    private final String WORD_FILE = "word_stats.txt";
    private final String GAME_FILE = "game_stats.txt";
    private Context app;
    private ArrayList<LetterStat> letterList = new ArrayList<>();
    private ArrayList<GameStat> gameList = new ArrayList<>();
    private ArrayList<WordStat> wordBank = new ArrayList<>();

    public Statistics(Context context) {
        app = context;
        try{
            loadLetterStats();
            loadWordBank();
            loadGameStats();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<WordStat> getWordBank() {
        return wordBank;
    }

    public ArrayList<LetterStat> getLetterList() {
        return letterList;
    }

    public ArrayList<GameStat> getGameList() {
        return gameList;
    }

    private void loadLetterStats() throws Exception{
        File file = new File(app.getFilesDir(), LETTER_FILE);
        if (!file.exists()){
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        final String DELIMITER = ",";
        String[] tokens;
        while(scanner.hasNext()){
            tokens = scanner.nextLine().split(DELIMITER);
            letterList.add(new LetterStat(tokens[0].charAt(0), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
        }
    }

    private void loadWordBank() throws Exception{
        File file = new File(app.getFilesDir(), WORD_FILE);
        if (!file.exists()){
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        final String DELIMITER = ",";
        String[] tokens;while(scanner.hasNext()){
            tokens = scanner.nextLine().split(DELIMITER);
            System.out.println(tokens.length);
            wordBank.add(new WordStat(tokens[0], Integer.parseInt(tokens[1])));
        }
    }

    private void loadGameStats() throws Exception{
        File file = new File(app.getFilesDir(), GAME_FILE);
        if (!file.exists()){
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        final String DELIMITER = ",";
        String[] tokens;while(scanner.hasNext()){
            tokens = scanner.nextLine().split(DELIMITER);
            letterList.add(new LetterStat(tokens[0].charAt(0), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
        }
    }

    private void saveLetterStats() throws Exception{
        FileOutputStream fos = this.app.openFileOutput(LETTER_FILE, Context.MODE_PRIVATE);
        OutputStreamWriter os = new OutputStreamWriter(fos);
        for (LetterStat ls : letterList){
            os.write(ls.toString());
        }
        os.close();
    }

    private void saveWordBank() throws Exception {
        FileOutputStream fos = this.app.openFileOutput(WORD_FILE, Context.MODE_PRIVATE);
        OutputStreamWriter os = new OutputStreamWriter(fos);
        for (WordStat ws : wordBank){
            os.write(ws.toString());
        }
        os.close();
    }

    public void collectGame(Game g){

    }

    public void playLetter(Letter l){
        boolean found = false;
        for (LetterStat ls: letterList){
            if (ls.getLetter() == l.getLetter()){
                ls.addPlayed();
                found = true;
            }
        }
        if (!found) {
            letterList.add(new LetterStat(l.getLetter(), 1, 1));
        }
        try {
            saveLetterStats();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void drawLetter(Letter l){
        boolean found = false;
        for (LetterStat ls: letterList){
            if (ls.getLetter() == l.getLetter()){
                ls.addDrawn();
                found = true;
            }
        }
        if (!found) {
            letterList.add(new LetterStat(l.getLetter(), 0, 1));
        }
        try {
            saveLetterStats();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addWord(Word w){
        System.out.println("adding " + w.toString() + " to bank");
        boolean found = false;
        for (WordStat ws: wordBank){
            if (ws.getWord().equals(w.toString())){
                found = true;
                ws.useWord();
            }
        }
        if (!found){
            wordBank.add(new WordStat(w.toString(),1));
        }
        try {
            saveWordBank();
        } catch (Exception e) {
            System.out.println("can't write words");
        }
    }
}
