public class hw6_uid {
    public static void main(String[] args) {
        char[] vexs = { 'A', 'B', 'C', 'D', 'E' };
        char[][] edges = new char[][] { { 'A', 'B' }, { 'A', 'D' }, { 'B', 'C' }, { 'C', 'D' }, { 'D', 'C' },
                { 'C', 'E' }, { 'D', 'E' } };
        List pG;

        pG = new List(vexs, edges);
        pG.BFS(); // 广度优先遍历
        pG.DFS(); // 深度优先遍历
    }
}

class List {

    private class ENode {
        int ivex;
        ENode nextEdge;
    }

    private class VNode {
        char data;
        ENode firstEdge;
    };

    private VNode[] mVexs;

    public List(char[] vexs, char[][] edges) {

        int vlen = vexs.length;
        int elen = edges.length;

        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode();
            mVexs[i].data = vexs[i];
            mVexs[i].firstEdge = null;
        }

        for (int i = 0; i < elen; i++) {
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);

            ENode node1 = new ENode();
            node1.ivex = p2;
            if (mVexs[p1].firstEdge == null)
                mVexs[p1].firstEdge = node1;
            else
                linkLast(mVexs[p1].firstEdge, node1);
        }
    }

    private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while (p.nextEdge != null)
            p = p.nextEdge;
        p.nextEdge = node;
    }

    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++)
            if (mVexs[i].data == ch)
                return i;
        return -1;
    }

    private void DFSVisit(int i, boolean[] visited) {
        ENode node;

        visited[i] = true;
        System.out.printf("%c ", mVexs[i].data);
        node = mVexs[i].firstEdge;
        while (node != null) {
            if (!visited[node.ivex])
                DFSVisit(node.ivex, visited);
            node = node.nextEdge;
        }
    }

    public void DFS() {
        boolean[] visited = new boolean[mVexs.length];

        for (int i = 0; i < mVexs.length; i++)
            visited[i] = false;

        System.out.printf("DFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i])
                DFSVisit(i, visited);
        }
        System.out.printf("\n");
    }

    public void BFS() {
        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.length];
        boolean[] visited = new boolean[mVexs.length];

        for (int i = 0; i < mVexs.length; i++)
            visited[i] = false;

        System.out.printf("BFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", mVexs[i].data);
                queue[rear++] = i;
            }

            while (head != rear) {
                int j = queue[head++];
                ENode node = mVexs[j].firstEdge;
                while (node != null) {
                    int k = node.ivex;
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.printf("%c ", mVexs[k].data);
                        queue[rear++] = k;
                    }
                    node = node.nextEdge;
                }
            }
        }
        System.out.printf("\n");
    }
}