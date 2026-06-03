package com.suz.graph.trials.nestedInt;

import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBfsIterator {

	// [1, [2,3],  4,  [[[5]]],  [6,7],  8, [[[9], 10]] ]
	// Itr = 1,4,8, 2,3, 6,7, 10, 5, 9
	NestedInt root = null;
	
	@BeforeEach
	void setUp() throws Exception {
		this.root = new NestedInt(true, 0, new NestedInt[] {
				new NestedInt(false, 1, null),
				new NestedInt(true, 0, new NestedInt[] {
					new NestedInt(false, 2, null),
					new NestedInt(false, 3, null),
				}),
				new NestedInt(false, 4, null),
				new NestedInt(true, 0, new NestedInt[] {
					new NestedInt(true, 0, new NestedInt[] {
						new NestedInt(true, 0, new NestedInt[] {
							new NestedInt(true, 0, new NestedInt[] {
								new NestedInt(false, 5, null),
							})
						})
					})
				}),
				new NestedInt(true, 0, new NestedInt[] {
					new NestedInt(false, 6, null),
					new NestedInt(false, 7, null),
				}),
				new NestedInt(false, 8, null),
				new NestedInt(true, 0, new NestedInt[] {
						new NestedInt(true, 0, new NestedInt[] {
							new NestedInt(true, 0, new NestedInt[] {
								new NestedInt(true, 0, new NestedInt[] {
									new NestedInt(false, 9, null),
								}),
								new NestedInt(false, 10, null),
							})
						})
					}),
		});
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testNext() {
		Iterator<NestedInt> itr = new NestedIntBfsIterator(root);
		
		for(; itr.hasNext();) {
			System.out.println("[BFS] Next=" + itr.next().value());
		}
	}

}
