package org.usfirst.frc6133.Steamworks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionPipeline;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

/**
* Grip class.
*
* <p>An OpenCV pipeline generated by GRIP.
*
* @author GRIP
*/
public class Grip implements VisionPipeline {

	//Outputs
	private Mat hslThresholdOutput = new Mat();
	private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();
	private ArrayList<MatOfPoint> filterContoursOutput = new ArrayList<MatOfPoint>();
	private ArrayList<MatOfPoint> convexHullsOutput = new ArrayList<MatOfPoint>();
	private final int IMG_WIDTH = 320;
	private final int IMG_HEIGHT = 240;
	private double center;
	private double deltaX;
	private Rect r1;
	private Rect r2;
	
	public int w1;
	public int w2;
	public int h1;
	public int h2;
	
	private Mat source;
	private CvSink cvSink;
	private CvSource outputStream;
	

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public void initCamera() {
		source = new Mat();
		new Thread(() -> {
        	RobotMap.USBcam = new UsbCamera("cam0",0);
        	RobotMap.cam = CameraServer.getInstance();
        	RobotMap.USBcam.setBrightness(10);
        	RobotMap.USBcam.setFPS(10);
        	RobotMap.USBcam.setResolution(320, 240);
        	RobotMap.cam.startAutomaticCapture(RobotMap.USBcam);
        	//cvSink = CameraServer.getInstance().getVideo();
        	//outputStream = CameraServer.getInstance().putVideo("Raw Input", 160, 120);
        	while(!Thread.interrupted()) {
        		//cvSink.grabFrame(source);
        		//if (grabSource) { System.out.println("Grabbing Source"); process(source); grabSource = false; }
        		//outputStream.putFrame(source);
        	}
        }).start();
	}

	/**
	 * This is the primary method that runs the entire pipeline and updates the outputs.
	 */
	@Override	public void process(Mat source0) {
		// Step HSL_Threshold0:
		Mat hslThresholdInput = source0;
		double[] hslThresholdHue = {77.7818537624009, 92.18532074990301};
		double[] hslThresholdSaturation = {171.98741007194246, 255.0};
		double[] hslThresholdLuminance = {21.276978417266186, 255.0};
		hslThreshold(hslThresholdInput, hslThresholdHue, hslThresholdSaturation, hslThresholdLuminance, hslThresholdOutput);

		// Step Find_Contours0:
		Mat findContoursInput = hslThresholdOutput;
		boolean findContoursExternalOnly = false;
		findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);

		// Step Filter_Contours0:
		ArrayList<MatOfPoint> filterContoursContours = findContoursOutput;
		double filterContoursMinArea = 125.0;
		double filterContoursMinPerimeter = 0;
		double filterContoursMinWidth = 0;
		double filterContoursMaxWidth = 1000;
		double filterContoursMinHeight = 0;
		double filterContoursMaxHeight = 1000;
		double[] filterContoursSolidity = {0, 100};
		double filterContoursMaxVertices = 1000000;
		double filterContoursMinVertices = 0;
		double filterContoursMinRatio = 0;
		double filterContoursMaxRatio = 1000;
		filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter, filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight, filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices, filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput);

		// Step Convex_Hulls0:
		ArrayList<MatOfPoint> convexHullsContours = filterContoursOutput;
		convexHulls(convexHullsContours, convexHullsOutput);

	}

	/**
	 * This method is a generated getter for the output of a HSL_Threshold.
	 * @return Mat output from HSL_Threshold.
	 */
	public Mat hslThresholdOutput() {
		return hslThresholdOutput;
	}

	/**
	 * This method is a generated getter for the output of a Find_Contours.
	 * @return ArrayList<MatOfPoint> output from Find_Contours.
	 */
	public ArrayList<MatOfPoint> findContoursOutput() {
		return findContoursOutput;
	}

	/**
	 * This method is a generated getter for the output of a Filter_Contours.
	 * @return ArrayList<MatOfPoint> output from Filter_Contours.
	 */
	public ArrayList<MatOfPoint> filterContoursOutput() {
		return filterContoursOutput;
	}

	/**
	 * This method is a generated getter for the output of a Convex_Hulls.
	 * @return ArrayList<MatOfPoint> output from Convex_Hulls.
	 */
	public ArrayList<MatOfPoint> convexHullsOutput() {
		return convexHullsOutput;
	}


	/**
	 * Segment an image based on hue, saturation, and luminance ranges.
	 *
	 * @param input The image on which to perform the HSL threshold.
	 * @param hue The min and max hue
	 * @param sat The min and max saturation
	 * @param lum The min and max luminance
	 * @param output The image in which to store the output.
	 */
	private void hslThreshold(Mat input, double[] hue, double[] sat, double[] lum,
		Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HLS);
		Core.inRange(out, new Scalar(hue[0], lum[0], sat[0]),
			new Scalar(hue[1], lum[1], sat[1]), out);
	}

	/**
	 * Sets the values of pixels in a binary image to their distance to the nearest black pixel.
	 * @param input The image on which to perform the Distance Transform.
	 * @param type The Transform.
	 * @param maskSize the size of the mask.
	 * @param output The image in which to store the output.
	 */
	private void findContours(Mat input, boolean externalOnly,
		List<MatOfPoint> contours) {
		Mat hierarchy = new Mat();
		contours.clear();
		int mode;
		if (externalOnly) {
			mode = Imgproc.RETR_EXTERNAL;
		}
		else {
			mode = Imgproc.RETR_LIST;
		}
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(input, contours, hierarchy, mode, method);
	}


	/**
	 * Filters out contours that do not meet certain criteria.
	 * @param inputContours is the input list of contours
	 * @param output is the the output list of contours
	 * @param minArea is the minimum area of a contour that will be kept
	 * @param minPerimeter is the minimum perimeter of a contour that will be kept
	 * @param minWidth minimum width of a contour
	 * @param maxWidth maximum width
	 * @param minHeight minimum height
	 * @param maxHeight maximimum height
	 * @param Solidity the minimum and maximum solidity of a contour
	 * @param minVertexCount minimum vertex Count of the contours
	 * @param maxVertexCount maximum vertex Count
	 * @param minRatio minimum ratio of width to height
	 * @param maxRatio maximum ratio of width to height
	 */
	private void filterContours(List<MatOfPoint> inputContours, double minArea,
		double minPerimeter, double minWidth, double maxWidth, double minHeight, double
		maxHeight, double[] solidity, double maxVertexCount, double minVertexCount, double
		minRatio, double maxRatio, List<MatOfPoint> output) {
		final MatOfInt hull = new MatOfInt();
		output.clear();
		//operation
		for (int i = 0; i < inputContours.size(); i++) {
			final MatOfPoint contour = inputContours.get(i);
			final Rect bb = Imgproc.boundingRect(contour);
			if (bb.width < minWidth || bb.width > maxWidth) continue;
			if (bb.height < minHeight || bb.height > maxHeight) continue;
			final double area = Imgproc.contourArea(contour);
			if (area < minArea) continue;
			if (Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < minPerimeter) continue;
			Imgproc.convexHull(contour, hull);
			MatOfPoint mopHull = new MatOfPoint();
			mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
			for (int j = 0; j < hull.size().height; j++) {
				int index = (int)hull.get(j, 0)[0];
				double[] point = new double[] { contour.get(index, 0)[0], contour.get(index, 0)[1]};
				mopHull.put(j, 0, point);
			}
			final double solid = 100 * area / Imgproc.contourArea(mopHull);
			if (solid < solidity[0] || solid > solidity[1]) continue;
			if (contour.rows() < minVertexCount || contour.rows() > maxVertexCount)	continue;
			final double ratio = bb.width / (double)bb.height;
			if (ratio < minRatio || ratio > maxRatio) continue;
			output.add(contour);
		}
	}

	/**
	 * Compute the convex hulls of contours.
	 * @param inputContours The contours on which to perform the operation.
	 * @param outputContours The contours where the output will be stored.
	 */
	private void convexHulls(List<MatOfPoint> inputContours,
		ArrayList<MatOfPoint> outputContours) {
		final MatOfInt hull = new MatOfInt();
		outputContours.clear();
		for (int i = 0; i < inputContours.size(); i++) {
			final MatOfPoint contour = inputContours.get(i);
			final MatOfPoint mopHull = new MatOfPoint();
			Imgproc.convexHull(contour, hull);
			mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
			for (int j = 0; j < hull.size().height; j++) {
				int index = (int) hull.get(j, 0)[0];
				double[] point = new double[] {contour.get(index, 0)[0], contour.get(index, 0)[1]};
				mopHull.put(j, 0, point);
			}
			outputContours.add(mopHull);
		}
	}

	/**
	 * 
	 * 
	 */
	private void findCenterX() {
				
		if (convexHullsOutput.isEmpty())
			center = -2.0;
		else if (convexHullsOutput.size() < 2)
			center = -1.0;
		else if (convexHullsOutput.size() > 2)
			center = -3.0;
		else {
			r1 = Imgproc.boundingRect(convexHullsOutput.get(0));
			r2 = Imgproc.boundingRect(convexHullsOutput.get(1));
			
			w1 = r1.width;
			w2 = r2.width;
			h1 = r1.height;
			h2 = r2.height;
		
			center = (r1.x + (r1.width / 2) + r2.x + (r2.width / 2)) / 2;
		}
	}
	
	/**
	 * 
	 * 
	 */
	public double reportDeltaX() {
		findCenterX();
		if (center < 0)
			deltaX = center * 1000;
		else
			deltaX = center - (IMG_WIDTH / 2.0);
		
		return deltaX;
	}
	
	public void imgProc() {
        cvSink = CameraServer.getInstance().getVideo();
        //System.out.println(source.get(80, 60));
        cvSink.grabFrame(source);
        process(source);
	}


}