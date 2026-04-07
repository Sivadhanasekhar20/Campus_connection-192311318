import java.io.*;

2	+
import java.util.*;
3	+
4	+
public class AttendanceSystem {
5	+
    static HashMap<String, Integer> map = new HashMap<>();
6	+
    static int totalDays = 0;
7	+
8	+
    public static void main(String[] args) throws Exception {
9	+
        load();
10	+
        Scanner sc = new Scanner(System.in);
11	+
12	+
        while (true) {
13	+
            System.out.println("1 Mark 2 Report 3 Percentage 0 Exit");
14	+
            int ch = sc.nextInt();
15	+
16	+
            if (ch == 1) {
17	+
                totalDays++;
18	+
                String name = sc.next();
19	+
                map.put(name, map.getOrDefault(name, 0) + 1);
20	+
            }
21	+
            else if (ch == 2) {
22	+
                for (String k : map.keySet())
23	+
                    System.out.println(k + " " + map.get(k));
24	+
            }
25	+
            else if (ch == 3) {
26	+
                String n = sc.next();
27	+
                int p = map.getOrDefault(n, 0);
28	+
                System.out.println((p * 100) / totalDays);
29	+
            }
30	+
            else break;
31	+
        }
32	+
        save();
33	+
    }
34	+
35	+
    static void save() throws Exception {
36	+
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("att.dat"));
37	+
        o.writeObject(map);
38	+
        o.writeInt(totalDays);
39	+
        o.close();
40	+
    }
41	+
42	+
    static void load() {
43	+
        try {
44	+
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("att.dat"));
45	+
            map = (HashMap<String, Integer>) o.readObject();
46	+
            totalDays = o.readInt();
47	+
            o.close();
48	+
        } catch (Exception e) {}
49	+
    }
50	+
}
