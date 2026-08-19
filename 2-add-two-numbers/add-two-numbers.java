/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode head = null;   // first node of our answer list
        ListNode tail = null;   // last node of our answer list so far
        int carry = 0;

        while (l1 != null || l2 != null) {

            int x = 0;
            if (l1 != null) {
                x = l1.val;
            }

            int y = 0;
            if (l2 != null) {
                y = l2.val;
            }

            int sum = x + y + carry;
            int digit = sum % 10;   // the digit we keep
            carry = sum / 10;       // the digit we carry over

            ListNode newNode = new ListNode(digit);

            if (head == null) {
                head = newNode;     // this is the very first node
                tail = newNode;
            } else {
                tail.next = newNode; // attach new node to the end
                tail = newNode;      // move tail forward
            }

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // handle leftover carry, e.g. 5+5=10
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return head;
    }
}