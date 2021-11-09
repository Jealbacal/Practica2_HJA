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
        NOMADEHAND(0),DRAWFLUSH(1),DRAWOPENENDED(2),DRAWGUSTSHOT(69), WEAKPAIR(2),MIDDLEPAIR(3),PPBTP(4),TOPPAIR(5),OVERPAIR(6), TWOPAIR(7), THREEOFAKIND(8), STRAIGHT(9), FLUSH(10), FULLHOUSE(11), FOUROFAKIND(12), STRAIGHTFLUSH(13);
	
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
