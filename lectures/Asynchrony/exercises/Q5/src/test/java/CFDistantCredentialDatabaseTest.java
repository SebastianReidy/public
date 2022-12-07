import model.AlreadyExistsUserException;
import model.InvalidCredentialException;
import model.UnknownUserException;
import model.compfuture.CFCredentialDatabase;
import model.compfuture.CFDistantCredentialDatabase;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CFDistantCredentialDatabaseTest {
    // TODO add test for CompletableFuture DB here

    @Test
    public void AuthenticateUnknownUserThrowsUnknownUserException(){
        String user = "user";
        String pw = "pw";

        CFCredentialDatabase db = new CFDistantCredentialDatabase();

        var e = assertThrows(CompletionException.class, () -> db.authenticate(user,pw)
                .orTimeout(5, TimeUnit.SECONDS)
                .join());
        assertThat(e.getCause(), instanceOf(UnknownUserException.class)); // the top exception is a completion exception
    }

    @Test
    public void AddUserToDatabaseAddsCorrectUser(){
        var db = new CFDistantCredentialDatabase();
        var studentUser = db
                .addUser("user","pw",123)
                .orTimeout(5,TimeUnit.SECONDS)
                .join();

        assertThat(studentUser.getUserName(), is("user"));
        assertThat(studentUser.getID(), is("123"));
    }

    @Test
    public void AddSameUserTwiceThrowsAlreadyExistsUserException(){
        var db = new CFDistantCredentialDatabase();
        String user = "user";
        String pw = "pw";
        int id = 123;

        db.addUser(user,pw,id)
                .orTimeout(5,TimeUnit.SECONDS)
                .join();
        var e = assertThrows(CompletionException.class,
                () -> db.addUser(user,pw,id)
                        .orTimeout(5,TimeUnit.SECONDS)
                        .join());
        assertThat(e.getCause(), instanceOf(AlreadyExistsUserException.class));
    }

    @Test
    public void AuthenticateCorrectUserYieldsUser(){
        var db = new CFDistantCredentialDatabase();
        String user = "user";
        String pw = "pw";
        int id = 123;

        db.addUser(user,pw,id)
                .orTimeout(5,TimeUnit.SECONDS)
                .join();
        var studentUser = db.authenticate(user,pw)
                .orTimeout(5,TimeUnit.SECONDS)
                .join();
        assertThat(studentUser.getID(), is(Integer.toString(id)));
        assertThat(studentUser.getUserName(), is(user));
    }

    @Test
    public void AuthenticateWithWrongPasswordThrowsInvalidCredentialException(){
        var db = new CFDistantCredentialDatabase();
        String user = "user";
        String pw = "pw";
        int id = 123;

        db.addUser(user,pw,id)
                .orTimeout(5,TimeUnit.SECONDS)
                .join();
        var e = assertThrows(CompletionException.class,
                () -> db.authenticate(user,"pw1")
                .orTimeout(5,TimeUnit.SECONDS)
                .join());
        assertThat(e.getCause(),instanceOf(InvalidCredentialException.class));
    }
}
