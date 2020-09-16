package com.atul;

import java.util.LinkedList;
import java.util.Queue;

public class LevelWithMaximumNumberOfNodes {
	static class TreeNode {
		public Integer val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(Integer x) {
			val = x;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		// Function to insert nodes in level order
		public TreeNode insertLevelOrder(int[] arr, TreeNode root, int i) {
			// Base case for recursion
			if (i < arr.length) {
				TreeNode temp = new TreeNode(arr[i]);
				root = temp;

				// insert left child
				root.left = insertLevelOrder(arr, root.left, 2 * i + 1);

				// insert right child
				root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
			}
			return root;
		}
	}

	static int maxNodeLevel(TreeNode root) {
		if (root == null)
			return -1;

		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		// Current level  
		int level = 0;

		// Maximum Nodes at same level  
		int max = Integer.MIN_VALUE;

		// Level having maximum Nodes  
		int level_no = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			if (size > max) {
				max = size;
				level_no = level;

			}

			while (size > 0) {
				TreeNode current = q.poll();
				if (current.left != null)
					q.add(current.left);
				if(current.right!=null)
					q.add(current.right);
				
				size--;
			}
			level++;
		}
		
		return level_no;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(2); /*     2     */
		root.left = new TreeNode(1); /*     / \ */
		root.right = new TreeNode(3); /*     1     3     */
		root.left.left = new TreeNode(4); /* / \ \ */
		root.left.right = new TreeNode(6); /* 4     6 8 */
		root.right.right = new TreeNode(8); /*     /     */
		root.left.right.left = new TreeNode(5);/*     5     */
		System.out.println(maxNodeLevel(root));
	}
}
