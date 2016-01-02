package me.cungu.transactiontreetest.case1;

import java.util.Random;

import org.springframework.stereotype.Service;

import me.cungu.transactiontree.api.TransactionNode;

@Service
public class ClassC {
	
	public static boolean exception = false; 
	
	@TransactionNode(commit = "m1_confirm", rollback = "r1_cannel")
	public String m1(String p1, String p2) {
//		System.out.println("ClassC m1");
		
//		if (exception) {
		if (new Random().nextInt(100) < 10) {
			throw new RuntimeException("test");
		}
		
		return "c";
	}

	public void m1_confirm(String p1, String p2) {
//		System.out.println("ClassC m1_confirm");
	}

	public void r1_cannel(String p1, String p2) {
//		System.out.println("ClassC r1_cannel");
	}
}
