/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker.practica2.hja.ap1;

/**
 *
 * @author Pablo
 */
public enum Ranking {
     HIGHCARD(0), PAIR(1), TWOPAIR(2), THREEOFAKIND(3), STRAIGHT(4), FLUSH(5), FULLHOUSE(6), FOUROFAKIND(7), STRAIGHTFLUSH(8);
	
	private int val;
	
	Ranking(int val) {
		this.val = val;
	}
	
	public int getNumVal() {
        return val;
    }
	
	public boolean biggerThan(Ranking r) {
		return this.val > r.getNumVal();
	}
}
