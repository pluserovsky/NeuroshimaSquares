package models;

import java.util.ArrayList;

public class Soldier extends Card {
	public Soldier(){
		super();
	}

	public Soldier(int posX, int posY, char faces, int health, int damage, int initiative){
		super("Soldier", posX, posY, faces, health, damage, initiative);
	}

	@Override
	public ArrayList<Pair<Integer, Integer>> getAttacks() {
		// Can attack only field in front of a card.
		ArrayList<Pair<Integer, Integer>> attacks = new ArrayList<>();

		if (faces == 'N' && posY > 0){
			// attack above
			attacks.add(new Pair<Integer, Integer>(posX, posY-1));
		} else if (faces == 'E' && posX < 3){
			// attack to the right
			attacks.add(new Pair<Integer, Integer>(posX+1, posY));
		} else if (faces == 'S' && posY < 3){
			// attack below
			attacks.add(new Pair<Integer, Integer>(posX, posY+1));
		} else if (faces == 'W' && posX > 0){
			// attack to the left
			attacks.add(new Pair<Integer, Integer>(posX-1, posY));
		}
		return attacks;
	}

}
