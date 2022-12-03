package com.aoc2021;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day20 implements Day {
    private String readAlgorithm(String filename) {
        String algorithm = null;
        try (Scanner sc = new Scanner(new File(filename))) {
            algorithm = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Something went wrong " + e);
        }
        return algorithm;
    }

    private List<List<Character>> readImage(String filename) {
        ArrayList<List<Character>> image = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filename))) {
            sc.nextLine();
            sc.nextLine();
            sc.useDelimiter("");
            while (sc.hasNextLine()) {
                image.add(stringToList(sc.nextLine()));
            }
        } catch (Exception e) {
            System.out.println("Something went wrong " + e);
        }
        return image;
    }

    private List<List<Character>> surroundWithDots(List<List<Character>> image) {
        List<List<Character>> newImage = new ArrayList<>();
        newImage.add(new ArrayList<>());
        for (List<Character> line : image) {
            ArrayList<Character> newLine = new ArrayList<>();
            newLine.add('.');
            newLine.addAll(line);
            newLine.add('.');
            newImage.add(newLine);
        }
        newImage.add(new ArrayList<>());
        for (int i = 0; i < newImage.get(1).size(); i++) {
            newImage.get(0).add('.');
            newImage.get(newImage.size() - 1).add('.');
        }
        return newImage;
    }

    private List<Character> stringToList(String line) {
        List<Character> result = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            result.add(line.charAt(i));
        }
        return result;
    }

    private int determineOutputPixelIndex(List<List<Character>> image, int x, int y) {
        StringBuilder stringIndex = new StringBuilder();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                stringIndex.append(image.get(y + i).get(x + j));
            }
        }
        String binaryString = stringIndex.toString().replace("#", "1").replace(".", "0");
        return Integer.parseInt(binaryString, 2);
    }

    private int countLightPixels(int steps, String filename) {
        String algorithm = readAlgorithm(filename);
        List<List<Character>> image = readImage(filename);
        for (int i = 0; i < steps * 2; i++) {
            image = surroundWithDots(image);
        }
        for (int i = 0; i < steps; i++) {
            image = surroundWithDots(image);
            List<List<Character>> tmpImage = new ArrayList<>();
            for (List<Character> line : image) {
                ArrayList<Character> tmpLine = new ArrayList<>(line);
                tmpImage.add(tmpLine);
            }
            for (int y = 1; y < tmpImage.size() - 1; y++) {
                for (int x = 1; x < tmpImage.get(0).size() - 1; x++) {
                    tmpImage.get(y).set(x, algorithm.charAt(determineOutputPixelIndex(image, x, y)));
                }
            }
            image = tmpImage;
        }
        int count = 0;
        for (int y = steps * 2; y < image.size() - steps * 2; y++) {
            for (int x = steps * 2; x < image.get(0).size() - steps * 2; x++) {
                if (image.get(y).get(x) == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    public String part1(String filename) {
        return String.valueOf(countLightPixels(2, filename));
    }

    public String part2(String filename) {
        return String.valueOf(countLightPixels(50, filename));
    }
}