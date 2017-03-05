#JString-generator

## Language : Java with [NetBeans IDE](https://netbeans.org/)

### This is a port of my project in C++ https://github.com/AdrienJarretier/string-generator

You need Java 8 with Javafx to make this work.

Also for random numbers generation I'm using [ssj](https://github.com/umontreal-simul/ssj),
the dependancy should be handled automatically by Maven in NetBeans.


When starting it will first read a list of words and construct a 3-order Markov Chain
it is possible to change very easily those 2 parameters in the main,
by default the program reads resources/words-lists/english.txt so you can use this filename if you want to use another list.

Once the chain is generated you can click on the button on the right to generate words over and over again.

TODO :

1. ~~Analyse an English list of words to generate a Markov chain~~
2. ~~Generate a unique random word using the Markov chain~~
3. Choose the base language when starting the program
4. Differenciate word types (ie : verbs, nouns ...)
5. Generate a whole text


- English word list got here : https://github.com/dwyl/english-words
- French word list : http://www.pallier.org/ressources/dicofr/dicofr.html
- Tolkien list compiled from : http://www.behindthename.com/namesakes/list/tolkien/alpha

