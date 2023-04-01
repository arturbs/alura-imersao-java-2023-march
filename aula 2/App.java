import java.net.URI;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
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

        var diretorio = new File("stickers");
        diretorio.mkdir();
        

        //Exibir e manipular os dados
        var sticker = new Sticker(); 
        for(Map<String, String> seriado : listaDeSeriados) {
            String urlImaString = seriado.get("image");
            String urlImaMaior = urlImaString.replaceFirst("(@?\\.)([0-9A-Z,_]+).jpg$", "$1.jpg");
            String titulo = seriado.get("title");
            double classificacao = Double.parseDouble(seriado.get("imDbRating"));

            String textoSticker;
            if (classificacao >= 9) {
                textoSticker = "CONFIA QUE EH BOM";
            } else if(classificacao >= 8) {
                textoSticker = "TOPZEIRA";

            } else if(classificacao >= 6) {
                textoSticker = "pq naon neh";

            } else {
                textoSticker = "VISH";
            }


            InputStream imputStream = new URL(urlImaMaior).openStream();
            String nomeArquivo = "stickers/" + titulo + ".png";
            sticker.cria(imputStream, nomeArquivo, textoSticker);

            System.out.println(titulo);
            System.out.println();
            
        }
    }
    
}
