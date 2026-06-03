package com.suz.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.suz.ThreadPool;
import com.suz.ThreadPoolException;
import com.suz.DefaultThreadPool;

@Deprecated
// See Main class
class DefaultThreadPoolTest {

	@Test
	void testStart() throws ThreadPoolException {
		ThreadPool thp = new DefaultThreadPool("UnitTest", 5, 20);
		thp.start();
	}

}
