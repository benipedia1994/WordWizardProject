
<br />1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.
<br />	This was not designed as its system implementation rather than a design.
<br />	Class User can be Class Player, and Class Player uses Class Game (requirement #3).
<br />	Class User can modify Class Settings (requirement #2).
<br />	Class User can Access Class Statistics (requirement #5).
<br />
<br />2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain maximum number of turns and (2) may adjust the number of and the
<br />letter points for each letter available in the pool of letters, starting with the default matching the English Scrabble distribution (12 E’s worth 1 point each, 4 D’s worth 2 points each, etc).
<br />	Class Settings have attributes: (1) maxNumTurn: int, and (2) letters: HashMap<char, int[]> -- the int array in HashMap has two integers, number of letters and letter points for each letter.
<br />	The above attributes can be set by getters, but these methods will not be included here as the instructor advised not to.
<br />
<br />3. When choosing to play a word game, a player will:
<br />a. Be shown a ‘board’ consisting of 4 letters drawn from the pool of available letters.
<br />b. Be shown a ‘rack’ of 7 letters also drawn from the pool of available letters.
<br />	Class Game has attributes: (1) board: char[], and (2) pool: char[], because board and pool are shared.
<br />	Class Player has an attribute rack: char[], because each Player has their own rack.
<br />
<br />c. On each turn, up to the maximum number of turns either:
<br />i. Enter a word made up of one or more letters from the player’s rack of letters, and one from the board. The word must contain only valid letters and may not be repeated within a single game, but does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).
<br />Or ii. Swap 1-7 letters from their rack with letters from the pool of letters. This is the only time letters are returned to the pool during a game.
<br />	Recall that maximum number of turns is an attribute of Class Settings (maxNumTurns: int, requirement #2).
<br />	Class Player has a method enterWord(String): Boolean. This method returns true if the word can be added to the board (if all the letters are found in player’s rack and on the board), and false if it cannot be. 
<br />	Class Player has a method swapLetters(int): Boolean. This method returns true if the letters on Player’s rack were successfully swapped with the letters on Game’s pool, and false if otherwise.
<br />	Class Player plays Class Game, therefore, Player depends on Game.
<br />d. After a word is played, that letter on the board will be replaced by a different random letter from the word that was just played. Example: If ‘c’ is on the board, and ‘j’,’a’,’k’,’e’,’t’,’s’ are part of the rack, then the player may enter ‘jackets’ as a word, and the ‘c’ will be randomly replaced by the ‘j’,’a’,’k’,’e’,’t’, or ’s’ for the next turn on the board.
<br />	Class Game has a method replaceLetter(int, int, char[]): void. This method is called whenever Method enterWord(String) returns true (when a word is entered), and it replaces the letter at position (argument 1, argument 2) by a random letter in the list of letters (argument 3) used from the rack.
<br />
<br />e. After a word is played, the tiles used from the rack are replaced from the pool of letters.
<br />	Class Player has replaceRack(): void which changes the value of its attribute rack, as well as Class Game’s attribute pool.
<br />	This method is invoked when enterWord(String) returns true.
<br />
<br />f. After a word is played, the player’s score will increase by the total number of points for the letters in the word, including the letter used from the board. (So ‘jackets’, if using default values, would score 20 points.)
<br />	Class Player has an attribute score: int.
<br />	Class GameScore has information about the current round of game. Class GameScore has attributes: (a) maxNumTurns: int, (b) letterDistribution: char[], (c) letterPoints: HashMap<char, int>, (d) numTurns: int, (e) avgScorePerTurn: int,  and (f) id: int which is the unique identifier for GameScore. Class GameScore is updated by Class Setting every time a game ends, but not when the player chooses to leave. (Class GameScore was designed for requirement #6 initially and that is why it contains extra attributes).
<br />	Class Player has an method calcScore(String, char[], char[]): void. This method uses the word (argument 1), the letters from the pool (argument 2), the letters from the rack (argument 3), and letter points from attribute letterPoints from Class GameScore to calculate the score and increment the current Player’s score by the result. This method is invoked when enterWord(String) returns true.
<br />	Class GameScore uses information from Class Settings.<br />
	Class Game has Class GameScore.<br />
<br />
g. If the pool of letters is empty and the rack cannot be refilled, the player will score an additional 10 points.<br />
	Class Game has a method isPoolEmpty():Boolean which returns true if the rack is empty. Class Player’s calcScore calls this method and if returned true, adds 10 to the score.
<br /><br />
h. When the maximum number of turns has been played, or the pool of letters is empty and the rack cannot be refilled, the game will end, and the final score will be displayed before returning to the first menu.
<br />	Class Game has an attribute turns:int which displays the number of turns played. This is incremented by 1 whenever a Player takes a turn.<br />
	Class Game has a method plays(int): void which allows the specified (argument 1) player to play. Class Game has an attribute playerTurn: int which specifies which player should take the turn, and is used as the argument for plays(int). playerTurn loops though 1 to numPlayer: int, which is another attribute of Class Game that specifies the number of players.
<br />	Class Game has a method endGame(): void which is invoked when isPoolEmpty() returns true, or when isMaxReached() returns true. endGame() ends the game and update Statistics. To update Statistics, Class Game needs to keep track of needed data. Therefore, an extra class was created for letter tracking – Class LetterRecord, Class LetterRecord has attributes: (a) totalLetterInWord: HashMap<char, int>, (b) totalLetterIntoPool: HashMap<char, int>, and (c) percentageUsed: HashMap<char, int[]>[] which takes two integers: numberUsed and totalNumDrawn as its int array values (for requirement #7). Also, Class Game has an attribute words: LinkedList<String,int> to keep track of recently used words and their counts. When the game ends, this will be used to update Class WordBank in requirement #8. Class Game updates Statistics.
<br /><br />
4. A player may choose to leave a game in progress at any time. Selecting to play a game from the menu should then return to the game in progress.<br />
	Class Game has a method: (a) leaveGame(Boolean): void – the game runs while the Boolean argument is set to false, and when the Boolean argument is set to true, then the status of Class Game is saved, and the player is returned to Class Main.
<br /><br />
5. When choosing to view statistics, the player may view (1) game score statistics, (2) letter statistics or (3) the word bank.<br />
	Class ViewStats has methods: (1) viewGameScoreStats():void, (2) viewLetterStats():void, and (3) viewWordBank():void.<br />
<br />
6. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:<br />
a. The final game score<br />
b. The number of turns in that game<br />
c. The average score per turn<br />
The player may select any of the game scores to view the settings for that game’s maximum number of turns, letter distribution, and letter points.<br />
	Class GameScoreStats has an attribute: stats: Class GameScore, that is a list of Class GameScore (specified below)<br />
	Class GameScoreStats has methods: (1) displayList(): void to display the list of scores in descending order by the final game score to the player, and (2) displaySpecificGame(int): void to display the specified game’s maximum number of turns, letter distribution, and letter points when the player selects a specific game score. Class GameScoreStats is updated by Class Game every time a game ends, but not when the player chooses to leave. 
<br /><br />
7. For letter statistics, the player will view the list of letters, in ascending order by number of times played, displaying:<br />
a. The total number of times that letter has been played in a word<br />
b. The total number of times that letter has been traded back into the pool<br />
c. The percentage of times that the letter is used in a word, out of the total number<br />
of times it has been drawn<br />
	ClassLetterStatistics has an attribute letterStats: LetterRecord, which is updated every time a game finishes.<br />
	Class LetterStatistics has methods: (a) displayTotalLetterInWord(char): void that will display the total number of times that the specified letter has been played in a word, (b) displayTotalLetterIntoPool(char): void that will display the total number of times that the specified letter has been traded back into the pool, (c) displayPercentageUsed(char): void that will display the percentage of times that the letter is used in a word by calculating the percentage (numberUsed / totalNumDrawn), and (d) displayLetterStatistics(): void which will display above three methods for all letters.<br />
<br />
8. For the word bank, the player will view the list of words used, starting from the most recently played, displaying:<br />
i. The word<br />
ii. The number of times the word has been played<br />
	Class WordBank has attributes: (a) words: LinkedList<HashMap<String, int>> — String indicates the word, and int indicates the number of times the word has been played, and LinkedList is used to organize the list by most recently played games at the end. (If the word can only appear once in the list, then LinkedList will not be the best data structure. However, this was not specified).<br />
	Class WordBank has methods: (a) displayWordBank(): void which displays the attribute words.<br />
	Class WordBank is updated by Class Board every time a game ends, but not when the player chooses to leave. <br />
<br />
9. The user interface must be intuitive and responsive.<br />
	This is not represented in my design as it will be handled within the GUI implementation.<br />
<br />
10. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.<br />
	This is not represented in my design as they depend on how efficient the implementation is, not the design.<br />
<br />
11. For simplicity, you may assume there is a single system running the application.<br />
	This is not represented in my design as it depends on the system, not the design.
