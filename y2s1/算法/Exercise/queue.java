import java.util.Arrays;

public class queue {
    public int[] queue;
    public int size;
    public int tail = -1, head = -1;

    public queue(int size){
        queue = new int[size];
        this.size = size;
    }

    public boolean isFull() {
        return head == size - 1 ;
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public void enQueue(int n) {
        if(isFull()) return;
        queue[++head] = n;
    }

    public int deQueue() {
        return queue[++tail];
    }

    public int[] getQueue() {
        return  Arrays.copyOfRange(queue, tail + 1, head + 1);
    }
}
