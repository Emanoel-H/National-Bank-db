package br.com.luna.banco.Testes;

import br.com.luna.banco.Dao.ClienteDAO;
import br.com.luna.banco.Dao.ContaDAO;
import br.com.luna.banco.Modelo.AccountType;
import br.com.luna.banco.Modelo.Cliente;
import br.com.luna.banco.Modelo.Conta;
import br.com.luna.banco.Util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeConta {

    public static void main(String[] args){
//        cadastrarConta();
        EntityManager em = JPAUtil.getEntityManager();
        ContaDAO contaDAO = new ContaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
//        contaDAO.updateByID(1l);
//        clienteDAO.updateById(14l);
//        Cliente c1 = clienteDAO.searchById(14l);
//        Conta carlos = contaDAO.searchById(1l);


//        System.out.println("Nome: " + carlos.getCliente().getNome()
//                + "\n CPF: " + carlos.getCliente().getCpf());

//        List<Conta> searchAll = contaDAO.readAllAccounts();
//        searchAll.forEach(conta -> System.out.println(conta.getCliente().getNome()));
//        System.out.println(contaDAO.searchByName("Jose"));
//        System.out.println(contaDAO.searchByAccountType(AccountType.DIGITAL));
        System.out.println(contaDAO.searchBalanceByName("Jose"));
    }

    private static void cadastrarConta() {
        Conta jose = new Conta(new BigDecimal("250"), 500, 8935, AccountType.DIGITAL,
                new Cliente("Jose", "18006509"));


        EntityManager em = JPAUtil.getEntityManager();

        ContaDAO contaDAO = new ContaDAO(em);
//        ClienteDAO clienteDAO = new ClienteDAO(em);


        em.getTransaction().begin();
        contaDAO.createAccount(jose);
//        clienteDAO.createClient(new Cliente("Carlos", "78953462"));
        em.getTransaction().commit();
        em.close();
    }
}
