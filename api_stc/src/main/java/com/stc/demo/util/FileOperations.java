package com.stc.demo.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


public class FileOperations {

	public static byte[] compressFile(byte[] data) {
		Deflater defl = new Deflater();
		defl.setLevel(defl.BEST_COMPRESSION);
		defl.setInput(data);
		defl.finish();

		ByteArrayOutputStream obs = new ByteArrayOutputStream(data.length);
		byte[] temp = new byte[4 * 1024];
		while (!defl.finished()) {
			int size = defl.deflate(temp);
			obs.write(temp, 0, size);
		}
		try {
			obs.close();
		} catch (Exception ignoredeException) {

		}

		return obs.toByteArray();
	}

	public static byte[] decompressFile(byte[] data) {
		Inflater infl = new Inflater();
		infl.setInput(data);
		ByteArrayOutputStream obs = new ByteArrayOutputStream(data.length);
		byte[] temp = new byte[4 * 1024];
		try {
			while (!infl.finished()) {
			int count = infl.inflate(temp);
			obs.write(temp, 0, count);
			}
			obs.close();
		} catch (Exception ignoredeException) {

		}

		return obs.toByteArray();

	}

}
