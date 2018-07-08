package controle;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import players.MoviePlayer;


public class Contoler extends Application {
	
	static public boolean exite=true;
	static public boolean view_full = false;
	static Stage stage;
	
	static public int pos_acctuelle = 0 ;
	static public ArrayList<URL> list = new ArrayList<>();
	
	static public String media_source="File:/C:/Users/eldja/workspace/MediaPlyaer/sources/funny.mp4";
	
	static public double media_volume=1;
	

	
	
	public static void main(String[] args) throws Exception{
		launch(args);
	}

	
	
	
	public static void M_suiv(Stage stage)throws Exception{

		if(pos_acctuelle>=list.size() ){pos_acctuelle=0;}
		if(pos_acctuelle<0){pos_acctuelle=list.size()-1;}
		if(exite)System.exit(0);
		
		media_source=list.get(pos_acctuelle).toString();
		
		exite=true;
		MoviePlayer mv_player=new MoviePlayer();
		mv_player.start(stage);

	}

	//JFileChoosers !!


	@Override
	public void start(Stage stage) throws Exception {
		// System.out.println(getClass().getResource("").toString()); source Actuel 
		Contoler.stage=stage;
		
		/*String media3 = "file:/C:/Users/eldja/workspace/fx/bin/application/a.mp3";
		String media2 = "File:/C:/Users/eldja/workspace/MediaPlyaer/sources/snow.mp4";
		String media1 = "File:/C:/Users/eldja/workspace/MediaPlyaer/sources/funny.mp4";
		String media = "File:/C:/Users/eldja/workspace/MediaPlyaer/sources/trailer.mp4";
		list.add(media); list.add(media1); list.add(media2); list.add(media3);*/
		
		
		
		if(list.size()!=0) media_source=list.get(pos_acctuelle).toString();
		MoviePlayer mv_player=new MoviePlayer();
		mv_player.start(stage);
		
	}
	
	
	public static void openFile() throws MalformedURLException{
		
		/*FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
		 JFileChooser fileChooser = ...;
		 fileChooser.addChoosableFileFilter(filter);
		*/
		
		/*FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		chooser.getExtensionFilters().add(extFilter);*/
		
		FileChooser choser = new FileChooser();
		
		
		FileChooser.ExtensionFilter extFilterALL = new FileChooser.ExtensionFilter("ALL (*.MP4)(*.MP3)", "*.MP*");
		FileChooser.ExtensionFilter extFilterMp4 = new FileChooser.ExtensionFilter("MP4 Files (*.MP4)", "*.MP4");
		FileChooser.ExtensionFilter extFilterMp3 = new FileChooser.ExtensionFilter("MP3 Files (*.MP3)", "*.MP3");
		choser.getExtensionFilters().addAll(extFilterALL,extFilterMp4,extFilterMp3);
		
		File f = choser.showOpenDialog(null);
		//f.getName().toLowerCase().endsWith(".MKV"); boolean 
		list.add(f.toURI().toURL());
		
	}

	
	
}

