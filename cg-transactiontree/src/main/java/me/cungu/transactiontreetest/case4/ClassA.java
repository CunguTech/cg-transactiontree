package me.cungu.transactiontreetest.case4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.cungu.transactiontree.api.TransactionNode;

@Service
public class ClassA {

	@Autowired
	private Client client;

	@TransactionNode(commit = "m1_confirm", rollback = "r1_cannel")
	public String m1(String p1, String p2) {
		System.out.println(Thread.currentThread() + " ClassA m1");

		client.m1(p1, p2);

		return "a";
	}

	public void m1_confirm(String p1, String p2) {
		System.out.println(Thread.currentThread() + " ClassA m1_confirm");
	}

	public void r1_cannel(String p1, String p2) {
		System.out.println(Thread.currentThread() + " ClassA r1_cannel");
	}
}
