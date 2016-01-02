package me.cungu.transactiontree.repository;

import me.cungu.transactiontree.api.Transaction;
import me.cungu.transactiontree.api.TransactionCallback;

/**
 * 
 * @author fuhaining
 */
public interface TransactionStore {
	
	Transaction get(String transactionId);
	
	void put(String transactionId, Transaction transaction);
	
	void delete(String transactionId);
	
	void foreach(TransactionCallback<Object> action);
}