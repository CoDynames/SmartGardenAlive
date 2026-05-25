import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmartGardenAlive extends JFrame {
// Declaração de Variaveis
private boolean p = false;
private boolean q = false;
private boolean r = false;

//Declaração Objetos
private JToggleButton BotaoP;
private JLabel labelstatusregador;

//-------------------------------------------------------------------------------------------------------------------------

public SmartGardenAlive() {

setTitle("Smart Garden Alive");
setSize(500,400);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setLayout(new BorderLayout());

// PAINEL DO SENSOR P
JPanel painelTopo = new JPanel();
BotaoP = new JToggleButton("Sensor P (Terra Seca) : Não");// <-BOTAO P
painelTopo.add(BotaoP);// <- ADICIONA O BOTÃO P NO PAINEL
add(painelTopo, BorderLayout.NORTH);// <- COLOCA O PAINEL NO CENTRO DA JANELA

labelstatusregador = new JLabel("IRRIGADOR : DESLIGADO");
labelstatusregador.setFont(new Font("Arial", Font.BOLD, 16));
add(labelstatusregador, BorderLayout.CENTER);

//-----------------------------------------------------------------------------------------------------------------------
ActionListener escutador = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
     
    p = BotaoP.isSelected();
    
    BotaoP.setText("Sensor P (Terra Seca):" + (p ? "SIM" : "NÂO"));

    atualizarLogicaDoJardim();
    
    }   
};

BotaoP.addActionListener(escutador);

}
private void atualizarLogicaDoJardim() {

    boolean acionarRegador = p && !r;

    if (acionarRegador) {
        labelstatusregador.setText("Irrigador : LIGADO");
        labelstatusregador.setForeground(Color.BLUE);
    } else {
        labelstatusregador.setText("Irrigador : DESLIGADO");
        labelstatusregador.setForeground(Color.GRAY);
    }
}
public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            
            new SmartGardenAlive().setVisible(true);
        }
    });
}
}


