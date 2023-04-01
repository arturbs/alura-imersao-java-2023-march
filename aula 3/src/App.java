import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        API api = API.NASA;

        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var diretorio = new File("stickers");
        diretorio.mkdir();

        var sticker = new Sticker();

        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "stickers/" + conteudo.titulo() + ".png";

            /*double classificacao = Double.parseDouble(seriado.get("imDbRating"));

            String textoSticker;
            if (classificacao >= 9) {
                textoSticker = "CONFIA QUE EH BOM";
            } else if(classificacao >= 8) {
                textoSticker = "TOPZEIRA";

            } else if(classificacao >= 6) {
                textoSticker = "pq naon neh";

            } else {
                textoSticker = "VISH";
            }*/

            sticker.cria(inputStream, nomeArquivo);
            //sticker.cria(inputStream, nomeArquivo, textoSticker);

            System.out.println(conteudo.titulo());
            System.out.println();
        }
    }
}