package AdvanceTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(BankAccountParameterResolver.class)
public class bankAccountParameterizedTest {
    @ParameterizedTest //Parameterized test is to execute the same test over and over again using different values.
    @ValueSource(ints = {100, 400, 800, 1000}) //provide the arguments for each invocation of test
    @DisplayName("Deposit successfully")
    public void testDepositValueSource(int amount, bankAccount BankAccount){
        BankAccount.deposit(amount); //deposit will be the amount
        assertEquals(amount,BankAccount.getBalance()); //check the amount witht the current balance
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, names = {"Tuesday","Thursday"}) //In order to run a test with different values from an enumeration, we can use the @EnumSource annotation.
    public void testDayOfWeek(DayOfWeek day){
        assertTrue(day.toString().startsWith("T")); // the day should start with T

    }

    @ParameterizedTest
    @CsvSource({"100, Mary", "200, Rishab","300, Shila"}) //The @CsvSource accepts an array of comma-separated values, and each array entry corresponds to a line in a CSV file.
    public void depositAndNameTest(double amount, String name, bankAccount BankAccount){
        BankAccount.deposit(amount);
        BankAccount.setHolderName(name);
        assertEquals(amount, BankAccount.getBalance()); // check the provided amount with balance
        assertEquals(name, BankAccount.getHolderName()); //check the name with account Name
    }

    @ParameterizedTest
    //@CsvSource({"100, Mary", "200, Rishab","300, Shila"}) //The @CsvSource accepts an array of comma-separated values, and each array entry corresponds to a line in a CSV file.
    @CsvFileSource(resources = "res.csv", delimiter = ';') //this will locate the file inside the folder
    public void depositAndNameTest2(double amount, String name, bankAccount BankAccount){
        BankAccount.deposit(amount);
        BankAccount.setHolderName(name);
        assertEquals(amount, BankAccount.getBalance()); // check the provided amount with balance
        assertEquals(name, BankAccount.getHolderName()); //check the name with account Name
    }

}
