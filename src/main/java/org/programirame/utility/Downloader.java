package org.programirame.utility;

import java.io.File;
import java.util.Optional;

/**
 * Implements downloading of a url and returning its contents as a file.
 */
public interface Downloader {
    /**
     * Downloads the content of the given url and returns them as a {@link File}. The file is expected to have the same
     * extension as the url.
     *
     * @param url a valid url
     * @return the file with the contents of the url location
     */
    Optional<File> downloadUrl(String url);
}
