import java.util.Arrays;

public class hw4_uid {
    public static void main(String args[]) {
        int[] a = {2,0,2,0,0,0,0,0,0,0,0};
        Stack s = new Stack(10);
        Queue q = new Queue(10);
        Queue2Stack q2s = new Queue2Stack(10);
        Stack2Queue s2q = new Stack2Queue(10);

        for (int i = 1; i <= 10; i++) {
            s.push(i);
            q.enQueue(i);
            q2s.push(i);
            s2q.enQueue(i);
        }

        // countSort
        int maxNum = Arrays.stream(a).max().getAsInt();
        System.out.println("Before countSort" + Arrays.toString(a));
        int[] a_sorted = CountSort(a, maxNum);
        System.out.println("After countSort" + Arrays.toString(a_sorted) + '\n');

        // Stack test
        System.out.println(Arrays.toString(s.getStack()));
        System.out.println("Stack Remove:" + s.pop());
        System.out.println(Arrays.toString(s.getStack()) + '\n');

        // Queue test
        System.out.println(Arrays.toString(q.getQueue()));
        System.out.println("Queue Remove:" + q.deQueue());
        System.out.println(Arrays.toString(q.getQueue()) + '\n');

        // Queue2Stack test
        System.out.println(Arrays.toString(q2s.getQueue()));
        System.out.println("Queue2Stack Remove:" + q2s.pop());
        System.out.println(Arrays.toString(q2s.getQueue()) + '\n');

        // Stack2Queue test
        System.out.println(Arrays.toString(s2q.getStack()));
        System.out.println("Stack2Queue Remove:" + s2q.deQueue());
        System.out.println(Arrays.toString(s2q.getStack()) + '\n');
    }

    public static int[] CountSort(int[] array, int maxNum) {
        int[] temp = new int[maxNum + 1];
        for (int i = 0; i < array.length; i++) temp[array[i]]++;
        int[] res = new int[array.length];
        int i = 0, index = 0;
        while(index < array.length) {
            if(temp[i] != 0){
                res[index++] = i;
                temp[i]--;
            }else i++;
            
        }
        return res;
    }
}

class Stack {
    public int[] stack;
    public int top;
    public int size;

    public Stack(int stackSize) {
        size = stackSize;
        stack = new int[stackSize];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(int n) {
        if (isFull()) {
            System.out.println("Stack full");
        } else
            stack[++top] = n;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack empty");
        }
        return stack[top--];
    }

    public int[] getStack() {
        int[] stackArray = new int[top + 1];
        for (int i = 0; i < top + 1; i++) {
            stackArray[i] = stack[i];
        }
        return stackArray;
    }
}

class Queue {
    public int size;
    public int[] queue;
    public int tail = -1;
    public int head = -1;

    public Queue(int queueSize) {
        size = queueSize;
        queue = new int[queueSize];
    }

    public boolean isFull() {
        return head == size - 1;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void enQueue(int n) {
        if (isFull()) {
            System.out.println("Queue full");
            return;
        }
        queue[++head] = n;
    }

    public int deQueue() {
        if (isEmpty()) {
            System.out.println("Queue empty");
        }
        return queue[++tail];
    }

    public int[] getQueue() {
        int[] queueArray = new int[head - tail];
        int j = 0;
        for (int i = tail + 1; i < head + 1; i++) {
            queueArray[j++] = queue[i];
        }
        return queueArray;
    }
}

class Queue2Stack extends Queue {

    public Queue2Stack(int queueSize) {
        super(queueSize);
        size = queueSize;
        queue = new int[queueSize];
    }

    public void push(int n) {
        if (this.isFull()) {
            System.out.println("Queue full");
            return;
        }
        queue[++head] = n;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Queue empty");
        }
        int tempTail = tail, tempHead = head;
        Queue temp = new Queue(size);
        for (int i = tail + 1; i < head; i++) {
            temp.enQueue(this.deQueue());
        }
        int popNum = this.deQueue();
        queue = temp.getQueue();
        tail = tempTail;
        head = tempHead - 1;
        return popNum;
    }
}

class Stack2Queue extends Stack {

    public Stack2Queue(int stackSize) {
        super(stackSize);
    }

    public void enQueue(int n) {
        if (isFull()) {
            System.out.println("Queue full");
        } else
            stack[++top] = n;
    }

    public int deQueue() {
        if (isEmpty()) {
            System.out.println("Queue empty");
        }
        Stack temp = new Stack(size);
        int tempTop = top;
        for (int i = 0; i < tempTop; i++) {
            temp.push(this.pop());
        }
        int res = this.pop();
        for (int i = 0; i < tempTop; i++) {
            this.push(temp.pop());
        }
        return res;
    }
}