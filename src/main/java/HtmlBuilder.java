import org.apache.commons.io.FileUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class HtmlBuilder {
    private static Producer<String, String> producer;
    private static Properties properties = new Properties();

    public static void main(String[] args) throws IOException {
        File htmlTemplateFile = new File("/home/cassandra/mailTemplate.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        String body = "Hi\n" +
                "\n" +
                "Below is the summary of ingestion of TOT file test.txt";
        //htmlString = htmlString.replace("$title", "Testing java html builder");
        htmlString = htmlString.replace("$body", body);
        htmlString = htmlString.replace("$successEntity", "DOMINO_ITEM_RULE_RAW, DOMINO_ITEM_PRICE_RAW, DOMINO_CHASSIS_OPTION_ITEM_RAW, DOMINO_ITEM_TRANSLATION_RAW");
        htmlString = htmlString.replace("$fileName", "test");
        //File newHtmlFile = new File("C:\\Users\\AntimKant.Verma\\Documents\\BACKUP_DATA_DESKTOP\\Intellij Workspace\\Example\\src\\main\\resources\\new.html");
        //FileUtils.writeStringToFile(newHtmlFile, htmlString);
        String topic = "mailtest";
        loadProperties();
        producer = new KafkaProducer<String, String>(properties);
        producer.send(new ProducerRecord<>(topic,"test",htmlString));
    }

    public static void loadProperties() {
        properties.put("bootstrap.servers", "10.105.22.175:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }
}
