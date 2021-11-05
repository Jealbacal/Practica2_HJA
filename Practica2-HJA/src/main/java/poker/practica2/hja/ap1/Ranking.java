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
        NOMADEHAND(0), WEAKPAIR(1),MIDDLEPAIR(2),PPBTP(3),TOPPAIR(4),OVERPAIR(5), TWOPAIR(6), THREEOFAKIND(7), STRAIGHT(8), FLUSH(7), FULLHOUSE(8), FOUROFAKIND(9), STRAIGHTFLUSH(10);
	
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
