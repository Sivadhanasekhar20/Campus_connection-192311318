
import java.io.*;

2	+
import java.util.*;
3	+
4	+
class Student implements Serializable {
5	+
    int id;
6	+
    String name;
7	+
8	+
    Student(int i, String n) {
9	+
        id = i;
10	+
        name = n;
11	+
    }
12	+
13	+
    void show() {
14	+
        System.out.println(id + " " + name);
15	+
    }
16	+
}
17	+
18	+
public class StudentSystem {
19	+
    static ArrayList<Student> list = new ArrayList<>();
20	+
21	+
    public static void main(String[] args) throws Exception {
22	+
        load();
23	+
        Scanner sc = new Scanner(System.in);
24	+
25	+
        while (true) {
26	+
            System.out.println("1 Add 2 Update 3 Search 4 Delete 5 All 0 Exit");
27	+
            int ch = sc.nextInt();
28	+
29	+
            if (ch == 1) list.add(new Student(sc.nextInt(), sc.next()));
30	+
            else if (ch == 2) {
31	+
                int id = sc.nextInt();
32	+
                String n = sc.next();
33	+
                for (Student s : list) if (s.id == id) s.name = n;
34	+
            }
35	+
            else if (ch == 3) {
36	+
                int id = sc.nextInt();
37	+
                for (Student s : list) if (s.id == id) s.show();
38	+
            }
39	+
            else if (ch == 4) {
40	+
                int id = sc.nextInt();
41	+
                list.removeIf(s -> s.id == id);
42	+
            }
43	+
            else if (ch == 5) {
44	+
                for (Student s : list) s.show();
45	+
            }
46	+
            else break;
47	+
        }
48	+
        save();
49	+
    }
50	+
51	+
    static void save() throws Exception {
52	+
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("s.dat"));
53	+
        o.writeObject(list);
54	+
        o.close();
55	+
    }
56	+
57	+
    static void load() {
58	+
        try {
59	+
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("s.dat"));
60	+
            list = (ArrayList<Student>) o.readObject();
61	+
            o.close();
62	+
        } catch (Exception e) {}
63	+
    }
64	+
}
