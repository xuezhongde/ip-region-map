package org.zdxue.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 * @author xuezhongde
 */
public class IpRegionMap {
    private static final TreeMap<IpRegionKey, IpRegion> ipRegionMap = new TreeMap<>();

    public static void load(InputStream input) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(input))
        ) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split("\t");
                if (columns.length < 3 || columns.length > 4)
                    continue;

                long begin = ip2long(columns[0]);
                long end = ip2long(columns[1]);
                String prov = columns[2];
                String city = columns.length < 4 ? "" : columns[3];
                ipRegionMap.put(new IpRegionKey(begin, end), new IpRegion(begin, end, prov, city));
                lineCount++;
            }
            System.out.println(lineCount + " lines loaded");
        }
    }

    public static IpRegion get(String ip) {
        long ipLongVal = ip2long(ip);
        return ipRegionMap.get(new IpRegionKey(ipLongVal, ipLongVal));
    }

    public static int size() {
        return ipRegionMap.size();
    }

    private static long ip2long(String ip) {
        String[] segments = ip.split("\\.");
        if (segments.length != 4) {
            return -1;
        }

        long ipAddr = 0;
        ipAddr |= (str2long(segments[0]) << 24) & 0xFF000000;
        ipAddr |= (str2long(segments[1]) << 16) & 0x00FF0000;
        ipAddr |= (str2long(segments[2]) << 8) & 0x0000FF00;
        ipAddr |= str2long(segments[3]) & 0x000000FF;
        return ipAddr;
    }

    private static long str2long(String str) {
        return Long.parseLong(str);
    }

    private final static class IpRegionKey implements Comparable<IpRegionKey> {
        private final long begin;
        private final long end;

        public IpRegionKey(long begin, long end) {
            this.begin = begin;
            this.end = end;
        }

        public long getBegin() {
            return begin;
        }

        public long getEnd() {
            return end;
        }

        @Override
        public int compareTo(IpRegionKey e) {
            if (e.begin <= this.begin && e.end >= this.begin) {
                return 0;
            }

            return Long.compare(this.end, e.begin);
        }
    }

    /**
     * Tips: the fields of 'begin', 'end' were not needed!
     */
    public final static class IpRegion {
        private final long begin;
        private final long end;
        private final String prov;
        private final String city;

        public IpRegion(long begin, long end, String prov, String city) {
            this.begin = begin;
            this.end = end;
            this.prov = prov;
            this.city = city;
        }

        public long getBegin() {
            return begin;
        }

        public long getEnd() {
            return end;
        }

        public String getProv() {
            return prov;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "IpRegion{" +
                    "begin=" + begin +
                    ", end=" + end +
                    ", prov='" + prov + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

}
