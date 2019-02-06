/*
 * Created by JFormDesigner on Thu Jan 31 16:14:25 PST 2019
 */

package csulb.cecs327.Controllers.FrontEnd;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.*;
import csulb.cecs327.Models.*;
import csulb.cecs327.Services.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class AppUI extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Lexzander Saplan
    private JLabel SearchLabel;
    private JTextField searchBox;
    private JLabel playlistTitle;
    private JLabel LibraryTitle;
    private JRadioButton songFilter;
    private JRadioButton artistFilter;
    private JRadioButton genreFilter;
    private JScrollPane songInfoPane;
    private JTable songInfoTable;
    private JScrollPane playlistPane;
    private JList playlistItems;
    private JLabel Song;
    private JLabel artist;
    private JButton shuffleButton;
    private JButton previousButton;
    private JButton playPauseButton;
    private JButton nextButton;
    private JButton muteButton;
    private JSlider volumeSlider;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // Custom Variables
    private int currentSong = 0;
    private SongDatabase songDatabase = new SongDatabase();
    private String song = songDatabase.getSongList().get(currentSong);
    private MusicPlayer player = new MusicPlayer(song);
    private DefaultTableModel model;
    private User user;

    // Constructor
    public AppUI(User user) {
        initComponents();
        this.user = user;
    }

    // Methods

    /**
     * This button allows the user to play or pause a song
     *
     * @param e The action performed when clicking the button
     */
    private void playPauseButtonActionPerformed(ActionEvent e) {
        if (playPauseButton.getText().equals("Play")) {
            playPauseButton.setText("Pause");
            player.play();
        } else {
            playPauseButton.setText("Play");
            player.pause();
        }
    }

    private void previousButtonActionPerformed(ActionEvent e) {
        try {
            player.stop();
            currentSong--;
            if (currentSong < 0)
                currentSong = songDatabase.getSongList().size() - 1;
            song = songDatabase.getSongList().get(currentSong);
            player = new MusicPlayer(song);
            playPauseButton.setText("Pause");
            player.play();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void nextButtonActionPerformed(ActionEvent e) {
        try {
            player.stop();
            currentSong++;
            currentSong %= songDatabase.getSongList().size();
            song = songDatabase.getSongList().get(currentSong);
            player = new MusicPlayer(song);
            playPauseButton.setText("Pause");
            player.play();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void muteButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void playSongOnDoubleClick() {
        songInfoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (e.getClickCount() == 2) {
                        currentSong = songInfoTable.getSelectedRow();
                        player.stop();
                        song = songDatabase.getSongList().get(currentSong);
                        player = new MusicPlayer(song);
                        playPauseButton.setText("Pause");
                        player.play();
                    }
                } catch (Exception exception) {
                    song = songDatabase.getSongList().get(new Random().nextInt(5));
                    player = new MusicPlayer(song);
                    playPauseButton.setText("Pause");
                    player.play();
                }

            }
        });
    }

    // Initialize music player components
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Lexzander Saplan
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        SearchLabel = new JLabel();
        searchBox = new JTextField();
        playlistTitle = compFactory.createTitle("Playlist");
        LibraryTitle = new JLabel();
        songFilter = new JRadioButton();
        artistFilter = new JRadioButton();
        genreFilter = new JRadioButton();
        songInfoPane = new JScrollPane();
        songInfoTable = new JTable();
        playlistPane = new JScrollPane();
        playlistItems = new JList();
        Song = new JLabel();
        artist = new JLabel();
        shuffleButton = new JButton();
        previousButton = new JButton();
        playPauseButton = new JButton();
        nextButton = new JButton();
        muteButton = new JButton();
        volumeSlider = new JSlider();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new MigLayout(
            "fillx,hidemode 3",
            // columns
            "0[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[48:103,fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[0,fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]" +
            "[fill]" +
            "[fill]0" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[9,fill]" +
            "[fill]0" +
            "[191,fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[14,fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0",
            // rows
            "[]0" +
            "[]0" +
            "[]0" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]0" +
            "[]0" +
            "[]0" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]0" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]0" +
            "[]0" +
            "[]0" +
            "[]0" +
            "[]"));

        //---- SearchLabel ----
        SearchLabel.setText("Search");
        SearchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(SearchLabel, "cell 25 29 11 1,align center center,grow 0 0");
        add(searchBox, "cell 36 29 4 1");

        //---- playlistTitle ----
        playlistTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(playlistTitle, "cell 6 30,alignx center,growx 0");

        //---- LibraryTitle ----
        LibraryTitle.setText("Song Library");
        LibraryTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(LibraryTitle, "cell 18 30,align center center,grow 0 0");

        //---- songFilter ----
        songFilter.setText("Song");
        add(songFilter, "cell 39 30,aligny center,growy 0");

        //---- artistFilter ----
        artistFilter.setText("Artist");
        add(artistFilter, "cell 39 30,aligny center,growy 0");

        //---- genreFilter ----
        genreFilter.setText("Genre");
        add(genreFilter, "cell 39 30,aligny center,growy 0");

        //======== songInfoPane ========
        {

            //---- songInfoTable ----
            songInfoTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            songInfoPane.setViewportView(songInfoTable);
        }
        add(songInfoPane, "cell 17 31 25 26");

        //======== playlistPane ========
        {
            playlistPane.setViewportView(playlistItems);
        }
        add(playlistPane, "cell 6 31 10 26,growy");

        //---- Song ----
        Song.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        Song.setText("Song");
        add(Song, "cell 7 61");

        //---- artist ----
        artist.setText("Artist");
        artist.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(artist, "cell 7 62,alignx left,growx 0");

        //---- shuffleButton ----
        shuffleButton.setText("Shuffle");
        shuffleButton.addActionListener(e -> muteButtonActionPerformed(e));
        add(shuffleButton, "cell 20 62");

        //---- previousButton ----
        previousButton.setText("Previous");
        previousButton.addActionListener(e -> previousButtonActionPerformed(e));
        add(previousButton, "cell 20 62");

        //---- playPauseButton ----
        playPauseButton.setText("Play");
        playPauseButton.addActionListener(e -> playPauseButtonActionPerformed(e));
        add(playPauseButton, "cell 20 62");

        //---- nextButton ----
        nextButton.setText("Next");
        nextButton.addActionListener(e -> nextButtonActionPerformed(e));
        add(nextButton, "cell 20 62");

        //---- muteButton ----
        muteButton.setText("Mute");
        muteButton.addActionListener(e -> muteButtonActionPerformed(e));
        add(muteButton, "cell 20 62");
        add(volumeSlider, "cell 36 62 2 1,aligny center,growy 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        playSongOnDoubleClick();


        Object[] columns = {"Song Title", "Artist", "Album"};
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columns);
        songInfoTable.getTableHeader().setReorderingAllowed(false);
        songInfoTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        addDefaultTableRows();
        songInfoTable.setShowVerticalLines(false);
        songInfoTable.setRowHeight(30);
        songInfoTable.setModel(model);
    }

    // Initializing the JTable
    public void setSongInfoTable(JTable songInfoTable) {
        this.songInfoTable = songInfoTable;
    }

    public void addDefaultTableRows() {
        model.addRow(new Object[]{"Faded", "Alan Walker", "Different World"});
        model.addRow(new Object[]{"Mine", "Bazzi", "Cosmic"});
        model.addRow(new Object[]{"Crab Rave", "Noisestorm", "Monstercat"});
        model.addRow(new Object[]{"High Hopes", "Panic! At The Disco", "Pray for the Wicked"});
        model.addRow(new Object[]{"Stressed Out", "Twenty One Pilots", "Blurryface"});
        SongSerializer songSerializer = new SongSerializer();
        RootObject[] musicJson = songSerializer.getRootObjects();

        for (int i = 0; i < musicJson.length; i++) {
            model.addRow(new Object[]{
                    musicJson[i].getSong().getTitle(),
                    musicJson[i].getArtist().getName(),
                    musicJson[i].getRelease().getName()
            });
        }
    }
}
