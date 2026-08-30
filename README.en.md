<!-- |-------------------------------------------------------------------------------------------| -->
<!-- |                                         LANGUAGE                                          | -->
<!-- |-------------------------------------------------------------------------------------------| -->
<div align="right">
  <a href="README.md">
    <img src="https://img.shields.io/badge/🇫🇷 Français-1e3a5f?style=for-the-badge" alt="Français"/>
  </a>
  <a href="README.en.md">
    <img src="https://img.shields.io/badge/🇬🇧 English-555555?style=for-the-badge" alt="English"/>
  </a>
</div>

<!-- |-------------------------------------------------------------------------------------------| -->
<!-- |                                          HEADER                                           | -->
<!-- |-------------------------------------------------------------------------------------------| -->
<h1 align="left">♣️ Inscryption Card Game</h1>

<p align="justify">
This project is a Java card game inspired by the video game <i>Inscryption</i>. You face an opponent and must defeat them over several rounds. This project was carried out as part of a SAé (school project), in pairs, over a period of 5 weeks. It consists of modeling the card game rules of Inscryption: card placement, combat, sacrifices, and the power system.
</p>

<!-- |-------------------------------------------------------------------------------------------| -->
<!-- |                                       ABOUT                                               | -->
<!-- |-------------------------------------------------------------------------------------------| -->
## About the project

<p align="justify">
The game implements a board with obstacle card management, as well as a score system based on the point gap between the two players. It features various animal cards, each with an attack value, health points, a sacrifice cost in the form of blood drops or bones, and special powers.
</p>

<p align="justify">
The game is played over three rounds, with new cards added to the deck between each round. Several card powers are available, including many lives, growth, smelly, runner, deadly contact, and sharp spikes, along with a sacrifice stone mechanic used to manage certain sacrifices. Finally, the game features a command-line interface allowing the player to interact with the game, as well as input error handling to prevent invalid entries.
</p>

<!-- |-------------------------------------------------------------------------------------------| -->
<!-- |                                        PREVIEW                                            | -->
<!-- |-------------------------------------------------------------------------------------------| -->
## 📸 Preview

**Image of the board at the start of a game:**
<div align="center">
  <img src="images/image1.png" alt="Game board" width="600"/>
</div>
<br>

**Image of the possible actions with the hand:**
<div align="center">
  <img src="images/image2.png" alt="Hand actions" width="600"/>
</div>
<br>

<!-- |-------------------------------------------------------------------------------------------| -->
<!-- |                                        USAGE                                              | -->
<!-- |-------------------------------------------------------------------------------------------| -->
## 🚀 Running the project

<p align="justify">
This is an IntelliJ IDEA project. To run it, open the project folder in IntelliJ, then open the <code>Main.java</code> file located in the <code>src</code> folder. Finally, launch the execution by clicking the green arrow. The libraries required for the tests are included in the <code>deps/</code> folder.
</p>

<!-- |-------------------------------------------------------------------------------------------| -->
<!-- |                                    PROJECT STRUCTURE                                      | -->
<!-- |-------------------------------------------------------------------------------------------| -->
## 📁 Project structure

```text
Inscryption-card-game/
├── deps/                    # Dependencies
├── docs/                    # Project subject and instructions
├── out/                     # Compiled files
├── src/                     # Game source code
│   ├── Main.java
│   └── ...
├── tests/                   # JUnit unit tests
├── uml/                     # Class diagrams, updated week by week
├── images/                  # Images used in the README
├── .gitignore
└── README.md
```

<!-- |-------------------------------------------------------------------------------------------| -->
<!-- |                                          TESTS                                            | -->
<!-- |-------------------------------------------------------------------------------------------| -->
## 🧪 Tests

<p align="justify">
The project is covered by a suite of JUnit unit tests, covering in particular a card's attack and the attack of all cards at the end of a turn, card powers, the sacrifice stone mechanic, score updates, card placement on the board and drawing from the deck, as well as setting up a game and the win or loss conditions.
</p>

<!-- |-------------------------------------------------------------------------------------------| -->
<!-- |                                      CONTRIBUTORS                                         | -->
<!-- |-------------------------------------------------------------------------------------------| -->
## 👥 Contributors

Work carried out in pairs as part of a project at IUT Robert Schuman.

<div align="center">

[![rmax3iu](https://img.shields.io/badge/rmax3iu-1e3a5f?style=for-the-badge&logo=github&logoColor=white)](https://github.com/rmax3iu)
[![lucastreiber](https://img.shields.io/badge/lucastreiber-1e3a5f?style=for-the-badge&logo=github&logoColor=white)](https://github.com/lucastreiber)

</div>
