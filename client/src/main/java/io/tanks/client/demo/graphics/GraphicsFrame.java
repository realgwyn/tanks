package io.tanks.client.demo.graphics;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GraphicsFrame extends JFrame {

  public JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          GraphicsFrame frame = new GraphicsFrame();
          frame.setVisible(true);

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public GraphicsFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new RotationPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));

    setContentPane(contentPane);
  }

}
