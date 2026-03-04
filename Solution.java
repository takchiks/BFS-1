class Solution {
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int indegrees [] = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        // have a key where key is the course that is prerequisite
        for (int  [] pair : prerequisites ) {
            indegrees[pair[0]]++;
            List<Integer> list = map.getOrDefault(pair[1], new ArrayList<>());
            list.add(pair[0]);
            map.put(pair[1], list);
        }
        // if course doesnt have prerequisite add into the queue and keep numCourses left to ge through
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
                numCourses--;
            }
        }

        int size = q.size();
        int temp = 0;

        // q contains courses that can be completed so far
        while (size != 0) {
            size = q.size();
            if (size == 0) return numCourses == 0;
            temp = q.poll();
            List<Integer> li = map.getOrDefault(temp, new ArrayList());
            for (int i = 0; i < li.size(); i++) {
                // if a course no longer have prerequisites add to q and decrements the num of courses left
                if (--indegrees[li.get(i)] == 0) {
                    q.add(li.get(i));
                    numCourses--;
                }
            }

            if(li.size() != 0) map.remove(temp);
        }
        // if there are no courses that cant be completed return true
        return numCourses == 0;
    }



    ///  level order with recursive

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        //keep track of the level
        helper(root, ans, 0);

        return ans;

    }

    public void helper(TreeNode rt, List<List<Integer>> ans, int level) {
        if (rt == null) return;

        if (ans.size() == level) {
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(rt.val);


        helper(rt.left, ans, level + 1);
        helper(rt.right, ans, level + 1);


    }
}