package linked;

import java.util.Arrays;

/**
 * 反转链表
 * @Author lx
 * @Date 2020/9/26 11:44
 * @Version 1.0
 */
public class ReverseLinked {

    public static void main(String[] args) {
        ListNode head = new ListNode(9);
        getNode(head, 9);
        ListNode temp = head;
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = temp.val;
            temp = temp.next;
        }
        System.out.println("反转之前：" + Arrays.toString(arr));
        double random = Math.random() * 3;
        if (random < 1) {
            System.out.println("================反转方式 1 ================");
            head = reverse1(head);
        } else if (random < 2) {
            System.out.println("================反转方式 2 ================");
            head = reverse2(head);
        } else {
            System.out.println("================反转方式 3 ================");
            head = reverse3(head);
        }
        for (int i = 0; i < 10; i++) {
            arr[i] = head.val;
            head = head.next;
        }
        System.out.println("反转之后：" + Arrays.toString(arr));
    }

    /**
     * 构造一个链表
     * @param head
     * @param n
     * @return
     */
    public static ListNode getNode(ListNode head, int n) {
        if (n == 0) {return null;}
        head.next = new ListNode(--n);
        return getNode(head.next, n);
    }

    public static ListNode reverse1(ListNode head) {
        ListNode cur = null, temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = cur;
            cur = temp;
            temp = next;
        }
        return cur;
    }

    public static ListNode reverse2(ListNode head) {
        if (head == null) {return null;}
        ListNode cur = head;
        while (head.next != null) {
            ListNode next = head.next.next;
            head.next.next = cur;
            cur = head.next;
            head.next = next;
        }
        return cur;
    }

    public static ListNode reverse3(ListNode head) {
        if (head == null || head.next == null) {return head;}
        ListNode newHead = reverse3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
