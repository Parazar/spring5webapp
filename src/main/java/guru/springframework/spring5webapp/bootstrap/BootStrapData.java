package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher dialPress = new Publisher("Dial Press", "Somewhere", "New York City", "US", "Not a RAR");
        publisherRepository.save(dialPress);

        Author annieBarrows = new Author("Annie", "Barrows");
        Book theTruth = new Book("The Truth According to Us", "45587");
        annieBarrows.getBooks().add(theTruth);
        theTruth.getAuthors().add(annieBarrows);

        theTruth.setPublisher(dialPress);
        dialPress.getBooks().add(theTruth);

        authorRepository.save(annieBarrows);
        bookRepository.save(theTruth);
        publisherRepository.save(dialPress);

        Author maryAnnShaffer = new Author("May Ann", "Shaffer");
        Book potatoPeelPie = new Book("The Guernsey Literary and Potato Peel Pie Society", "1234500");
        maryAnnShaffer.getBooks().add(potatoPeelPie);
        potatoPeelPie.getAuthors().add(maryAnnShaffer);
        potatoPeelPie.getAuthors().add(annieBarrows);

        potatoPeelPie.setPublisher(dialPress);
        dialPress.getBooks().add(potatoPeelPie);

        authorRepository.save(maryAnnShaffer);
        bookRepository.save(potatoPeelPie);
        publisherRepository.save(dialPress);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
    }
}
