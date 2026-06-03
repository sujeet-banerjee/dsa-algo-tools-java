/**
 * 
 */
package algo.reporting.dual;

/**
 * WORKED!!!
 */
public final class Entry implements Comparable<Entry>{
	final String fileName;
	final int fSize;
	final String cName;
	
	public Entry(String fName, int fSize, String cName) {
		
		if(fName == null || fName.isEmpty()) {
			throw new IllegalArgumentException("File Name cannot be null or empty");
		}
		
		if(fSize <0) {
			throw new IllegalArgumentException("size cannot be -ve");
		}
		
		this.fileName = fName;
		this.fSize = fSize;
		this.cName = cName == null || cName.isEmpty() ? fName : cName;
	}
	
	@Override
	public boolean equals(Object that) {
		if(this == that) {
			return true;
		}
		
		if(that instanceof Entry) {
			Entry thatE = (Entry)that;
			thatE.cName.equals(this.cName);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.cName.hashCode();
	}

	@Override
	public int compareTo(Entry that) {
		if(that == null) {
			System.out.println("Cannot compare against null");
		}
		
		// For reverse order
		int bySize = - this.fSize + that.fSize;
		
		return bySize != 0 ? bySize : that.cName.compareTo(this.cName);
	}
	
	public Entry copyUpdateSize(int newSize, String newFName) {
		if(newFName == null || newFName.isEmpty()) {
			throw new IllegalArgumentException("File Name cannot be null or empty");
		}
		
		if(newSize <0) {
			throw new IllegalArgumentException("size cannot be -ve");
		}
		
		return new Entry(this.fileName+ "|"+ newFName, newSize, this.cName);
	}
	
	@Override
	public String toString() {
		return String.format("{%s, %d}", this.cName, this.fSize);
	}
}
