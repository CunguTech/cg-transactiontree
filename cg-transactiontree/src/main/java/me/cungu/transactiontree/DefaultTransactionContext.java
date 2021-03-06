package me.cungu.transactiontree;

import me.cungu.transactiontree.api.TransactionContext;
import me.cungu.transactiontree.api.TransactionStatus;
import me.cungu.transactiontree.api.Xid;

/**
 * 
 * @author fuhaining
 */
public class DefaultTransactionContext implements TransactionContext {
	
	private Xid xid;
	
	private TransactionStatus transactionStatus;
	
//	private Map<String, Object> attachments = new HashMap<String, Object>(); // TODO 需要持久化

	@Override
	public Xid getXid() {
		return this.xid;
	}

	@Override
	public void setXid(Xid xid) {
		this.xid = xid;
	}

	@Override
	public TransactionStatus getTransactionStatus() {
		return this.transactionStatus;
	}

	@Override
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

//	public Map<String, Object> getAttachments() {
//		return attachments;
//	}
//
//	public void setAttachments(Map<String, Object> attachments) {
//		this.attachments = attachments;
//	}
}