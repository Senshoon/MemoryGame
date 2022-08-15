# MemoryGame
This is a simple game known as Memory, in which you have to find all pairs (e.g. signs, symbols, words).


## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Features](#features)
* [Status](#status)

## General info
I created this project as a requirement for recruiting.
This is my first such large program.

Not long ago, I started learning the basics of the JAVA programming language and it was a step forward for me, because I had to spend all my time creating a project, first acquiring skills that would allow me to finish it on time.

I am very happy that it is working properly and I can't wait to make any improvements to it


**GAME**

In the current state, we operate the game from the console, after starting the program, select the difficulty level (currently: Easy, Hard).

Then, depending on the level, we generate a board with hidden (X) words filled with it, which we discover by typing into the consoles the coordinates of hidden words that we want to discover and compare.

If we do not match the first word to the second, the first card is turned down (the second was not revealed, but we know it did not match) and the number of chances (which is displayed during the game) is reduced by 1.

Depending on the level of difficulty, the size of the table (number of words) and the chances are greater.

The game is over when we hit all the pairs or when we run out of chances.

Then the program asks us if we want to start over or end the program.

## Technologies
* Java version 18.0.1

## Features
* Creating methods for cleaning the console

* Creating a method that allows the previous game state to be displayed for a few seconds in case of a missed word

* Adding new difficulty levels
## Status
Project is: _in early access_
