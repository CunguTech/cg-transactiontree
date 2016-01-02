package me.cungu.transactiontree.api;

/**
 * 
 * @author fuhaining
 */
public interface TransactionContext {

	Xid getXid();

	void setXid(Xid xid);

	TransactionStatus getTransactionStatus();

	void setTransactionStatus(TransactionStatus transactionStatus);

}