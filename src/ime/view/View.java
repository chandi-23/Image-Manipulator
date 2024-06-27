package ime.view;

import ime.controller.Features;
import ime.model.IViewModel;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Desktop;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.InputMismatchException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * The View class extends JFrame and implements the IView interface, providing
 * a graphical user interface (GUI) for displaying images and interacting with the user.
 * It is constructed with an implementation of the IViewModel interface to retrieve image data.
 */
public class View extends JFrame implements IView {

  private final IViewModel model;
  private JPanel inputPanel;
  private JComboBox<String> comboBox;
  private JButton flipHorizontalButton;
  private JButton flipVerticalButton;
  private JButton redComponentButton;
  private JButton blueComponentButton;
  private JButton greenComponentButton;
  private JLabel currentImageLabel;
  private JLabel histogramImageLabel;
  private JCheckBox splitCheckBox;
  private JTextField splitPercentageTextField;
  private JButton goButton;
  private JTextField compressionPercentageTextField;
  private JTextField blackTextField;
  private JTextField midTextField;
  private JTextField whiteTextField;
  private boolean isImageSaved;
  private JMenuItem loadMenuItem;
  private JMenuItem saveMenuItem;
  private  JMenuItem useMeMenuItem;
  private JPanel legendPanel;

  /**
   * Constructs a new View with the specified IViewModel model.
   *
   * @param model The model providing image data through the IViewModel interface.
   */
  public View(IViewModel model) {
    super();

    this.model = model;

    setUpWindowConfigurations();
    setUpMenuBar();
    setUpInputComponents();

    // Setting up the main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // Panel for displaying the current image and its histogram
    JPanel imagePanel = createImageContainer();
    mainPanel.add(imagePanel);

    JPanel actions = createActionsPanel();
    mainPanel.add(actions);

  }

  @Override
  public void addFeatures(Features controller) {

    comboBox.addActionListener(e -> updateInputPanel());
    loadMenuItem.addActionListener(e -> load(controller));
    saveMenuItem.addActionListener(e -> save(controller));
    useMeMenuItem.addActionListener(e -> openLink("https://github.khoury.northeastern.edu/pnavada/"
            + "PDP-Assignment-4/blob/temp/view_test/USEME.md"));

    redComponentButton.addActionListener(e -> simpleEffectsListeners(
            "redComponent", controller));
    greenComponentButton.addActionListener(e -> simpleEffectsListeners(
            "greenComponent", controller));
    blueComponentButton.addActionListener(e -> simpleEffectsListeners(
            "blueComponent", controller));
    flipHorizontalButton.addActionListener(e -> simpleEffectsListeners(
            "flipHorizontal", controller));
    flipVerticalButton.addActionListener(e -> simpleEffectsListeners(
            "flipVertical", controller));

    goButton.addActionListener(e -> filters(controller));
    splitCheckBox.addActionListener(e -> splitViewDisabled(e, controller));

  }

  @Override
  public void refresh(String currentImageName, String histogramImageName) {
    int[][][] currentImageData = model.getImage(currentImageName);
    displayCurrentImage(currentImageData);
    int[][][] histogramImageData = model.getImage(histogramImageName);
    displayHistogramImage(histogramImageData);
  }


  private void setUpWindowConfigurations() {
    // Setting up the window
    setTitle("GRIME");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void setUpInputComponents() {
    setImageSaved(FALSE);
    goButton = new JButton("Go");
    compressionPercentageTextField = new JTextField(3);
    blackTextField = new JTextField(3);
    midTextField = new JTextField(3);
    whiteTextField = new JTextField(3);
    splitCheckBox = new JCheckBox("Split");
    splitPercentageTextField = new JTextField(3);
  }

  private void setUpMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu fileMenu = new JMenu("File");
    JMenu helpMenu = new JMenu("Help");
    menuBar.add(fileMenu);
    menuBar.add(helpMenu);

    loadMenuItem = new JMenuItem("Load");
    saveMenuItem = new JMenuItem("Save as");
    useMeMenuItem = new JMenuItem("How to use");

    fileMenu.add(loadMenuItem);
    fileMenu.add(saveMenuItem);

    helpMenu.add(useMeMenuItem);

    menuBar.setBorder(BorderFactory.createEmptyBorder());

  }

  private JPanel createActionsPanel() {
    JPanel actionPanel = new JPanel();
    actionPanel.setLayout(new GridLayout(1, 0, 10, 10));
    JPanel quickAccess = simpleActionPanel();

    actionPanel.add(quickAccess);

    JPanel comboBoxPanel = createSelectFilter();
    actionPanel.add(comboBoxPanel);

    // A panel for accepting inputs from the user
    inputPanel = new JPanel(new GridLayout(0, 1));
    comboBoxPanel.add(inputPanel);
    return actionPanel;
  }

  private void setImageSaved(boolean state) {
    isImageSaved = state;
  }
  
  private boolean getIsImageSaved() {
    return isImageSaved;
  }

  private JPanel simpleActionPanel() {

    JPanel simpleEffects = new JPanel();
    simpleEffects.setLayout(new GridLayout(3, 4, 5, 5));
    simpleEffects.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Simple Effects", TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION, new Font("SF Pro variant", Font.BOLD, 14)));
    flipHorizontalButton = new JButton("Flip Horizontal");
    flipVerticalButton = new JButton("Flip Vertical");
    redComponentButton = new JButton("Red Component");
    blueComponentButton = new JButton("Blue Component");
    greenComponentButton = new JButton("Green Component");

    simpleEffects.add(flipHorizontalButton);
    simpleEffects.add(flipVerticalButton);
    simpleEffects.add(redComponentButton);
    simpleEffects.add(blueComponentButton);
    simpleEffects.add(greenComponentButton);

    return simpleEffects;
  }


  private JPanel createImageContainer() {

    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));

    // Display current image with a scroll bar
    currentImageLabel = new JLabel();
    JScrollPane currImageScrollPane = new JScrollPane(currentImageLabel);
    currImageScrollPane.setPreferredSize(new Dimension(100, 400));
    imagePanel.add(currImageScrollPane);
    currImageScrollPane.setBorder(new LineBorder(Color.GRAY, 1));
    currImageScrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Current Image", TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION, new Font("SF Pro variant", Font.BOLD, 14)));


    histogramImageLabel = new JLabel();
    JScrollPane histImageScrollPane = new JScrollPane(histogramImageLabel);
    histImageScrollPane.setPreferredSize(new Dimension(100, 400));
    histImageScrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Live Histogram", TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION, new Font("SF Pro variant", Font.BOLD, 14)));

    addLegend();

    // Create a container panel to hold the histogram and legend
    JPanel containerPanel = new JPanel(new BorderLayout());
    containerPanel.add(histImageScrollPane, BorderLayout.CENTER);
    containerPanel.add(legendPanel, BorderLayout.SOUTH);

    imagePanel.add(containerPanel);

    return imagePanel;

  }

  private void addLegend() {
    // Create a legend panel
    legendPanel = new JPanel();
    legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));

    legendPanel.add(new JLabel("<html><font color='blue'>--</font> Blue component</html>"));
    legendPanel.add(new JLabel("<html><font color='red'>--</font> Red component</html>"));
    legendPanel.add(new JLabel("<html><font color='green'>--</font> Green component</html>"));
    legendPanel.setBorder(new LineBorder(Color.GRAY, 1));
    legendPanel.setBorder(BorderFactory.createTitledBorder("Histogram Details"));
    legendPanel.setVisible(false);
  }

  private JPanel createSelectFilter() {

    JPanel comboBoxPanel = new JPanel();
    comboBoxPanel.setBorder(BorderFactory.createTitledBorder("Advanced Effects"));
    comboBoxPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Advanced Effects", TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION, new Font("SF Pro variant", Font.BOLD, 14)));
    comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.PAGE_AXIS));

    // Create JComboBox for selecting filters
    JLabel comboBoxDisplay = new JLabel("Select the effect");
    comboBoxPanel.add(comboBoxDisplay);
    comboBox = new JComboBox<>(new String[]{
        "None",
        "Adjust Levels",
        "Blur",
        "Color Correct",
        "Compress",
        "Luma",
        "Sepia",
        "Sharpen"
    });

    comboBoxPanel.add(comboBox);

    return comboBoxPanel;
  }

  private void openLink(String link) {
    try {
      Desktop.getDesktop().browse(new URI(link));
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  private String saveAs() {

    JFileChooser fileChooser = getFileChooser();
    fileChooser.setDialogTitle("Save as");

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      String filePath = fileToSave.getAbsolutePath();
      FileNameExtensionFilter selectedFilter =
              (FileNameExtensionFilter) fileChooser.getFileFilter();
      String extension = selectedFilter.getExtensions()[0];
      return filePath + "." + extension;
    }

    return "";
  }

  private JFileChooser getFileChooser() {

    JFileChooser fileChooser = new JFileChooser();

    // Add file filters for PNG, PPM and JPEG
    FileNameExtensionFilter pngFilter = new FileNameExtensionFilter(
        "PNG files (*.png)", "png");
    FileNameExtensionFilter jpegFilter = new FileNameExtensionFilter(
        "JPEG files (*.jpeg, *.jpg)", "jpeg", "jpg");
    FileNameExtensionFilter ppmFilter = new FileNameExtensionFilter(
        "PPM files", "ppm");

    fileChooser.addChoosableFileFilter(pngFilter);
    fileChooser.addChoosableFileFilter(jpegFilter);
    fileChooser.addChoosableFileFilter(ppmFilter);

    // Set default filter to PNG
    fileChooser.setFileFilter(pngFilter);
    return fileChooser;
  }

  private void updateInputPanel() {

    String selectedFilter = (String) comboBox.getSelectedItem();
    inputPanel.removeAll();
    if ("None".equals(selectedFilter)) {
      inputPanel.setVisible(false);
      inputPanel.setPreferredSize(new Dimension(0, 0)); // Set preferred size to zero
      revalidate();
      repaint();
      return;
    } else {
      inputPanel.setVisible(true);
      inputPanel.setPreferredSize(null); // Reset preferred size
    }

    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    // A checkbox to enable/disable split view
    JPanel splitPanel = new JPanel();
    splitPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
    splitCheckBox.setSelected(false);
    splitPanel.add(splitCheckBox);
    splitPanel.add(new JLabel("Percentage:"));
    splitPanel.add(splitPercentageTextField);

    // Input components
    JPanel inputComponentsPanel = new JPanel();
    inputComponentsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
    JPanel inputs = new JPanel();

    if ("Compress".equals(selectedFilter)) {
      inputs.add(new JLabel("Compression Percentage:"));
      inputs.add(compressionPercentageTextField);
    } else if ("Adjust Levels".equals(selectedFilter)) {
      inputPanel.add(splitPanel);
      inputs.add(new JLabel("Black value"));
      inputs.add(blackTextField);
      inputs.add(new JLabel("Mid value"));
      inputs.add(midTextField);
      inputs.add(new JLabel("White Value"));
      inputs.add(whiteTextField);
    } else {
      inputPanel.add(splitPanel);
    }

    if (inputs.getComponentCount() > 0) {
      inputComponentsPanel.add(inputs);
      inputPanel.add(inputComponentsPanel);
    }

    // A button to see the effect
    JPanel goBtnPanel = new JPanel();
    goBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
    goBtnPanel.add(goButton);
    inputPanel.add(goBtnPanel);

    revalidate();
    repaint();

  }

  private void displayCurrentImage(int[][][] currentImageData) {
    BufferedImage currentImage = getDisplayImage(currentImageData);
    currentImageLabel.setIcon(new ImageIcon(currentImage));
    currentImageLabel.setHorizontalAlignment(JLabel.CENTER);
    currentImageLabel.setVerticalAlignment(JLabel.CENTER);
  }

  private void displayHistogramImage(int[][][] histogramImageData) {
    BufferedImage histogramImage = getDisplayImage(histogramImageData);
    ImageIcon scaledIcon = scaleImage(histogramImage,
            histogramImageLabel.getWidth(),
            histogramImageLabel.getHeight());
    histogramImageLabel.setIcon(scaledIcon);
    histogramImageLabel.setHorizontalAlignment(JLabel.CENTER);
    histogramImageLabel.setVerticalAlignment(JLabel.CENTER);
    legendPanel.setVisible(true);
  }

  private ImageIcon scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
    int padding = 10;
    Image scaledImage = originalImage.getScaledInstance(targetWidth - padding,
            targetHeight - padding, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
  }

  private BufferedImage getDisplayImage(int[][][] imageData) {
    int height = imageData.length;
    int width = imageData[0].length;
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    int[] data = new int[width * height];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = (imageData[y][x][0] << 16) | (imageData[y][x][1] << 8) | imageData[y][x][2];
        data[y * width + x] = rgb;
      }
    }
    bufferedImage.setRGB(0, 0, width, height, data, 0, width);
    return bufferedImage;
  }

  private void save(Features controller) {

    try {
      if (currentImageLabel.getIcon() != null) {
        String imageFilePath = saveAs();
        controller.save(imageFilePath);
        setImageSaved(TRUE);
        JOptionPane.showMessageDialog(
                null,
                "Saved Successfully",
                "Confirmation",
                JOptionPane.INFORMATION_MESSAGE);
      }
      else {
        JOptionPane.showMessageDialog(
                null,
                "Nothing to save",
                "Confirmation",
                JOptionPane.INFORMATION_MESSAGE);

      }
    } catch (IOException | CloneNotSupportedException ex) {
      showErrorDialog("Error Saving",
              "Error occurred while saving the image", ex);
    }
  }

  private void redComponent(Features controller) {
    try {
      controller.redComponent();
    } catch (IOException | CloneNotSupportedException ex) {
      showErrorDialog("Error",
              "Error occurred while creating the red component", ex);
    }
  }

  private void blueComponent(Features controller) {
    try {
      controller.blueComponent();
    } catch (IOException | CloneNotSupportedException ex) {
      showErrorDialog("Error", "Error occurred while creating the blue component", ex);
    }
  }

  private void greenComponent(Features controller) {
    try {
      controller.greenComponent();
    } catch (IOException | CloneNotSupportedException ex) {
      showErrorDialog("Error", "Error occurred while creating the green component", ex);
    }
  }

  private void flipHorizontal(Features controller) {
    try {
      controller.flipHorizontal();
    } catch (IOException | CloneNotSupportedException ex) {
      showErrorDialog("Error", "Error occurred while flipping the image horizontally", ex);
    }
  }

  private void flipVertical(Features controller) {
    try {
      controller.flipVertical();
    } catch (IOException | CloneNotSupportedException ex) {
      showErrorDialog("Error", "Error occurred while flipping the image vertically", ex);
    }
  }

  private void filters(Features controller) {
    if (currentImageLabel.getIcon() == null) {
      showErrorDialog("Invalid Image",
              "Please load the image",
              new IllegalArgumentException());
      return;
    }

    try {
      String selectedFilter = comboBox.getSelectedItem().toString();
      if ("Compress".equals(selectedFilter)) {
        double compressionFactor = Double.parseDouble(compressionPercentageTextField.getText());
        controller.compress(compressionFactor);
      }
      else if (splitCheckBox.isSelected()) {
        double split = Double.parseDouble(splitPercentageTextField.getText());
        switch (selectedFilter) {
          case "Adjust Levels":
            int black = Integer.parseInt(blackTextField.getText());
            int mid = Integer.parseInt(midTextField.getText());
            int white = Integer.parseInt(whiteTextField.getText());
            controller.adjustLevels(black, mid, white, split);
            break;
          case "Blur":
            controller.blur(split);
            break;
          case "Color Correct":
            controller.colorCorrect(split);
            break;
          case "Luma":
            controller.luma(split);
            break;
          case "Sepia":
            controller.sepia(split);
            break;
          case "Sharpen":
            controller.sharpen(split);
            break;
          default:
            System.out.println("Invalid Operation");
        }
      } else {
        switch (selectedFilter) {
          case "Adjust Levels":
            int black = Integer.parseInt(blackTextField.getText());
            int mid = Integer.parseInt(midTextField.getText());
            int white = Integer.parseInt(whiteTextField.getText());
            controller.adjustLevels(black, mid, white);
            break;
          case "Blur":
            controller.blur();
            break;
          case "Color Correct":
            controller.colorCorrect();
            break;
          case "Luma":
            controller.luma();
            break;
          case "Sepia":
            controller.sepia();
            break;
          case "Sharpen":
            controller.sharpen();
            break;
          default:
            System.out.println("Invalid Operation");
        }
      }

    } catch (IOException | CloneNotSupportedException | NullPointerException ex) {
      showErrorDialog("Error",
              "Error occurred while performing the selected operation",
              ex);
    }
    catch (NumberFormatException ex) {
      showErrorDialog("Error",
              "Please provide the valid input",
              ex);
    }
    catch (IllegalArgumentException exIArg) {
      showErrorDialog("Error",
              exIArg.getMessage(),
              exIArg);
    }
    setImageSaved(FALSE);
  }

  private void splitViewDisabled(ActionEvent e, Features controller) {
    JCheckBox cb = (JCheckBox) e.getSource();
    if (!cb.isSelected()) {
      controller.disableSplitView();
    }
  }

  @Override
  public void displayErrorMessage(Exception ex, String message) {
    showErrorDialog("Error", message, ex);
  }

  private void showErrorDialog(String title, String message, Exception ex) {
    JOptionPane.showMessageDialog(this,
        message,
        title,
        JOptionPane.ERROR_MESSAGE);
  }

  private void simpleEffectsListeners(String action, Features controller) {
    if (currentImageLabel.getIcon() == null) {
      showErrorDialog("Invalid Image",
              "Please load the image",
              new IllegalArgumentException());
    }
    switch (action) {
      case "redComponent" :
        redComponent(controller);
        break;
      case "greenComponent":
        greenComponent(controller);
        break;
      case "blueComponent":
        blueComponent(controller);
        break;
      case "flipHorizontal":
        flipHorizontal(controller);
        break;
      case "flipVertical":
        flipVertical(controller);
        break;
      default:
        System.out.println("Invalid Operation");
    }
    setImageSaved(FALSE);
  }

  private void load(Features controller) {
    String imageFilePath = loadFile();
    try {
      if (!getIsImageSaved() && currentImageLabel.getIcon() != null) {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to proceed to Save?",
                "Save image",
                JOptionPane.YES_NO_OPTION);

        // Check the user's response
        if (response == JOptionPane.YES_OPTION ) {
          saveMenuItem.doClick();
        } else {
          controller.load(imageFilePath);
        }
      }

      controller.load(imageFilePath);

    } catch (IOException | CloneNotSupportedException ex) {
      showErrorDialog("Error Opening",
              "Error occurred while opening the image", ex);
    }
    catch (InputMismatchException exInM) {
      showErrorDialog("Error Opening",
              "Would like to save the old Image before ", exInM);
    }
  }

  private String loadFile() {

    JFileChooser fileLoader = new JFileChooser(".");
    fileLoader.setDialogTitle("Open File");

    // Add file filters for PNG and JPEG
    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
            "Image files", "png", "jpeg", "jpg", "ppm");
    fileLoader.addChoosableFileFilter(imageFilter);
    // Set default filter to PNG
    fileLoader.setFileFilter(imageFilter);
    int userSelection = fileLoader.showOpenDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileLoader.getSelectedFile();
      return selectedFile.getAbsolutePath();
    }

    return "";

  }
}
