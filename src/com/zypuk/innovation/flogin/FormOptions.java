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
// GUI routines: "Options" form
// -----------------------------------------------------------------------------------

package com.zypuk.innovation.flogin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class FormOptions extends JOptionPane {

	private javax.swing.JPanel jContentPane = null; //  @jve:decl-index=0:visual-constraint="10,110"

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;
	
	private JPanel jPanel2 = null;
	
	private ColorPane colorPane = null;
	
	private ColorPane colorPane1 = null;
	
	private ColorPane colorPane2 = null;

	private JLabel jLabel = null;

	private JTextField jTextField = null;

	private JLabel jLabel1 = null;

	private JTextField jTextField1 = null;

	private JLabel jLabel2 = null;

	private JTextField jTextField2 = null;

	private JLabel jLabel3 = null;

	private JTextField jTextField3 = null;
	
	
	

	/**
	 * This is the default constructor
	 */
	public FormOptions() {
		super(null, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		setMessage(getJContentPane());
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.EAST);
			jContentPane.add(getJPanel1(), BorderLayout.WEST);
			jContentPane.add(getJPanel2(),BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			GridLayout gridLayout3 = new GridLayout();
			jPanel = new JPanel();
			jPanel.setLayout(gridLayout3);
			jLabel.setText("Threshold");
			jLabel.setName("jLabel");
			jLabel1.setText("Rotation tolerance");
			jLabel1.setName("jLabel1");
			gridLayout3.setRows(2);
			gridLayout3.setColumns(2);
			gridLayout3.setHgap(5);
			gridLayout3.setVgap(5);
			jPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(10, 5, 10, 5),
					BorderFactory.createTitledBorder(null,
							"Identify", TitledBorder.LEFT,
							TitledBorder.DEFAULT_POSITION,
							null, null)));
					
			jPanel.add(jLabel, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel3 = new JLabel();
			jLabel2 = new JLabel();
			GridLayout gridLayout4 = new GridLayout();
			jPanel1 = new JPanel();
			jPanel1.setLayout(gridLayout4);
			jLabel2.setText("Threshold");
			jLabel3.setText("Rotation tolerance");
			gridLayout4.setRows(2);
			gridLayout4.setColumns(2);
			gridLayout4.setVgap(5);
			gridLayout4.setHgap(5);
			jPanel1.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(10, 5, 10, 5),
					BorderFactory.createTitledBorder(null,
							"Verify", TitledBorder.LEFT,
							TitledBorder.DEFAULT_POSITION,
							null, null)));
						jPanel1.add(jLabel2, null);
			jPanel1.add(getJTextField2(), null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(getJTextField3(), null);
		}
		return jPanel1;
	}
	
	private JPanel getJPanel2(){
		if (jPanel2 == null){
			jPanel2 = new javax.swing.JPanel();
			jPanel2.setLayout(new BorderLayout());
			colorPane = new ColorPane("Minutiae Colors",Color.BLUE,Color.MAGENTA);
			colorPane1 = new ColorPane("Segments Colors",Color.GREEN,Color.MAGENTA);
			colorPane2 = new ColorPane("Minutiae Direction Colors",Color.RED,Color.MAGENTA);
			
			jPanel2.add(colorPane, BorderLayout.NORTH);
			jPanel2.add(colorPane1, BorderLayout.CENTER);
			jPanel2.add(colorPane2, BorderLayout.SOUTH);
			
		}
		
		return jPanel2;
		
	}
	
	
	
	

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setName("jTextField");
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setName("jTextField1");
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
		}
		return jTextField3;
	}

	/**
	 * @return
	 */
	public int getIdentifyThreshold() throws NumberFormatException{
		
		return Integer.parseInt(jTextField.getText());
	}

	/**
	 * @return
	 */
	public int getIdentifyRotationTolerance() throws NumberFormatException{
		
		return Integer.parseInt(jTextField1.getText());
	}

	/**
	 * @return
	 */
	public int getVerifyThreshold() throws NumberFormatException{
	
		return Integer.parseInt(jTextField2.getText());
	}
	
	/**
	 * @return
	 */
	public int getVerifyRotationTolorance() throws NumberFormatException{
		
		return Integer.parseInt(jTextField3.getText());
	}

	
	/**
	 * @param threshold
	 */
	public void setIdentifyThreshold(int threshold) {
		jTextField.setText(Integer.toString(threshold));
		
	}

	/**
	 * @param rotationTolerance
	 */
	public void setIdentifyRotationTolerance(int rotationTolerance) {
		jTextField1.setText(Integer.toString(rotationTolerance));
		
	}

	/**
	 * @param threshold
	 */
	public void setVerifyThreshold(int threshold) {
		jTextField2.setText(Integer.toString(threshold));
		
	}

	/**
	 * @param rotationTolerance
	 */
	public void setVerifyRotationTolorance(int rotationTolerance) {
		jTextField3.setText(Integer.toString(rotationTolerance));
		
	}
	
	public Color getMinutiaeColor(){
		return colorPane.getColor1();
	}
	
	public Color getMinutiaeMatchColor(){
		return colorPane.getColor2();
	}
	
	public boolean mustShowMinutiae(){
		return colorPane.mustShowColor1();
	}
	
	public boolean mustShowMinutiaeMatch(){
		return colorPane.mustShowColor2();
	}
	
	public Color getSegmentColor(){
		return colorPane1.getColor1();
	}
	
	public Color getSegmentMatchColor(){
		return colorPane1.getColor2();
	}
	
	public boolean mustShowSegment(){
		return colorPane1.mustShowColor1();
	}
	
	public boolean mustShowSegmentMatch(){
		return colorPane1.mustShowColor2();
	}
	
	public Color getDirectionColor(){
		return colorPane2.getColor1();
	}
	
	public Color getDirectionMatchColor(){
		return colorPane2.getColor2();
	}
	
	public boolean mustShowDirection(){
		return colorPane2.mustShowColor1();
	}
	
	public boolean mustShowDirectionMatch(){
		return colorPane2.mustShowColor2();
	}

	public void restoreState() {
		colorPane.restoreState();
		colorPane1.restoreState();
		colorPane2.restoreState();		
	}

	public void saveState() {
		colorPane.saveState();
		colorPane1.saveState();
		colorPane2.saveState();		
	}
}
