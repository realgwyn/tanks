package org.game.tanks.client.core;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.client.view.GuiComponent;
import org.game.tanks.client.view.components.MessageWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Receives Player Input actions<br>
 * Renders GuiComponents<br>
 * Forwards Player Input actions to GuiComponents or GameWorld<br>
 * 
 * @author rafal.kojta
 */
@Component
public class GuiManager implements PlayerInputListener {

  private List<GuiComponent> visibleComponents;

  @Autowired
  GameDisplay display;
  @Autowired
  PlayerInput playerInput;
  @Autowired
  ApplicationContext ctx;
  @Autowired
  GameEngine engine;
  @Autowired
  MessageWindow messageWindow;

  GuiComponent focusedGuiComponent = null;
  GuiComponent hoveringComponent = null;

  @PostConstruct
  public void init() {
    playerInput.setInputListener(this);
    visibleComponents = new ArrayList<>();
  }

  public void draw() {
    for (GuiComponent component : visibleComponents) {
      if (component.isVisible()) {
        component.paintComponent(display.getGraphics());
      }
    }
    // Draw focused Component in front of all other components
    if (focusedGuiComponent != null) {
      focusedGuiComponent.paintComponent(display.getGraphics());
    }
  }

  public void update() {
    // XXX: might need performance improvement - animate hud every frame, but other components every 5-10 frames
    for (GuiComponent component : visibleComponents) {
      component.update();
    }

    // XXX: Update this every frame
    // Hud should load player positions on the map, ammo, hp, fps, money etc

    // XXX: update this every 250ms or more
    // Chat window should load new chat messages
    // Console window should load server messages
    // TeamStats windows should be updated with new data, other player pings, kills, deaths, team etc

    messageWindow.update();// messageWindow fade away while the time passes
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

    if (component.isFocusable()) {
      if (focusedGuiComponent == null) {
        focusedGuiComponent = component;
        focusedGuiComponent.setFocused(true);
      } else if (!focusedGuiComponent.equals(component)) {
        focusedGuiComponent.setFocused(false);
        focusedGuiComponent = component;
        focusedGuiComponent.setFocused(true);
      } else {
        return;// Do nothing, component already focused
      }
    }
  }

  public void hideComponent(GuiComponent component) {
    component.setVisible(false);
    visibleComponents.remove(component);
    if (focusedGuiComponent != null && focusedGuiComponent.equals(component)) {
      focusNextComponent();
    }
  }

  public void hideAllComponents() {
    for (GuiComponent comp : visibleComponents) {
      comp.setVisible(false);
    }
    visibleComponents = new ArrayList<GuiComponent>();
    if (focusedGuiComponent != null) {
      focusedGuiComponent.setFocused(false);
      focusedGuiComponent = null;
    }
    messageWindow.setVisible(false);
  }

  private GuiComponent hoverComponentAt(int x, int y) {
    // Disable hover on current component
    if (hoveringComponent != null) {
      hoveringComponent.setHovering(false);
      hoveringComponent = null;
    }

    // Focused components are in front of all other Gui component, first check if it is hovering
    if (focusedGuiComponent != null) {
      GuiComponent comp = focusedGuiComponent.getComponentAt(x, y);
      if (comp != null) {
        hoveringComponent = comp;
        hoveringComponent.setHovering(true);
        return hoveringComponent;
      }
    }

    // Iterate from the end because this is the order of rendering from back to front - focus on component most in front
    for (int i = visibleComponents.size() - 1; i >= 0; i--) {
      GuiComponent root = visibleComponents.get(i);
      if (root.isVisible() && root.isFocusable()) {
        GuiComponent comp = root.getComponentAt(x, y);
        if (comp != null) {
          hoveringComponent = comp;
          hoveringComponent.setHovering(true);
          return hoveringComponent;
        }
      }
    }

    return null;
  }

  private GuiComponent focusComponentAt(int x, int y) {
    // If mouse click was outside currently focused component - lose focus
    if (focusedGuiComponent != null) {
      GuiComponent comp = focusedGuiComponent.getComponentAt(x, y);
      // Lose focus on current component (maybe its child is gaining focus right now)
      focusedGuiComponent.setFocused(false);
      focusedGuiComponent = null;
      if (comp != null) {
        focusedGuiComponent = comp;
        focusedGuiComponent.setFocused(true);
        return focusedGuiComponent;
      }
    }
    // Iterate from the end because this is the order of rendering from back to front - focus on component most in front
    GuiComponent root = null;
    for (int i = visibleComponents.size() - 1; i >= 0; i--) {
      root = visibleComponents.get(i);
      if (root.isVisible() && root.isFocusable()) {
        GuiComponent comp = root.getComponentAt(x, y);
        if (comp != null) {
          focusedGuiComponent = comp;
          focusedGuiComponent.setFocused(true);
          return focusedGuiComponent;
        }
      }
    }
    return null;
  }

  private GuiComponent focusNextComponent() {
    if (focusedGuiComponent != null) {
      focusedGuiComponent.setFocused(false);
      focusedGuiComponent = null;
    }

    if (!visibleComponents.isEmpty()) {
      for (int i = visibleComponents.size() - 1; i >= 0; i--) {
        GuiComponent comp = visibleComponents.get(i);
        if (comp.isVisible() && comp.isFocusable()) {
          focusedGuiComponent = comp;
          focusedGuiComponent.setFocusable(true);
          return focusedGuiComponent;
        }
      }
    }
    return null;
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
    GuiComponent comp = hoverComponentAt(e.getX(), e.getY());
    if (comp != null) {
      comp.mouseMoved(e);
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
    GuiComponent comp = focusComponentAt(e.getX(), e.getY());
    if (comp != null) {
      comp.mousePressed(e);
    } else {
      engine.getCurrentState().mousePressed(e);
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

}
