package my.code.trials.queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueueTrialsTest {

//	@Test
//	void testIsLevelNode() {
//		fail("Not yet implemented");
//	}

	@Test
	void testCreateTree() {
		QueueTrials qt = new QueueTrials();
		Node tree = null;
//		tree = qt.createTree("");
//		assertNull(tree);
//		
//		tree = qt.createTree("null");
//		assertNull(tree);
		
		tree = qt.createTree("10,null,20,28,16,null,19");
		
	}

}
