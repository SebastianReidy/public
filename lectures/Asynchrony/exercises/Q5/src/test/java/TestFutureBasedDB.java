import model.InvalidCredentialException;
import model.UnknownUserException;
import model.UserAlreadyExistsException;
import model.compfuture.CFCredentialDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.CommunicationException;
import javax.xml.crypto.Data;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

public abstract class TestFutureBasedDB {

    private CFCredentialDatabase cfCredentialDatabase;

    public abstract CFCredentialDatabase getFutureBasedDatabase();

    @BeforeEach
    public void getDatabase(){
        cfCredentialDatabase = getFutureBasedDatabase();
    }

    @Test
    public void authenticateUnknownUserThrowsUnknownUserException(){
        var e = assertThrows(CompletionException.class, () ->
                cfCredentialDatabase.authenticate("user","pw")
                        .orTimeout(10, TimeUnit.SECONDS).join());
        assertThat(e.getCause(), instanceOf(UnknownUserException.class));
    }

    @Test
    public void addUserToEmptyDatabaseAddsCorrectUser(){
        var future = cfCredentialDatabase.addUser("user", "pw", 123);
        var studentUser = future.orTimeout(10,TimeUnit.SECONDS).join();
        assertThat(studentUser.getUserName(), is("user"));
        assertThat(studentUser.getID(), is("123"));
    }

    @Test
    public void addUserTwiceThrowsUserAlreadyExistsException(){
        var future1 = cfCredentialDatabase.addUser("user", "pw", 123);
        future1.orTimeout(10, TimeUnit.SECONDS).join();
        var future2 = cfCredentialDatabase.addUser("user", "pw", 123);
        var e = assertThrows(CompletionException.class, () ->
                future2.orTimeout(10,TimeUnit.SECONDS).join());
        assertThat(e.getCause(), instanceOf(UserAlreadyExistsException.class));
    }

    @Test
    public void authenticateUserInDatabaseReturnsUser(){
        cfCredentialDatabase.addUser("user", "pw", 123)
                .orTimeout(10, TimeUnit.SECONDS)
                .join();
        var studentUser = cfCredentialDatabase.authenticate("user", "pw")
                .orTimeout(10,TimeUnit.SECONDS)
                .join();
        assertThat(studentUser.getID(), is("123"));
        assertThat(studentUser.getUserName(), is("user"));
    }

    @Test
    public void authenticateUserWithWrongPasswordThrowsInvalidCredentialException(){
        cfCredentialDatabase.addUser("user", "pw", 123)
                .orTimeout(10, TimeUnit.SECONDS)
                .join();
        var e = assertThrows(CompletionException.class, () ->
                cfCredentialDatabase.authenticate("user", "wrongPW")
                        .orTimeout(10,TimeUnit.SECONDS)
                        .join());
        assertThat(e.getCause(), instanceOf(InvalidCredentialException.class));
    }

}
