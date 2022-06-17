package com.github.kaydunovdenis.springsupplier.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(mappedBy = "recipients")
    private Set<Supplier> suppliers;

    protected Recipient() {
    }

    public Recipient(Long id, String name, Address address, Set<Supplier> suppliers) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.suppliers = suppliers;
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
        if (!(o instanceof Recipient)) return false;

        Recipient recipient = (Recipient) o;

        if (!getId().equals(recipient.getId())) return false;
        if (!getName().equals(recipient.getName())) return false;
        if (getAddress() != null ? !getAddress().equals(recipient.getAddress()) : recipient.getAddress() != null)
            return false;
        return getSuppliers() != null ? getSuppliers().equals(recipient.getSuppliers()) : recipient.getSuppliers() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getSuppliers() != null ? getSuppliers().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nRecipient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
