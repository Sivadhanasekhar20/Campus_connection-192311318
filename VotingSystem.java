import java.io.*;

2	+
import java.util.*;
3	+
4	+
class Candidate implements Serializable {
5	+
    String name;
6	+
    int votes;
7	+
8	+
    Candidate(String n) {
9	+
        name = n;
10	+
    }
11	+
}
12	+
13	+
public class VotingSystem {
14	+
    static HashMap<String, Candidate> map = new HashMap<>();
15	+
    static HashSet<String> voted = new HashSet<>();
16	+
17	+
    public static void main(String[] args) throws Exception {
18	+
        load();
19	+
        Scanner sc = new Scanner(System.in);
20	+
21	+
        while (true) {
22	+
            System.out.println("1 Add 2 Vote 3 Result 4 Winner 5 Exit");
23	+
            int ch = sc.nextInt();
24	+
25	+
            if (ch == 1) {
26	+
                String n = sc.next();
27	+
                map.put(n, new Candidate(n));
28	+
            }
29	+
            else if (ch == 2) {
30	+
                String voter = sc.next();
31	+
                String cand = sc.next();
32	+
33	+
                if (voted.contains(voter)) continue;
34	+
35	+
                if (map.containsKey(cand)) {
36	+
                    map.get(cand).votes++;
37	+
                    voted.add(voter);
38	+
                }
39	+
            }
40	+
            else if (ch == 3) {
41	+
                for (Candidate c : map.values())
42	+
                    System.out.println(c.name + " " + c.votes);
43	+
            }
44	+
            else if (ch == 4) {
45	+
                Candidate w = null;
46	+
                for (Candidate c : map.values())
47	+
                    if (w == null || c.votes > w.votes) w = c;
48	+
                if (w != null) System.out.println(w.name);
49	+
            }
50	+
            else break;
51	+
        }
52	+
        save();
53	+
    }
54	+
55	+
    static void save() throws Exception {
56	+
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("vote.dat"));
57	+
        o.writeObject(map);
58	+
        o.writeObject(voted);
59	+
        o.close();
60	+
    }
61	+
62	+
    static void load() {
63	+
        try {
64	+
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("vote.dat"));
65	+
            map = (HashMap<String, Candidate>) o.readObject();
66	+
            voted = (HashSet<String>) o.readObject();
67	+
            o.close();
68	+
        } catch (Exception e) {}
69	+
    }
70	+
}
