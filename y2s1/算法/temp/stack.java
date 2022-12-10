public class stack {
    int[] stack;
    int top = -1;
    int size;
    stack(int size) {
        this.size = size;
        stack = new int[size];
    }
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull(){
        return top == size - 1;
    }
    public void push(int x){
        if(isFull()) throw new IllegalArgumentException("Stack Full");
        stack[++top] = x;
    }
    public int pop(){
        if(isEmpty()) throw new IllegalArgumentException("Stack Empty");
        return stack[top--];
    }
}