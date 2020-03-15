package org.zdxue.util;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author xuezhongde
 */
public class IpRegionMapTest {

    @Test
    public void test() throws Exception {
        InputStream input = IpRegionMapTest.class.getClassLoader().getResourceAsStream("ip_region.conf");
        IpRegionMap.load(input);
        System.out.println(IpRegionMap.size() + " elements in the IpRegionMap");

        String[] samples = {
                "223.247.19.27",
                "210.52.128.70",
                "183.44.53.128",
                "1.0.3.215",
                "171.217.190.232",
                "183.39.148.212"
        };

        for (String ip : samples) {
            long startNano = System.nanoTime();
            IpRegionMap.IpRegion ipRegion = IpRegionMap.get(ip);
            long elapsed = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startNano);
            if (ipRegion == null) {
                System.out.println("miss " + ip + ", elapsed: " + elapsed + "μs)");
            } else {
                System.out.println("hit " + ip + ", " + ipRegion + ", elapsed: " + elapsed + "μs)");
            }
        }
    }

}
