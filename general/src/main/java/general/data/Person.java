package general.data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Person class.
 */
public class Person  implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDateTime birthday; //Поле может быть null
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0
    private String passportID; //Поле может быть null

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public Long getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return passportID;
    }

    /**
     * Constructor, just set class
     * @param nm Name
     * @param bth Birthday
     * @param wdt Weight
     * @param passId Passport ID
     */
    public Person(String nm, LocalDateTime bth, Long wdt, String passId){
        name = nm;
        birthday = bth;
        weight = wdt;
        passportID = passId;
    }

    @Override
    public String toString() {
        return "{" +
                "имя = '" + name + '\'' +
                ", День рождения = " + birthday.toString() +
                ", Вес = " + weight.toString() +
                ", Номер паспорта = '" + passportID + '\'' +
                '}';
    }
}
