package de.neuendorf_online.garagedoorremote;

/**
 * Created by michael on 15.11.15.
 */
public interface DynDNSResolver {
    void resolve(String url, Callback callback);

    public interface Callback {
        void onUrlResolved();
    }
}
