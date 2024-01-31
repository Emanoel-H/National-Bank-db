package br.com.luna.banco.Dao;

import br.com.luna.banco.Modelo.Cliente;


import javax.persistence.EntityManager;
import java.util.Scanner;

public class ClienteDAO {
    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }
    public void createClient(Cliente cliente){
        em.persist(cliente);
    }

    public void updateById(Long id){
        Scanner sc = new Scanner(System.in);
        Cliente cliente = searchById(id);
        em.getTransaction().begin();
        String a;

        System.out.println("================================");
        System.out.println("Você deseja atualizar qual dado?");
        System.out.println("\n1- Nome \n2- CPF ");
        System.out.println("================================");
        int op = sc.nextInt();

        switch (op){
            case 1:
                em.merge(cliente);
                System.out.println("Insira o novo nome: ");
                a = sc.nextLine();
                sc.nextLine();
                cliente.setNome(a);
                em.getTransaction().commit();
                sc.close();
                break;
            case 2:
                em.merge(cliente);
                System.out.println("Insira o novo cpf: ");
                a = sc.nextLine();
                sc.nextLine();
                cliente.setCpf(a);
                em.getTransaction().commit();
                sc.close();
                break;
        }
    }
    public void deleteClient(Cliente cliente){
        em.getTransaction().begin();
        em.merge(cliente);
        em.remove(cliente);
        em.getTransaction().commit();
    }

    public void deleteById(Long id){
        deleteClient(searchById(id));
    }

    public Cliente searchById(Long id){
        return em.find(Cliente.class, id);
    }
}
