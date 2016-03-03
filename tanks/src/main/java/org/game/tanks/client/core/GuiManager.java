package org.game.tanks.client.core;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.client.gui.widgets.Focusable;
import org.game.tanks.client.gui.widgets.GuiComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GuiManager implements Focusable {

  private List<GuiComponent> visibleComponents;

  @Autowired
  GameDisplay display;
  @Autowired
  PlayerInput playerInput;
  @Autowired
  ApplicationContext ctx;
  @Autowired
  GameEngine engine;

  GuiComponent focusedGuiComponent = null;

  @PostConstruct
  public void init() {
    playerInput.setInputListener(this);
    visibleComponents = new ArrayList<>();

  }

  public void draw() {
    for (GuiComponent component : visibleComponents) {
      if (component.isVisible()) {
        component.draw(display.getGraphics());
      }
    }
    // Draw focused Component in front of all other components
    if (focusedGuiComponent != null) {
      focusedGuiComponent.draw(display.getGraphics());
    }
  }

  public void update() {
    // XXX: Update this every frame
    // Hud should load player positions on the map, ammo, hp, fps, money etc

    // XXX: update this every 250ms or more
    // Chat window should load new chat messages
    // Console window should load server messages
    // TeamStats windows should be updated with new data, other player pings, kills, deaths, team etc
  }

  public void showComponent(GuiComponent component) {
    if (!visibleComponents.contains(component)) {
      visibleComponents.add(component);
    }
    component.setVisible(true);
  }

  public void showAndFocusComponent(GuiComponent component) {
    if (!visibleComponents.contains(component)) {
      visibleComponents.add(component);
    }
    component.setVisible(true);

    if (focusedGuiComponent == null) {
      focusedGuiComponent = component;
      focusedGuiComponent.onFocus();
    } else if (!focusedGuiComponent.equals(component)) {
      focusedGuiComponent.onFocusLost();
      focusedGuiComponent = component;
      focusedGuiComponent.onFocus();
    } else {
      return;// Do nothing, component already focused
    }
  }

  public void hideComponent(GuiComponent component) {
    component.setVisible(false);
    visibleComponents.remove(component);
    if (focusedGuiComponent.equals(component)) {
      focusedGuiComponent.onFocusLost();
      if (!visibleComponents.isEmpty()) {
        focusedGuiComponent = visibleComponents.get(visibleComponents.size() - 1);// Focus last component from the list
                                                                                  // - it was rendered last so its in
                                                                                  // front of other ones
        focusedGuiComponent.onFocus();
      } else {
        focusedGuiComponent = null;
      }
    }
  }

  public void hideAllComponents() {
    for (GuiComponent comp : visibleComponents) {
      comp.setVisible(false);
    }
    focusedGuiComponent.onFocusLost();
    focusedGuiComponent = null;
  }

  private GuiComponent requestFocusAt(int x, int y) {
    // If mouse click was outside currently focused component - loose focus
    if (focusedGuiComponent != null) {
      if (focusedGuiComponent.contains(x, y)) {
        return focusedGuiComponent;
      } else {
        focusedGuiComponent.onFocusLost();
      }
    }
    // Iterate from the end because this is the order of rendering from back to front - focus on component most in front
    for (int i = visibleComponents.size() - 1; i >= 0; i--) {
      GuiComponent comp = visibleComponents.get(i);
      if (comp.isVisible() && comp.contains(x, y)) {
        focusedGuiComponent = comp;
        focusedGuiComponent.onFocus();
        return focusedGuiComponent;
      }
    }
    focusedGuiComponent = null;
    return focusedGuiComponent;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.keyPressed(e);
    } else {
      engine.getCurrentState().keyPressed(e);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.keyReleased(e);
    } else {
      engine.getCurrentState().keyReleased(e);
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.mouseMoved(e);
    } else {
      engine.getCurrentState().mouseMoved(e);
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.mouseDragged(e);
    } else {
      engine.getCurrentState().mouseDragged(e);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    focusedGuiComponent = requestFocusAt(e.getX(), e.getY());
    if (focusedGuiComponent != null) {
      focusedGuiComponent.mouseReleased(e);
    } else {
      engine.getCurrentState().mouseReleased(e);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.mouseReleased(e);
    } else {
      engine.getCurrentState().mouseReleased(e);
    }
  }

  @Override
  public void onFocusLost() {
    // TODO Auto-generated method stub
  }

  @Override
  public void onFocus() {
    // TODO Auto-generated method stub
  }

}
