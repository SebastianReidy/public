package service;

import model.Document;
import provider.DocumentNotFoundException;
import provider.DocumentProvider;

import javax.print.Doc;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.failedFuture;

public class RetryDecorator implements DocumentProvider {

    DocumentProvider provider;

    RetryDecorator(DocumentProvider provider){
        this.provider = provider;
    }
    @Override
    public CompletableFuture<Document> fetchDocument(String id) {
        return provider.fetchDocument(id).exceptionallyCompose(e -> {
            while(e!=null && e!=e.getCause()) {  // use while because the exceptions can be nested
                if (e instanceof DocumentNotFoundException) {  // have to use getCause because the future returns a completion exception
                    return failedFuture(e);
                }
                e = e.getCause();
            }
            return fetchDocument(id);
        });
    }
}
