package org.programirame;

import org.junit.Test;
import org.igorski.utility.XmlDownloader;

import java.io.File;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlDownloaderTest {

    @Test
    public void shouldDownloadAnNonEmptyFile() throws Exception {

        XmlDownloader xmlDownloader = new XmlDownloader();
        Optional<File> optionalXml = xmlDownloader.downloadUrl("https://outscan.outpost24.com/pub/report_test.xml");
        assertThat(optionalXml.isPresent()).isTrue();

        if(optionalXml.isPresent()) {
            File xml = optionalXml.get();
            assertThat(xml.length()).isGreaterThan(0);
        }
    }

}