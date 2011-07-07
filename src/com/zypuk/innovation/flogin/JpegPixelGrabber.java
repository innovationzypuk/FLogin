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
// Support class: JPEG image pixel grabber
// -----------------------------------------------------------------------------------

package com.zypuk.innovation.flogin;

import java.awt.Image;
import java.awt.image.PixelGrabber;

public class JpegPixelGrabber {
	
	  private Image m_image=null;   // pointer to original image
	  private int[] m_pixels=null; // will contains either array of 
	  private int m_iWidth, m_iHeight;
	  
	  JpegPixelGrabber(Image img)
	  {
	    m_image=img;

	    //Requesting the images sizes
	    //The method is syncronous, then ImageObserver is not necessary
	    m_iWidth=m_image.getWidth(null);
	  	m_iHeight=m_image.getHeight(null);
	  }

	  public void grabPixels() throws Exception
	  {
	    // CONSTRUCT
	    m_pixels = new int[m_iWidth*m_iHeight];
	    PixelGrabber pixelGrabber=new PixelGrabber(m_image, 0,0, 
	    		m_iWidth, m_iHeight,m_pixels,0,m_iWidth); 

	    // GRAB
	    try
	    {
	      pixelGrabber.grabPixels();
	    }
	    catch (Exception e)
	    {
	      throw e; 
	    }
	  }

	  //Getting grayScale Pixels
	  public byte[] getPixels()
	  {
		 byte[] b = new byte[m_pixels.length];
		 for(int i=0;i<m_pixels.length;i++) {
			 int pixel = m_pixels[i];
			 int red   = (pixel >> 16) & 0xff;
			 int green = (pixel >>  8) & 0xff;
			 int blue  = (pixel      ) & 0xff;
			 b[i] = (byte)((red + green + blue)/3);
		 }
		 return b;
	  }

	  public int getWidth()
	  {
	    return m_iWidth;
	  }

	  public int getHeight()
	  {
	    return m_iHeight;
	  }

	  public void destroy()
	  {
	    m_image=null;
	    m_pixels=null;
	  }
}
