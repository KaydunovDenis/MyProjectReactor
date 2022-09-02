package com.github.kaydunovdenis.springsupplier.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
import java.util.Set;

@Entity
@Table(name = "suppliers")//изначально Entity вяжется на таблицу 'supplier'
//@SelectBeforeUpdate - проверяет в кэше нужно ли обновить
public class Supplier {
    /**
     * GenerationType.IDENTITY  is a nice simple approach
     * There is yet another important runtime impact of choosing IDENTITY generation:
     * Hibernate will not be able to JDBC batching for inserts of the entities
     * (Hibernate не сможет выполнять пакетную обработку JDBC для вставок сущностей) that use IDENTITY generation.
     * If the application is not usually creating many new instances of a given type of entity that
     * uses IDENTITY generation, then this is not an important impact since batching would not have been helpful anyway.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    @OneToOne(
            cascade = CascadeType.ALL,//при сохранении/удалении Supplier каскадно удалить и адрес за ненужностью
//            orphanRemoval = true,// при удалении адреса у Supplier, он удалится полностью из базы
            fetch = FetchType.EAGER//EAGER стоит по дефолту для @OneToOne
    )
    //@JoinColumn вместо получения 'address_id' мы получаем объект Address
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<Order> orders;

    @ManyToMany(cascade = CascadeType.ALL)
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

    public Supplier(Long id, String name, Address address, Set<Order> orders, Set<Recipient> recipients) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
        this.recipients = recipients;
    }

    public static SupplierBuilder builder() {
        return new SupplierBuilder();
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<Recipient> recipients) {
        this.recipients = recipients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier supplier)) return false;

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
                ",\n name='" + name + '\'' +
                ",\n address=" + address +
                ",\n orders=" + orders +
                ",\n recipients=" + recipients +
                '}';
    }

    /**
     * Чтобы создать Builder автоматически можно использовать Refactor -> Delombok
     */
    public static class SupplierBuilder {
        private Long id;
        private String name;
        private Address address;
        private Set<Order> orders;
        private Set<Recipient> recipients;

        SupplierBuilder() {
        }

        public SupplierBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SupplierBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SupplierBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public SupplierBuilder orders(Set<Order> orders) {
            this.orders = orders;
            return this;
        }

        public SupplierBuilder recipients(Set<Recipient> recipients) {
            this.recipients = recipients;
            return this;
        }

        public Supplier build() {
            return new Supplier(id, name, address, orders, recipients);
        }

        public String toString() {
            return "Supplier.SupplierBuilder(id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", orders=" + this.orders + ", recipients=" + this.recipients + ")";
        }
    }
}
