import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class SealNotes extends JFrame{
    public static void main(String[] args) {
        new SealNotes();
    }
    public int nota_pronta = 0;
    public int lista_pronta = 0;
    public SealNotes(){
//---------IMAGENS, LOGOS E FONTES DE LETRAS-----------------------------------------------------
        setLayout(null);
        setIcon();

        ImageIcon imagem = new ImageIcon(SealNotes.class.getResource("LogoSealNotes.png"));
        JLabel logo = new JLabel(imagem);
        logo.setBounds(223,40,418,353);
        logo.setVisible(true);

        ImageIcon imagem_mini = new ImageIcon(SealNotes.class.getResource("MiniLogo.png"));
        JLabel logo_mini = new JLabel(imagem_mini);
        logo_mini.setBounds(10,340,132,164);
        logo_mini.setVisible(false);

        ImageIcon img_nd_add = new ImageIcon(SealNotes.class.getResource("PeopleWorried3.png"));
        JLabel img_worried = new JLabel(img_nd_add);
        img_worried.setBounds(223,5,380,490);
        img_worried.setVisible(false);

        ImageIcon img_prog = new ImageIcon(SealNotes.class.getResource("martina-programming.png"));
        JLabel img_programming = new JLabel(img_prog);
        img_programming.setBounds(450,260,256,256);
        img_programming.setVisible(false);

        Color cor_background = new Color(244,164,96);
        Color cor_bt = new Color(255,222,173);
        Color transparente = new Color(255, 255, 255, 0);
        Font fonte = new Font("Sans-Serif",Font.PLAIN,12);
        Font fonte_tam15 = new Font("Sans-Serif",Font.PLAIN,15);
        Font fonte_titulo = new Font("Sans-Serif",Font.BOLD,14);
        Font fonte_desenv = new Font("Sans-Serif",Font.BOLD,16);
//-------------------------------------------------------------------------
//-------------CRIANDO OS BOTÕES DA TELA PRINCIPAL-------------------------
        JButton bt_nova_nota = new JButton("Nova Nota");
        bt_nova_nota.setBounds(10,10,130,35);
        bt_nova_nota.setBackground(cor_bt);
        bt_nova_nota.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));

        JButton bt_nova_lista = new JButton("Nova Lista");
        bt_nova_lista.setBounds(10,60,130,35);
        bt_nova_lista.setBackground(cor_bt);
        bt_nova_lista.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));

        JButton bt_ver_tarefas = new JButton("Ver Tarefas");
        bt_ver_tarefas.setBounds(10,110,130,35);
        bt_ver_tarefas.setBackground(cor_bt);
        bt_ver_tarefas.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));

        JButton bt_informacoes = new JButton("Informações!");
        bt_informacoes.setBounds(10,160,130,35);
        bt_informacoes.setBackground(cor_bt);
        bt_informacoes.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
//-------------------------------------------------------------------------
//-------------ÁREA DA NOVA NOTA-------------------------------------------
        JLabel txt_titulo = new JLabel("Título:");
        txt_titulo.setBounds(300,20,50,10);
        txt_titulo.setVisible(false);

        JLabel txt_assunto = new JLabel("Assunto:");
        txt_assunto.setBounds(300,70,50,10);
        txt_assunto.setVisible(false);

        JTextField input_titulo = new JTextField();
        input_titulo.setBounds(300,35,280,25);
        input_titulo.setFont(fonte);
        input_titulo.setVisible(false);

        JTextArea input_assunto = new JTextArea();
        input_assunto.setBounds(300,90,280,400);
        input_assunto.setFont(fonte_tam15);
        input_assunto.setLineWrap(true);
        input_assunto.setVisible(false);

        JButton bt_salvar = new JButton("Salvar");
        bt_salvar.setBackground(cor_bt);
        bt_salvar.setBounds(600,455,110,35);
        bt_salvar.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        bt_salvar.setVisible(false);
//-------------------------------------------------------------------------
//----------------ÁREA NOVA LISTA------------------------------------------
        JLabel txt_nova_tarefa2 = new JLabel("Digite nova tarefa:");
        txt_nova_tarefa2.setBounds(200,20,110,20);
        txt_nova_tarefa2.setFont(fonte);
        txt_nova_tarefa2.setVisible(false);

        JTextField input_nova_tarefa = new JTextField();
        input_nova_tarefa.setText("");
        input_nova_tarefa.setBounds(200,40,200,27);
        input_nova_tarefa.setFont(fonte);
        input_nova_tarefa.setVisible(false);

        JButton bt_adicionar = new JButton("Adicionar");
        bt_adicionar.setBounds(250,75,90,25);
        bt_adicionar.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        bt_adicionar.setBackground(cor_bt);
        bt_adicionar.setVisible(false);

        JCheckBox checkBox1 = new JCheckBox();
        checkBox1.setBounds(530,30,250,30);
        checkBox1.setBackground(null);
        checkBox1.setFont(fonte);
        checkBox1.setVisible(false);
//-------------------------------------------------------------------------
//----------------ÁREA VER TAREFAS-----------------------------------------
        JTextArea txt_ver_nota = new JTextArea();
        txt_ver_nota.setFont(fonte);
        txt_ver_nota.setEditable(false);
        txt_ver_nota.setLineWrap(true);
        txt_ver_nota.setBounds(200,30,265,400);
        txt_ver_nota.setBackground(transparente);
        txt_ver_nota.setVisible(false);
//-------------------------------------------------------------------------
//------------ÁREA DE INFORMAÇÕES---------------------------------------------
        JTextArea titulo_add_nota = new JTextArea("Como adicionar uma nota?");
        titulo_add_nota.setBounds(200,10,300,30);
        titulo_add_nota.setBackground(transparente);
        titulo_add_nota.setFont(fonte_titulo);
        titulo_add_nota.setEditable(false);
        titulo_add_nota.setLineWrap(true);
        titulo_add_nota.setVisible(false);

        JTextArea como_add_nota = new JTextArea("""
                Para adicionar uma nova nota, aperte no botão "Nova Nota", preencha o campo "título" com o tema da sua\s
                nota.\s
                Em seguida, preencha o campo "assunto" falando\s
                sobre ou dando melhores informações.""");
        como_add_nota.setBounds(200,28,300,100);
        como_add_nota.setBackground(transparente);
        como_add_nota.setFont(fonte);
        como_add_nota.setEditable(false);
        como_add_nota.setLineWrap(true);
        como_add_nota.setVisible(false);

        JTextArea titulo_add_lista = new JTextArea("Como adicionar uma tarefa?");
        titulo_add_lista.setBounds(200,115,300,80);
        titulo_add_lista.setBackground(transparente);
        titulo_add_lista.setFont(fonte_titulo);
        titulo_add_lista.setEditable(false);
        titulo_add_lista.setLineWrap(true);
        titulo_add_lista.setVisible(false);

        JTextArea como_add_lista = new JTextArea("""
                Para adicionar uma nova tarefa, aperte o botão "Nova Tarefa", em seguida, preencha o campo que está\s
                vazio com a sua tarefa. Ex: Lavar a louça, e clique em\s
                "Adicionar".""");
        como_add_lista.setBounds(200,135,300,80);
        como_add_lista.setBackground(transparente);
        como_add_lista.setFont(fonte);
        como_add_lista.setEditable(false);
        como_add_lista.setLineWrap(true);
        como_add_lista.setVisible(false);

        JTextArea titulo_ver_tarefa = new JTextArea("Como vejo minhas terefas criadas?");
        titulo_ver_tarefa.setBounds(510,10,300,30);
        titulo_ver_tarefa.setBackground(transparente);
        titulo_ver_tarefa.setFont(fonte_titulo);
        titulo_ver_tarefa.setEditable(false);
        titulo_ver_tarefa.setLineWrap(true);
        titulo_ver_tarefa.setVisible(false);

        JTextArea como_ver_tarefa = new JTextArea("Para visualizar sua nota e sua tarefa criada,\n clique no botão \"Ver Tarefas\".");
        como_ver_tarefa.setBounds(510,28,250,30);
        como_ver_tarefa.setBackground(transparente);
        como_ver_tarefa.setFont(fonte);
        como_ver_tarefa.setEditable(false);
        como_ver_tarefa.setLineWrap(true);
        como_ver_tarefa.setVisible(false);
        //-----------PARTE DOS DESENVOLVEDORES-------------------------------------
        JLabel txt_desenvolvedores = new JLabel("Desenvolvedores:");
        txt_desenvolvedores.setBounds(200,300,200,30);
        txt_desenvolvedores.setBackground(transparente);
        txt_desenvolvedores.setFont(fonte_desenv);
        txt_desenvolvedores.setVisible(false);
        //--------PARTE DO ABEL------------------------------------------------
        JLabel link_abel = new JLabel("Abel Costa Dantas");
        link_abel.setBounds(210,340,110,15);
        link_abel.setForeground(Color.BLACK);
        link_abel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link_abel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/abelcostadantas/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                link_abel.setForeground(Color.BLUE);
            }
            public void mouseExited(MouseEvent e) {
                link_abel.setForeground(Color.BLACK);
            }
        });
        link_abel.setVisible(false);
        //-------------------------------------------------------------------------
        //--------PARTE GUSTAVO----------------------------------------------------
        JLabel link_gustavo = new JLabel("Gustavo de morais de Oliveira");
        link_gustavo.setBounds(209,365,200,15);
        link_gustavo.setForeground(Color.BLACK);
        link_gustavo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link_gustavo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/gustavo-morais-de-oliveira-a00128253"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                link_gustavo.setForeground(Color.BLUE);
            }
            public void mouseExited(MouseEvent e) {
                link_gustavo.setForeground(Color.BLACK);
            }
        });
        link_gustavo.setVisible(false);
        //-------------------------------------------------------------------------
        //--------PARTE LAVINIA----------------------------------------------------
        JLabel link_lavinia = new JLabel("Lavínia Silva de Sousa");
        link_lavinia.setBounds(209,390,130,15);
        link_lavinia.setForeground(Color.BLACK);
        link_lavinia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link_lavinia.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/lav%C3%ADnia-silva-de-sousa-237b8b256"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                link_lavinia.setForeground(Color.BLUE);
            }
            public void mouseExited(MouseEvent e) {
                link_lavinia.setForeground(Color.BLACK);
            }
        });
        link_lavinia.setVisible(false);
        //-------------------------------------------------------------------------
        //----------PARTE SABRINA--------------------------------------------------
        JLabel link_sabrina = new JLabel("Sabrina Moreno Paes");
        link_sabrina.setBounds(208,415,126,15);
        link_sabrina.setForeground(Color.BLACK);
        link_sabrina.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link_sabrina.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/sabrina-paes-46525b223/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                link_sabrina.setForeground(Color.BLUE);
            }
            public void mouseExited(MouseEvent e) {
                link_sabrina.setForeground(Color.BLACK);
            }
        });
        link_sabrina.setVisible(false);
        //-------------------------------------------------------------------------
        //------FIM DESENVOLVEDORES----------------------------------------------------
//------FIM INFORMAÇÕES----------------------------------------------------------------
//------------AÇÕES DE TODOS OS BOTÕES-------------------------------------
        //--------AÇÃO DO BOTÃO NOVA NOTA----------------------------------
        bt_nova_nota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //------IMAGENS DE FUNDO---------------------------
                img_worried.setVisible(false);
                logo.setVisible(false);
                logo_mini.setVisible(true);
                //-------------------------------------------------
                //------PARTE DA NOVA NOTA-------------------------
                txt_titulo.setVisible(true);
                txt_assunto.setVisible(true);
                input_titulo.setVisible(true);
                input_assunto.setVisible(true);
                bt_salvar.setVisible(true);
                //-------------------------------------------------
                //------PARTE DA NOVA LISTA------------------------
                txt_nova_tarefa2.setVisible(false);
                input_nova_tarefa.setVisible(false);
                bt_adicionar.setVisible(false);
                checkBox1.setVisible(false);
                //-------------------------------------------------
                //------PARTE VER TAREFAS--------------------------
                txt_ver_nota.setVisible(false);
                //-------------------------------------------------
                //------PARTE INFORMAÇÕES--------------------------
                titulo_ver_tarefa.setVisible(false);
                titulo_add_lista.setVisible(false);
                titulo_add_nota.setVisible(false);
                como_add_nota.setVisible(false);
                como_add_lista.setVisible(false);
                como_ver_tarefa.setVisible(false);
                txt_desenvolvedores.setVisible(false);
                link_abel.setVisible(false);
                link_sabrina.setVisible(false);
                link_gustavo.setVisible(false);
                link_lavinia.setVisible(false);
                img_programming.setVisible(false);
                //-------------------------------------------------
            }
        });
        //---------------------------------------------------------
        //--------AÇÃO DO BOTÃO SALVAR NOTA------------------------
        bt_salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //mude o diretório para usar em seu computador.
                Path caminho_do_arquivo = Paths.get("C:/Users/costa/Desktop/nota.txt"); /* "C:/Users/aluno/Desktop/lavínia/nota.txt" */
                String titulo_nota = input_titulo.getText();
                String assunto_nota = input_assunto.getText();
                String nota = "Titulo: " + titulo_nota + "\nAssunto: " + assunto_nota;
                byte[] nota_em_byte = nota.getBytes();
                try {
                    int jp_algo_errado = JOptionPane.showConfirmDialog(null, "Algo está errado?",
                            "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (jp_algo_errado == 1) {
                        nota_pronta++;
                        JOptionPane.showConfirmDialog(null, "Nota adicionada com sucesso!",
                                "Nota adicionada!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        Files.write(caminho_do_arquivo, nota_em_byte);
                        input_titulo.setText("");
                        input_assunto.setText("");
                    } else if (jp_algo_errado == 0) {
                        JOptionPane.showConfirmDialog(null, "Corrija os campos errados!",
                                "Atenção!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                catch(Exception error) {
                    JOptionPane.showConfirmDialog(null, "Erro no diretório de arquivo!", "Atenção!",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //-------------------------------------------------------------------------
        //-----------AÇÃO DO BOTÃO NOVA LISTA--------------------------------------
        bt_nova_lista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //------IMAGENS------------------------------------------
                img_worried.setVisible(false);
                logo.setVisible(false);
                logo_mini.setVisible(true);
                //-------------------------------------------------------
                //-------PARTE NOVA NOTA---------------------------------
                txt_titulo.setVisible(false);
                txt_assunto.setVisible(false);
                input_titulo.setVisible(false);
                input_assunto.setVisible(false);
                bt_salvar.setVisible(false);
                //-------------------------------------------------------
                //------PARTE NOVA LISTA---------------------------------
                txt_nova_tarefa2.setVisible(true);
                checkBox1.setVisible(false);
                input_nova_tarefa.setVisible(true);
                bt_adicionar.setVisible(true);
                //-------------------------------------------------------
                //------PARTE VER TAREFAS--------------------------------
                txt_ver_nota.setVisible(false);
                //-------------------------------------------------------
                //------PARTE INFORMAÇÕES--------------------------------
                titulo_ver_tarefa.setVisible(false);
                titulo_add_lista.setVisible(false);
                titulo_add_nota.setVisible(false);
                como_add_nota.setVisible(false);
                como_add_lista.setVisible(false);
                como_ver_tarefa.setVisible(false);
                txt_desenvolvedores.setVisible(false);
                link_abel.setVisible(false);
                link_sabrina.setVisible(false);
                link_gustavo.setVisible(false);
                link_lavinia.setVisible(false);
                img_programming.setVisible(false);
                //-------------------------------------------------------
            }
        });
        //---------AÇÃO DO BOTÃO ADICIONAR LISTA-------------------------
        bt_adicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(input_nova_tarefa.getText().equals("")){
                    JOptionPane.showConfirmDialog(null, "Não é possivel adicionar uma tarefa vazia!",
                            "Cuidado!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String texto_tarefa = input_nova_tarefa.getText();
                    checkBox1.setText(texto_tarefa);
                    checkBox1.setVisible(true);
                    lista_pronta++;
                    input_nova_tarefa.setText("");
                }
            }
        });
        //-------------------------------------------------------------------------
        //------AÇÃO QUANDO O USUÁRIO CLICA NO CHECKBOX----------------------------
        checkBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(checkBox1.isSelected()) {
                    JOptionPane.showConfirmDialog(null, "Muito bem! Você concluiu esta tarefa.",
                            "Parabéns!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //-------------------------------------------------------------------------
        //-------AÇÃO DO BOTÃO VER TAREFAS-----------------------------------------
        bt_ver_tarefas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //------IMAGENS DE FUNDO---------------------------
                logo.setVisible(false);
                logo_mini.setVisible(true);
                //-------------------------------------------------
                //------PARTE DA NOVA NOTA-------------------------
                txt_titulo.setVisible(false);
                txt_assunto.setVisible(false);
                input_titulo.setVisible(false);
                input_assunto.setVisible(false);
                bt_salvar.setVisible(false);
                //-------------------------------------------------
                //------PARTE DA NOVA LISTA------------------------
                txt_nova_tarefa2.setVisible(false);
                input_nova_tarefa.setVisible(false);
                bt_adicionar.setVisible(false);
                checkBox1.setVisible(false);
                //------PARTE INFORMAÇÕES--------------------------
                titulo_ver_tarefa.setVisible(false);
                titulo_add_lista.setVisible(false);
                titulo_add_nota.setVisible(false);
                como_add_nota.setVisible(false);
                como_add_lista.setVisible(false);
                como_ver_tarefa.setVisible(false);
                txt_desenvolvedores.setVisible(false);
                link_abel.setVisible(false);
                link_sabrina.setVisible(false);
                link_gustavo.setVisible(false);
                link_lavinia.setVisible(false);
                img_programming.setVisible(false);
                //-------------------------------------------------
                //----EXIBINDO O ARQUIVO TEXTO QUE FOI CRIANDO-----//mude o diretório para o mesmo que esta salvando o arquivo texto.
                Path caminho_ler_arq = Paths.get("C:/Users/costa/Desktop/nota.txt");
                if(nota_pronta != 0 && lista_pronta != 0){
                    try{
                        byte[] arq_lido = Files.readAllBytes(caminho_ler_arq);
                        String arq = new String(arq_lido);
                        txt_ver_nota.setText(arq);
                        txt_ver_nota.setVisible(true);
                        checkBox1.setVisible(true);
                        img_worried.setVisible(false);
                    }catch (Exception a){
                        JOptionPane.showConfirmDialog(null, "Erro no diretório do arquivo, " +
                                        "corrija o local onde está sendo salvo o arquivo!", "Erro!",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if (nota_pronta != 0 && lista_pronta == 0){
                    try{
                        byte[] arq_lido = Files.readAllBytes(caminho_ler_arq);
                        String arq = new String(arq_lido);
                        txt_ver_nota.setText(arq);
                        txt_ver_nota.setVisible(true);
                        img_worried.setVisible(false);
                    }catch (Exception a){
                        JOptionPane.showConfirmDialog(null, "Erro no diretório do arquivo, " +
                                        "corrija o local onde está sendo salvo o arquivo!", "Erro!",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if (nota_pronta == 0 && lista_pronta != 0){
                    checkBox1.setVisible(true);
                }
                else {
                    img_worried.setVisible(true);
                }
            }
        });
        //---------------------------------------------------------
        //-------AÇÃO DO BOTÃO INFORMAÇÕES-------------------------
        bt_informacoes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //------IMAGENS DE FUNDO---------------------------
                img_worried.setVisible(false);
                logo.setVisible(false);
                logo_mini.setVisible(true);
                //-------------------------------------------------
                //------PARTE DA NOVA NOTA-------------------------
                txt_titulo.setVisible(false);
                txt_assunto.setVisible(false);
                input_titulo.setVisible(false);
                input_assunto.setVisible(false);
                bt_salvar.setVisible(false);
                //-------------------------------------------------
                //------PARTE DA NOVA LISTA------------------------
                txt_nova_tarefa2.setVisible(false);
                input_nova_tarefa.setVisible(false);
                bt_adicionar.setVisible(false);
                checkBox1.setVisible(false);
                //-------------------------------------------------
                //------PARTE VER TAREFAS--------------------------
                txt_ver_nota.setVisible(false);
                //-------------------------------------------------
                //------PARTE INFORMAÇÕES--------------------------
                titulo_ver_tarefa.setVisible(true);
                titulo_add_lista.setVisible(true);
                titulo_add_nota.setVisible(true);
                como_add_nota.setVisible(true);
                como_add_lista.setVisible(true);
                como_ver_tarefa.setVisible(true);
                txt_desenvolvedores.setVisible(true);
                link_abel.setVisible(true);
                link_sabrina.setVisible(true);
                link_gustavo.setVisible(true);
                link_lavinia.setVisible(true);
                img_programming.setVisible(true);
                //-------------------------------------------------
            }
        });
        //---------------------------------------------------------
//-------FIM DA AÇÃO DE TODOS OS BOTÕES----------------------------
//--------ADICIONANDO OS COMPONENTES NA TELA-----------------------
        add(txt_assunto);
        add(input_assunto);
        add(input_titulo);
        add(txt_titulo);
        add(bt_salvar);
        add(bt_nova_nota);
        add(bt_nova_lista);
        add(bt_ver_tarefas);
        add(checkBox1);
        add(bt_adicionar);
        add(input_nova_tarefa);
        add(txt_ver_nota);
        add(bt_informacoes);
        add(como_add_nota);
        add(como_add_lista);
        add(como_ver_tarefa);
        add(titulo_add_nota);
        add(titulo_add_lista);
        add(titulo_ver_tarefa);
        add(txt_nova_tarefa2);
        add(logo);
        add(logo_mini);
        add(img_worried);
        add(txt_desenvolvedores);
        add(link_abel);
        add(link_sabrina);
        add(link_gustavo);
        add(link_lavinia);
        add(img_programming);
//-------------------------------------------------------------------------
//--------CONFIGURAÇÕES NECESSÁRIAS PARA O JFRAME--------------------------
        setTitle("SealNotes");
        setSize(800,550);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(cor_background);
//-------------------------------------------------------------------------
    }
    //Método que altera a logo do JAVA para imagem de nossa escolha.
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("LogoPrincipal.png")));
    }
}