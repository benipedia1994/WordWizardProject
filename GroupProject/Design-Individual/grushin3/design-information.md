# Requirements

### *1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.*
  To complete this requirement, I have added player, adjust the game settings, Play word game UI, and View statistics classes to the UML. Start-up() method under players starts the application for the player to choose (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.

### *2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain maximum number of turns and (2) may adjust the number of and the letter points for each letter available in the pool of letters, starting with the default matching the English Scrabble distribution (12 E’s worth 1 point each, 4 D’s worth 2 points each, etc).*
  To satify this requirement, I have added the following operation the the adjust the game settings class. (1) maximum_amt_turns():int operation which allows for adjustment of maximum number of turns and returns that int. (2) Adjust_points():int operation  which allows for adjust of letter points and returns that float. Additionally, Adjust_letter_numb(): int was added to satify filtering done on the letters in the pool of letters. default() was added as a boolean to enforce the default matching (the English Scrabble
  distribution). Lastly, pool of letters was created as a classes.
  There is a dependency relationship bbetween Pool of Letters, and Adjust the games Settings.

### *3. When choosing to play a word game, a player will:*
#####  *a. Be shown a ‘board’ consisting of 4 letters drawn from the pool of available letters.*
  To realize this requirement, a board class was created and Start_board_letter() will put 4 characters on the board from the Pool of letter class.

#####  *b. Be shown a ‘rack’ of 7 letters also drawn from the pool of available letters.*
  To deliver this requirement, a rack class was created and rack_letter operation will put the first 7 characters

#####  *a. On each turn, up to the maximum number of turns either:*
* ###### *i) Enter a word made up of one or more letters from the player’s rack of letters, and one from the board.  The word must contain only valid letters and may not be repeated within a single game, but does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly). or*
To deliver this requirement, I created player_input operation inorder to enter a word.
I then created valid_player_input under the Board class to satify the requirements for a valid input. (one or more letters from the player’s rack of letters, and one from the board.  The word must contain only valid letters and may not be repeated within a single game, but does not need to be checked against a dictionary). Under Rack class, I create a board_input operation to take the valid user input and put that input on the board.

* ###### *i) Swap 1-7 letters from their rack with letters from the pool of letters.  This is the only time letters are returned to the pool during a game.*
To meet this requirement, under the Rack class is swap_letters operation which interacts with give_swap_letters(in Pool of letters) operation. Give_swap_letters completes the swap of letters from the rack.

#####  *d. After a word is played, that letter on the board will be replaced by a different random letter from the word that was just played.  Example:  If ‘c’ is on the board, and ‘j’,’a’,’k’,’e’,’t’,’s’ are part of the rack, then the player may enter ‘jackets’ as a word, and the ‘c’ will be randomly replaced by the ‘j’,’a’,’k’,’e’,’t’, or ’s’ for the next turn on the board.*
To meet this requirement, under the board class replace_letter having a board_input opertion to complete the following letter random swap. The Board_input operation takes in to consideration "after word is played". Under the Pool of letters class there is give_swap_letters which will give the correct letter to the board from the recently played word.

#####  *e. After a word is played, the tiles used from the rack are replaced from the pool of letters.*
To satify this requirement, under the Rack class the replace_rack operation handles the tile replacement. Valid_player_input and rack_count being less than 7 allows for the application to identify a word has been played and how many tiles need to be called from Pool of letters. Under the Pool of letters class there is a give_rack_letter which give the rack class the letters.

#####  *f. After a word is played, the player’s score will increase by the total number of points for the letters in the word, including the letter used from the board. (So ‘jackets’, if using default values, would score 20 points.)*
To complete these requirements, Under the board class the player_board_points operation keeps track of the player board points. Its' inputs include available points and board inputs inorder to calculate score on the board.

#####  *g. If the pool of letters is empty and the rack cannot be refilled, the player will score an additional 10 points.*
Under the pool of letters class is empty_pool() operation. Which communicates to the Play word game UI, which contains the summation of board points and rack points. This includes the additional 10 points in play_score operation under teh Play word game UI class.


#####  *g. When the maximum number of turns has been played, or the pool of letters is empty and the rack cannot be refilled, the game will end, and the final score will be displayed before returning to the first menu.*
Under the class Play word game UI, there is game_end() operation.it contains turn_max,
empty_pool, and replace_rack. Turn_max operation is udner the board class and takes current turn numer to check if game is at it maximum turn count. empty_pool() checks
for a empty() pool. Lastly replace_rack, if null, means that rack cannot be filled.

###  *4. A player may choose to leave a game in progress at any time.  Selecting to play a game from the menu should then return to the game in progress.*
Under the Play word game UI Class, the following operations assist in completing
the operation. Game_in_progress(), New_game(), and leave_game().

### *5. When choosing to view statistics, the player may view (1) game score statistics, (2) letter statistics or (3) the word bank.*
To satify the requirments,the following classes where created: Word_bank, Game_score_statistics, and Letter_statistics. Addtionally, under the view statistics class the follow attributes activate these classes: view_game_score_statistics(), view_letter_statistics(), and view_word_bank().


### *6. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:*
##### *a. The final game score*
To satify the requirements, under the Game_score_statistics class. score_list attribute shows the list of scores in descending order. Final_scores operations used to get and store the final score of the games from the play word game UI.

##### *b. The number of turns in that game*
To satify the requirements, under the Game_score_statistics class. get_turns_per_game operations takes input max_amt_turns to get the maximum number of turns per game. Which can be averaged to get turns per game.

###### *c. The average score per turn*
To complete the requiremnts, under the Game_score_statistics class points_per_turn()
operations performs this requiremnt. max_amt_turns sets a upper limit on the points.
Turn count is needed because it is able to points a pointer to number of points each turn.


### *7. For letter statistics, the player will view the list of letters, in ascending order by number of times played, displaying:*
##### *a. The total number of times that letter has been played in a word*
To complete the requirment, under the Letter_statistics classs the letter_count operation
handles the requiremnt. The inputs are the board_inputs inorder to loop through and count the occurences of letters. Additionally there is a letter_list attribute that
shows a list of letters in ascednign order by the number of times played.

##### *b. The total number of times that letter has been traded back into the pool*
To take this requirement into account, under the Letter_statistics class the letter_pool swap. This takes inot accoutn swqp_letter from the Rack class. The operation will count each letter as the swap is ocurring.

##### *c. The percentage of times that the letter is used in a word, out of the total number of times it has been drawn*
To deliver this requirement, under the letter and statistics class is the letter_percentage operation. This operation has give_swap_letters for pool of letter
to get total count of the letter counted and total number drawn. Board_input
from board class to get the number of times a particular letter was played.





### *8. For the word bank, the player will view the list of words used, starting from the most recently played, displaying:*
###### *i. The word*
To deliver this requirement, a word_bank class was created additionally the list_words() attribute shows the list of words starting iwth teh word
###### *ii. The number of times the word has been played*
Under word_bank class is word_count which takes the board_input from board to
count the number of words played represented with an int value.

### *9. The user interface must be intuitive and responsive.*
This is not represented in my design, as it will be handled entirely within the UI/UX design and GUI implementation.

### *10. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.*
This is not represented in my design, as it will be handled entirely within the System implementation.

### *11. For simplicity, you may assume there is a single system running the application.*
This is not represented in my design, as it will be handled entirely within the System implementation.
