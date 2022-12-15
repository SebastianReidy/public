package service;

import model.Document;
import provider.DocumentNotFoundException;
import provider.DocumentProvider;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class MultipleProvidersMiddleware implements DocumentProvider {
    DocumentProvider[] documentProviders;

    MultipleProvidersMiddleware(DocumentProvider... documentProviders){
        this.documentProviders = documentProviders;
    }

    @Override
    public CompletableFuture<Document> fetchDocument(String id) {
        return Arrays.stream(this.documentProviders).map(provider ->
                provider.fetchDocument(id))
                .reduce(CompletableFuture.failedFuture(new DocumentNotFoundException(id)),
                        (a,b) -> a.exceptionallyCompose(e1 -> b
                                .exceptionallyCompose(e2 ->
                                        CompletableFuture.failedFuture(new DocumentNotFoundException(id)))));
    }
}
