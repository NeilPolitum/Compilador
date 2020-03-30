package main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.logging.*;
import java_cup.runtime.Symbol;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author danie
 */


public class FramePrincipal extends JFrame implements ActionListener {
    
    public JPanel contentPane;
    public JLabel anLexico, anSintactico;
    public JButton anLex, anSin, limpLex, limpSin, abrirT, guardarT;
    public JTextArea entradaT, salLex, salAn;
    public JScrollPane enT, sLex;
    
    JFileChooser escoger = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    
    
    public FramePrincipal(){
        //FRAME
        this.setTitle("Compilador");
        this.setSize(1200, 650);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //PANEL
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        //LABEL
        anLexico = new JLabel("Analizador Léxico");
        anLexico.setForeground(Color.BLACK);
        anLexico.setFont(new Font("Berlin Sans FB", 0, 20));
        anLexico.setBounds(500, 10, 400, 30);
        
        anSintactico = new JLabel("Analizador Sintáctico");
        anSintactico.setForeground(Color.BLACK);
        anSintactico.setFont(new Font("Berlin Sans FB", 0, 20));
        anSintactico.setBounds(500, 460, 400, 30);
        
        //BUTTON
        abrirT = new JButton("Abrir Archivo");
        abrirT.setFont(new Font("Berlin Sans FB", 0, 15));
        abrirT.setForeground(Color.WHITE);
        abrirT.setBackground(Color.DARK_GRAY);
        abrirT.setBounds(20, 10, 150, 30);
        abrirT.addActionListener(this);
        
        anLex = new JButton("Analizar");
        anLex.setFont(new Font("Berlin Sans FB", 0, 15));
        anLex.setForeground(Color.WHITE);
        anLex.setBackground(Color.DARK_GRAY);
        anLex.setBounds(30, 420, 150, 30);
        anLex.addActionListener(this);
        
        guardarT = new JButton("Guardar");
        guardarT.setFont(new Font("Berlin Sans FB", 0, 15));
        guardarT.setForeground(Color.WHITE);
        guardarT.setBackground(Color.DARK_GRAY);
        guardarT.setBounds(200, 420, 150, 30);
        guardarT.addActionListener(this);
        
        anSin = new JButton("Analizar");
        anSin.setFont(new Font("Berlin Sans FB", 0, 15));
        anSin.setForeground(Color.WHITE);
        anSin.setBackground(Color.DARK_GRAY);
        anSin.setBounds(30, 550, 100, 30);
        anSin.addActionListener(this);
        
        limpLex = new JButton("Limpiar");
        limpLex.setFont(new Font("Berlin Sans FB", 0, 15));
        limpLex.setForeground(Color.WHITE);
        limpLex.setBackground(Color.DARK_GRAY);
        limpLex.setBounds(1000, 420, 150, 30);
        limpLex.addActionListener(this);
        
        limpSin = new JButton("Limpiar");
        limpSin.setFont(new Font("Berlin Sans FB", 0, 15));
        limpSin.setForeground(Color.WHITE);
        limpSin.setBackground(Color.DARK_GRAY);
        limpSin.setBounds(1050, 550, 100, 30);
        limpSin.addActionListener(this);
        
        //TEXT AREA
        entradaT = new JTextArea();
        entradaT.setFont(new Font("Berlin Sans FB", 0, 15));
        entradaT.setForeground(Color.BLACK);
        entradaT.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        entradaT.setBounds(10, 50, 580, 350);
        enT = new JScrollPane(entradaT);
        enT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        salLex = new JTextArea();
        salLex.setFont(new Font("Berlin Sans FB", 0, 15));
        salLex.setForeground(Color.BLACK);
        salLex.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        salLex.setEditable(false);
        salLex.setBounds(600, 50, 580, 350);
        sLex = new JScrollPane(salLex);
        sLex.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        salAn = new JTextArea();
        salAn.setFont(new Font("Berlin Sans FB", 0, 15));
        salAn.setForeground(Color.BLACK);
        salAn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        salAn.setEditable(false);
        salAn.setBounds(200, 500, 800, 100);
        
        //AGREGAR COMPONENTES
        contentPane.add(anLexico);
        contentPane.add(anSintactico);
        contentPane.add(abrirT);
        contentPane.add(anLex);
        contentPane.add(guardarT);
        contentPane.add(anSin);
        contentPane.add(limpLex);
        contentPane.add(limpSin);
        contentPane.add(enT);
        contentPane.add(sLex);
        contentPane.add(salAn);
        contentPane.add(entradaT);
        contentPane.add(salLex);
    } 
    
    public String GuardarArchivo(File archivo, String documento){
        String mensaje = null;
        try{
            salida = new FileOutputStream(archivo);
            byte[] bytxt=documento.getBytes();
            salida.write(bytxt);
            mensaje="Archivo Guardado";
        } catch (Exception e){          
        }
          return mensaje;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==anLex){
            archivo = new File("archivo.txt");
            PrintWriter escribir;
            try {
                escribir = new PrintWriter(archivo);
                escribir.print(entradaT.getText());
                escribir.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                Reader lector = new BufferedReader(new FileReader("archivo.txt"));
                Lexer lexer = new Lexer(lector);
                String resultado = "";
                while(true){
                    Tokens tokens = lexer.yylex();
                    if(tokens == null){
                        salLex.setText(resultado);
                        return;
                    }
                    switch(tokens){
                        case ERROR:
                            resultado += "Símbolo no definido\n";
                            break;
                        case Comillas: case Byte: case Int: case Char: case Long: case Float: 
                        case Double: case Cadena: case If: case Else: case Do: case While: 
                        case For: case Igual: case Suma: case Resta: case Multiplicacion: case Division: 
                        case Dos_Puntos: case Punto: case Operador_logico: case Operador_relacional: 
                        case Operador_atribucion: case Operador_incremento: case Operador_booleano: 
                        case Parentesis_cerrado: case Parentesis_abierto: case Llave_abierta: 
                        case Llave_cerrada: case Corchete_abierto: case Corchete_cerrado: case Main: 
                        case Identificador: case Numero:
                            resultado += lexer.lexeme + ": Es un " + tokens + "\n";
                            break;
                        default:
                            resultado += "Token: " + tokens + "\n";
                            break;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource()==limpLex){
            salLex.setText("");
        } else if (e.getSource()==anSin){
            String ST = entradaT.getText();
            Sintax s = new Sintax(new main.LexerCup(new StringReader(ST)));
            
            try{
                s.parse();
                salAn.setText("Analisis realizado correctamente");
            } catch (Exception ex) {
                Symbol sym = s.getS();
                salAn.setText("Error de sintaxis. Linea: "+(sym.right+1)+" Columna: "+(sym.left+1)+", Texto: \""+sym.value+"\"");
            }
        } else if (e.getSource()==limpSin){
            salAn.setText("");
        } else if (e.getSource()==guardarT){
            if(escoger.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
                archivo= escoger.getSelectedFile();
                if(archivo.getName().endsWith("c")){
                    String Documento = entradaT.getText();
                    String mensaje= GuardarArchivo(archivo, Documento);
                    if(mensaje!=null){
                        JOptionPane.showMessageDialog(null, mensaje);
                    }else{
                        JOptionPane.showMessageDialog(null, "Archivo no compatible");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Guardar documento de texto");
                }
            }   
        } else if (e.getSource()==abrirT){
            int cont = 1;
            escoger.showOpenDialog(null);
            File arc = new File(escoger.getSelectedFile().getAbsolutePath());
            try {
                String ST = new String(Files.readAllBytes(arc.toPath()));
                entradaT.setText(ST);
            }catch (FileNotFoundException ex){
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE,null,ex);
            }catch (IOException ex){
            Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
    public static void main(String[] args){
        new FramePrincipal().setVisible(true);
    }
}