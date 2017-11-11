package org.programirame.utility;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * Apache common IO downloader.
 */
public class XmlDownloader implements Downloader {

    /**
     * Downloads the content of the url as a file using apache common IO library.
     *
     * @param url a valid url
     * @return optional file
     */
    public Optional<File> downloadUrl(String url) {

        Optional<File> optional = Optional.empty();

        try {
            optional = Optional.of(File.createTempFile("tmp", ".xml"));
            FileUtils.copyURLToFile(new URL(url), optional.get());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optional;
    }
}
