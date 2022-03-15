package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplementationTest {

    private UserRepository userRepository;

    @BeforeEach
    void beforeEachTest() {
        userRepository = new UserRepositoryImplementation();
    }

    @Test
    void saveToRepositoryTest() {
        User owner = new User();
        userRepository.save(owner, "anthonystark3000@gmail.com");
        assertEquals(1, userRepository.count());
        assertEquals("anthonystark3000@gmail.com", owner.getEmail());
    }

    @Test
    void findByEmailRepoTest(){
        User owner1 = new User();
        User owner2 = new User();
        User owner3 = new User();

        userRepository.save(owner1, "blueridgemountain@gmail.com");
        userRepository.save(owner2, "esquire@gmail.com");
        userRepository.save(owner3, "the-unspeakable@gmail.com");

        assertEquals(3, userRepository.count());
        assertEquals(owner2, userRepository.findByEmail("esquire@gmail.com"));
    }

    @Test
    void deleteByEmailFromRepoTest(){
        User owner1 = new User();
        User owner2 = new User();
        User owner3 = new User();

        userRepository.save(owner1, "blueridgemountain@gmail.com");
        userRepository.save(owner2, "esquire@gmail.com");
        userRepository.save(owner3, "the-unspeakable@gmail.com");

        userRepository.delete("the-unspeakable@gmail.com");

        assertEquals(2, userRepository.count());
    }

    @Test
    void findByEmailWorks_AfterADeleteTest(){
        User owner1 = new User();
        User owner2 = new User();
        User owner3 = new User();

        userRepository.save(owner1, "blueridgemountain@gmail.com");
        userRepository.save(owner2, "esquire@gmail.com");
        userRepository.save(owner3, "the-unspeakable@gmail.com");

        userRepository.delete("blueridgemountain@gmail.com");
        User foundOwner = userRepository.findByEmail("blueridgemountain@gmail.com");
        assertNull(foundOwner);
    }

    @Test
    void deleteByUserTest(){
        User owner1 = new User();
        User owner2 = new User();
        User owner3 = new User();

        userRepository.save(owner1, "blueridgemountain@gmail.com");
        userRepository.save(owner2, "esquire@gmail.com");
        userRepository.save(owner3, "the-unspeakable@gmail.com");

        userRepository.delete(owner1);

        assertEquals(2, userRepository.count());
        assertNull(userRepository.findByEmail("blueridgemountain@gmail.com"));
    }

    @Test
    void findAllUsersTest(){
        User owner1 = new User();
        User owner2 = new User();
        User owner3 = new User();

        userRepository.save(owner1, "blueridgemountain@gmail.com");
        userRepository.save(owner2, "esquire@gmail.com");
        userRepository.save(owner3, "the-unspeakable@gmail.com");

        var all = userRepository.findAll();

        assertEquals(3, all.size());
    }
}