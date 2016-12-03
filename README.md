# AttentionGame_Java
Prosta gra na ćwiczenie koncentracji i pamięci napisana w Java.

Celem projektu było stworzenie prostej aplikacji wykorzystującej to czego się nauczyłem o Java Swing.
Gra składa się z trzech klas:
* ButtonNamesContainer - Zawiera zbiór nazw (owoców) które będą przypisywane przyciskom w trakcie gry.
  Umieściłem je osobno aby można było kontrolować zbiór owoców z którego korzysta gra bez ingerencji w kod samego gui.
  Dodatkowo, nazwy są na początku przechowywane w zbiorze, dzięki czemu nie ma możliwości duplikatu danej nazwy, 
  i w klasie ButtonNamesContainer odbywa się konwersja zbioru na listę dla wybranej liczby obiektów.
* MyButton - Klasa MyButton dziedziczy po klasie JButton. Dzięki temu gra korzysta z przycisków, 
  które zawierają dodatkowy atrybut o wartości true lub false.
* FocusGame - Kod zawierający GUI i  wszystkie czynności które po kolei mają mieć miejsce w aplikacji.

W repozytorium umieściłem jeszcze plik FocusGame.jar - zawiera on skompilowaną, działąjącą grę.
