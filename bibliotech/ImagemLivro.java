import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.imageio.ImageIO;

public class ImagemLivro {

    private static final long LIMITE = 5 * 1024 * 1024;

    public static String salvar(File arquivo) throws IOException {

        if (arquivo == null || !arquivo.exists()) {
            throw new IOException("Arquivo não encontrado.");
        }

        if (arquivo.length() > LIMITE) {
            throw new IOException("A imagem deve ter no máximo 5 MB.");
        }

        BufferedImage imagem = ImageIO.read(arquivo);

        if (imagem == null) {
            throw new IOException("Arquivo de imagem inválido.");
        }

        Path pasta = Paths.get("imagens", "livros");
        Files.createDirectories(pasta);

        String nome = UUID.randomUUID().toString() + ".png";
        Path destino = pasta.resolve(nome);

        ImageIO.write(imagem, "png", destino.toFile());

        return "imagens/livros/" + nome;
    }
}
