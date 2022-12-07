package service;

import model.Document;
import provider.DocumentNotFoundException;
import provider.DocumentProvider;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.failedFuture;

public class CombineDecorator implements DocumentProvider {

    private final DocumentProvider[] providers;

    CombineDecorator(DocumentProvider... providers){
        this.providers = providers;
    }

    private Throwable combineException(Throwable ea, Throwable eb){
        if (ea instanceof DocumentNotFoundException){
            return eb;
        }
        return ea;
    }

    @Override
    public CompletableFuture<Document> fetchDocument(String id) {
        return Arrays.stream(this.providers)
                .map(provider -> provider.fetchDocument(id))
                .reduce(failedFuture(new DocumentNotFoundException(id)), (a,b) ->
                    a.exceptionallyCompose(ea -> b.exceptionallyCompose(eb -> failedFuture(combineException(ea,eb)))));
        // cannot use CompletableFuture.anyOf because anyOf returns just the first completed future; also if the first
        // one failed.

        // the reduce function needs a default value(at index 0) or an additional .orElse(<default>) after the reduce function

    }
}
