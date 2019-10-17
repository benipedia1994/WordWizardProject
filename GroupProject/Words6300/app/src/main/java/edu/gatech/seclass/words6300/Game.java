package edu.gatech.seclass.words6300;


import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int score;
    private GameSettings settings;
    private int currentTurn;
    private boolean isOver;
    private ArrayList<Word> playedWords;
    private ArrayList<Letter> pool;
    private ArrayList<Letter> board;
    private ArrayList<Letter> rack;

    public Game(GameSettings settings) {
        this.settings = settings;
        this.score = 0;
        this.currentTurn = 1;
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

    public void makeWord(String attempt) throws Exception {
        if (this.isOver){
            throw new Exception("GAME OVER");
        }

        // Convert String to a ArrayList of Letters
        ArrayList<Letter> letters = stringToLetterList(attempt);

        // Make sure one of the letters is from the board
        Letter boardLetter = null;
        for (Letter l : letters){
            if (board.contains(l)){
                boardLetter = l;
            }
        }
        if (boardLetter != null){
            letters.remove(boardLetter);
        } else {
            throw new Exception("Must use at least one letter from the board");
        }

        // Make sure the rest of the letters came from the rack
        if (!rack.containsAll(letters)) {
            throw new Exception("Letters must be in the rack");
        }

        // Create a new word
        Word playedWord = new Word(stringToLetterList(attempt));

        // Make sure the word hasn't been played
        if (playedWords.contains(playedWord)){
            throw new Exception("Duplicate word");
        }

        // add the word to the played list
        playedWords.add(playedWord);
        // up the score
        this.increaseScore(playedWord.getScore());
        //update WordsStatistics File
        //updateWordData();
        //update LetterStatisticsActivity file
        //updateLetterData();
        // if there are no more letters, end the game, and award the bonus
        if (pool.isEmpty()){
            this.isOver = true;
            increaseScore(10);
        } else {
            // replace the used board letter with one from the played word
            board.remove(boardLetter);
            board.add(playedWord.getRandomLetter());
            // replace the used rack letters with new ones
            discardLetters(letters);
            // end the turn
            endTurn();
        }

    }

    public void swapLetters(ArrayList<Letter> discards) throws Exception {
        System.out.println("swapping " + Integer.toString(discards.size())+ " letters");
        if (this.isOver){
            throw new Exception("GAME OVER");
        }
        if (this.pool.size() < discards.size()){
            throw new Exception("Pool not big enough");
        }
        System.out.println("dumping existing tiles");
        discardLetters(discards);
        System.out.println("putting tiles back into pool");
        pool.addAll(discards);
        endTurn();
    }

    private void endTurn(){
        currentTurn++;
        if (currentTurn >= settings.getMaxTurns()){
            this.isOver = true;
        }
    }

    private void discardLetters(ArrayList<Letter> discards){
        int count = discards.size();
        this.rack.removeAll(discards);
        if (pool.size() < count){
            count = pool.size();
        }
        System.out.println("getting " + Integer.toString(count) + " tiles from pool");
        for (int i = 0; i < count ; i++) {
            this.rack.add(takeLetter());
        }
    }

    private Letter takeLetter(){
        System.out.println("taking a new letter");
        Letter taken;
        Random rand = new Random();
        int n = rand.nextInt(this.pool.size());
        taken = this.pool.get(n);
        System.out.println("got " + taken.getLetter());
        this.pool.remove(n);
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

    public int remainingTileCount() {
        return this.pool.size();
    }
}
