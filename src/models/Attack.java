package models;

import java.util.ArrayList;

public class Attack extends Card{
	
	public Attack() {
		super();
	}
	
	public Attack(int posX, int posY, char faces, int health, int damage, int initiative){
		super("Attack", posX, posY, faces, health, damage, initiative);
	}

	@Override
	public ArrayList<Pair<Integer, Integer>> getAttacks() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
