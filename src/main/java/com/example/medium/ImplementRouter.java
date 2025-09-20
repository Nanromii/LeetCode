package com.example.medium;

import java.util.*;

public class ImplementRouter {
    class Router {
        Map<Integer, List<Packet>> destinationMap;
        Deque<Packet> packets;
        Set<Packet> packetSet;
        private final int SIZE;

        public Router(int memoryLimit) {
            SIZE = memoryLimit;
            destinationMap = new HashMap<>();
            packets = new LinkedList<>();
            packetSet = new HashSet<>();
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            Packet newPacket = new Packet(source, destination, timestamp);
            if (packetSet.contains(newPacket)) return false;
            if (packetSet.size() == SIZE) forwardPacket();
            packetSet.add(newPacket);
            packets.addLast(newPacket);
            destinationMap
                    .computeIfAbsent(destination, k -> new ArrayList<>())
                    .add(newPacket);
            return true;
        }

        public int[] forwardPacket() {
            if (packetSet.isEmpty()) return new int[]{};
            Packet packet = packets.pollFirst();
            packetSet.remove(packet);
            List<Packet> list = destinationMap.get(packet.destination);
            if (list != null) {
                list.remove(packet);
                if (list.isEmpty()) destinationMap.remove(packet.destination);
            }
            return new int[]{packet.source, packet.destination, packet.time};
        }

        public int getCount(int destination, int startTime, int endTime) {
            List<Packet> list = destinationMap.get(destination);
            if (list == null) return 0;
            int left = lowerBound(list, startTime);
            int right = upperBound(list, endTime);
            return right - left;
        }

        private int lowerBound(List<Packet> list, int target) {
            int l = 0, r = list.size();
            while (l < r) {
                int m = (l + r) / 2;
                if (list.get(m).time >= target) r = m;
                else l = m + 1;
            }
            return l;
        }

        private int upperBound(List<Packet> list, int target) {
            int l = 0, r = list.size();
            while (l < r) {
                int m = (l + r) / 2;
                if (list.get(m).time > target) r = m;
                else l = m + 1;
            }
            return l;
        }

        class Packet {
            int source, destination, time;

            public Packet(int source, int destination, int time) {
                this.source = source;
                this.destination = destination;
                this.time = time;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Packet)) return false;
                Packet p = (Packet) o;
                return this.source == p.source && this.time == p.time && this.destination == p.destination;
            }

            @Override
            public int hashCode() {
                return Objects.hash(source, destination, time);
            }
        }
    }


/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */
}
