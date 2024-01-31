package br.com.luna.banco.Dao;

import br.com.luna.banco.Modelo.AccountType;
import br.com.luna.banco.Modelo.Cliente;
import br.com.luna.banco.Modelo.Conta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ContaDAO {
    private EntityManager em;

    public ContaDAO(EntityManager em) {
        this.em = em;
    }
    public void createAccount(){
        Scanner sc = new Scanner(System.in);
        int a, b, c;
        String d, e;
        em.getTransaction().begin();

        System.out.println("Qual o valor do seu saldo? ");
        BigDecimal saldo = sc.nextBigDecimal();
        sc.nextLine();
        System.out.println("Insira o número da sua agência: ");
        a = sc.nextInt();
        sc.nextLine();
        System.out.println("Insira o número da sua conta: ");
        b = sc.nextInt();
        sc.nextLine();
        System.out.println("Qual o tipo de conta que você deseja criar? ");
        System.out.println("\n1- Corrente \n2- Poupança \n3- Digital");
        c = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o seu nome: ");
        d = sc.nextLine();
        sc.nextLine();
        System.out.println("Digite o seu CPF: ");
        e = sc.nextLine();
        sc.nextLine();

        if(c == 1){
            createAccount(new Conta(saldo, a, b, AccountType.CORRENTE,
                    new Cliente(d, e)));
            em.getTransaction().commit();
        } else if(c == 2){
            createAccount(new Conta(saldo, a, b, AccountType.POUPANÇA,
                    new Cliente(d, e)));
            em.getTransaction().commit();
        } else if (c == 3){
            createAccount(new Conta(saldo, a, b, AccountType.DIGITAL,
                    new Cliente(d, e)));
            em.getTransaction().commit();
        }

        sc.close();
    }

    public void createAccount(Conta conta){
        em.persist(conta);
    }

    public void deleteAccount(Conta conta){
        em.getTransaction().begin();
        em.merge(conta);
        em.remove(conta);
        em.getTransaction().commit();
    }

    public void deleteById(Long id){
        deleteAccount(searchById(id));
    }

    public Conta searchById (Long id){
        return em.find(Conta.class, id);
    }

    public List<Conta> searchByName(String nome){
        String jpql = "SELECT c FROM Conta c WHERE c.cliente.nome = :nome";
        return em.createQuery(jpql, Conta.class).setParameter("nome", nome).getResultList();
    }

    public BigDecimal searchBalanceByName(String nome){
        String jpql = "SELECT c.saldo FROM Conta c WHERE c.cliente.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
    }

    public List<Conta> searchByAccountType (AccountType accountType) {
        String jpql = "SELECT c FROM Conta c WHERE c.tipoConta = :accountType";
        return em.createQuery(jpql, Conta.class).setParameter("accountType", accountType).getResultList();
    }

    public List<Conta> readAllAccounts(){
        String jpql = "SELECT c FROM Conta AS c";
        return em.createQuery(jpql, Conta.class).getResultList();

    }
    public void updateAccount(Conta conta){
        em.merge(conta);
    }

    public void updateByID (Long id){
        Scanner sc = new Scanner(System.in);
        Conta conta = searchById(id);
        em.getTransaction().begin();

        System.out.println("================================");
        System.out.println("Você deseja atualizar qual dado?");
        System.out.println("\n1- Saldo \n2- Agência \n3- Número \n4- Tipo da Conta");
        System.out.println("================================");
        int op = sc.nextInt();

        switch (op){
            case 1:
                em.merge(conta);
                System.out.println("Insira o novo valor: ");
                BigDecimal saldo = sc.nextBigDecimal();
                conta.setSaldo(new BigDecimal(saldo.toString()));
                em.getTransaction().commit();
                break;
            case 2:
                em.merge(conta);
                System.out.println("Insira o novo valor: ");
                int agencia = sc.nextInt();
                conta.setAgencia(agencia);
                em.getTransaction().commit();
                break;
            case 3:
                em.merge(conta);
                System.out.println("Insira o novo valor: ");
                int numero = sc.nextInt();
                conta.setNumero(numero);
                em.getTransaction().commit();
                break;
            case 4:
                System.out.println("Para qual tipo de conta você quer atualizar? ");
                System.out.println("\n1- Corrente \n2- Poupança \n3- Digital");
                int tipo = sc.nextInt();

                if(tipo == 1){
                    em.merge(conta);
                    conta.setTipoConta(AccountType.CORRENTE);
                    em.getTransaction().commit();
                } else if(tipo == 2){
                    em.merge(conta);
                    conta.setTipoConta(AccountType.POUPANÇA);
                    em.getTransaction().commit();
                } else if (tipo == 3){
                    em.merge(conta);
                    conta.setTipoConta(AccountType.DIGITAL);
                    em.getTransaction().commit();
                } else {
                    System.out.println("Insira um dos três valores");
                }
                break;
        }

        sc.close();
    }
}
