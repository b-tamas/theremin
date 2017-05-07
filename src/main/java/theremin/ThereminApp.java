package theremin;

import java.awt.BorderLayout;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.CategoryTableXYDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import javax.swing.JFrame;
import javax.swing.JPanel;





public class ThereminApp  extends JFrame{
	Tracks tracks;
	
	public ThereminApp() {
		System.out.println("New ThereminApp");
		
		
		tracks = new Tracks(10);
		
		
	}
	
	public void configureClips() {
		
		
		
		///////////////////////////
		//rythm
		
		ClipTrackSet rythmClipTrackSet = new ClipTrackSet();

		Clip rythmClip1=new Clip();
		rythmClip1.soundfile="KickSnr01.wav";
		rythmClipTrackSet.addClip(rythmClip1);
				
		Clip rythmClip2=new Clip();
		rythmClip2.soundfile="KickSnr05.wav";
		rythmClipTrackSet.addClip(rythmClip2);
		
		
		/////////////////////////
		//bass
		
		ClipTrackSet bassClipTrackSet = new ClipTrackSet();

		Clip bassClip1=new Clip();
		bassClip1.soundfile="BowFidl01-E.wav";
		bassClipTrackSet.addClip(bassClip1);
		
		Clip bassClip2=new Clip();
		bassClip2.soundfile="BowFidl09-A.wav";
		bassClipTrackSet.addClip(bassClip2);
		
		
		/////////////
		//foll
		
		ClipTrackSet follClipTrackSet = new ClipTrackSet();

		Clip follClip1=new Clip();
		follClip1.soundfile="ChugginA.wav";
		follClipTrackSet.addClip(follClip1);
		
		Clip follClip2=new Clip();
		follClip2.soundfile="ChugginE.wav";
		follClipTrackSet.addClip(follClip2);
		
		
		
		
		/////////////
		//melody
		
		ClipTrackSet melodyClipTrackSet = new ClipTrackSet();

		Clip melodyClip1=new Clip();
		melodyClip1.soundfile="ChugginA.wav";
		melodyClipTrackSet.addClip(melodyClip1);
		
		Clip melodyClip2=new Clip();
		melodyClip2.soundfile="ChugginE.wav";
		melodyClipTrackSet.addClip(melodyClip2);
		
		
		
		
		tracks.track[1].clipTrackSet=rythmClipTrackSet;
		tracks.track[2].clipTrackSet=bassClipTrackSet;
		tracks.track[3].clipTrackSet=follClipTrackSet;
		tracks.track[4].clipTrackSet=melodyClipTrackSet;
		
		
		
	}
		
	
	
	public ChartPanel diagramTest(Dataset dataset) {
		
		
		
		//ChartFactory();
		
		//org.jfree.data.general.Dataset dataset = new org.jfree.data.general.Dataset();
//		DefaultPieDataset dataset = new DefaultPieDataset( );
		//dataset.setValue( "1st chan" , new Double( 20 ) ); 
		
		
		//org.jfree.data.general.DefaultValueDataset dataset = new org.jfree.data.general.DefaultValueDataset();

		//dataset.setValue(4);
		
		
//		CategoryTableXYDataset dataset = new CategoryTableXYDataset();
//		dataset.add(1, 2, "1st sensor");

		
		
		
		
	    
	    
		
		
		JFreeChart chart = ChartFactory.createLineChart("1st chan", "Value axis label", "value", (CategoryDataset) dataset, PlotOrientation.VERTICAL, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		
		
//        JPanel chartPanel = createChartPanel();
//        add(chartPanel, BorderLayout.CENTER);
		
		
		//ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel, dataset, orientation, legend, tooltips, urls)
		
        return new ChartPanel(chart);
		
	}

	
		

	public Dataset createFakeDataset() {
	
		
		Analyzer analyzerUnitTest = new Analyzer();
		
		ArrayList<Integer> data = new ArrayList<Integer>();
		
		analyzerUnitTest.generateSlowMovementData(data);
		
		
		
		
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    String DiaNameAleft = "A left";
    String DiaNameAright = "A right";
    String DiaNameBleft = "B left";
    String DiaNameBright = "B right";		
	
    
    
    Integer time = 0;   
    int index = 0 ;
	    while (data.size()> index) {
	    	int value = 0;
	    	value=data.get(index);
	        //System.out.println(value);
	        dataset.addValue(value, "1", Integer.toString(time) ); 
	        time++;
	        index++ ;
	    }
    
    return dataset;
	}
	
	
	
	public static void main(String[] args) {
		ThereminApp ta = new ThereminApp();
				
		JPanel chartPanel = ta.diagramTest(ta.createFakeDataset());
    
		ta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ta.setLocationRelativeTo(null);
		ta.setSize(640,  480);
		ta.setVisible(true);


	}
	

}
