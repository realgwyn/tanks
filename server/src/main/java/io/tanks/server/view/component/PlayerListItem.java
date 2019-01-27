package io.tanks.server.view.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import io.tanks.common.network.model.command.admin.BanPlayer;
import io.tanks.common.network.model.command.admin.KickPlayer;
import io.tanks.server.core.ServerController;
import io.tanks.server.model.PlayerServerModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PlayerListItem extends JPanel {

  private PlayerServerModel playerServerModel;
  private ServerController controller;

  JLabel lblRankNumber;
  JLabel lblName;
  JLabel lblId;
  JLabel lblKills;
  JLabel lblDeaths;
  JLabel lblLatency;
  JButton btnKick;
  JButton btnBan;

  public PlayerListItem(PlayerServerModel playerServerModel, ServerController controller) {
    this.playerServerModel = playerServerModel;
    this.controller = controller;
    setLayout(
        new FormLayout(
            new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("40px"), FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("160px"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("80px"),
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("40px"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("40px"),
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("40px"), FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC, },
            new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

    lblRankNumber = new JLabel();
    add(lblRankNumber, "2, 2");

    lblName = new JLabel();
    add(lblName, "4, 2");

    lblId = new JLabel();
    add(lblId, "6, 2");

    lblKills = new JLabel();
    add(lblKills, "8, 2");

    lblDeaths = new JLabel();
    add(lblDeaths, "10, 2");

    lblLatency = new JLabel();
    add(lblLatency, "12, 2");

    btnKick = new JButton("Kick");
    add(btnKick, "14, 2");

    btnBan = new JButton("Ban");

    add(btnBan, "16, 2");
    setModel(playerServerModel);
    initActions();
  }

  private void initActions() {
    btnKick.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.actionKickPlayer(new KickPlayer().setPlayerId(playerServerModel.getConnectionId()));
      }
    });
    btnBan.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.actionBanPlayer(new BanPlayer().setPlayerId(playerServerModel.getConnectionId()).setBanTimeLenghtMinutes(60));
      }
    });
  }

  private void setModel(PlayerServerModel model) {
    lblRankNumber.setText("?");
    lblName.setText(model.getPlayerName());
    lblId.setText(Long.toString(model.getConnectionId()));
    lblKills.setText(Integer.toString(model.getKills()));
    lblDeaths.setText(Integer.toString(model.getDeaths()));
    lblLatency.setText(Integer.toString(model.getLatency()));
  }

  public PlayerServerModel getModel() {
    return playerServerModel;
  }

  public void refresh() {
    setModel(playerServerModel);
  }

}
