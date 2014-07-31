package dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class User {

    public User(Map params) {

        for(Object key: params.keySet()) {

            String[] value = (String[]) params.get(key);

            if (value[0].length() != 0) {

                if (key.equals("firstname")) {
                    setFirstName(value[0]);
                } else {
                    if (key.equals("lastname")) {
                        setLastName(value[0]);
                    } else {
                        if (key.equals("age")) {
                            try {
                                setAge(Integer.parseInt(value[0]));
                            } catch (NumberFormatException e){
                                setAge(null);
                            }
                        } else {
                            if (key.equals("sex")) {
                                setSex(value[0].toCharArray()[0]);
                            } else {
                                if (key.equals("id")){
                                    try {
                                        setId(Integer.parseInt(value[0]));
                                    } catch (NumberFormatException e){
                                        setId(null);
                                    }
                                }else{
                                    setPhone(value[0]);
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String validate(){

        String msg = "";

        if (getFirstName().length() < 5){
            msg = msg + "You have entered too short first name! Please, enter at least 5 symbols\n";
        }

        if (getLastName().length() < 5){
            msg = msg + "You have entered too short last name! Please, enter at least 5 symbols\n";
        }

        if(getAge() == null){
            msg = msg + "You have entered not a valid number value for age field! Please, correct number at age field\n";
        }

        String pattern = "^\\+\\([0-9][0-9][0-9]\\) [0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]$";

        System.out.println(getPhone());
        System.out.println(getPhone().matches(pattern));

        if (! getPhone().matches(pattern)){
            msg = msg + "You have entered not a valid phone number! Please, phone number must be entered in '+(xxx) xxx-xx-xx' format\n";
        }

        if (msg.isEmpty()) return null;
        else return msg;
    }

    Integer id = null;
    String lastName = null;
    String firstName = null;
    Integer age = null;
    Character sex = null;
    String phone = null;
}
