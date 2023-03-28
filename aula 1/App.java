import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception{
        //Conectando com o HTTP epara buscar as informações

        //(
        //Se tivesse usando chave de da API:
        //String key = System.getenv("algum nome");
        //String url = "https://imdb-api.com/en/API/algoalgo/" + key;
        //)
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI endereco = URI.create(url);
        var cliente = HttpClient.newHttpClient();
        var requisicao = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> resposta = cliente.send(requisicao, BodyHandlers.ofString());
        String json = resposta.body();
        
        //Extraindo os dados que interessam
        var parser = new JsonParser();
        List<Map<String, String>> listaDeSeriados = parser.parse(json);

        //Exibir os dados
        for(Map<String, String> seriado : listaDeSeriados) {
            System.out.println(seriado.get("title"));
            System.out.println(seriado.get("year"));
            System.out.println(seriado.get("rank"));
            System.out.println(seriado.get("image"));
        }
    }
    
}
