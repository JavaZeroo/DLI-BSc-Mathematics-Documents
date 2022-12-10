import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;

public class hw9_uid {
	public static void main(String[] args) {
		Vertex s = new Vertex("S");
		Vertex t = new Vertex("T");
		Vertex y = new Vertex("Y");
		Vertex x = new Vertex("X");
		Vertex z = new Vertex("Z");
		Map<Vertex, Map<Vertex, Double>> G = new HashMap<Vertex, Map<Vertex, Double>>();
		Map<Vertex, Double> sNbr = new HashMap<>();
		sNbr.put(t, 10.);
		sNbr.put(y, 5.);
		G.put(s, sNbr);
		Map<Vertex, Double> tNbr = new HashMap<>();
		tNbr.put(x, 1.);
		tNbr.put(y, 2.);
		G.put(t, tNbr);
		Map<Vertex, Double> yNbr = new HashMap<>();
		yNbr.put(t, 3.);
		yNbr.put(x, 9.);
		yNbr.put(z, 2.);
		G.put(y, yNbr);
		Map<Vertex, Double> xNbr = new HashMap<>();
		xNbr.put(z, 4.);
		G.put(x, xNbr);
		Map<Vertex, Double> zNbr = new HashMap<>();
		zNbr.put(s, 7.);
		zNbr.put(x, 6.);
		G.put(z, zNbr);
		List<Vertex> path = shortestPath(G, x, y);

		System.out.print("The shortest path from Vertex X to Vertex Y: ");
		for (int i = 0; i < path.size(); i++) {
			System.out.print(path.get(i).getName() + ((i != path.size() - 1) ? " -> " : "\n"));
		}
		System.out.println("Shortest Distance:" + path.get(path.size() - 1).getD());

		int[] arr = { -2, 0, 2, 0, 3, 2, -9, 3, 0, 1, 2 };
		System.out.println("\narr is " + Arrays.toString(arr));
        System.out.println("Using  brutal force get the sum of maximum subarray is " + brutal(arr));
        System.out.println("Using divide-and-conquer get the sum of maximum subarray is " + maxSubArray(arr));
	}

	public static List<Vertex> shortestPath(Map<Vertex, Map<Vertex, Double>> G, Vertex start, Vertex end) {
		List<Vertex> res = new ArrayList<Vertex>();
		PriorityQueue<Vertex> Q = new PriorityQueue<>((o1, o2) -> {
			return Double.compare(o1.d, o2.d);
		});
		start.setD(0);
		Q.addAll(G.keySet());
		while (!Q.isEmpty()) {
			Vertex temp = Q.poll();
			relx(temp, G.get(temp));
		}

		Vertex temp = end;
		res.add(temp);
		while (temp.getPi() != null) {
			res.add(temp.getPi());
			temp = temp.getPi();
		}
		Collections.reverse(res);
		return res;
	}

	public static void relx(Vertex vertex, Map<Vertex, Double> map) {
		for (Vertex neighbor : map.keySet()) {
			double d = vertex.getD() + map.get(neighbor);
			if (neighbor.getD() > d) {
				neighbor.setD(d);
				neighbor.setPi(vertex);
			}
		}
	}

	static int brutal(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int sum = 0;
                for (int t = i; t <= j; t++) {
                    sum += arr[t];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        return maxSubArraySum(nums, 0, len - 1);
    }

    private static int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;

    }

    private static int maxSubArraySum(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) >> 1;
        return max3(maxSubArraySum(nums, left, mid), maxSubArraySum(nums, mid + 1, right),
                maxCrossingSum(nums, left, mid, right));
    }

    private static int max3(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }
}

class Vertex {
	double d;
	String name;
	Vertex pi;

	public Vertex(String name) {
		this.name = name;
		d = Double.POSITIVE_INFINITY;
		pi = null;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vertex getPi() {
		return pi;
	}

	public void setPi(Vertex pi) {
		this.pi = pi;
	}

	@Override
	public String toString() {
		return "Vertex [d=" + d + ", name=" + name + "]";
	}
}