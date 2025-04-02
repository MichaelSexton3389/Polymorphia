# Object-Oriented Multiplayer Adventure Game

### Developed by Michael Sexton & Tom Riley

This project is the result of a semester-long collaboration where we built a fully functional, web-based **turn-based adventure game**. The game is designed with a strong emphasis on **object-oriented programming (OOP) and design patterns**, allowing for highly flexible and dynamic gameplay.

---

## 🛠 Project Overview

Our game is a **strategic, turn-based adventure simulator** that can accommodate:  
✔ **Any number of players**  
✔ **Dynamic enemy AI** that reacts based on character types  
✔ **A procedurally generated world** with rooms, items, and challenges  

Each game session plays out based on predefined **character behaviors**, but players also have the ability to interact using an **API-controlled character**, giving them the freedom to navigate and strategize their actions.

---

## 🎮 Key Features

- 🏰 **Fully Dynamic World** – The game allows for infinite customization, featuring multiple:  
  - Players  
  - Enemies  
  - Rooms  
  - Food items  
  - Armor and equipment  
  - Character classes with unique behaviors  

- 🧠 **AI-Based Gameplay** – Enemies and characters make decisions based on predefined behavior patterns, creating a dynamic and strategic experience.  

- 🔄 **Turn-Based Simulation** – The game plays out one turn at a time, allowing both AI-controlled and API-controlled players to interact.  

- 📈 **Leveling & Equipment System** – Players can **level up** and **equip armor** to strengthen their characters as they progress through the game.  

- 🔌 **API Player Integration** – Players can control their character programmatically via an API, choosing their own path while still adhering to the game’s logic.

- 🔊 **Audio Feedback & Logging System** – A **real-time logger** tracks every action taken by players and enemies, providing **both system logs and audible feedback**, enhancing the immersive experience.  


---

## 🛠 Technical Details

### 🔹 Object-Oriented Design  
We applied key OOP principles such as:  
✔ **Encapsulation** – Each game entity (player, enemy, item) is self-contained.  
✔ **Polymorphism** – Different characters and enemies behave uniquely.  
✔ **Abstraction** – Game logic is modular and reusable.  
✔ **Design Patterns** – Used to structure character behaviors and AI decisions.  

### 🔹 Scalability  
The game is designed to support **unlimited characters, enemies, and items**, making it infinitely replayable with different setups.  

---

## 🚀 Running the Game

To play the game locally:

1. **Clone the Repository**
   ```sh
   git clone https://github.com/yourusername/game-project.git
   cd game-project

2. **Build and Run**
   ```sh
    ./gradlew clean build
    java -jar build/libs/game.jar

3. **Access the Web Interface**
   Open a browser and go to http://localhost:PORT to start playing!


Due to the way that this project was structured over the course of the term, this is just the finished product. If you have any questions regarding versioning, or the different edits made over the course of the project feel free to reach out! 
