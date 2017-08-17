# SentencePracticer

This "SentencePracticer" is a tool for language learning. This is a Java program that picks random words from the user's list and prompts the user to use them in a sentence - the user will be prompted to use at least 1 random noun, verb, adverb, adjective, and "other" in a sentence. This depends on a user-provided text file that functions essentially as a "Dictionary" for the program. I included an example file here: "Genki_1_ch1-10_vocab.txt".

You can create your own "Dictionary" file for this program. It must be a text file (.txt) encoded in UTF-8, and each line must contain an English word and its translation, separated with ";". The words must be categorized as "*NOUNS", "*VERBS", "*ADJECTIVES", "*ADVERBS", and "*OTHERS". The category headers take up one line, followed by a list of word;translation pairs. After the last word;translation pair, end the category's list with a line containing "#". Here is an example:

*NOUNS  
student;学生   
language;語  
&#
*VERBS   
to go;行く   
&#
*ADVERBS   
usually;大抵   
&#
*ADJECTIVES   
delicious;おいしい   
expensive/high;高い   
&#
*OTHERS   
um...;あの。。。   
yes;   
P.M.;午後   
&#
