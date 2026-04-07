
import java.io.*;

2	+
import java.util.*;
3	+
4	+
class Appointment implements Serializable {
5	+
    String time;
6	+
    String name;
7	+
8	+
    Appointment(String t, String n) {
9	+
        time = t;
10	+
        name = n;
11	+
    }
12	+
}
13	+
14	+
public class AppointmentSystem {
15	+
    static ArrayList<Appointment> list = new ArrayList<>();
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
            System.out.println("1 Book 2 Cancel 3 View 0 Exit");
23	+
            int ch = sc.nextInt();
24	+
25	+
            if (ch == 1) {
26	+
                String t = sc.next();
27	+
                boolean ok = true;
28	+
29	+
                for (Appointment a : list)
30	+
                    if (a.time.equals(t)) ok = false;
31	+
32	+
                if (ok) list.add(new Appointment(t, sc.next()));
33	+
                else System.out.println("Busy");
34	+
            }
35	+
            else if (ch == 2) {
36	+
                String t = sc.next();
37	+
                list.removeIf(a -> a.time.equals(t));
38	+
            }
39	+
            else if (ch == 3) {
40	+
                for (Appointment a : list)
41	+
                    System.out.println(a.time + " " + a.name);
42	+
            }
43	+
            else break;
44	+
        }
45	+
        save();
46	+
    }
47	+
//https://github.com/Ravi123sv-----
48	+
    static void save() throws Exception {
49	+
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("a.dat"));
50	+
        o.writeObject(list);
51	+
        o.close();
52	+
    }
53	+
54	+
    static void load() {
55	+
        try {
56	+
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("a.dat"));
57	+
            list = (ArrayList<Appointment>) o.readObject();
58	+
            o.close();
59	+
        } catch (Exception e) {}
60	+
    }
61	+
}
