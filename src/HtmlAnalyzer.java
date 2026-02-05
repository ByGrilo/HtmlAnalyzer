import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Stack;

public class HtmlAnalyzer {

    public static void main(String[] args) {

        //Verifica se recebe url

        if (args.length == 1) {
            return;
        }
        String urlString = args[0];
        try {
            URL url = new URL(urlString);
            InputStream inputStream = url.openStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            String deepestText = null;
            int maxDepth = 0;

            Stack<String> stack = new Stack<>();
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("</")) {

                    if (stack.isEmpty()) {
                        System.out.println("malformed HTML");
                        return;
                    }

                    String closeName = line.substring(2, line.length() - 1);
                    String openName = stack.peek();

                    if (openName.equals(closeName)) {
                        stack.pop();
                    } else {
                        System.out.println("malformed HTML");
                        return;
                    }
                }else if (line.startsWith("<")) {
                    String tagName = line.substring(1, line.length() - 1);
                    stack.push(tagName);
                } else {
                    if (stack.size() > maxDepth) {
                        maxDepth = stack.size();
                        deepestText = line;
                    }

                }
            }
            bufferedReader.close();
            if (!stack.isEmpty()){
                System.out.println("malformed HTML");
            } else if (deepestText != null) {
                System.out.println(deepestText);
            }

        } catch (Exception e) {
            System.out.println("URL connection error");
        }
    }
}
