import model.callback.CBDistantCredentialDatabase;
import model.compfuture.CFCredentialDatabase;

public class CBDistantCredentialDatabaseTest extends TestFutureBasedDB{
    @Override
    public CFCredentialDatabase getFutureBasedDatabase() {
        return new CallbackToFutureBasedDBAdapter(new CBDistantCredentialDatabase());
    }
    // TODO add test for Thread DB here
}
