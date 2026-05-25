import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;
import java.util.jar.JarEntry;

public class SmartGardenAlive extends JFrame {
// Declaração de Variaveis
private boolean p = false;// <- Terra seca
private boolean q = false;// <- Sol Forte
private boolean r = false;// <- Praga detectada

//Declaração Objetos
private JToggleButton BotaoP, BotaoQ, BotaoR;// <- Botões
private JLabel labelPlantaArte, labelVisorImagens;// <- Desenho da planta
private JLabel labelstatusregador, LabelStatusToldo, LabelStatusRepelente;// <- Tela de status

//Controle de audio
private Clip somClique, somAlerta, musicaFundo;

//Carregador de imagens
private ImageIcon imgSaudavel;
private ImageIcon imgCuidado;
private ImageIcon imgPerigo;

//-------------------------------------------------------------------------------------------------------------------------

public SmartGardenAlive() {
// Configuraçoes basicas da janela
setTitle("Smart Garden Alive");
setSize(750,520);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setLayout(new BorderLayout(15,15));
getContentPane().setBackground(new Color(30,30,36));

// 
imgSaudavel = carregarERedimensionarImagem("planta_saudavel.png",250, 250);
imgCuidado  = carregarERedimensionarImagem("planta_cuidada.png",250, 250);
imgPerigo   = carregarERedimensionarImagem("planta_perigo.png",250, 250);

/* 
JPanel painelTopo = new JPanel();
BotaoP = new JToggleButton("Sensor P (Terra Seca) : Não");// <-BOTAO P
painelTopo.add(BotaoP);// <- ADICIONA O BOTÃO P NO PAINEL
add(painelTopo, BorderLayout.NORTH);// <- COLOCA O PAINEL NO CENTRO DA JANELA

labelstatusregador = new JLabel("IRRIGADOR : DESLIGADO");
labelstatusregador.setFont(new Font("Arial", Font.BOLD, 16));
add(labelstatusregador, BorderLayout.CENTER);
 */
//
JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 15, 0));
painelBotoes.setBackground(new Color(30,30,36));

BotaoP = new JToggleButton("Terra Seca : NÃO");
BotaoQ = new JToggleButton("Sol Forte : NÃO");
BotaoR = new JToggleButton("Praga Ativa : NÃO");

estilizadorBotao(BotaoP);
estilizadorBotao(BotaoQ);
estilizadorBotao(BotaoR);

painelBotoes.add(BotaoP);
painelBotoes.add(BotaoQ);
painelBotoes.add(BotaoR);
add(painelBotoes, BorderLayout.NORTH);

//
JPanel painelVisor = new JPanel(new BorderLayout());
painelVisor. setBackground(new Color(20,20,24));
painelVisor.setBorder(BorderFactory.createLineBorder(new Color(0,255,150), 1));

//
labelVisorImagens = new JLabel (imgSaudavel, SwingConstants.CENTER);
 painelVisor.add(labelVisorImagens, BorderLayout.CENTER);
add(painelVisor, BorderLayout.CENTER);

//
JPanel PainelStatus = new JPanel(new GridLayout(3,1,10,10));
PainelStatus.setBackground(new Color(30,30,36));
PainelStatus.setPreferredSize(new Dimension(220, 0));

labelstatusregador = new JLabel("Irrigador : Desligado", SwingConstants.CENTER);
LabelStatusToldo = new JLabel("Toldo : Recolhido", SwingConstants.CENTER);
LabelStatusRepelente = new JLabel("Repelente : Desligado", SwingConstants.CENTER);

configurarLabelStatus(labelstatusregador);
configurarLabelStatus(LabelStatusToldo);
configurarLabelStatus(LabelStatusRepelente);
add(PainelStatus,BorderLayout.EAST);

//
inicializadorSons();


//-----------------------------------------------------------------------------------------------------------------------
ActionListener escutador = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
     //
    p = BotaoP.isSelected();
    q = BotaoQ.isSelected();
    r = BotaoR.isSelected();

    
    BotaoP.setText("Sensor P (Terra Seca):" + (p ? "SIM" : "NÂO"));
    BotaoQ.setText("Sensor Q (Sol Forte) " + (q ? "SIM" : "NÃO"));
    BotaoR.setText("Sensor R (Praga Detectada) " + (r ? "SIM" : "NÃO"));
    
    if (p || q || r) {
        tocarSom(somAlerta);
    }

    atualizarMotorGraficoComImagens();
    
    }   
};

BotaoP.addActionListener(escutador);
BotaoQ.addActionListener(escutador);
BotaoR.addActionListener(escutador);

} // 

private ImageIcon carregarERedimensionarImagem(String caminho, int largura, int altura) {
File arquivo = new File(caminho);

if (arquivo.exists()) {
    ImageIcon iconeOriginal = new ImageIcon(caminho);

    Image imagemTransformada = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
    return  new ImageIcon(imagemTransformada);
}else{
    System.out.println("Arquivo" + caminho + "pasta não encontrada");
    return null;
}

}

private void atualizarMotorGraficoComImagens() {
    boolean LigarRegador = p && !r;
    boolean EstenderToldo = q && !r;
    boolean LigarRepelente = r;

    if(LigarRegador) {
        labelstatusregador.setText("Irrigador : LIGADO");
        labelstatusregador.setForeground(new Color(50,190,255));

    } else {
        labelstatusregador.setText(p && r ? "Irrigador : BLOQUEADO " : "Irrigador : DESLIGADO");
        labelstatusregador.setForeground(Color.GRAY);
    }

    





}


}


