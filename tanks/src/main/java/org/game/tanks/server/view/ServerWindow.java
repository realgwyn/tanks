package org.game.tanks.server.view;

import java.awt.EventQueue;
import java.beans.Beans;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.game.tanks.server.core.ServerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@Component
public class ServerWindow extends JFrame {

  @Autowired
  ServerController controller;

  private JPanel contentPane;
  @Autowired
  private ControlsPanel controlsPanel;
  private MapPanel mapPanel;
  @Autowired
  private PlayersListPanel playersListPanel;
  private ChatPanel chatPanel;
  @Autowired
  private ServerLogPanel serverLogPanel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          ServerWindow frame = new ServerWindow();
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
  public ServerWindow() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 955, 465);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new FormLayout(
        new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
            FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("200px"), },
        new RowSpec[] { RowSpec.decode("200px"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
            FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

    if (Beans.isDesignTime()) {
      controlsPanel = new ControlsPanel();
      mapPanel = new MapPanel();
      playersListPanel = new PlayersListPanel();
      chatPanel = new ChatPanel();
      serverLogPanel = new ServerLogPanel();
    }

    contentPane.add(controlsPanel, "1, 1, fill, fill");
    contentPane.add(playersListPanel, "3, 1, fill, fill");

    mapPanel = new MapPanel();

    contentPane.add(mapPanel, "5, 1, fill, fill");

    chatPanel = new ChatPanel();
    contentPane.add(chatPanel, "1, 3, 5, 1, fill, fill");

    contentPane.add(serverLogPanel, "1, 5, 5, 1, fill, fill");
  }

}
