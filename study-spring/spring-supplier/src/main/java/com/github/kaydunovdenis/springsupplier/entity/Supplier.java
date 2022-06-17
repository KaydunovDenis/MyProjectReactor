package com.github.kaydunovdenis.springsupplier.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //todo добавил каскад, это норм?
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "supplier_recipient",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id"))
    private Set<Recipient> recipients;

    /**
     * Конструктор по умолчанию необходимый Hibernate
     */
    protected Supplier() {
    }

    /**
     * Пользовательский конструктор создается, когда нам нужно создать новый экземпляр,
     * но у него еще нет идентификатора.
     */
    public Supplier(String name, Address address) {
        this.name = name;
        this.address = address;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<Recipient> recipients) {
        this.recipients = recipients;
    }

    //TODO check implementation
    //TODO Найти правила переопределения для ентити


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier)) return false;

        Supplier supplier = (Supplier) o;

        if (!getId().equals(supplier.getId())) return false;
        if (!getName().equals(supplier.getName())) return false;
        if (!getAddress().equals(supplier.getAddress())) return false;
        if (getOrders() != null ? !getOrders().equals(supplier.getOrders()) : supplier.getOrders() != null)
            return false;
        return getRecipients() != null ? getRecipients().equals(supplier.getRecipients()) : supplier.getRecipients() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + (getOrders() != null ? getOrders().hashCode() : 0);
        result = 31 * result + (getRecipients() != null ? getRecipients().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nSupplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", orders=" + orders +
                ", recipients=" + recipients +
                '}';
    }
}
