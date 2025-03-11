Game Board AI

Overview

The Game Board AI is a Java-based strategy game where a human player competes against an AI opponent on a square grid. The objective is to align k adjacent tiles in a row, column, or diagonal while navigating around unavailable positions on the board. The game ends when a player wins or when the board is full, resulting in a draw.

This project demonstrates key concepts in data structures, algorithms, and artificial intelligence, including:

✔ Hash Tables with Separate Chaining for efficient game state storage.
✔ Game State Evaluation to determine the best moves for the AI.
✔ Dynamic Board Representation using a 2D character grid.

Features

🔹 Turn-Based Gameplay – The game alternates between the human player and the AI.
🔹 Smart AI Opponent – The AI evaluates board states to determine the best move.
🔹 Customizable Board Size – Supports different grid sizes and win conditions.
🔹 Efficient Game State Storage – Uses a custom hash table to avoid redundant calculations.
🔹 Win & Draw Detection – Automatically checks for game-ending conditions.

How It Works

The board size (R × R) and winning condition (k tiles in a row/column/diagonal) are customizable.
The game state is stored as a string representation, using:
'h' → Human player's tile
'c' → Computer (AI) tile
'e' → Empty tile
'u' → Unavailable tile
The AI evaluates potential moves using a score-based system:
3 → AI wins
0 → Human wins
2 → Draw
1 → Game still in progress
The AI avoids recalculating previous board states by storing them in a custom dictionary (hash table).
Implementation Details
The project consists of three main Java classes:

🟢 1. Record.java

Stores a game state and its corresponding score and level in the AI’s decision tree.

🔵 2. Dictionary.java

Implements a custom hash table with separate chaining to store previously encountered game states.

Collision Handling: Uses linked lists for separate chaining.
Efficient Lookup: Prevents redundant calculations.

🟡 3. Evaluate.java

Handles game logic, board representation, and AI decision-making.

Detects winning moves for both players.
Stores and retrieves board states from the hash table.
Evaluates the best move for the AI using precomputed game states.
