package service;

import model.Document;
import provider.DocumentNotFoundException;
import provider.DocumentProvider;

import java.util.concurrent.CompletableFuture;

public class RetryMiddleware implements DocumentProvider {

    private DocumentProvider documentProvider;

    RetryMiddleware(DocumentProvider documentProvider){
        this.documentProvider = documentProvider;
    }
    @Override
    public CompletableFuture<Document> fetchDocument(String id) {
        return this.documentProvider.fetchDocument(id).exceptionallyCompose(e -> {
            if(! (e.getCause() instanceof DocumentNotFoundException)){
                return fetchDocument(id);
            }
            return CompletableFuture.failedFuture(new DocumentNotFoundException(id));
        });
    }
}
