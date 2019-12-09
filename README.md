### Introduce 
IpRegionMap based on TreeMap(red-black-tree), `$ O(log_2N) $`

### ip_region.conf 
<b>Line Format(separated by '\t'): </b>ip_begin, ip_end, province, city <br>
content as below (only for sample)
```$xslt
0.0.0.0	1.0.0.255	9999	
1.0.1.0	1.0.3.255	5	
1.0.4.0	1.0.7.255	9999	
1.0.8.0	1.0.15.255	4	
1.0.16.0	1.0.31.255	9999	
1.0.32.0	1.0.63.255	4	
1.0.64.0	1.0.255.255	9999	
61.175.183.0	61.175.183.255	32	277
61.175.184.0	61.175.191.255	32	272
61.175.192.0	61.175.199.255	32	280
61.175.200.0	61.175.205.255	32	276
61.175.206.0	61.175.217.255	32	278
61.175.218.0	61.175.224.255	32	279
61.175.225.0	61.175.225.255	32	282
61.175.226.0	61.175.229.255	32	272
61.175.230.0	61.175.231.255	32	279
61.175.232.0	61.175.232.255	32	282
61.175.233.0	61.175.233.255	32	281
61.175.234.0	61.175.235.255	32	276
```

### Usage
```java
InputStream input = new FileInputStream("D:/ip_region.conf");
IpRegionMap.load(input);
IpRegionMap.IpRegion ipRegion = IpRegionMap.get(ip);
```

### TeseCase
```java
public class IpRegionMapTest {

    @Test
    public void test() throws IOException {
        InputStream input = new FileInputStream("D:/ip_region.conf");
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
```

### TestCase output
```$xslt
D:\Program\Java8\jdk1.8.0_45\bin\java.exe -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2019.3\lib\idea_rt.jar=50855:D:\Program\JetBrains\IntelliJ IDEA 2019.3\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program\JetBrains\IntelliJ IDEA 2019.3\lib\idea_rt.jar;D:\Program\JetBrains\IntelliJ IDEA 2019.3\plugins\junit\lib\junit5-rt.jar;D:\Program\JetBrains\IntelliJ IDEA 2019.3\plugins\junit\lib\junit-rt.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\charsets.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\deploy.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\access-bridge-64.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\cldrdata.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\dnsns.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\jaccess.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\jfxrt.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\localedata.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\nashorn.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\sunec.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\sunjce_provider.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\sunmscapi.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\sunpkcs11.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\ext\zipfs.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\javaws.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\jce.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\jfr.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\jfxswt.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\jsse.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\management-agent.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\plugin.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\resources.jar;D:\Program\Java8\jdk1.8.0_45\jre\lib\rt.jar;D:\Projects\2_opensource\github\java\ip-region-map\target\test-classes;D:\Projects\2_opensource\github\java\ip-region-map\target\classes;D:\Maven_repo\junit\junit\4.12\junit-4.12.jar;D:\Maven_repo\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 org.zdxue.util.IpRegionMapTest,test
86163 lines loaded
86163 elements in the IpRegionMap
hit 223.247.19.27, IpRegion{begin=3757506560, end=3757527039, prov='9', city='130'}, elapsed: 1099μs)
hit 210.52.128.70, IpRegion{begin=3526656070, end=3526656070, prov='5', city='81'}, elapsed: 31μs)
hit 183.44.53.128, IpRegion{begin=3073114112, end=3073130495, prov='4', city='111'}, elapsed: 7μs)
hit 1.0.3.215, IpRegion{begin=16777472, end=16778239, prov='5', city=''}, elapsed: 5μs)
hit 171.217.190.232, IpRegion{begin=2883124224, end=2883174399, prov='28', city='226'}, elapsed: 8μs)
hit 183.39.148.212, IpRegion{begin=3072655360, end=3073114111, prov='4', city=''}, elapsed: 6μs)
```