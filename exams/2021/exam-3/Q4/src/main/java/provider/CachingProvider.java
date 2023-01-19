package provider;

import model.VideoFile;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CachingProvider implements VideoFileProvider{
    private VideoFileProvider videoFileProvider;
    private Deque<VideoFile> cache;
    private final int CACHE_SIZE = 2;

    CachingProvider(VideoFileProvider videoFileProvider){
        this.videoFileProvider = videoFileProvider;
        this.cache = new ArrayDeque<>();
    }
    @Override
    public CompletableFuture<VideoFile> getVideo(int uniqueID) {
        VideoFile videoFile;
        for(var file : cache){
            if(uniqueID == file.uniqueID){
                videoFile = file;
                cache.remove(videoFile);
                cache.add(videoFile);
                return CompletableFuture.completedFuture(videoFile);
            }
        }

        return videoFileProvider.getVideo(uniqueID).thenApply(file -> {
            if(cache.size() >= CACHE_SIZE){
                cache.removeFirst();
            }
            cache.addLast(file);
            return file;
        });
    }
}
