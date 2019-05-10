# Scramble Generator - Letter Pairs
This program uses scramble filtering to generate scrambles containing specific letter pairs. It checks each generated scramble's memo to see if the desired letters pairs appear x times in the memo. If it does, it displays the scramble. If not, it will gen another scramble.

## To run
1. Clone and extract the repo
2. Open the "lpList.txt" file and enter the letter pairs you want to drill. Enter each letter pair on a separate line. Lines that begin with "//" will not be read.
3. Open the "letterScheme.txt" file and enter your letter scheme.
4. Double click the "run_scramble_generator" batch file (NOT the jar file) to run the program 

## Notes
* Frequency is the number of times the desired letter pairs will appear in memo
* I recommend keeping freq below 3, otherwise it will take a long time (3 corner comms without a cycle break are quite rare, 3 such corner comms  that contain specific letter pairs even more so)
* Cycle breaks are handled by only considering the letter pairs before the break.
* For the sake of speed and simplicity, the scrambles are not random state, they are random move. I don't think this is a problem since you're just drilling letter pairs.

