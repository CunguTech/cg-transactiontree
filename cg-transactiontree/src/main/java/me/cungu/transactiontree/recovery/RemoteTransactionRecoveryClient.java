package me.cungu.transactiontree.recovery;

/**
 * 
 * @author fuhaining
 */
public interface RemoteTransactionRecoveryClient {
	
	boolean recovery(byte[] transactionBytes);
	
}