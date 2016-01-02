package me.cungu.transactiontree;

import java.util.UUID;

import me.cungu.transactiontree.api.TransactionIdGenerator;

/**
 * 
 * @author fuhaining
 */
public class UuidTransactionIdGenerator implements TransactionIdGenerator {
	
	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}
}