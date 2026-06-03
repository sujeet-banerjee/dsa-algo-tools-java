package algo.minmax;

public final class Pair<U, V> {
	private U first;
	private V second;
	
	public Pair(U first, V second) {
		this.first = first;
		this.second = second;
	}
	
	// Setters Getters
	public U first() {
		return this.first;
	}
	
	public V second() {
		return this.second;
	}
	
	public void setFirst(U first) {
		this.first = first;
	}
	
	public void setSecond(V second) {
		this.second = second;
	}
}
