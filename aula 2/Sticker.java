import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import javax.imageio.ImageIO;

public class Sticker {

    public void cria(InputStream inputStream, String nomeArquivo, String textoStickerDesejado) throws Exception {

        // leitura da imagem
        // InputStream inputStream = 
        //             new FileInputStream(new File("entrada/filme-maior.jpg"));
        // InputStream inputStream = 
        //                 new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
        //                 .openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font("Impact", Font.BOLD, 90);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem (centralizada)
        String textoSticker = textoStickerDesejado;
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(textoSticker, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto )/ 2;
        int posicaoTextoY = novaAltura - 100;
        graphics.drawString(textoSticker, posicaoTextoX, novaAltura - 100);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(textoSticker, fonte, fontRenderContext);

        Shape contornoShape = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);

        var contornoStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(contornoStroke);

        graphics.setColor(Color.CYAN);
        graphics.draw(contornoShape);
        graphics.setClip(contornoShape);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }
}
