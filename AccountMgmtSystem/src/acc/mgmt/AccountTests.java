package acc.mgmt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class AccountTests {

	@Test
	void test() {
		Account a = new BasicAccount("accA");
		Account b = new BasicAccount("accB");
		
		a.deposit(100.01);
		Assert.assertEquals(100.01, a.getBalance());
		
		b.deposit(1000.01);
		Assert.assertEquals(1000.01, b.getBalance());
		b.withdraw(2.22);
		Assert.assertEquals(997.79, b.getBalance());
		
		b.transferTo(a, 100);
		Assert.assertEquals(200.01, a.getBalance());
		Assert.assertEquals(897.79, b.getBalance());
		
		//b.withdraw(2000);
		a.transferTo(b, 2000);
	}

}
