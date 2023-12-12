package vttp.ssf.revision.newsapi.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.revision.newsapi.Utils;
import vttp.ssf.revision.newsapi.models.CountryCode;
import vttp.ssf.revision.newsapi.models.News;
import vttp.ssf.revision.newsapi.repositories.NewsRepository;

@Service
public class NewsService {

   @Value("${newsapi.key}")
   private String apiKey;

   @Value("${newsapi.page.size}")
   private String pageSize;

   @Autowired
   private NewsRepository newsRepo;

   private List<CountryCode> codes = null;

   // GET
   // /v2/top-headlines?country=us&category=technology&apiKey=abc123&pageSize=10
   public List<News> getNews(String country, String category) {

      Optional<String> opt = newsRepo.getNews(country, category);
      String payload;
      JsonArray articles;

      if (opt.isEmpty()) {

         String url = UriComponentsBuilder
               .fromUriString("https://newsapi.org/v2/top-headlines")
               .queryParam("country", country)
               .queryParam("category", category)
               .queryParam("pageSize", pageSize)
               .toUriString();

         RequestEntity<Void> req = RequestEntity.get(url)
               .header("X-Api-Key", apiKey)
               .build();

         RestTemplate template = new RestTemplate();
         ResponseEntity<String> resp = null;

         try {

            resp = template.exchange(req, String.class);

         } catch (Exception ex) {
            ex.printStackTrace();
            return new LinkedList<>();
         }

         payload = resp.getBody();
         JsonReader reader = Json.createReader(new StringReader(payload));
         JsonObject result = reader.readObject();
         articles = result.getJsonArray("articles");

      } else {
         System.out.println("--------- result from cache --------------");
         payload = opt.get();
         JsonReader reader = Json.createReader(new StringReader(payload));
         articles = reader.readArray();
      }

      if (opt.isEmpty())
         newsRepo.cacheNews(country, category, articles);

      // Cache the news

      return articles.stream()
            .map(j -> j.asJsonObject())
            .map(o -> {
               String author = o.getString("author", "Anonymous");
               String title = o.getString("title");
               String description = o.getString("description", "No description");
               String newsUrl = o.getString("url");
               String image = o.getString("urlToImage",
                     "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Placeholder_view_vector.svg/310px-Placeholder_view_vector.svg.png");
               String publish = o.getString("publishedAt");
               return new News(title, author, description, newsUrl, image, publish);
            })
            .toList();
   }

   public List<CountryCode> getCountryCode() {

      if (null == codes) {
         // /v3.1/alpha?codes=sg,my,jp
         String url = UriComponentsBuilder
               .fromUriString("https://restcountries.com/v3.1/alpha")
               .queryParam("codes", Utils.getCodeAsCSV())
               .toUriString();

         // GET /v3.1/alpha?codes=sg,my,jp
         RequestEntity<Void> req = RequestEntity
               .get(url).build();

         RestTemplate template = new RestTemplate();

         ResponseEntity<String> resp = template.exchange(req, String.class);
         String payload = resp.getBody();

         JsonReader reader = Json.createReader(new StringReader(payload));
         // [ {...}, {...} ]
         JsonArray arr = reader.readArray();
         codes = arr.stream()
               .map(j -> j.asJsonObject())
               .map(o -> {
                  String cca2 = o.getString("cca2").toLowerCase();
                  String name = o.getJsonObject("name").getString("common");
                  return new CountryCode(cca2, name);
               })
               .sorted((c0, c1) -> c0.name().compareTo(c1.name()))
               .toList();
      }

      return codes;
   }

}