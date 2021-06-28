public class test {
    private static Producer<String, String> producer;
    private static Properties properties = new Properties();

    public static void main(String[] args) throws IOException {
        File htmlTemplateFile = new File("/home/cassandra/mailTemplate.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        System.out.println("Read mail template file");
        String body = "Hi\n" +
    }
}
