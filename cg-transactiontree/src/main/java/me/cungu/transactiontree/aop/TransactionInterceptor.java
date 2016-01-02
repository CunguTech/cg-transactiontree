package me.cungu.transactiontree.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import me.cungu.transactiontree.TransactionTemplate;
import me.cungu.transactiontree.api.Participant;
import me.cungu.transactiontree.api.Transaction;
import me.cungu.transactiontree.api.TransactionCallback;
import me.cungu.transactiontree.api.TransactionManager;
import me.cungu.transactiontree.api.TransactionNode;
import me.cungu.transactiontree.api.TransactionStatus;
import me.cungu.transactiontree.util.ParticipantBuilder;

/**
 * 
 * @author fuhaining
 *
 * POINTCUT @annotation(me.cungu.transationtree.TransationNode)
 */
public class TransactionInterceptor implements MethodInterceptor {
	
	private TransactionTemplate transactionTemplate = null;

	@Override
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		TransactionNode transactionNode = AnnotationUtils.findAnnotation(method, TransactionNode.class);
		if (transactionNode != null) { // check
			return transactionTemplate.execute(new TransactionCallback<Object>() {
				@Override
				public Object doInTransaction(Transaction transaction) throws Throwable {
					if (transaction.getTransactionStatus() == TransactionStatus.BEGINNING) { // Root / Nested / Branch
						Participant participant = buildParticipant(invocation);
						transactionTemplate.getTransactionManager().addParticipant(participant);
					}
					
					return invocation.proceed();
				}
			});
		} else {
			return invocation.proceed();
		}
	}
	
	public Participant buildParticipant(MethodInvocation invocation) {
		Object target = invocation.getThis();
		Class<?> targetClass = target.getClass();
		Method method = invocation.getMethod();
		Object[] arguments = invocation.getArguments();
		
		return ParticipantBuilder.build(null, targetClass, method, arguments);
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
}