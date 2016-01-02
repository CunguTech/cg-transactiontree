package me.cungu.transactiontree.recovery;

import me.cungu.transactiontree.api.Transaction;
import me.cungu.transactiontree.api.TransactionRepository;
import me.cungu.transactiontree.log.TransactionLog;
import me.cungu.transactiontree.util.SerializationUtil;

/**
 * 
 * @author fuhaining
 */
public class DefaultRemoteTransactionRecoveryClient implements RemoteTransactionRecoveryClient {
	
	private TransactionRepository transactionRepository = null;

	@Override
	public boolean recovery(byte[] transactionBytes) {
		if (transactionBytes == null) {
			return false;
		}
		
		Transaction transaction = (Transaction) SerializationUtil.deserialize(transactionBytes);
		String transactionId = transaction.getXid().getTransactionId();
		try {
			int currentyRecoveryNum = transaction.incrRecoveryNum();
			transactionRepository.update(transaction);
			
			TransactionLog.RECOVERY.info("远程第{}次恢复事务, transactionId={}", currentyRecoveryNum, transactionId);
			
			transaction.recovery();
			
			transactionRepository.delete(transactionId);
			
			TransactionLog.RECOVERY.info("远程恢复事务成功, transactionId={}", transactionId);
			
			return true;
		} catch (RuntimeException e) {
			TransactionLog.RECOVERY.error("远程事务恢复错误，transactionId=" + transactionId, e);
			return false;
		}
	}

	public TransactionRepository getTransactionRepository() {
		return transactionRepository;
	}

	public void setTransactionRepository(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
}