# xmlToJsonReporter

Provides a way to generate a report based on a xml data source. The xml has to follow the [sample.xsd](src/main/resources/sample.xsd).

The xml can be provided either as a file or the [XmlFileDownloader](src/main/java/org/programirame/utility/XmlDownloader.java) utility can be used to download a url as a file.

Two diferent reportes are created:

* [JsonRiskSummaryReport](src/main/java/org/programirame/report/generators/JsonRiskSummaryReport.java) - which generates a JSON
* [SimpleRiskSummaryReport](src/main/java/org/programirame/report/generators/SimpleRiskSummaryReport.java) - which generates simple text report

The WireMock based [integration test](src/test/java/org/programirame/XmlDownloaderIT.java) shows best how to use them.


