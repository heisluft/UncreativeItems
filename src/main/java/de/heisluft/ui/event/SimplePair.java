package de.heisluft.ui.event;

/**
 * A simple, immutable pair of two unconnected values. Nicer than Object[]
 * 
 * @param <P1>
 *            the first value
 * @param <P2>
 *            the second value
 */
public class SimplePair<P1, P2> {
	
	private final P1 p1;
	private final P2 p2;
	
	/**
	 * Constructs a new pair
	 * 
	 * @param p1
	 *            the first value
	 * @param p2
	 *            the second value
	 */
	public SimplePair(P1 p1, P2 p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Gets the first value
	 * 
	 * @return the first value
	 */
	public P1 getP1() {
		return p1;
	}
	
	/**
	 * Gets the second value
	 * 
	 * @return the second value
	 */
	public P2 getP2() {
		return p2;
	}
}
