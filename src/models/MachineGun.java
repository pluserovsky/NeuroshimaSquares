package models;

import java.util.ArrayList;

public class MachineGun extends Card {

	public MachineGun(){
		super();
	}

	public MachineGun(int posX, int posY, char faces, int health, int damage, int initiative){
		super("MachineGun", posX, posY, faces, health, damage, initiative);
	}

	@Override
	public ArrayList<Pair<Integer, Integer>> getAttacks() {
		// attacks everybody in a straight line
		ArrayList<Pair<Integer, Integer>> attacks = new ArrayList<>();

		if (faces == 'N'){
			// attack above
			for (int i=posY-1; i >= 0; i--){
				attacks.add(new Pair<Integer, Integer>(posX, i));
			}
		} else if (faces == 'E'){
			// attack to the right
			for (int i=posX+1; i <= 3; i++){
				attacks.add(new Pair<Integer, Integer>(i, posY));
			}
		} else if (faces == 'S'){
			// attack below
			for (int i=posY+1; i <= 3; i++){
				attacks.add(new Pair<Integer, Integer>(posX, i));
			}
		} else if (faces == 'W'){
			// attack to the left
			for (int i=posX-1; i >= 0; i--){
				attacks.add(new Pair<Integer, Integer>(i, posY));
			}
		}
		return attacks;
	}

}
