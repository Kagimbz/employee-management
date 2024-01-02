package com.emtech.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
//		ListNode head = new ListNode(10);
//		ListNode second = new ListNode(1);
//		ListNode third = new ListNode(5);
//		ListNode fourth = new ListNode(3);
//		head.next = second;
//		second.next = third;
//		third.next = fourth;
//
//		//Add Beginning
//		ListNode newNode = new ListNode(22);
//		newNode.next = head;
//		head = newNode;
//
//		//Add End
//		ListNode newNode2 = new ListNode(23);
//		ListNode current = head;
//		while (current.next != null){
//			current = current.next;
//		}
//		current.next = newNode2;
//
//		printLinkedList(head);
	}

//	private static void printLinkedList(ListNode head) {
//		ListNode current = head;
//		while (current != null){
//			System.out.print(current.data + "-->");
//			current = current.next;
//		}
//		System.out.print("null");
//	}
//
//	public static class ListNode{
//		private final int data;
//		private ListNode next;
//
//		public ListNode(int data) {
//			this.data = data;
//		}
//	}

}
