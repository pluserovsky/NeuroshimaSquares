package models;

import java.util.ArrayList;

public class Berserker extends Card {

	public Berserker(){
		super();
	}

	public Berserker(int posX, int posY, char faces, int health, int damage, int initiative){
		super("Berserker", posX, posY, faces, health, damage, initiative);
	}

	@Override
	public ArrayList<Pair<Integer, Integer>> getAttacks() {
		// attacks everybody around
		ArrayList<Pair<Integer, Integer>> attacks = new ArrayList<>();

		if (posY > 0) attacks.add(new Pair<Integer, Integer>(posX, posY-1));
		if (posX < 3 && posY > 0) attacks.add(new Pair<Integer, Integer>(posX+1, posY-1));
		if (posX < 3) attacks.add(new Pair<Integer, Integer>(posX+1, posY));
		if (posX < 3 && posY < 3) attacks.add(new Pair<Integer, Integer>(posX+1, posY+1));
		if (posY < 3) attacks.add(new Pair<Integer, Integer>(posX, posY+1));
		if (posX > 0 && posY < 3)attacks.add(new Pair<Integer, Integer>(posX-1, posY+1));
		if (posX > 0) attacks.add(new Pair<Integer, Integer>(posX-1, posY));
		if (posX > 0 && posY > 0) attacks.add(new Pair<Integer, Integer>(posX-1, posY-1));

		return attacks;
	}

}
