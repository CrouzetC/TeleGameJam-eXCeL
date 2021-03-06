import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public final class Main {

	public static final void main(String[] arguments) throws SlickException {
		String title = "eXCeL";
		int width = 1280;
		int height = 720;
		boolean fullscreen = false;
		String request = "Voulez-vous jouer en plein ecran ?";
		String[] options = new String[] {
			"Oui",
			"Non"
		};
		JFrame frame = new JFrame();
		/*
		    It is possible that launching the program from an IDE (such as IntelliJ)
		    will fail and raise the following error :
		      Exception in thread "main" java.lang.UnsatisfiedLinkError: no lwjgl64 in java.library.path

		    In this case, it is still possible to launch the program from command line, with :
		      make build run
		 */
		try {
			frame.setIconImage(AppLoader.loadIcon("/images/tgj_icon.png").getImage());
		} catch (Error e) {
			System.out.println(
					  "\nWarning ! It is normal that launching the program fails from an IDE (such as IntelliJ, Eclipse,...).\n"
					+ "          If you get the following error message :\n"
					+ "              Exception in thread \"main\" java.lang.UnsatisfiedLinkError: no lwjgl64 in java.library.path\n"
					+ "          Then you should try to launch the program from a terminal with :\n"
					+ "              make build run\n");
			e.printStackTrace();
			return;
		}
		int returnValue = JOptionPane.showOptionDialog(
			frame,
			request,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);
		frame.dispose();
		if (returnValue == -1) {
			return;
		}
		if (returnValue == 0) {
			DisplayMode display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
			width = display.getWidth();
			height = display.getHeight();
			fullscreen = true;
		}
		StateBasedGame game = new StateBasedGame(title) {

			@Override
			public void initStatesList(GameContainer container) {
				this.addState(new pages.Welcome(0));
				this.addState(new pages.Choice(1));
				this.addState(new pages.Pause(2));
				this.addState(new games.test.World(3));
				this.addState(new pages.NewGame(4));
				this.addState(new pages.Defeat(5));
				this.addState(new pages.Victory(6));
			}

		};
		AppGameContainer container = new AppGameContainer(game, width, height, fullscreen);
		container.setTargetFrameRate(60);
		container.setVSync(true);
		container.setShowFPS(false);
		container.setIcon(AppLoader.resolve("/images/tgj_icon.png"));
		container.start();
	}

}
