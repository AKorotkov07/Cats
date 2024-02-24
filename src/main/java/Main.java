import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats"))
                .build();

        try {
            List<Cat> cats;
            ObjectMapper mapper = new ObjectMapper();
            cats = mapper.readValue(httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body(), new TypeReference<List<Cat>>() {});

            List<Cat> filteredCats = cats.stream()
                    .filter(cat -> cat.getUpvotes() != null)
                    .toList();

            filteredCats.forEach(System.out::println);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
