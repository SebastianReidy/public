import model.CredentialDatabaseCallback;
import model.DatabaseException;
import model.StudentUser;
import model.callback.CBCredentialDatabase;
import model.compfuture.CFCredentialDatabase;

import java.util.concurrent.CompletableFuture;

public class CallbackToFutureBasedDBAdapter implements CFCredentialDatabase {

    private CBCredentialDatabase cbCredentialDatabase;

    CallbackToFutureBasedDBAdapter(CBCredentialDatabase cbCredentialDatabase){
        this.cbCredentialDatabase = cbCredentialDatabase;
    }
    @Override
    public CompletableFuture<StudentUser> addUser(String userName, String userPassword, int id) {
        CompletableFuture<StudentUser> future = new CompletableFuture<>();
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
        cbCredentialDatabase.addUser(userName, userPassword, id, callback);
        return future;
    }

    @Override
    public CompletableFuture<StudentUser> authenticate(String userName, String userPassword) {
        CompletableFuture<StudentUser> future = new CompletableFuture<>();
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
        cbCredentialDatabase.authenticate(userName, userPassword, callback);
        return future;
    }
}
