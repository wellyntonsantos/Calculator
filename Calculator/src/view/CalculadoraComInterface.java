package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraComInterface extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField display;
    private double num1, num2, resultado;
    private char operacao;

    public CalculadoraComInterface() {
        // Configuração da janela
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuração do display
        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Configuração dos botões
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                // Entrada numérica
                display.setText(display.getText() + command);
            } else if ("+-*/".contains(command)) {
                // Operadores
                num1 = Double.parseDouble(display.getText());
                operacao = command.charAt(0);
                display.setText("");
            } else if (command.equals("=")) {
                // Calcula o resultado
                num2 = Double.parseDouble(display.getText());
                resultado = calcular();
                display.setText(String.valueOf(resultado));
            }
        } catch (NumberFormatException ex) {
            // Trata entrada inválida
            JOptionPane.showMessageDialog(this, "Entrada inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            display.setText("");
        }
    }

    private double calcular() {
        switch (operacao) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: Divisão por zero não permitida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    display.setText("");
                    return 0;
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraComInterface());
    }
}
