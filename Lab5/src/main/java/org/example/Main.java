package org.example;

import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void wypiszWszystkich(EntityManager em) {
        wypisz(em, "SELECT a FROM Author a");
    }
    public static void wypiszBooks(EntityManager em, String query) {
        List<Book> books = em.createQuery(query, Book.class).getResultList();
        for (Book book : books) {
            System.out.println("ksiazka: " + book.getTitle());
        }
    }

    public static void wypiszAutors(EntityManager em, String query) {
        List<Author> a = em.createQuery(query, Author.class).getResultList();
        for (Author autor : a) {
            System.out.println("Author: " + autor.getName());
        }
    }

    public static void wypisz(EntityManager em, String query) {
        // pokaz wszystkich autorów
        List<Author> authors = em.createQuery(query, Author.class).getResultList();
        for (Author author : authors) {
            System.out.println("Autor: " + author.getName());
            for (Book book : author.getBooks()) {
                System.out.println("  - " + book.getTitle());
            }
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Author a = new Author();
        a.setName("Jan Kowalski");
        Author a1 = new Author();
        a1.setName("Adam Kowalski");

        Book b1 = new Book();
        b1.setTitle("Hibernate 101");
        b1.setAuthor(a);

        Book b2 = new Book();
        b2.setTitle("JPA");
        b2.setAuthor(a);

        a.getBooks().add(b1);
        a.getBooks().add(b2);

        em.persist(a);
        em.persist(a1);
        em.getTransaction().commit();

        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println("Wybiesz jedną z opcji: \n 1-Dodaj autora\n 2-Dodaj ksiażki\n 3-Usun autora\n 4-Usun ksiazke u autora\n 5-Wypisz wszystko\n 6-Zakoncz ");
            int option = s.nextInt();
            s.nextLine(); // enter

            switch (option){
                case 1:
                    System.out.println("Wypisz imie: ");
                    String name = s.nextLine();
                    Author nowy = new Author();
                    nowy.setName(name);
                    em.getTransaction().begin();
                    em.persist(nowy);
                    em.getTransaction().commit();
                    System.out.println("Dodano autora ");
                    break;
                case 2:
                    System.out.print("Podaj tytuł: ");
                    String title = s.nextLine();
                    wypiszAutors(em, "SELECT a FROM Author a");
                    System.out.print("Podaj ID autora, do którego przypisać książkę: ");
                    long authorId = s.nextLong();
                    s.nextLine(); // enter

                    Author foundAuthor = em.find(Author.class, authorId);
                    if (foundAuthor != null) {
                        Book newBook = new Book();
                        newBook.setTitle(title);
                        newBook.setAuthor(foundAuthor);
                        foundAuthor.getBooks().add(newBook);

                        em.getTransaction().begin();
                        em.persist(newBook);
                        em.getTransaction().commit();
                        System.out.println("Dodano książkę.");
                    } else {
                        System.out.println("Nie znaleziono autora.");
                    }
                    break;
                    case 3:
                        wypiszAutors(em, "SELECT a FROM Author a");
                        System.out.print("Podaj ID autora do usunięcia: ");
                        long id = s.nextLong();
                        s.nextLine(); // enter

                        Author toDelete = em.find(Author.class, id);
                        if (toDelete != null) {
                            em.getTransaction().begin();
                            em.remove(toDelete);
                            em.getTransaction().commit();
                            System.out.println("Usunięto autora.");
                        } else {
                            System.out.println("Nie znaleziono autora.");
                        }
                        break;
                    case 4:
                        wypiszBooks(em, "SELECT b FROM Book b");
                        System.out.print("Podaj ID książki do usunięcia: ");
                        long bookId = s.nextLong();
                        s.nextLine(); // enter

                        Book book = em.find(Book.class, bookId);
                        if (book != null) {
                            em.getTransaction().begin();
                            Author author = book.getAuthor();
                            if (author != null) {
                                author.getBooks().remove(book);
                            }
                            em.remove(book);
                            em.getTransaction().commit();
                            System.out.println("Usunięto książkę.");
                        } else {
                            System.out.println("Nie znaleziono książki.");
                        }
                        break;
                case 5:
                    wypiszWszystkich(em);
                    break;
                case 6:
                    em.getTransaction().begin();
                    em.remove(a);
                    em.getTransaction().commit();

                    em.close();
                    emf.close();
                    return;

                default:
                    System.out.println("Niezanan opcja");
            }
        }
//        wypiszWszystkich(em);
//        wypisz(em, "SELECT a FROM Author a WHERE name='Jan Kowalski'");
//        wypiszBooks(em, "SELECT a FROM Book a WHERE title='Hibernate 101'");
//        wypiszBooks(em, "SELECT a FROM Book a WHERE author.name='Jan Kowalski'");
//        wypiszBooks(em, "SELECT a FROM Book a WHERE title='JPA'");

//        em.getTransaction().begin();
//        em.remove(a);
//        em.getTransaction().commit();
//scazsvzsvz
//        em.close();
//        emf.close();
    }
}