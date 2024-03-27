import org.example.BankAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void initMethod() {
        account = new BankAccount("123", 10000);
    }
    @AfterEach
    public void clearMethod() {
        account = null;
    }

    @Test
    public void testConstructorNegative() {
        BankAccount otherAccount = new BankAccount("123", -5000);
        Assertions.assertEquals(0, otherAccount.getBalance());
    }
    @Test
    public void testGetAccountNumber() {
        Assertions.assertNotNull(account.getAccountNumber());
        Assertions.assertEquals("123", account.getAccountNumber());
    }
    @Test
    public void testGetBalance() {
        Assertions.assertEquals(10000, account.getBalance());
    }
    @Test
    public void testDeposit() {
        Assertions.assertEquals(10000, account.getBalance());
        account.deposit(50000);
        Assertions.assertEquals(60000, account.getBalance());
        account.deposit(0);
        Assertions.assertEquals(60000, account.getBalance());
    }
    @Test
    public void testDepositNegative() {
        Assertions.assertEquals(10000, account.getBalance());
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                                ()-> account.deposit(-10000));
        Assertions.assertEquals("Deposit amount cannot be negative.", exception.getMessage());
    }
    @Test
    public void testWithdraw() {
        Assertions.assertEquals(10000, account.getBalance());
        account.withdraw(5000);
        Assertions.assertEquals(5000, account.getBalance());
        account.withdraw(0);
        Assertions.assertEquals(5000, account.getBalance());
    }
    @Test
    public void testWithdrawNegative() {
        Assertions.assertEquals(10000, account.getBalance());
        account.withdraw(-5000);
        Assertions.assertEquals(10000, account.getBalance());
    }
    @Test
    public void testWithdrawInsufficient() {
        Assertions.assertEquals(10000, account.getBalance());
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                                ()-> account.withdraw(50000));
        Assertions.assertEquals("Insufficient funds for withdraw",
                                exception.getMessage());
    }
    @Test
    public void testTransferFund() {
        BankAccount otherAccount = new BankAccount("456", 50000);
        Assertions.assertEquals(10000, account.getBalance());
        Assertions.assertEquals(50000, otherAccount.getBalance());

        account.transferFunds(otherAccount, 5000);
        Assertions.assertEquals(5000, account.getBalance());
        Assertions.assertEquals(55000, otherAccount.getBalance());
    }
    @Test
    public void testTransferFundInsufficient() {
        BankAccount otherAccount = new BankAccount("456", 50000);
        Assertions.assertEquals(10000, account.getBalance());
        Assertions.assertEquals(50000, otherAccount.getBalance());

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                                ()-> account.transferFunds(otherAccount, 100000));
        Assertions.assertEquals("Insufficient funds for transfer.",
                                exception.getMessage());
    }
}
