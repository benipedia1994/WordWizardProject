# Test Plan

*This is the template for your test plan. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: Gabriel Rushin _ Team 28

## 1 Testing Strategy

### 1.1 Overall strategy

*This section should provide details about your unit-, integration-, system-, and regression-testing strategies. In particular, it should discuss which activities you will perform as part of your testing process, and who will perform such activities.*

Testing will be ran by Gabriel Rushin.

The overall strategy  for unit testing includes the following:
1. *Unit Testing:* Will include black-box testing and white box testing techinques.

2. *Intergration testing:* Will be appoarch with the big bang appoarch. This invloves all components of the application will be integrated and then tested upon.

3. *System Testing:* Will be done be 2 individuals outside of team 28 to ensure the applciation is funcation and easy to use. All use cases will be developed from the use case descriptions will be tested by each person in the group.

4. *Regression testing strategy:* After each update to the system previous Unit, Intergration, and System testing will be performed on the after the updated. Additionally, new test will be created on every level after update

### 1.2 Test Selection
*Here you should discuss how you are going to select your test cases, that is, which black-box and/or white-box techniques you will use. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

Testing cases will be selected utilizing category partition method.<br />
White box and black box methods will be utilized at both the unit and sytem level.


Black box testing techniques:
  1. Input testing: create unit test to ensure valid inputs from the user- *unit*
  2. Boundary Value: create unit test for any boundary values.(e.g. player score and negative values) - *unit*
  3. Load testing will be perfomed  - *system*

White-box testing (Unit Testing & integration):
  1. Structural Techniques:
     * Statement testing: Create unit test to ensure all statements of the progam run(e.g. value is int) - *unit*
     * Condition testing: Create unit test on the if statements of the code - *unit*
     * Path testing: Create unit test on execuite able paths of the progam (e.g. view statistics of the game, changing game settings of the board game) - *unit*
     * Branch testing: Create unit test on the possible decision that the scrabble game is able to take(e.g. when to add bonus points for the player) - *unit*
     * Usability testing will be done at the system level. - *system*

### 1.3 Adequacy Criterion

*Define how you are going to assess the quality of your test cases. Typically, this involves some form of functional or structural coverage. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

Evaluation of quality of test cases will inlcude the following:
1. Output of the test case
2. Expected/desire length of time for test cases
3. Number of correct tests
4. Number of tests
5. Number of incorrect tests
6. Number of bugs found
7. Types of bugs cause
8. Location of bugs in the code
9. How many test cases executed
10. Type  of test (Structural and Functional)
11. High level of code coverage.


### 1.4 Bug Tracking

*Describe how bugs and enhancement requests will be tracked.*

Bugs and enchancement request will be handled in Github.

### 1.5 Technology

*Describe any testing technology you intend to use or build (e.g., JUnit, Selenium).*

Junit will be utilized for unit testing.Selenium will be utilized for framework testing. Expresso will automatic testing. Lastly, TSL generator may be used where applicable.


## 2 Test Cases

*This section should be the core of this document. You should provide a table of test cases, one per row. For each test case, the table should provide its purpose, the steps necessary to perform the test, the expected result, the actual result (to be filled later), pass/fail information (to be filled later), and any additional information you think is relevant.*

| Test case | Purpose | Steps | Expected result | Acutal result (to be filled later) | pass/fail info| additional info |
| ----------- | ----------- |----------- |-----------  | ---- | ---- | ---- |
| Play window appears | Test use case *Play Word Game*  | Path test which test if the play window appears | Path leads to play window | tbd | tbd | tbd |
| Board game appears | Test use case *Play Word Game*  | Path test to test if Board game appears | Path leads to Board game | tbd | tbd | tbd |
| Player_enter_word_test   |  Test Use Case *Play Word Game*  | Create Unit test that takes user input for letters only in the rack  | Output is user input that is a creation from letters in the rack  | tbd  | tbd  | tbd |
| player_swap_word_test   | Test use case *Play Word Game*  | Create Unit test that swaps letters from rack and replaces letters from rack  | 1. Player can swap Letters <br/> 2.Number of choosing to swap is the same number replaced.<br/> 3. swap letters give letters back at random  | tbd  | tbd  | tbd  |
|  maximum_turns_test | test use case *Play Word Game*  | Create Unit test that once maximum amount of turns is reach no other in-game action can be done.  | End Game screen appears  | tbd  | tbd  | tbd  |
| player_score_update_test | test use case *Enter a Word*  |  Create a Unit test and structural test to test player_score_update  | Player score should be correct base on settings and rules of the game  | tbd  | tbd  | tbd  |
| player_turn_update | test use case *Enter a Word*  | Create a Structural test which after a word is enter the player turn is updated   | Player turn is update to increase by one  | tbd  | tbd  | tbd  |
| view_statistics_windows | test use case *View Statistics* | Create a Path test that tests the opening of the view statistics window    |  Path is lead to the view statistics window | tbd  | tbd  | tbd  |
| view_game_score_statistics_window  | test use case *View Game Score Statistics*   | Create a pathtest for view game score window   | Path should be to view game score window   | tbd   | tbd   | tbd  |
| view_game_score_metric   | test use case *View Game Score Statistics  | Create a unit test that tests all metrics in game score  | Metric score should be correct based on game settings and gameplay  | tbd  | tbd  | tbd  |
| letter_statistics_test | test use case *View Letter Statistics*   | Create a Unit test|  Path should be to view letter statics  |  tbd | tbd | tbd  |
| letter_statistics_metric_test   | test use case *View Letter Statistics*  | Create a Unit test  | Metric score should be correct based on game settings and gameplay | tbd  | tbd  | tbd  |
|  view_word_bank_letter_test | test use case *view word bank*  | Create a Path test  | Path test should be to view_word_bank  | tbd  | tbd  | tbd  |
| view_word_bank_metric_test  |  test use case *metric test* | Create unit test  |  Metric score should be correct based on game settings and gameplay   | tbd  | tbd  | tbd  |
| adjust_game_settings_path  | test the use case *Adjust Game Settings* | Create a path test  | Path test should be to adjust_game_settings  | tbd | tbd  | tbd |
| change game settings   | test the use case *Adjust Game Settings*  |  Create a unit test  | should reflect user inputs in game  | tbd  | tbd  | tbd  |
| app_useability   |  test the usablility of the app | Let one other individual use the app  | Individual should say app is easy to use  | tbd  | tbd  | tbd  |
| app_load_testing   |  test the laod test of the app |  reduce the amount of phone resources | app shoud be able to run with low phone resources  | tbd  | tbd  | tbd  |
