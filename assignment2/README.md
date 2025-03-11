🎮 Interactive Object Simulation Game

🚀 Overview

Welcome to the Interactive Object Simulation Game! This program simulates objects moving within a 2D environment, where users control their objects while AI-driven entities navigate the space. Using binary search trees (BSTs) for efficient object management, the game ensures smooth movement and precise collision detection.

🎯 Key Features

🔹 Four Unique Object Types:

🏗 Fixed Objects – Act as barriers and cannot move.
🎮 User Objects – Controlled by the player using the keyboard.
🤖 AI Objects – Move automatically and remove player-controlled objects on contact.
🎯 Target Objects – Must be reached to win; disappear when touched by the player.
🔹 Collision Detection System

✅ Objects are stored in a binary search tree (BST) for optimized spatial management.
🚫 Prevents overlapping of objects and ensures movement restrictions.
🔍 Efficient searching for object interactions using tree traversal.
🔹 Real-Time Rendering & Movement

🖼 Objects are displayed using image files loaded from an input list.
⌨️ Players control objects dynamically using keyboard inputs.
📏 Objects stay within screen boundaries with movement validation.


🏗 How It Works

1️⃣ The game reads an input file with object details (position, type, and image file).
2️⃣ Objects are rendered on a graphical interface inside a rectangular window.
3️⃣ Players move their objects using the keyboard, while AI objects move automatically.
4️⃣ The game checks for collisions and interactions, ensuring objects behave realistically.

📂 Technologies Used

Java ☕ for core logic and object management.
Binary Search Tree (BST) 🌳 for efficient collision detection.
Graphical rendering 🎨 for interactive gameplay.
