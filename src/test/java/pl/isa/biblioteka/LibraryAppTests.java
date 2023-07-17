package pl.isa.biblioteka;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryAppTests {


//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    PersonDAO personDAO;
//
//    @Autowired
//    BookDAO bookDAO;
//
//    @Autowired
//    PersonService personService;
//
//    @Autowired
//    BookService bookService;
//
//    @Autowired
//    BookRepository bookRepository;


//    @Test
//    void readAndSavePerson() {
//        List<User> people = PersonService.readUsers();
//        for (User user : people) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            user.setRole("ROLE_ADMIN");
//            personDAO.savePerson(user);
//        }
//    }

//    @Test
//    void readAndSaveBook() {
//        List<Book> books = BookRepository.readBooks();
//        for (Book book : books) {
//            Random random = new Random();
//            int r = random.nextInt(300);
//            book.setCounter(r);
//            bookDAO.saveBook(book);
//        }
//    }

//    @Test
//    void sampelReadAndSaveBook() {
//        List<Book> books = BookRepository.sampelReadBooks();
//        for (Book book : books) {
//            Random random = new Random();
//            int r = random.nextInt(100);
//            book.setCounter(r);
//            System.out.println("r = " + r);
//            bookDAO.saveBook(book);
//        }
//    }


//    @Test
//    void savePerson() {
//        User user = new User(
//
//                "KamilLogin",
//                "KamilPass",
//                "Kamil",
//                "Koz",
//                "k@k.pl");
//        personDAO.savePerson(user);
//    }


//    @Test
//    void saveBook() {
//        var book = new Book(
//                "ISA-Junit",
//                "TomaszD",
//                "Nauka");
//        bookDAO.saveBook(book);
//    }


//    @Test
//    void contextLoads() {
//    }
}
