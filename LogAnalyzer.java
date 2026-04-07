import java.io.*;

2	+
import java.util.*;
3	+
4	+
public class LogAnalyzer {
5	+
6	+
    static ArrayList<String> logs = new ArrayList<>();
7	+
8	+
    public static void main(String[] args) throws Exception {
9	+
        Scanner sc = new Scanner(System.in);
10	+
11	+
        loadLogs();
12	+
13	+
        while (true) {
14	+
            System.out.println("\n1 Show All 2 Count Errors 3 Filter Keyword 4 Search Date 5 Summary 0 Exit");
15	+
            int ch = sc.nextInt();
16	+
            sc.nextLine();
17	+
18	+
            if (ch == 1) {
19	+
                for (String l : logs) System.out.println(l);
20	+
            }
21	+
            else if (ch == 2) {
22	+
                int err = 0, warn = 0;
23	+
24	+
                for (String l : logs) {
25	+
                    if (l.contains("ERROR")) err++;
26	+
                    if (l.contains("WARN")) warn++;
27	+
                }
28	+
29	+
                System.out.println("ERROR: " + err);
30	+
                System.out.println("WARN: " + warn);
31	+
            }
32	+
            else if (ch == 3) {
33	+
                String key = sc.nextLine();
34	+
35	+
                for (String l : logs)
36	+
                    if (l.toLowerCase().contains(key.toLowerCase()))
37	+
                        System.out.println(l);
38	+
            }
39	+
            else if (ch == 4) {
40	+
                String date = sc.nextLine();
41	+
42	+
                for (String l : logs)
43	+
                    if (l.startsWith(date))
44	+
                        System.out.println(l);
45	+
            }
46	+
            else if (ch == 5) {
47	+
                HashMap<String, Integer> map = new HashMap<>();
48	+
49	+
                for (String l : logs) {
50	+
                    String[] parts = l.split(" ");
51	+
                    if (parts.length > 2) {
52	+
                        String type = parts[2];
53	+
                        map.put(type, map.getOrDefault(type, 0) + 1);
54	+
                    }
55	+
                }
56	+
57	+
                for (String k : map.keySet())
58	+
                    System.out.println(k + " " + map.get(k));
59	+
            }
60	+
            else break;
61	+
        }
62	+
    }
63	+
64	+
    static void loadLogs() {
65	+
        try {
66	+
            BufferedReader br = new BufferedReader(new FileReader("log.txt"));
67	+
            String line;
68	+
69	+
            while ((line = br.readLine()) != null)
70	+
                logs.add(line);
71	+
72	+
            br.close();
73	+
        } catch (Exception e) {
74	+
            System.out.println("no file");
75	+
        }
76	+
    }
77	+
}
