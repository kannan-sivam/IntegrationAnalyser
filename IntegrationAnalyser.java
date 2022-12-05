package com.app.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class IntegrationAnalyser {


    static JFrame frame;
    static JPanel panel;
    static IntegrationAnalyser app=new IntegrationAnalyser();

    public static void main(String[] args) {
        //new TestURLImage("");
        //simQhw+ZPt3+O9CbX/t28OfAEkr1
    	
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIMake();

        } catch (Exception e) {
			e.printStackTrace();
		}
    }
    static ButtonListener buttonListener = new ButtonListener();

    public static JTextField search;
    public static JButton btnClr;
    public static void UIMake(){

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame = new JFrame("Integration Analyser");
        frame.setBackground(Color.red);
        frame.setIconImage( new
                ImageIcon("/Users/kparamasivam/Documents/conviva_logo.png")
                .getImage());
        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        search = new JTextField(10);
        search.setMaximumSize(new Dimension(990,40));
        //search.setUI(new HintTextFieldUI("Enter Touchstone URL", true));

        JPanel JENTRY=new JPanel();
        JENTRY.setLayout(new BoxLayout(JENTRY, BoxLayout.Y_AXIS));
        JENTRY.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel mp = new JPanel();
            mp.setLayout(new BoxLayout(mp, BoxLayout.X_AXIS));
            mp.setAlignmentX(Component.LEFT_ALIGNMENT);
            mp.add(search);
            btnClr = new JButton("SUBMIT");
            btnClr.setActionCommand("C-SUBMIT");
            btnClr.addActionListener(buttonListener);
            mp.add(btnClr);

            JPanel pImgs=new JPanel();
            pImgs.setLayout(new BoxLayout(pImgs, BoxLayout.X_AXIS));
            pImgs.setAlignmentX(Component.LEFT_ALIGNMENT);
            pImgs.setBorder(new EmptyBorder(40, 5, 5, 50));
            pImgs.add(new JLabel("Enter Touchstone URL or Client ID...")); //LABEL

        String url = "/Users/kparamasivam/Documents/conviva_logo.png";
        ImageIcon imageIcon = new ImageIcon(url);
        JLabel ilabel = new JLabel(".", imageIcon, JLabel.CENTER);

        JENTRY.setBorder(new EmptyBorder(40, 50, 50, 50));
        JENTRY.add(ilabel);
        JENTRY.setBorder(new EmptyBorder(40, 50, 50, 50));

        JLabel jTitle = new JLabel("Integration Analyser".toUpperCase());
        jTitle.setFont(new Font("Calibri", Font.BOLD, 25));
        jTitle.setBorder(new EmptyBorder(40, 5, 5, 20));
        JENTRY.add(jTitle);

        JENTRY.add(pImgs);
        JENTRY.add(mp);

        panel.add(JENTRY); //top layout

        jMainData=new JPanel();
        jMainData.setLayout(new BoxLayout(jMainData, BoxLayout.X_AXIS));
        JPanel JLIVE = app.getSectionData("LIVE ( 5 Sessions )");
        jMainData.add(JLIVE);
        JPanel JVOD = app.getSectionData("VOD ( 8 Sessions )");
        jMainData.add(JVOD);
        jMainData.setVisible(false);

        panel.add(jMainData);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }


    static JPanel jMainData;
    static String sTestC[] = {"Buffering State","Play State","Pause State","Bitrate", "Metadata", "Viewer ID", "Is Session CleanUp?" };
    static String sTCResult[] = {"REPORTED","REPORTED","REPORTED","REPORTED", "REPORTED", "REPORTED", "YES" };
    static String sTCResult2[] = {"NOT REPORTED","NOT REPORTED","NOT REPORTED","NOT REPORTED", "NOT REPORTED", "NOT REPORTED", "NO" };

    static JButton jbLink[] = new JButton[sTestC.length];
    static JButton jbResult[] = new JButton[sTestC.length];

    JPanel getSectionData(String txt){

        JPanel prows=new JPanel();
        prows.setLayout(new BoxLayout(prows, BoxLayout.Y_AXIS));
        prows.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel jLiveC = new JLabel(txt);
        jLiveC.setMaximumSize(new Dimension(160,40));
        Font font = new Font("Courier", Font. BOLD,12); jLiveC. setFont(font);
        prows.add(jLiveC);


        for(int l=0;l<sTestC.length;l++){

            JPanel plabels=new JPanel();
            plabels.setLayout(new BoxLayout(plabels, BoxLayout.X_AXIS));
            plabels.add(app.getTC(sTestC[l]));

            jbResult[l] = app.getResult(sTCResult[l]);

            if(!isPassed && app.getQuickInt()==2) {
                jbResult[l].setText(sTCResult2[l]);
                jbResult[l].setForeground(Color.red);
            }


            plabels.add(jbResult[l]);
            jbLink[l] =app.getLink(""+getRandonNumber()); plabels.add(jbLink[l]);
            jbLink[l].setActionCommand("CL"+l+txt); jbLink[l].addActionListener(buttonListener);

            prows.add(plabels);
        }

        return prows;
    }

    int getRandonNumber(){
        int min = 121200;
        int max = 998100;
        int b = (int)(Math.random()*(max-min+1)+min);
        return b;
    }
    int getQuickInt(){
        return (int)(Math.random()*(3-1+1)+1);
    }

    JLabel getTC(String name){
        JLabel jl = new JLabel("  "+name);
        jl.setMaximumSize(new Dimension(270,40));
        Border border = BorderFactory.createLineBorder(Color.lightGray);
        jl.setBorder(border);
        jl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return  jl;
    }

    JButton getResult(String name){
        JButton jl = new JButton(name);
        jl.setMaximumSize(new Dimension(130,40));
        jl.setPreferredSize(new Dimension(130,40));
        Border border = BorderFactory.createLineBorder(new Color(62,160,85));
        jl.setBorder(border);
        jl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return  jl;
    }

    JButton getLink(String name){
        JButton jl = new JButton(name);
        jl.setForeground(new Color(1,120,255));
        jl.setMaximumSize(new Dimension(100,40));
        Border border = BorderFactory.createLineBorder(Color.lightGray);
        jl.setBorder(border);
        jl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return  jl;
    }

    void runOnBackground(){

        new Thread(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                jMainData.removeAll();
                //jMainData.setLayout(new BoxLayout(jMainData, BoxLayout.X_AXIS));
                JPanel JLIVE = app.getSectionData("LIVE ( 5 Sessions )");
                jMainData.add(JLIVE);
                JPanel JVOD = app.getSectionData("VOD ( 8 Sessions )");
                jMainData.add(JVOD);

                JPanel prows=new JPanel();
                prows.setLayout(new BoxLayout(prows, BoxLayout.Y_AXIS));
                prows.setAlignmentX(Component.LEFT_ALIGNMENT);
                prows.add(new JLabel("VST Min: 0.9"));
                prows.add(new JLabel("VST Max: 10.9"));
                jMainData.add(prows);

                jMainData.setVisible(true);
                panel.revalidate();
            }
        }).start();

    }

    void openWeb(String web){
        try {
            java.awt.Desktop.getDesktop().browse(new URI(web));
        } catch(Exception e1){}

    }


    static boolean isPassed;
    static long actionDelay;
    public static class ButtonListener implements ActionListener
    {

        public void actionPerformed(final ActionEvent ev)
        {

            if(actionDelay==0||(System.currentTimeMillis()-actionDelay)>900){
                actionDelay  = System.currentTimeMillis();
            }
            else return;

            String cmd = ev.getActionCommand();
            //System.out.println(cmd);
            if(cmd.startsWith("CL")){ //IMAGE SEARCH
                app.openWeb("https://touchstone-conviva.conviva.com/ui?client=946721560.3315917585.650101618.12345681&session=2030932120&pub=0&before=1670074122.6303995");

            }
            else if(cmd.equals("C-SUBMIT")){
                String sInput = search.getText();
                if(sInput.length()>=0){

                    isPassed=  (sInput.length() ==0 || sInput.endsWith("0"))?true:false;
                    jMainData.setVisible(false);  jMainData.revalidate(); jMainData.repaint();
                    panel.revalidate(); panel.repaint();
                    frame.revalidate(); frame.repaint();
                    app.runOnBackground();
                }

            }
            else if(cmd.equals("G2")){ // MAP UPDATE
                //app.
            }
            else if(cmd.equals("G1")){ // hide

            }
            else if(cmd.equals("G0")){ // refresh

            }
        }
    }


}