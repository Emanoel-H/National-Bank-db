package br.com.luna.banco.Testes;

import br.com.luna.banco.Dao.ContaDAO;
import br.com.luna.banco.Modelo.AccountType;
import br.com.luna.banco.Util.JPAUtil;

import javax.persistence.EntityManager;

public class NationalBankTest {

    public static void main (String[] args){
        EntityManager em = JPAUtil.getEntityManager();
        ContaDAO dao = new ContaDAO(em);
        // Create
            dao.createAccount();

        // Read
        System.out.println(dao.readAllAccounts());
        System.out.println(dao.searchByName("Emanoel"));
        System.out.println(dao.searchByAccountType(AccountType.CORRENTE));
        System.out.println(dao.searchBalanceByName("Catarina"));

        // Update
        dao.updateByID(14l);
        System.out.println(dao.searchById(14l));

        // Delete
            dao.updateByID(13l);
            System.out.println(dao.readAllAccounts());

            em.close();
    }
}
