package Guawa.Guawa;

import com.google.common.base.Charsets;
import com.google.common.hash.*;

import java.util.Objects;

/**
 * 散列
 * 按照谷歌的说法，java中的hashCode是劣质的，因为依赖的JDK中的类是个劣质散列码.
 * 原因：java内建的散列码被限制为32位，而且没有分离散列算法和所作用的数据，所以替代算法比较难做
 */
public class Hash {

    public static void main(String[] args) {
        HashFunction function_0 = Hashing.md5();
        HashFunction function_1 = Hashing.murmur3_128();
        Hasher hasher_0 = function_0.newHasher();
        Hasher hasher_1 = function_1.newHasher();

        Person person = new Person();
        person.setAge(27);
        person.setName("hahahah");
        person.setAddress("北京三里屯");
        person.setPhoneNumber(16666666666L);
        person.setMale(Male.man);

        HashCode code_0 = hasher_0.putInt(person.getAge())
                .putString(person.getName(), Charsets.UTF_8)
                .putString(person.getAddress(), Charsets.UTF_8)
                .putLong(person.getPhoneNumber())
                .putObject(person.getMale(), new Funnel<Male>() {
                    @Override
                    public void funnel(Male from, PrimitiveSink into) {
                        into.putString(from.name(), Charsets.UTF_8);
                    }
                }).hash();
        HashCode code_1 = hasher_1.putInt(person.getAge())
                .putString(person.getName(), Charsets.UTF_8)
                .putString(person.getAddress(), Charsets.UTF_8)
                .putLong(person.getPhoneNumber())
                .putObject(person.getMale(), new Funnel<Male>() {
                    @Override
                    public void funnel(Male from, PrimitiveSink into) {
                        into.putString(from.name(), Charsets.UTF_8);
                    }
                }).hash();
        System.out.println(code_0.asLong());
        System.out.println(code_1.asLong());
    }


    public enum Male {
        man, woman;
    }

    public static class Person {
        int age;
        String name;
        String address;
        long phoneNumber;
        Male male;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(long phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Male getMale() {
            return male;
        }

        public void setMale(Male male) {
            this.male = male;
        }
    }

}
