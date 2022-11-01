import java.util.HashMap;

class BankAccount {

  public enum CurrencyType {DOLLAR, EURO, POUND}  // SOLUTION: can also store double for conversion in the enum, enum should be on top level and not in bank account class

  public BankAccount(CurrencyType currencyType, double amount) { // SOLUTION: add sanity checks
    this.currencyType = currencyType;
    this.amount = amount;
    this.exchangeRates = populateExchangeRates();
  }

  private CurrencyType currencyType;
  private HashMap<KeyWrapper, Double> exchangeRates;
  private double amount;

  private HashMap<KeyWrapper, Double> populateExchangeRates(){
    HashMap<KeyWrapper, Double> ExchangeRates = new HashMap<KeyWrapper, Double>();

    ExchangeRates.put(new KeyWrapper(CurrencyType.DOLLAR, CurrencyType.EURO), 1.15);  // SOLUTION: only need three values, the others are related
    ExchangeRates.put(new KeyWrapper(CurrencyType.DOLLAR, CurrencyType.POUND), 1.31); // SOLUTION: separate class for currency conversion
    ExchangeRates.put(new KeyWrapper(CurrencyType.EURO, CurrencyType.DOLLAR), 0.87);
    ExchangeRates.put(new KeyWrapper(CurrencyType.EURO, CurrencyType.POUND), 1.14);
    ExchangeRates.put(new KeyWrapper(CurrencyType.POUND, CurrencyType.DOLLAR), 0.76);
    ExchangeRates.put(new KeyWrapper(CurrencyType.POUND, CurrencyType.EURO), 0.88);

    return ExchangeRates;
  }

  public boolean add(CurrencyType addedType, double addedAmount) {
    double inCurrency = convertToCurrency(addedType, addedAmount);
    amount += inCurrency;
    return true;
  }

  public boolean remove(CurrencyType removedType, double removedAmount) {
    double inCurrency = convertToCurrency(removedType, removedAmount);
    if (inCurrency > amount) {
      return false;
    }
    amount -= inCurrency;
    return true;
  }

  private double convertToCurrency(CurrencyType type, double amount){
    double inCurrency = 0;
    if (currencyType == type) {
      inCurrency = amount;
    } else {
      inCurrency = exchangeRates.get(new KeyWrapper(currencyType, type)) * amount;
    }
    return inCurrency;
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

class KeyWrapper {
  private BankAccount.CurrencyType primary;
  private BankAccount.CurrencyType secondary;

  KeyWrapper(BankAccount.CurrencyType prim, BankAccount.CurrencyType sec) {
    this.primary = prim;
    this.secondary = sec;
  }
}