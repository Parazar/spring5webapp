package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author annieBarrows = new Author("Annie", "Barrows");
        Book theTruth = new Book("The Truth According to Us", "45587");
        annieBarrows.getBooks().add(theTruth);
        theTruth.getAuthors().add(annieBarrows);

        authorRepository.save(annieBarrows);
        bookRepository.save(theTruth);

        Author maryAnnShaffer = new Author("May Ann", "Shaffer");
        Book potatoPeelPie = new Book("The Guernsey Literary and Potato Peel Pie Society", "1234500");
        maryAnnShaffer.getBooks().add(potatoPeelPie);
        potatoPeelPie.getAuthors().add(maryAnnShaffer);
        potatoPeelPie.getAuthors().add(annieBarrows);

        authorRepository.save(maryAnnShaffer);
        bookRepository.save(potatoPeelPie);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
    }
}
