/**
 * 
 */
package algo.reporting;

/**
 * Entry (TBD)
 */
public final class Entry {
	final String fileName;
	final int fSize;
	String cName;
	
	public Entry(String fName, int fSize, String cName) {
		
		if(fName == null || fName.isEmpty()) {
			throw new IllegalArgumentException("File Name cannot be null or empty");
		}
		
		if(fSize <0) {
			throw new IllegalArgumentException("size cannot be -ve");
		}
		
		this.cName = cName;
		if(cName == null || cName.isEmpty()) {
			this.cName = fName;
		}
		this.fileName = fName;
		this.fSize = fSize;
	}
}
