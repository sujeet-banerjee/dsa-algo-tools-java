package acc.mgmt;

/**
 * Not Thread safe.
 */
public class BasicAccount implements Account {
	
	// TODO check if 0-balance is allowed.
	// TODO Transfer cannot be made to the same account (i.e. check: src != dest)
	
	private final String accId;
	
	private double balance;
	
	public BasicAccount(String accId) {
		this.accId = accId;
	}

	@Override
	public void deposit(double amount) {
		if(amount <0 ) {
			throw new IllegalArgumentException("-ve amount cannot be deposited");
		}
		this.balance += amount;
	}

	@Override
	public void withdraw(double amount) {
		if(amount <0 ) {
			throw new IllegalArgumentException("-ve amount cannot be withdrawan");
		}
		if(this.balance <= amount) {
			throw new IllegalStateException("Low Balance, cannot withdraw.");
		}
		
		this.balance -= amount;
	}

	@Override
	public void transferTo(Account target, double amount) {
		// TODO make these two atomic
		this.withdraw(amount);
		target.deposit(amount);
	}
	
	//@Override
	public double getBalance() {
		return this.balance;
	}

}
