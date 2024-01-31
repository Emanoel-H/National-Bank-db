package br.com.luna.banco.Modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal saldo;
    private int agencia;
    private int numero;
    private LocalDate dataCadastro = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private AccountType tipoConta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientes_id", referencedColumnName = "id")
    private Cliente cliente;

    public Conta() {

    }

    public Conta(BigDecimal saldo, int agencia, int numero, AccountType tipoConta, Cliente cliente) {
        this.saldo = saldo;
        this.agencia = agencia;
        this.numero = numero;
        this.tipoConta = tipoConta;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public AccountType getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(AccountType tipoConta) {
        this.tipoConta = tipoConta;
    }

    @Override
    public String toString() {
        return  "Id: " + id + " Numero: " + numero + " Agência: " + agencia +
                " Tipo da Conta: " + tipoConta + " Titular: " + getCliente().getNome() + "\n";
    }
}
