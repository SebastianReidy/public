import java.lang.reflect.Array;

class BankAccount {

  public enum CurrencyType {DOLLAR, EURO, POUND}

  public double[] changeRate = {1, 1.15, 1.31};
  public BankAccount(CurrencyType currencyType, double amount) {
    this.currencyType = currencyType;
    this.amount = amount;
  }

  private CurrencyType currencyType;
  private double amount;

  public boolean add(CurrencyType addedType, double addedAmount) {

    if(addedType == null)
      throw new IllegalArgumentException("type must not be null");
    if(addedAmount<0)
      throw new IllegalArgumentException("added amount must not be negative");

    amount += convertTo(addedType, addedAmount);

    return true;
  }

  private double convertTo(CurrencyType addedType, double addedAmount){

    if(addedType == null || addedAmount < 0)
      throw new IllegalArgumentException("convertTo requires currency and no zero amount");

    double inCurrency = 0;
    double rate = changeRate[addedType.ordinal()];
    if(this.currencyType != CurrencyType.DOLLAR) {
      rate = 1 / rate;
    }
    inCurrency = addedAmount * rate;

    return inCurrency;
  }

  public boolean remove(CurrencyType removedType, double removedAmount) {

    if (removedType == null)
      throw new IllegalArgumentException("removedType must not be null");
    if (removedAmount < 0)
      throw new IllegalArgumentException("removed amount must not be negative");

    amount -= convertTo(removedType, removedAmount);
    return true;
  }

  public CurrencyType getCurrencyType() {
    return currencyType;
  }

  public double getAmount() {
    return amount;
  }
}

public class App {

  public static void main(String[] args) {
    BankAccount account = new BankAccount(BankAccount.CurrencyType.EURO, 9001);
    account.add(BankAccount.CurrencyType.DOLLAR, 100);
    account.remove(BankAccount.CurrencyType.POUND, 10);
    System.out.println("Balance: " + account.getAmount());
  }
}

/**
 * SOLUTION: extract the currency conversion into a class of its own since the rates change over time & they are logically
 * decoupled from the bankaccount.
 */