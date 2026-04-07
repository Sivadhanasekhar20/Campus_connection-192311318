import java.io.*;

2	+
import java.util.*;
3	+
4	+
class Patient implements Serializable {
5	+
    int id;
6	+
    String name;
7	+
    String record;
8	+
    ArrayList<String> history = new ArrayList<>();
9	+
10	+
    Patient(int id, String name, String record) {
11	+
        this.id = id;
12	+
        this.name = name;
13	+
        this.record = record;
14	+
        history.add("Created: " + record);
15	+
    }
16	+
17	+
    void update(String r) {
18	+
        record = r;
19	+
        history.add("Updated: " + r);
20	+
    }
21	+
22	+
    void show() {
23	+
        System.out.println(id + " " + name + " " + record);
24	+
    }
25	+
26	+
    void showHistory() {
27	+
        for (String h : history) System.out.println(h);
28	+
    }
29	+
}
30	+
31	+
public class PatientSystem {
32	+
    static ArrayList<Patient> list = new ArrayList<>();
33	+
    static final String FILE = "patient.dat";
34	+
35	+
    public static void main(String[] args) {
36	+
        load();
37	+
        Scanner sc = new Scanner(System.in);
38	+
39	+
        while (true) {
40	+
            System.out.println("1 Add 2 Search 3 Update 4 Delete 5 History 0 Exit");
41	+
            int ch = sc.nextInt();
42	+
43	+
            if (ch == 1) {
44	+
                list.add(new Patient(sc.nextInt(), sc.next(), sc.next()));
45	+
            }
46	+
            else if (ch == 2) {
47	+
                int id = sc.nextInt();
48	+
                for (Patient p : list) if (p.id == id) p.show();
49	+
            }
50	+
            else if (ch == 3) {
51	+
                int id = sc.nextInt();
52	+
                String r = sc.next();
53	+
                for (Patient p : list) if (p.id == id) p.update(r);
54	+
            }
55	+
            else if (ch == 4) {
56	+
                int id = sc.nextInt();
57	+
                list.removeIf(p -> p.id == id);
58	+
            }
59	+
            else if (ch == 5) {
60	+
                int id = sc.nextInt();
61	+
                for (Patient p : list) if (p.id == id) p.showHistory();
62	+
            }
63	+
            else break;
64	+
        }
65	+
        save();
66	+
    }
67	+
68	+
    static void save() {
69	+
        try {
70	+
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(FILE));
71	+
            o.writeObject(list);
72	+
            o.close();
73	+
        } catch (Exception e) {}
74	+
    }
75	+
76	+
    static void load() {
77	+
        try {
78	+
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(FILE));
79	+
            list = (ArrayList<Patient>) o.readObject();
80	+
            o.close();
81	+
        } catch (Exception e) {}
82	+
    }
83	+
}
