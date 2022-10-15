package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "name")
    private String name;

    public Account(Long id, String accountNumber, String name) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
    }

    public Account(String accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
    }
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Expense> expenses;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Income> incomes;

    public Account(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", expenses=" + expenses +
                ", incomes=" + incomes +
                '}';
    }
}

