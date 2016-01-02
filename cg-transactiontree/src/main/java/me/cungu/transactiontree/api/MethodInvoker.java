package me.cungu.transactiontree.api;

/**
 * 
 * @author fuhaining
 */
public interface MethodInvoker {
	
	Object invoke(Object[] arguments);
	
	Class<?> getTargetClass();
	
	void setTargetClass(Class<?> targetClass);
	
	String getMethodSignature();

	void setMethodSignature(String methodSignature);
}