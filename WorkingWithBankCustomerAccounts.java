import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class WorkingWithBankCustomerAccounts {
    public static void main(String[] args) {
        // Предварительные действия - создание объектов и вспомогательных коллекций.
        Client ivan = new Client("Иван Иванов", 20);
        Client petr = new Client("Пётр Петров", 30);
        Client sergey = new Client("Сергей Сергеев", 40);

        Account account1 = new Account("0001");
        Account account2 = new Account("0002");
        Account account3 = new Account("0003");

        ArrayList<Account> accountsIvan = new ArrayList<>();
        accountsIvan.add(account1);
        accountsIvan.add(account2);

        ArrayList<Account> accountsPetr = new ArrayList<>();
        accountsPetr.add(account3);


        // Создание основных коллекций accountsToClients и clientsToAccounts.
        HashMap<Account, Client> accountsToClients = new HashMap<>();
        accountsToClients.put(account1, ivan);
        accountsToClients.put(account2, ivan);
        accountsToClients.put(account3, petr);

        HashMap<Client, ArrayList<Account>> clientsToAccounts = new HashMap<>();
        clientsToAccounts.put(ivan, accountsIvan);
        clientsToAccounts.put(petr, accountsPetr);


        // Применение методов поиска.
        Bank bank = new Bank(accountsToClients, clientsToAccounts);
        ArrayList<Account> accounts = bank.getAccounts(ivan);
        Client client = bank.findClient(account1);


        // Вывод на экран.
        System.out.println(accounts);
        System.out.println(client);
    }
}

class Client {
    private String name;
    private int age;

    Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Account {
    private String accountNumber;

    Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                '}';
    }
}

class Bank {
    private HashMap<Account, Client> accountsToClients;
    private HashMap<Client, ArrayList<Account>> clientsToAccounts;

    Bank (HashMap<Account, Client> accountsToClients, HashMap<Client, ArrayList<Account>> clientsToAccounts) {
        this.accountsToClients = accountsToClients;
        this.clientsToAccounts = clientsToAccounts;
    }

    public ArrayList<Account> getAccounts(Client client) {
        return clientsToAccounts.get(client);
    }

    public Client findClient(Account account) {
        return accountsToClients.get(account);
    }
}