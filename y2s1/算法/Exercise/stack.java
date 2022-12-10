public class stack {
    public int[] stack;
    public int size;
    public int top = -1;

    public stack(int size){
        this.size = size;
        stack = new int[size];
    }
    
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(int n) {
        if(isFull()){ 
            System.out.println("Stack Full!");
            return;
        }
        stack[++top] = n;
    }

    public int pop(){
        if(isEmpty()) System.out.println("Stack Empty!");
        return stack[top--];
    }

    public int[] getStack(){
        return stack.clone();
    }
}