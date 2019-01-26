package org.game.tanks.server.view;


import java.awt.*;
import java.beans.Beans;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.game.tanks.server.core.ServerController;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@Component
public class ServerWindow extends JFrame {

  @Autowired
  private ControlsPanel controlsPanel;
  @Autowired
  private MapPanel mapPanel;
  @Autowired
  private PlayersListPanel playersListPanel;
  @Autowired
  private ChatPanel chatPanel;
  @Autowired
  private ServerLogPanel serverLogPanel;
  @Autowired
  ServerController controller;

  private JPanel contentPane;

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

  public ServerWindow()
      throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    setTitle("TanksServer 0.0.1");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 955, 465);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new FormLayout(
        new ColumnSpec[] { FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
            FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("200px"), },
        new RowSpec[] { RowSpec.decode("200px"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
            FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

    if (Beans.isDesignTime()) {
      controlsPanel = new ControlsPanel();
      mapPanel = new MapPanel();
      playersListPanel = new PlayersListPanel();
      chatPanel = new ChatPanel();
      serverLogPanel = new ServerLogPanel();
      init();
    }
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  }

  @PostConstruct
  public void init() {
    contentPane.add(controlsPanel, "1, 1, fill, fill");
    contentPane.add(playersListPanel, "3, 1, fill, fill");
    contentPane.add(mapPanel, "5, 1, fill, fill");
    contentPane.add(chatPanel, "1, 3, 5, 1, fill, fill");
    contentPane.add(serverLogPanel, "1, 5, 5, 1, fill, fill");
  }

  public void setPlayers(List<PlayerServerModel> players) {
    playersListPanel.setPlayers(players);
    mapPanel.setPlayers(players);
  }

  public void refresh() {
    playersListPanel.refresh();
  }

  public void setServerName(String string) {
    controlsPanel.setServerName(string);
  }

  public void setNetworkAddress(String networkAddress) {
    controlsPanel.setNetworkAddress(networkAddress);
  }

  public void setTcpPort(int port) {
    controlsPanel.setTcpPort(port);
  }

  public void setUdpPort(int port) {
    controlsPanel.setUdpPort(port);
  }

  public void setStatus(String string) {
    controlsPanel.setStatus(string);
  }

}
