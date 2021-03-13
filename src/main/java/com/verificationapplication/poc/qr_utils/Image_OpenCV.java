package com.verificationapplication.poc.qr_utils;


import javafx.scene.image.Image;
import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Image_OpenCV {

    public static void main (String[] args){
        process();
    }

    public static void process(){
        OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String sourceImagePath = "/Users/dplower/development/poc/verificationapplication/src/main/resources/ronaldo_input.jpg";
        String targetImagePath = "/Users/dplower/development/poc/verificationapplication/src/main/resources/DP.jpg";

        Mat loadedImage = loadImage(sourceImagePath);

        MatOfRect facesDetected = new MatOfRect();

        CascadeClassifier cascadeClassifier = new CascadeClassifier();
        int minFaceSize = Math.round(loadedImage.rows() * 0.1f);
        cascadeClassifier.load("/Users/dplower/development/poc/verificationapplication/src/main/resources/haarcascade_frontalface_alt.xml");
        cascadeClassifier.detectMultiScale(loadedImage,
                facesDetected,
                1.1,
                3,
                Objdetect.CASCADE_SCALE_IMAGE,
                new Size(minFaceSize, minFaceSize),
                new Size()
        );

        Rect[] facesArray = facesDetected.toArray();
        for(Rect face : facesArray) {
            Imgproc.rectangle(loadedImage, face.tl(), face.br(), new Scalar(0, 0, 255), 3);
        }

        saveImage(loadedImage, targetImagePath);
        Image myImage = mat2Img(loadedImage);

        System.out.println("Hello");



    }


    public static Mat loadImage(String imagePath) {
        Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(imagePath);
    }

    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();

        imgcodecs.imwrite(targetPath, imageMatrix);

    }

    public static Image mat2Img(Mat mat) {
        MatOfByte bytes = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, bytes);
        InputStream inputStream = new ByteArrayInputStream(bytes.toArray());
        return new Image(inputStream);
    }

}
