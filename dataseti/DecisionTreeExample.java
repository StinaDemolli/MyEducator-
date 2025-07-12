import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DecisionTreeExample {

    static class Record {
        String topic;
        int studentAnswer;
        boolean isCorrect;

        Record(String topic, int studentAnswer, boolean isCorrect) {
            this.topic = topic;
            this.studentAnswer = studentAnswer;
            this.isCorrect = isCorrect;
        }
    }

    static class Node {
        String attribute;
        String value;
        Boolean label;
        Node left;
        Node right;

        Node(Boolean label) {
            this.label = label;
        }

        Node(String attribute, String value, Node left, Node right) {
            this.attribute = attribute;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        boolean predict(Record record) {
            if (label != null) {
                return label;
            }
            if ("Topic".equals(attribute)) {
                if (record.topic.equalsIgnoreCase(value)) {
                    return left.predict(record);
                } else {
                    return right.predict(record);
                }
            }
            return false;
        }
    }

    static Node buildTree() {
        Node leafTrue = new Node(true);
        Node leafFalse = new Node(false);
        return new Node("Topic", "Addition", leafTrue, leafFalse);
    }

    static ArrayList<Record> readRecordsFromFile() {
        ArrayList<Record> records = new ArrayList<>();
        String filename = "C:\\Users\\vesav\\Desktop\\dataseti\\data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String topic = parts[0].trim();
                    int studentAnswer = Integer.parseInt(parts[1].trim());
                    boolean isCorrect = Boolean.parseBoolean(parts[2].trim());
                    records.add(new Record(topic, studentAnswer, isCorrect));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return records;
    }

    public static void main(String[] args) {
        ArrayList<Record> data = readRecordsFromFile();
        Node tree = buildTree();

        for (Record r : data) {
            boolean prediction = tree.predict(r);
            System.out.println("Topic: " + r.topic + ", Actual: " + r.isCorrect + ", Predicted: " + prediction);
        }
    }
}
