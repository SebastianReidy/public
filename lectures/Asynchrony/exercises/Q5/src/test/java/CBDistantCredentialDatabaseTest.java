import model.*;
import model.callback.CBCredentialDatabase;
import model.callback.CBDistantCredentialDatabase;
import model.compfuture.CFCredentialDatabase;
import model.compfuture.CFDistantCredentialDatabase;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.*;

public class CBDistantCredentialDatabaseTest {
    // TODO add test for Thread DB here
    // SOLUTION: could have made an class for the callback to reduce duplicate code
    // SOLUTION: could have made an adapter for to use the tests of the future based database
    //              for that we can make the future based tests an abstract class and use a method to give the future
    //              based database
    @Test
    public void AuthenticateUnknownUserThrowsUnknownUserException(){

        final CompletableFuture<StudentUser> future = new CompletableFuture<StudentUser>();
        CredentialDatabaseCallback callback = new CredentialDatabaseCallback() {
            @Override
            public void onSuccess(StudentUser user) {
                future.complete(user);
            }

            @Override
            public void onError(DatabaseException exception) {
                future.completeExceptionally(exception);
            }
        };

        CBDistantCredentialDatabase db = new CBDistantCredentialDatabase();

        db.authenticate("user", "pw", callback);

        var e = assertThrows(CompletionException.class,
                () -> future.orTimeout(10,TimeUnit.SECONDS).join());
        assertThat(e.getCause(), instanceOf(UnknownUserException.class));
    }

    @Test
    public void AddUserToDatabaseAddsCorrectUser(){
        final CompletableFuture<StudentUser> future = new CompletableFuture<StudentUser>();
        CredentialDatabaseCallback callback = new CredentialDatabaseCallback() {
            @Override
            public void onSuccess(StudentUser user) {
                future.complete(user);
            }

            @Override
            public void onError(DatabaseException exception) {
                future.completeExceptionally(exception);
            }
        };

        CBDistantCredentialDatabase db = new CBDistantCredentialDatabase();

        String user = "user";
        String pw = "pw";
        int id = 123;

        db.addUser(user,pw,id,callback);

        assertThat(future.orTimeout(10,TimeUnit.SECONDS).join().getUserName(), is(user));
        assertThat(future.orTimeout(10,TimeUnit.SECONDS).join().getID(), is(Integer.toString(id)));

    }

    @Test
    public void AddingSameUserTwiceThrowsAlreadyExistsUserException(){
        final CompletableFuture<StudentUser> future1 = new CompletableFuture<StudentUser>();
        final CompletableFuture<StudentUser> future2 = new CompletableFuture<StudentUser>();
        CredentialDatabaseCallback callback1 = new CredentialDatabaseCallback() {
            @Override
            public void onSuccess(StudentUser user) {
                future1.complete(user);
            }

            @Override
            public void onError(DatabaseException exception) {
                future1.completeExceptionally(exception);
            }
        };

        CredentialDatabaseCallback callback2 = new CredentialDatabaseCallback() {
            @Override
            public void onSuccess(StudentUser user) {
                future2.complete(user);
            }

            @Override
            public void onError(DatabaseException exception) {
                future2.completeExceptionally(exception);
            }
        };

        CBDistantCredentialDatabase db = new CBDistantCredentialDatabase();

        String user = "user";
        String pw = "pw";
        int id = 123;

        db.addUser(user,pw,id,callback1);
        future1.orTimeout(10,TimeUnit.SECONDS).join();  // somehow doesn't work w/o this line;
        // we have to wait until the insertion of the first user is completed
        db.addUser(user,pw,id,callback2);

        var e = assertThrows(CompletionException.class,
                () -> future2.orTimeout(10,TimeUnit.SECONDS).join());
        assertThat(e.getCause(), instanceOf(AlreadyExistsUserException.class));

    }

    @Test
    public void AddAndAuthenticateUserYieldsCorrectUser(){
        final CompletableFuture<StudentUser> future1 = new CompletableFuture<StudentUser>();
        final CompletableFuture<StudentUser> future2 = new CompletableFuture<StudentUser>();
        CredentialDatabaseCallback callback1 = new CredentialDatabaseCallback() {
            @Override
            public void onSuccess(StudentUser user) {
                future1.complete(user);
            }

            @Override
            public void onError(DatabaseException exception) {
                future1.completeExceptionally(exception);
            }
        };

        CredentialDatabaseCallback callback2 = new CredentialDatabaseCallback() {
            @Override
            public void onSuccess(StudentUser user) {
                future2.complete(user);
            }

            @Override
            public void onError(DatabaseException exception) {
                future2.completeExceptionally(exception);
            }
        };

        CBDistantCredentialDatabase db = new CBDistantCredentialDatabase();

        String user = "user";
        String pw = "pw";
        int id = 123;

        db.addUser(user,pw,id, callback1);
        future1.orTimeout(10,TimeUnit.SECONDS).join();
        db.authenticate(user,pw,callback2);
        var studentUser = future2.orTimeout(10,TimeUnit.SECONDS).join();

        assertThat(studentUser.getUserName(), is(user));
        assertThat(studentUser.getID(), is(Integer.toString(id)));
    }

    @Test
    public void AuthenticateExistingUserWithInvalidCredentialsThrowsInvalidCredentialsException(){
        final CompletableFuture<StudentUser> future1 = new CompletableFuture<StudentUser>();
        final CompletableFuture<StudentUser> future2 = new CompletableFuture<StudentUser>();
        CredentialDatabaseCallback callback1 = new CredentialDatabaseCallback() {
            @Override
            public void onSuccess(StudentUser user) {
                future1.complete(user);
            }

            @Override
            public void onError(DatabaseException exception) {
                future1.completeExceptionally(exception);
            }
        };

        CredentialDatabaseCallback callback2 = new CredentialDatabaseCallback() {
            @Override
            public void onSuccess(StudentUser user) {
                future2.complete(user);
            }

            @Override
            public void onError(DatabaseException exception) {
                future2.completeExceptionally(exception);
            }
        };

        CBDistantCredentialDatabase db = new CBDistantCredentialDatabase();

        String user = "user";
        String pw = "pw";
        int id = 123;

        db.addUser(user,pw,id, callback1);
        future1.orTimeout(10,TimeUnit.SECONDS).join();
        db.authenticate(user,"1",callback2);
        var e = assertThrows(CompletionException.class,
                () -> future2.orTimeout(10,TimeUnit.SECONDS).join());
        assertThat(e.getCause(),instanceOf(InvalidCredentialException.class));

    }

}
