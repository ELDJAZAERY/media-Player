package players;


import controle.Contoler;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;




public class MoviePlayer extends Application{
	boolean play = false ;
	boolean pause_slider = false;
	boolean pp = false;
	boolean play_butt=false;
	
	double h_full=-1 ;
	double w_full=-1 ;
	
	double h_Media ;
	double w_Media ;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
    //Group root = new Group();
		StackPane root = new StackPane();
	
    //get screen size
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    h_full=primaryScreenBounds.getHeight();
    w_full = primaryScreenBounds.getWidth();
	
	Media media = new Media(Contoler.media_source);
	MediaPlayer player = new MediaPlayer(media);
	MediaView view = new MediaView(player);
	
	
	
	final Timeline slideIn = new Timeline();
	final Timeline slideOut = new Timeline();
	final Timeline slideIn_full = new Timeline();
	final Timeline slideOut_full = new Timeline();
	
	
	
	Slider slider = new Slider();
	slider.setMin(0.0);
	
	Slider slider_vol = new Slider();
	slider_vol.setMin(0);
	slider_vol.setMax(500);
	slider_vol.setMaxSize(100, 100);
	
	
	Image  img_pause = new Image(getClass().getResourceAsStream("..\\..\\IMGS\\2.2.png"));
	Image  img_play = new Image(getClass().getResourceAsStream("2.1.png"));
	Image  img_suiv = new Image(getClass().getResourceAsStream("3.1.png"));
	Image  img_pres = new Image(getClass().getResourceAsStream("3.2.png"));
	Image  imgg_ajoute = new Image(getClass().getResourceAsStream("ajoute.png"));
	Image  imgg_plein = new Image(getClass().getResourceAsStream("plein.png"));
	/*Image  imgg_bg = new Image(getClass().getResourceAsStream("bg.png"));
	Image  imgg_bg1 = new Image(getClass().getResourceAsStream("bg2.png"));*/
	
	//BackgroundImage bk = new BackgroundImage(imgg_bg, null, null, null, null);
	
	ImageView imgpp = new ImageView(img_play);
	ImageView img_s = new ImageView(img_suiv);
	ImageView img_p = new ImageView(img_pres);
	ImageView img_ajoute = new ImageView(imgg_ajoute);
	ImageView img_plein = new ImageView(imgg_plein);
	/*ImageView img_bg = new ImageView(imgg_bg);
	ImageView img_bg1 = new ImageView(imgg_bg1);*/
	
	
	//positionnement
	img_p.setTranslateX(23);
	img_p.setTranslateY(-52);	
	
	imgpp.setTranslateX(50);
	imgpp.setTranslateY(-3);
	
	img_s.setTranslateX(78);
	img_s.setTranslateY(-27);
	
	
	slider_vol.setTranslateX(200);
	slider_vol.setTranslateY(15);
	
	
	
	
	
	
	final VBox vbox = new VBox();
	
	if(Contoler.list.size()<=1) vbox.getChildren().addAll(slider,slider_vol,imgpp,img_ajoute,img_plein);
	else 						  vbox.getChildren().addAll(slider,slider_vol,imgpp,img_s,img_p,img_ajoute,img_plein);
	
	
	//vbox.setBackground(bk);
	
	
	
	
	root.getChildren().addAll(view,vbox);
	
	
	Scene scen = new Scene(root,stage.getHeight(),stage.getWidth(),javafx.scene.paint.Color.BLACK);
	stage.setScene(scen);
	stage.show();
	stage.setTitle("Media Player");
	
	
	//if(Contoler.view_full){ plein_ecran(stage, slider_vol, vbox, view);}
	
	player.setVolume(Contoler.media_volume);
	
	player.play(); play=true;
	if(Contoler.view_full){plein_ecran(stage, slider , vbox, view);}
	
	player.setOnReady( new Runnable() {
		
		public void run() {
			 h_Media = player.getMedia().getHeight()+39;
			 w_Media = player.getMedia().getWidth()+16;
			
			//System.out.println(h+" ---- "+w);
			
			stage.setMinHeight(h_Media);
			stage.setMinWidth(w_Media);
			if(!Contoler.view_full){
				stage.setMaxHeight(h_Media);
				stage.setMaxWidth(w_Media);
			}
			
			
			if(!Contoler.view_full){
			stage.setHeight(h_Media);
			stage.setWidth(w_Media);
			stage.centerOnScreen();
			}
		
			
			//System.out.println(stage.getHeight()+"-----"+stage.getWidth());
			
			if(!Contoler.view_full){
			vbox.setMinSize(w_Media-26,h_Media);
			vbox.setTranslateY(h_Media-100);
			}else{
			 vbox.setMinSize(w_full-26,h_full);
			 vbox.setTranslateY(h_full-100);			
			}
			vbox.setTranslateX(5);
			
			if(!Contoler.view_full){
				if(Contoler.list.size()<=1){
				img_ajoute.setTranslateX(w_Media-120);
				img_ajoute.setTranslateY(imgpp.getX()-30);
				
				img_plein.setTranslateX(w_Media-80);
				img_plein.setTranslateY(imgpp.getX()-55);
				}else{
				img_ajoute.setTranslateX(w_Media-115);
				img_ajoute.setTranslateY(imgpp.getX()-78);
				
				img_plein.setTranslateX(w_Media-75);
				img_plein.setTranslateY(imgpp.getX()-103);
				}
			}else{
			    if(Contoler.list.size()<=1){
				img_ajoute.setTranslateX(w_full-120);
				img_ajoute.setTranslateY(imgpp.getX()-30);
					
				img_plein.setTranslateX(w_full-80);
				img_plein.setTranslateY(imgpp.getX()-55);
				}else{
				img_ajoute.setTranslateX(w_full-115);
				img_ajoute.setTranslateY(imgpp.getX()-78);
					
				img_plein.setTranslateX(w_full-75);
				img_plein.setTranslateY(imgpp.getX()-103);
				}	
			}
			
			slider.setMax(player.getTotalDuration().toSeconds());
			
			slider_vol.setValue(player.getVolume() * 500);
			slider_vol.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable) {
				player.setVolume(slider_vol.getValue() / 500);
				Contoler.media_volume=player.getVolume();
				}
			});
			
			
			// stage move
			slideIn.getKeyFrames().addAll(
					new KeyFrame(new Duration(0),
						new KeyValue(vbox.translateYProperty(),h_Media),
						new KeyValue(vbox.opacityProperty(),0.0)
					    ),
					new KeyFrame(new Duration(200),
							new KeyValue(vbox.translateYProperty(),h_Media-100),
							new KeyValue(vbox.opacityProperty(),0.9)
						)
			);
			slideOut.getKeyFrames().addAll(
					new KeyFrame(new Duration(0),
							new KeyValue(vbox.translateYProperty(),h_Media-100),
							new KeyValue(vbox.opacityProperty(),0.9)
					    ),
					new KeyFrame(new Duration(300),
							new KeyValue(vbox.translateYProperty(),h_Media),
							new KeyValue(vbox.opacityProperty(),0.0)
						)
			);
			
			// stage move in full screen
			slideIn_full.getKeyFrames().addAll(
					new KeyFrame(new Duration(0),
						new KeyValue(vbox.translateYProperty(),h_full),
						new KeyValue(vbox.opacityProperty(),0.0)
					    ),
					new KeyFrame(new Duration(200),
							new KeyValue(vbox.translateYProperty(),h_full-100),
							new KeyValue(vbox.opacityProperty(),0.9)
						)
			);
			slideOut_full.getKeyFrames().addAll(
					new KeyFrame(new Duration(0),
							new KeyValue(vbox.translateYProperty(),h_full-100),
							new KeyValue(vbox.opacityProperty(),0.9)
					    ),
					new KeyFrame(new Duration(300),
							new KeyValue(vbox.translateYProperty(),h_full),
							new KeyValue(vbox.opacityProperty(),0.0)
						)
			);
		
			
			
		}
		
	});
	player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
		@Override
		public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration current) {
			slider.setValue(current.toSeconds());
			//System.out.println(current.toSeconds()+"   "+player.getTotalDuration().toSeconds());
			if(current.toSeconds()>player.getTotalDuration().toSeconds()-0.1){Contoler.pos_acctuelle++; Contoler.exite=false; stage.close(); }
			
			//labelle time !!!
		}
	});
	
	
	slider.setOnMousePressed(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			player.seek(Duration.seconds(slider.getValue()));
			if(play){ player.pause(); pause_slider=true;}
			pp=true;
			
			//System.out.println("ok 1");
		}
	});
	slider.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			player.seek(Duration.seconds(slider.getValue()));	
			if(pause_slider){ player.play(); pause_slider=false; }
			pp=false;
		}
	});
		
	
	
	root.setOnMousePressed(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			if(play) { player.pause(); play=false; imgpp.setImage(img_pause); }
			else { player.play(); play=true; imgpp.setImage(img_play); }
			
		}
	});
	root.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			if(pause_slider){ player.seek(Duration.seconds(slider.getValue())); player.play(); pause_slider=false;}
			if(pp){player.seek(Duration.seconds(slider.getValue())); pp=false;}
		}
	});
	
	/*root.setOnKeyPressed(new EventHandler<Event>() {
		@Override
		public void handle(Event event) {
			event.getCode() == KeyCode.UP
		}
	
	});*/
	
	root.setOnKeyPressed( (event)-> {
		if(event.getCode() == KeyCode.ESCAPE ){
			if(!stage.isFullScreen() && Contoler.view_full){
		        plein_ecran(stage, slider, vbox, view);
			}
		}
	} );
	
	//player.setVolume(arg0); // voulume
	//player.setRate(1/2/.5);; // speed
	
	
	imgpp.setOnMousePressed(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			if(play_butt) { player.pause(); play_butt=false; imgpp.setImage(img_pause); }
			else { player.play();  play_butt=true; imgpp.setImage(img_play); }
		}
	});
	
	img_s.setOnMousePressed(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>(){
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			Contoler.pos_acctuelle++; Contoler.exite=false; player.dispose(); try{Contoler.M_suiv(stage);}catch(Exception e){}
		}
	});
	img_p.setOnMousePressed(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			 Contoler.pos_acctuelle--; Contoler.exite=false; player.dispose(); try{Contoler.M_suiv(stage);}catch(Exception e){}
			 
		}
	});
	
	
	img_ajoute.setOnMousePressed(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			try{ Contoler.openFile();
			Contoler.pos_acctuelle=Contoler.list.size()-1; player.dispose(); Contoler.exite=false; Contoler.M_suiv(stage);
			}catch(Exception e) { }
			
		}
	});
	
	img_plein.setOnMousePressed(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
		@Override
		public void handle(javafx.scene.input.MouseEvent event) {
			plein_ecran(stage, slider, vbox, view);
		}
		
	});
	
	/*stage.setOnHidden(new EventHandler<WindowEvent>() {
		@Override
		public void handle(WindowEvent arg0) {
			
		}});*/
	
	/*stage.setOnHidden( (evente) ->{
		if(evente.getSource()== WindowEvent.ANY){
			if(evente.getSource() != WindowEvent.WINDOW_HIDDEN && evente.getSource() != WindowEvent.WINDOW_HIDING){
				plein_ecran(stage, slider, vbox, view);
			}
		}
	});*/
	
	root.setOnMouseEntered(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if(!stage.isFullScreen()){slideIn.play();}else{slideIn_full.play();}
		}
	});
	root.setOnMouseExited(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if(!stage.isFullScreen()){slideOut.play();}else{slideOut_full.play();}
		}
	});
	
	
}
	
	public void plein_ecran(Stage stage, Slider slider,VBox vbox,MediaView view){
		
		if(!stage.isFullScreen() && Contoler.view_full){
			stage.setMaxHeight(h_Media);
			stage.setMaxWidth(w_Media);
			
			  view.setFitHeight(h_Media);
	          view.setFitWidth(w_Media);
	          Contoler.view_full=false;    
	    
	          vbox.setMinSize(w_Media-26,h_Media);
			  vbox.setTranslateY(h_Media-100);
		    
	          stage.centerOnScreen();
	        return;
		}
		
		if(!stage.isFullScreen()){
			stage.setMaxHeight(h_full+50);
			stage.setMaxWidth(w_full);
			
	          
		stage.setFullScreen(true);
        view.setFitHeight(stage.getHeight());
        view.setFitWidth(stage.getWidth());
        view.setPreserveRatio(true);
        vbox.setMinSize(w_full-26,h_full);
		vbox.setTranslateY(h_full-100);
		
        Contoler.view_full=true;
     
        
        }else{
        	/*stage.setMaxHeight(h_Media);
			stage.setMaxWidth(w_Media);*/
          stage.setFullScreen(false);
          
          
          view.setFitHeight(h_Media);
          view.setFitWidth(w_Media);
          Contoler.view_full=false;    
    
          vbox.setMinSize(w_Media-26,h_Media);
		  vbox.setTranslateY(h_Media-100);
	    
          stage.centerOnScreen();
        }
		

		
	}
	
	
}
