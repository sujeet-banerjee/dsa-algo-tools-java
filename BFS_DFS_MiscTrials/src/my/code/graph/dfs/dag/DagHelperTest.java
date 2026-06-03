package my.code.graph.dfs.dag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

class DagHelperTest {

	@Test
	void testBuildDependencyList() {
		BuildSystem bs = BuildSystemTest.getBS1();
		Map<String, String> prod = DagHelper.buildProducers(bs);
		System.out.println(prod);
		
		List<String> depList = DagHelper.buildDependencyList(bs);
		System.out.println("Dep Order: "+ depList);
	}

	@Test
	void testBuildProducers() {
		BuildSystem bs = BuildSystemTest.getBS1();
		Map<String, String> prod = DagHelper.buildProducers(bs);
		System.out.println(prod);
	}
	
	@Test
	void testGetDeps() {
		BuildSystem bs = BuildSystemTest.getBS1();
		Map<String, String> prod = DagHelper.buildProducers(bs);
		Set<String> deps = DagHelper.getDeps("F1", bs, prod);
		System.out.println(deps);
	}


	@Test
	void testBuildDfsDag() {
		//fail("Not yet implemented");
	}

}
