/*
 -------------------------------------------------------------------------------
 GrFinger Sample
 (c) 2006 Griaule Tecnologia Ltda.
 http://www.griaule.com
 -------------------------------------------------------------------------------

 This sample is provided with "GrFinger Fingerprint Recognition Library" and
 can't run without it. It's provided just as an example of using GrFinger
 Fingerprint Recognition Library and should not be used as basis for any
 commercial product.

 Griaule Tecnologia makes no representations concerning either the merchantability
 of this software or the suitability of this sample for any particular purpose.

 THIS SAMPLE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL GRIAULE BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 You can download the free version of GrFinger directly from Griaule website.
                                                                   
 These notices must be retained in any copies of any part of this
 documentation and/or sample.

 -------------------------------------------------------------------------------
*/

// -----------------------------------------------------------------------------------
// GUI routines: "Color chooser" form
// -----------------------------------------------------------------------------------

package com.zypuk.innovation.flogin;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class ColorPane extends JPanel implements MouseListener {
	private Canvas canvas;
	private Canvas canvas1;
	private JCheckBox jCheckBox;
	private JCheckBox jCheckBox1;
	private Color stateColor1;
	private Color stateColor2;
	private boolean stateCheckBox;
	private boolean stateCheckBox1;
	
	public ColorPane(String label,Color color1, Color color2){
		
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 5, 10, 5),
				BorderFactory.createTitledBorder(null,
						label, TitledBorder.LEFT,
						TitledBorder.DEFAULT_POSITION,
						null, null)));
		GridLayout gridLayout = new GridLayout(1,2,5,5);
		setLayout(gridLayout);
		
		add(getJPanel(color1,color2),null);
		add(new JLabel("Double click the color to change it."),null);
		
		
	}
	
	public void saveState(){
		stateColor1 = getColor1();
		stateColor2 = getColor2();
		stateCheckBox = jCheckBox.isSelected();
		stateCheckBox1 = jCheckBox1.isSelected();
	}
	
	public void restoreState(){
		canvas.setBackground(stateColor1);
		canvas1.setBackground(stateColor2);
		jCheckBox.setSelected(stateCheckBox);
		jCheckBox1.setSelected(stateCheckBox1);
	}
	
	private JPanel getJPanel(Color color1, Color color2){
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(2,3,5,5);
		panel.setLayout(gridLayout);
		
		canvas = new Canvas();
		canvas1 = new Canvas();
		
		canvas.addMouseListener(this);
		canvas1.addMouseListener(this);
		
		canvas.setBackground(color1);
		canvas1.setBackground(color2);
		
		jCheckBox = new JCheckBox("Show",true);
		jCheckBox1 = new JCheckBox("Show",true);
		
		panel.add(new JLabel("Regular:"),null);
		panel.add(canvas,null);
		panel.add(jCheckBox,null);
		panel.add(new JLabel("Match:"),null);
		panel.add(canvas1,null);
		panel.add(jCheckBox1,null);
		
		return panel;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			Canvas c = (Canvas) e.getSource();
			Color newColor = JColorChooser.showDialog(getParent(),"Color",c.getBackground()); 
			if (newColor!=null)
				c.setBackground(newColor);
			
		}
		
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
				
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
	
	}

	public Color getColor1(){
		return canvas.getBackground();
	}
	
	public Color getColor2(){ 
		return canvas1.getBackground();
	}
	
	public boolean mustShowColor1(){
		return jCheckBox.isSelected();
	}
	
	public boolean mustShowColor2(){
		return jCheckBox1.isSelected();
	}
}
