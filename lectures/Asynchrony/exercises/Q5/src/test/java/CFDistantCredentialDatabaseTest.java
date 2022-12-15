import model.compfuture.CFCredentialDatabase;
import model.compfuture.CFDistantCredentialDatabase;
import org.junit.jupiter.api.Test;

public class CFDistantCredentialDatabaseTest extends TestFutureBasedDB {
    // TODO add test for CompletableFuture DB here

    @Override
    public CFCredentialDatabase getFutureBasedDatabase() {
        return new CFDistantCredentialDatabase();
    }
}
