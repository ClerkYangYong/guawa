package Guawa.Guawa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MutiFutureTaskTest {

    private static final Map<Integer, Person> persons = new HashMap<>();

    static {
        persons.put(1, new Person(1, "test1", 1, "aaa", 8888888888l));
        persons.put(2, new Person(2, "test2", 2, "bbb", 8888888888l));
        persons.put(3, new Person(3, "test2", 3, "bbb", 8888888888l));
        persons.put(4, new Person(4, "test2", 4, "bbb", 8888888888l));
        persons.put(5, new Person(5, "test2", 5, "bbb", 8888888888l));
    }

    public static void main(String[] args) {
        List<Integer> param = new ArrayList<Integer>();
        param.add(1);
        param.add(2);
        param.add(3);
        param.add(4);
        List<Person> result = MutiFutureTask.batchExec(param, v -> persons.get(v));
        System.out.println(123123);
        System.out.println(result.size());
    }



    static class Person {
        public Integer id;
        public String name;
        public Integer num;
        public String sname;
        public Long snum;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public Long getSnum() {
            return snum;
        }

        public void setSnum(Long snum) {
            this.snum = snum;
        }

        public Person(Integer id, String name, Integer num, String sname, Long snum) {
            this.id = id;
            this.name = name;
            this.num = num;
            this.sname = sname;
            this.snum = snum;
        }
    }
}
