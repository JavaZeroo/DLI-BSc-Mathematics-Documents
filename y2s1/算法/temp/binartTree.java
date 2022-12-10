public class binartTree {
    public static void main(String[] args) {
        tree tree = new tree();
        tree.addNode(new treeNode('F'));
        tree.addNode(new treeNode('B'));
        tree.addNode(new treeNode('G'));
        tree.addNode(new treeNode('A'));
        tree.addNode(new treeNode('D'));
        tree.addNode(new treeNode('I'));
        tree.addNode(new treeNode('C'));
        tree.addNode(new treeNode('E'));
        tree.addNode(new treeNode('H'));
        tree.preOrder();
        tree.inOrder();
        tree.postOrder();
        System.out.println(tree.successor(tree.search('B')));
    }
}

class tree{
    treeNode root;

    public tree(){};
    public tree(char data){
        root = new treeNode(data);
    }
    public tree(treeNode root){
        this.root = root;
    }

    public void addNode(treeNode node){
        if(isEmpty()) root = node;
        addNode(root, node);
    }

    private treeNode addNode(treeNode current, treeNode node){
        if(current == null) return node;
        if(node.getData() < current.getData()){
            current.setLeft(addNode(current.getLeft(), node));
            current.getLeft().setParent(current);
        }else if(node.getData() > current.getData()){
            current.setRight(addNode(current.getRight(), node));
            current.getRight().setParent(current);
        }else{
            return current;
        }
        return current;
    }

    public void preOrder() {
        preOrder(root);
        System.out.print('\n');
    }

    private void preOrder(treeNode node){
        if(node == null) return;
        System.out.print(node.getData());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void inOrder() {
        inOrder(root);
        System.out.print('\n');
    }

    private void inOrder(treeNode node){
        if(node == null) return;
        inOrder(node.getLeft());
        System.out.print(node.getData());
        inOrder(node.getRight());
    }

    public void postOrder() {
        postOrder(root);
        System.out.print('\n');
    }

    private void postOrder(treeNode node){
        if(node == null) return;
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getData());
    }

    public treeNode search(char data){
        return search(data, root);
    }

    private treeNode search(char data, treeNode current){
        if(data == current.getData()) return current;
        else if(data < current.getData()) return search(data, current.getLeft());
        else return search(data, current.getRight());
    }

    public treeNode successor(treeNode node){
        if(node.getRight() != null){
            return min(node.getRight());
        }
        else{
            while(node.getParent() != null && node.getParent().getLeft() != node){
                node = node.getParent();
            }
            return node.getParent();
        }
    }

    public treeNode min(treeNode node){
        if(node.getLeft() != null){
            return min(node.getLeft());
        }
        else return node;
    }

    public boolean isEmpty(){
        return root == null;
    }
}

class treeNode {
    char data;
    treeNode left, right, parent;
    
    public treeNode(char data) {
        this.data = data;
    }
    public char getData() {
        return data;
    }
    public void setData(char data) {
        this.data = data;
    }
    public treeNode getLeft() {
        return left;
    }
    public void setLeft(treeNode left) {
        this.left = left;
    }
    public treeNode getRight() {
        return right;
    }
    public void setRight(treeNode right) {
        this.right = right;
    }
    public treeNode getParent() {
        return parent;
    }
    public void setParent(treeNode parent) {
        this.parent = parent;
    }
    @Override
    public String toString() {
        return "treeNode [data=" + data + "]";
    }
    
}