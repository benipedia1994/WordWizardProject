package edu.gatech.seclass.words6300;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import edu.gatech.seclass.words6300.data.Statistics;
import edu.gatech.seclass.words6300.exceptions.*;

public class Game {
    private Statistics gameStats;
    private int score;
    private GameSettings settings;
    private int currentTurn;
    private boolean isOver;
    private ArrayList<Word> playedWords;
    private ArrayList<Letter> pool;
    private ArrayList<Letter> board;
    private ArrayList<Letter> rack;

    public Game(GameSettings settings, Statistics stats) {
        this.gameStats = stats;
        this.settings = settings;
        this.score = 0;
        this.currentTurn = 0;
        this.isOver = false;
        this.playedWords = new ArrayList<Word>();
        this.pool = new ArrayList<Letter>();
        this.board = new ArrayList<Letter>();
        this.rack = new ArrayList<Letter>();

        // generate the pool from the settings
        for (LetterSettings ls: this.settings.getLetterSettings()) {
            for (int i = 0; i < ls.getCount() ; i++) {
                pool.add(new Letter(ls.getLetter(), ls.getValue()));
            }
        }

        // Pull 4 tiles for the board
        for (int i = 0; i < 4 ; i++) {
            board.add(takeLetter());
        }

        // Pull 7 tiles for the rack
        for (int i = 0; i < 7 ; i++) {
            rack.add(takeLetter());
        }
    }
    //file constructor
    public Game(File inputFile,GameSettings settings, Statistics stats){
        this.rack=new ArrayList<Letter>();
        this.board= new ArrayList<Letter>();
        this.pool = new ArrayList<Letter>();
        this.isOver=false;
        this.playedWords= new ArrayList<Word>();
        this.gameStats=stats;
        this.settings=settings;

        try {
            Scanner scanner = new Scanner(inputFile);

            this.score=scanner.nextInt();
            this.currentTurn=scanner.nextInt();
            char letterBuffer;
            int numberBuffer;

            for(int i = 0; i< 7; i++){
                letterBuffer= scanner.next().charAt(0);
                numberBuffer=scanner.nextInt();
                rack.add(new Letter(letterBuffer, numberBuffer));
            }
            for(int i = 0; i <4; i++){
                letterBuffer= scanner.next().charAt(0);
                numberBuffer=scanner.nextInt();
                board.add(new Letter(letterBuffer, numberBuffer));
            }
            System.out.println(scanner.hasNext());
            while(scanner.hasNext()){
                letterBuffer=scanner.next().charAt(0);
                numberBuffer=scanner.nextInt();
                pool.add(new Letter(letterBuffer, numberBuffer));
            }
            scanner.close();


        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void makeWord(String attempt) throws Exception {
        if (this.isOver){
            throw new GameOverException();
        }

        // Convert String to a ArrayList of Letters
        ArrayList<Letter> letters = stringToLetterList(attempt);


        Letter boardLetter = null;

        for (Letter l : letters){
            if (!rack.contains(l)){
                if (boardLetter != null) {
                    throw new RackException(l.getLetter());
                }
                boardLetter = l;
            }
        }

        if (boardLetter == null) {
            boolean found = false;
            for (Letter l: letters) {
                if (board.contains(l)){
                    found = true;
                    boardLetter = l;
                }
            }
            if (!found) {
                throw new BoardException("Must use at least one letter from the board");
            }
        } else {
            if (!board.contains(boardLetter)){
                throw new BoardException("Letter: " + boardLetter.getLetter() + " not on board");
            }
        }

        letters.remove(boardLetter);

        // Create a new word
        Word playedWord = new Word(stringToLetterList(attempt));

        // Make sure the word hasn't been played
        if (playedWords.contains(playedWord)){
            throw new WordException();
        }

        // add the word to the played list
        playedWords.add(playedWord);
        // up the score
        this.increaseScore(playedWord.getScore());

        //updateWordData();
        gameStats.addWord(playedWord);
        //update LetterStatisticsActivity file

        //updateLetterData();

        // if there are no more letters, end the game, and award the bonus
        if (pool.isEmpty()){
            currentTurn++;
            this.isOver = true;
            increaseScore(10);
            gameStats.collectGame(new GameStat(this.score, this.currentTurn, this.settings));
        } else {
            // replace the used board letter with one from the played word
            board.remove(boardLetter);
            board.add(playedWord.getRandomLetter());
            for (Letter letter: letters){
                gameStats.playLetter(letter);
            }
            // replace the used rack letters with new ones
            discardLetters(letters);
            // end the turn
            endTurn();
        }

    }

    public void swapLetters(String toDiscard) throws Exception {
        // Convert String to a ArrayList of Letters
        ArrayList<Letter> discards = stringToLetterList(toDiscard);

        //System.out.println("swapping " + Integer.toString(discards.size())+ " letters");
        if (this.isOver){
            throw new GameOverException();
        }
        if (this.pool.size() < discards.size()){
            throw new PoolException();
        }
        // Make sure the rest of the letters came from the rack
        if (!rack.containsAll(discards)) {
            throw new RackException();
        }
        //System.out.println("dumping existing tiles");
        discardLetters(discards);
        //System.out.println("putting tiles back into pool");
        pool.addAll(discards);
        endTurn();
    }

    private void endTurn(){
        currentTurn++;
        if (currentTurn >= settings.getMaxTurns()){
            this.isOver = true;
            gameStats.collectGame(new GameStat(this.score, currentTurn, this.settings));
        }
    }

    private void discardLetters(ArrayList<Letter> discards){
        // get the number of new tiles to draw
        int count = discards.size();

        // remove the discarded tiles from the rack
        Iterator itr = discards.iterator();
        while(itr.hasNext()){
            Letter l = (Letter)itr.next();
            Iterator jtr = rack.iterator();
            while(jtr.hasNext()){
                Letter l2 = (Letter)jtr.next();
                if (l.equals(l2)){
                    jtr.remove();
                    break;
                }
            }
        }

        // if the pool size is less than the count, grab all the remaining tiles
        if (pool.size() < count){
            count = pool.size();
        }

        //System.out.println("getting " + Integer.toString(count) + " tiles from pool");

        // Add N new tiles to the rack
        for (int i = 0; i < count ; i++) {
            this.rack.add(takeLetter());
        }
    }

    private Letter takeLetter(){
        //System.out.println("taking a new letter");
        Letter taken;
        Random rand = new Random();
        int n = rand.nextInt(this.pool.size());
        taken = this.pool.get(n);
        //System.out.println("got " + taken.getLetter());
        this.pool.remove(n);
        gameStats.drawLetter(taken);
        return taken;
    }

    // Convert String to a ArrayList of Letters
    private ArrayList<Letter> stringToLetterList(String word) {
        ArrayList<Letter> letters = new ArrayList<Letter>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length ; i++) {
            LetterSettings ls = this.settings.getLetter(chars[i]);
            Letter l = new Letter(ls.getLetter(), ls.getValue());
            letters.add(l);
        }
        return letters;
    }


    public void increaseScore(int value){
        this.score += value;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public boolean isOver() {
        return isOver;
    }

    public ArrayList<Word> getPlayedWords() {
        return playedWords;
    }

    public ArrayList<Letter> getBoard() {
        return board;
    }

    public ArrayList<Letter> getRack() {
        return rack;

    }
    public ArrayList<Letter> getPool(){
        return pool;
    }
    public int getMaxTurns(){
        return this.settings.getMaxTurns();
    }

    public int remainingTileCount() {
        return this.pool.size();
    }
}
