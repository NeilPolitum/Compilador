package main;
/**
 *
 * @author daniel
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception{
        String ruta1 = "../CompiladorC/src/main/Lexer.flex";
        String ruta2 = "../CompiladorC/src/main/LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", "../CompiladorC/src/main/Sintax.cup"};
        generar(ruta1, ruta2, rutaS);
    }
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception {
        File archivo;
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("../CompiladorC/src/main/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("../CompiladorC/sym.java"), 
                Paths.get("../CompiladorC/src/main/sym.java")
        );
        Path rutaSin = Paths.get("../CompiladorC/src/main/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("../CompiladorC/Sintax.java"), 
                Paths.get("../CompiladorC/src/main/Sintax.java")
        );
    }
}
