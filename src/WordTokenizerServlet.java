import vn.pipeline.Annotation;
import vn.pipeline.VnCoreNLP;
import vn.pipeline.Word;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class WordTokenizerServlet extends HttpServlet {
    private VnCoreNLP pipeline;

    public WordTokenizerServlet() {
        String[] annotators = {"wseg"};
        try {
            this.pipeline = new VnCoreNLP(annotators);
        } catch (IOException ignored) {
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        try (InputStream is = request.getInputStream();
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            sb.append(br.readLine()).append('\n');
        }

        String input = sb.toString();

        Annotation annotation = new Annotation(input);
        pipeline.annotate(annotation);

        StringBuilder result = new StringBuilder();
        result.append('[');
        for (Word word : annotation.getWords()) {
            result.append('"').append(word.getForm()).append('"').append(", ");
        }
        result.deleteCharAt(result.length() - 1); // remove last comma
        result.append(']');

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(result.toString());
        response.getWriter().flush();
    }
}
