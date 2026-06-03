package acc.mgmt;

public interface Account {
	
	void deposit(double amount);
	
	// TODO throw exception (e.g. lowBalance)
	void withdraw(double amount) ;
	
	void transferTo(Account target, double amount);
	
	double getBalance();
}
