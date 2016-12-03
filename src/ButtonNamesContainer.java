import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.*;

public class ButtonNamesContainer {
	final int objLimit = 16;
	private HashSet<String> mySet = new HashSet<>();
	
	public ButtonNamesContainer() {
		//Nazwy przedmiot�w s� przechowywane jako HashSet, aby unikn�� duplikat�w
		mySet.add("Jab�ko");
		mySet.add("Brzoskwinia");
		mySet.add("Arbuz");
		mySet.add("Ananas");
		mySet.add("Gruszka");
		mySet.add("�liwka");
		mySet.add("Winogrono");
		mySet.add("Mango");
		mySet.add("Banan");
		mySet.add("Melon");
		mySet.add("Nektarynka");
		mySet.add("Papaja");
		mySet.add("Truskawka");
		mySet.add("Pomara�cza");
		mySet.add("Kiwi");
		mySet.add("Liczi");
	}
	
	public ArrayList getButtonsObjects(int numsToGet) {
		if (numsToGet > mySet.size()) throw new IllegalArgumentException("Chosen number of objects to be obtained is too high");
		ArrayList tempArray = new ArrayList<>();
		for (int objIter=0; objIter<numsToGet; objIter++) {
			tempArray.add(mySet.toArray()[objIter]); 
		}
		return tempArray;

	}
	 
	
}
