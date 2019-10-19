package edu.gatech.seclass.words6300.data;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import edu.gatech.seclass.words6300.GameSettings;
import edu.gatech.seclass.words6300.GameStat;
import edu.gatech.seclass.words6300.Letter;
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
        Collections.sort(wordBank, new SortbyWord());
        return wordBank;
    }

    public ArrayList<LetterStat> getLetterList() {
        Collections.sort(letterList, new SortbyLetter());
        return letterList;
    }

    public ArrayList<GameStat> getGameList() {
        Collections.sort(gameList, new SortbyGame());
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
            String line = scanner.nextLine();
            //System.out.println(line);
            tokens = line.split(DELIMITER);
            if (tokens.length == 4) {
                letterList.add(new LetterStat(tokens[0].charAt(0), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
            }
        }
        scanner.close();
    }

    private void loadWordBank() throws Exception{
        File file = new File(app.getFilesDir(), WORD_FILE);
        if (!file.exists()){
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        final String DELIMITER = ",";
        String[] tokens;
        while(scanner.hasNext()){
            tokens = scanner.nextLine().split(DELIMITER);
            wordBank.add(new WordStat(tokens[0], Integer.parseInt(tokens[1])));
        }
        scanner.close();
    }

    private void loadGameStats() throws Exception{
        File file = new File(app.getFilesDir(), GAME_FILE);
        if (!file.exists()){
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        final String DELIMITER = "<->";
        String[] tokens;
        while(scanner.hasNext()){
            tokens = scanner.nextLine().split(DELIMITER);
            int score = Integer.parseInt(tokens[0]);
            int turns = Integer.parseInt(tokens[1]);
            int maxTurns = Integer.parseInt(tokens[2]);
            String letterDistribution = tokens[3];
            gameList.add(new GameStat(score, turns, new GameSettings(maxTurns,letterDistribution)));
        }
        scanner.close();
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

    private void saveGameStats() throws Exception {
        FileOutputStream fos = this.app.openFileOutput(GAME_FILE, Context.MODE_PRIVATE);
        OutputStreamWriter os = new OutputStreamWriter(fos);
        for (GameStat gs : gameList){
            os.write(gs.toString());
        }
        os.close();
    }

    public void collectGame(GameStat gs){
        gameList.add(gs);
        try {
            saveGameStats();
        } catch (Exception e) {
            System.err.println(e);
        }
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
            letterList.add(new LetterStat(l.getLetter(), 1, 0, 1));
        }
        try {
            saveLetterStats();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void tradeLetter(Letter l){
        boolean found = false;
        for (LetterStat ls: letterList){
            if (ls.getLetter() == l.getLetter()){
                ls.addTraded();
                found = true;
            }
        }
        if (!found) {
            letterList.add(new LetterStat(l.getLetter(), 0, 1, 1));
        }
        try {
            saveLetterStats();
        } catch (Exception e) {
            System.err.println(e);
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
            letterList.add(new LetterStat(l.getLetter(), 0, 0, 1));
        }
        try {
            saveLetterStats();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void addWord(Word w){
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
            System.err.println(e);
        }
    }
}
