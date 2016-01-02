package me.cungu.transactiontree.support;

import org.springframework.beans.factory.FactoryBean;

import me.cungu.transactiontree.api.TransactionManager;

/**
 * 
 * @author fuhaining
 */
public class TransactionManagerFactoryBean implements FactoryBean<TransactionManager> {

	@Override
	public TransactionManager getObject() throws Exception {
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		return TransactionManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
