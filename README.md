#JString-generator

## Language : Java with [NetBeans IDE](https://netbeans.org/)

### This is a port of my project in C++ https://github.com/AdrienJarretier/string-generator

Using JavaFx, I'll add a GUI, you need Java 8 with Javafx to make this work.

Also for random numbers generation I'm using [ssj](https://github.com/umontreal-simul/ssj),
the dependancy should be handled automatically by Maven in NetBeans.



When starting it will first read a list of words and construct a 3-order Markov Chain
it is possible to change very easily those 2 parameters in the main.

Once the chain is generated you're asked the boundaries for the length of the words you want to create.

The process is actually quite fast, the production of a 3-order chain whith the list of 235 886 words, and generation of 16 "random" words with default length parameters takes 1.1 sec on my computer. That's why I striped number 6 of the TODO list.

TODO :

1. ~~Analyse an English list of words to generate a Markov chain~~
2. ~~Generate a unique random word using the Markov chain~~
3. Choose the base language when starting the program
4. Differenciate word types (ie : verbs, nouns ...)
5. Generate a whole text
6. ~~Perform the analysis only once and store the markov chain.~~ => not necessary


- English word list got here : https://github.com/dwyl/english-words
- French word list : http://www.pallier.org/ressources/dicofr/dicofr.html
- Tolkien list compiled from : http://www.behindthename.com/namesakes/list/tolkien/alpha

