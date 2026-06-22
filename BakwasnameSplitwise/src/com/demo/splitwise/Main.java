package com.demo.splitwise;

import java.util.List;

import com.demo.splitwise.split.PercentageSplit;
import com.demo.splitwise.split.Split;
import com.demo.splitwise.split.SplitType;

public class Main {

	public static void main(String[] args) {

		/*
		 * User abhishek = new User("1", "Abhishek");
		 * 
		 * User palak = new User("2", "Palak");
		 * 
		 * User rahul = new User("3", "Rahul"); User naman = new User("4", "Naman");
		 * 
		 * SplitwiseService service = new SplitwiseService();
		 * 
		 * SplitStrategy equalSplit = new EqualSplitStrategy();
		 * 
		 * service.addExpense(abhishek, 900, List.of(abhishek, palak, rahul),
		 * equalSplit);
		 * 
		 * System.out.println("Showing individual balances"); service.showBalances();
		 */

		User abhishek = new User("Abhishek");

		User palak = new User("Palak");

		User rahul = new User("Rahul");
		User naman = new User("Naman");

		SplitwiseService service = new SplitwiseService(new BalanceSheet());

		//List<Split> participants = List.of(new EqualSplit(naman), new EqualSplit(palak), new EqualSplit(rahul),new EqualSplit(abhishek));
		//Expense expense = service.createExpense(abhishek, participants, 1000, SplitType.EQUAL);

		List<Split> participants = List.of(new PercentageSplit(naman, 40.0), new PercentageSplit(palak, 30.0),
				new PercentageSplit(rahul, 20.0), new PercentageSplit(abhishek, 10.0));
		Expense expense = service.createExpense(abhishek, participants, 1000, SplitType.PERCENTAGE);

		service.addExpense(expense);

		service.showBalances();

	}
}

/*
 * class User {
 * 
 * private final String id; private final String name;
 * 
 * public User(String id, String name) { this.id = id; this.name = name; }
 * 
 * public String getId() { return id; }
 * 
 * public String getName() { return name; }
 * 
 * @Override public boolean equals(Object o) {
 * 
 * if (this == o) { return true; }
 * 
 * if (!(o instanceof User)) { return false; }
 * 
 * User user = (User) o;
 * 
 * return id.equals(user.id); }
 * 
 * @Override public int hashCode() { return id.hashCode(); } }
 * 
 * class Split {
 * 
 * private final User user;
 * 
 * private final double amount;
 * 
 * public Split(User user, double amount) {
 * 
 * this.user = user; this.amount = amount; }
 * 
 * public User getUser() { return user; }
 * 
 * public double getAmount() { return amount; } }
 * 
 * class Expense {
 * 
 * private final User paidBy;
 * 
 * private final double amount;
 * 
 * private final List<Split> splits;
 * 
 * public Expense(User paidBy, double amount, List<Split> splits) {
 * 
 * this.paidBy = paidBy; this.amount = amount; this.splits = splits; }
 * 
 * public User getPaidBy() { return paidBy; }
 * 
 * public double getAmount() { return amount; }
 * 
 * public List<Split> getSplits() { return splits; } }
 * 
 * interface SplitStrategy {
 * 
 * List<Split> calculateSplit(double amount, List<User> participants); }
 * 
 * class EqualSplitStrategy implements SplitStrategy {
 * 
 * @Override public List<Split> calculateSplit(double amount, List<User>
 * participants) {
 * 
 * List<Split> splits = new ArrayList<>();
 * 
 * double share = amount / participants.size();
 * 
 * for (User user : participants) {
 * 
 * splits.add(new Split(user, share)); }
 * 
 * return splits; } }
 * 
 * class BalanceSheet {
 * 
 * private final Map<User, Map<User, Double>> balances;
 * 
 * public BalanceSheet() {
 * 
 * balances = new HashMap<>(); }
 * 
 * public void updateBalance(User paiseJisneDiye, User jispeUdharHai, double
 * amount) {
 * 
 * balances.computeIfAbsent(paiseJisneDiye, k -> new HashMap<>());
 * balances.computeIfAbsent(jispeUdharHai, k -> new HashMap<>());
 * 
 * double jispeUdharHaiUskaUdhar =
 * balances.get(jispeUdharHai).getOrDefault(paiseJisneDiye, 0.0);
 * 
 * double jisnePaiseDiyeUskaUdhar =
 * balances.get(paiseJisneDiye).getOrDefault(jispeUdharHai, 0.0);
 * 
 * balances.get(paiseJisneDiye).put(jispeUdharHai, jisnePaiseDiyeUskaUdhar -
 * amount); balances.get(jispeUdharHai).put(paiseJisneDiye,
 * jispeUdharHaiUskaUdhar + amount); }
 * 
 * public void showBalances() {
 * 
 * for (User debtor : balances.keySet()) { for (Map.Entry<User, Double> entry :
 * balances.get(debtor).entrySet()) { if (entry.getValue() > 0) { System.out
 * .println(debtor.getName() + " owes " + entry.getKey().getName() + " : " +
 * entry.getValue()); } } } } }
 * 
 * class SplitwiseService {
 * 
 * private final List<Expense> expenses;
 * 
 * private final BalanceSheet balanceSheet;
 * 
 * public SplitwiseService() {
 * 
 * expenses = new ArrayList<>();
 * 
 * balanceSheet = new BalanceSheet(); }
 * 
 * public void addExpense(User paidBy, double amount, List<User> participants,
 * SplitStrategy strategy) {
 * 
 * if (amount <= 0) { throw new
 * IllegalArgumentException("Amount must be positive"); }
 * 
 * List<Split> splits = strategy.calculateSplit(amount, participants);
 * 
 * Expense expense = new Expense(paidBy, amount, splits);
 * 
 * expenses.add(expense);
 * 
 * for (Split split : splits) {
 * 
 * User participant = split.getUser();
 * 
 * if (participant.equals(paidBy)) {
 * 
 * continue; }
 * 
 * balanceSheet.updateBalance(paidBy, participant, split.getAmount()); } }
 * 
 * public void showBalances() { balanceSheet.showBalances(); }
 * 
 * public BalanceSheet getBalanceSheet() { return balanceSheet; } }
 */