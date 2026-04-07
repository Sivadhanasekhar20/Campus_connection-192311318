import java.io.*;

2	+
import java.util.*;
3	+
4	+
class Account implements Serializable {
5	+
    int accNo;
6	+
    String name;
7	+
    double balance;
8	+
    ArrayList<String> history = new ArrayList<>();
9	+
10	+
    Account(int accNo, String name, double balance) {
11	+
        this.accNo = accNo;
12	+
        this.name = name;
13	+
        this.balance = balance;
14	+
        history.add("Created with " + balance);
15	+
    }
16	+
17	+
    void deposit(double amt) {
18	+
        balance += amt;
19	+
        history.add("Deposit " + amt);
20	+
    }
21	+
22	+
    void withdraw(double amt) {
23	+
        if (amt > balance) {
24	+
            history.add("Failed Withdraw " + amt);
25	+
            System.out.println("Insufficient balance");
26	+
            return;
27	+
        }
28	+
        balance -= amt;
29	+
        history.add("Withdraw " + amt);
30	+
    }
31	+
32	+
    void showBalance() {
33	+
        System.out.println("Balance: " + balance);
34	+
    }
35	+
36	+
    void showHistory() {
37	+
        for (String s : history) System.out.println(s);
38	+
    }
39	+
}
40	+
41	+
public class BankSystem {
42	+
43	+
    static HashMap<Integer, Account> map = new HashMap<>();
44	+
    static final String FILE = "bank.dat";
45	+
46	+
    public static void main(String[] args) {
47	+
        load();
48	+
        Scanner sc = new Scanner(System.in);
49	+
50	+
        while (true) {
51	+
            System.out.println("\n1 Create 2 Deposit 3 Withdraw 4 Balance 5 History 6 Exit");
52	+
            int ch = sc.nextInt();
53	+
54	+
            try {
55	+
                if (ch == 1) {
56	+
                    int id = sc.nextInt();
57	+
                    String name = sc.next();
58	+
                    double bal = sc.nextDouble();
59	+
60	+
                    if (map.containsKey(id)) {
61	+
                        System.out.println("Account exists");
62	+
                        continue;
63	+
                    }
64	+
65	+
                    map.put(id, new Account(id, name, bal));
66	+
                }
67	+
                else if (ch == 2) {
68	+
                    int id = sc.nextInt();
69	+
                    double amt = sc.nextDouble();
70	+
71	+
                    Account a = map.get(id);
72	+
                    if (a != null) a.deposit(amt);
73	+
                    else System.out.println("Not found");
74	+
                }
75	+
                else if (ch == 3) {
76	+
                    int id = sc.nextInt();
77	+
                    double amt = sc.nextDouble();
78	+
79	+
                    Account a = map.get(id);
80	+
                    if (a != null) a.withdraw(amt);
81	+
                    else System.out.println("Not found");
82	+
                }
83	+
                else if (ch == 4) {
84	+
                    int id = sc.nextInt();
85	+
                    Account a = map.get(id);
86	+
                    if (a != null) a.showBalance();
87	+
                    else System.out.println("Not found");
88	+
                }
89	+
                else if (ch == 5) {
90	+
                    int id = sc.nextInt();
91	+
                    Account a = map.get(id);
92	+
                    if (a != null) a.showHistory();
93	+
                    else System.out.println("Not found");
94	+
                }
95	+
                else break;
96	+
97	+
            } catch (Exception e) {
98	+
                System.out.println("Invalid input");
99	+
                sc.nextLine();
100	+
            }
101	+
        }
102	+
103	+
        save();
104	+
    }
105	+
106	+
    static void save() {
107	+
        try {
108	+
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(FILE));
109	+
            o.writeObject(map);
110	+
            o.close();
111	+
        } catch (Exception e) {
112	+
            System.out.println("Save error");
113	+
        }
114	+
    }
115	+
116	+
    static void load() {
117	+
        try {
118	+
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(FILE));
119	+
            map = (HashMap<Integer, Account>) o.readObject();
120	+
            o.close();
121	+
        } catch (Exception e) {
122	+
            map = new HashMap<>();
123	+
        }
124	+
    }
125	+
}
