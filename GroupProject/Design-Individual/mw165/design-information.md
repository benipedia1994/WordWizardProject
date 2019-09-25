# Design Information

## When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.

To realize this the Player class can (1) "play" a WordGame class represented by the plays association, (2) "view" the Statistics class represented by the views association, (3) "adjust" the GameSettings represented by the adjusts association.

---

## When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain maximum number of turns and (2) may adjust the number of and the letter points for each letter available in the pool of letters, starting with the default matching the English Scrabble distribution (12 E’s worth 1 point each, 4 D’s worth 2 points each, etc).

To realize this, the GameSettings has an attribute named max_turns which can be altered via the setTurns method.  The GameSettings also has an aggregation of LetterSettings.  This is defined with a quanity of 26 unique LetterSettings classes.

---

## When choosing to play a word game, a player will: Be shown a ‘board’ consisting of 4 letters drawn from the pool of available letters.

This is realized by the WordGame containing a Board class.  The board class is only allowed to contain 4 letters at a time.  The act of showing will be handled within the GUI implementation.

---

## Be shown a ‘rack’ of 7 letters also drawn from the pool of available letters.

This is realized by the WordGame containing a Rack class.  The rack class is limited to containing only 7 letters.  The act of showing will be handled within the GUI implementation.

---

## On each turn, up to the maximum number of turns either: Enters a word made up of one or more letters from the player’s rack of letters, and one from the board.  The word must contain only valid letters and may not be repeated within a single game, but does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).

This is realized by the makeWord method in the WordGame class.  The method takes two parameters, the first is a single letter that must match one of the letters from the board, and a list of letters that must all match letters on the rack.

---

## Or Swap 1-7 letters from their rack with letters from the pool of letters.  This is the only time letters are returned to the pool during a game.

This is realized by the swapLetters method in the WordGame class. It takes one parameter, a list of Letters that must all match letters that are currently on the rack.  It will remove those letters from the rack, and make N calls to returnLetter method in the Pool class.  Then it will make N calls to takeLetter from the Pool class, and add each of those returned letters to the Rack.

---

## After a word is played, that letter on the board will be replaced by a different random letter from the word that was just played.  Example:  If ‘c’ is on the board, and ‘j’,’a’,’k’,’e’,’t’,’s’ are part of the rack, then the player may enter ‘jackets’ as a word, and the ‘c’ will be randomly replaced by the ‘j’,’a’,’k’,’e’,’t’, or ’s’ for the next turn on the board.

This is realized by the makeWord method returning a new Word.  The Board class then calls replace letter with the letter passed to makeWord and the Word that it returned.  It then removes the letter from it's list, calls getRandomLetter off the Word class, and then adds that letter to the list of letters on the board.

---

## After a word is played, the tiles used from the rack are replaced from the pool of letters.

This is realized by counting the number of tiles removed from the rack, and then calling takeLetter N times from the Pool class and adding each of those to the Rack letters list.

---

## After a word is played, the player’s score will increase by the total number of points for the letters in the word, including the letter used from the board. (So ‘jackets’, if using default values, would score 20 points.)

This is realized by taking the points value from the Word class returned by makeWord, and then calling increaseScore on the WordGame class by that value

---

## If the pool of letters is empty and the rack cannot be refilled, the player will score an additional 10 points.

This is realized by checking to see if the list of letters in the Pool class is empty.  If it is, then call increaseScore with a value of 10

---

## When the maximum number of turns has been played, or the pool of letters is empty and the rack cannot be refilled, the game will end, and the final score will be displayed before returning to the first menu.

This is realized by checking to see if the currentTurn value in WordGame is equal to the max_turns value in the settings variable.  If it is then the isOver will be set to true. Displaying the score and return to the menu will be handled in the GUI implementation.

---

## A player may choose to leave a game in progress at any time.  Selecting to play a game from the menu should then return to the game in progress.

This is handled by the GUI implementation

---

## When choosing to view statistics, the player may view (1) game score statistics, (2) letter statistics or (3) the word bank.

Choosing which stat to navigate to will be handled by the GUI implementation

---

## For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:
The final game score
The number of turns in that game
The average score per turn
The player may select any of the game scores to view the settings for that game’s maximum number of turns, letter distribution, and letter points.

This is realized within the Statistics class.  The Statistics class contains a list of GameStats objects.  Each GameStats instance has a score and a turns value.  It also contains the computed value scorePerTurn.  Each GameStats also contains a copy of the GameSettings used for that particular game.  Whenever a game is finished, a GameStats instance is added to the games list in the Statistics class via the collectGame method.

---

## For letter statistics, the player will view the list of letters, in ascending order by number of times played, displaying:
The total number of times that letter has been played in a word
The total number of times that letter has been traded back into the pool
The percentage of times that the letter is used in a word, out of the total number of times it has been drawn

This is realized with the Statistics class.  The Statistics class contains a list of LetterStats objects.  There is one LetterStats instance for each letter, so 26 in total.  Each LetterStats instance contains the number of times played, and the number of times it was traded as well as a computed value "percentPlayed" describing how many times it was played vs traded.  Each LetterStat is updated by either calling playLetter when makeWord is called, or tradeLetter whenever swapLetters is called.

---

## For the word bank, the player will view the list of words used, starting from the most recently played, displaying:
The word
The number of times the word has been played

This is realized with the Statistics class.  The Statistics class contains a list of WordStats objects referred to as the wordBank.  Each WordStats instance contains the unique Word, the count of how many times that word has been played, and a datetime stamp representing the last time is was played.  This wordBank is updated everytime makeWord is called.

---

## The user interface must be intuitive and responsive.

This will be handled in the GUI implementation

---

## The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.

This is a performance consideration not modelled in this structural diagram

