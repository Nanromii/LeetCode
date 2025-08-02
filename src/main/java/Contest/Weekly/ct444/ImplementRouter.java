package Contest.Weekly.ct444;

import java.util.*;

class ImplementRouter {
    private int memoryLimit;
    private Queue<Data> queue;
    private Set<PacketKey> packetSet;
    private Map<Integer, TreeMap<Integer, Integer>> timeMap;

    public ImplementRouter(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.queue = new LinkedList<>();
        this.packetSet = new HashSet<>();
        this.timeMap = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        PacketKey key = new PacketKey(source, destination, timestamp);
        if (packetSet.contains(key)) return false;
        if (queue.size() == memoryLimit) {
            Data old = queue.poll();
            packetSet.remove(old.key);
            TreeMap<Integer, Integer> map = timeMap.get(old.destination);
            if (map != null) {
                map.put(old.timestamp, map.get(old.timestamp) - 1);
                if (map.get(old.timestamp) == 0) map.remove(old.timestamp);
                if (map.isEmpty()) timeMap.remove(old.destination);
            }
        }
        Data data = new Data(source, destination, timestamp, key);
        queue.offer(data);
        packetSet.add(key);
        timeMap.putIfAbsent(destination, new TreeMap<>());
        TreeMap<Integer, Integer> map = timeMap.get(destination);
        map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
        return true;
    }

    public int[] forwardPacket() {
        if (queue.isEmpty()) return new int[0];
        Data data = queue.poll();
        packetSet.remove(data.key);
        TreeMap<Integer, Integer> map = timeMap.get(data.destination);
        if (map != null) {
            map.put(data.timestamp, map.get(data.timestamp) - 1);
            if (map.get(data.timestamp) == 0) map.remove(data.timestamp);
            if (map.isEmpty()) timeMap.remove(data.destination);
        }
        return new int[]{data.source, data.destination, data.timestamp};
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!timeMap.containsKey(destination)) return 0;
        TreeMap<Integer, Integer> map = timeMap.get(destination);
        Integer from = map.ceilingKey(startTime);
        Integer to = map.floorKey(endTime);
        if (from == null || to == null || from > to) return 0;
        return map.subMap(from, true, to, true).values().stream().mapToInt(i -> i).sum();
    }

    class Data {
        int source, destination, timestamp;
        PacketKey key;
        public Data(int source, int destination, int timestamp, PacketKey key) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
            this.key = key;
        }
    }

    class PacketKey {
        int source, destination, timestamp;
        public PacketKey(int source, int destination, int timestamp) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof PacketKey)) return false;
            PacketKey other = (PacketKey) o;
            return source == other.source && destination == other.destination && timestamp == other.timestamp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination, timestamp);
        }
    }
}