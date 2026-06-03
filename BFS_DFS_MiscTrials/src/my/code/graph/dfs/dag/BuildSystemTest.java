package my.code.graph.dfs.dag;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class BuildSystemTest {
	
	public static <T> List<T> asList(T[] arr) {
		return Arrays.asList(arr).stream().collect(Collectors.toList());
	}

	@Test
	void testAddFileAndRetrievals() {
		BuildSystem bs = getBS1();
		
		System.out.println(bs.toString());
	}

	public static BuildSystem getBS1() {
		BuildSystem bs = new BuildSystem();
		
		bs.addFile("F1", 
				asList(new String[] {"D"}), 
				asList(new String[] {"A", "B"}));
		
		bs.addFile("F2", 
				asList(new String[] {"A"}), 
				asList(new String[] {"B"}));
		
		bs.addFile("F3", 
				asList(new String[] {}), 
				asList(new String[] {"D", "X"}));
		
		bs.addFile("F4", 
				asList(new String[] {"B", "Y"}), 
				asList(new String[] {}));
		
		bs.addFile("F5", 
				asList(new String[] {"X"}), 
				asList(new String[] {"B"}));
		return bs;
	}

}
