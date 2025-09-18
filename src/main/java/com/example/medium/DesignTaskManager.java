package com.example.medium;

import java.util.*;

public class DesignTaskManager {
    class TaskManager {
        private Map<Integer, Integer> userOfTask;
        private Map<Integer, Task> latestTask;
        private PriorityQueue<Task> maxHeap;

        public TaskManager(List<List<Integer>> tasks) {
            userOfTask = new HashMap<>();
            latestTask = new HashMap<>();
            maxHeap = new PriorityQueue<>((a, b) -> {
                if (b.priority != a.priority) return b.priority - a.priority;
                return b.taskId - a.taskId;
            });
            for (List<Integer> t : tasks) {
                add(t.get(0), t.get(1), t.get(2));
            }
        }

        public void add(int userId, int taskId, int priority) {
            Task task = new Task(taskId, priority);
            userOfTask.put(taskId, userId);
            latestTask.put(taskId, task);
            maxHeap.add(task);
        }

        public void edit(int taskId, int newPriority) {
            Task task = new Task(taskId, newPriority);
            latestTask.put(taskId, task);
            maxHeap.add(task);
        }

        public void rmv(int taskId) {
            latestTask.remove(taskId);
            userOfTask.remove(taskId);
        }

        public int execTop() {
            while (!maxHeap.isEmpty()) {
                Task top = maxHeap.poll();
                Task latest = latestTask.get(top.taskId);
                if (latest != null && latest == top) {
                    latestTask.remove(top.taskId);
                    return userOfTask.remove(top.taskId);
                }
            }
            return -1;
        }

        class Task {
            int taskId, priority;
            Task(int taskId, int priority) {
                this.taskId = taskId;
                this.priority = priority;
            }
        }
    }
}
