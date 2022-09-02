package com.github.kaydunovdenis.springsupplier.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "recipients")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(
            cascade = CascadeType.ALL,//при сохранении/удалении Recipient каскадно удалить адрес за ненужностью
            orphanRemoval = true// при удалении адреса у Recipient, он удалится полностью из базы
    )
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(mappedBy = "recipients")
    private Set<Supplier> suppliers;

    protected Recipient() {
    }

    public Recipient(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipient recipient)) return false;

        if (!getName().equals(recipient.getName())) return false;
        return getAddress() != null ? getAddress().equals(recipient.getAddress()) : recipient.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\n\tRecipient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
