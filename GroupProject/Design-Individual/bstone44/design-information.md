# Design Information

## 1.	When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings. 

To fulfill this requirement I added three functions startGame(), adjustSettings(), and viewStatistics(). startGame() will start the game and initialize variables according to the settings

## 2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain maximum number of turns and (2) may adjust the number of and the letter points for each letter available in the pool of letters, starting with the default matching the English Scrabble distribution (12 E’s worth 1 point each, 4 D’s worth 2 points each, etc).

To update the letters the player can call Settings.setMap(). Settings.setMap() will take in a number for each letter.  The number will be used to create the letters hashmap of letter to value

## 3. When choosing to play a word game, a player will:
### a. Be shown a ‘board’ consisting of 4 letters drawn from the pool of available letters.

Upon starting the game, the board will call draw() which draws four letters at random from the pool and adds them to a list of characters called letters. displayBoard will then be called

### b. Be shown a ‘rack’ of 7 letters also drawn from the pool of available letters.

Upon starting the game, the board will call Rack.rackDraw(). rackDraw will draw an amount of cards equal to the maximum amount of cards(7) minus the current amount. Then Rack.displayRack() will be called

### c. On each turn, up to the maximum number of turns either:
#### i. Enter a word made up of one or more letters from the player’s rack of letters, and one from the board.  The word must contain only valid letters and may not be repeated within a single game, but does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).

If the player chooses this option the makeWord.makeWord() function will be called. This function is the main workhorse of this app. The function will first concatenate the list of letters to make a word. Then it will pass the word into Words.checkUsed(), if the word has not been used before it will update Words.addUsed().If the word has been used before it will call makeWord.invalidWord(), which will take the user back to his two initial options for the turn. 



#### or
#### (ii)Swap 1-7 letters from their rack with letters from the pool of letters.  This is the only time letters are returned to the pool during a game.

The requirements don't specify whether this is randomized, but I will assume it is. swapPool() will be called. This function will take in a list of characters from rack and swap them randomly with a list of values from Pool.



### d. After a word is played, that letter on the board will be replaced by a different random letter from the word that was just played.  Example:  If ‘c’ is on the board, and ‘j’,’a’,’k’,’e’,’t’,’s’ are part of the rack, then the player may enter ‘jackets’ as a word, and the ‘c’ will be randomly replaced by the ‘j’,’a’,’k’,’e’,’t’, or ’s’ for the next turn on the board.

After verifying that the word hasn't been used and is valid, the makeWord() function will call swapletters, which will have the same input as makeWord() and will swap the character chosen from Board with one of the ones chosen from Rack

### e. After a word is played, the tiles used from the rack are replaced from the pool of letters.

After Rack.swapBoard() is called in the function makeWord.makeWord() function, Rack.discard() will be called on the remaining letters chosen from Rack. These letters will be deleted. Then Rack.rackDraw() will be called again.

### f. After a word is played, the player’s score will increase by the total number of points for the letters in the word, including the letter used from the board. (So ‘jackets’, if using default values, would score 20 points.)

After the word is validated in makeWord() it will then call makeWord.getValue() and use the hashmap of letters to numerical values to calculate the pointValue of the word. This will then be passed to Score.Increment(), which will update the score value.

### g. If the pool of letters is empty and the rack cannot be refilled, the player will score an additional 10 points.

If the function rackDraw cannot draw any more letters from pool, it will pass 10 into Score.Increment().

### h. When the maximum number of turns has been played, or the pool of letters is empty and the rack cannot be refilled, the game will end, and the final score will be displayed before returning to the first menu.

The function checkAgainstMax will run at the end of every turn. if currentTurn==maxTurn, endGame() will be called. Inside of endGame a Score.displayScore() function is called. Also, inside of emptyBonus is an endGame() call as well, it occurs after Score.Increment has been called

## 4. A player may choose to leave a game in progress at any time.  Selecting to play a game from the menu should then return to the game in progress.

Upon the first time at the start menu, GameFinished will be set to 1. Once a game is started, the value is set to 0 and the values will not be reinitialized upon calling Player.startGame(). the function Score.endGame() will reset Player.gameFinished() to 1

## 5. When choosing to view statistics, the player may view (1) game score statistics, (2) letter statistics or (3) the word bank.

When the player chooses to view statistics, they will then have the choice to call Statistics.WorldBankView(), Statistics.GameScoreView() or Statistic.LetterView(). each of these calls will display the respective data

## 6. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:
### a.The final game score
### b.The number of turns in that game
### c.The average score per turn
The player may select any of the game scores to view the settings for that game’s maximum number of turns, letter distribution, and letter points.

When a player first calls Player.startGame(), a new instance of Game will be created , and Game.getSettingsInfo() will also be called. This will pass the letter distribution, Maximum number of turns, and letter points into the Game object. On a call of endgame, the total turns and final points will be passed in. Then calcAvg will be called by dividing pts/turns. Once the player selects the game using the selectGame() function, that games data will be displayed.

## 7. For letter statistics, the player will view the list of letters, in ascending order by number of times played, displaying:
### a.The total number of times that letter has been played in a word
### b.The total number of times that letter has been traded back into the pool
### c.The percentage of times that the letter is used in a word, out of the total number of times it has been drawn

Every time a word is accepted in the makeWord function, the LetterStatistics.incUsed() function will update the list of statLetters in LetterStatistics. Also, every time draw, rackDraw, or swapPool is called, incDrawn() and incTraded() will be called. The list of letters will be sorted using LetterStatistics.sort() and displayed upon calling Statistics.LetterView()

## 8. For the word bank, the player will view the list of words used, starting from the most recently played, displaying:
###i.The word
###ii.The number of times the word has been played

Whenever a word is accepted in makeWord.makeWord(), it will be sent to WordBank. If it is not in the list of words, it will be added with WordBank.addWord(). If it is in the list of words it will be incremented with WordBank.Increment(). Both of these calls will move the word to the top of the list of words. The list will be displayed in order when Statistics.WordBankView() is called.


The following three requirements are related not related to design so they will not be represented.

## 9. The user interface must be intuitive and responsive.
## 10. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
## 11. For simplicity, you may assume there is a single system running the application.
