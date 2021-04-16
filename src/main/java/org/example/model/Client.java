package org.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Andrievskiy Ilia
 */
@Entity
@Table(name="clients")
public class Client implements Serializable {
    private static final long serialVersionUID = -4276582812268399483L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String patronymic;

    @Column
    private String address;

    @Column
    private int age;

    @OneToMany (mappedBy = "client", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Account> Account;

    public Client(){

    }

    public Client(long id, String name, String surname, String patronymic, String address, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = address;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
