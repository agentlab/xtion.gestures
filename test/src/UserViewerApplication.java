
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;

import org.openni.Device;
import org.openni.OpenNI;

import org.openni.*;
import com.primesense.nite.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class UserViewerApplication {

    private JFrame mFrame;
    private UserViewer mViewer;
    private boolean mShouldRun = true;

    public UserViewerApplication(UserTracker tracker) {
        mFrame = new JFrame("NiTE User Tracker Viewer");
        mViewer = new UserViewer(tracker);
        
        // register to key events
        mFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}
            
            @Override
            public void keyReleased(KeyEvent arg0) {}
            
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    mShouldRun = false;
                }
            }
        });
        
        // register to closing event
        mFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mShouldRun = false;
            }
        });
        
     //register to closing event
        mFrame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(SwingUtilities.isLeftMouseButton(arg0)) {
					System.out.println("left button(stay)");
					mViewer.scanVectors("hello");
				}
				else if(SwingUtilities.isRightMouseButton(arg0)) {
					System.out.println("right button(seat)");
					mViewer.scanVectors("phone");
				}
				else if(SwingUtilities.isMiddleMouseButton(arg0)) {
					System.out.println("middle button(hello)");
					mViewer.scanVectors("hello");
				}
				else 
				{
					System.out.println("phone");
					mViewer.scanVectors("phone");
				}
				
				
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        }); 

        mViewer.setSize(800, 600);
        mFrame.add("Center", mViewer);
        mFrame.setSize(mViewer.getWidth(), mViewer.getHeight());
        mFrame.setVisible(true);
    }

    void run() {
        while (mShouldRun) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mFrame.dispose();
    }

    public static void main(String s[]) {
        // initialize OpenNI and NiTE
    	OpenNI.initialize();
        NiTE.initialize();
        
        List<DeviceInfo> devicesInfo = OpenNI.enumerateDevices();
        if (devicesInfo.size() == 0) {
            JOptionPane.showMessageDialog(null, "No device is connected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Device device = Device.open(devicesInfo.get(0).getUri());
        UserTracker tracker = UserTracker.create();

        final UserViewerApplication app = new UserViewerApplication(tracker);
        app.run();
    }
}
