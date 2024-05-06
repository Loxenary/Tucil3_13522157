<h1 align="center">Word Ladder</h1>

## Project Overview
This Project is Aimed to fulfill the requirement of Tucil 3 of STIIMA which involves in implementing UCS, Greedy Best First Search and A* Algoritm on clearing a word ladder problem. 

## Implementation
### UCS
Algoritma UCS yang kami implementasikan akan melakukan iterasi untuk queue yang dibuat hingga ditemukan solusi yang sesuai. Pada kasus word ladder, UCS yang dibuat sebenarnya tidak ada bedanya dengan BFS hal ini karena perbedaan cost yang dihasilkan akan selalu 1 sehingga node apapun yang dimasukan pasti akan diprocess secara bergilir
### Greedy BFS 
Algoritma Greedy BFS yang kami implementasikan menggunakan priority Queue sebagai struktur data dasarnya dan menggunakan sebuah string matching dalam pengaplikasian cost-nya. Pada Algoritma ini, type yang di implementasikan adalah Greedy BFS yang tidak dapat melakukan backtrack hal ini menyebabkan node yang di lalui dapat tidak optimal dan tidak selesai.
### Astar
Algoritma Astar yang kami implementasikan mirip seperti Greedy BFS. namun, Astar dalam pengimplementasian bobotnya menggunakan penggabungan antara cost dari Greedy BFS dan cost dari UCS. setelah itu Astar akan terus di iterasi hingga didapatkan target atau seluruh node tidak dapat menghasilkan end path
## Setup Project

### Requirements
1. Install the Java Development Kit (JDK) of Version 22 or higher
    ```
    https://www.oracle.com/java/technologies/downloads/
    ```

2. Clone the Repo :
    ```
    git clone https://github.com/Loxenary/Tucil3_13522157.git
    ```

### Running

1. Compile the java in the Root Directory
    ```
    javac src/*.java
    ```
2. Launch The Program <br>
    ```
    java src/Main
    ```
The project is properly setup

## Authors

[Muhammad Davis Adhipramana (13522157)](https://github.com/Loxenary).